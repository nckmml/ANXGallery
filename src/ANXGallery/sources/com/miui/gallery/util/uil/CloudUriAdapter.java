package com.miui.gallery.util.uil;

import android.content.ContentUris;
import android.content.UriMatcher;
import android.net.Uri;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.sdk.uploadstatus.ItemType;
import com.miui.gallery.sdk.uploadstatus.UriAdapter;

public class CloudUriAdapter extends UriAdapter {
    private static final UriMatcher sUriMatcher = new UriMatcher(-1);

    /* renamed from: com.miui.gallery.util.uil.CloudUriAdapter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$sdk$uploadstatus$ItemType = new int[ItemType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            $SwitchMap$com$miui$gallery$sdk$uploadstatus$ItemType[ItemType.OWNER.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$sdk$uploadstatus$ItemType[ItemType.OWNER_SUB_UBI.ordinal()] = 2;
            $SwitchMap$com$miui$gallery$sdk$uploadstatus$ItemType[ItemType.SHARER.ordinal()] = 3;
            try {
                $SwitchMap$com$miui$gallery$sdk$uploadstatus$ItemType[ItemType.SHARER_SUB_UBI.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    static {
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "gallery_cloud/#", 0);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "cloud_owner_sububi/#", 1);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "share_image/#", 2);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "share_image_sububi/#", 3);
    }

    public static Uri getDownloadUri(long j) {
        return ShareMediaManager.isOtherShareMediaId(j) ? ContentUris.withAppendedId(GalleryContract.ShareImage.SHARE_URI, ShareMediaManager.getOriginalMediaId(j)) : ContentUris.withAppendedId(GalleryContract.Cloud.CLOUD_URI, j);
    }

    public String getLocalId(Uri uri) {
        return uri.getLastPathSegment();
    }

    public Uri getUserUri(ItemType itemType, String str) {
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$sdk$uploadstatus$ItemType[itemType.ordinal()];
        if (i == 1) {
            return ContentUris.withAppendedId(GalleryContract.Cloud.CLOUD_URI, Long.valueOf(str).longValue());
        }
        if (i == 2) {
            return ContentUris.withAppendedId(GalleryContract.Cloud.CLOUD_SUBUBI, Long.valueOf(str).longValue());
        }
        if (i == 3) {
            return ContentUris.withAppendedId(GalleryContract.ShareImage.SHARE_URI, Long.valueOf(str).longValue());
        }
        if (i != 4) {
            return null;
        }
        return ContentUris.withAppendedId(GalleryContract.ShareImage.SHARE_URI_SUBUBI, Long.valueOf(str).longValue());
    }
}
