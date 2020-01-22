package com.miui.gallery.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.baby.BabyInfo;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.provider.deprecated.ThumbnailInfo;
import com.miui.gallery.share.AlbumShareOperations;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.share.Path;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.BabyAlbumDetailFaceHeaderItem;
import com.miui.gallery.ui.BabyAlbumDetailGridHeaderItem;
import com.miui.gallery.util.CropUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.baby.FindFace2CreateBabyAlbum;

public class BabyAlbumDetailTimeLineAdapter extends AlbumDetailTimeLineAdapter {
    /* access modifiers changed from: private */
    public FaceHeaderHelper mFaceHeaderHelper = new FaceHeaderHelper();

    class FaceHeaderHelper {
        /* access modifiers changed from: private */
        public String TAG = "FaceHeaderHelper";
        /* access modifiers changed from: private */
        public long mBabyAlbumLocalId;
        /* access modifiers changed from: private */
        public String mBabyAlbumPeopleServerId;
        /* access modifiers changed from: private */
        public BabyInfo mBabyInfo;
        private int mBirthdayDay;
        private int mBirthdayMonth;
        private int mBirthdayYear;
        BabyAlbumDetailFaceHeaderItem mFaceHeaderItem;
        /* access modifiers changed from: private */
        public String mSharerInfoStr;
        /* access modifiers changed from: private */
        public ThumbnailInfo mThumbnailInfo;

        FaceHeaderHelper() {
        }

        /* access modifiers changed from: private */
        public void bindBackgroundByCursor(Cursor cursor) {
            if (cursor != null && !cursor.isAfterLast()) {
                String string = cursor.getString(1);
                String string2 = cursor.getString(2);
                String string3 = cursor.getString(14);
                if (!TextUtils.isEmpty(string2)) {
                    string = string2;
                } else if (!TextUtils.isEmpty(string3)) {
                    string = string3;
                }
                setBackgroundByPathOrUri(string, BaseAdapter.getDownloadUri(cursor, 12, 0));
            }
        }

        /* access modifiers changed from: private */
        public boolean bindBackgroundByThumbnail() {
            ThumbnailInfo thumbnailInfo = this.mThumbnailInfo;
            if (thumbnailInfo == null) {
                return false;
            }
            String bgPath = thumbnailInfo.getBgPath();
            if (TextUtils.isEmpty(bgPath)) {
                return false;
            }
            setBackgroundByPathOrUri(bgPath, (Uri) null);
            return true;
        }

        /* access modifiers changed from: private */
        public void bindBackgroundPicByPath(String str) {
            setBackgroundByPathOrUri(str, (Uri) null);
        }

        /* access modifiers changed from: private */
        public void bindFaceImageFromPath(String str, ThumbnailInfo thumbnailInfo) {
            if (thumbnailInfo.getFaceRegion() != null) {
                this.mFaceHeaderItem.bindHeadFacePic(str, thumbnailInfo.getFaceRegion());
            } else {
                this.mFaceHeaderItem.bindHeadFacePicFromNet(str, thumbnailInfo.getFaceRegionFromFaceInfo());
            }
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
            r11 = com.miui.gallery.cloud.baby.BabyInfo.getAge(r11, r10.mBirthdayYear, r10.mBirthdayMonth, r2);
         */
        public String getAge(long j) {
            int[] age;
            int i;
            StringBuilder sb;
            String str;
            String country = GalleryApp.sGetAndroidContext().getResources().getConfiguration().locale.getCountry();
            if ((!"cn".equalsIgnoreCase(country) && !"en".equalsIgnoreCase(country) && !"us".equalsIgnoreCase(country)) || (r2 = this.mBirthdayDay) == 0 || (i = age[0]) < 0) {
                return "";
            }
            int i2 = age[1];
            int i3 = age[2];
            if (!"cn".equalsIgnoreCase(country)) {
                Resources resources = GalleryApp.sGetAndroidContext().getResources();
                String quantityString = resources.getQuantityString(R.plurals.age_year, i, new Object[]{Integer.valueOf(i)});
                String quantityString2 = resources.getQuantityString(R.plurals.age_day, i3, new Object[]{Integer.valueOf(i3)});
                String quantityString3 = resources.getQuantityString(R.plurals.age_month, i2, new Object[]{Integer.valueOf(i2)});
                if (i != 0 && i2 != 0 && i3 != 0) {
                    return quantityString + " " + quantityString3 + " " + quantityString2;
                } else if (i != 0 && i2 != 0 && i3 == 0) {
                    return quantityString + " " + quantityString3;
                } else if (i != 0 && i2 == 0 && i3 == 0) {
                    return quantityString;
                } else {
                    if (i == 0 && i2 == 0 && i3 != 0) {
                        if (i3 == 0) {
                            sb = new StringBuilder();
                            sb.append(i3 + 1);
                            str = " day";
                        } else {
                            sb = new StringBuilder();
                            sb.append(i3 + 1);
                            str = " days";
                        }
                        sb.append(str);
                        return sb.toString();
                    } else if (i == 0 && i2 != 0 && i3 == 0) {
                        return quantityString3;
                    } else {
                        if (i != 0 && i2 == 0 && i3 != 0) {
                            return quantityString + " " + quantityString2;
                        } else if (i == 0 && i2 != 0 && i3 != 0) {
                            return quantityString3 + " " + quantityString2;
                        } else if (i == 0 && i2 == 0 && i3 == 0) {
                            return " first day";
                        } else {
                            return null;
                        }
                    }
                }
            } else if (i != 0 && i2 != 0 && i3 != 0) {
                return i + "岁" + i2 + "个月" + i3 + "天";
            } else if (i != 0 && i2 != 0 && i3 == 0) {
                return i + "岁" + i2 + "个月";
            } else if (i != 0 && i2 == 0 && i3 == 0) {
                return i + "岁生日";
            } else if (i == 0 && i2 == 0 && i3 != 0) {
                return "第" + (i3 + 1) + "天";
            } else if (i == 0 && i2 != 0 && i3 == 0) {
                return i2 + "个月";
            } else if (i != 0 && i2 == 0 && i3 != 0) {
                return i + "岁零" + i3 + "天";
            } else if (i == 0 && i2 != 0 && i3 != 0) {
                return i2 + "个月" + i3 + "天";
            } else if (i == 0 && i2 == 0 && i3 == 0) {
                return "出生第一天";
            } else {
                return null;
            }
        }

        /* access modifiers changed from: private */
        public void gotoBabyInfoSettingPage(boolean z) {
            FindFace2CreateBabyAlbum.gotoBabyAlbumInfoPage((Activity) BabyAlbumDetailTimeLineAdapter.this.mContext, this.mThumbnailInfo, this.mBabyInfo, this.mBabyAlbumLocalId, CropUtil.circleBitmap(getFaceImage()), z);
        }

        private void setBackgroundByPathOrUri(String str, Uri uri) {
            BabyAlbumDetailTimeLineAdapter.this.mFaceHeaderHelper.mFaceHeaderItem.bindHeaderBackgroundPic(str, uri, BabyAlbumDetailTimeLineAdapter.this.mDisplayImageOptionBuilder.loadFromMicroCache(false).build());
        }

        /* access modifiers changed from: private */
        public void setBirthday() {
            String age = getAge(System.currentTimeMillis());
            if (!TextUtils.isEmpty(age)) {
                this.mFaceHeaderItem.setAge(age);
            }
        }

        /* access modifiers changed from: private */
        public void setBirthdayYearMonthDay() {
            int[] splitBirthDay = BabyInfo.splitBirthDay(this.mBabyInfo);
            if (splitBirthDay != null) {
                this.mBirthdayYear = splitBirthDay[0];
                this.mBirthdayMonth = splitBirthDay[1];
                this.mBirthdayDay = splitBirthDay[2];
            }
        }

        /* access modifiers changed from: private */
        public void setFaceImageFromByFirstGetSharerInfo() {
            AlbumShareUIManager.updateInvitationAsync(new Path(this.mBabyAlbumLocalId, true, true), new AlbumShareUIManager.OnCompletionListener<Path, String>() {
                public void onCompletion(Path path, String str, int i, boolean z) {
                    if (z) {
                        Log.i(FaceHeaderHelper.this.TAG, "updateInvitationAsync cancelled");
                    } else if (i == 0) {
                        ThreadManager.getMiscPool().submit(new ThreadPool.Job<String>() {
                            public String run(ThreadPool.JobContext jobContext) {
                                return FaceManager.querySharerInfoOfBabyAlbum(String.valueOf(FaceHeaderHelper.this.mBabyAlbumLocalId));
                            }
                        }, new FutureListener<String>() {
                            public void onFutureDone(Future<String> future) {
                                final String str = future.get();
                                if (!TextUtils.isEmpty(str)) {
                                    ThreadManager.getMainHandler().post(new Runnable() {
                                        public void run() {
                                            FaceHeaderHelper.this.setFaceImageFromSharerInfo(str);
                                        }
                                    });
                                }
                            }
                        });
                    } else {
                        String access$1600 = FaceHeaderHelper.this.TAG;
                        Log.i(access$1600, "updateInvitationAsync error, errorCode=" + i);
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        public void setFaceImageFromFaceAlbumCover(String str) {
            RectF[] rectFArr = new RectF[1];
            this.mFaceHeaderItem.bindHeadFacePic(FaceManager.queryCoverImageOfOnePerson(str, rectFArr), rectFArr[0]);
        }

        /* access modifiers changed from: private */
        public void setFaceImageFromSharerInfo(String str) {
            if (!TextUtils.isEmpty(str)) {
                AlbumShareOperations.SharerInfo parseSharerInfo = AlbumShareOperations.parseSharerInfo(str);
                if (parseSharerInfo == null || TextUtils.isEmpty(parseSharerInfo.mThumbnailUrl)) {
                    String str2 = this.TAG;
                    Log.e(str2, "illegal SharerInfo: " + str);
                } else if (!TextUtils.isEmpty(parseSharerInfo.mThumbnailUrl)) {
                    this.mFaceHeaderItem.bindHeadFacePicFromNet(parseSharerInfo.mThumbnailUrl, parseSharerInfo.mFaceRelativePos);
                }
            }
        }

        /* access modifiers changed from: protected */
        public Bitmap getFaceImage() {
            return this.mFaceHeaderItem.getHeadFacePic();
        }

        /* access modifiers changed from: protected */
        public void setFaceImage() {
            final ThumbnailInfo thumbnailInfo = this.mThumbnailInfo;
            if (!BabyAlbumDetailTimeLineAdapter.this.isOtherShareAlbum()) {
                if (thumbnailInfo != null) {
                    ThreadManager.getMiscPool().submit(new ThreadPool.Job<String>() {
                        public String run(ThreadPool.JobContext jobContext) {
                            return thumbnailInfo.getFaceInfo(BabyAlbumDetailTimeLineAdapter.this.isOtherShareAlbum());
                        }
                    }, new FutureListener<String>() {
                        public void onFutureDone(Future<String> future) {
                            final String str = future.get();
                            ThreadManager.getMainHandler().post(new Runnable() {
                                public void run() {
                                    if (TextUtils.isEmpty(str)) {
                                        FaceHeaderHelper.this.setFaceImageFromFaceAlbumCover(FaceHeaderHelper.this.mBabyAlbumPeopleServerId);
                                    } else {
                                        FaceHeaderHelper.this.bindFaceImageFromPath(str, thumbnailInfo);
                                    }
                                }
                            });
                        }
                    });
                } else {
                    setFaceImageFromFaceAlbumCover(this.mBabyAlbumPeopleServerId);
                }
            } else if (!TextUtils.isEmpty(this.mBabyAlbumPeopleServerId)) {
                setFaceImageFromFaceAlbumCover(this.mBabyAlbumPeopleServerId);
            } else {
                ThreadManager.getMiscPool().submit(new ThreadPool.Job<String>() {
                    public String run(ThreadPool.JobContext jobContext) {
                        return thumbnailInfo.getFaceInfo(BabyAlbumDetailTimeLineAdapter.this.isOtherShareAlbum());
                    }
                }, new FutureListener<String>() {
                    public void onFutureDone(Future<String> future) {
                        final String str = future.get();
                        ThreadManager.getMainHandler().post(new Runnable() {
                            public void run() {
                                if (!TextUtils.isEmpty(str)) {
                                    FaceHeaderHelper.this.bindFaceImageFromPath(str, thumbnailInfo);
                                } else if (!TextUtils.isEmpty(FaceHeaderHelper.this.mSharerInfoStr)) {
                                    FaceHeaderHelper.this.setFaceImageFromSharerInfo(FaceHeaderHelper.this.mSharerInfoStr);
                                } else {
                                    FaceHeaderHelper.this.setFaceImageFromByFirstGetSharerInfo();
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    public BabyAlbumDetailTimeLineAdapter(Context context) {
        super(context);
    }

    private void setBabyInfoAndThumbnailInfo(BabyInfo babyInfo, ThumbnailInfo thumbnailInfo, String str) {
        if (babyInfo != null) {
            BabyInfo unused = this.mFaceHeaderHelper.mBabyInfo = babyInfo;
        }
        if (thumbnailInfo != null) {
            ThumbnailInfo unused2 = this.mFaceHeaderHelper.mThumbnailInfo = thumbnailInfo;
        }
        if (!TextUtils.isEmpty(str)) {
            String unused3 = this.mFaceHeaderHelper.mSharerInfoStr = str;
        }
        this.mFaceHeaderHelper.setBirthdayYearMonthDay();
    }

    public void firstBindHeaderPic(Cursor cursor) {
        if (!this.mFaceHeaderHelper.bindBackgroundByThumbnail()) {
            this.mFaceHeaderHelper.bindBackgroundByCursor(cursor);
        }
    }

    public Bitmap getFaceImageOfFaceHeaderItem() {
        int dimension = (int) this.mContext.getResources().getDimension(R.dimen.baby_timeline_quick_start_icon_size);
        return CropUtil.cropImage(this.mFaceHeaderHelper.getFaceImage(), dimension, dimension, false);
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.baby_album_detail_grid_header_item, viewGroup, false);
        }
        String str = null;
        if (!(this.mGroupStartLocations == null || this.mGroupStartPos == null)) {
            str = (String) this.mGroupStartLocations.get(i);
            i = ((Integer) this.mGroupStartPos.get(i)).intValue();
        }
        long j = ((Cursor) getItem(i)).getLong(5);
        ((BabyAlbumDetailGridHeaderItem) view).bindData(j, str, this.mFaceHeaderHelper.getAge(j));
        return view;
    }

    public void gotoBabyInfoSettingPage(boolean z) {
        this.mFaceHeaderHelper.gotoBabyInfoSettingPage(z);
    }

    public void rebindHeaderPic(String str) {
        this.mFaceHeaderHelper.bindBackgroundPicByPath(str);
    }

    public void resetBabyInfoAndThumbnailInfo(BabyInfo babyInfo, ThumbnailInfo thumbnailInfo) {
        setBabyInfoAndThumbnailInfo(babyInfo, thumbnailInfo, (String) null);
        this.mFaceHeaderHelper.setFaceImage();
        this.mFaceHeaderHelper.setBirthday();
        notifyDataSetChanged();
    }

    public void setAlbumId(long j) {
        long unused = this.mFaceHeaderHelper.mBabyAlbumLocalId = j;
    }

    public void setFaceHeader(BabyInfo babyInfo, ThumbnailInfo thumbnailInfo, String str, BabyAlbumDetailFaceHeaderItem babyAlbumDetailFaceHeaderItem, View view, View.OnClickListener onClickListener, final boolean z) {
        setBabyInfoAndThumbnailInfo(babyInfo, thumbnailInfo, str);
        FaceHeaderHelper faceHeaderHelper = this.mFaceHeaderHelper;
        faceHeaderHelper.mFaceHeaderItem = babyAlbumDetailFaceHeaderItem;
        faceHeaderHelper.setFaceImage();
        this.mFaceHeaderHelper.setBirthday();
        this.mFaceHeaderHelper.mFaceHeaderItem.setOnFaceClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BabyAlbumDetailTimeLineAdapter.this.gotoBabyInfoSettingPage(z);
                GallerySamplingStatHelper.recordCountEvent("baby", "baby_edit_baby_info");
            }
        });
        this.mFaceHeaderHelper.mFaceHeaderItem.setOnBackgroundClickListener(onClickListener);
    }

    public void setPeopleServerId(String str) {
        String unused = this.mFaceHeaderHelper.mBabyAlbumPeopleServerId = str;
    }
}
