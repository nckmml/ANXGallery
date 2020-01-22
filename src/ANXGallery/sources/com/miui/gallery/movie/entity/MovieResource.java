package com.miui.gallery.movie.entity;

import android.text.TextUtils;
import com.miui.gallery.movie.R;
import com.miui.gallery.net.resource.Resource;
import java.util.HashMap;

public abstract class MovieResource extends Resource implements Comparable<MovieResource> {
    private static HashMap<String, Integer> resourcesNames = new HashMap<String, Integer>() {
        {
            put("movieAudioDefault", Integer.valueOf(R.string.movie_audio_summertime));
            put("movieAudioSoft", Integer.valueOf(R.string.movie_audio_antiquity));
            put("movieAudioLight", Integer.valueOf(R.string.movie_audio_quiet));
            put("movieAudioLinear", Integer.valueOf(R.string.movie_audio_electronics));
            put("movieAudioSport", Integer.valueOf(R.string.movie_audio_vitality));
            put("movieAudioKawaii", Integer.valueOf(R.string.movie_audio_dream));
            put("movieAudioPoint1", Integer.valueOf(R.string.movie_audio_point1));
            put("movieAudioPoint2", Integer.valueOf(R.string.movie_audio_point2));
            put("movieAudioPoint3", Integer.valueOf(R.string.movie_audio_point3));
            put("movieAudioPoint4", Integer.valueOf(R.string.movie_audio_point4));
            put("movieAudioPoint5", Integer.valueOf(R.string.movie_audio_point5));
            put("movieAudioPoint6", Integer.valueOf(R.string.movie_audio_point6));
            put("movieTemplateTravel", Integer.valueOf(R.string.movie_template_travel));
            put("movieTemplateBaby", Integer.valueOf(R.string.movie_template_baby));
            put("movieTemplateParty", Integer.valueOf(R.string.movie_template_party));
            put("movieTemplateFood", Integer.valueOf(R.string.movie_template_food));
            put("movieTemplateXmas", Integer.valueOf(R.string.movie_template_xmas));
            put("movieTemplateNewYear", Integer.valueOf(R.string.movie_template_new_year));
            put("movieTemplateSelfie", Integer.valueOf(R.string.movie_template_selfie));
            put("movieTemplatePet", Integer.valueOf(R.string.movie_template_pet));
            put("movieTemplateDull", Integer.valueOf(R.string.movie_template_dull));
            put("movieTemplateDull3", Integer.valueOf(R.string.movie_template_dull3));
            put("movieTemplateDull2", Integer.valueOf(R.string.movie_template_dull2));
            put("movieTemplateCool3", Integer.valueOf(R.string.movie_template_cool3));
            put("movieTemplateCool", Integer.valueOf(R.string.movie_template_cool));
            put("movieTemplateCool2", Integer.valueOf(R.string.movie_template_cool2));
        }
    };
    public int downloadState = 19;
    public String enName;
    public int imageId;
    public int index;
    public boolean isPackageAssets;
    public String nameKey;
    public String pathKey;
    public String srcPath;
    public int stringId;
    public int version;

    public MovieResource() {
    }

    public MovieResource(String str, int i, int i2) {
        this.srcPath = str;
        this.imageId = i;
        this.stringId = i2;
        this.isPackageAssets = true;
    }

    public int compareTo(MovieResource movieResource) {
        return this.index - movieResource.index;
    }

    public String getDownloadFilePath() {
        return getDownloadSrcPath() + ".zip";
    }

    public abstract String getDownloadSrcPath();

    public int getDownloadState() {
        if (this.downloadState == 0 || !isDownloaded()) {
            return this.downloadState;
        }
        return 17;
    }

    public int getNameId() {
        if (this.stringId == 0 && !TextUtils.isEmpty(this.nameKey) && resourcesNames.containsKey(this.nameKey)) {
            this.stringId = resourcesNames.get(this.nameKey).intValue();
        }
        return this.stringId;
    }

    public String getSrcPath() {
        return this.isPackageAssets ? this.nameKey : getDownloadSrcPath();
    }

    public abstract String getStatNameString();

    public abstract String getStatTypeString();

    public boolean isDownloaded() {
        int i = this.downloadState;
        return i == 17 || i == 0;
    }
}
