package com.miui.gallery.util.imageloader.imageloadiotion;

import com.miui.gallery.Config;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class BaseImageLoadOption {
    private DisplayImageOptions.Builder mDefaultDisplayImageOptionBuilder;
    private DisplayImageOptions mDefaultDisplayImageOptions;

    public BaseImageLoadOption() {
        initDefalutOption();
    }

    public DisplayImageOptions.Builder cloneDefalutImageOptionsBuilder() {
        return new DisplayImageOptions.Builder().cloneFrom(this.mDefaultDisplayImageOptions);
    }

    public void initDefalutOption() {
        this.mDefaultDisplayImageOptionBuilder = new DisplayImageOptions.Builder().cloneFrom(Config.ThumbConfig.get().MICRO_THUMB_DISPLAY_IMAGE_OPTIONS_DEFAULT);
        this.mDefaultDisplayImageOptions = this.mDefaultDisplayImageOptionBuilder.build();
    }
}
