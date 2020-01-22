package com.miui.gallery.provider.deprecated;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.CreateGroupItem;
import com.miui.gallery.cloud.baby.BabyInfo;
import com.miui.gallery.cloud.peopleface.FaceDataManager;
import com.miui.gallery.cloud.peopleface.PeopleFace;
import com.miui.gallery.model.PeopleContactInfo;
import com.miui.gallery.model.SendToCloudFolderItem;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.ui.renameface.FaceAlbumHandlerBase;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryStatHelper;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.baby.CopyFaceAlbumItemsToBabyAlbumTask;
import com.miui.gallery.util.face.PeopleCursorHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class NormalPeopleFaceMediaSet implements Parcelable {
    public static final Parcelable.Creator<NormalPeopleFaceMediaSet> CREATOR = new Parcelable.Creator<NormalPeopleFaceMediaSet>() {
        public NormalPeopleFaceMediaSet createFromParcel(Parcel parcel) {
            return new NormalPeopleFaceMediaSet(parcel);
        }

        public NormalPeopleFaceMediaSet[] newArray(int i) {
            return new NormalPeopleFaceMediaSet[i];
        }
    };
    protected long mId;
    private String mName;
    protected String mServerId;

    public NormalPeopleFaceMediaSet(long j, String str, String str2) {
        this.mId = j;
        this.mServerId = str;
        this.mName = str2;
    }

    public NormalPeopleFaceMediaSet(Parcel parcel) {
        this.mId = parcel.readLong();
        this.mServerId = parcel.readString();
        this.mName = parcel.readString();
    }

    private static void closeCursor(Cursor cursor) {
        MiscUtil.closeSilently(cursor);
    }

    private void deleteLocalGroups(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("localFlag", 2);
        FaceDataManager.safeUpdateFace(contentValues, "_id = ? ", new String[]{str});
    }

    public static void doMoveFacesToAnother(FaceAlbumHandlerBase.FaceFolderItem faceFolderItem, long[] jArr) {
        long j;
        if (faceFolderItem instanceof FaceAlbumHandlerBase.FaceNewFolerItem) {
            initialFaceNewFolerItem(faceFolderItem);
        }
        if (faceFolderItem == null) {
            ignoreFaces(jArr);
            return;
        }
        try {
            j = Long.parseLong(faceFolderItem.getId());
        } catch (NumberFormatException unused) {
            j = -1;
        }
        if (j != -1) {
            move2AnohterAlbum(jArr, j);
        }
    }

    private static boolean doMoveToRelation(long[] jArr, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            PeopleContactInfo queryContactInfoOfOnePerson = FaceManager.queryContactInfoOfOnePerson(j);
            if (queryContactInfoOfOnePerson == null) {
                queryContactInfoOfOnePerson = new PeopleContactInfo();
            } else if (PeopleContactInfo.getRelationValue(PeopleContactInfo.Relation.myself).equals(queryContactInfoOfOnePerson.relationWithMe)) {
                HashMap hashMap = new HashMap(1);
                hashMap.put("MoveToRelation", str);
                hashMap.put("MoveToRelationText", str2);
                GalleryStatHelper.recordCountEvent("people_mark", "move_people_from_myself", hashMap);
            }
            queryContactInfoOfOnePerson.relationWithMe = str;
            queryContactInfoOfOnePerson.relationWithMeText = str2;
            ContentValues contentValues = new ContentValues();
            String formatContactJson = queryContactInfoOfOnePerson.formatContactJson();
            if (!TextUtils.isEmpty(formatContactJson)) {
                contentValues.put("peopleContactJsonInfo", formatContactJson);
            }
            int relationType = PeopleContactInfo.getRelationType(queryContactInfoOfOnePerson.relationWithMe);
            if (PeopleContactInfo.isUserDefineRelation(relationType)) {
                contentValues.put("relationText", str2);
            }
            contentValues.put("relationType", Integer.valueOf(relationType));
            contentValues.put("localFlag", 10);
            arrayList.add(ContentProviderOperation.newUpdate(FaceDataManager.PEOPLE_FACE_URI).withValues(contentValues).withSelection("_id = ? ", new String[]{String.valueOf(j)}).build());
        }
        if (!arrayList.isEmpty()) {
            try {
                GalleryApp.sGetAndroidContext().getContentResolver().applyBatch("com.miui.gallery.cloud.provider", arrayList);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (OperationApplicationException e2) {
                e2.printStackTrace();
            }
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("relation", str);
        GallerySamplingStatHelper.recordCountEvent("people", "people_set_relation", hashMap2);
        return true;
    }

    private static String formatSelectionIn(long[] jArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(jArr[i]);
            if (i < length - 1) {
                stringBuffer.append(',');
            }
        }
        return stringBuffer.toString();
    }

    public static ArrayList<PeopleFace> getBrothers(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor safeQueryFace2ImageByServerId = FaceDataManager.safeQueryFace2ImageByServerId(str);
        if (safeQueryFace2ImageByServerId == null || safeQueryFace2ImageByServerId.getCount() == 0) {
            closeCursor(safeQueryFace2ImageByServerId);
            return null;
        }
        while (safeQueryFace2ImageByServerId.moveToNext()) {
            FaceDataManager.appendId(stringBuffer, safeQueryFace2ImageByServerId.getString(1));
        }
        closeCursor(safeQueryFace2ImageByServerId);
        ArrayList<PeopleFace> arrayList = new ArrayList<>();
        Cursor faceInFaceIds = FaceDataManager.getFaceInFaceIds(stringBuffer.toString(), "");
        if (faceInFaceIds == null || faceInFaceIds.getCount() <= 0) {
            closeCursor(faceInFaceIds);
        } else {
            ArrayList arrayList2 = new ArrayList();
            while (faceInFaceIds.moveToNext()) {
                arrayList2.add(new PeopleFace(faceInFaceIds));
            }
            closeCursor(faceInFaceIds);
            arrayList = FaceDataManager.fillInPeopleInfo(new LinkedList(arrayList2));
        }
        Log.i("PeopleFaceMediaSet", "get brothers cost:" + (System.currentTimeMillis() - currentTimeMillis));
        return arrayList;
    }

    public static void ignoreFaces(long[] jArr) {
        String formatSelectionIn = formatSelectionIn(jArr);
        if (!TextUtils.isEmpty(formatSelectionIn)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("localFlag", 2);
            FaceDataManager.safeUpdateFace(contentValues, String.format(Locale.US, "%s IN (%s)", new Object[]{"_id", formatSelectionIn}), (String[]) null);
        }
    }

    private static void initialFaceNewFolerItem(Object obj) {
        FaceAlbumHandlerBase.FaceNewFolerItem faceNewFolerItem = (FaceAlbumHandlerBase.FaceNewFolerItem) obj;
        if (!faceNewFolerItem.isCreatedInDb()) {
            PeopleFace groupByPeopleName = FaceDataManager.getGroupByPeopleName(GalleryApp.sGetAndroidContext(), faceNewFolerItem.getName());
            if (groupByPeopleName != null) {
                ContentValues contentValues = new ContentValues();
                String[] strArr = {groupByPeopleName._id};
                contentValues.put("visibilityType", 1);
                FaceDataManager.safeUpdateFace(contentValues, "_id = ? ", strArr);
                faceNewFolerItem.setId(groupByPeopleName._id);
            } else {
                faceNewFolerItem.setId(String.valueOf(FaceDataManager.insertOneGroupWithName2DB(faceNewFolerItem.getName(), faceNewFolerItem.getContactjson())));
            }
            faceNewFolerItem.setCreatedInDb();
        }
    }

    public static void merge(Context context, ArrayList<NormalPeopleFaceMediaSet> arrayList, String str) {
        NormalPeopleFaceMediaSet normalPeopleFaceMediaSet;
        if (!arrayList.isEmpty() && !TextUtils.isEmpty(str)) {
            synchronized (FaceDataManager.PEOPLE_FACE_URI) {
                if (arrayList.size() != 1) {
                    PeopleFace groupByPeopleName = FaceDataManager.getGroupByPeopleName(context, str);
                    if (groupByPeopleName == null || TextUtils.isEmpty(groupByPeopleName.serverId)) {
                        Iterator<NormalPeopleFaceMediaSet> it = arrayList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            normalPeopleFaceMediaSet = it.next();
                            if (!TextUtils.isEmpty(normalPeopleFaceMediaSet.getServerId())) {
                                normalPeopleFaceMediaSet.rename(context, str, (PeopleContactInfo) null);
                                break;
                            }
                        }
                    }
                    normalPeopleFaceMediaSet = null;
                    if (normalPeopleFaceMediaSet == null) {
                        if (groupByPeopleName != null) {
                            normalPeopleFaceMediaSet = new NormalPeopleFaceMediaSet(Long.parseLong(groupByPeopleName._id), groupByPeopleName.serverId, str);
                        } else {
                            normalPeopleFaceMediaSet = arrayList.get(0);
                            normalPeopleFaceMediaSet.rename(context, str, (PeopleContactInfo) null);
                        }
                    }
                    arrayList.remove(normalPeopleFaceMediaSet);
                    normalPeopleFaceMediaSet.mergeToThis(arrayList);
                } else if (!str.equalsIgnoreCase(arrayList.get(0).getName())) {
                    arrayList.get(0).rename(context, str, (PeopleContactInfo) null);
                }
            }
        }
    }

    private long mergeGroupA2GroupB(PeopleFace peopleFace, PeopleFace peopleFace2) {
        String str;
        String str2 = peopleFace._id;
        if (TextUtils.isEmpty(peopleFace.serverId)) {
            str = "";
        } else {
            str = "'" + peopleFace.serverId + "'";
        }
        return mergeGroups(str2, str, peopleFace2._id, peopleFace2.serverId);
    }

    private long mergeGroups(String str, String str2, String str3, String str4) {
        long j;
        ContentValues contentValues = new ContentValues();
        if (TextUtils.isEmpty(str2)) {
            contentValues.put("localFlag", 2);
            j = FaceDataManager.safeUpdateFace(contentValues, String.format(Locale.US, "%s in (%s)", new Object[]{"_id", str}), (String[]) null);
        } else {
            contentValues.put("localFlag", 12);
            contentValues.put("localGroupId", str3);
            contentValues.put("groupId", str4);
            j = FaceDataManager.safeUpdateFace(contentValues, String.format(Locale.US, "%s in (%s) and %s != %s and %s != %d", new Object[]{"_id", str, "_id", str3, "localFlag", 2}), (String[]) null);
        }
        FaceDataManager.moveChildrenToAnotherGroup(str, str2, str3, str4);
        return j;
    }

    private void mergeToThis(ArrayList<NormalPeopleFaceMediaSet> arrayList) {
        String concatAll = GalleryUtils.concatAll(arrayList, ",", new GalleryUtils.ConcatConverter<NormalPeopleFaceMediaSet>() {
            public String convertToString(NormalPeopleFaceMediaSet normalPeopleFaceMediaSet) {
                String serverId = normalPeopleFaceMediaSet.getServerId();
                if (TextUtils.isEmpty(serverId) || TextUtils.equals(serverId, NormalPeopleFaceMediaSet.this.getServerId())) {
                    return "";
                }
                return "'" + normalPeopleFaceMediaSet.getBucketId() + "'";
            }
        });
        String concatAll2 = GalleryUtils.concatAll(arrayList, ",", new GalleryUtils.ConcatConverter<NormalPeopleFaceMediaSet>() {
            public String convertToString(NormalPeopleFaceMediaSet normalPeopleFaceMediaSet) {
                return TextUtils.isEmpty(normalPeopleFaceMediaSet.getServerId()) ? String.valueOf(normalPeopleFaceMediaSet.getBucketId()) : "";
            }
        });
        long mergeGroups = mergeGroups(concatAll, GalleryUtils.concatAll(arrayList, ",", new GalleryUtils.ConcatConverter<NormalPeopleFaceMediaSet>() {
            public String convertToString(NormalPeopleFaceMediaSet normalPeopleFaceMediaSet) {
                String serverId = normalPeopleFaceMediaSet.getServerId();
                if (TextUtils.isEmpty(serverId) || TextUtils.equals(serverId, NormalPeopleFaceMediaSet.this.getServerId())) {
                    return "";
                }
                return "'" + serverId + "'";
            }
        }), String.valueOf(getBucketId()), getServerId());
        long mergeGroups2 = mergeGroups(concatAll2, "", String.valueOf(getBucketId()), getServerId());
        Log.i("PeopleFaceMediaSet", "merge result: rowsAffectedSynced=" + mergeGroups + ", rowsAffectedNotSynced=" + mergeGroups2);
    }

    public static void move2AnohterAlbum(long[] jArr, long j) {
        String formatSelectionIn = formatSelectionIn(jArr);
        if (!TextUtils.isEmpty(formatSelectionIn)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("localFlag", 5);
            contentValues.put("groupId", "");
            contentValues.put("localGroupId", Long.valueOf(j));
            FaceDataManager.safeUpdateFace(contentValues, String.format(Locale.US, "%s IN (%s)", new Object[]{"_id", formatSelectionIn}), (String[]) null);
        }
    }

    private void moveFaceFromLocalA2B(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("localFlag", 5);
        contentValues.put("localGroupId", str2);
        FaceDataManager.safeUpdateFace(contentValues, "localGroupId = ? and localFlag != ? ", new String[]{str, String.valueOf(2)});
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: java.lang.Object} */
    /* JADX WARNING: type inference failed for: r12v3, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r12v7, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r12v12 */
    /* JADX WARNING: type inference failed for: r12v13 */
    /* JADX WARNING: type inference failed for: r4v6, types: [java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ac A[Catch:{ Exception -> 0x00c6 }] */
    public static boolean moveFaceToMyselfGroup(Context context, long j) {
        Object obj;
        Cursor cursor;
        List<Long> queryPeopleIdOfRelation = FaceManager.queryPeopleIdOfRelation(context, PeopleContactInfo.Relation.myself.getRelationType());
        boolean z = false;
        if (queryPeopleIdOfRelation == null || !queryPeopleIdOfRelation.contains(Long.valueOf(j))) {
            String relationValue = PeopleContactInfo.getRelationValue(PeopleContactInfo.Relation.myself);
            doMoveToRelation(new long[]{j}, relationValue, relationValue);
        } else {
            queryPeopleIdOfRelation.remove(Long.valueOf(j));
        }
        if (queryPeopleIdOfRelation != null && !queryPeopleIdOfRelation.isEmpty()) {
            String relationValue2 = PeopleContactInfo.getRelationValue(PeopleContactInfo.Relation.unknown);
            doMoveToRelation(MiscUtil.listToArray(queryPeopleIdOfRelation), relationValue2, relationValue2);
        }
        try {
            String defaultNameForMyself = PeopleContactInfo.getDefaultNameForMyself(context);
            Cursor cursor2 = null;
            try {
                Cursor cursor3 = context.getContentResolver().query(GalleryContract.PeopleFace.PERSONS_URI, PeopleCursorHelper.PROJECTION, (String) null, (String[]) null, (String) null);
                if (cursor3 != null) {
                    try {
                        if (cursor3.moveToFirst()) {
                            do {
                                if (Long.valueOf(PeopleCursorHelper.getPeopleLocalId(cursor3)).longValue() == j) {
                                    cursor = PeopleCursorHelper.getPeopleName(cursor3);
                                } else {
                                    cursor = cursor2;
                                    if (defaultNameForMyself.equalsIgnoreCase(PeopleCursorHelper.getPeopleName(cursor3))) {
                                        z = true;
                                        cursor = cursor2;
                                    }
                                }
                                cursor2 = cursor;
                                cursor2 = cursor;
                            } while (cursor3.moveToNext());
                        }
                    } catch (Exception unused) {
                        cursor2 = cursor3;
                        obj = null;
                        try {
                            Log.e("PeopleFaceMediaSet", "Failed to get people when moving face to myself group");
                            MiscUtil.closeSilently(cursor2);
                            HashMap hashMap = new HashMap(2);
                            if (TextUtils.isEmpty(obj)) {
                            }
                            hashMap.put("PeopleOriginName", obj);
                            hashMap.put("MarkOtherPeopleWhenExistMe", String.valueOf(z));
                            GallerySamplingStatHelper.recordCountEvent("people_mark", "mark_people_to_myself", hashMap);
                            return true;
                        } catch (Throwable th) {
                            th = th;
                            cursor3 = cursor2;
                            MiscUtil.closeSilently(cursor3);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        MiscUtil.closeSilently(cursor3);
                        throw th;
                    }
                }
                MiscUtil.closeSilently(cursor3);
                obj = cursor2;
            } catch (Exception unused2) {
                obj = null;
                Log.e("PeopleFaceMediaSet", "Failed to get people when moving face to myself group");
                MiscUtil.closeSilently(cursor2);
                HashMap hashMap2 = new HashMap(2);
                if (TextUtils.isEmpty(obj)) {
                }
                hashMap2.put("PeopleOriginName", obj);
                hashMap2.put("MarkOtherPeopleWhenExistMe", String.valueOf(z));
                GallerySamplingStatHelper.recordCountEvent("people_mark", "mark_people_to_myself", hashMap2);
                return true;
            }
            HashMap hashMap22 = new HashMap(2);
            if (TextUtils.isEmpty(obj)) {
                obj = "NoName";
            }
            hashMap22.put("PeopleOriginName", obj);
            hashMap22.put("MarkOtherPeopleWhenExistMe", String.valueOf(z));
            GallerySamplingStatHelper.recordCountEvent("people_mark", "mark_people_to_myself", hashMap22);
        } catch (Exception unused3) {
            Log.e("PeopleFaceMediaSet", "Error occurred when log event");
        }
        return true;
    }

    public static boolean moveFaceToRelationGroup(long[] jArr, String str, String str2) {
        if (PeopleContactInfo.getRelationType(str) != PeopleContactInfo.Relation.myself.getRelationType()) {
            return doMoveToRelation(jArr, str, str2);
        }
        if (jArr.length <= 1) {
            return moveFaceToMyselfGroup(GalleryApp.sGetAndroidContext(), jArr[0]);
        }
        Log.e("PeopleFaceMediaSet", "Moving more than one person to 'myself' is not allowed!");
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009e, code lost:
        if (-1 == r10) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a0, code lost:
        setName(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a3, code lost:
        return true;
     */
    private boolean rename(Context context, String str, boolean z, PeopleContactInfo peopleContactInfo) {
        PeopleFace peopleFace;
        long j;
        synchronized (FaceDataManager.PEOPLE_FACE_URI) {
            PeopleFace groupByPeopleName = FaceDataManager.getGroupByPeopleName(context, str, getBucketId());
            if (!TextUtils.isEmpty(getServerId())) {
                peopleFace = FaceDataManager.getGroupByServerId(context, getServerId());
            } else {
                peopleFace = FaceDataManager.getGroupByPeopleName(context, getName(), groupByPeopleName != null ? Long.parseLong(groupByPeopleName._id) : -1);
            }
            boolean z2 = false;
            if (groupByPeopleName == null) {
                if (-1 != renameOldGroup(peopleFace, str, peopleContactInfo)) {
                    setName(str);
                }
            } else if (TextUtils.isEmpty(peopleFace.serverId)) {
                moveFaceFromLocalA2B(peopleFace._id, groupByPeopleName._id);
                deleteLocalGroups(peopleFace._id);
                j = -1;
            } else if (TextUtils.isEmpty(groupByPeopleName.serverId)) {
                moveFaceFromLocalA2B(groupByPeopleName._id, peopleFace._id);
                deleteLocalGroups(groupByPeopleName._id);
                j = renameOldGroup(peopleFace, str);
            } else {
                if (GalleryPreferences.Face.isFaceRecommendGroupHidden(peopleFace.serverId) && GalleryPreferences.Face.isFaceRecommendGroupHidden(groupByPeopleName.serverId)) {
                    z2 = true;
                }
                if (z) {
                    j = mergeGroupA2GroupB(groupByPeopleName, peopleFace);
                    GalleryPreferences.Face.setFaceRecommendGroupHidden(peopleFace.serverId, z2);
                    renameOldGroup(peopleFace, str);
                } else {
                    j = mergeGroupA2GroupB(peopleFace, groupByPeopleName);
                    GalleryPreferences.Face.setFaceRecommendGroupHidden(groupByPeopleName.serverId, z2);
                }
            }
        }
    }

    private long renameOldGroup(PeopleFace peopleFace, String str) {
        return renameOldGroup(peopleFace, str, (PeopleContactInfo) null);
    }

    private long renameOldGroup(PeopleFace peopleFace, String str, PeopleContactInfo peopleContactInfo) {
        ContentValues contentValues = new ContentValues();
        if (peopleFace == null) {
            return -1;
        }
        contentValues.put("peopleName", str);
        if (peopleContactInfo != null) {
            String formatContactJson = peopleContactInfo.formatContactJson();
            if (!TextUtils.isEmpty(formatContactJson)) {
                contentValues.put("peopleContactJsonInfo", formatContactJson);
            }
            if (!TextUtils.isEmpty(peopleContactInfo.relationWithMe)) {
                int relationType = PeopleContactInfo.getRelationType(peopleContactInfo.relationWithMe);
                if (relationType >= 0) {
                    contentValues.put("relationType", Integer.valueOf(relationType));
                }
                if (PeopleContactInfo.isUserDefineRelation(relationType)) {
                    contentValues.put("relationText", peopleContactInfo.relationWithMeText);
                }
            }
        }
        if (peopleFace.localFlag == 0) {
            contentValues.put("localFlag", 10);
        }
        return FaceDataManager.safeUpdateFace(contentValues, "_id = ? ", new String[]{peopleFace._id});
    }

    private void setName(String str) {
        this.mName = str;
    }

    public String createBabyAlbumAndAddItems(String str, BabyInfo babyInfo, Activity activity) {
        if (CloudUtils.getGroupByFileName(activity, str) != null) {
            str = str + activity.getString(R.string.baby_suffix);
            ToastUtils.makeTextLong((Context) activity, (CharSequence) activity.getString(R.string.same_name_album_exist_and_toast, new Object[]{str}));
        }
        String str2 = str;
        String localCreateBabyGroup = CreateGroupItem.localCreateBabyGroup(GalleryApp.sGetAndroidContext(), str2);
        CopyFaceAlbumItemsToBabyAlbumTask.instance(activity, getAllImagesData(), new SendToCloudFolderItem(0, localCreateBabyGroup, false, str2, babyInfo), 0, R.string.creating_baby_album).execute(new Void[0]);
        return localCreateBabyGroup;
    }

    public int describeContents() {
        return 0;
    }

    public Cursor getAllImagesData() {
        return FaceManager.queryAllImagesOfOnePerson(getServerId());
    }

    public long getBucketId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getServerId() {
        return this.mServerId;
    }

    public boolean hasName() {
        return !TextUtils.isEmpty(this.mName);
    }

    public void mergeAnAlbumToThis(String str) {
        if (!TextUtils.equals(str, getServerId())) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("localFlag", 12);
            contentValues.put("groupId", getServerId());
            FaceDataManager.safeUpdateFace(contentValues, "serverId = ? and localFlag != ? ", new String[]{str, String.valueOf(2)});
            FaceDataManager.changeChildrenOfPeopleA2PeopleB(str, getServerId());
        }
    }

    public boolean rename(Context context, String str, PeopleContactInfo peopleContactInfo) {
        return rename(context, str, false, peopleContactInfo);
    }

    public boolean rename(Context context, String str, boolean z) {
        return rename(context, str, z, (PeopleContactInfo) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mId);
        parcel.writeString(this.mServerId);
        parcel.writeString(this.mName);
    }
}
