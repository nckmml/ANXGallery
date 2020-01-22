package com.miui.gallery.model.repository.datesource.album.db;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.dao.AlbumTableServices;
import com.miui.gallery.loader.AlbumConvertCallback;
import com.miui.gallery.loader.CursorConvertCallback;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.AlbumConstants;
import com.miui.gallery.model.AlbumList;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import com.miui.gallery.share.ShareAlbumCacheManager;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.ui.album.NoRepeatContentObserver;
import com.miui.gallery.util.MediaAndAlbumOperations;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class AlbumDbDataSource implements IAlbumRepository {
    /* access modifiers changed from: private */
    public CursorConvertCallback<AlbumList> mAlbumCursorSafeConvert = new AlbumConvertCallback();

    /* access modifiers changed from: private */
    public void registerContentObserver(Uri uri, boolean z, ContentObserver contentObserver) {
        if (uri != null) {
            GalleryApp.sGetAndroidContext().getContentResolver().registerContentObserver(uri, z, contentObserver);
        }
    }

    /* access modifiers changed from: private */
    public void unRegisterContentObserver(ContentObserver contentObserver) {
        if (contentObserver != null) {
            GalleryApp.sGetAndroidContext().getContentResolver().unregisterContentObserver(contentObserver);
        }
    }

    public Flowable<Boolean> cancelAlbumHiddenStatus(long j) {
        return doChangeAlbumHiddenStatus(false, j);
    }

    public Flowable<Boolean> doChangeAlbumBackupStatus(final boolean z, final long j) {
        return Flowable.fromCallable(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return Boolean.valueOf(MediaAndAlbumOperations.doChangeAutoUpload(GalleryApp.sGetAndroidContext(), j, z, false));
            }
        });
    }

    public Flowable<Boolean> doChangeAlbumHiddenStatus(final boolean z, final long j) {
        return Flowable.fromCallable(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return Boolean.valueOf(AlbumTableServices.changeAlbumHiddenStatus(j, z, false));
            }
        });
    }

    public Flowable<List<AlbumConstants.ShareAlbum>> queryAlbumListShareInfo() {
        return Flowable.create(new FlowableOnSubscribe<List<AlbumConstants.ShareAlbum>>() {
            public void subscribe(FlowableEmitter<List<AlbumConstants.ShareAlbum>> flowableEmitter) throws Exception {
                Cursor queryShareAlbum = AlbumTableServices.queryShareAlbum();
                if (queryShareAlbum == null || !queryShareAlbum.moveToFirst()) {
                    flowableEmitter.onError(new IllegalStateException("can't find datas"));
                    return;
                }
                ShareAlbumCacheManager.getInstance().putSharedAlbums(queryShareAlbum);
                Collection<AlbumConstants.ShareAlbum> shareAlbumList = ShareAlbumCacheManager.getInstance().getShareAlbumList();
                ArrayList arrayList = new ArrayList(shareAlbumList.size());
                arrayList.addAll(shareAlbumList);
                flowableEmitter.onNext(arrayList);
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }

    public Flowable<List<Album>> queryAlbumsExcludeSystemAndRubbish() {
        return Flowable.create(new FlowableOnSubscribe<List<Album>>() {
            public void subscribe(final FlowableEmitter<List<Album>> flowableEmitter) throws Exception {
                Cursor queryAlbumsExcludeCamera = AlbumTableServices.queryAlbumsExcludeCamera();
                if (queryAlbumsExcludeCamera == null) {
                    flowableEmitter.onError(new IllegalStateException("can't find datas"));
                    return;
                }
                if (queryAlbumsExcludeCamera.moveToFirst()) {
                    flowableEmitter.onNext(AlbumDbDataSource.this.mAlbumCursorSafeConvert.convert(queryAlbumsExcludeCamera));
                } else {
                    flowableEmitter.onNext(Collections.EMPTY_LIST);
                }
                if (queryAlbumsExcludeCamera.getNotificationUri() != null) {
                    final AnonymousClass1 r1 = new NoRepeatContentObserver(ThreadManager.getWorkHandler()) {
                        public void onDataChange(boolean z, Uri uri) {
                            Cursor queryAlbumsExcludeCamera = AlbumTableServices.queryAlbumsExcludeCamera();
                            if (queryAlbumsExcludeCamera != null && queryAlbumsExcludeCamera.moveToFirst()) {
                                flowableEmitter.onNext(AlbumDbDataSource.this.mAlbumCursorSafeConvert.convert(queryAlbumsExcludeCamera));
                            }
                        }
                    };
                    AlbumDbDataSource.this.registerContentObserver(queryAlbumsExcludeCamera.getNotificationUri(), true, r1);
                    flowableEmitter.setCancellable(new Cancellable() {
                        public void cancel() throws Exception {
                            AlbumDbDataSource.this.unRegisterContentObserver(r1);
                        }
                    });
                }
            }
        }, BackpressureStrategy.BUFFER).map(new Function<List<Album>, List<Album>>() {
            public List<Album> apply(List<Album> list) throws Exception {
                if (list.isEmpty()) {
                    return list;
                }
                ArrayList arrayList = new ArrayList(list.size());
                for (Album next : list) {
                    if (!next.isRubbishAlbum()) {
                        arrayList.add(next);
                    }
                }
                return arrayList;
            }
        });
    }

    public Flowable<List<Album>> queryHiddenAlbum() {
        return Flowable.create(new FlowableOnSubscribe<List<Album>>() {
            public void subscribe(final FlowableEmitter<List<Album>> flowableEmitter) throws Exception {
                Cursor queryHiddenAlbum = AlbumTableServices.queryHiddenAlbum();
                if (queryHiddenAlbum == null) {
                    flowableEmitter.onError(new IllegalStateException("can't find datas"));
                    return;
                }
                if (queryHiddenAlbum.moveToFirst()) {
                    flowableEmitter.onNext(AlbumDbDataSource.this.mAlbumCursorSafeConvert.convert(queryHiddenAlbum));
                } else {
                    flowableEmitter.onNext(Collections.EMPTY_LIST);
                }
                if (queryHiddenAlbum.getNotificationUri() != null) {
                    final AnonymousClass1 r1 = new NoRepeatContentObserver(ThreadManager.getWorkHandler()) {
                        public void onDataChange(boolean z, Uri uri) {
                            Cursor queryHiddenAlbum = AlbumTableServices.queryHiddenAlbum();
                            if (queryHiddenAlbum != null && queryHiddenAlbum.moveToFirst()) {
                                flowableEmitter.onNext(AlbumDbDataSource.this.mAlbumCursorSafeConvert.convert(queryHiddenAlbum));
                            }
                        }
                    };
                    AlbumDbDataSource.this.registerContentObserver(queryHiddenAlbum.getNotificationUri(), true, r1);
                    flowableEmitter.setCancellable(new Cancellable() {
                        public void cancel() throws Exception {
                            AlbumDbDataSource.this.unRegisterContentObserver(r1);
                        }
                    });
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
