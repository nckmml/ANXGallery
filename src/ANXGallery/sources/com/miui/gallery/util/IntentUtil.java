package com.miui.gallery.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import com.android.internal.SystemPropertiesCompat;
import com.android.internal.WindowCompat;
import com.miui.gallery.Config;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.activity.AlbumLocalPageActivity;
import com.miui.gallery.activity.BurstPhotoActivity;
import com.miui.gallery.activity.CloudGuideWelcomeActivity;
import com.miui.gallery.activity.CloudSettingsActivity;
import com.miui.gallery.activity.ExternalPhotoPageActivity;
import com.miui.gallery.activity.GallerySettingsActivity;
import com.miui.gallery.activity.InternalPhotoPageActivity;
import com.miui.gallery.activity.PhotoDetailActivity;
import com.miui.gallery.activity.PhotoPreviewSelectActivity;
import com.miui.gallery.activity.facebaby.BabyAlbumDetailActivity;
import com.miui.gallery.adapter.CheckableAdapter;
import com.miui.gallery.agreement.cta.CtaAgreement;
import com.miui.gallery.card.model.MediaInfo;
import com.miui.gallery.card.ui.detail.StoryAlbumActivity;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.collage.CollageActivity;
import com.miui.gallery.editor.photo.app.PhotoEditor;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.movie.ui.activity.MovieActivity;
import com.miui.gallery.picker.PickerActivity;
import com.miui.gallery.picker.helper.Picker;
import com.miui.gallery.picker.uri.OriginUrlRequestor;
import com.miui.gallery.picker.uri.UriGenerator;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.GalleryOpenProvider;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.BaseMediaFragment;
import com.miui.gallery.ui.PhotoPageFragment;
import com.miui.gallery.ui.PhotoPageItem;
import com.miui.gallery.util.GalleryIntent;
import com.miui.gallery.util.photoview.ItemViewInfo;
import com.miui.gallery.util.photoview.PhotoPageDataCache;
import com.miui.gallery.video.VideoFrameProvider;
import com.miui.os.Rom;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.jcodec.containers.mp4.boxes.Box;

public class IntentUtil {
    private static final String[] CONTACT_PHOTO_CLASS = {"com.jeejen.contact.ui.AttachPhotoActivity", "com.android.contacts.activities.AttachPhotoActivity"};
    private static final String[] CONTACT_PHOTO_PACKAGE = {"com.jeejen.family.miui", "com.android.contacts"};
    private static final String[] DEVICE_SUPPORT_MEITU_XX_EDITOR = {"vela"};
    public static final LazyValue<Void, Boolean> IS_XMAN_AVAILABLE = new LazyValue<Void, Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean onInit(Void voidR) {
            Intent intent = new Intent("miui.intent.action.XMAN_SHARE_MANAGER");
            intent.setPackage("com.miui.xman");
            return Boolean.valueOf(MiscUtil.isIntentSupported(intent));
        }
    };
    private static final String[] PLAY_VIDEO_CLASS = {"com.miui.videoplayer.VideoPlayerActivity", "com.miui.videoplayer.VideoPlayerActivity"};
    private static final String[] PLAY_VIDEO_PACKAGE = {"com.miui.videoplayer", "com.miui.video"};
    private static final SupportMeituCollage SUPPORT_MEITU_COLLAGE = new SupportMeituCollage();
    private static final SupportMeituEditor SUPPORT_MEITU_EDITOR = new SupportMeituEditor();
    private static final SupportNewVideoPlayer SUPPORT_NEW_VIDEO_PLAYER = new SupportNewVideoPlayer();
    private static final VideoPlayerCapabilities VIDEO_PLAYER_CAPABILITIES = new VideoPlayerCapabilities();
    private static final VideoPlayerSupportEditSubtitle VIDEO_PLAYER_SUPPORT_EDIT_SUBTITLE = new VideoPlayerSupportEditSubtitle();

    private static class SupportMeituCollage extends LazyValue<Void, Boolean> {
        private SupportMeituCollage() {
        }

        /* access modifiers changed from: protected */
        public Boolean onInit(Void voidR) {
            if (!IntentUtil.isDeviceSupportMeituXX()) {
                return false;
            }
            Intent intent = new Intent("com.xiaomi.intent.action.PUZZLE");
            intent.setPackage("com.mt.mtxx.mtxx");
            intent.setType("image/*");
            return Boolean.valueOf(MiscUtil.isIntentSupported(intent));
        }
    }

    private static class SupportMeituEditor extends LazyValue<Void, Boolean> {
        private SupportMeituEditor() {
        }

        /* access modifiers changed from: protected */
        public Boolean onInit(Void voidR) {
            if (!IntentUtil.isDeviceSupportMeituXX()) {
                return false;
            }
            Intent intent = new Intent("com.xiaomi.intent.action.BEAUTY");
            intent.setPackage("com.mt.mtxx.mtxx");
            intent.setType("image/*");
            return Boolean.valueOf(MiscUtil.isIntentSupported(intent));
        }
    }

    private static class SupportNewVideoPlayer extends LazyValue<Void, Boolean> {
        private SupportNewVideoPlayer() {
        }

        /* access modifiers changed from: protected */
        public Boolean onInit(Void voidR) {
            if (!VideoFrameProvider.isDeviceSupport()) {
                return false;
            }
            Intent intent = new Intent("com.miui.videoplayer.GALLERY_VIDEO_PLAY");
            intent.setPackage(IntentUtil.getMiuiVideoPackageName());
            intent.setData(Uri.parse("content:///"));
            return Boolean.valueOf(MiscUtil.isIntentSupported(intent));
        }
    }

    private static class VideoPlayerCapabilities extends LazyValue<Void, String[]> {
        private VideoPlayerCapabilities() {
        }

        /* access modifiers changed from: protected */
        public String[] onInit(Void voidR) {
            String applicationMetaData = MiscUtil.getApplicationMetaData(IntentUtil.getMiuiVideoPackageName(), "com.miui.video.EDIT_CAPABILITIES");
            if (TextUtils.isEmpty(applicationMetaData)) {
                return null;
            }
            return applicationMetaData.split("\\|");
        }
    }

    private static class VideoPlayerSupportEditSubtitle extends LazyValue<Void, Boolean> {
        private VideoPlayerSupportEditSubtitle() {
        }

        /* access modifiers changed from: protected */
        public Boolean onInit(Void voidR) {
            return Boolean.valueOf(IntentUtil.isVideoPlayerSupport("subtitle"));
        }
    }

    public static boolean checkCreationCondition(Activity activity, int i) {
        if (i == 0) {
            return true;
        }
        if (i == 1) {
            return MovieLibraryLoaderHelper.getInstance().checkAbleOrDownload(activity);
        }
        if (i != 2) {
            return false;
        }
        return PrintInstallHelper.getInstance().ensurePrintFucntionAvailable();
    }

    public static void createShortCutForBabyAlbum(Context context, boolean z, long j, String str, Bitmap bitmap, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent("com.miui.gallery.action.VIEW_SHARED_BABY_ALBUM");
        intent.putExtra("album_id", j);
        intent.putExtra("album_name", str);
        intent.putExtra("other_share_album", z);
        intent.putExtra("people_id", str2);
        intent.putExtra("thumbnail_info_of_baby", str3);
        intent.putExtra("baby_info", str4);
        intent.putExtra("baby_sharer_info", str5);
        String valueOf = TextUtils.isEmpty(str2) ? String.valueOf(j) : str2;
        ShortcutUtil.createShortcut(context, valueOf, str, bitmap, R.drawable.baby_default_icon, intent);
        if (Build.VERSION.SDK_INT < 26) {
            valueOf = str;
        }
        GalleryPreferences.Baby.recordBabyAlbumHasShortcut(valueOf);
    }

    /* access modifiers changed from: private */
    public static void dispatchImageUrisInternalIntent(Activity activity, Intent intent, Uri[] uriArr) {
        if (uriArr.length == 0) {
            Log.e("IntentUtil", "No result Uris to dispatch!");
            return;
        }
        if (GalleryOpenProvider.needReturnContentUri((Context) activity, "")) {
            for (int i = 0; i < uriArr.length; i++) {
                uriArr[i] = GalleryOpenProvider.translateToContent(uriArr[i].getPath());
            }
        }
        ClipData clipData = new ClipData("data", new String[]{"image/*", "text/uri-list"}, new ClipData.Item(uriArr[0]));
        for (int i2 = 1; i2 < uriArr.length; i2++) {
            clipData.addItem(new ClipData.Item(uriArr[i2]));
        }
        intent.setClipData(clipData);
        intent.setFlags(1);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("IntentUtil", (Throwable) e);
        }
    }

    public static void doCreation(final Activity activity, final int i, List<CheckableAdapter.CheckedItem> list, Picker.ImageType imageType) {
        if (list.isEmpty()) {
            Log.e("IntentUtil", "No selected Item!");
        } else {
            new UriGenerator(activity, list, imageType, new UriGenerator.UriGenerateListener() {
                public void onFail() {
                    IntentUtil.recordUriGenerateError();
                }

                public void onSuccess(Uri[] uriArr, List<OriginUrlRequestor.OriginInfo> list) {
                    int i = i;
                    if (i == 0) {
                        boolean z = false;
                        if (IntentUtil.isSupportMeituCollage()) {
                            z = IntentUtil.startMeituCollageAction(activity, Arrays.asList(uriArr), -1);
                        }
                        if (!z) {
                            IntentUtil.dispatchImageUrisInternalIntent(activity, new Intent(activity, CollageActivity.class), uriArr);
                        }
                    } else if (i != 1) {
                        if (i == 2) {
                            PrintInstallHelper.getInstance().printPhotos(activity, uriArr, list);
                        }
                    } else if (MovieLibraryLoaderHelper.getInstance().checkAbleOrDownload(activity)) {
                        IntentUtil.dispatchImageUrisInternalIntent(activity, new Intent(activity, MovieActivity.class), uriArr);
                    }
                }
            }).generate();
        }
    }

    private static String ensureMimeType(Uri uri, String str) {
        return !TextUtils.isEmpty(str) ? str : FileMimeUtil.getMimeType(uri.toString());
    }

    public static void enterGallerySetting(Context context) {
        context.startActivity(new Intent(context, GallerySettingsActivity.class));
    }

    public static void enterPrivateAlbum(Context context) {
        Intent intent = new Intent("com.miui.gallery.action.VIEW_ALBUM_DETAIL");
        intent.putExtra("album_id", -1000);
        intent.putExtra("album_name", context.getResources().getString(R.string.secret_album_display_name));
        intent.putExtra("album_server_id", String.valueOf(1000));
        intent.putExtra("album_unwriteable", false);
        context.startActivity(intent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        r3 = r3.getReferrer();
     */
    public static String getCallingPackage(Activity activity) {
        Uri referrer;
        ComponentName callingActivity;
        String callingPackage = activity.getCallingPackage();
        if (TextUtils.isEmpty(callingPackage) && (callingActivity = activity.getCallingActivity()) != null) {
            callingPackage = callingActivity.getPackageName();
        }
        return (!TextUtils.isEmpty(callingPackage) || Build.VERSION.SDK_INT < 22 || referrer == null) ? callingPackage : referrer.toString();
    }

    public static int getCollageMaxImageSize() {
        return isSupportMeituCollage() ? 9 : 6;
    }

    public static String getMiuiVideoPackageName() {
        return BuildUtil.isInternational() ? "com.miui.videoplayer" : "com.miui.video";
    }

    public static void gotoAlbumDetailPage(Context context, Album album, int i, int i2) {
        Intent intent;
        Intent intent2;
        if (album != null) {
            long albumId = album.getAlbumId();
            if (album.isOtherAlbum() && !album.isHiddenAlbum()) {
                intent = new Intent(context, AlbumLocalPageActivity.class);
            } else if (album.isFaceAlbum()) {
                intent = new Intent("com.miui.gallery.action.VIEW_PEOPLE");
            } else if (album.isRecentAlbum()) {
                intent = new Intent("android.intent.action.VIEW");
                intent.setData(GalleryContract.RecentAlbum.VIEW_PAGE_URI.buildUpon().appendQueryParameter("source", "album_page").build());
                intent.setPackage(context.getPackageName());
            } else {
                if (!album.isBabyAlbum()) {
                    intent2 = new Intent("com.miui.gallery.action.VIEW_ALBUM_DETAIL");
                } else {
                    intent2 = new Intent(context, BabyAlbumDetailActivity.class);
                    intent2.putExtra("people_id", album.getPeopleId());
                    intent2.putExtra("baby_info", album.getBabyInfo());
                    intent2.putExtra("thumbnail_info_of_baby", album.getThumbnailInfoOfBaby());
                    intent2.putExtra("baby_sharer_info", album.getBabyShareInfo());
                    intent2.putExtra("photodetail_is_photo_datetime_editable", false);
                }
                boolean isOtherShareAlbum = album.isOtherShareAlbum();
                intent2.putExtra("other_share_album", isOtherShareAlbum);
                if (isOtherShareAlbum) {
                    intent2.putExtra("photodetail_is_photo_datetime_editable", false);
                }
                intent2.putExtra("owner_share_album", album.isOwnerShareAlbum());
                intent2.putExtra("is_local_album", !SyncUtil.existXiaomiAccount(GalleryApp.sGetAndroidContext()) || !album.isAutoUploadedAlbum());
                intent2.putExtra("screenshot_album", false);
                intent2.putExtra("pano_album", album.isPanoAlbum());
                intent2.putExtra("album_id", albumId);
                intent2.putExtra("album_name", album.getAlbumName());
                intent2.putExtra("album_server_id", album.getServerId());
                intent2.putExtra("attributes", album.getAttributes());
                intent2.putExtra("album_unwriteable", album.isImmutable());
                intent2.putExtra("album_local_path", album.getLocalPath());
                intent2.putExtra("extra_from_type", i);
                intent = intent2;
            }
            if (context == null) {
                return;
            }
            if (-1 == i2 || !(context instanceof Activity)) {
                context.startActivity(intent);
            } else {
                ((Activity) context).startActivityForResult(intent, i2);
            }
        }
    }

    public static boolean gotoAppDetailTraffic(Context context) {
        Intent intent = new Intent("miui.intent.action.NETWORKASSISTANT_APP_DETAIL");
        Bundle bundle = new Bundle();
        bundle.putString("package_name", context.getPackageName());
        bundle.putInt("title_type", 2);
        bundle.putInt("sort_type", 0);
        intent.putExtras(bundle);
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception unused) {
            Log.e("IntentUtil", "can't go to traffic page");
            return false;
        }
    }

    public static boolean gotoBugreport(Context context) {
        try {
            Intent intent = new Intent("miui.intent.action.BUGREPORT");
            intent.putExtra("packageName", "com.miui.gallery");
            intent.addFlags(268435456);
            context.startActivity(intent);
            return true;
        } catch (Exception unused) {
            Log.w("IntentUtil", "can't go to bug report app");
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.addCategory("android.intent.category.BROWSABLE");
                intent2.setData(Uri.parse("http://www.miui.com"));
                context.startActivity(intent2);
                return true;
            } catch (Exception unused2) {
                Log.e("IntentUtil", "can't go to www.miui.com");
                return false;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.app.Fragment, com.miui.gallery.ui.PhotoPageFragment] */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static void gotoBurstPhotoActivity(Activity activity, PhotoPageFragment r6, BaseDataItem baseDataItem, long j, String str) {
        ActivityOptionsCompat activityOptionsCompat;
        PhotoPageItem pageItem;
        Intent intent = new Intent(activity, BurstPhotoActivity.class);
        Bundle bundle = new Bundle();
        Uri build = GalleryContract.Media.URI.buildUpon().appendQueryParameter("remove_duplicate_items", String.valueOf(true)).build();
        bundle.putParcelable("photo_data", build);
        bundle.putLong("album_id", j);
        bundle.putString("photo_selection", str);
        bundle.putString("photo_order_by", "alias_sort_time desc ");
        bundle.putBoolean("unford_burst", true);
        bundle.putInt("notch_height", WindowCompat.getTopNotchHeight(activity));
        intent.putExtra("extra_custom_transition", true);
        intent.setData(build);
        intent.putExtras(bundle);
        Bundle bundle2 = null;
        if (activity.getResources().getConfiguration().orientation != 1 || !com.miui.gallery.compat.view.WindowCompat.supportActivityTransitions(activity.getWindow()) || (pageItem = r6.getPageItem()) == null) {
            activityOptionsCompat = null;
        } else {
            activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, Pair.create(pageItem, activity.getString(R.string.burst_transition_image_view)));
            intent.putExtra("has_transition", true);
        }
        if (activityOptionsCompat != null) {
            bundle2 = activityOptionsCompat.toBundle();
        }
        activity.startActivityFromFragment(r6, intent, 52, bundle2);
    }

    public static void gotoCloudAlbumListPage(Context context) {
        if (context != null) {
            Intent intent = new Intent("com.miui.gallery.action.SETTING_MAIN");
            intent.putExtra("extra_to_type", 1002);
            context.startActivity(intent);
        }
    }

    public static void gotoCloudPrivacy(Context context) {
        Intent intent = new Intent("com.miui.gallery.action.VIEW_WEB");
        intent.putExtra("url", HostManager.CloudPrivacy.getCloudPrivacyUrl());
        context.startActivity(intent);
    }

    public static void gotoDailyAlbumDetailPage(Activity activity, int i) {
        Intent intent = new Intent("com.miui.gallery.action.VIEW_ALBUM_DETAIL");
        intent.putExtra("daily_album", true);
        intent.putExtra("daily_album_date", i);
        intent.putExtra("album_name", GalleryDateUtils.formatRelativeDate(GalleryDateUtils.format(i)));
        intent.putExtra("album_unwriteable", false);
        intent.putExtra("album_id", 2147483643);
        intent.putExtra("album_server_id", String.valueOf(-2147483643));
        activity.startActivity(intent);
    }

    public static void gotoDeepClean(Context context) {
        try {
            context.startActivity(new Intent("miui.intent.action.GARBAGE_DEEPCLEAN"));
        } catch (Exception unused) {
            Log.e("IntentUtil", "can't goto deep clean page");
        }
    }

    public static void gotoHiddenAlbumPage(Context context) {
        if (context != null) {
            Intent intent = new Intent("com.miui.gallery.action.SETTING_MAIN");
            intent.putExtra("extra_to_type", 1001);
            context.startActivity(intent);
        }
    }

    public static void gotoMiCloudVipPage(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setComponent(new ComponentName("com.miui.cloudservice", "com.miui.cloudservice.ui.MiCloudHybridActivity"));
            intent.putExtra("com.miui.sdk.hybrid.extra.URL", "https://i.mi.com/vip?source=miui_gallery&ext=miui_gallery");
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e("IntentUtil", "can't goto micloud vip page", (Object) e);
        }
    }

    public static void gotoOperationCard(Context context, String str) {
        Intent intent = new Intent("com.miui.gallery.action.VIEW_WEB_OPERATION_STORY");
        intent.putExtra("url", str);
        context.startActivity(intent);
    }

    public static void gotoPhotoDetailPage(Activity activity, BaseDataItem baseDataItem, boolean z, boolean z2, boolean z3) {
        Intent intent = new Intent(activity, PhotoDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("photo_detail_target", baseDataItem);
        bundle.putBoolean("StartActivityWhenLocked", z);
        bundle.putBoolean("photodetail_is_photo_datetime_editable", z2);
        bundle.putBoolean("photo_detail_is_need_download_originphoto", z3);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 38);
    }

    private static void gotoPhotoPage(final Activity activity, ViewGroup viewGroup, Uri uri, int i, int i2, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, long j, String str3, int i3, boolean z, String str4, ArrayList<ItemViewInfo> arrayList, boolean z2, boolean z3) {
        Activity activity2 = activity;
        ViewGroup viewGroup2 = viewGroup;
        Uri uri2 = uri;
        String[] strArr2 = strArr;
        ImageLoadParams imageLoadParams2 = imageLoadParams;
        long j2 = j;
        ArrayList<ItemViewInfo> arrayList2 = arrayList;
        ThreadManager.getPreviewPool().submit(new ThreadPool.Job<Object>() {
            public Object run(ThreadPool.JobContext jobContext) {
                activity.startActivity(new Intent(activity, InternalPhotoPageActivity.class));
                return null;
            }
        });
        Bundle bundle = new Bundle();
        bundle.putParcelable("photo_data", uri);
        int i4 = i;
        bundle.putInt("photo_init_position", i);
        bundle.putInt("photo_count", i2);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("photo_selection", str);
        }
        if (strArr2 != null) {
            bundle.putStringArray("photo_selection_args", strArr2);
        }
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("photo_order_by", str2);
        }
        if (imageLoadParams2 != null) {
            bundle.putParcelable("photo_transition_data", imageLoadParams2);
        }
        bundle.putBoolean("from_gallery", true);
        if (viewGroup2 != null) {
            PhotoPageDataCache.getInstance().setItemViewParent(viewGroup);
        } else if (arrayList2 != null && arrayList.size() > 0) {
            PhotoPageDataCache.getInstance().setAppointedItemViewInfos(arrayList2);
        }
        if (!TextUtils.isEmpty(str3)) {
            bundle.putString("album_name", str3);
        }
        if (j2 == 2147483640 || j2 == -1000) {
            bundle.putBoolean("photodetail_is_photo_datetime_editable", false);
        }
        if (uri2 != null) {
            if (uri.getEncodedPath().contains("person")) {
                bundle.putBoolean("photodetail_is_photo_datetime_editable", false);
            } else if (uri.getEncodedPath().contains("searchResultPhoto")) {
                bundle.putBoolean("photodetail_is_photo_datetime_editable", false);
            } else if (uri.getEncodedPath().contains("share_album_media")) {
                bundle.putBoolean("photodetail_is_photo_datetime_editable", false);
            }
        }
        bundle.putLong("album_id", j2);
        bundle.putInt("support_operation_mask", i3);
        bundle.putBoolean("from_recommend_face_page", z);
        bundle.putString("recommend_face_id", str4);
        bundle.putBoolean("photo_enter_transit", true);
        bundle.putBoolean("photo_preview_mode", z2);
        if (z3) {
            bundle.putBoolean("unford_burst", true);
        }
        PhotoPageDataCache.getInstance().setArguments(bundle);
        if (imageLoadParams2 != null) {
            DisplayImageOptions.Builder cacheInMemory = new DisplayImageOptions.Builder().cloneFrom(Config.ThumbConfig.THUMBNAIL_DISPLAY_IMAGE_OPTIONS_DEFAULT).syncLoadFromThumbCache(false).cacheInMemory(true);
            if (imageLoadParams.getFileLength() > 0) {
                cacheInMemory.considerFileLength(true).fileLength(imageLoadParams.getFileLength());
            }
            imageLoadParams2.setDisplayImageOptions(cacheInMemory.build());
            PhotoPageDataCache.preLoad(activity, imageLoadParams2);
        }
    }

    public static void gotoPhotoPage(BaseMediaFragment baseMediaFragment, ViewGroup viewGroup, Uri uri, int i, int i2, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, long j, String str3, int i3, boolean z) {
        gotoPhotoPage(baseMediaFragment.getActivity(), viewGroup, uri, i, i2, str, strArr, str2, imageLoadParams, j, str3, i3, false, (String) null, (ArrayList<ItemViewInfo>) null, false, z);
    }

    public static void gotoPhotoPage(BaseMediaFragment baseMediaFragment, ViewGroup viewGroup, Uri uri, int i, int i2, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, long j, String str3, int i3, boolean z, boolean z2) {
        Activity activity = baseMediaFragment.getActivity();
        gotoPhotoPage(activity, viewGroup, uri, i, i2, str, strArr, str2, imageLoadParams, j, str3, i3, false, (String) null, (ArrayList<ItemViewInfo>) null, z2, z);
    }

    public static void gotoPhotoPage(BaseMediaFragment baseMediaFragment, ViewGroup viewGroup, Uri uri, int i, int i2, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, long j, boolean z) {
        gotoPhotoPage(baseMediaFragment.getActivity(), viewGroup, uri, i, i2, str, strArr, str2, imageLoadParams, j, (String) null, -1, false, (String) null, (ArrayList<ItemViewInfo>) null, false, z);
    }

    public static void gotoPhotoPage(BaseMediaFragment baseMediaFragment, ViewGroup viewGroup, Uri uri, int i, int i2, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, String str3, boolean z) {
        gotoPhotoPage(baseMediaFragment.getActivity(), viewGroup, uri, i, i2, str, strArr, str2, imageLoadParams, -1, str3, -1, false, (String) null, (ArrayList<ItemViewInfo>) null, false, z);
    }

    public static void gotoPhotoPage(BaseMediaFragment baseMediaFragment, ViewGroup viewGroup, Uri uri, int i, int i2, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, String str3, boolean z, String str4, ArrayList<ItemViewInfo> arrayList, boolean z2) {
        gotoPhotoPage(baseMediaFragment.getActivity(), viewGroup, uri, i, i2, str, strArr, str2, imageLoadParams, -1, str3, -1, z, str4, arrayList, false, z2);
    }

    public static void gotoPhotoPage(BaseMediaFragment baseMediaFragment, ViewGroup viewGroup, Uri uri, int i, int i2, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, boolean z) {
        gotoPhotoPage(baseMediaFragment.getActivity(), viewGroup, uri, i, i2, str, strArr, str2, imageLoadParams, -1, (String) null, -1, false, (String) null, (ArrayList<ItemViewInfo>) null, false, z);
    }

    public static void gotoPhotoPageFromPicker(Activity activity, Uri uri, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, ArrayList<ItemViewInfo> arrayList, boolean z) {
        gotoPhotoPage(activity, (ViewGroup) null, uri, 0, 1, str, strArr, str2, imageLoadParams, -1, (String) null, -1, false, (String) null, arrayList, true, z);
    }

    public static void gotoPreviewSelectPage(BaseMediaFragment baseMediaFragment, Uri uri, int i, int i2, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, long[] jArr, int[] iArr) {
        gotoPreviewSelectPage(baseMediaFragment, uri, i, i2, str, strArr, str2, imageLoadParams, jArr, iArr, (String) null, (String) null);
    }

    public static void gotoPreviewSelectPage(BaseMediaFragment baseMediaFragment, Uri uri, int i, int i2, String str, String[] strArr, String str2, ImageLoadParams imageLoadParams, long[] jArr, int[] iArr, String str3, String str4) {
        Intent intent = new Intent(baseMediaFragment.getActivity(), PhotoPreviewSelectActivity.class);
        intent.setData(uri);
        intent.putExtra("photo_init_position", i);
        intent.putExtra("photo_count", i2);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("photo_selection", str);
        }
        if (strArr != null) {
            intent.putExtra("photo_selection_args", strArr);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("photo_order_by", str2);
        }
        if (imageLoadParams != null) {
            intent.putExtra("photo_transition_data", imageLoadParams);
        }
        if (jArr != null) {
            intent.putExtra("photo_preview_selected_ids", jArr);
        }
        if (iArr != null) {
            intent.putExtra("photo_preview_selected_positions", iArr);
        }
        if (str3 != null) {
            intent.putExtra("assistant_target_package", str3);
        }
        if (str4 != null) {
            intent.putExtra("assistant_target_class", str4);
        }
        baseMediaFragment.startActivity(intent);
    }

    public static void gotoPrivacyPolicy(Activity activity) {
        activity.startActivity(CtaAgreement.Licence.getPrivacyIntent());
    }

    public static boolean gotoSettings(Context context) {
        try {
            context.startActivity(new Intent("android.settings.SETTINGS"));
            return true;
        } catch (Exception unused) {
            Log.e("IntentUtil", "can't go to settings page");
            return false;
        }
    }

    public static void gotoSinglePhotoPage(Context context, Uri uri) {
        Intent intent = new Intent(context, ExternalPhotoPageActivity.class);
        intent.setData(uri);
        ArrayList arrayList = new ArrayList();
        arrayList.add(uri);
        intent.putExtra("com.miui.gallery.extra.photo_items", arrayList);
        context.startActivity(intent);
    }

    public static void gotoStoryAlbum(Activity activity, long j) {
        if (activity != null) {
            Intent intent = new Intent(activity, StoryAlbumActivity.class);
            intent.putExtra("card_id", j);
            activity.startActivity(intent);
        }
    }

    public static void gotoTrashBin(Context context, String str) {
        Intent intent = new Intent("com.miui.gallery.action.VIEW_WEB_DEVICE_ID");
        intent.putExtra("url", com.miui.gallery.request.HostManager.getTrashBinUrl());
        context.startActivity(intent);
        HashMap hashMap = new HashMap();
        hashMap.put("from", str);
        GallerySamplingStatHelper.recordCountEvent("trash_bin", "enter_trash_bin_page", hashMap);
    }

    public static void gotoTurnOnSyncSwitch(Context context) {
        if (context != null) {
            if (!ContentResolver.getMasterSyncAutomatically()) {
                context.startActivity(new Intent("com.xiaomi.action.MICLOUD_MAIN"));
                return;
            }
            Intent intent = new Intent(context, CloudSettingsActivity.class);
            intent.setAction("com.miui.gallery.cloud.provider.SYNC_SETTINGS");
            context.startActivity(intent);
        }
    }

    public static void guideToLoginXiaomiAccount(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CloudGuideWelcomeActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void guideToLoginXiaomiAccount(Context context, GalleryIntent.CloudGuideSource cloudGuideSource) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("cloud_guide_source", cloudGuideSource);
        guideToLoginXiaomiAccount(context, bundle);
    }

    /* access modifiers changed from: private */
    public static boolean isDeviceSupportMeituXX() {
        for (String equals : DEVICE_SUPPORT_MEITU_XX_EDITOR) {
            if (Build.DEVICE.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSpeechInputSupported() {
        if (BuildUtil.isInternational() || BuildUtil.isPad()) {
            return false;
        }
        Intent intent = new Intent("com.xiaomi.mibrain.action.RECOGNIZE_SPEECH");
        intent.setPackage("com.xiaomi.mibrain.speech");
        return MiscUtil.isIntentSupported(intent);
    }

    public static boolean isSupportMeituCollage() {
        return ((Boolean) SUPPORT_MEITU_COLLAGE.get(null)).booleanValue();
    }

    public static boolean isSupportMeituEditor() {
        return ((Boolean) SUPPORT_MEITU_EDITOR.get(null)).booleanValue();
    }

    public static boolean isSupportNewVideoPlayer() {
        return ((Boolean) SUPPORT_NEW_VIDEO_PLAYER.get(null)).booleanValue();
    }

    /* access modifiers changed from: private */
    public static boolean isVideoPlayerSupport(String str) {
        String[] strArr;
        if (!TextUtils.isEmpty(str) && (strArr = (String[]) VIDEO_PLAYER_CAPABILITIES.get(null)) != null) {
            for (String equals : strArr) {
                if (TextUtils.equals(str, equals)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isVideoPlayerSupportEditSubtitle() {
        return ((Boolean) VIDEO_PLAYER_SUPPORT_EDIT_SUBTITLE.get(null)).booleanValue();
    }

    public static void jumpToExplore(Context context, String str) {
        try {
            Intent intent = new Intent("miui.intent.action.OPEN");
            intent.addFlags(268435456);
            intent.addFlags(Box.MAX_BOX_SIZE);
            if (Rom.IS_INTERNATIONAL) {
                intent.setPackage("com.mi.android.globalFileexplorer");
            } else {
                intent.setPackage("com.android.fileexplorer");
            }
            intent.putExtra("explorer_path", str);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pickFace(Activity activity, String str, String str2, String str3, ArrayList<Long> arrayList, int i, boolean z) {
        Intent intent = new Intent("com.miui.gallery.action.PICK_FACE");
        intent.putExtra("server_id_of_album", str2);
        intent.putExtra("local_id_of_album", str3);
        intent.putExtra("album_name", str);
        intent.putExtra("pick_face_direct", true);
        intent.putExtra("need_pick_face_id", z);
        if (arrayList != null && arrayList.size() > 0) {
            intent.putExtra("pick_face_ids_in", TextUtils.join(",", arrayList));
        }
        intent.putExtra("pick-upper-bound", i);
        intent.putExtra("picker_result_set", PickerActivity.copyPicker((Picker) null));
        activity.startActivityForResult(intent, 31);
    }

    public static void pickPeople(Activity activity, String str) {
        Intent intent = new Intent("com.miui.gallery.action.PICK_PEOPLE");
        intent.putExtra("pick_people", true);
        intent.putExtra("pick_people_candidate_name", str);
        activity.startActivityForResult(intent, 14);
    }

    public static boolean playVideo(Context context, Uri uri, String str, boolean z, boolean z2, int i, boolean z3, Bundle bundle) {
        Intent intent = new Intent();
        String ensureMimeType = ensureMimeType(uri, str);
        if ("*/*".equals(ensureMimeType)) {
            ensureMimeType = "video/*";
        }
        try {
            Log.i("IntentUtil", "showGalleryWhenLocked: %s, requestOrientation: %d", Boolean.valueOf(z2), Integer.valueOf(i));
            if (z3) {
                setDataAndType(intent, uri, ensureMimeType, true);
            } else if (!"file".equals(uri.getScheme()) || GalleryOpenProvider.needReturnContentUri()) {
                setDataAndType(intent, uri, ensureMimeType);
            } else {
                setDataAndType(intent, uri, (String) null, false);
            }
            intent.setAction("com.miui.videoplayer.GALLERY_VIDEO_PLAY");
            if (!z || !MiscUtil.isIntentSupported(intent)) {
                intent.setAction("com.miui.videoplayer.LOCAL_VIDEO_PLAY");
            }
            intent.putExtra("StartActivityWhenLocked", z2);
            intent.putExtra("com.miui.video.extra.play_video_request_orientation", i);
            intent.putExtra("com.miui.video.extra.is_secret", z3);
            intent.putExtras(bundle);
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                String callingPackage = activity.getCallingPackage();
                Log.i("IntentUtil", "play video extra calling package: %s", (Object) callingPackage);
                intent.putExtra("miui_video_extra_calling_package", callingPackage);
                activity.startActivityForResult(intent, 45);
            } else {
                context.startActivity(intent);
            }
            return true;
        } catch (ActivityNotFoundException unused) {
            Log.e("IntentUtil", "local video player not found!");
            intent.setAction("android.intent.action.VIEW");
            setDataAndType(intent, uri, ensureMimeType);
            context.startActivity(intent);
            return false;
        } catch (Exception unused2) {
            ToastUtils.makeText(context, (CharSequence) context.getString(R.string.video_err));
            return false;
        }
    }

    public static void prepareVideoPlayerStatus() {
        SUPPORT_NEW_VIDEO_PLAYER.get(null);
        VIDEO_PLAYER_CAPABILITIES.get(null);
    }

    /* access modifiers changed from: private */
    public static void recordUriGenerateError() {
        Log.e("IntentUtil", "checkedItem Uri generate error");
        GallerySamplingStatHelper.recordCountEvent("creation", "checked_item_uri_error");
    }

    public static void removeAllShortCutForBabyAlbums(Context context) {
        Iterator<String> it = GalleryPreferences.Baby.getBabyAlbumsHasShortcut().iterator();
        while (it.hasNext()) {
            String next = it.next();
            removeShortCutForBabyAlbumByName(context, next);
            GalleryPreferences.Baby.removeBabyAlbumShortcut(next);
        }
    }

    public static void removeShortCutForBabyAlbumByName(Context context, String str) {
        Intent intent = new Intent("com.miui.home.launcher.action.UNINSTALL_SHORTCUT");
        intent.setPackage(SystemPropertiesCompat.get("ro.miui.product.home", "com.miui.home"));
        if (Build.VERSION.SDK_INT < 26) {
            intent.putExtra("android.intent.extra.shortcut.NAME", str);
        } else {
            intent.putExtra("shortcut_id", str);
        }
        intent.putExtra("android.intent.extra.shortcut.INTENT", new Intent());
        context.sendBroadcast(intent);
    }

    private static void setDataAndType(Intent intent, Uri uri, String str) {
        setDataAndType(intent, uri, str, GalleryOpenProvider.needReturnContentUri());
    }

    private static void setDataAndType(Intent intent, Uri uri, String str, boolean z) {
        if (!"file".equals(uri.getScheme()) || !z) {
            intent.setDataAndType(uri, str);
            return;
        }
        intent.setDataAndType(GalleryOpenProvider.translateToContent(uri.getPath()), str);
        intent.setFlags(1);
    }

    public static boolean showOnMap(Context context, double d, double d2) {
        try {
            String format = String.format(Locale.US, "http://maps.google.com/maps?f=q&q=(%f,%f)", new Object[]{Double.valueOf(d), Double.valueOf(d2)});
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(format)).setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")));
            return true;
        } catch (ActivityNotFoundException e) {
            try {
                Log.e("IntentUtil", "GMM activity not found!", (Object) e);
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.US, "geo:%f,%f", new Object[]{Double.valueOf(d), Double.valueOf(d2)}))));
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e("IntentUtil", "GEO activity not found!", (Object) e2);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public static void startAdvancedRefocusAction(BaseDataItem baseDataItem, Activity activity, PhotoPageFragment photoPageFragment) {
        startExtraPhotoActivity(baseDataItem, activity, photoPageFragment, "com.miui.extraphoto.action.VIEW_ADVANCED_REFOCUS", 44);
    }

    public static void startCameraActivity(Context context) {
        context.startActivity(new Intent("android.media.action.STILL_IMAGE_CAMERA").setFlags(335544320));
    }

    public static boolean startCloudMainPage(Context context) {
        try {
            context.startActivity(new Intent("com.xiaomi.action.MICLOUD_MAIN"));
            return true;
        } catch (ActivityNotFoundException unused) {
            Log.e("IntentUtil", "cloud main page start fail");
            return false;
        }
    }

    public static void startCollagePicker(Context context) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.putExtra("pick_close_type", 3);
        intent.setPackage("com.miui.gallery");
        Intent intent2 = new Intent();
        intent2.setData(GalleryContract.Common.URI_COLLAGE_FROM_PICKER);
        intent.putExtra("pick_intent", intent2);
        intent.putExtra("pick-upper-bound", getCollageMaxImageSize());
        context.startActivity(intent);
    }

    private static void startDualActionInternal(BaseDataItem baseDataItem, Fragment fragment, boolean z, String str) {
        if (baseDataItem != null && fragment != null && fragment.getActivity() != null && !TextUtils.isEmpty(baseDataItem.getOriginalPath())) {
            Intent intent = new Intent(str);
            intent.setPackage("com.miui.extraphoto");
            setDataAndType(intent, Uri.fromFile(new File(baseDataItem.getOriginalPath())), baseDataItem.getMimeType());
            Bundle bundle = new Bundle();
            bundle.putBoolean("StartActivityWhenLocked", z);
            intent.putExtras(bundle);
            try {
                fragment.startActivityForResult(intent, 37);
            } catch (Exception e) {
                Log.e("IntentUtil", (Throwable) e);
            }
        }
    }

    public static boolean startEditAction(BaseDataItem baseDataItem, Activity activity, PhotoPageFragment photoPageFragment) {
        return startEditAction(baseDataItem, activity, photoPageFragment, true);
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [android.app.Fragment, com.miui.gallery.ui.PhotoPageFragment] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0183  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x018b  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x018f  */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static boolean startEditAction(BaseDataItem baseDataItem, Activity activity, PhotoPageFragment r13, boolean z) {
        ActivityOptionsCompat activityOptionsCompat;
        Intent intent = new Intent();
        intent.putExtra("allow_use_on_offline_global", GalleryPreferences.CTA.allowUseOnOfflineGlobal());
        Bundle bundle = null;
        if (baseDataItem.isVideo()) {
            if (TextUtils.isEmpty(baseDataItem.getOriginalPath())) {
                ToastUtils.makeText((Context) activity, (int) R.string.video_edit_file_not_exits_tip);
                return false;
            }
            try {
                intent.setClass(activity, Class.forName("com.miui.gallery.video.editor.activity.VideoEditorActivity"));
                intent.putExtra("extra_screen_brightness", activity.getWindow().getAttributes().screenBrightness);
                if (activity instanceof BrightnessProvider) {
                    BrightnessProvider brightnessProvider = (BrightnessProvider) activity;
                    intent.putExtra("photo-brightness-manual", brightnessProvider.getManualBrightness());
                    intent.putExtra("photo-brightness-auto", brightnessProvider.getAutoBrightness());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            intent.setDataAndType(Uri.fromFile(new File(baseDataItem.getOriginalPath())), "video/*");
        } else if (baseDataItem.getContentUriForExternal() == null) {
            ToastUtils.makeText((Context) activity, (int) R.string.photo_edit_file_not_exits_tip);
            return false;
        } else {
            intent.setClass(activity, PhotoEditor.class);
            intent.setDataAndType(baseDataItem.getContentUriForExternal(), "image/*");
            intent.putExtra("extra_screen_brightness", activity.getWindow().getAttributes().screenBrightness);
            if (activity instanceof BrightnessProvider) {
                BrightnessProvider brightnessProvider2 = (BrightnessProvider) activity;
                intent.putExtra("photo-brightness-manual", brightnessProvider2.getManualBrightness());
                intent.putExtra("photo-brightness-auto", brightnessProvider2.getAutoBrightness());
            }
            Intent intent2 = activity.getIntent();
            boolean booleanExtra = intent2 != null ? intent2.getBooleanExtra("skip_interception", false) : false;
            Resources resources = activity.getResources();
            ImageView photoView = r13.getPhotoView();
            if (photoView != null) {
                Drawable drawable = photoView.getDrawable();
                if (drawable instanceof BitmapDrawable) {
                    String generatePreviewPhotoKey = MemoryCacheUtils.generatePreviewPhotoKey(baseDataItem.getContentUriForExternal().toString());
                    ImageLoader.getInstance().referenceToMemoryCache(generatePreviewPhotoKey, ((BitmapDrawable) drawable).getBitmap());
                    Log.d("IntentUtil", "cache preview: %s", (Object) generatePreviewPhotoKey);
                }
            }
            if (z && !booleanExtra && com.miui.gallery.compat.view.WindowCompat.supportActivityTransitions(activity.getWindow()) && resources.getConfiguration().orientation == 1 && photoView != null) {
                View findViewById = r13.getView().findViewById(R.id.photo_pager);
                if (findViewById != null) {
                    Drawable drawable2 = photoView.getDrawable();
                    if (drawable2 == null) {
                        return false;
                    }
                    activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, Pair.create(findViewById, resources.getString(R.string.photo_editor_transition_image_view)));
                    intent.putExtra("extra_custom_transition", true);
                    intent.putExtra("extra_image_width", drawable2.getIntrinsicWidth());
                    intent.putExtra("extra_image_height", drawable2.getIntrinsicHeight());
                    float[] fArr = new float[9];
                    photoView.getImageMatrix().getValues(fArr);
                    intent.putExtra("extra_image_matrix", fArr);
                    if (baseDataItem.isSecret()) {
                        intent.putExtra("extra_is_secret", baseDataItem.isSecret());
                        intent.putExtra("extra_secret_key", baseDataItem.getSecretKey());
                        intent.putExtra("photo_secret_id", baseDataItem.getKey());
                    }
                    if (activityOptionsCompat != null) {
                        bundle = activityOptionsCompat.toBundle();
                    }
                    if (r13 == 0) {
                        activity.startActivityFromFragment(r13, intent, 30, bundle);
                    } else {
                        activity.startActivityForResult(intent, 30, bundle);
                    }
                    Log.d("IntentUtil", "startEditor");
                    return true;
                }
                throw new IllegalArgumentException("photo view not found");
            }
        }
        activityOptionsCompat = null;
        if (baseDataItem.isSecret()) {
        }
        if (activityOptionsCompat != null) {
        }
        if (r13 == 0) {
        }
        Log.d("IntentUtil", "startEditor");
        return true;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [android.app.Fragment, com.miui.gallery.ui.PhotoPageFragment] */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static void startExtraPhotoActivity(BaseDataItem baseDataItem, Activity activity, PhotoPageFragment r9, String str, int i) {
        int i2;
        int i3;
        ActivityOptionsCompat activityOptionsCompat;
        Drawable drawable;
        View findViewById = r9.getView().findViewById(R.id.photo_pager);
        ImageView photoView = r9.getPhotoView();
        float[] fArr = new float[9];
        if (photoView == null || (drawable = photoView.getDrawable()) == null) {
            i3 = 0;
            i2 = 0;
        } else {
            i2 = drawable.getIntrinsicWidth();
            i3 = drawable.getIntrinsicHeight();
            photoView.getImageMatrix().getValues(fArr);
        }
        Intent intent = new Intent(str);
        intent.setPackage("com.miui.extraphoto");
        intent.putExtra("extra_screen_brightness", activity.getWindow().getAttributes().screenBrightness);
        intent.putExtra("extra_path", baseDataItem.getOriginalPath());
        Bundle bundle = null;
        if (i2 <= 0 || i3 <= 0 || activity.getResources().getConfiguration().orientation != 1 || !com.miui.gallery.compat.view.WindowCompat.supportActivityTransitions(activity.getWindow())) {
            activityOptionsCompat = null;
        } else {
            intent.putExtra("extra_preview_image_width", i2);
            intent.putExtra("extra_preview_image_height", i3);
            intent.putExtra("extra_preview_image_matrix", fArr);
            intent.putExtra("extra_has_transition", true);
            activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, Pair.create(findViewById, "tag_transition_view"), Pair.create(findViewById, "tag_transition_view_menu"));
        }
        if (activityOptionsCompat != null) {
            bundle = activityOptionsCompat.toBundle();
        }
        if (r9 != 0) {
            try {
                activity.startActivityFromFragment(r9, intent, i, bundle);
            } catch (Exception e) {
                Log.e("IntentUtil", "start extra photo activity error\n", (Object) e);
            }
        } else {
            activity.startActivityForResult(intent, i, bundle);
        }
    }

    public static void startFancyColorAction(BaseDataItem baseDataItem, Fragment fragment, boolean z) {
        startDualActionInternal(baseDataItem, fragment, z, "com.miui.extraphoto.action.FANCY_COLOR");
    }

    public static void startFreeViewAction(BaseDataItem baseDataItem, Fragment fragment, boolean z) {
        startDualActionInternal(baseDataItem, fragment, z, "com.miui.extraphoto.action.VIEW_3D");
    }

    public static boolean startMeituCollageAction(Activity activity, List<Uri> list, int i) {
        if (!(activity == null || list == null)) {
            ArrayList arrayList = new ArrayList(list.size());
            for (Uri next : list) {
                if ("file".equals(next.getScheme())) {
                    next = GalleryOpenProvider.translateToContent(next.getPath());
                }
                arrayList.add(next);
                if ("com.miui.gallery.open".equals(next.getAuthority())) {
                    activity.grantUriPermission("com.mt.mtxx.mtxx", next, 1);
                }
            }
            Intent intent = new Intent("com.xiaomi.intent.action.PUZZLE");
            intent.putExtra("meitu_edit_result_path", StorageUtils.getCreativeDirectory());
            intent.putExtra("edit_from_xiaomi_album", true);
            intent.putParcelableArrayListExtra("extra_key_out_puzzle_image_info", arrayList);
            intent.setType("image/*");
            intent.setPackage("com.mt.mtxx.mtxx");
            if (MiscUtil.isIntentSupported(intent)) {
                if (i > 0) {
                    try {
                        activity.startActivityForResult(intent, i);
                    } catch (Exception e) {
                        Log.w("IntentUtil", (Throwable) e);
                    }
                } else {
                    activity.startActivity(intent);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean startMeituEditAction(BaseDataItem baseDataItem, Activity activity, PhotoPageFragment photoPageFragment) {
        Uri contentUriForExternal;
        if (baseDataItem == null || activity == null || photoPageFragment == null || (contentUriForExternal = baseDataItem.getContentUriForExternal()) == null) {
            return false;
        }
        String path = contentUriForExternal.getPath();
        String parentFolderPath = FileUtils.getParentFolderPath(path);
        Uri translateToContent = GalleryOpenProvider.translateToContent(path);
        Intent intent = new Intent("com.xiaomi.intent.action.BEAUTY");
        intent.setDataAndType(translateToContent, "image/*");
        intent.setFlags(1);
        intent.putExtra("edit_from_xiaomi_album", true);
        intent.putExtra("meitu_edit_result_path", parentFolderPath);
        intent.putExtra("android.intent.extra.STREAM", translateToContent);
        intent.setPackage("com.mt.mtxx.mtxx");
        if (MiscUtil.isIntentSupported(intent)) {
            try {
                activity.grantUriPermission("com.mt.mtxx.mtxx", translateToContent, 1);
                activity.startActivityForResult(intent, 51);
                return true;
            } catch (Exception e) {
                Log.w("IntentUtil", (Throwable) e);
                startEditAction(baseDataItem, activity, photoPageFragment, false);
                return true;
            }
        }
        return false;
    }

    public static void startMotionPhotoAction(BaseDataItem baseDataItem, Activity activity, PhotoPageFragment photoPageFragment) {
        startExtraPhotoActivity(baseDataItem, activity, photoPageFragment, "com.miui.extraphoto.action.MOTION_PHOTO_REPICK", 50);
    }

    public static void startPhotoMovie(Activity activity, List<MediaInfo> list, long j, int i, String str, String str2) {
        if (MovieLibraryLoaderHelper.getInstance().checkAbleOrDownload(activity, $$Lambda$IntentUtil$QgCHVbMrgHayG_yxP4xGcmRerEI.INSTANCE) && MiscUtil.isValid(list)) {
            int i2 = 0;
            ClipData clipData = null;
            for (int i3 = 0; i3 < list.size() && i2 < 20; i3++) {
                if (list.get(i3) != null) {
                    Uri translateToContent = GalleryOpenProvider.translateToContent(list.get(i3).getUri());
                    String sha1 = list.get(i3).getSha1();
                    if (clipData == null) {
                        clipData = new ClipData("data", new String[]{"image/*", "text/uri-list"}, new ClipData.Item(sha1, (Intent) null, translateToContent));
                    } else {
                        clipData.addItem(new ClipData.Item(sha1, (Intent) null, translateToContent));
                    }
                    i2++;
                }
            }
            Intent intent = new Intent(activity, MovieActivity.class);
            intent.putExtra("movie_extra_preview_mode", true);
            intent.putExtra("card_id", j);
            intent.putExtra("card_title", str);
            intent.putExtra("card_sub_title", str2);
            intent.putExtra("movie_extra_template", i);
            intent.setClipData(clipData);
            activity.startActivity(intent);
        }
    }

    public static void startRefocusAction(BaseDataItem baseDataItem, Fragment fragment, boolean z) {
        startDualActionInternal(baseDataItem, fragment, z, "com.miui.extraphoto.action.VIEW_REFOCUS");
    }

    public static boolean startSpeechInput(Activity activity, int i, boolean z, ArrayList<String> arrayList) {
        try {
            Intent intent = new Intent("com.xiaomi.mibrain.action.RECOGNIZE_SPEECH");
            intent.setPackage("com.xiaomi.mibrain.speech");
            intent.putExtra("miref", activity.getPackageName());
            intent.putExtra("appId", "2882303761517492012");
            intent.putExtra("appToken", "527730249789");
            intent.putExtra("client_id", "2882303761517492012");
            intent.putExtra("api_key", "NwRthN_W6eI4dvXX47gZIlZdwBoDMT5t2Xu-7uciaqw");
            intent.putExtra("sign_secret", "wyRVnz7UEHQqNcwYAvANxiXBL25taWWPsSQGHbWIcNcEVYrU72NO0MBZjkqXuOk-iOXXiAnrMlZo78sJDhFreg");
            intent.putExtra("needNlp", z);
            if (MiscUtil.isValid(arrayList)) {
                intent.putStringArrayListExtra("suggestedSpeechTexts", arrayList);
            }
            activity.startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException unused) {
            Log.e("IntentUtil", "Start speech input error");
            return false;
        }
    }
}
