package com.miui.gallery.editor.photo.core.imports.mosaic;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.RenderEngine;
import com.miui.gallery.editor.photo.core.SdkManager;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractMosaicFragment;
import com.miui.gallery.editor.photo.core.common.model.MosaicData;
import com.miui.gallery.editor.photo.core.imports.mosaic.MosaicGLEntity;
import com.miui.gallery.util.GsonUtils;
import com.miui.gallery.util.IoUtils;
import com.miui.gallery.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class MosaicProvider extends SdkProvider<MosaicData, AbstractMosaicFragment> {
    /* access modifiers changed from: private */
    public static final String PATH_MOSAIC = ("mosaic" + SEPARATOR + "entities");
    public static final String PEN_MASK_PATH = ("mosaic" + SEPARATOR + "pen" + SEPARATOR + "pen_mask.png");
    private static final String SEPARATOR = File.separator;
    /* access modifiers changed from: private */
    public List<MosaicData> mMosaicDataList = new ArrayList();

    /* renamed from: com.miui.gallery.editor.photo.core.imports.mosaic.MosaicProvider$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE = new int[MosaicGLEntity.TYPE.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE[MosaicGLEntity.TYPE.ORIGIN.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE[MosaicGLEntity.TYPE.RESOURCE.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE[MosaicGLEntity.TYPE.EFFECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private static class LoadMosaicTask extends AsyncTask<Application, Void, List<MosaicGLEntity>> {
        private AssetManager mAssetManager;
        private ResourceListener mResourceListener;

        LoadMosaicTask(ResourceListener resourceListener, AssetManager assetManager) {
            this.mResourceListener = resourceListener;
            this.mAssetManager = assetManager;
        }

        /* access modifiers changed from: protected */
        public List<MosaicGLEntity> doInBackground(Application... applicationArr) {
            AssetManager assetManager = this.mAssetManager;
            ArrayList arrayList = new ArrayList();
            try {
                String[] list = assetManager.list(MosaicProvider.PATH_MOSAIC);
                for (int i = 0; i < list.length; i++) {
                    String str = list[i];
                    String access$300 = MosaicProvider.getMosaicConfigPath(str);
                    String access$400 = MosaicProvider.getMosaicIconPath(str);
                    MosaicConfig mosaicConfig = (MosaicConfig) GsonUtils.fromJson(MosaicProvider.loadResourceFileString(assetManager, access$300), MosaicConfig.class);
                    Object obj = null;
                    int i2 = AnonymousClass2.$SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE[mosaicConfig.type.ordinal()];
                    if (i2 == 1) {
                        obj = new MosaicGLOriginEntity((short) i, str, access$400);
                    } else if (i2 == 2) {
                        obj = new MosaicGLResourceEntity((short) i, str, access$400, MosaicProvider.getMosaicReourcePath(str), mosaicConfig.tileMode);
                    } else if (i2 == 3) {
                        obj = new MosaicGLEffectEntity((short) i, str, access$400, mosaicConfig.effectType);
                    }
                    arrayList.add(obj);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(List<MosaicGLEntity> list) {
            ResourceListener resourceListener = this.mResourceListener;
            if (resourceListener != null) {
                resourceListener.onLoadFinish(list);
            }
        }
    }

    private interface ResourceListener {
        void onLoadFinish(List<MosaicGLEntity> list);
    }

    static {
        SdkManager.INSTANCE.register(new MosaicProvider());
    }

    private MosaicProvider() {
        super(Effect.MOSAIC);
    }

    /* access modifiers changed from: private */
    public static String getMosaicConfigPath(String str) {
        return PATH_MOSAIC + SEPARATOR + str + SEPARATOR + "config.json";
    }

    /* access modifiers changed from: private */
    public static String getMosaicIconPath(String str) {
        return "assets://" + PATH_MOSAIC + SEPARATOR + str + SEPARATOR + "icon.png";
    }

    /* access modifiers changed from: private */
    public static String getMosaicReourcePath(String str) {
        return "assets://" + PATH_MOSAIC + SEPARATOR + str + SEPARATOR + "resource.png";
    }

    private void initialize() {
        new LoadMosaicTask(new ResourceListener() {
            public void onLoadFinish(List<MosaicGLEntity> list) {
                MosaicProvider.this.mMosaicDataList.clear();
                MosaicProvider.this.mMosaicDataList.addAll(list);
                MosaicProvider.this.notifyInitializeFinish();
            }
        }, getApplicationContext().getAssets()).execute(new Application[]{getApplicationContext()});
    }

    static String loadResourceFileString(AssetManager assetManager, String str) {
        InputStream inputStream;
        String str2 = null;
        try {
            inputStream = assetManager.open(str);
            try {
                str2 = IoUtils.readInputStreamToString("MosaicProvider", inputStream);
            } catch (IOException e) {
                e = e;
                try {
                    Log.e("MosaicProvider", (Throwable) e);
                    IoUtils.close(inputStream);
                    return str2;
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            Log.e("MosaicProvider", (Throwable) e);
            IoUtils.close(inputStream);
            return str2;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            IoUtils.close(inputStream);
            throw th;
        }
        IoUtils.close(inputStream);
        return str2;
    }

    public RenderEngine createEngine(Context context) {
        return new MosaicEngine();
    }

    public List<? extends MosaicData> list() {
        return this.mMosaicDataList;
    }

    /* access modifiers changed from: protected */
    public void onActivityCreate() {
        super.onActivityCreate();
        initialize();
    }

    /* access modifiers changed from: protected */
    public AbstractMosaicFragment onCreateFragment() {
        return new MosaicFragment();
    }
}
