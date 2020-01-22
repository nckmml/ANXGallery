package com.miui.gallery.util.baby;

import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.model.PeopleContactInfo;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.face.PeopleCursorHelper;

public class BabyFaceFinder implements FutureListener<Boolean> {
    private static final Long TIME_INTERVAL_FOR_CHECK_CANDIDATE_PEOPLE = 604800000L;
    private Boolean mFoundBabyAlbums;
    private Future mFuture;
    private BabyAlbumsFoundListener mListener;

    public interface BabyAlbumsFoundListener {
        void onBabyAlbumsFound(Boolean bool);
    }

    /* access modifiers changed from: private */
    public boolean accept(String str, int i) {
        return PeopleContactInfo.isBabyRelation(i) && !TextUtils.isEmpty(str);
    }

    public synchronized void onFutureDone(Future<Boolean> future) {
        if (!future.isCancelled()) {
            this.mFoundBabyAlbums = future.get();
            if (this.mListener != null) {
                this.mListener.onBabyAlbumsFound(this.mFoundBabyAlbums);
            }
        }
        if (future == this.mFuture) {
            this.mFuture = null;
        }
    }

    public synchronized void setBabyAlbumsFoundListener(BabyAlbumsFoundListener babyAlbumsFoundListener) {
        this.mListener = babyAlbumsFoundListener;
    }

    public synchronized void startFindFace(String str) {
        if (this.mFuture != null) {
            this.mFuture.cancel();
        }
        if (System.currentTimeMillis() - GalleryPreferences.Baby.getLastClickPeopleRecommandationTime(str) >= TIME_INTERVAL_FOR_CHECK_CANDIDATE_PEOPLE.longValue()) {
            this.mFuture = ThreadManager.getMiscPool().submit(new ThreadPool.Job<Boolean>() {
                /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
                    if (r8 != null) goto L_0x0043;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
                    if (r8 == null) goto L_0x0046;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
                    r8.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:21:0x004b, code lost:
                    return false;
                 */
                public Boolean run(ThreadPool.JobContext jobContext) {
                    Cursor cursor = null;
                    try {
                        cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.PeopleFace.PERSONS_URI, PeopleCursorHelper.PROJECTION, (String) null, (String[]) null, (String) null);
                        while (cursor != null && cursor.moveToNext()) {
                            if (BabyFaceFinder.this.accept(PeopleCursorHelper.getPeopleName(cursor), PeopleCursorHelper.getRelationType(cursor))) {
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return true;
                            }
                        }
                    } catch (Exception unused) {
                    } catch (Throwable th) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }, this);
        }
    }
}
