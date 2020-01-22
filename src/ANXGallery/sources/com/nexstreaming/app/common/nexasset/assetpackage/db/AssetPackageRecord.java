package com.nexstreaming.app.common.nexasset.assetpackage.db;

import com.nexstreaming.app.common.nexasset.assetpackage.InstallSourceType;
import com.nexstreaming.app.common.nexasset.assetpackage.a;
import com.nexstreaming.app.common.nexasset.assetpackage.d;
import com.nexstreaming.app.common.norm.b;
import java.io.File;
import java.util.Map;

public class AssetPackageRecord extends b implements com.nexstreaming.app.common.nexasset.assetpackage.b {
    public long _id;
    public Map<String, String> assetDesc;
    @b.g
    @b.e
    public String assetId;
    public int assetIdx;
    public Map<String, String> assetName;
    public String assetUrl;
    public CategoryRecord category;
    @b.a(a = 12)
    public long expireTime;
    @b.c
    @b.d
    public InstallSourceRecord installSource;
    @b.a(a = 12)
    public long installedTime;
    public String localPath;
    @b.a(a = 13)
    public int minVersion;
    public String packageURI;
    @b.a(a = 13)
    public int packageVersion;
    public String priceType;
    public SubCategoryRecord subCategory;
    public String thumbPath;
    public String thumbUrl;

    public a getAssetCategory() {
        return this.category;
    }

    public Map<String, String> getAssetDesc() {
        return this.assetDesc;
    }

    public String getAssetId() {
        return this.assetId;
    }

    public int getAssetIdx() {
        return this.assetIdx;
    }

    public Map<String, String> getAssetName() {
        return this.assetName;
    }

    public d getAssetSubCategory() {
        return this.subCategory;
    }

    public String getAssetUrl() {
        return this.assetUrl;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public InstallSourceType getInstallSourceType() {
        InstallSourceRecord installSourceRecord = this.installSource;
        if (installSourceRecord == null) {
            return null;
        }
        return installSourceRecord.installSourceType;
    }

    public long getInstalledTime() {
        return this.installedTime;
    }

    public File getLocalPath() {
        String str = this.localPath;
        if (str == null) {
            return null;
        }
        return new File(str);
    }

    public int getMinVersion() {
        return this.minVersion;
    }

    public String getPackageURI() {
        return this.packageURI;
    }

    public int getPackageVersion() {
        return this.packageVersion;
    }

    public String getPriceType() {
        return this.priceType;
    }

    public String getThumbPath() {
        return this.thumbPath;
    }

    public String getThumbUrl() {
        return this.thumbUrl;
    }

    public String toString() {
        return "AssetPackageRecord{_id=" + this._id + ", assetId='" + this.assetId + '\'' + ", assetIdx=" + this.assetIdx + ", assetUrl='" + this.assetUrl + '\'' + ", thumbUrl='" + this.thumbUrl + '\'' + ", thumbPath='" + this.thumbPath + '\'' + ", priceType='" + this.priceType + '\'' + ", localPath='" + this.localPath + '\'' + ", category=" + this.category + ", subCategory=" + this.subCategory + ", assetName=" + this.assetName + ", assetDesc=" + this.assetDesc + ", packageURI='" + this.packageURI + '\'' + ", installSource=" + this.installSource + '}';
    }
}
