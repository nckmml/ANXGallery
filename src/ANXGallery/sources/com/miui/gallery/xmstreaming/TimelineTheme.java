package com.miui.gallery.xmstreaming;

import java.util.List;

public class TimelineTheme {
    public List<ImageTrans> points;
    public String themeName;
    public int transType;
    public int version;

    public class ImageTrans {
        public int cutPoint;
        public String effects;
        public String para;
        public int transLen;
        public String transName;

        public ImageTrans() {
        }
    }
}
