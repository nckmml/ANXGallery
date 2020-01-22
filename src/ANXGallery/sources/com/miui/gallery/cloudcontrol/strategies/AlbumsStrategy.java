package com.miui.gallery.cloudcontrol.strategies;

import android.content.res.Resources;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AlbumsStrategy extends BaseStrategy {
    @SerializedName("patterns")
    private List<AlbumPattern> mAlbumPatterns;
    @SerializedName("albums")
    private List<Album> mAlbums;
    private transient HashMap<String, Album> mAlbumsMap;
    private transient ArrayList<String> mWhiteList;
    private transient ArrayList<Pattern> mWhiteListPatterns;

    public static class Album {
        @SerializedName("attributes")
        private Attributes mAttributes;
        @SerializedName("name-string-res")
        private String mNameStringRes;
        @SerializedName("names")
        private List<NameData> mNames;
        @SerializedName("path")
        private String mPath;

        public static class NameData {
            @SerializedName("language-code")
            private String mLanguageCode;
            @SerializedName("name")
            private String mName;

            public String getLanguageCode() {
                return this.mLanguageCode;
            }

            public String getName() {
                return this.mName;
            }

            public String toString() {
                return "NameData{mLanguageCode='" + this.mLanguageCode + '\'' + ", mName='" + this.mName + '\'' + '}';
            }
        }

        private String getLanguageCode() {
            return getLanguageCode(GalleryApp.sGetAndroidContext().getResources().getConfiguration().locale);
        }

        private String getLanguageCode(Locale locale) {
            String language = locale.getLanguage();
            if (TextUtils.isEmpty(locale.getCountry())) {
                return language;
            }
            return language + "_" + locale.getCountry();
        }

        public Attributes getAttributes() {
            return this.mAttributes;
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x002f A[RETURN] */
        public String getBestName() {
            String str;
            Resources resources = GalleryApp.sGetAndroidContext().getResources();
            String str2 = null;
            if (!TextUtils.isEmpty(this.mNameStringRes)) {
                int identifier = resources.getIdentifier(this.mNameStringRes, "string", "com.miui.gallery");
                if (identifier != 0) {
                    try {
                        str = resources.getString(identifier);
                    } catch (Resources.NotFoundException e) {
                        Log.d("AlbumsStrategy", (Throwable) e);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        return str;
                    }
                }
                str = null;
                if (!TextUtils.isEmpty(str)) {
                }
            } else {
                str = null;
            }
            if (this.mNames != null) {
                String languageCode = getLanguageCode();
                String languageCode2 = getLanguageCode(Locale.ENGLISH);
                boolean equals = TextUtils.equals(languageCode2, resources.getConfiguration().locale.getLanguage());
                Iterator<NameData> it = this.mNames.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    NameData next = it.next();
                    if (next.getLanguageCode().equals(languageCode)) {
                        str = next.getName();
                        break;
                    } else if (equals && next.getLanguageCode().equals(languageCode2)) {
                        str2 = next.getName();
                    }
                }
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
                if (!TextUtils.isEmpty(str2)) {
                    return str2;
                }
            }
            return str;
        }

        public String getPath() {
            return this.mPath;
        }

        public String toString() {
            return "AlbumsStrategy{mPath='" + this.mPath + '\'' + ", mNames=" + this.mNames + ", mAttributes=" + this.mAttributes + ", mNameStringRes=" + this.mNameStringRes + '}';
        }
    }

    public static class AlbumPattern {
        @SerializedName("attributes")
        private Attributes mAttributes;
        @SerializedName("pattern")
        private String mPattern;

        public Attributes getAttributes() {
            return this.mAttributes;
        }

        public String getPattern() {
            return this.mPattern;
        }

        public String toString() {
            return "AlbumPattern{mPattern='" + this.mPattern + '\'' + ", mAttributes=" + this.mAttributes + '}';
        }
    }

    public static class Attributes {
        @SerializedName("auto-upload")
        private boolean mAutoUpload;
        @SerializedName("hide")
        private boolean mHide;
        @SerializedName("in-whitelist")
        private boolean mInWhiteList;
        @SerializedName("manual-rename-restricted")
        private boolean mManualRenameRestricted;
        @SerializedName("show-in-photos-tab")
        private boolean mShowInPhotosTab;

        public boolean isAutoUpload() {
            return this.mAutoUpload;
        }

        public boolean isHide() {
            return this.mHide;
        }

        public boolean isInWhiteList() {
            return this.mInWhiteList;
        }

        public boolean isManualRenameRestricted() {
            return this.mManualRenameRestricted;
        }

        public boolean isShowInPhotosTab() {
            return this.mShowInPhotosTab;
        }

        public String toString() {
            return "Attributes{mHide=" + this.mHide + ", mAutoUpload=" + this.mAutoUpload + ", mShowInPhotosTab=" + this.mShowInPhotosTab + ", mInWhiteList=" + this.mInWhiteList + ", mManualRenameRestricted=" + this.mManualRenameRestricted + '}';
        }
    }

    public void doAdditionalProcessing() {
        if (MiscUtil.isValid(this.mAlbums)) {
            HashMap<String, Album> hashMap = this.mAlbumsMap;
            if (hashMap == null) {
                this.mAlbumsMap = new HashMap<>();
            } else {
                hashMap.clear();
            }
            ArrayList<String> arrayList = this.mWhiteList;
            if (arrayList == null) {
                this.mWhiteList = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            for (Album next : this.mAlbums) {
                if (next.getPath() != null) {
                    this.mAlbumsMap.put(next.getPath().toLowerCase(), next);
                    if (next.getAttributes() != null && next.getAttributes().isInWhiteList()) {
                        this.mWhiteList.add(next.getPath());
                    }
                }
            }
        }
        if (MiscUtil.isValid(this.mAlbumPatterns)) {
            ArrayList<Pattern> arrayList2 = this.mWhiteListPatterns;
            if (arrayList2 == null) {
                this.mWhiteListPatterns = new ArrayList<>();
            } else {
                arrayList2.clear();
            }
            for (AlbumPattern next2 : this.mAlbumPatterns) {
                if (!TextUtils.isEmpty(next2.getPattern()) && next2.getAttributes() != null && next2.getAttributes().isInWhiteList()) {
                    try {
                        this.mWhiteListPatterns.add(Pattern.compile(next2.getPattern(), 2));
                    } catch (PatternSyntaxException e) {
                        Log.e("AlbumsStrategy", (Throwable) e);
                    }
                }
            }
        }
    }

    public Album getAlbumByPath(String str) {
        HashMap<String, Album> hashMap;
        if (str == null || (hashMap = this.mAlbumsMap) == null || hashMap.size() <= 0) {
            return null;
        }
        return this.mAlbumsMap.get(str.toLowerCase());
    }

    public List<AlbumPattern> getAlbumPatterns() {
        return this.mAlbumPatterns;
    }

    public ArrayList<String> getAlbumsInWhiteList() {
        return this.mWhiteList;
    }

    public ArrayList<Pattern> getWhiteListPatterns() {
        return this.mWhiteListPatterns;
    }

    public String toString() {
        return "AlbumsStrategy{mAlbums=" + this.mAlbums + ", mAlbumPatterns=" + this.mAlbumPatterns + '}';
    }
}
