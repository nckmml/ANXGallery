package com.miui.gallery.search.core.source.local;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.Pair;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.SearchContract;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.suggestion.BaseSuggestion;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.miui.gallery.util.AlbumsCursorHelper;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.ArrayList;
import java.util.List;

public class AlbumSource extends LocalCacheBackedSuggestionSource<List<Pair<String, Suggestion>>> {
    private static final Uri CONTENT_URI = GalleryContract.Album.URI_ALL_EXCEPT_DELETED;
    private final String[] PROJECTION = {"_id", "serverId", "name", "cover_id", "cover_path"};
    private final String[] SHARE_PROJECTION = {"_id", "creatorId"};

    public AlbumSource(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    public Suggestion createAlbumSuggestion(String str, Cursor cursor, LongSparseArray<String> longSparseArray) {
        long j = cursor.getLong(0);
        if (j == 2147483646) {
            return null;
        }
        BaseSuggestion baseSuggestion = new BaseSuggestion();
        baseSuggestion.setSuggestionSource(this);
        baseSuggestion.setSuggestionTitle(str);
        Uri.Builder buildUpon = GalleryContract.Common.URI_ALBUM_PAGE.buildUpon();
        String string = cursor.getString(1);
        if (!TextUtils.isEmpty(string)) {
            buildUpon.appendQueryParameter("serverId", string);
            if (!TextUtils.isEmpty(longSparseArray.get(j))) {
                buildUpon.appendQueryParameter("creatorId", longSparseArray.get(j));
            }
        } else {
            buildUpon.appendQueryParameter("id", String.valueOf(j));
        }
        baseSuggestion.setIntentActionURI(buildUpon.build().toString());
        String string2 = cursor.getString(4);
        if (!TextUtils.isEmpty(string2)) {
            baseSuggestion.setSuggestionIcon(ImageDownloader.Scheme.FILE.wrap(string2));
        } else {
            long j2 = cursor.getLong(3);
            if (j2 >= 0) {
                baseSuggestion.setSuggestionIcon(SearchContract.Icon.LOCAL_IMAGE_URI.buildUpon().appendQueryParameter("id", String.valueOf(j2)).build().toString());
            }
        }
        return baseSuggestion;
    }

    /* access modifiers changed from: private */
    public LongSparseArray<String> getShareAlbumInfo() {
        return (LongSparseArray) SafeDBUtil.safeQuery(this.mContext, GalleryContract.Album.URI_SHARE_ALL, this.SHARE_PROJECTION, (String) null, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<LongSparseArray<String>>() {
            public LongSparseArray<String> handle(Cursor cursor) {
                if (cursor == null || !cursor.moveToFirst()) {
                    return null;
                }
                LongSparseArray<String> longSparseArray = new LongSparseArray<>();
                do {
                    if (cursor.getLong(0) >= 2147383647) {
                        longSparseArray.append(cursor.getLong(0), cursor.getString(1));
                    }
                } while (cursor.moveToNext());
                return longSparseArray;
            }
        });
    }

    public Uri getContentUri() {
        return CONTENT_URI;
    }

    public String getName() {
        return "local_album_source";
    }

    /* access modifiers changed from: protected */
    public SearchConstants.SectionType getSectionType() {
        return SearchConstants.SectionType.SECTION_TYPE_ALBUM;
    }

    /* access modifiers changed from: protected */
    public List<Suggestion> handleQuery(List<Pair<String, Suggestion>> list, String str, QueryInfo queryInfo) {
        if (!MiscUtil.isValid(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Pair next : list) {
            if (((String) next.first).contains(str)) {
                arrayList.add(next.second);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public boolean isFatalCondition(QueryInfo queryInfo, int i) {
        return super.isFatalCondition(queryInfo, i) || i == 14;
    }

    public List<Pair<String, Suggestion>> loadContent() {
        return (List) SafeDBUtil.safeQuery(this.mContext, CONTENT_URI, this.PROJECTION, (String) null, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<List<Pair<String, Suggestion>>>() {
            public List<Pair<String, Suggestion>> handle(Cursor cursor) {
                Suggestion access$200;
                if (cursor == null || !cursor.moveToFirst()) {
                    return null;
                }
                LongSparseArray access$000 = AlbumSource.this.getShareAlbumInfo();
                ArrayList arrayList = new ArrayList(cursor.getCount());
                do {
                    String albumName = AlbumsCursorHelper.getAlbumName(AlbumSource.this.mContext, cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                    if (!TextUtils.isEmpty(albumName) && (access$200 = AlbumSource.this.createAlbumSuggestion(albumName, cursor, access$000)) != null) {
                        arrayList.add(new Pair(albumName.toLowerCase(), access$200));
                    }
                } while (cursor.moveToNext());
                return arrayList;
            }
        });
    }
}
