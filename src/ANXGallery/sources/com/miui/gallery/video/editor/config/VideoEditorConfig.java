package com.miui.gallery.video.editor.config;

import android.content.Context;
import java.io.File;

public class VideoEditorConfig {
    public static String ASSET_INSTALL_ROOT_PATH;
    public static String ASSET_STORE_PATH;
    public static String AUDIO_PATH;
    public static String SMART_EFFECT_PATH;
    public static String WATER_MARK_PATH;

    public static void init(Context context) {
        File externalFilesDir = context.getExternalFilesDir((String) null);
        if (externalFilesDir == null) {
            externalFilesDir = context.getFilesDir();
        }
        AUDIO_PATH = (externalFilesDir + File.separator + "video_editor") + File.separator + "video_editor_audio_dir";
        String str = context.getFilesDir() + File.separator + "video_editor_template";
        ASSET_STORE_PATH = str + File.separator + "video_editor_asset_store" + File.separator + "asset_store";
        ASSET_INSTALL_ROOT_PATH = str + File.separator + "video_editor_asset_store" + File.separator + "assets";
        String str2 = ASSET_STORE_PATH;
        SMART_EFFECT_PATH = str2;
        WATER_MARK_PATH = str2;
    }
}
