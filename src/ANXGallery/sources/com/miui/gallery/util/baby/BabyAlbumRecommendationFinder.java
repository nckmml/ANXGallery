package com.miui.gallery.util.baby;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.SparseArray;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import java.util.ArrayList;

public class BabyAlbumRecommendationFinder {
    /* access modifiers changed from: private */
    public volatile Future<RecommendationDatas> mFuture;
    /* access modifiers changed from: private */
    public String mPeopleId;
    /* access modifiers changed from: private */
    public volatile RecommendationDatas mRecommandationDatas;
    /* access modifiers changed from: private */
    public volatile RecommendationFoundListener mRecommandationFoundListener;

    public static final class RecommendationDatas {
        public ArrayList<Long> ids;
        public String peopleLocalId;
        public String peopleServerId;
        public int totalFaceCountInFaceAlbum;

        public int getRecommendationSize() {
            ArrayList<Long> arrayList = this.ids;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        public boolean hasNewRecommendation() {
            ArrayList<Long> arrayList = this.ids;
            return arrayList != null && arrayList.size() > 0;
        }
    }

    public interface RecommendationFoundListener {
        void onRecommendationFound(RecommendationDatas recommendationDatas);
    }

    public BabyAlbumRecommendationFinder(String str) {
        this.mPeopleId = str;
    }

    public void findRecommendation(final SparseArray<Boolean> sparseArray, final String str) {
        if (this.mFuture == null) {
            this.mFuture = ThreadManager.getMiscPool().submit(new ThreadPool.Job<RecommendationDatas>() {
                /* JADX WARNING: Code restructure failed: missing block: B:16:0x008e, code lost:
                    if (r0 != null) goto L_0x00a0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:21:0x009e, code lost:
                    if (r0 == null) goto L_0x00a9;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a0, code lost:
                    r11.totalFaceCountInFaceAlbum = r0.getCount();
                    r0.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a9, code lost:
                    return r11;
                 */
                public RecommendationDatas run(ThreadPool.JobContext jobContext) {
                    RecommendationDatas recommendationDatas = new RecommendationDatas();
                    recommendationDatas.peopleServerId = BabyAlbumRecommendationFinder.this.mPeopleId;
                    recommendationDatas.peopleLocalId = String.valueOf(FaceManager.getPeopleLocalIdByServerId(BabyAlbumRecommendationFinder.this.mPeopleId));
                    Cursor cursor = null;
                    try {
                        cursor = GalleryApp.sGetAndroidContext().getContentResolver().query(GalleryContract.PeopleFace.ONE_PERSON_URI, new String[]{"_id", "title", "mixedDateTime"}, (String) null, new String[]{recommendationDatas.peopleServerId, recommendationDatas.peopleLocalId}, (String) null);
                        while (cursor != null && cursor.moveToNext()) {
                            if (!TextUtils.isEmpty(cursor.getString(1)) && sparseArray.get(cursor.getString(1).hashCode()) == null && cursor.getLong(2) > GalleryPreferences.Baby.getLastClickBabyPhotosRecommandationTime(str)) {
                                if (recommendationDatas.ids == null) {
                                    recommendationDatas.ids = new ArrayList<>();
                                }
                                recommendationDatas.ids.add(Long.valueOf(cursor.getLong(0)));
                            }
                        }
                    } catch (Exception unused) {
                    } catch (Throwable th) {
                        if (cursor != null) {
                            recommendationDatas.totalFaceCountInFaceAlbum = cursor.getCount();
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }, new FutureListener<RecommendationDatas>() {
                public void onFutureDone(Future<RecommendationDatas> future) {
                    if (!future.isCancelled()) {
                        RecommendationDatas unused = BabyAlbumRecommendationFinder.this.mRecommandationDatas = future.get();
                        RecommendationFoundListener access$200 = BabyAlbumRecommendationFinder.this.mRecommandationFoundListener;
                        if (access$200 != null) {
                            access$200.onRecommendationFound(BabyAlbumRecommendationFinder.this.mRecommandationDatas);
                        }
                    }
                    if (future == BabyAlbumRecommendationFinder.this.mFuture) {
                        Future unused2 = BabyAlbumRecommendationFinder.this.mFuture = null;
                    }
                }
            });
        }
    }

    public void setRecommendationFoundListener(RecommendationFoundListener recommendationFoundListener) {
        this.mRecommandationFoundListener = recommendationFoundListener;
    }
}
