package com.nexstreaming.app.common.nexasset.assetpackage.db;

import com.nexstreaming.app.common.nexasset.assetpackage.a;
import com.nexstreaming.app.common.norm.b;

public class CategoryRecord extends b implements a {
    public long _id;
    public String categoryIconURL;
    @b.g
    @b.e
    public long categoryId;
    public String categoryName;
    public String categoryURL;

    public String getCategoryAlias() {
        return this.categoryName;
    }

    public String getCategoryIconURL() {
        return this.categoryIconURL;
    }

    public long getCategoryId() {
        return this.categoryId;
    }

    public String getCategoryURL() {
        return this.categoryURL;
    }
}
