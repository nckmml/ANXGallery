package com.nexstreaming.nexeditorsdk;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.nexstreaming.app.common.nexasset.store.AssetLocalInstallDB;
import com.nexstreaming.app.common.nexasset.store.AssetStoreClient;
import com.nexstreaming.app.common.nexasset.store.VendorList;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.nexeditorsdk.exception.nexSDKException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class nexAssetStoreAppUtils {
    public static final int AssetStoreMimeType_Audio = 8;
    public static final int AssetStoreMimeType_Background = 32;
    public static final int AssetStoreMimeType_BeatTemplate = 1024;
    public static final int AssetStoreMimeType_DynamicCollage = 16384;
    public static final int AssetStoreMimeType_Effect = 2;
    public static final int AssetStoreMimeType_Extra = Integer.MIN_VALUE;
    public static final int AssetStoreMimeType_Filter = 16;
    public static final int AssetStoreMimeType_Font = 256;
    public static final int AssetStoreMimeType_IntegratedCollage = 4096;
    public static final int AssetStoreMimeType_Overlay = 64;
    public static final int AssetStoreMimeType_RenderItem = 128;
    public static final int AssetStoreMimeType_StaticCollage = 8192;
    public static final int AssetStoreMimeType_Template = 1;
    public static final int AssetStoreMimeType_TitleTemplate = 512;
    public static final int AssetStoreMimeType_Transition = 4;
    private static final int AssetStoreProtocolVersion = 1;
    @Deprecated
    public static final int AssetStoreServerDRAFT = 1;
    @Deprecated
    public static final int AssetStoreServerPRODUCTION = 3;
    @Deprecated
    public static final int AssetStoreServerSTAGING = 2;
    private static final String TAG = "nexAssetStoreAppUtils";
    private static final int assetStoreAppResultCode = 22153;
    private static int availableCategory = 0;
    private static boolean denyFeaturedList = false;
    private static final int getAssetStoreSDKLevel = 7;
    private static final String kAssetStoreAssetConnectServer = "AssetStore.intent.extra.ConnectServer";
    private static final String kAssetStoreAssetID = "AssetStore.intent.extra.AssetID";
    private static final String kAssetStoreAssetImageUrl = "AssetStore.intent.extra.AssetImageUrl";
    private static final String kAssetStoreAssetProtocolVersion = "AssetStore.intent.extra.ProtocolVersion";
    private static final String kAssetStoreAssetSDKLevel = "AssetStore.intent.extra.SDKLevel";
    private static final String kAssetStoreAvailableCategorys = "AssetStore.intent.extra.UiSet.Categorys";
    private static final String kAssetStoreDenyFeaturedList = "AssetStore.intent.extra.DenyFeaturedList";
    private static final String kAssetStoreLaunchMode = "AssetStore.intent.extra.Launch.Mode";
    private static final String kAssetStoreMarketId = "AssetStore.intent.extra.MarketId";
    private static final String kAssetStoreMimeType = "AssetStore.intent.extra.MimeType";
    private static final String kAssetStoreMimeTypeExtra = "AssetStore.intent.extra.MimeTypeExtra";
    private static final String kAssetStoreVendor = "AssetStore.intent.extra.Vendor";
    private static String marketId = "default2";
    private static String mimeTypeExtra;
    private static int moveCategory = 1;
    private static final Executor sInstallThreadPool = Executors.newSingleThreadExecutor();
    private static int serverType;
    private static String vendor = "NexStreaming";

    public static int getSDKLevel() {
        return 7;
    }

    public static boolean isInstalledAssetStoreApp(Context context) {
        if (context == null) {
            context = a.a().b();
        }
        try {
            return context.getPackageManager().getPackageInfo(VendorList.getInstance().getAssetStoreAppPackageName(vendor), 1).applicationInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isInstalledKineMaster(Context context) {
        if (context == null) {
            context = a.a().b();
        }
        try {
            return context.getPackageManager().getPackageInfo(VendorList.getInstance().getKineMasterPackageName(vendor), 1).applicationInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isServiceRunningCheck(Context context) {
        if (context == null) {
            context = a.a().b();
        }
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Reader.READ_DONE)) {
            if (VendorList.getInstance().getAssetStoreAppServiceName(vendor).equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUpdatedFeaturedList(int i, String str) {
        return AssetLocalInstallDB.isUpdatedFeaturedList(i, str);
    }

    static void makeConfigAsync() {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                AssetStoreClient.makeConfig();
                return null;
            }
        }.executeOnExecutor(sInstallThreadPool, new Void[0]);
    }

    public static void moveGooglePlayAssetStoreLink(Context context) {
        if (context == null) {
            context = a.a().b();
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(402653184);
        intent.setData(Uri.parse("market://details?id=" + VendorList.getInstance().getAssetStoreAppPackageName(vendor)));
        context.startActivity(intent);
    }

    public static void moveGooglePlayKineMaster(Context context) {
        if (context == null) {
            context = a.a().b();
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(402653184);
        intent.setData(Uri.parse("market://details?id=" + VendorList.getInstance().getKineMasterPackageName(vendor)));
        context.startActivity(intent);
    }

    public static int runAssetStoreApp(Activity activity, String str) {
        return runAssetStoreApp(activity, str, -1);
    }

    public static int runAssetStoreApp(Activity activity, String str, int i) {
        if (availableCategory != 0) {
            Intent intent = new Intent();
            intent.setPackage(activity.getPackageName());
            intent.setAction("com.nexstreaming.app.assetstore.start.application");
            if (str != null) {
                if (!str.startsWith("FL")) {
                    intent.putExtra(kAssetStoreAssetImageUrl, AssetLocalInstallDB.getInstance(a.a().b()).getThumbnailUrl(Integer.parseInt(str)));
                }
                intent.putExtra(kAssetStoreAssetID, str);
            } else {
                intent.putExtra(kAssetStoreMimeType, moveCategory);
            }
            int i2 = serverType;
            if (i2 > 0) {
                intent.putExtra(kAssetStoreAssetConnectServer, i2);
            }
            String str2 = marketId;
            if (str2 != null) {
                intent.putExtra(kAssetStoreMarketId, str2);
            }
            intent.putExtra(kAssetStoreVendor, vendor);
            intent.putExtra(kAssetStoreAssetSDKLevel, 7);
            intent.putExtra(kAssetStoreAssetProtocolVersion, 1);
            intent.putExtra(kAssetStoreAvailableCategorys, availableCategory);
            intent.putExtra(kAssetStoreDenyFeaturedList, denyFeaturedList);
            String str3 = mimeTypeExtra;
            if (str3 != null) {
                intent.putExtra(kAssetStoreMimeTypeExtra, str3);
            }
            if (vendor.startsWith("TINNO") || i > 0) {
                if (i > 0) {
                    intent.putExtra(kAssetStoreLaunchMode, i);
                    Log.d(TAG, "Set intent flag value=" + i);
                }
                String assetStoreAppPackageName = VendorList.getInstance().getAssetStoreAppPackageName(vendor);
                intent.setClassName(assetStoreAppPackageName, VendorList.getInstance().getAssetStoreAppPackageName(vendor) + ".EnterActivity");
                activity.startActivity(intent);
                return assetStoreAppResultCode;
            }
            String assetStoreAppPackageName2 = VendorList.getInstance().getAssetStoreAppPackageName(vendor);
            intent.setClassName(assetStoreAppPackageName2, VendorList.getInstance().getAssetStoreAppPackageName(vendor) + ".AssetStoreService");
            activity.startService(intent);
            return assetStoreAppResultCode;
        }
        throw new nexSDKException("must called 'setAvailableCategorys(int mimeTypes)'");
    }

    public static void saveFeaturedList(int i, String str) {
        AssetLocalInstallDB.saveFeaturedList(i, str);
    }

    public static void saveFeaturedThumbnail(int i, Bitmap bitmap) {
        AssetLocalInstallDB.saveFeaturedThumbnail(i, bitmap);
    }

    public static void sendAssetStoreAppServiceIntent(Context context) {
        Intent intent = new Intent();
        intent.setClassName(VendorList.getInstance().getAssetStoreAppPackageName(vendor), VendorList.getInstance().getAssetStoreAppServiceName(vendor));
        intent.putExtra(kAssetStoreVendor, vendor);
        intent.putExtra(kAssetStoreAssetSDKLevel, 7);
        intent.putExtra(kAssetStoreAssetProtocolVersion, 1);
        intent.putExtra(kAssetStoreDenyFeaturedList, denyFeaturedList);
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    public static void setAvailableCategorys(int i) {
        availableCategory = i;
    }

    public static void setDenyFeaturedList(boolean z) {
        denyFeaturedList = z;
    }

    public static void setMarketId(String str) {
        marketId = str;
    }

    public static void setMimeType(int i) {
        moveCategory = i;
    }

    public static void setMimeTypeExtra(String str) {
        mimeTypeExtra = str;
    }

    @Deprecated
    public static void setServer(int i) {
        serverType = i;
    }

    public static void setVendor(String str) {
        vendor = str;
    }

    public static String vendorName() {
        return vendor;
    }
}
