package com.nexstreaming.app.common.nexasset.assetpackage.db;

import com.nexstreaming.app.common.nexasset.assetpackage.InstallSourceType;
import com.nexstreaming.app.common.norm.b;

public class InstallSourceRecord extends b {
    public long _id;
    @b.g
    @b.e
    public String installSourceId;
    public InstallSourceType installSourceType;
    public long installSourceVersion;
}
