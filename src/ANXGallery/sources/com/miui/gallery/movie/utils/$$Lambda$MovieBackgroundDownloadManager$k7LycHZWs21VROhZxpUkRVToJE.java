package com.miui.gallery.movie.utils;

import com.miui.gallery.movie.ui.factory.MovieFactory;
import io.reactivex.functions.Function;

/* renamed from: com.miui.gallery.movie.utils.-$$Lambda$MovieBackgroundDownloadManager$k7LycHZWs-21VROhZxpUkRVToJE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MovieBackgroundDownloadManager$k7LycHZWs21VROhZxpUkRVToJE implements Function {
    public static final /* synthetic */ $$Lambda$MovieBackgroundDownloadManager$k7LycHZWs21VROhZxpUkRVToJE INSTANCE = new $$Lambda$MovieBackgroundDownloadManager$k7LycHZWs21VROhZxpUkRVToJE();

    private /* synthetic */ $$Lambda$MovieBackgroundDownloadManager$k7LycHZWs21VROhZxpUkRVToJE() {
    }

    public final Object apply(Object obj) {
        return MovieFactory.getParentTemplateName(MovieFactory.getTemplateNameById(((Integer) obj).intValue()));
    }
}
