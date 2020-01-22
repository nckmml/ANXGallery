package com.nexstreaming.app.common.nexasset.store;

import java.util.HashMap;
import java.util.Map;

public class VendorList {
    private static final String KineMasterPackageName = "com.nexstreaming.app.kinemasterfree";
    private static final String LOG_TAG = "VendorList";
    private static final String assetStoreAppGlobalPackageName = "com.nexstreaming.app.vasset.global";
    private static final String assetStoreAppServiceName = ".AssetStoreService";
    private static final String assetStoreAppTestPackageName = "com.nexstreaming.assetstore.test1";
    private static VendorList instance;
    private Map<String, vendor> m_builtinVendors;

    public static class vendor {
        private final String assetStoreAppPackageName;
        private final String assetStoreAppServiceName;
        private final String kineMasterPackageName;

        vendor(String str, String str2, String str3) {
            this.assetStoreAppPackageName = str;
            this.assetStoreAppServiceName = str2;
            this.kineMasterPackageName = str3;
        }

        public String getAssetStoreAppPackageName() {
            return this.assetStoreAppPackageName;
        }

        public String getAssetStoreAppServiceName() {
            return this.assetStoreAppServiceName;
        }

        public String getKineMasterPackageName() {
            return this.kineMasterPackageName;
        }
    }

    static Map<String, vendor> builtinVendor() {
        HashMap hashMap = new HashMap();
        hashMap.put("NexStreaming", new vendor(assetStoreAppGlobalPackageName, "com.nexstreaming.app.vasset.global.AssetStoreService", KineMasterPackageName));
        hashMap.put("LGE", new vendor(assetStoreAppGlobalPackageName, "com.nexstreaming.app.vasset.global.AssetStoreService", KineMasterPackageName));
        hashMap.put("ZTE", new vendor("com.nexstreaming.app.assetstore.zte.china", "com.nexstreaming.app.assetstore.zte.china.AssetStoreService", KineMasterPackageName));
        hashMap.put("ZTEGlobal", new vendor("com.nexstreaming.app.assetstore.zte.global", "com.nexstreaming.app.assetstore.zte.global.AssetStoreService", KineMasterPackageName));
        hashMap.put("TINNO", new vendor("com.nexstreaming.app.assetstore.tinno.china", "com.nexstreaming.app.assetstore.tinno.china.AssetStoreService", KineMasterPackageName));
        hashMap.put("TINNOGlobal", new vendor("com.nexstreaming.app.assetstore.tinno.global", "com.nexstreaming.app.assetstore.tinno.global.AssetStoreService", KineMasterPackageName));
        hashMap.put("SEERSTECH", new vendor("com.nexstreaming.app.assetstore.seerstech.global", "com.nexstreaming.app.assetstore.seerstech.global.AssetStoreService", KineMasterPackageName));
        hashMap.put("APPChina", new vendor("com.nexstreaming.app.assetstore.sp.china", "com.nexstreaming.app.assetstore.sp.china.AssetStoreService", KineMasterPackageName));
        hashMap.put("APPGlobal", new vendor("com.nexstreaming.app.assetstore.sp.global", "com.nexstreaming.app.assetstore.sp.global.AssetStoreService", KineMasterPackageName));
        hashMap.put("XIAOMI", new vendor("com.nexstreaming.app.assetstore.xiaomi.china", "com.nexstreaming.app.assetstore.xiaomi.china.AssetStoreService", KineMasterPackageName));
        hashMap.put("XIAOMIGlobal", new vendor("com.nexstreaming.app.assetstore.xiaomi.global", "com.nexstreaming.app.assetstore.xiaomi.global.AssetStoreService", KineMasterPackageName));
        return hashMap;
    }

    public static VendorList getInstance() {
        if (instance == null) {
            instance = new VendorList();
        }
        return instance;
    }

    public String getAssetStoreAppPackageName(String str) {
        init();
        vendor vendor2 = this.m_builtinVendors.get(str);
        return vendor2 == null ? assetStoreAppGlobalPackageName : vendor2.getAssetStoreAppPackageName();
    }

    public String getAssetStoreAppServiceName(String str) {
        init();
        vendor vendor2 = this.m_builtinVendors.get(str);
        return vendor2 == null ? "com.nexstreaming.app.vasset.global.AssetStoreService" : vendor2.getAssetStoreAppServiceName();
    }

    public String getKineMasterPackageName(String str) {
        init();
        vendor vendor2 = this.m_builtinVendors.get(str);
        return vendor2 == null ? KineMasterPackageName : vendor2.getKineMasterPackageName();
    }

    public void init() {
        if (this.m_builtinVendors == null) {
            this.m_builtinVendors = builtinVendor();
        }
    }
}
