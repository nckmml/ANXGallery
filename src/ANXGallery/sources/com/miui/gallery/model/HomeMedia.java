package com.miui.gallery.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.miui.gallery.dao.base.Entity;
import com.miui.gallery.dao.base.TableColumn;
import java.util.ArrayList;
import java.util.List;

public class HomeMedia extends Entity {
    private int isFavorite;
    private String mAliasClearPath;
    private long mAliasCreateDate;
    private long mAliasCreateTime;
    private String mAliasMicroPath;
    private long mAliasSortTime;
    private long mBurstGroup;
    private long mDuration;
    private String mLocation;
    private long mMediaId;
    private String mMimeType;
    private String mOriginPath;
    private String mSha1;
    private long mSize;
    private long mSpecialTypeFlags;
    private long mSyncState;
    private String mThumbPath;
    private String mTitle;
    private int mType;

    public interface Constants {
        public static final String[] PROJECTION = {"_id", "alias_micro_thumbnail", "alias_create_date", "alias_create_time", "location", "sha1", "serverType", "duration", "mimeType", "alias_sync_state", "thumbnailFile", "localFile", "alias_clear_thumbnail", "alias_is_favorite", "specialTypeFlags", "alias_sort_time", "size", "title", "burst_group_id"};
    }

    public static HomeMedia convertFromMediaCursor(Cursor cursor) {
        HomeMedia homeMedia = new HomeMedia();
        homeMedia.mMediaId = cursor.getLong(0);
        homeMedia.mAliasMicroPath = cursor.getString(1);
        homeMedia.mAliasCreateDate = cursor.getLong(2);
        homeMedia.mAliasCreateTime = cursor.getLong(3);
        homeMedia.mLocation = cursor.getString(4);
        homeMedia.mSha1 = cursor.getString(5);
        homeMedia.mType = cursor.getInt(6);
        homeMedia.mDuration = cursor.getLong(7);
        homeMedia.mMimeType = cursor.getString(8);
        homeMedia.mSyncState = (long) cursor.getInt(9);
        homeMedia.mThumbPath = cursor.getString(10);
        homeMedia.mOriginPath = cursor.getString(11);
        homeMedia.mAliasClearPath = cursor.getString(12);
        homeMedia.isFavorite = cursor.getInt(13);
        homeMedia.mSpecialTypeFlags = (long) cursor.getInt(14);
        homeMedia.mAliasSortTime = cursor.getLong(15);
        homeMedia.mSize = cursor.getLong(16);
        homeMedia.mTitle = cursor.getString(17);
        homeMedia.mBurstGroup = cursor.getLong(18);
        return homeMedia;
    }

    /* access modifiers changed from: protected */
    public List<TableColumn> getTableColumns() {
        ArrayList arrayList = new ArrayList();
        addColumn(arrayList, "media_id", "INTEGER");
        addColumn(arrayList, "alias_micro_thumbnail", "TEXT");
        addColumn(arrayList, "alias_create_date", "INTEGER");
        addColumn(arrayList, "alias_create_time", "INTEGER");
        addColumn(arrayList, "location", "TEXT");
        addColumn(arrayList, "sha1", "TEXT");
        addColumn(arrayList, "serverType", "INTEGER");
        addColumn(arrayList, "duration", "INTEGER");
        addColumn(arrayList, "mimeType", "TEXT");
        addColumn(arrayList, "alias_sync_state", "INTEGER");
        addColumn(arrayList, "thumbnailFile", "TEXT");
        addColumn(arrayList, "localFile", "TEXT");
        addColumn(arrayList, "alias_clear_thumbnail", "TEXT");
        addColumn(arrayList, "alias_is_favorite", "INTEGER");
        addColumn(arrayList, "specialTypeFlags", "INTEGER");
        addColumn(arrayList, "alias_sort_time", "INTEGER");
        addColumn(arrayList, "size", "INTEGER");
        addColumn(arrayList, "title", "TEXT");
        addColumn(arrayList, "burst_group_id", "INTEGER");
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onConvertToContents(ContentValues contentValues) {
        contentValues.put("media_id", Long.valueOf(this.mMediaId));
        contentValues.put("alias_micro_thumbnail", this.mAliasMicroPath);
        contentValues.put("alias_create_date", Long.valueOf(this.mAliasCreateDate));
        contentValues.put("alias_create_time", Long.valueOf(this.mAliasCreateTime));
        contentValues.put("location", this.mLocation);
        contentValues.put("sha1", this.mSha1);
        contentValues.put("serverType", Integer.valueOf(this.mType));
        contentValues.put("duration", Long.valueOf(this.mDuration));
        contentValues.put("mimeType", this.mMimeType);
        contentValues.put("alias_sync_state", Long.valueOf(this.mSyncState));
        contentValues.put("thumbnailFile", this.mThumbPath);
        contentValues.put("localFile", this.mOriginPath);
        contentValues.put("alias_clear_thumbnail", this.mAliasClearPath);
        contentValues.put("alias_is_favorite", Integer.valueOf(this.isFavorite));
        contentValues.put("specialTypeFlags", Long.valueOf(this.mSpecialTypeFlags));
        contentValues.put("alias_sort_time", Long.valueOf(this.mAliasSortTime));
        contentValues.put("size", Long.valueOf(this.mSize));
        contentValues.put("title", this.mTitle);
        contentValues.put("burst_group_id", Long.valueOf(this.mBurstGroup));
    }

    /* access modifiers changed from: protected */
    public void onInitFromCursor(Cursor cursor) {
        this.mMediaId = getLong(cursor, "media_id");
        this.mAliasMicroPath = getString(cursor, "alias_micro_thumbnail");
        this.mAliasCreateDate = getLong(cursor, "alias_create_date");
        this.mAliasCreateTime = getLong(cursor, "alias_create_time");
        this.mLocation = getString(cursor, "location");
        this.mSha1 = getString(cursor, "sha1");
        this.mType = getInt(cursor, "serverType");
        this.mDuration = getLong(cursor, "duration");
        this.mMimeType = getString(cursor, "mimeType");
        this.mSyncState = (long) getInt(cursor, "alias_sync_state");
        this.mThumbPath = getString(cursor, "thumbnailFile");
        this.mOriginPath = getString(cursor, "localFile");
        this.mAliasClearPath = getString(cursor, "alias_clear_thumbnail");
        this.isFavorite = getInt(cursor, "alias_is_favorite");
        this.mSpecialTypeFlags = (long) getInt(cursor, "specialTypeFlags");
        this.mAliasSortTime = getLong(cursor, "alias_sort_time");
        this.mSize = getLong(cursor, "size");
        this.mTitle = getString(cursor, "title");
        this.mBurstGroup = getLong(cursor, "burst_group_id");
    }

    public Object[] values() {
        return new Object[]{Long.valueOf(this.mMediaId), this.mAliasMicroPath, Long.valueOf(this.mAliasCreateDate), Long.valueOf(this.mAliasCreateTime), this.mLocation, this.mSha1, Integer.valueOf(this.mType), Long.valueOf(this.mDuration), this.mMimeType, Long.valueOf(this.mSyncState), this.mThumbPath, this.mOriginPath, this.mAliasClearPath, Integer.valueOf(this.isFavorite), Long.valueOf(this.mSpecialTypeFlags), Long.valueOf(this.mAliasSortTime), Long.valueOf(this.mSize), this.mTitle, Long.valueOf(this.mBurstGroup)};
    }
}
