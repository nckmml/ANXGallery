package com.miui.gallery.movie.ui.factory;

import com.miui.gallery.movie.MovieConfig;
import com.miui.gallery.movie.R;
import com.miui.gallery.movie.entity.AudioResource;
import java.io.File;
import java.util.ArrayList;

public class AudioFactory {
    private static final AudioResource LOCAL = new AudioResource(2, (String) null, R.drawable.movie_icon_autio_local, R.string.movie_audio_custom);
    private static final AudioResource NONE = new AudioResource(1, (String) null, R.drawable.movie_icon_audio_none, R.string.movie_audio_none);

    public static ArrayList<AudioResource> getAllLocalAudios() {
        ArrayList<AudioResource> arrayList = new ArrayList<>();
        arrayList.add(NONE);
        arrayList.add(LOCAL);
        return arrayList;
    }

    public static String getAudioDownloadPath(String str) {
        return MovieConfig.sAudioDir + File.separator + str;
    }
}
