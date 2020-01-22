package com.miui.gallery.editor.photo.screen.core;

import android.content.Context;
import com.miui.gallery.editor.photo.screen.mosaic.ScreenMosaicProvider;
import java.util.HashMap;
import java.util.Map;

public class ScreenProviderManager extends ScreenProvider {
    public static final ScreenProviderManager INSTANCE = new ScreenProviderManager();
    private Map<Class, ScreenProvider> mProviderMap = new HashMap();

    private ScreenProviderManager() {
        this.mProviderMap.put(ScreenMosaicProvider.class, new ScreenMosaicProvider());
    }

    public <T extends ScreenProvider> T getProvider(Class cls) {
        return (ScreenProvider) this.mProviderMap.get(cls);
    }

    public void onActivityCreate(Context context) {
        for (ScreenProvider next : this.mProviderMap.values()) {
            if (next != null) {
                next.onActivityCreate(context);
            }
        }
    }

    public void onActivityDestroy() {
        for (ScreenProvider next : this.mProviderMap.values()) {
            if (next != null) {
                next.onActivityDestroy();
            }
        }
    }
}
