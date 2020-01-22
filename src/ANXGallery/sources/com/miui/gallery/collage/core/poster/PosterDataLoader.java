package com.miui.gallery.collage.core.poster;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.miui.gallery.collage.CollageUtils;
import com.miui.gallery.collage.app.common.IDataLoader;
import com.miui.gallery.collage.core.layout.LayoutModel;
import com.miui.gallery.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PosterDataLoader implements IDataLoader {
    private static final String PATH_POSTER = ("collage" + SEPARATOR + "poster");
    public static final String PATH_POSTER_LAYOUT = (PATH_POSTER + SEPARATOR + "layout");
    public static final String PATH_POSTER_MODE = (PATH_POSTER + SEPARATOR + "mode");
    private static final String SEPARATOR = File.separator;
    private AssetManager mAssetManager;
    private DataLoadListener mDataLoadListener;
    private AsyncTask mTask;

    interface DataLoadListener {
        void onDataLoad(SparseArray<List<LayoutModel>> sparseArray, SparseArray<List<PosterModel>> sparseArray2);
    }

    private static class LoadResourceTask extends AsyncTask<AssetManager, Void, Void> {
        private DataLoadListener mDataLoadListener;
        private SparseArray<List<LayoutModel>> mPosterLayoutSparseArray = new SparseArray<>();
        private SparseArray<List<PosterModel>> mPosterSparseArray = new SparseArray<>();

        public LoadResourceTask(DataLoadListener dataLoadListener) {
            this.mDataLoadListener = dataLoadListener;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(AssetManager... assetManagerArr) {
            Gson generateCustomGson = CollageUtils.generateCustomGson();
            AssetManager assetManager = assetManagerArr[0];
            try {
                JsonParser jsonParser = new JsonParser();
                for (String str : assetManager.list(PosterDataLoader.PATH_POSTER_LAYOUT)) {
                    if (isCancelled()) {
                        return null;
                    }
                    PosterDataLoader.addLayoutModel(this.mPosterLayoutSparseArray, PosterDataLoader.generatePosterLayoutModelByName(assetManager, generateCustomGson, str));
                }
                for (String str2 : assetManager.list(PosterDataLoader.PATH_POSTER_MODE)) {
                    if (isCancelled()) {
                        return null;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    PosterDataLoader.addPosterModel(this.mPosterSparseArray, PosterDataLoader.generatePosterModelByName(assetManager, generateCustomGson, jsonParser, str2));
                    Log.d("PosterDataLoader", "gson parse posterModel %s coast %d", str2, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            } catch (IOException e) {
                Log.d("PosterDataLoader", (Throwable) e);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            DataLoadListener dataLoadListener = this.mDataLoadListener;
            if (dataLoadListener != null) {
                dataLoadListener.onDataLoad(this.mPosterLayoutSparseArray, this.mPosterSparseArray);
            }
        }
    }

    public PosterDataLoader(AssetManager assetManager, DataLoadListener dataLoadListener) {
        this.mAssetManager = assetManager;
        this.mDataLoadListener = dataLoadListener;
    }

    /* access modifiers changed from: private */
    public static void addLayoutModel(SparseArray<List<LayoutModel>> sparseArray, LayoutModel layoutModel) {
        int i = layoutModel.size;
        List list = sparseArray.get(i);
        if (list == null) {
            list = new ArrayList();
            sparseArray.put(i, list);
        }
        list.add(layoutModel);
    }

    /* access modifiers changed from: private */
    public static void addPosterModel(SparseArray<List<PosterModel>> sparseArray, PosterModel posterModel) {
        for (int i : posterModel.collageModels) {
            int i2 = i / 10;
            List list = sparseArray.get(i2);
            if (list == null) {
                list = new ArrayList();
                sparseArray.put(i2, list);
            }
            list.add(posterModel);
        }
    }

    public static LayoutModel generatePosterLayoutModelByName(AssetManager assetManager, Gson gson, String str) {
        LayoutModel layoutModel = (LayoutModel) gson.fromJson(CollageUtils.loadResourceFileString(assetManager, PATH_POSTER_LAYOUT + SEPARATOR + str), LayoutModel.class);
        layoutModel.name = str;
        return layoutModel;
    }

    public static PosterModel generatePosterModelByName(AssetManager assetManager, Gson gson, JsonParser jsonParser, String str) {
        String str2 = PATH_POSTER_MODE + SEPARATOR + str;
        PosterModel posterModel = (PosterModel) gson.fromJson(CollageUtils.loadResourceFileString(assetManager, str2 + SEPARATOR + "main.json"), PosterModel.class);
        posterModel.relativePath = str2;
        posterModel.name = str;
        ImageElementModel[] imageElementModelArr = posterModel.imageElementModels;
        if (imageElementModelArr != null) {
            for (ImageElementModel imageElementModel : imageElementModelArr) {
                imageElementModel.relativePath = str2;
            }
        }
        CollagePositionModel[] collagePositionModelArr = posterModel.collagePositions;
        if (collagePositionModelArr != null) {
            for (CollagePositionModel collagePositionModel : collagePositionModelArr) {
                collagePositionModel.relativePath = str2;
            }
        }
        return posterModel;
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
