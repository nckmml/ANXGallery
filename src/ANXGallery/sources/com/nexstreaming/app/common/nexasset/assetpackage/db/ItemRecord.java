package com.nexstreaming.app.common.nexasset.assetpackage.db;

import com.nexstreaming.app.common.nexasset.assetpackage.ItemCategory;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemType;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.norm.b;
import java.util.Collections;
import java.util.Map;

public class ItemRecord extends b implements f {
    public long _id;
    @b.c
    @b.d
    public AssetPackageRecord assetPackageRecord;
    public String filePath;
    public boolean hidden;
    public String iconPath;
    public ItemCategory itemCategory;
    @b.g
    @b.e
    public String itemId;
    public ItemType itemType;
    public Map<String, String> label;
    public String packageURI;
    public String sampleText;
    public String thumbPath;

    public com.nexstreaming.app.common.nexasset.assetpackage.b getAssetPackage() {
        return this.assetPackageRecord;
    }

    public ItemCategory getCategory() {
        return this.itemCategory;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getIconPath() {
        return this.iconPath;
    }

    public String getId() {
        return this.itemId;
    }

    public Map<String, String> getLabel() {
        Map<String, String> map = this.label;
        return (map == null || map.isEmpty()) ? Collections.singletonMap("en", this.itemId) : this.label;
    }

    public String getPackageURI() {
        return this.packageURI;
    }

    public String getSampleText() {
        return this.sampleText;
    }

    public String getThumbPath() {
        return this.thumbPath;
    }

    public ItemType getType() {
        return this.itemType;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public String toString() {
        return "ItemRecord{_id=" + this._id + ", itemId='" + this.itemId + '\'' + ", packageURI='" + this.packageURI + '\'' + ", filePath='" + this.filePath + '\'' + ", iconPath='" + this.iconPath + '\'' + ", thumbPath='" + this.thumbPath + '\'' + ", label=" + this.label + ", itemType=" + this.itemType + ", itemCategory=" + this.itemCategory + ", assetPackageRecord=" + this.assetPackageRecord + '}';
    }
}
