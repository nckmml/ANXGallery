package com.miui.gallery.activity.facebaby;

import android.content.Context;
import android.graphics.Bitmap;
import android.preference.Preference;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.provider.BabyLockWallpaperDataManager;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.lang.ref.WeakReference;

/* compiled from: BabyLockWallpaperSettingActivity */
class BabyAlbumPreference extends Preference {
    public static DisplayImageOptions sDisplayImageOptions = new DisplayImageOptions.Builder().cacheThumbnail(true).loadFromMicroCache(true).considerExifParams(true).showStubImage(R.drawable.default_album_cover).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).build();
    private BabyLockWallpaperDataManager.BabyAlbumInfo mBabyAlbum;
    /* access modifiers changed from: private */
    public boolean mChecked;
    /* access modifiers changed from: private */
    public CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener;

    /* compiled from: BabyLockWallpaperSettingActivity */
    private static class CoverQueryHandler extends FutureHandler<String> {
        private WeakReference<View> mCoverViewRef;

        CoverQueryHandler(View view) {
            this.mCoverViewRef = new WeakReference<>(view);
        }

        public void onPostExecute(Future<String> future) {
            View view = (View) this.mCoverViewRef.get();
            if (view != null && view.getContext() != null && future != null && future.get() != null) {
                ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(future.get()), (ImageView) view.findViewById(R.id.cover), BabyAlbumPreference.sDisplayImageOptions, (ImageLoadingListener) null);
            }
        }
    }

    /* compiled from: BabyLockWallpaperSettingActivity */
    private static class CoverQueryJob implements ThreadPool.Job<String> {
        private long mAlbumLocalId;
        private boolean mIsOtherShare;

        CoverQueryJob(long j, boolean z) {
            this.mAlbumLocalId = j;
            this.mIsOtherShare = z;
        }

        public String run(ThreadPool.JobContext jobContext) {
            return FaceManager.queryBabyAlbumCover(String.valueOf(this.mAlbumLocalId), this.mIsOtherShare);
        }
    }

    public BabyAlbumPreference(Context context, BabyLockWallpaperDataManager.BabyAlbumInfo babyAlbumInfo, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, boolean z) {
        super(context);
        this.mBabyAlbum = babyAlbumInfo;
        this.mOnCheckedChangeListener = onCheckedChangeListener;
        this.mChecked = z;
        setLayoutResource(R.layout.baby_lock_wallpaper_item);
    }

    /* access modifiers changed from: protected */
    public void onBindView(View view) {
        super.onBindView(view);
        ((TextView) view.findViewById(R.id.baby_album_name)).setText(this.mBabyAlbum.name);
        final CheckBox checkBox = (CheckBox) view.findViewById(16908289);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BabyAlbumPreference.this.mOnCheckedChangeListener.onCheckedChanged(compoundButton, z);
                boolean unused = BabyAlbumPreference.this.mChecked = z;
            }
        });
        checkBox.setChecked(this.mChecked);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox.isEnabled()) {
                    checkBox.toggle();
                }
            }
        });
        ThreadManager.getMiscPool().submit(new CoverQueryJob(this.mBabyAlbum.localId, this.mBabyAlbum.isOtherShared), new CoverQueryHandler(view));
    }
}
