package com.miui.gallery.provider;

import android.text.TextUtils;
import com.miui.gallery.provider.GalleryContract;
import java.io.File;
import java.util.Locale;

public interface InternalContract {

    public interface Cloud {
        public static final String ALIAS_ALBUM_CLASSIFICATION;
        public static final String ALIAS_CLEAR_FIRST = (" CASE WHEN " + ALIAS_ORIGIN_FILE_VALID + " THEN " + "localFile" + " " + "WHEN " + ALIAS_THUMBNAIL_VALID + " THEN " + "thumbnailFile" + " " + "ELSE " + "microthumbfile" + " " + "END ");
        public static final String ALIAS_LOCAL_IMAGE = ("(" + ALIAS_ORIGIN_FILE_VALID + " OR " + ALIAS_THUMBNAIL_VALID + ")");
        public static final String ALIAS_LOCAL_MEDIA = ("((serverType=2 AND " + ALIAS_ORIGIN_FILE_VALID + ")" + " OR " + "(" + "serverType" + "=" + 1 + " AND (" + ALIAS_ORIGIN_FILE_VALID + " OR " + ALIAS_THUMBNAIL_VALID + "))" + ")");
        public static final String ALIAS_LOCAL_VIDEO;
        public static final String ALIAS_MICRO_VALID = String.format(Locale.US, "(%s NOT NULL and %s != '')", new Object[]{"microthumbfile", "microthumbfile"});
        public static final String ALIAS_NON_SYSTEM_ALBUM;
        public static final String ALIAS_NOT_IN_LOCAL_SYSTEM_ALBUM;
        public static final String ALIAS_ORIGIN_FILE_VALID = String.format(Locale.US, "(%s NOT NULL and %s != '')", new Object[]{"localFile", "localFile"});
        public static final String ALIAS_SIZE_FIRST = (" CASE WHEN " + ALIAS_MICRO_VALID + " THEN " + "microthumbfile" + " " + "WHEN " + ALIAS_THUMBNAIL_VALID + " THEN " + "thumbnailFile" + " " + "ELSE " + "localFile" + " " + "END ");
        public static final String ALIAS_THUMBNAIL_VALID = String.format(Locale.US, "(%s NOT NULL and %s != '')", new Object[]{"thumbnailFile", "thumbnailFile"});
        public static final String ALIAS_USER_CREATE_ALBUM;
        public static final String INVALID_FILE_PATH_NULL_MARK = null;

        static {
            StringBuilder sb = new StringBuilder();
            sb.append("local_path LIKE 'MIUI/Gallery/cloud/owner");
            sb.append(File.separator);
            sb.append("%' COLLATE NOCASE");
            ALIAS_USER_CREATE_ALBUM = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("(serverId IS NULL OR serverId NOT IN ('");
            sb2.append(TextUtils.join("','", GalleryContract.Album.ALL_SYSTEM_ALBUM_SERVER_IDS));
            sb2.append("'))");
            ALIAS_NON_SYSTEM_ALBUM = sb2.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("(serverId IS NULL OR serverId NOT IN ('");
            sb3.append(TextUtils.join("','", GalleryContract.Album.LOCAL_SYSTEM_ALBUM_SERVER_IDS));
            sb3.append("'))");
            ALIAS_NOT_IN_LOCAL_SYSTEM_ALBUM = sb3.toString();
            StringBuilder sb4 = new StringBuilder();
            sb4.append("(");
            sb4.append(ALIAS_ORIGIN_FILE_VALID);
            sb4.append(")");
            ALIAS_LOCAL_VIDEO = sb4.toString();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(" CASE  WHEN (attributes&64<>0 AND ");
            sb5.append(ALIAS_NON_SYSTEM_ALBUM);
            sb5.append(")");
            sb5.append(" THEN ");
            sb5.append(1);
            sb5.append(" ELSE ");
            sb5.append(0);
            sb5.append(" END ");
            ALIAS_ALBUM_CLASSIFICATION = sb5.toString();
        }
    }

    public interface RecentDiscoveredMedia {
        public static final String ALIAS_CLEAR_THUMBNAIL = Cloud.ALIAS_CLEAR_FIRST;
        public static final String ALIAS_MICRO_THUMBNAIL = Cloud.ALIAS_SIZE_FIRST;
    }

    public interface ShareImage {
        public static final String ALIAS_CLEAR_FIRST = Cloud.ALIAS_CLEAR_FIRST;
        public static final String ALIAS_MICRO_THUMBNAIL = (" CASE WHEN " + Cloud.ALIAS_MICRO_VALID + " THEN " + "microthumbfile" + " " + "WHEN " + Cloud.ALIAS_THUMBNAIL_VALID + " THEN " + "thumbnailFile" + " " + "ELSE " + "localFile" + " " + "END ");
        public static final String ALIAS_SIZE_FIRST = Cloud.ALIAS_SIZE_FIRST;
    }
}
