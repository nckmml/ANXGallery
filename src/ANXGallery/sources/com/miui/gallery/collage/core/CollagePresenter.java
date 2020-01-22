package com.miui.gallery.collage.core;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.format.DateFormat;
import com.miui.gallery.collage.BitmapManager;
import com.miui.gallery.collage.CollageActivity;
import com.miui.gallery.collage.app.common.CollageMenuFragment;
import com.miui.gallery.collage.app.common.CollageRenderFragment;
import com.miui.gallery.collage.app.common.IDataLoader;
import com.miui.gallery.scanner.MediaScanner;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IoUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaStoreUtils;
import com.miui.gallery.util.StorageUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public abstract class CollagePresenter {
    private IDataLoader mDataLoader;
    protected int mImageCount = -1;
    private CollageMenuFragment mMenuFragment;
    private CollageRenderFragment mRenderFragment;
    private CollageActivity.ReplaceImageListener mReplaceImageListener = new CollageActivity.ReplaceImageListener() {
        public void onReplace(Bitmap bitmap) {
            if (CollagePresenter.this.mViewInterface != null) {
                CollagePresenter.this.mViewInterface.onReplaceBitmap(bitmap);
            }
        }
    };
    private SaveTask mSaveTask;
    protected ViewInterface mViewInterface;

    public interface DataLoadListener {
        void onDataLoad();
    }

    private interface SaveListener {
        void onSaveFinish(String str, boolean z);
    }

    private static class SaveTask extends AsyncTask<Void, Void, Void> {
        private final Context mContext;
        private final String mOutPath;
        private final RenderData mRenderData;
        private final RenderEngine mRenderEngine;
        /* access modifiers changed from: private */
        public SaveListener mSaveListener;
        private boolean mSuccess;

        private SaveTask(RenderEngine renderEngine, RenderData renderData, String str, Context context) {
            this.mSuccess = false;
            this.mRenderEngine = renderEngine;
            this.mRenderData = renderData;
            this.mOutPath = str;
            this.mContext = context.getApplicationContext();
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            FileOutputStream fileOutputStream;
            this.mSuccess = false;
            Bitmap render = this.mRenderEngine.render(this.mRenderData);
            if (isCancelled()) {
                return null;
            }
            File file = new File(this.mOutPath);
            try {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }
                fileOutputStream = new FileOutputStream(file);
                try {
                    render.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    MediaScanner.scanSingleFile(this.mContext, file.getPath());
                    MediaStoreUtils.insert(this.mContext, file, 1);
                    this.mSuccess = true;
                } catch (IOException e) {
                    e = e;
                }
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = null;
                try {
                    Log.d("CollagePresenter", (Throwable) e);
                    IoUtils.close(fileOutputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    IoUtils.close(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                IoUtils.close(fileOutputStream);
                throw th;
            }
            IoUtils.close(fileOutputStream);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            SaveListener saveListener = this.mSaveListener;
            if (saveListener != null) {
                saveListener.onSaveFinish(this.mOutPath, this.mSuccess);
            }
        }
    }

    private static String getCreativePath() {
        return StorageUtils.getCreativeDirectory();
    }

    public void attach(ViewInterface viewInterface) {
        this.mViewInterface = viewInterface;
    }

    /* access modifiers changed from: protected */
    public abstract RenderEngine createEngine(Context context, BitmapManager bitmapManager);

    public final void detach() {
        this.mViewInterface = null;
        this.mMenuFragment = null;
        this.mRenderFragment = null;
        SaveTask saveTask = this.mSaveTask;
        if (saveTask != null) {
            SaveListener unused = saveTask.mSaveListener = null;
            this.mSaveTask.cancel(false);
        }
        IDataLoader iDataLoader = this.mDataLoader;
        if (iDataLoader != null) {
            iDataLoader.cancel();
        }
        onDetach();
    }

    public void dismissControlWindow() {
        CollageRenderFragment collageRenderFragment = this.mRenderFragment;
        if (collageRenderFragment != null) {
            collageRenderFragment.dismissControlWindow();
        }
    }

    public void doSave(BitmapManager bitmapManager) {
        CollageRenderFragment collageRenderFragment = this.mRenderFragment;
        if (collageRenderFragment != null && this.mViewInterface != null) {
            RenderData export = collageRenderFragment.export();
            GallerySamplingStatHelper.recordCountEvent("collage", "collage_save", this.mRenderFragment.onSimple());
            RenderEngine createEngine = createEngine(this.mViewInterface.getContext().getApplicationContext(), bitmapManager);
            if (export == null || createEngine == null) {
                this.mViewInterface.onSaveFinish((String) null, false);
                return;
            }
            this.mViewInterface.onSaving();
            CharSequence format = DateFormat.format("yyyyMMdd_HHmmss", System.currentTimeMillis());
            this.mSaveTask = new SaveTask(createEngine, export, new File(getCreativePath(), String.format(Locale.US, "IMG_%s.jpg", new Object[]{format})).getAbsolutePath(), this.mViewInterface.getContext().getApplicationContext());
            SaveListener unused = this.mSaveTask.mSaveListener = new SaveListener() {
                public void onSaveFinish(String str, boolean z) {
                    if (CollagePresenter.this.mViewInterface != null) {
                        CollagePresenter.this.mViewInterface.onSaveFinish(str, z);
                    }
                }
            };
            this.mSaveTask.execute(new Void[0]);
        }
    }

    public int getImageCount() {
        return this.mImageCount;
    }

    public CollageMenuFragment getMenuFragment() {
        if (this.mMenuFragment == null) {
            this.mMenuFragment = onCreateMenuFragment();
        }
        return this.mMenuFragment;
    }

    public abstract String getMenuFragmentTag();

    public Fragment getRenderFragment() {
        if (this.mRenderFragment == null) {
            this.mRenderFragment = onCreateRenderFragment();
        }
        return this.mRenderFragment;
    }

    public abstract String getRenderFragmentTag();

    public abstract int getTitle();

    /* access modifiers changed from: protected */
    public abstract boolean hasResourceData();

    public boolean isActivating() {
        CollageRenderFragment collageRenderFragment = this.mRenderFragment;
        return collageRenderFragment != null && collageRenderFragment.isActivating();
    }

    public final void loadDataFromResourceAsync(DataLoadListener dataLoadListener) {
        if (!hasResourceData()) {
            this.mDataLoader = onCreateDataLoader(dataLoadListener);
            this.mDataLoader.loadData();
        } else if (dataLoadListener != null) {
            dataLoadListener.onDataLoad();
        }
    }

    public void notifyBitmapReplace(Bitmap bitmap, Bitmap bitmap2) {
        CollageRenderFragment collageRenderFragment = this.mRenderFragment;
        if (collageRenderFragment != null) {
            collageRenderFragment.onBitmapReplace(bitmap, bitmap2);
        }
    }

    public void notifyReceiveBitmaps() {
        CollageRenderFragment collageRenderFragment = this.mRenderFragment;
        if (collageRenderFragment != null) {
            collageRenderFragment.setBitmap(this.mViewInterface.getBitmaps());
        }
    }

    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof CollageRenderFragment) {
            Bitmap[] bitmaps = this.mViewInterface.getBitmaps();
            if (bitmaps != null) {
                this.mRenderFragment.setBitmap(bitmaps);
            }
            this.mRenderFragment.setReplaceImageListener(this.mReplaceImageListener);
        } else if (fragment instanceof CollageMenuFragment) {
            this.mMenuFragment.setPresenter(this);
            CollageRenderFragment collageRenderFragment = this.mRenderFragment;
            if (collageRenderFragment != null) {
                this.mMenuFragment.setRenderFragment(collageRenderFragment);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract IDataLoader onCreateDataLoader(DataLoadListener dataLoadListener);

    /* access modifiers changed from: protected */
    public abstract CollageMenuFragment onCreateMenuFragment();

    /* access modifiers changed from: protected */
    public abstract CollageRenderFragment onCreateRenderFragment();

    /* access modifiers changed from: protected */
    public abstract void onDetach();

    public void setImageSize(int i) {
        this.mImageCount = i;
    }

    public boolean supportImageSize(int i) {
        return true;
    }
}
