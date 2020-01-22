package com.miui.gallery.editor.photo.screen.mosaic;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import com.miui.gallery.editor.photo.screen.core.ScreenProvider;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicData;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntity;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntityBitmap;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntityBlur;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntityNormal;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntityOrigin;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntityTriangle;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntityTriangleRect;
import com.miui.gallery.util.GsonUtils;
import com.miui.gallery.util.IoUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScreenMosaicProvider extends ScreenProvider {
    /* access modifiers changed from: private */
    public static final String PATH_MOSAIC = ("mosaic" + SEPARATOR + "entities");
    private static final String SEPARATOR = File.separator;
    private List<MosaicData> mMosaicDataList = new ArrayList();

    /* renamed from: com.miui.gallery.editor.photo.screen.mosaic.ScreenMosaicProvider$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$editor$photo$screen$mosaic$shader$MosaicEntity$TYPE = new int[MosaicEntity.TYPE.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            $SwitchMap$com$miui$gallery$editor$photo$screen$mosaic$shader$MosaicEntity$TYPE[MosaicEntity.TYPE.ORIGIN.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$editor$photo$screen$mosaic$shader$MosaicEntity$TYPE[MosaicEntity.TYPE.BITMAP.ordinal()] = 2;
            $SwitchMap$com$miui$gallery$editor$photo$screen$mosaic$shader$MosaicEntity$TYPE[MosaicEntity.TYPE.BLUR.ordinal()] = 3;
            $SwitchMap$com$miui$gallery$editor$photo$screen$mosaic$shader$MosaicEntity$TYPE[MosaicEntity.TYPE.TRIANGLE.ordinal()] = 4;
            $SwitchMap$com$miui$gallery$editor$photo$screen$mosaic$shader$MosaicEntity$TYPE[MosaicEntity.TYPE.TRIANGLE_RECT.ordinal()] = 5;
            try {
                $SwitchMap$com$miui$gallery$editor$photo$screen$mosaic$shader$MosaicEntity$TYPE[MosaicEntity.TYPE.NORMAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private static class LoadMosaicTask extends AsyncTask<Void, Void, List<MosaicEntity>> {
        private Context mContext;
        private ResourceListener mResourceListener;

        LoadMosaicTask(ResourceListener resourceListener, Context context) {
            this.mResourceListener = resourceListener;
            this.mContext = context;
        }

        /* access modifiers changed from: protected */
        public List<MosaicEntity> doInBackground(Void... voidArr) {
            AssetManager assets = this.mContext.getAssets();
            ArrayList arrayList = new ArrayList();
            try {
                String[] list = assets.list(ScreenMosaicProvider.PATH_MOSAIC);
                for (String str : list) {
                    String access$100 = ScreenMosaicProvider.getMosaicConfigPath(str);
                    String access$200 = ScreenMosaicProvider.getMosaicIconPath(str);
                    ScreenMosaicConfig screenMosaicConfig = (ScreenMosaicConfig) GsonUtils.fromJson(ScreenMosaicProvider.loadResourceFileString(assets, access$100), ScreenMosaicConfig.class);
                    if (screenMosaicConfig.supportScreenEditor) {
                        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$editor$photo$screen$mosaic$shader$MosaicEntity$TYPE[screenMosaicConfig.effectType.ordinal()];
                        arrayList.add(i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? new MosaicEntityNormal(str, access$200) : new MosaicEntityTriangleRect(str, access$200) : new MosaicEntityTriangle(str, access$200) : new MosaicEntityBlur(str, access$200, this.mContext) : new MosaicEntityBitmap(str, access$200, ScreenMosaicProvider.getMosaicResourcePath(str), screenMosaicConfig.tileMode) : new MosaicEntityOrigin(str, access$200));
                        if (arrayList.size() >= 6) {
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(List<MosaicEntity> list) {
            ResourceListener resourceListener = this.mResourceListener;
            if (resourceListener != null) {
                resourceListener.onLoadFinish(list);
            }
        }
    }

    private interface ResourceListener {
        void onLoadFinish(List<MosaicEntity> list);
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
    public static String getMosaicResourcePath(String str) {
        return "assets://" + PATH_MOSAIC + SEPARATOR + str + SEPARATOR + "resource.png";
    }

    private void initialize(Context context) {
        if (!this.mIsInitialized) {
            new LoadMosaicTask(new ResourceListener() {
                public final void onLoadFinish(List list) {
                    ScreenMosaicProvider.this.lambda$initialize$93$ScreenMosaicProvider(list);
                }
            }, context.getApplicationContext()).execute(new Void[0]);
        }
    }

    static String loadResourceFileString(AssetManager assetManager, String str) {
        InputStream inputStream;
        String str2 = null;
        try {
            inputStream = assetManager.open(str);
            try {
                str2 = IoUtils.readInputStreamToString("ScreenMosaicProvider", inputStream);
            } catch (IOException e) {
                e = e;
                try {
                    Log.e("ScreenMosaicProvider", (Throwable) e);
                    IoUtils.close(inputStream);
                    return str2;
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            Log.e("ScreenMosaicProvider", (Throwable) e);
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

    public void clearShader() {
        Iterator<MosaicData> it = this.mMosaicDataList.iterator();
        while (it.hasNext()) {
            ((MosaicEntity) it.next()).clearShader();
        }
    }

    public MosaicData getDefaultData() {
        if (!MiscUtil.isValid(this.mMosaicDataList)) {
            return null;
        }
        for (MosaicData next : this.mMosaicDataList) {
            if (next instanceof MosaicEntityNormal) {
                return next;
            }
        }
        return null;
    }

    public /* synthetic */ void lambda$initialize$93$ScreenMosaicProvider(List list) {
        this.mMosaicDataList.clear();
        this.mMosaicDataList.addAll(list);
        this.mIsInitialized = true;
    }

    public List<? extends MosaicData> list() {
        return this.mMosaicDataList;
    }

    public void onActivityCreate(Context context) {
        initialize(context);
    }

    public void onActivityDestroy() {
    }
}
