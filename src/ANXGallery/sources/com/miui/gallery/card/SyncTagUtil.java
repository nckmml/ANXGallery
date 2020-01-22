package com.miui.gallery.card;

import android.accounts.Account;
import android.content.ContentValues;
import com.miui.gallery.dao.GalleryEntityManager;
import com.miui.gallery.dao.base.Entity;
import com.miui.gallery.util.MiscUtil;
import java.util.List;
import java.util.Locale;

public class SyncTagUtil {
    public static void ensureAccount(Account account) {
        if (account != null && !MiscUtil.isValid(GalleryEntityManager.getInstance().query(SyncTag.class, getAcountSelection(account), (String[]) null))) {
            GalleryEntityManager.getInstance().insert((Entity) new SyncTag(account));
        }
    }

    private static String getAcountSelection(Account account) {
        return "accountName = '" + account.name + "' AND " + "accountType" + " = '" + account.type + "'";
    }

    public static String getCardSyncInfo(Account account) {
        SyncTag syncTagByAccount = getSyncTagByAccount(account);
        return syncTagByAccount != null ? syncTagByAccount.getCardSyncInfo() : "";
    }

    public static long getCardSyncTag(Account account) {
        SyncTag syncTagByAccount = getSyncTagByAccount(account);
        if (syncTagByAccount != null) {
            return syncTagByAccount.getCardSyncTag();
        }
        return 0;
    }

    private static SyncTag getSyncTagByAccount(Account account) {
        if (account == null) {
            return null;
        }
        Class<SyncTag> cls = SyncTag.class;
        List<SyncTag> query = GalleryEntityManager.getInstance().query(cls, getAcountSelection(account), (String[]) null, (String) null, String.format(Locale.US, "%s,%s", new Object[]{0, 1}));
        if (MiscUtil.isValid(query)) {
            return query.get(0);
        }
        return null;
    }

    public static void setCardSyncInfo(Account account, String str) {
        if (account != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cardSyncInfo", str);
            GalleryEntityManager.getInstance().update(SyncTag.class, contentValues, getAcountSelection(account), (String[]) null);
        }
    }

    public static void setCardSyncTag(Account account, long j) {
        if (account != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cardSyncTag", Long.valueOf(j));
            GalleryEntityManager.getInstance().update(SyncTag.class, contentValues, getAcountSelection(account), (String[]) null);
        }
    }
}
