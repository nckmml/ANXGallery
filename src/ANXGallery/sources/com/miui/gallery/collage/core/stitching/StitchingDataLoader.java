package com.miui.gallery.collage.core.stitching;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import com.google.gson.Gson;
import com.miui.gallery.collage.CollageUtils;
import com.miui.gallery.collage.app.common.IDataLoader;
import com.miui.gallery.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StitchingDataLoader implements IDataLoader {
    public static final String PATH_STITCHING = ("collage" + SEPARATOR + "stitching");
    private static final String SEPARATOR = File.separator;
    private AssetManager mAssetManager;
    private DataLoadListener mDataLoadListener;
    private AsyncTask mTask;

    interface DataLoadListener {
        void onDataLoad(List<StitchingModel> list);
    }

    private static class LoadResourceTask extends AsyncTask<AssetManager, Void, Void> {
        private DataLoadListener mDataLoadListener;
        private List<StitchingModel> mStitchingModelList;

        private LoadResourceTask(DataLoadListener dataLoadListener) {
            this.mDataLoadListener = dataLoadListener;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(AssetManager... assetManagerArr) {
            this.mStitchingModelList = new ArrayList();
            Gson generateCustomGson = CollageUtils.generateCustomGson();
            AssetManager assetManager = assetManagerArr[0];
            try {
                for (String str : assetManager.list(StitchingDataLoader.PATH_STITCHING)) {
                    if (isCancelled()) {
                        return null;
                    }
                    this.mStitchingModelList.add(StitchingDataLoader.generateStitchingModelByName(assetManager, generateCustomGson, str));
                }
            } catch (IOException e) {
                Log.d("StitchingDataLoader", (Throwable) e);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            DataLoadListener dataLoadListener = this.mDataLoadListener;
            if (dataLoadListener != null) {
                dataLoadListener.onDataLoad(this.mStitchingModelList);
            }
        }
    }

    public StitchingDataLoader(AssetManager assetManager, DataLoadListener dataLoadListener) {
        this.mAssetManager = assetManager;
        this.mDataLoadListener = dataLoadListener;
    }

    /* access modifiers changed from: private */
    public static StitchingModel generateStitchingModelByName(AssetManager assetManager, Gson gson, String str) {
        String str2 = PATH_STITCHING + SEPARATOR + str;
        StitchingModel stitchingModel = (StitchingModel) gson.fromJson(CollageUtils.loadResourceFileString(assetManager, str2 + SEPARATOR + "main.json"), StitchingModel.class);
        stitchingModel.relativePath = str2;
        stitchingModel.name = str;
        return stitchingModel;
    }

    public void cancel() {
        AsyncTask asyncTask = this.mTask;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
    }

    public void loadData() {
        this.mTask = new LoadResourceTask(this.mDataLoadListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AssetManager[]{this.mAssetManager});
    }
}
