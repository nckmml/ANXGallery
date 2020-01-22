package com.miui.gallery.provider.deprecated;

import android.content.ContentValues;
import android.text.TextUtils;
import com.miui.gallery.cloud.peopleface.FaceDataManager;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.util.face.FeedbackIgnoredPeople2Server;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PeopleRecommendMediaSet {
    private static Object sLock = new Object();
    private HashMap<String, String> mGroupIdOfFaceMap = new HashMap<>();
    private HashMap<String, Boolean> mPeopleRecommondHistoryMap = new HashMap<>();
    private NormalPeopleFaceMediaSet mRecommendSourcePeople;

    public PeopleRecommendMediaSet(NormalPeopleFaceMediaSet normalPeopleFaceMediaSet) {
        this.mRecommendSourcePeople = normalPeopleFaceMediaSet;
    }

    public static void addSelectItemsToRecommendedMediaSet(ArrayList<String> arrayList, NormalPeopleFaceMediaSet normalPeopleFaceMediaSet) {
        PeopleRecommendMediaSet peopleRecommendMediaSet = new PeopleRecommendMediaSet(normalPeopleFaceMediaSet);
        peopleRecommendMediaSet.refreshRecommendInfo();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            normalPeopleFaceMediaSet.mergeAnAlbumToThis(peopleRecommendMediaSet.mGroupIdOfFaceMap.get(it.next()));
        }
    }

    public static void feedbackIgnoredPeople2Server(ArrayList<String> arrayList, NormalPeopleFaceMediaSet normalPeopleFaceMediaSet) {
        PeopleRecommendMediaSet peopleRecommendMediaSet = new PeopleRecommendMediaSet(normalPeopleFaceMediaSet);
        peopleRecommendMediaSet.refreshRecommendInfo();
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(peopleRecommendMediaSet.mGroupIdOfFaceMap.get(it.next()));
        }
        FeedbackIgnoredPeople2Server.startFeedbackIgnoredPeople2Server(normalPeopleFaceMediaSet.getServerId(), arrayList2);
    }

    private void fillMapGroupIdOfFace(String str) {
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONObject("data").getJSONArray("recommendPeoples");
            if (jSONArray != null) {
                ArrayList<String> queryAllPeopleAlbumServerIds = FaceManager.queryAllPeopleAlbumServerIds();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i).getJSONObject("coreFace");
                    Iterator<String> keys = jSONObject.keys();
                    while (true) {
                        if (keys == null || !keys.hasNext()) {
                            break;
                        }
                        String next = keys.next();
                        String string = jSONArray.getJSONObject(i).getString("peopleId");
                        if (jSONObject.getInt(next) == 1 && queryAllPeopleAlbumServerIds.indexOf(string) != -1) {
                            this.mGroupIdOfFaceMap.put(next, string);
                            break;
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void fillMapPeopleRecommondHistory(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> keys = jSONObject.keys();
                while (keys != null && keys.hasNext()) {
                    String next = keys.next();
                    this.mPeopleRecommondHistoryMap.put(next, Boolean.valueOf(jSONObject.getBoolean(next)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<String> getNeedRecommendPeopleFaceId() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String next : this.mGroupIdOfFaceMap.keySet()) {
            if (!this.mPeopleRecommondHistoryMap.containsKey(next)) {
                this.mPeopleRecommondHistoryMap.put(next, false);
                arrayList.add(next);
            } else if (!this.mPeopleRecommondHistoryMap.get(next).booleanValue()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private static String hashMapToJson(HashMap hashMap) {
        String str = "{";
        for (Map.Entry entry : hashMap.entrySet()) {
            str = (str + "'" + entry.getKey() + "':") + "'" + entry.getValue() + "',";
        }
        return str.substring(0, str.lastIndexOf(",")) + "}";
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.String, android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v2, types: [android.database.Cursor] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    private void queryTableOfPeopleRecommendInfo(String str) {
        ? r0 = 0;
        try {
            r0 = FaceDataManager.queryPeopleRecommondTableToCursor(new String[]{" * "}, "peopleServerId = ? ", new String[]{str}, r0);
            if (r0 != 0 && r0.getCount() > 0) {
                r0.moveToNext();
                fillMapGroupIdOfFace(r0.getString(2));
                fillMapPeopleRecommondHistory(r0.getString(3));
            }
            if (r0 == 0) {
                return;
            }
        } catch (Exception unused) {
            if (r0 == 0) {
                return;
            }
        } catch (Throwable th) {
            if (r0 != 0) {
                r0.close();
            }
            throw th;
        }
        r0.close();
    }

    public static PeopleRecommendMediaSet refreshRecommendHistoryToTrue(ArrayList<String> arrayList, NormalPeopleFaceMediaSet normalPeopleFaceMediaSet) {
        PeopleRecommendMediaSet peopleRecommendMediaSet = new PeopleRecommendMediaSet(normalPeopleFaceMediaSet);
        peopleRecommendMediaSet.refreshRecommendInfo();
        peopleRecommendMediaSet.refreshRecommendHistoryToTrue(arrayList);
        return peopleRecommendMediaSet;
    }

    private void saveHistoryToDB() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("recommendHistoryJson", hashMapToJson(this.mPeopleRecommondHistoryMap));
        FaceDataManager.safeUpdatePeopleRecommend(contentValues, "peopleServerId = ?", new String[]{this.mRecommendSourcePeople.getServerId()});
    }

    public int getActualNeedRecommendPeopleFacePhotoNumber() {
        return FaceManager.queryCountOfPhotosOfOneRecommendAlbum(getServerIdsIn());
    }

    public String getServerIdsIn() {
        ArrayList<String> needRecommendPeopleFaceId = getNeedRecommendPeopleFaceId();
        if (needRecommendPeopleFaceId.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = needRecommendPeopleFaceId.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append("'");
            sb.append(next);
            sb.append("'");
        }
        return sb.toString();
    }

    public void refreshRecommendHistoryToTrue(ArrayList<String> arrayList) {
        synchronized (sLock) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                this.mPeopleRecommondHistoryMap.put(it.next(), true);
            }
            saveHistoryToDB();
        }
    }

    public void refreshRecommendInfo() {
        synchronized (sLock) {
            queryTableOfPeopleRecommendInfo(this.mRecommendSourcePeople.getServerId());
        }
    }
}
