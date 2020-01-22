package com.miui.gallery.movie.net;

import com.miui.gallery.movie.MovieConfig;
import com.miui.gallery.movie.entity.MovieResource;
import com.miui.gallery.movie.entity.TemplateResource;
import com.miui.gallery.util.BaseBuildUtil;
import com.miui.settings.Settings;

public class TemplateResourceRequest extends LocalResourceRequest {
    /* access modifiers changed from: protected */
    public long getParentId() {
        if (MovieConfig.isUserXmSdk()) {
            return 12192914761187392L;
        }
        if (BaseBuildUtil.isInternational()) {
            return Settings.getRegion().endsWith("IN") ? 10702647724474528L : 10624721397481568L;
        }
        return 10146691454730304L;
    }

    /* access modifiers changed from: protected */
    public MovieResource newLocalResource() {
        return new TemplateResource();
    }
}
