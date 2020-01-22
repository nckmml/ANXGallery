package com.miui.gallery.video.editor.factory;

import android.content.Context;
import com.miui.gallery.video.editor.config.VideoEditorConfig;
import com.miui.gallery.video.editor.model.LocalResource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioFactory extends VideoEditorModuleFactory {
    public List<LocalResource> getLocalTemplateEntities(Context context) {
        return new ArrayList();
    }

    public String getTemplateDir(long j) {
        return VideoEditorConfig.AUDIO_PATH + File.separator + j;
    }
}
