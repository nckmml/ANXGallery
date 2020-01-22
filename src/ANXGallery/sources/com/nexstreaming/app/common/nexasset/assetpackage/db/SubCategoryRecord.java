package com.nexstreaming.app.common.nexasset.assetpackage.db;

import com.nexstreaming.app.common.nexasset.assetpackage.d;
import com.nexstreaming.app.common.norm.b;
import java.util.Map;

public class SubCategoryRecord extends b implements d {
    public long _id;
    public String subCategoryAlias;
    @b.g
    @b.e
    public long subCategoryId;
    public Map<String, String> subCategoryName;

    public String getSubCategoryAlias() {
        return this.subCategoryAlias;
    }

    public long getSubCategoryId() {
        return this.subCategoryId;
    }

    public Map<String, String> getSubCategoryName() {
        return this.subCategoryName;
    }
}
