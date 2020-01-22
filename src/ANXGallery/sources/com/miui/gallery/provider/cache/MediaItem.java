package com.miui.gallery.provider.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWindow;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.miui.gallery.provider.MediaSortDateHelper;
import com.miui.gallery.provider.cache.CacheItem;
import com.miui.gallery.provider.cache.Filter;
import com.miui.gallery.sdk.SyncStatus;
import com.miui.gallery.sdk.uploadstatus.ItemType;
import com.miui.gallery.sdk.uploadstatus.SyncProxy;
import com.miui.gallery.sdk.uploadstatus.UploadStatusItem;
import com.miui.gallery.util.GalleryDateUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.Numbers;
import com.miui.gallery.util.StringUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MediaItem implements CacheItem {
    /* access modifiers changed from: private */
    public static final HashMap<String, Integer> PROJECTION = new HashMap<>();
    /* access modifiers changed from: private */
    public Long mAlbumId;
    /* access modifiers changed from: private */
    public String mAlbumServerId;
    /* access modifiers changed from: private */
    public int mAliasCreateDate;
    /* access modifiers changed from: private */
    public int mAliasModifyDate;
    private int mAliasSortDate;
    /* access modifiers changed from: private */
    public long mAliasSortTime;
    /* access modifiers changed from: private */
    public long mBurstGroupId;
    /* access modifiers changed from: private */
    public String mCreatorId;
    /* access modifiers changed from: private */
    public long mDateModified;
    /* access modifiers changed from: private */
    public AlbumDelegate mDelegate;
    /* access modifiers changed from: private */
    public String mDescription;
    /* access modifiers changed from: private */
    public Long mDuration;
    /* access modifiers changed from: private */
    public FavoritesDelegate mFavoritesDelegate;
    /* access modifiers changed from: private */
    public String mFilePath;
    /* access modifiers changed from: private */
    public Integer mHeight;
    /* access modifiers changed from: private */
    public long mId;
    /* access modifiers changed from: private */
    public Boolean mIsSyncing;
    /* access modifiers changed from: private */
    public String mLatitude;
    /* access modifiers changed from: private */
    public Character mLatitudeRef;
    /* access modifiers changed from: private */
    public Long mLocalFlag;
    /* access modifiers changed from: private */
    public String mLocation;
    /* access modifiers changed from: private */
    public String mLongitude;
    /* access modifiers changed from: private */
    public Character mLongitudeRef;
    /* access modifiers changed from: private */
    public String mMicroThumb;
    /* access modifiers changed from: private */
    public String mMimeType;
    /* access modifiers changed from: private */
    public long mMixedTime;
    /* access modifiers changed from: private */
    public Integer mOrientation;
    /* access modifiers changed from: private */
    public byte[] mSecretKey;
    /* access modifiers changed from: private */
    public String mServerId;
    /* access modifiers changed from: private */
    public String mServerStatus;
    /* access modifiers changed from: private */
    public Long mServerTag;
    /* access modifiers changed from: private */
    public String mSha1;
    /* access modifiers changed from: private */
    public Long mSize;
    /* access modifiers changed from: private */
    public Long mSpecialTypeFlags;
    /* access modifiers changed from: private */
    public String mThumbnail;
    /* access modifiers changed from: private */
    public String mTitle;
    /* access modifiers changed from: private */
    public Integer mType;
    /* access modifiers changed from: private */
    public Integer mWidth;

    /* renamed from: com.miui.gallery.provider.cache.MediaItem$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$provider$MediaSortDateHelper$SortDate = new int[MediaSortDateHelper.SortDate.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$gallery$provider$MediaSortDateHelper$SortDate[MediaSortDateHelper.SortDate.CREATE_TIME.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$provider$MediaSortDateHelper$SortDate[MediaSortDateHelper.SortDate.MODIFY_TIME.ordinal()] = 2;
        }
    }

    private static class AliasClearThumbnailFilter extends Filter.CompareFilter<MediaItem> {
        private static final Pattern DELIMITER_PATTERN = Pattern.compile("'\\s*,\\s*'");
        private String mAliasClearThumbnail;
        private Set<String> mFileSet;

        public AliasClearThumbnailFilter(Filter.Comparator comparator, String str) {
            super(-1, comparator, str);
            if (comparator == Filter.Comparator.EQUALS || comparator == Filter.Comparator.NOT_EQUALS) {
                this.mAliasClearThumbnail = str;
            } else if (comparator == Filter.Comparator.IN || comparator == Filter.Comparator.NOT_IN) {
                StringBuilder sb = new StringBuilder(str);
                int length = sb.length();
                if (length <= 0 || sb.charAt(0) != '(') {
                    throw new IllegalArgumentException("argument must start with '('");
                }
                sb.deleteCharAt(0);
                int i = length - 1;
                while (i > 0 && sb.charAt(0) == ' ') {
                    sb.deleteCharAt(0);
                    i--;
                }
                if (i > 0 && sb.charAt(0) == '\'') {
                    sb.deleteCharAt(0);
                    i--;
                }
                if (i > 0) {
                    int i2 = i - 1;
                    if (sb.charAt(i2) == ')') {
                        sb.deleteCharAt(i2);
                        int i3 = i - 1;
                        while (i3 > 0) {
                            int i4 = i3 - 1;
                            if (sb.charAt(i4) != ' ') {
                                break;
                            }
                            sb.deleteCharAt(i4);
                            i3--;
                        }
                        if (i3 > 0) {
                            int i5 = i3 - 1;
                            if (sb.charAt(i5) == '\'') {
                                sb.deleteCharAt(i5);
                                i3--;
                            }
                        }
                        this.mFileSet = new HashSet();
                        if (i3 > 0) {
                            Collections.addAll(this.mFileSet, TextUtils.split(sb.toString(), DELIMITER_PATTERN));
                            return;
                        }
                        return;
                    }
                }
                throw new IllegalArgumentException("argument must end with ')'");
            }
        }

        public MediaItem filter(MediaItem mediaItem) {
            if (this.mComparator == Filter.Comparator.EQUALS && TextUtils.equals(mediaItem.getAliasClearThumbnail(), this.mAliasClearThumbnail)) {
                return mediaItem;
            }
            if (this.mComparator == Filter.Comparator.NOT_NULL && !TextUtils.isEmpty(mediaItem.getAliasClearThumbnail())) {
                return mediaItem;
            }
            if (this.mComparator == Filter.Comparator.NOT_EQUALS && !TextUtils.equals(mediaItem.getAliasClearThumbnail(), this.mAliasClearThumbnail)) {
                return mediaItem;
            }
            if (this.mComparator == Filter.Comparator.IN && this.mFileSet.contains(mediaItem.getAliasClearThumbnail())) {
                return mediaItem;
            }
            if (this.mComparator != Filter.Comparator.NOT_IN || this.mFileSet.contains(mediaItem.getAliasClearThumbnail())) {
                return null;
            }
            return mediaItem;
        }
    }

    public static class Generator implements CacheItem.Generator<MediaItem> {
        /* access modifiers changed from: private */
        public static final Map<String, Integer> COLUMN_MAP = new ArrayMap(PROJECTION.length);
        private static final CacheItem.ColumnMapper COLUMN_MAPPER = new CacheItem.ColumnMapper() {
            public int getIndex(String str) {
                Integer num = (Integer) Generator.COLUMN_MAP.get(str);
                if (num != null) {
                    return num.intValue();
                }
                Log.w(".provider.cache.MediaItem", "column '%s' not found", str);
                return -1;
            }
        };
        private static final String[] PROJECTION = {"_id", "sha1", "microthumbfile", "thumbnailFile", "localFile", "serverType", "title", "duration", "description", "location", "size", "localGroupId", "mimeType", "exifGPSLatitude", "exifGPSLatitudeRef", "exifGPSLongitude", "exifGPSLongitudeRef", "secretKey", "localFlag", "mixedDateTime", "exifImageWidth", "exifImageLength", "serverStatus", "dateModified", "creatorId", "serverTag", "serverId", "groupId", "specialTypeFlags", "exifOrientation"};
        private AlbumDelegate mAlbumDelegate;
        private FavoritesDelegate mFavoritesDelegate;

        private static class CloudIdFilter extends Filter.CompareFilter<MediaItem> {
            private static final Pattern ID_PATTERN = Pattern.compile("\\d+");
            private long mId;
            private Set<Long> mIdSet;

            public CloudIdFilter(Filter.Comparator comparator, String str) {
                super(0, comparator, str);
                if (comparator == Filter.Comparator.EQUALS) {
                    this.mId = Long.parseLong(str);
                } else if (comparator == Filter.Comparator.IN) {
                    this.mIdSet = new ArraySet();
                    Matcher matcher = ID_PATTERN.matcher(str);
                    while (matcher.find()) {
                        this.mIdSet.add(Long.valueOf(Long.parseLong(matcher.group())));
                    }
                }
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && mediaItem.mId == this.mId) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.IN || !this.mIdSet.contains(Long.valueOf(mediaItem.mId))) {
                    return null;
                }
                return mediaItem;
            }
        }

        static {
            COLUMN_MAP.put("_id", 0);
            COLUMN_MAP.put("sha1", 1);
            COLUMN_MAP.put("microthumbfile", 2);
            COLUMN_MAP.put("thumbnailFile", 3);
            COLUMN_MAP.put("localFile", 4);
            COLUMN_MAP.put("serverType", 5);
            COLUMN_MAP.put("title", 6);
            COLUMN_MAP.put("duration", 7);
            COLUMN_MAP.put("description", 8);
            COLUMN_MAP.put("location", 9);
            COLUMN_MAP.put("size", 10);
            COLUMN_MAP.put("localGroupId", 11);
            COLUMN_MAP.put("mimeType", 12);
            COLUMN_MAP.put("exifGPSLatitude", 13);
            COLUMN_MAP.put("exifGPSLatitudeRef", 14);
            COLUMN_MAP.put("exifGPSLongitude", 15);
            COLUMN_MAP.put("exifGPSLongitudeRef", 16);
            COLUMN_MAP.put("secretKey", 17);
            COLUMN_MAP.put("localFlag", 18);
            COLUMN_MAP.put("mixedDateTime", 19);
            COLUMN_MAP.put("exifImageWidth", 20);
            COLUMN_MAP.put("exifImageLength", 21);
            COLUMN_MAP.put("serverStatus", 22);
            COLUMN_MAP.put("dateModified", 23);
            COLUMN_MAP.put("creatorId", 24);
            COLUMN_MAP.put("serverTag", 25);
            COLUMN_MAP.put("serverId", 26);
            COLUMN_MAP.put("groupId", 27);
            COLUMN_MAP.put("specialTypeFlags", 28);
            COLUMN_MAP.put("exifOrientation", 29);
        }

        public Generator(AlbumDelegate albumDelegate, FavoritesDelegate favoritesDelegate) {
            this.mAlbumDelegate = albumDelegate;
            this.mFavoritesDelegate = favoritesDelegate;
        }

        public MediaItem from(long j, ContentValues contentValues) {
            MediaItem mediaItem = new MediaItem();
            long unused = mediaItem.mId = j;
            String unused2 = mediaItem.mSha1 = contentValues.getAsString(PROJECTION[1]);
            Long unused3 = mediaItem.mAlbumId = contentValues.getAsLong(PROJECTION[11]);
            String unused4 = mediaItem.mMicroThumb = contentValues.getAsString(PROJECTION[2]);
            String unused5 = mediaItem.mThumbnail = contentValues.getAsString(PROJECTION[3]);
            String unused6 = mediaItem.mFilePath = contentValues.getAsString(PROJECTION[4]);
            Integer unused7 = mediaItem.mType = contentValues.getAsInteger(PROJECTION[5]);
            String unused8 = mediaItem.mTitle = contentValues.getAsString(PROJECTION[6]);
            Long unused9 = mediaItem.mDuration = contentValues.getAsLong(PROJECTION[7]);
            String unused10 = mediaItem.mDescription = contentValues.getAsString(PROJECTION[8]);
            String unused11 = mediaItem.mLocation = contentValues.getAsString(PROJECTION[9]);
            Long asLong = contentValues.getAsLong(PROJECTION[10]);
            long j2 = 0;
            Long unused12 = mediaItem.mSize = Long.valueOf(asLong == null ? 0 : asLong.longValue());
            String unused13 = mediaItem.mMimeType = contentValues.getAsString(PROJECTION[12]);
            String unused14 = mediaItem.mLatitude = contentValues.getAsString(PROJECTION[13]);
            String asString = contentValues.getAsString(PROJECTION[14]);
            Character ch = null;
            Character unused15 = mediaItem.mLatitudeRef = TextUtils.isEmpty(asString) ? null : Character.valueOf(asString.charAt(0));
            String unused16 = mediaItem.mLongitude = contentValues.getAsString(PROJECTION[15]);
            String asString2 = contentValues.getAsString(PROJECTION[16]);
            if (!TextUtils.isEmpty(asString2)) {
                ch = Character.valueOf(asString2.charAt(0));
            }
            Character unused17 = mediaItem.mLongitudeRef = ch;
            byte[] unused18 = mediaItem.mSecretKey = contentValues.getAsByteArray(PROJECTION[17]);
            Long unused19 = mediaItem.mLocalFlag = contentValues.getAsLong(PROJECTION[18]);
            Integer unused20 = mediaItem.mWidth = contentValues.getAsInteger(PROJECTION[20]);
            Integer unused21 = mediaItem.mHeight = contentValues.getAsInteger(PROJECTION[21]);
            String unused22 = mediaItem.mServerStatus = contentValues.getAsString(PROJECTION[22]);
            Long asLong2 = contentValues.getAsLong(PROJECTION[19]);
            long unused23 = mediaItem.mMixedTime = asLong2 == null ? 0 : asLong2.longValue();
            Long asLong3 = contentValues.getAsLong(PROJECTION[23]);
            if (asLong3 != null) {
                j2 = asLong3.longValue();
            }
            long unused24 = mediaItem.mDateModified = j2;
            int unused25 = mediaItem.mAliasCreateDate = GalleryDateUtils.format(mediaItem.mMixedTime);
            String unused26 = mediaItem.mCreatorId = contentValues.getAsString(PROJECTION[24]);
            AlbumDelegate unused27 = mediaItem.mDelegate = this.mAlbumDelegate;
            Long unused28 = mediaItem.mServerTag = contentValues.getAsLong(PROJECTION[25]);
            String unused29 = mediaItem.mServerId = contentValues.getAsString(PROJECTION[26]);
            int unused30 = mediaItem.mAliasModifyDate = GalleryDateUtils.format(mediaItem.mDateModified);
            FavoritesDelegate unused31 = mediaItem.mFavoritesDelegate = this.mFavoritesDelegate;
            String unused32 = mediaItem.mAlbumServerId = contentValues.getAsString(PROJECTION[27]);
            Long unused33 = mediaItem.mSpecialTypeFlags = contentValues.getAsLong(PROJECTION[28]);
            mediaItem.regenerateSortTimeAndDate();
            Integer unused34 = mediaItem.mOrientation = contentValues.getAsInteger(PROJECTION[29]);
            long unused35 = mediaItem.mBurstGroupId = MediaItem.generateBurstGroupId(mediaItem.mTitle);
            return mediaItem;
        }

        public MediaItem from(Cursor cursor) {
            MediaItem mediaItem = new MediaItem();
            long unused = mediaItem.mId = cursor.getLong(0);
            String unused2 = mediaItem.mSha1 = ParseUtils.getString(cursor, 1);
            Long unused3 = mediaItem.mAlbumId = ParseUtils.getLong(cursor, 11);
            String unused4 = mediaItem.mMicroThumb = ParseUtils.getString(cursor, 2);
            String unused5 = mediaItem.mThumbnail = ParseUtils.getString(cursor, 3);
            String unused6 = mediaItem.mFilePath = ParseUtils.getString(cursor, 4);
            Integer unused7 = mediaItem.mType = ParseUtils.getInt(cursor, 5);
            String unused8 = mediaItem.mTitle = ParseUtils.getString(cursor, 6);
            Long unused9 = mediaItem.mDuration = ParseUtils.getLong(cursor, 7);
            String unused10 = mediaItem.mDescription = ParseUtils.getString(cursor, 8);
            String unused11 = mediaItem.mLocation = ParseUtils.getString(cursor, 9);
            Long unused12 = mediaItem.mSize = Long.valueOf(cursor.getLong(10));
            String unused13 = mediaItem.mMimeType = ParseUtils.getString(cursor, 12);
            String unused14 = mediaItem.mLatitude = ParseUtils.getString(cursor, 13);
            Character unused15 = mediaItem.mLatitudeRef = ParseUtils.getChar(cursor, 14);
            String unused16 = mediaItem.mLongitude = ParseUtils.getString(cursor, 15);
            Character unused17 = mediaItem.mLongitudeRef = ParseUtils.getChar(cursor, 16);
            byte[] unused18 = mediaItem.mSecretKey = ParseUtils.getBlob(cursor, 17);
            Long unused19 = mediaItem.mLocalFlag = ParseUtils.getLong(cursor, 18);
            Integer unused20 = mediaItem.mWidth = ParseUtils.getInt(cursor, 20);
            Integer unused21 = mediaItem.mHeight = ParseUtils.getInt(cursor, 21);
            String unused22 = mediaItem.mServerStatus = ParseUtils.getString(cursor, 22);
            long unused23 = mediaItem.mMixedTime = cursor.getLong(19);
            long unused24 = mediaItem.mDateModified = cursor.getLong(23);
            int unused25 = mediaItem.mAliasCreateDate = GalleryDateUtils.format(mediaItem.mMixedTime);
            String unused26 = mediaItem.mCreatorId = cursor.getString(24);
            AlbumDelegate unused27 = mediaItem.mDelegate = this.mAlbumDelegate;
            Long unused28 = mediaItem.mServerTag = Long.valueOf(cursor.getLong(25));
            String unused29 = mediaItem.mServerId = cursor.getString(26);
            int unused30 = mediaItem.mAliasModifyDate = GalleryDateUtils.format(mediaItem.mDateModified);
            FavoritesDelegate unused31 = mediaItem.mFavoritesDelegate = this.mFavoritesDelegate;
            String unused32 = mediaItem.mAlbumServerId = ParseUtils.getString(cursor, 27);
            Long unused33 = mediaItem.mSpecialTypeFlags = ParseUtils.getLong(cursor, 28);
            mediaItem.regenerateSortTimeAndDate();
            Integer unused34 = mediaItem.mOrientation = ParseUtils.getInt(cursor, 29);
            long unused35 = mediaItem.mBurstGroupId = MediaItem.generateBurstGroupId(mediaItem.mTitle);
            return mediaItem;
        }

        public Filter.CompareFilter<MediaItem> getFilter(int i, Filter.Comparator comparator, String str) {
            return i == 0 ? new CloudIdFilter(comparator, str) : i == 1 ? new QueryFactory.Sha1Filter(comparator, str) : Filter.NOT_SUPPORTED_FILTER;
        }

        public CacheItem.ColumnMapper getMapper() {
            return COLUMN_MAPPER;
        }

        public String[] getProjection() {
            return PROJECTION;
        }

        public void update(MediaItem mediaItem, ContentValues contentValues) {
            boolean z;
            if (contentValues.containsKey(PROJECTION[1])) {
                String unused = mediaItem.mSha1 = contentValues.getAsString(PROJECTION[1]);
            }
            boolean z2 = false;
            if (contentValues.containsKey(PROJECTION[11])) {
                Long unused2 = mediaItem.mAlbumId = contentValues.getAsLong(PROJECTION[11]);
                z = true;
            } else {
                z = false;
            }
            if (contentValues.containsKey(PROJECTION[2])) {
                String unused3 = mediaItem.mMicroThumb = contentValues.getAsString(PROJECTION[2]);
            }
            if (contentValues.containsKey(PROJECTION[3])) {
                String unused4 = mediaItem.mThumbnail = contentValues.getAsString(PROJECTION[3]);
            }
            if (contentValues.containsKey(PROJECTION[4])) {
                String unused5 = mediaItem.mFilePath = contentValues.getAsString(PROJECTION[4]);
            }
            if (contentValues.containsKey(PROJECTION[5])) {
                Integer unused6 = mediaItem.mType = contentValues.getAsInteger(PROJECTION[5]);
            }
            if (contentValues.containsKey(PROJECTION[6])) {
                String unused7 = mediaItem.mTitle = contentValues.getAsString(PROJECTION[6]);
                long unused8 = mediaItem.mBurstGroupId = MediaItem.generateBurstGroupId(mediaItem.mTitle);
            }
            if (contentValues.containsKey(PROJECTION[7])) {
                Long unused9 = mediaItem.mDuration = contentValues.getAsLong(PROJECTION[7]);
            }
            if (contentValues.containsKey(PROJECTION[8])) {
                String unused10 = mediaItem.mDescription = contentValues.getAsString(PROJECTION[8]);
            }
            if (contentValues.containsKey(PROJECTION[9])) {
                String unused11 = mediaItem.mLocation = contentValues.getAsString(PROJECTION[9]);
            }
            long j = 0;
            if (contentValues.containsKey(PROJECTION[10])) {
                Long asLong = contentValues.getAsLong(PROJECTION[10]);
                Long unused12 = mediaItem.mSize = Long.valueOf(asLong == null ? 0 : asLong.longValue());
            }
            if (contentValues.containsKey(PROJECTION[12])) {
                String unused13 = mediaItem.mMimeType = contentValues.getAsString(PROJECTION[12]);
            }
            if (contentValues.containsKey(PROJECTION[13])) {
                String unused14 = mediaItem.mLatitude = contentValues.getAsString(PROJECTION[13]);
            }
            if (contentValues.containsKey(PROJECTION[14])) {
                String asString = contentValues.getAsString(PROJECTION[14]);
                Character unused15 = mediaItem.mLatitudeRef = asString == null ? null : Character.valueOf(asString.charAt(0));
            }
            if (contentValues.containsKey(PROJECTION[15])) {
                String unused16 = mediaItem.mLongitude = contentValues.getAsString(PROJECTION[15]);
            }
            if (contentValues.containsKey(PROJECTION[16])) {
                String asString2 = contentValues.getAsString(PROJECTION[16]);
                Character unused17 = mediaItem.mLongitudeRef = asString2 == null ? null : Character.valueOf(asString2.charAt(0));
            }
            if (contentValues.containsKey(PROJECTION[19])) {
                Long asLong2 = contentValues.getAsLong(PROJECTION[19]);
                long unused18 = mediaItem.mMixedTime = asLong2 == null ? 0 : asLong2.longValue();
                int unused19 = mediaItem.mAliasCreateDate = GalleryDateUtils.format(mediaItem.mMixedTime);
                z = true;
            }
            if (contentValues.containsKey(PROJECTION[17])) {
                byte[] unused20 = mediaItem.mSecretKey = contentValues.getAsByteArray(PROJECTION[17]);
            }
            if (contentValues.containsKey(PROJECTION[18])) {
                Long unused21 = mediaItem.mLocalFlag = contentValues.getAsLong(PROJECTION[18]);
            }
            if (contentValues.containsKey("sync_status")) {
                if (SyncStatus.valueOf(contentValues.getAsString("sync_status")) == SyncStatus.STATUS_INIT) {
                    z2 = true;
                }
                Boolean unused22 = mediaItem.mIsSyncing = Boolean.valueOf(z2);
            }
            if (contentValues.containsKey(PROJECTION[20])) {
                Integer unused23 = mediaItem.mWidth = contentValues.getAsInteger(PROJECTION[20]);
            }
            if (contentValues.containsKey(PROJECTION[21])) {
                Integer unused24 = mediaItem.mHeight = contentValues.getAsInteger(PROJECTION[21]);
            }
            if (contentValues.containsKey(PROJECTION[22])) {
                String unused25 = mediaItem.mServerStatus = contentValues.getAsString(PROJECTION[22]);
            }
            if (contentValues.containsKey(PROJECTION[23])) {
                Long asLong3 = contentValues.getAsLong(PROJECTION[23]);
                if (asLong3 != null) {
                    j = asLong3.longValue();
                }
                long unused26 = mediaItem.mDateModified = j;
                int unused27 = mediaItem.mAliasModifyDate = GalleryDateUtils.format(mediaItem.mDateModified);
                z = true;
            }
            if (contentValues.containsKey(PROJECTION[24])) {
                String unused28 = mediaItem.mCreatorId = contentValues.getAsString(PROJECTION[24]);
            }
            if (contentValues.containsKey(PROJECTION[25])) {
                Long unused29 = mediaItem.mServerTag = contentValues.getAsLong(PROJECTION[25]);
            }
            if (contentValues.containsKey(PROJECTION[26])) {
                String unused30 = mediaItem.mServerId = contentValues.getAsString(PROJECTION[26]);
            }
            if (contentValues.containsKey(PROJECTION[27])) {
                String unused31 = mediaItem.mAlbumServerId = contentValues.getAsString(PROJECTION[27]);
            }
            if (contentValues.containsKey(PROJECTION[28])) {
                Long unused32 = mediaItem.mSpecialTypeFlags = contentValues.getAsLong(PROJECTION[28]);
            }
            if (z) {
                mediaItem.regenerateSortTimeAndDate();
            }
            if (contentValues.containsKey(PROJECTION[29])) {
                Integer unused33 = mediaItem.mOrientation = contentValues.getAsInteger(PROJECTION[29]);
            }
        }
    }

    public static class QueryFactory implements CacheItem.QueryFactory<MediaItem> {
        private static final CacheItem.ColumnMapper COLUMN_MAPPER = new CacheItem.ColumnMapper() {
            public int getIndex(String str) {
                Integer num = (Integer) MediaItem.PROJECTION.get(str);
                if (num != null) {
                    return num.intValue();
                }
                Log.e(".provider.cache.MediaItem", "column '%s' not found", (Object) str);
                return -1;
            }
        };
        private static Filter.CompareFilter<MediaItem> IS_FAVORITE_FILTER = new Filter.CompareFilter<MediaItem>(33, (Filter.Comparator) null, (String) null) {
            public MediaItem filter(MediaItem mediaItem) {
                if (mediaItem.getAliasIsFavorite() == CacheItem.TRUE) {
                    return mediaItem;
                }
                return null;
            }
        };
        private static Filter.CompareFilter<MediaItem> NOT_HIDDEN_FILTER = new Filter.CompareFilter<MediaItem>(27, (Filter.Comparator) null, (String) null) {
            public MediaItem filter(MediaItem mediaItem) {
                if (mediaItem.getAliasHidden() == CacheItem.FALSE) {
                    return mediaItem;
                }
                return null;
            }
        };
        private static final CacheItem.Merger<MediaItem> SHA1_MERGER = new CacheItem.Merger<MediaItem>() {
            public MediaItem merge(MediaItem mediaItem, MediaItem mediaItem2, int i) {
                return mediaItem.getAliasSyncState().longValue() < mediaItem2.getAliasSyncState().longValue() ? mediaItem : mediaItem2;
            }
        };
        private static Filter.CompareFilter<MediaItem> SHOW_IN_HOMEPAGE_FILTER = new Filter.CompareFilter<MediaItem>(24, (Filter.Comparator) null, (String) null) {
            public MediaItem filter(MediaItem mediaItem) {
                if (mediaItem.getAliasShowInHomePage() == CacheItem.TRUE) {
                    return mediaItem;
                }
                return null;
            }
        };

        private static class AlbumFilter extends Filter.CompareFilter<MediaItem> {
            private long mAlbumId;

            public AlbumFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                this.mAlbumId = Long.parseLong(str);
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && Numbers.equals(mediaItem.mAlbumId, this.mAlbumId)) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.NOT_EQUALS || Numbers.equals(mediaItem.mAlbumId, this.mAlbumId)) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class AlbumServerIdFilter extends Filter.CompareFilter<MediaItem> {
            private String mAlbumServerId;

            public AlbumServerIdFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                this.mAlbumServerId = str;
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && TextUtils.equals(mediaItem.mAlbumServerId, this.mAlbumServerId)) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.NOT_EQUALS || TextUtils.equals(mediaItem.mAlbumServerId, this.mAlbumServerId)) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class AliasCreateDateFilter extends Filter.CompareFilter<MediaItem> {
            private static final Pattern ID_PATTERN = Pattern.compile("\\d+");
            private int mAliasCreateDate;
            private Set<Integer> mCreateDateSet;

            public AliasCreateDateFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                if (comparator == Filter.Comparator.EQUALS) {
                    this.mAliasCreateDate = Integer.parseInt(str);
                } else if (comparator == Filter.Comparator.IN) {
                    this.mCreateDateSet = new HashSet();
                    Matcher matcher = ID_PATTERN.matcher(str);
                    while (matcher.find()) {
                        this.mCreateDateSet.add(Integer.valueOf(Integer.parseInt(matcher.group())));
                    }
                }
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && mediaItem.mAliasCreateDate == this.mAliasCreateDate) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.IN || !this.mCreateDateSet.contains(Integer.valueOf(mediaItem.mAliasCreateDate))) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class DateModifiedComparator extends TimeComparator {
            public DateModifiedComparator(boolean z) {
                super(z);
            }

            public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                int compare = Long.compare(mediaItem.mDateModified, mediaItem2.mDateModified);
                return compare == 0 ? super.compare(mediaItem, mediaItem2) : this.mDescent ? -compare : compare;
            }
        }

        private static class IdFilter extends Filter.CompareFilter<MediaItem> {
            private static final Pattern ID_PATTERN = Pattern.compile("\\d+");
            private long mId;
            private Set<Long> mIdSet;

            public IdFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                if (comparator == Filter.Comparator.EQUALS) {
                    this.mId = Long.parseLong(str);
                } else if (comparator == Filter.Comparator.IN) {
                    this.mIdSet = new ArraySet();
                    Matcher matcher = ID_PATTERN.matcher(str);
                    while (matcher.find()) {
                        this.mIdSet.add(Long.valueOf(Long.parseLong(matcher.group())));
                    }
                }
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && mediaItem.mId == this.mId) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.IN || !this.mIdSet.contains(Long.valueOf(mediaItem.mId))) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class LocalFileFilter extends Filter.CompareFilter<MediaItem> {
            private static final Pattern DELIMITER_PATTERN = Pattern.compile("'\\s*,\\s*'");
            private String mLocalFile;
            private Set<String> mLocalFileSet;

            public LocalFileFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                if (comparator == Filter.Comparator.EQUALS || comparator == Filter.Comparator.NOT_EQUALS) {
                    this.mLocalFile = str;
                } else if (comparator == Filter.Comparator.IN || comparator == Filter.Comparator.NOT_IN) {
                    StringBuilder sb = new StringBuilder(str);
                    int length = sb.length();
                    if (length <= 0 || sb.charAt(0) != '(') {
                        throw new IllegalArgumentException("argument must start with '('");
                    }
                    sb.deleteCharAt(0);
                    int i = length - 1;
                    while (i > 0 && sb.charAt(0) == ' ') {
                        sb.deleteCharAt(0);
                        i--;
                    }
                    if (i > 0 && sb.charAt(0) == '\'') {
                        sb.deleteCharAt(0);
                        i--;
                    }
                    if (i > 0) {
                        int i2 = i - 1;
                        if (sb.charAt(i2) == ')') {
                            sb.deleteCharAt(i2);
                            int i3 = i - 1;
                            while (i3 > 0) {
                                int i4 = i3 - 1;
                                if (sb.charAt(i4) != ' ') {
                                    break;
                                }
                                sb.deleteCharAt(i4);
                                i3--;
                            }
                            if (i3 > 0) {
                                int i5 = i3 - 1;
                                if (sb.charAt(i5) == '\'') {
                                    sb.deleteCharAt(i5);
                                    i3--;
                                }
                            }
                            this.mLocalFileSet = new HashSet();
                            if (i3 > 0) {
                                Collections.addAll(this.mLocalFileSet, TextUtils.split(sb.toString(), DELIMITER_PATTERN));
                                return;
                            }
                            return;
                        }
                    }
                    throw new IllegalArgumentException("argument must end with ')'");
                }
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && TextUtils.equals(mediaItem.mFilePath, this.mLocalFile)) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.NOT_NULL && !TextUtils.isEmpty(mediaItem.mFilePath)) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.NOT_EQUALS && !TextUtils.equals(mediaItem.mFilePath, this.mLocalFile)) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.IN && this.mLocalFileSet.contains(mediaItem.mFilePath)) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.NOT_IN || this.mLocalFileSet.contains(mediaItem.mFilePath)) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class LocalFlagFilter extends Filter.CompareFilter<MediaItem> {
            private int mLocalFlag;

            public LocalFlagFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                this.mLocalFlag = Integer.parseInt(str);
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && Numbers.equals(mediaItem.mLocalFlag, this.mLocalFlag)) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.NOT_EQUALS || Numbers.equals(mediaItem.mLocalFlag, this.mLocalFlag)) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class LocationFilter extends Filter.CompareFilter<MediaItem> {
            private String mLocation;

            public LocationFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                this.mLocation = str;
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && TextUtils.equals(mediaItem.mLocation, this.mLocation)) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.NOT_NULL || mediaItem.mLocation == null) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class MimeTypeFilter extends Filter.CompareFilter<MediaItem> {
            private static final Pattern MIMETYPE_PATTERN = Pattern.compile("\\(|\\)|\\'");
            private Set<String> mMimeTypeSets;
            private String mMineType;

            public MimeTypeFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                if (comparator == Filter.Comparator.NOT_IN || comparator == Filter.Comparator.IN) {
                    this.mMimeTypeSets = new HashSet();
                    String[] split = MIMETYPE_PATTERN.matcher(str).replaceAll("").split(",");
                    int i = 0;
                    while (split != null && i < split.length) {
                        this.mMimeTypeSets.add(split[i]);
                        i++;
                    }
                    return;
                }
                this.mMineType = str;
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && StringUtils.equalsIgnoreCase(mediaItem.mMimeType, this.mMineType)) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.NOT_EQUALS && !StringUtils.equalsIgnoreCase(mediaItem.mMimeType, this.mMineType)) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.NOT_IN && !this.mMimeTypeSets.contains(mediaItem.mMimeType)) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.IN || !this.mMimeTypeSets.contains(mediaItem.mMimeType)) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class ServerIdFilter extends Filter.CompareFilter<MediaItem> {
            private static final Pattern ID_PATTERN = Pattern.compile("[0-9]+");
            private Set<String> mIdSet;
            private String mServerId;

            public ServerIdFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                if (comparator == Filter.Comparator.EQUALS) {
                    this.mServerId = str;
                } else if (comparator == Filter.Comparator.IN) {
                    this.mIdSet = new HashSet();
                    Matcher matcher = ID_PATTERN.matcher(str);
                    while (matcher.find()) {
                        this.mIdSet.add(matcher.group());
                    }
                }
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && TextUtils.equals(mediaItem.mServerId, this.mServerId)) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.IN && this.mIdSet.contains(mediaItem.mServerId)) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.NOT_NULL || mediaItem.mServerId == null) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class ServerTagFilter extends Filter.CompareFilter<MediaItem> {
            private long mServerTag;

            public ServerTagFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                this.mServerTag = Long.parseLong(str);
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator != Filter.Comparator.GREATER_OR_EQUAL || Numbers.compare(Long.valueOf(this.mServerTag), mediaItem.mServerTag) > 0) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class Sha1Filter extends Filter.CompareFilter<MediaItem> {
            private static final Pattern SHA1_PATTERN = Pattern.compile("[0-9a-fA-F]+");
            private String mSha1;
            private Set<String> mSha1Set;

            public Sha1Filter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                if (comparator == Filter.Comparator.EQUALS) {
                    this.mSha1 = str;
                } else if (comparator == Filter.Comparator.IN) {
                    this.mSha1Set = new ArraySet();
                    Matcher matcher = SHA1_PATTERN.matcher(str);
                    while (matcher.find()) {
                        this.mSha1Set.add(matcher.group());
                    }
                }
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && TextUtils.equals(mediaItem.mSha1, this.mSha1)) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.IN && this.mSha1Set.contains(mediaItem.mSha1)) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.NOT_NULL || mediaItem.mSha1 == null) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class SizeComparator implements Comparator<MediaItem> {
            private boolean mDescent;

            public SizeComparator(boolean z) {
                this.mDescent = z;
            }

            public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                int compare = Long.compare(mediaItem.mSize.longValue(), mediaItem2.mSize.longValue());
                return this.mDescent ? -compare : compare;
            }
        }

        private static class SizeFilter extends Filter.CompareFilter<MediaItem> {
            private long mSize;

            public SizeFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                this.mSize = Long.parseLong(str);
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && mediaItem.mSize.longValue() == this.mSize) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.GREATER && mediaItem.mSize.longValue() > this.mSize) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.GREATER_OR_EQUAL && mediaItem.mSize.longValue() >= this.mSize) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.LESS && mediaItem.mSize.longValue() < this.mSize) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.LESS_OR_EQUAL || mediaItem.mSize.longValue() > this.mSize) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class SortTimeComparator extends TimeComparator {
            public SortTimeComparator(boolean z) {
                super(z);
            }

            public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                int compare = Long.compare(mediaItem.mAliasSortTime, mediaItem2.mAliasSortTime);
                return compare == 0 ? super.compare(mediaItem, mediaItem2) : this.mDescent ? -compare : compare;
            }
        }

        private static class SyncStateFilter extends Filter.CompareFilter<MediaItem> {
            private int mSyncState;

            public SyncStateFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                this.mSyncState = Integer.parseInt(str);
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && ((long) this.mSyncState) == mediaItem.getAliasSyncState().longValue()) {
                    return mediaItem;
                }
                return null;
            }
        }

        private static class ThumbnailFilter extends Filter.CompareFilter<MediaItem> {
            private static final Pattern THUMBNAIL_PATTERN = Pattern.compile("[0-9a-fA-F]+");
            private String mThumbnail;

            public ThumbnailFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                if (comparator == Filter.Comparator.EQUALS || comparator == Filter.Comparator.NOT_EQUALS) {
                    this.mThumbnail = str;
                }
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator == Filter.Comparator.EQUALS && TextUtils.equals(mediaItem.mThumbnail, this.mThumbnail)) {
                    return mediaItem;
                }
                if (this.mComparator == Filter.Comparator.NOT_NULL && mediaItem.mThumbnail != null) {
                    return mediaItem;
                }
                if (this.mComparator != Filter.Comparator.NOT_EQUALS || TextUtils.equals(mediaItem.mThumbnail, this.mThumbnail)) {
                    return null;
                }
                return mediaItem;
            }
        }

        private static class TimeComparator implements Comparator<MediaItem> {
            protected boolean mDescent;

            public TimeComparator(boolean z) {
                this.mDescent = z;
            }

            public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                int compare = Long.compare(mediaItem.mMixedTime, mediaItem2.mMixedTime);
                if (compare == 0 && mediaItem.mDateModified != mediaItem2.mDateModified) {
                    compare = Long.compare(mediaItem.mDateModified, mediaItem2.mDateModified);
                } else if (compare == 0) {
                    compare = Long.compare(mediaItem.mId, mediaItem2.mId);
                }
                return this.mDescent ? -compare : compare;
            }
        }

        private static class TitleComparator implements Comparator<MediaItem> {
            private boolean mDescent;

            public TitleComparator(boolean z) {
                this.mDescent = z;
            }

            public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
                int i;
                if (mediaItem.mTitle != null && mediaItem2.mTitle != null) {
                    i = mediaItem.mTitle.compareTo(mediaItem2.mTitle);
                } else if (mediaItem.mTitle != null) {
                    i = 1;
                } else if (mediaItem2.mTitle == null) {
                    return 0;
                } else {
                    i = -1;
                }
                return this.mDescent ? -i : i;
            }
        }

        private static class TitleFilter extends Filter.CompareFilter<MediaItem> {
            private int mFilterType = -1;

            public TitleFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                if (this.mArgument.startsWith("PANO")) {
                    this.mFilterType = 1;
                } else if (this.mArgument.startsWith("Screenshot")) {
                    this.mFilterType = 2;
                }
            }

            private boolean isPanoItem(MediaItem mediaItem) {
                return isPanoItemSize(mediaItem) && isPanoItemNamed(mediaItem) && !isSecretAlbumItem(mediaItem);
            }

            private boolean isPanoItemNamed(MediaItem mediaItem) {
                return (mediaItem == null || mediaItem.mTitle == null || !mediaItem.mTitle.startsWith("PANO")) ? false : true;
            }

            private boolean isPanoItemSize(MediaItem mediaItem) {
                return (mediaItem == null || mediaItem.mWidth == null || mediaItem.mHeight == null || mediaItem.mWidth.intValue() <= 1080 || mediaItem.mHeight.intValue() == 0 || ((float) mediaItem.mWidth.intValue()) / ((float) mediaItem.mHeight.intValue()) <= 3.0f) ? false : true;
            }

            private boolean isScreenshotItem(MediaItem mediaItem) {
                return (mediaItem == null || mediaItem.mTitle == null || !mediaItem.mTitle.startsWith("Screenshot")) ? false : true;
            }

            private boolean isSecretAlbumItem(MediaItem mediaItem) {
                return (mediaItem == null || mediaItem.mAlbumId == null || mediaItem.mAlbumId.longValue() != -1000) ? false : true;
            }

            public MediaItem filter(MediaItem mediaItem) {
                int i;
                if (this.mComparator == Filter.Comparator.LIKE && (i = this.mFilterType) > 0) {
                    if (i != 1) {
                        if (i == 2) {
                            if (isScreenshotItem(mediaItem)) {
                                return mediaItem;
                            }
                            return null;
                        }
                    } else if (isPanoItem(mediaItem)) {
                        return mediaItem;
                    } else {
                        return null;
                    }
                }
                return null;
            }
        }

        private static class TypeFilter extends Filter.CompareFilter<MediaItem> {
            private int mType;

            public TypeFilter(Filter.Comparator comparator, String str) {
                super(-1, comparator, str);
                this.mType = Integer.parseInt(str);
            }

            public MediaItem filter(MediaItem mediaItem) {
                if (this.mComparator != Filter.Comparator.EQUALS || !Numbers.equals(mediaItem.mType, this.mType)) {
                    return null;
                }
                return mediaItem;
            }
        }

        public Comparator<MediaItem> getComparator(int i, boolean z) {
            if (i == 18) {
                return new TimeComparator(z);
            }
            if (i == 7) {
                return new TitleComparator(z);
            }
            if (i == 10) {
                return new SizeComparator(z);
            }
            if (i == 30) {
                return new DateModifiedComparator(z);
            }
            if (i == 36) {
                return new SortTimeComparator(z);
            }
            return null;
        }

        public Filter.CompareFilter<MediaItem> getFilter(int i, Filter.Comparator comparator, String str) {
            return i == 24 ? SHOW_IN_HOMEPAGE_FILTER : i == 0 ? new IdFilter(comparator, str) : i == 2 ? new AlbumFilter(comparator, str) : i == 6 ? new TypeFilter(comparator, str) : i == 1 ? new Sha1Filter(comparator, str) : i == 20 ? new SyncStateFilter(comparator, str) : i == 7 ? new TitleFilter(comparator, str) : i == 5 ? new LocalFileFilter(comparator, str) : i == 4 ? new ThumbnailFilter(comparator, str) : i == 26 ? new LocalFlagFilter(comparator, str) : i == 27 ? NOT_HIDDEN_FILTER : i == 28 ? new ServerTagFilter(comparator, str) : i == 29 ? new ServerIdFilter(comparator, str) : i == 19 ? new AliasCreateDateFilter(comparator, str) : i == 33 ? IS_FAVORITE_FILTER : i == 11 ? new MimeTypeFilter(comparator, str) : i == 34 ? new AlbumServerIdFilter(comparator, str) : i == 12 ? new LocationFilter(comparator, str) : i == 10 ? new SizeFilter(comparator, str) : i == 32 ? new AliasClearThumbnailFilter(comparator, str) : Filter.NOT_SUPPORTED_FILTER;
        }

        public CacheItem.ColumnMapper getMapper() {
            return COLUMN_MAPPER;
        }

        public CacheItem.Merger<MediaItem> getMerger(int i) {
            if (i == 1) {
                return SHA1_MERGER;
            }
            return null;
        }
    }

    static {
        PROJECTION.put("_id", 0);
        PROJECTION.put("sha1", 1);
        PROJECTION.put("localGroupId", 2);
        PROJECTION.put("microthumbfile", 3);
        PROJECTION.put("thumbnailFile", 4);
        PROJECTION.put("localFile", 5);
        PROJECTION.put("serverType", 6);
        PROJECTION.put("title", 7);
        PROJECTION.put("duration", 8);
        PROJECTION.put("description", 9);
        PROJECTION.put("size", 10);
        PROJECTION.put("mimeType", 11);
        PROJECTION.put("location", 12);
        PROJECTION.put("exifGPSLatitude", 13);
        PROJECTION.put("exifGPSLatitudeRef", 14);
        PROJECTION.put("exifGPSLongitude", 15);
        PROJECTION.put("exifGPSLongitudeRef", 16);
        PROJECTION.put("alias_micro_thumbnail", 17);
        PROJECTION.put("alias_create_time", 18);
        PROJECTION.put("alias_create_date", 19);
        PROJECTION.put("alias_sync_state", 20);
        PROJECTION.put("secretKey", 21);
        PROJECTION.put("exifImageWidth", 22);
        PROJECTION.put("exifImageLength", 23);
        PROJECTION.put("alias_show_in_homepage", 24);
        PROJECTION.put("creatorId", 25);
        PROJECTION.put("localFlag", 26);
        PROJECTION.put("alias_hidden", 27);
        PROJECTION.put("serverTag", 28);
        PROJECTION.put("serverId", 29);
        PROJECTION.put("dateModified", 30);
        PROJECTION.put("alias_modify_date", 31);
        PROJECTION.put("alias_clear_thumbnail", 32);
        PROJECTION.put("alias_is_favorite", 33);
        PROJECTION.put("groupId", 34);
        PROJECTION.put("specialTypeFlags", 35);
        PROJECTION.put("alias_sort_time", 36);
        PROJECTION.put("alias_sort_date", 37);
        PROJECTION.put("exifOrientation", 38);
        PROJECTION.put("burst_group_id", 39);
    }

    MediaItem() {
    }

    /* access modifiers changed from: private */
    public static long generateBurstGroupId(String str) {
        int intValue;
        if (TextUtils.isEmpty(str) || !str.contains("_BURST")) {
            intValue = DEFAULT_INT.intValue();
        } else {
            String[] split = str.split("_");
            if (split == null || split.length < 4) {
                intValue = DEFAULT_INT.intValue();
            } else {
                try {
                    return Long.parseLong(split[1].concat(split[2]));
                } catch (NumberFormatException e) {
                    Log.e(".provider.cache.MediaItem", "generate burst group id error", (Object) e);
                    intValue = DEFAULT_INT.intValue();
                }
            }
        }
        return (long) intValue;
    }

    /* access modifiers changed from: private */
    public Long getAliasSyncState() {
        Long l = this.mLocalFlag;
        if (l == null) {
            Log.e(".provider.cache.MediaItem", "localFlag is null!");
            return 2147483647L;
        } else if (l.longValue() == 0) {
            return 0L;
        } else {
            if (this.mLocalFlag.longValue() == 5 || this.mLocalFlag.longValue() == 6 || this.mLocalFlag.longValue() == 9) {
                return 1L;
            }
            Long l2 = this.mAlbumId;
            if (l2 == null || !this.mDelegate.isAutoUpload(l2.longValue())) {
                return 4L;
            }
            if (this.mIsSyncing == null) {
                UploadStatusItem uploadStatus = SyncProxy.getInstance().getUploadStatusProxy().getUploadStatus(new UploadStatusItem(ItemType.OWNER, String.valueOf(this.mId)));
                this.mIsSyncing = Boolean.valueOf(uploadStatus != null && uploadStatus.mStatus == SyncStatus.STATUS_INIT);
            }
            return this.mIsSyncing.booleanValue() ? 2L : 3L;
        }
    }

    /* access modifiers changed from: private */
    public void regenerateSortTimeAndDate() {
        Long l = this.mAlbumId;
        if (l == null) {
            this.mAliasSortTime = this.mMixedTime;
            this.mAliasSortDate = this.mAliasCreateDate;
            return;
        }
        MediaSortDateHelper.SortDate sortDate = this.mDelegate.getSortDate(l.longValue());
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$provider$MediaSortDateHelper$SortDate[sortDate.ordinal()];
        if (i == 1) {
            this.mAliasSortTime = this.mMixedTime;
            this.mAliasSortDate = this.mAliasCreateDate;
        } else if (i == 2) {
            this.mAliasSortTime = this.mDateModified;
            this.mAliasSortDate = this.mAliasModifyDate;
        } else {
            throw new IllegalArgumentException("Unsupported sort date " + sortDate);
        }
    }

    public boolean columnEquals(CacheItem cacheItem, int i) {
        return CacheUtils.columnEquals(this, cacheItem, i);
    }

    public boolean fillWindow(CursorWindow cursorWindow, int i, int i2, int i3) {
        String str = null;
        switch (i3) {
            case 0:
                return ParseUtils.putLong(cursorWindow, Long.valueOf(this.mId), i, i2);
            case 1:
                return ParseUtils.putString(cursorWindow, this.mSha1, i, i2);
            case 2:
                return ParseUtils.putLong(cursorWindow, this.mAlbumId, i, i2);
            case 3:
                return ParseUtils.putString(cursorWindow, this.mMicroThumb, i, i2);
            case 4:
                return ParseUtils.putString(cursorWindow, this.mThumbnail, i, i2);
            case 5:
                return ParseUtils.putString(cursorWindow, this.mFilePath, i, i2);
            case 6:
                return ParseUtils.putLong(cursorWindow, this.mType, i, i2);
            case 7:
                return ParseUtils.putString(cursorWindow, this.mTitle, i, i2);
            case 8:
                return ParseUtils.putLong(cursorWindow, this.mDuration, i, i2);
            case 9:
                return ParseUtils.putString(cursorWindow, this.mDescription, i, i2);
            case 10:
                return cursorWindow.putLong(this.mSize.longValue(), i, i2);
            case 11:
                return ParseUtils.putString(cursorWindow, this.mMimeType, i, i2);
            case 12:
                return ParseUtils.putString(cursorWindow, this.mLocation, i, i2);
            case 13:
                return ParseUtils.putString(cursorWindow, this.mLatitude, i, i2);
            case 14:
                Character ch = this.mLatitudeRef;
                if (ch != null) {
                    str = String.valueOf(ch);
                }
                return ParseUtils.putString(cursorWindow, str, i, i2);
            case 15:
                return ParseUtils.putString(cursorWindow, this.mLongitude, i, i2);
            case 16:
                Character ch2 = this.mLongitudeRef;
                if (ch2 != null) {
                    str = String.valueOf(ch2);
                }
                return ParseUtils.putString(cursorWindow, str, i, i2);
            case 17:
                return ParseUtils.putString(cursorWindow, getAliasMicroThumbnail(), i, i2);
            case 18:
                return cursorWindow.putLong(this.mMixedTime, i, i2);
            case 19:
                return cursorWindow.putLong((long) this.mAliasCreateDate, i, i2);
            case 20:
                return ParseUtils.putLong(cursorWindow, getAliasSyncState(), i, i2);
            case 21:
                return ParseUtils.putBlob(cursorWindow, this.mSecretKey, i, i2);
            case 22:
                return ParseUtils.putLong(cursorWindow, this.mWidth, i, i2);
            case 23:
                return ParseUtils.putLong(cursorWindow, this.mHeight, i, i2);
            case 24:
                return ParseUtils.putLong(cursorWindow, getAliasShowInHomePage(), i, i2);
            case 25:
                return ParseUtils.putString(cursorWindow, this.mCreatorId, i, i2);
            case 26:
                return ParseUtils.putLong(cursorWindow, this.mLocalFlag, i, i2);
            case 27:
                return ParseUtils.putLong(cursorWindow, getAliasHidden(), i, i2);
            case 28:
                return ParseUtils.putLong(cursorWindow, this.mServerTag, i, i2);
            case 29:
                return ParseUtils.putString(cursorWindow, this.mServerId, i, i2);
            case 30:
                return ParseUtils.putLong(cursorWindow, Long.valueOf(this.mDateModified), i, i2);
            case 31:
                return ParseUtils.putLong(cursorWindow, Integer.valueOf(this.mAliasModifyDate), i, i2);
            case 32:
                return ParseUtils.putString(cursorWindow, getAliasClearThumbnail(), i, i2);
            case 33:
                return ParseUtils.putLong(cursorWindow, getAliasIsFavorite(), i, i2);
            case 34:
                return ParseUtils.putString(cursorWindow, this.mAlbumServerId, i, i2);
            case 35:
                return ParseUtils.putLong(cursorWindow, this.mSpecialTypeFlags, i, i2);
            case 36:
                return ParseUtils.putLong(cursorWindow, Long.valueOf(this.mAliasSortTime), i, i2);
            case 37:
                return ParseUtils.putLong(cursorWindow, Integer.valueOf(this.mAliasSortDate), i, i2);
            case 38:
                return ParseUtils.putLong(cursorWindow, this.mOrientation, i, i2);
            case 39:
                return ParseUtils.putLong(cursorWindow, Long.valueOf(this.mBurstGroupId), i, i2);
            default:
                throw new IllegalArgumentException(" not recognized column.");
        }
    }

    public Object get(int i, boolean z) {
        switch (i) {
            case 0:
                return Long.valueOf(this.mId);
            case 1:
                String str = this.mSha1;
                if (str != null) {
                    return str;
                }
                if (z) {
                    return "";
                }
                return null;
            case 2:
                Long l = this.mAlbumId;
                if (l != null) {
                    return l;
                }
                if (z) {
                    return DEFAULT_LONG;
                }
                return null;
            case 3:
                String str2 = this.mMicroThumb;
                if (str2 != null) {
                    return str2;
                }
                if (z) {
                    return "";
                }
                return null;
            case 4:
                String str3 = this.mThumbnail;
                if (str3 != null) {
                    return str3;
                }
                if (z) {
                    return "";
                }
                return null;
            case 5:
                String str4 = this.mFilePath;
                if (str4 != null) {
                    return str4;
                }
                if (z) {
                    return "";
                }
                return null;
            case 6:
                Integer num = this.mType;
                if (num != null) {
                    return num;
                }
                if (z) {
                    return DEFAULT_INT;
                }
                return null;
            case 7:
                String str5 = this.mTitle;
                if (str5 != null) {
                    return str5;
                }
                if (z) {
                    return "";
                }
                return null;
            case 8:
                Long l2 = this.mDuration;
                if (l2 != null) {
                    return l2;
                }
                if (z) {
                    return DEFAULT_LONG;
                }
                return null;
            case 9:
                String str6 = this.mDescription;
                if (str6 != null) {
                    return str6;
                }
                if (z) {
                    return "";
                }
                return null;
            case 10:
                return this.mSize;
            case 11:
                String str7 = this.mMimeType;
                if (str7 != null) {
                    return str7;
                }
                if (z) {
                    return "";
                }
                return null;
            case 12:
                String str8 = this.mLocation;
                if (str8 != null) {
                    return str8;
                }
                if (z) {
                    return "";
                }
                return null;
            case 13:
                String str9 = this.mLatitude;
                if (str9 != null) {
                    return str9;
                }
                if (z) {
                    return DEFAULT_LONG;
                }
                return null;
            case 14:
                Character ch = this.mLatitudeRef;
                return ch != null ? ch : z ? 32 : null;
            case 15:
                String str10 = this.mLongitude;
                if (str10 != null) {
                    return str10;
                }
                if (z) {
                    return DEFAULT_LONG;
                }
                return null;
            case 16:
                Character ch2 = this.mLongitudeRef;
                return ch2 != null ? ch2 : z ? 32 : null;
            case 17:
                String aliasMicroThumbnail = getAliasMicroThumbnail();
                if (aliasMicroThumbnail != null) {
                    return aliasMicroThumbnail;
                }
                if (z) {
                    return DEFAULT_LONG;
                }
                return null;
            case 18:
                return Long.valueOf(this.mMixedTime);
            case 19:
                return Integer.valueOf(this.mAliasCreateDate);
            case 20:
                return getAliasSyncState();
            case 21:
                byte[] bArr = this.mSecretKey;
                if (bArr != null) {
                    return bArr;
                }
                if (z) {
                    return new byte[0];
                }
                return null;
            case 22:
                Integer num2 = this.mWidth;
                if (num2 != null) {
                    return num2;
                }
                if (z) {
                    return DEFAULT_INT;
                }
                return null;
            case 23:
                Integer num3 = this.mHeight;
                if (num3 != null) {
                    return num3;
                }
                if (z) {
                    return DEFAULT_INT;
                }
                return null;
            case 24:
                return TRUE;
            case 25:
                return this.mCreatorId;
            case 26:
                return this.mLocalFlag;
            case 27:
                return getAliasHidden();
            case 28:
                return this.mServerTag;
            case 29:
                return this.mServerId;
            case 30:
                return Long.valueOf(this.mDateModified);
            case 31:
                return Integer.valueOf(this.mAliasModifyDate);
            case 32:
                String aliasClearThumbnail = getAliasClearThumbnail();
                if (aliasClearThumbnail != null) {
                    return aliasClearThumbnail;
                }
                if (z) {
                    return "";
                }
                return null;
            case 33:
                return getAliasIsFavorite();
            case 34:
                String str11 = this.mAlbumServerId;
                if (str11 != null) {
                    return str11;
                }
                if (z) {
                    return "";
                }
                return null;
            case 35:
                return this.mSpecialTypeFlags;
            case 36:
                return Long.valueOf(this.mAliasSortTime);
            case 37:
                return Integer.valueOf(this.mAliasSortDate);
            case 38:
                Integer num4 = this.mOrientation;
                if (num4 != null) {
                    return num4;
                }
                if (z) {
                    return DEFAULT_INT;
                }
                return null;
            case 39:
                return Long.valueOf(this.mBurstGroupId);
            default:
                throw new IllegalArgumentException(" not recognized column. ");
        }
    }

    public String getAliasClearThumbnail() {
        if (!TextUtils.isEmpty(this.mFilePath)) {
            return this.mFilePath;
        }
        if (!TextUtils.isEmpty(this.mThumbnail)) {
            return this.mThumbnail;
        }
        if (!TextUtils.isEmpty(this.mMicroThumb)) {
            return this.mMicroThumb;
        }
        return null;
    }

    public Long getAliasHidden() {
        Long l = this.mAlbumId;
        return l == null ? FALSE : this.mDelegate.isHidden(l.longValue()) ? TRUE : FALSE;
    }

    public Long getAliasIsFavorite() {
        String str = this.mSha1;
        return str == null ? FALSE : this.mFavoritesDelegate.isFavorite(str) ? TRUE : FALSE;
    }

    public String getAliasMicroThumbnail() {
        if (!TextUtils.isEmpty(this.mMicroThumb)) {
            return this.mMicroThumb;
        }
        if (!TextUtils.isEmpty(this.mThumbnail)) {
            return this.mThumbnail;
        }
        if (!TextUtils.isEmpty(this.mFilePath)) {
            return this.mFilePath;
        }
        return null;
    }

    public Long getAliasShowInHomePage() {
        Long l = this.mAlbumId;
        return l == null ? FALSE : this.mDelegate.isShowInHomePage(l.longValue()) ? TRUE : FALSE;
    }

    public int getAliasSortDate() {
        return this.mAliasSortDate;
    }

    public long getAliasSortTime() {
        return this.mAliasSortTime;
    }

    public long getId() {
        return this.mId;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public int getType(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return this.mSha1 != null ? 3 : 0;
            case 2:
                return this.mAlbumId != null ? 1 : 0;
            case 3:
                return this.mMicroThumb != null ? 3 : 0;
            case 4:
                return this.mThumbnail != null ? 3 : 0;
            case 5:
                return this.mFilePath != null ? 3 : 0;
            case 6:
                return this.mType != null ? 1 : 0;
            case 7:
                return this.mTitle != null ? 3 : 0;
            case 8:
                return this.mDuration != null ? 1 : 0;
            case 9:
                return this.mDescription != null ? 3 : 0;
            case 10:
                return 1;
            case 11:
                return this.mMimeType != null ? 3 : 0;
            case 12:
                return this.mLocation != null ? 3 : 0;
            case 13:
                return this.mLatitude != null ? 3 : 0;
            case 14:
                return this.mLatitudeRef != null ? 3 : 0;
            case 15:
                return this.mLongitude != null ? 3 : 0;
            case 16:
                return this.mLongitudeRef != null ? 3 : 0;
            case 17:
                return getAliasMicroThumbnail() != null ? 3 : 0;
            case 18:
            case 19:
            case 20:
                return 1;
            case 21:
                return this.mSecretKey != null ? 4 : 0;
            case 22:
                return this.mWidth != null ? 1 : 0;
            case 23:
                return this.mHeight != null ? 1 : 0;
            case 24:
                return 1;
            case 25:
                return 3;
            case 26:
            case 27:
            case 28:
                return 1;
            case 29:
                return 3;
            case 30:
            case 31:
                return 1;
            case 32:
                return getAliasClearThumbnail() != null ? 3 : 0;
            case 33:
                return 1;
            case 34:
                return this.mAlbumServerId != null ? 3 : 0;
            case 35:
            case 36:
            case 37:
                return 1;
            case 38:
                return this.mOrientation != null ? 1 : 0;
            case 39:
                return 1;
            default:
                throw new IllegalArgumentException(" not recognized column. ");
        }
    }

    public String toString() {
        return "MediaItem{mId=" + this.mId + ", mSha1='" + this.mSha1 + '\'' + ", mAlbumId=" + this.mAlbumId + ", mMicroThumb='" + this.mMicroThumb + '\'' + ", mThumbnail='" + this.mThumbnail + '\'' + ", mFilePath='" + this.mFilePath + '\'' + ", mType=" + this.mType + ", mTitle='" + this.mTitle + '\'' + ", mMimeType='" + this.mMimeType + '\'' + ", mLocalFlag=" + this.mLocalFlag + ", mIsSyncing=" + this.mIsSyncing + ", mSecretKey=" + Arrays.toString(this.mSecretKey) + ", mMixedTime=" + this.mMixedTime + ", mAliasCreateDate=" + this.mAliasCreateDate + ", mDateModified=" + this.mDateModified + ", mAilasSortTime=" + this.mAliasSortTime + ", mAlbumServerId=" + this.mAlbumServerId + ", mSpecialTypeFlags=" + this.mSpecialTypeFlags + '}';
    }
}
