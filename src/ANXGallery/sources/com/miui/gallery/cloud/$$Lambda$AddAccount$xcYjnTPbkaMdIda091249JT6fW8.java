package com.miui.gallery.cloud;

import android.database.Cursor;
import com.miui.gallery.util.SafeDBUtil;

/* renamed from: com.miui.gallery.cloud.-$$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8 implements SafeDBUtil.QueryHandler {
    public static final /* synthetic */ $$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8 INSTANCE = new $$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8();

    private /* synthetic */ $$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8() {
    }

    public final Object handle(Cursor cursor) {
        return AddAccount.lambda$queryExistSecretFiles$54(cursor);
    }
}
