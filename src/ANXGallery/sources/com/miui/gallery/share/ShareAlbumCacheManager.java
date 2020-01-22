package com.miui.gallery.share;

import android.database.Cursor;
import com.miui.gallery.model.AlbumConstants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ShareAlbumCacheManager {
    private Map<String, AlbumConstants.ShareAlbum> mSharedAlbums;
    private final Lock readLock;
    private final ReadWriteLock readWriteLock;
    private final Lock writeLock;

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static final ShareAlbumCacheManager INSTANCE = new ShareAlbumCacheManager();
    }

    private ShareAlbumCacheManager() {
        this.readWriteLock = new ReentrantReadWriteLock();
        this.readLock = this.readWriteLock.readLock();
        this.writeLock = this.readWriteLock.writeLock();
    }

    public static ShareAlbumCacheManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean containsKey(long j) {
        if (this.mSharedAlbums == null) {
            return false;
        }
        return getShareAlbumMaps().containsKey(String.valueOf(j));
    }

    public AlbumConstants.ShareAlbum getShareAlbum(long j) {
        if (this.mSharedAlbums == null) {
            return null;
        }
        return getShareAlbumMaps().get(String.valueOf(j));
    }

    public Collection<AlbumConstants.ShareAlbum> getShareAlbumList() {
        if (this.mSharedAlbums == null) {
            return null;
        }
        return Collections.unmodifiableCollection(getShareAlbumMaps().values());
    }

    public Map<String, AlbumConstants.ShareAlbum> getShareAlbumMaps() {
        try {
            this.readLock.lock();
            return Collections.unmodifiableMap(this.mSharedAlbums);
        } finally {
            this.readLock.unlock();
        }
    }

    public boolean putSharedAlbums(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return false;
        }
        try {
            this.writeLock.lockInterruptibly();
            if (this.mSharedAlbums != null) {
                this.mSharedAlbums.clear();
            }
            if (cursor.moveToFirst()) {
                do {
                    AlbumConstants.ShareAlbum shareAlbum = new AlbumConstants.ShareAlbum();
                    shareAlbum.mAlbumId = cursor.getString(0);
                    shareAlbum.mCreatorId = cursor.getString(1);
                    shareAlbum.mUserCount = cursor.getInt(2);
                    shareAlbum.mOwnerNickName = cursor.getString(3);
                    if (this.mSharedAlbums == null) {
                        this.mSharedAlbums = new HashMap(cursor.getCount());
                    }
                    this.mSharedAlbums.put(shareAlbum.mAlbumId, shareAlbum);
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } while (Thread.currentThread().isInterrupted());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.writeLock.unlock();
            throw th;
        }
        this.writeLock.unlock();
        return this.mSharedAlbums.size() > 0;
    }
}
