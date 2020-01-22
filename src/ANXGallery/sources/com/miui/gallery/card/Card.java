package com.miui.gallery.card;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.cloud.card.model.CardInfo;
import com.miui.gallery.dao.base.Entity;
import com.miui.gallery.dao.base.TableColumn;
import com.miui.gallery.util.GalleryStatHelper;
import com.miui.gallery.util.GsonUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;

public class Card extends Entity implements Comparable<Card> {
    public static final String BASE_UI_CARD_SELECTION = ("ignored = 0 AND localFlag NOT IN (1,-2,-1,4) AND (" + String.format("%s > %s AND %s < %s", new Object[]{Long.valueOf(System.currentTimeMillis()), "validStart", Long.valueOf(System.currentTimeMillis()), "validEnd"}) + " OR " + nexExportFormat.TAG_FORMAT_TYPE + "<>" + 2 + ")");
    public static final String BASE_UNSYNC_CARD_SELECTION;
    /* access modifiers changed from: private */
    public String mActionUrl;
    /* access modifiers changed from: private */
    public List<String> mAllMediaSha1s;
    /* access modifiers changed from: private */
    public int mBaseColor;
    /* access modifiers changed from: private */
    public List<MediaFeatureItem> mCoverMediaFeatureItems;
    /* access modifiers changed from: private */
    public int mCreateBy;
    /* access modifiers changed from: private */
    public long mCreateTime;
    /* access modifiers changed from: private */
    public String mDescription;
    /* access modifiers changed from: private */
    public String mDetailUrl;
    private HashMap<String, String> mExtras;
    private int mImageResId;
    /* access modifiers changed from: private */
    public Uri mImageUri;
    /* access modifiers changed from: private */
    public boolean mIsDeletable;
    /* access modifiers changed from: private */
    public boolean mIsIgnored;
    /* access modifiers changed from: private */
    public boolean mIsSyncable;
    /* access modifiers changed from: private */
    public boolean mIsTop;
    private int mLocalFlag;
    /* access modifiers changed from: private */
    public CardInfo.OperationInfo mOperationInfo;
    /* access modifiers changed from: private */
    public int mScenarioId;
    /* access modifiers changed from: private */
    public List<String> mSelectedMediaSha1s;
    /* access modifiers changed from: private */
    public String mServerId;
    /* access modifiers changed from: private */
    public long mServerTag;
    /* access modifiers changed from: private */
    public String mTitle;
    /* access modifiers changed from: private */
    public int mType;
    /* access modifiers changed from: private */
    public Record.UniqueKey mUniqueKey;
    /* access modifiers changed from: private */
    public long mUpdateTime;
    /* access modifiers changed from: private */
    public long mValidEndTime;
    /* access modifiers changed from: private */
    public long mValidStartTime;

    public static class Builder {
        private List<String> mAllMediaSha1s;
        private int mBaseColor;
        private final Context mContext;
        private List<MediaFeatureItem> mCoverMediaFeatureItems;
        private int mCreateBy;
        private long mCreateTime;
        private boolean mDeletable = true;
        private String mDescription;
        private String mDetailUrl;
        private int mImageResId;
        private Uri mImageUri;
        private boolean mIsIgnored = false;
        private boolean mIsSyncable = true;
        private boolean mIsTop = false;
        private CardInfo.OperationInfo mOperationInfo;
        private int mScenarioId;
        private List<String> mSelectedMediaSha1s;
        private String mServerId;
        private long mServerTag;
        private String mTitle;
        private int mType = 0;
        private Record.UniqueKey mUniqueKey;
        private long mUpdateTime;
        private long mValidEndTime = Long.MAX_VALUE;
        private long mValidStartTime = 0;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Card build() {
            if (TextUtils.isEmpty(this.mTitle)) {
                Log.e("Card", "the title must not be empty.");
            }
            Uri uri = null;
            Card card = new Card();
            String unused = card.mTitle = this.mTitle;
            String unused2 = card.mDescription = this.mDescription;
            String unused3 = card.mDetailUrl = this.mDetailUrl;
            boolean unused4 = card.mIsDeletable = this.mDeletable;
            int unused5 = card.mBaseColor = this.mBaseColor;
            Record.UniqueKey unused6 = card.mUniqueKey = this.mUniqueKey;
            CardInfo.OperationInfo unused7 = card.mOperationInfo = this.mOperationInfo;
            CardInfo.OperationInfo operationInfo = this.mOperationInfo;
            String unused8 = card.mActionUrl = operationInfo != null ? operationInfo.getUrl() : "";
            Uri uri2 = this.mImageUri;
            if (uri2 != null) {
                uri = Card.decodeUri(uri2);
            } else {
                CardInfo.OperationInfo operationInfo2 = this.mOperationInfo;
                if (operationInfo2 != null) {
                    uri = Uri.parse(operationInfo2.getBackgroundUrl());
                }
            }
            Uri unused9 = card.mImageUri = uri;
            List unused10 = card.mAllMediaSha1s = this.mAllMediaSha1s;
            List unused11 = card.mSelectedMediaSha1s = this.mSelectedMediaSha1s;
            List unused12 = card.mCoverMediaFeatureItems = this.mCoverMediaFeatureItems;
            int unused13 = card.mScenarioId = this.mScenarioId;
            String unused14 = card.mServerId = this.mServerId;
            long unused15 = card.mServerTag = this.mServerTag;
            int unused16 = card.mCreateBy = this.mCreateBy;
            long unused17 = card.mCreateTime = this.mCreateTime;
            long unused18 = card.mUpdateTime = this.mUpdateTime;
            boolean unused19 = card.mIsIgnored = this.mIsIgnored;
            boolean unused20 = card.mIsSyncable = this.mIsSyncable;
            long unused21 = card.mValidStartTime = this.mValidStartTime;
            long unused22 = card.mValidEndTime = this.mValidEndTime;
            boolean unused23 = card.mIsTop = this.mIsTop;
            int i = this.mImageResId;
            if (i > 0) {
                Uri unused24 = card.mImageUri = Card.convertResIdToUri(this.mContext, i);
            }
            int unused25 = card.mType = this.mType;
            return card;
        }

        public Builder setAllMediaSha1s(List<String> list) {
            this.mAllMediaSha1s = list;
            return this;
        }

        public Builder setCoverMediaFeatureItems(List<MediaFeatureItem> list) {
            this.mCoverMediaFeatureItems = list;
            return this;
        }

        public Builder setCreateBy(int i) {
            this.mCreateBy = i;
            return this;
        }

        public Builder setCreateTime(long j) {
            this.mCreateTime = j;
            return this;
        }

        public Builder setDeletable(boolean z) {
            this.mDeletable = z;
            return this;
        }

        public Builder setDescription(String str) {
            this.mDescription = str;
            return this;
        }

        public Builder setDetailUrl(String str) {
            this.mDetailUrl = str;
            return this;
        }

        public Builder setImageUri(Uri uri) {
            this.mImageUri = uri;
            return this;
        }

        public Builder setIsIgnored(boolean z) {
            this.mIsIgnored = z;
            return this;
        }

        public Builder setOperationInfo(CardInfo.OperationInfo operationInfo) {
            this.mOperationInfo = operationInfo;
            return this;
        }

        public Builder setScenarioId(int i) {
            this.mScenarioId = i;
            return this;
        }

        public Builder setSelectedMediaSha1s(List<String> list) {
            this.mSelectedMediaSha1s = list;
            return this;
        }

        public Builder setServerId(String str) {
            this.mServerId = str;
            return this;
        }

        public Builder setServerTag(long j) {
            this.mServerTag = j;
            return this;
        }

        public Builder setSyncable(boolean z) {
            this.mIsSyncable = z;
            return this;
        }

        public Builder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public Builder setTop(boolean z) {
            this.mIsTop = z;
            return this;
        }

        public Builder setType(int i) {
            this.mType = i;
            return this;
        }

        public Builder setUniqueKey(Record.UniqueKey uniqueKey) {
            this.mUniqueKey = uniqueKey;
            return this;
        }

        public Builder setUpdateTime(long j) {
            this.mUpdateTime = j;
            return this;
        }

        public Builder setValidEndTime(long j) {
            if (j <= 0) {
                j = Long.MAX_VALUE;
            }
            this.mValidEndTime = j;
            return this;
        }

        public Builder setValidStartTime(long j) {
            this.mValidStartTime = j;
            return this;
        }
    }

    public static class CardExtraInfo {
        public final boolean isIgnored;
        public final Record.UniqueKey uniqueKey;

        public CardExtraInfo(Record.UniqueKey uniqueKey2, boolean z) {
            this.uniqueKey = uniqueKey2;
            this.isIgnored = z;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("ignored = 0 AND syncable = 1 AND ");
        sb.append(String.format("%s = %s OR %s = %s OR %s = %s", new Object[]{"localFlag", 1, "localFlag", 0, "localFlag", 2}));
        BASE_UNSYNC_CARD_SELECTION = sb.toString();
    }

    private Card() {
        this.mImageResId = -1;
    }

    public static Uri convertResIdToUri(Context context, int i) {
        return Uri.parse(String.format("%s://%s", new Object[]{context.getResources().getResourceTypeName(i), context.getResources().getResourceEntryName(i)}));
    }

    public static int convertUriToResId(Context context, Uri uri) {
        String scheme = uri.getScheme();
        String authority = uri.getAuthority();
        String packageName = context.getPackageName();
        return context.getResources().getIdentifier(String.format("%s/%s", new Object[]{scheme, authority}), (String) null, packageName);
    }

    /* access modifiers changed from: private */
    public static Uri decodeUri(Uri uri) {
        return uri == null ? uri : Uri.parse(Uri.decode(uri.toString()));
    }

    private String getExtra(String str) {
        HashMap<String, String> hashMap = this.mExtras;
        if (hashMap != null) {
            return hashMap.get(str);
        }
        return null;
    }

    private static String mapToString(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return null;
        }
        try {
            return new Gson().toJson((Object) hashMap);
        } catch (Exception e) {
            Log.e("Card", "mapToString occur error.\n", (Object) e);
            return null;
        }
    }

    private void parseStyles(String str) {
        HashMap<String, String> stringToMap = stringToMap(str);
        if (stringToMap != null) {
            this.mBaseColor = stringToInt(stringToMap.get("baseColor"));
        }
    }

    private void putExtra(String str, String str2) {
        if (this.mExtras == null) {
            this.mExtras = new HashMap<>();
        }
        this.mExtras.put(str, str2);
    }

    private void setType(int i) {
        this.mType = i;
    }

    private static int stringToInt(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            Log.e("Card", "stringToInt occur error.\n", (Object) e);
            return 0;
        }
    }

    private static HashMap<String, String> stringToMap(String str) {
        if (str == null) {
            return null;
        }
        try {
            return (HashMap) new Gson().fromJson(str, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (Exception e) {
            Log.e("Card", "stringToMap occur error.\n", (Object) e);
            return null;
        }
    }

    private String wrapStyles() {
        HashMap hashMap = new HashMap();
        hashMap.put("baseColor", String.valueOf(this.mBaseColor));
        return mapToString(hashMap);
    }

    public int compareTo(Card card) {
        int i = -Boolean.compare(this.mIsTop, card.mIsTop);
        return i == 0 ? -Long.compare(this.mCreateTime, card.mCreateTime) : i;
    }

    /* access modifiers changed from: package-private */
    public synchronized void copyFrom(Card card) {
        if (card != null) {
            this.mTitle = card.getTitle();
            this.mDescription = card.getDescription();
            this.mActionUrl = card.getActionUrl();
            this.mDetailUrl = card.getDetailUrl();
            this.mImageUri = card.getImageUri();
            this.mCreateTime = card.getCreateTime();
            this.mIsDeletable = card.isDeletable();
            this.mType = card.getType();
            this.mUniqueKey = card.getUniqueKey();
            this.mOperationInfo = card.getOperationInfo();
            this.mAllMediaSha1s = card.getAllMediaSha1s();
            this.mSelectedMediaSha1s = card.getSelectedMediaSha1s();
            this.mCoverMediaFeatureItems = card.getCoverMediaFeatureItems();
            this.mImageResId = card.getImageResId(GalleryApp.sGetAndroidContext());
            this.mBaseColor = card.getBaseColor();
            this.mScenarioId = card.getScenarioId();
            this.mServerId = card.getServerId();
            this.mServerTag = card.getServerTag();
            this.mLocalFlag = card.getLocalFlag();
            this.mUpdateTime = card.getUpdateTime();
            this.mCreateBy = card.getCreateBy();
            this.mIsIgnored = card.isIgnored();
            this.mIsSyncable = card.isSyncable();
            this.mValidStartTime = card.getValidStartTime();
            this.mValidEndTime = card.getValidEndTime();
            this.mIsTop = card.isTop();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
        return this.mId == ((Card) obj).mId;
    }

    public String generateDuplicateKey() {
        if (getUniqueKey() == null || getUniqueKey().getStartTime() <= 0) {
            return this.mScenarioId + "_" + this.mCreateTime;
        }
        return this.mScenarioId + "_" + getUniqueKey().getStartTime() + "_" + getUniqueKey().getTargetTime() + "_" + getUniqueKey().getLocation();
    }

    public String getActionUrl() {
        return this.mActionUrl;
    }

    public synchronized List<String> getAllMediaSha1s() {
        return this.mAllMediaSha1s == null ? null : new ArrayList(this.mAllMediaSha1s);
    }

    public int getBaseColor() {
        return this.mBaseColor;
    }

    public CardExtraInfo getCardExtraInfo() {
        return new CardExtraInfo(this.mUniqueKey, this.mIsIgnored);
    }

    public List<MediaFeatureItem> getCoverMediaFeatureItems() {
        List<MediaFeatureItem> list = this.mCoverMediaFeatureItems;
        if (list == null) {
            return null;
        }
        return new ArrayList(list);
    }

    public int getCreateBy() {
        return this.mCreateBy;
    }

    public long getCreateTime() {
        return this.mCreateTime;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getDetailUrl() {
        return this.mDetailUrl;
    }

    public int getImageResId(Context context) {
        Uri uri = this.mImageUri;
        if (uri == null) {
            return 0;
        }
        if (this.mImageResId == -1) {
            this.mImageResId = convertUriToResId(context, uri);
        }
        return this.mImageResId;
    }

    public Uri getImageUri() {
        return this.mImageUri;
    }

    public int getLocalFlag() {
        return this.mLocalFlag;
    }

    public CardInfo.OperationInfo getOperationInfo() {
        return this.mOperationInfo;
    }

    public long getRecordStartTime() {
        if (getUniqueKey() != null) {
            return getUniqueKey().getStartTime();
        }
        return -1;
    }

    public long getRecordTargetTime() {
        if (getUniqueKey() != null) {
            return getUniqueKey().getTargetTime();
        }
        return -1;
    }

    public int getScenarioId() {
        return this.mScenarioId;
    }

    public synchronized List<String> getSelectedMediaSha1s() {
        return this.mSelectedMediaSha1s == null ? null : new ArrayList(this.mSelectedMediaSha1s);
    }

    public String getServerId() {
        return this.mServerId;
    }

    public long getServerTag() {
        return this.mServerTag;
    }

    /* access modifiers changed from: protected */
    public List<TableColumn> getTableColumns() {
        ArrayList arrayList = new ArrayList();
        addColumn(arrayList, "cardId", "INTEGER");
        addColumn(arrayList, "title", "TEXT");
        addColumn(arrayList, "description", "TEXT");
        addColumn(arrayList, "actionText", "TEXT");
        addColumn(arrayList, "actionUrl", "TEXT");
        addColumn(arrayList, "detailUrl", "TEXT");
        addColumn(arrayList, "imageUri", "TEXT");
        addColumn(arrayList, "createTime", "INTEGER");
        addColumn(arrayList, "deletable", "INTEGER");
        addColumn(arrayList, nexExportFormat.TAG_FORMAT_TYPE, "INTEGER");
        addColumn(arrayList, "styles", "TEXT");
        addColumn(arrayList, "extras", "TEXT");
        addColumn(arrayList, "scenarioId", "INTEGER");
        addColumn(arrayList, "serverId", "TEXT");
        addColumn(arrayList, "serverTag", "INTEGER");
        addColumn(arrayList, "localFlag", "INTEGER");
        addColumn(arrayList, "updateTime", "INTEGER");
        addColumn(arrayList, "createdBy", "INTEGER");
        addColumn(arrayList, "ignored", "INTEGER");
        addColumn((List<TableColumn>) arrayList, "syncable", "INTEGER", String.valueOf(1));
        addColumn((List<TableColumn>) arrayList, "validStart", "INTEGER", String.valueOf(0));
        addColumn((List<TableColumn>) arrayList, "validEnd", "INTEGER", String.valueOf(Long.MAX_VALUE));
        addColumn((List<TableColumn>) arrayList, "isTop", "INTEGER", String.valueOf(0));
        return arrayList;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getType() {
        return this.mType;
    }

    /* access modifiers changed from: protected */
    public String[] getUniqueConstraints() {
        return new String[]{"_id"};
    }

    public Record.UniqueKey getUniqueKey() {
        return this.mUniqueKey;
    }

    public long getUpdateTime() {
        return this.mUpdateTime;
    }

    public long getValidEndTime() {
        return this.mValidEndTime;
    }

    public long getValidStartTime() {
        return this.mValidStartTime;
    }

    public int hashCode() {
        return (int) this.mId;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0067, code lost:
        return r1;
     */
    public synchronized boolean isCoversNeedRefresh() {
        boolean z = false;
        if (this.mSelectedMediaSha1s == null) {
            return false;
        }
        if (this.mCoverMediaFeatureItems == null) {
            return true;
        }
        HashSet hashSet = new HashSet();
        for (MediaFeatureItem next : this.mCoverMediaFeatureItems) {
            if (next != null) {
                hashSet.add(next.getSha1());
            }
        }
        for (String next2 : this.mSelectedMediaSha1s) {
            if (hashSet.contains(next2)) {
                hashSet.remove(next2);
            }
        }
        if (!hashSet.isEmpty() || (this.mCoverMediaFeatureItems.size() < 5 && this.mSelectedMediaSha1s.size() > this.mCoverMediaFeatureItems.size())) {
            z = true;
        }
    }

    public boolean isDeletable() {
        return this.mIsDeletable;
    }

    public synchronized boolean isEmpty() {
        return this.mType != 2 && !MiscUtil.isValid(this.mSelectedMediaSha1s);
    }

    public boolean isIgnored() {
        return this.mIsIgnored;
    }

    public boolean isLocalDeleted() {
        return this.mLocalFlag == 1;
    }

    public boolean isOutofDate() {
        long currentTimeMillis = System.currentTimeMillis();
        return this.mValidStartTime > currentTimeMillis || this.mValidEndTime < currentTimeMillis;
    }

    public boolean isSyncable() {
        return this.mIsSyncable;
    }

    public boolean isTop() {
        return this.mIsTop;
    }

    public boolean isValid() {
        int i = this.mLocalFlag;
        return i == 0 || i == 2 || i == 3;
    }

    /* access modifiers changed from: protected */
    public synchronized void onConvertToContents(ContentValues contentValues) {
        contentValues.put("title", this.mTitle);
        contentValues.put("description", this.mDescription);
        contentValues.putNull("actionText");
        contentValues.put("actionUrl", this.mActionUrl);
        contentValues.put("detailUrl", this.mDetailUrl);
        if (this.mImageUri == null) {
            contentValues.putNull("imageUri");
        } else {
            contentValues.put("imageUri", this.mImageUri.toString());
        }
        contentValues.put("createTime", Long.valueOf(this.mCreateTime));
        int i = 1;
        contentValues.put("deletable", Integer.valueOf(this.mIsDeletable ? 1 : 0));
        contentValues.put(nexExportFormat.TAG_FORMAT_TYPE, Integer.valueOf(getType()));
        contentValues.put("styles", wrapStyles());
        putExtra("unique_key", GsonUtils.toString(this.mUniqueKey));
        putExtra("operation_info", GsonUtils.toString(this.mOperationInfo));
        putExtra("all_images", GsonUtils.toString(this.mAllMediaSha1s));
        putExtra("selected_images", GsonUtils.toString(this.mSelectedMediaSha1s));
        putExtra("covers", GsonUtils.toString(this.mCoverMediaFeatureItems));
        contentValues.put("extras", mapToString(this.mExtras));
        contentValues.put("scenarioId", Integer.valueOf(this.mScenarioId));
        contentValues.put("serverId", this.mServerId);
        contentValues.put("serverTag", Long.valueOf(this.mServerTag));
        contentValues.put("localFlag", Integer.valueOf(this.mLocalFlag));
        contentValues.put("updateTime", Long.valueOf(this.mUpdateTime));
        contentValues.put("createdBy", Integer.valueOf(this.mCreateBy));
        contentValues.put("ignored", Integer.valueOf(this.mIsIgnored ? 1 : 0));
        contentValues.put("syncable", Integer.valueOf(this.mIsSyncable ? 1 : 0));
        contentValues.put("validStart", Long.valueOf(this.mValidStartTime));
        contentValues.put("validEnd", Long.valueOf(this.mValidEndTime));
        if (!this.mIsTop) {
            i = 0;
        }
        contentValues.put("isTop", Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void onInitFromCursor(Cursor cursor) {
        this.mTitle = getString(cursor, "title");
        this.mDescription = getString(cursor, "description");
        this.mDetailUrl = getString(cursor, "detailUrl");
        this.mActionUrl = getString(cursor, "actionUrl");
        String string = getString(cursor, "imageUri");
        if (string != null) {
            this.mImageUri = Uri.parse(string);
        }
        this.mCreateTime = getLong(cursor, "createTime");
        boolean z = false;
        this.mIsDeletable = getInt(cursor, "deletable") == 1;
        setType(getInt(cursor, nexExportFormat.TAG_FORMAT_TYPE));
        parseStyles(getString(cursor, "styles"));
        this.mExtras = stringToMap(getString(cursor, "extras"));
        this.mUniqueKey = (Record.UniqueKey) GsonUtils.fromJson(getExtra("unique_key"), Record.UniqueKey.class);
        this.mOperationInfo = (CardInfo.OperationInfo) GsonUtils.fromJson(getExtra("operation_info"), CardInfo.OperationInfo.class);
        try {
            this.mAllMediaSha1s = GsonUtils.getArray(getExtra("all_images"), (Type) String.class);
            this.mSelectedMediaSha1s = GsonUtils.getArray(getExtra("selected_images"), (Type) String.class);
            this.mCoverMediaFeatureItems = GsonUtils.getArray(getExtra("covers"), (Type) MediaFeatureItem.class);
        } catch (JSONException e) {
            Log.e("Card", "Create card from cursor error:" + e);
        }
        this.mScenarioId = getInt(cursor, "scenarioId");
        if (this.mScenarioId <= 0) {
            Record.UniqueKey uniqueKey = this.mUniqueKey;
            this.mScenarioId = uniqueKey != null ? uniqueKey.getScenarioId() : 0;
        }
        this.mServerId = getString(cursor, "serverId");
        this.mServerTag = getLong(cursor, "serverTag");
        this.mLocalFlag = getInt(cursor, "localFlag");
        this.mUpdateTime = getLong(cursor, "updateTime");
        this.mCreateBy = getInt(cursor, "createdBy");
        this.mIsIgnored = getInt(cursor, "ignored") == 1;
        this.mIsSyncable = getInt(cursor, "syncable") == 1;
        this.mValidStartTime = getLong(cursor, "validStart");
        this.mValidEndTime = getLong(cursor, "validEnd");
        if (getInt(cursor, "isTop") == 1) {
            z = true;
        }
        this.mIsTop = z;
    }

    public synchronized boolean removeImages(List<String> list) {
        boolean z;
        int i;
        z = false;
        if (!MiscUtil.isValid(this.mSelectedMediaSha1s) || !MiscUtil.isValid(this.mAllMediaSha1s) || !MiscUtil.isValid(list)) {
            i = 0;
        } else {
            i = 0;
            for (String next : list) {
                if (this.mSelectedMediaSha1s.contains(next) && this.mSelectedMediaSha1s.remove(next)) {
                    i++;
                }
                if (this.mAllMediaSha1s.contains(next)) {
                    this.mAllMediaSha1s.remove(next);
                }
            }
        }
        if (i > 0) {
            Log.d("Card", "Delete %d images from Card %d", Integer.valueOf(i), Long.valueOf(this.mId));
        }
        if (i > 0) {
            z = true;
        }
        return z;
    }

    public synchronized void setAllMediaSha1s(List<String> list) {
        if (this.mAllMediaSha1s == null) {
            this.mAllMediaSha1s = new ArrayList();
        }
        this.mAllMediaSha1s.clear();
        if (MiscUtil.isValid(list)) {
            this.mAllMediaSha1s.addAll(list);
        }
    }

    public void setCardExtraInfo(CardExtraInfo cardExtraInfo) {
        if (cardExtraInfo != null) {
            this.mUniqueKey = cardExtraInfo.uniqueKey;
            this.mIsIgnored = cardExtraInfo.isIgnored;
        }
    }

    public synchronized void setCoverMediaFeatureItems(List<MediaFeatureItem> list) {
        if (this.mCoverMediaFeatureItems == null) {
            this.mCoverMediaFeatureItems = new ArrayList();
        }
        this.mCoverMediaFeatureItems.clear();
        if (MiscUtil.isValid(list)) {
            this.mCoverMediaFeatureItems.addAll(list);
        }
    }

    public void setCreateBy(int i) {
        this.mCreateBy = i;
    }

    public void setCreateTime(long j) {
        this.mCreateTime = j;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setLocalFlag(int i) {
        this.mLocalFlag = i;
    }

    public void setScenarioId(int i) {
        this.mScenarioId = i;
    }

    public synchronized void setSelectedMediaSha1s(List<String> list, String str) {
        if (this.mSelectedMediaSha1s == null) {
            this.mSelectedMediaSha1s = new ArrayList();
        }
        this.mSelectedMediaSha1s.clear();
        if (MiscUtil.isValid(list)) {
            this.mSelectedMediaSha1s.addAll(list);
        } else {
            HashMap hashMap = new HashMap(1);
            hashMap.put("from", str);
            GalleryStatHelper.recordCountEvent("assistant", "assistant_card_remove_all_image", hashMap);
            Log.d("Card", android.util.Log.getStackTraceString(new Throwable()));
        }
    }

    public void setServerId(String str) {
        this.mServerId = str;
    }

    public void setServerTag(long j) {
        this.mServerTag = j;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setUpdateTime(long j) {
        this.mUpdateTime = j;
    }
}
