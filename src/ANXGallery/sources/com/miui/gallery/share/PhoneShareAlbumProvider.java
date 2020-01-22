package com.miui.gallery.share;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.share.AlbumShareOperations;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import java.util.HashMap;

public class PhoneShareAlbumProvider {
    private static PhoneShareAlbumProvider sInstance;
    private int[] addSharerByWhich;

    private PhoneShareAlbumProvider() {
        initDialogItems();
    }

    public static synchronized PhoneShareAlbumProvider getInstance() {
        PhoneShareAlbumProvider phoneShareAlbumProvider;
        synchronized (PhoneShareAlbumProvider.class) {
            if (sInstance == null) {
                sInstance = new PhoneShareAlbumProvider();
            }
            phoneShareAlbumProvider = sInstance;
        }
        return phoneShareAlbumProvider;
    }

    private String[] getShareItemTexts(Context context) {
        if (context == null) {
            return null;
        }
        int length = this.addSharerByWhich.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            int i2 = this.addSharerByWhich[i];
            if (i2 == 1) {
                strArr[i] = context.getString(R.string.by_sms);
            } else if (i2 != 2) {
                Log.e("PhoneShareAlbumProvider", "not handled operation type" + this.addSharerByWhich[i]);
            } else {
                strArr[i] = context.getString(R.string.by_others);
            }
        }
        return strArr;
    }

    private void initDialogItems() {
        if (BuildUtil.isPad()) {
            this.addSharerByWhich = new int[]{2};
            return;
        }
        this.addSharerByWhich = new int[]{1, 2};
    }

    private void recordAddSharerCountEvent(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("mode", str);
        GallerySamplingStatHelper.recordCountEvent("album", "add_sharer", hashMap);
    }

    public int getAddSharerDialogItemsLength() {
        int[] iArr = this.addSharerByWhich;
        if (iArr == null) {
            return 0;
        }
        return iArr.length;
    }

    public Intent getShareUrlIntent(Activity activity, String str, String str2, String str3, boolean z) {
        Intent intent = new Intent("android.intent.action.SEND");
        String string = activity.getString(R.string.quotation, new Object[]{str});
        String string2 = activity.getString(R.string.share_public_url_text, new Object[]{string, str3});
        if (!TextUtils.isEmpty(str3)) {
            str2 = str3;
        }
        String string3 = activity.getString(R.string.share_public_url_text, new Object[]{string, "<a href=\"" + str3 + "\">" + str2 + "</a>"});
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head><body> <font size=12>");
        sb.append(string3);
        sb.append("</font>");
        sb.append("</body></html>");
        String sb2 = sb.toString();
        intent.putExtra("android.intent.extra.TEXT", string2);
        intent.putExtra("android.intent.extra.HTML_TEXT", sb2);
        intent.setType("text/*");
        return Intent.createChooser(intent, activity.getString(R.string.send_public_url));
    }

    public void shareByOther(Activity activity, String str, AlbumShareOperations.OutgoingInvitation outgoingInvitation) {
        activity.startActivity(getShareUrlIntent(activity, str, outgoingInvitation.getDescription(), outgoingInvitation.getUrl(), false));
    }

    public void shareBySms(String str, Activity activity) {
        if (TextUtils.isEmpty(str)) {
            UIHelper.toast((int) R.string.request_sms_url_failed);
            return;
        }
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("smsto:"));
        intent.putExtra("sms_body", str);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("PhoneShareAlbumProvider", "sms intent not resolved");
            Log.e("PhoneShareAlbumProvider", (Throwable) e);
        }
    }

    public void shareOperation(Activity activity, int i, AlbumShareOperations.OutgoingInvitation outgoingInvitation, String str) {
        int[] iArr = this.addSharerByWhich;
        if (i < iArr.length) {
            int i2 = iArr[i];
            if (i2 == 1) {
                shareBySms(outgoingInvitation.toMessage(), activity);
                recordAddSharerCountEvent("sms");
            } else if (i2 == 2) {
                shareByOther(activity, str, outgoingInvitation);
                recordAddSharerCountEvent("other");
            } else {
                throw new UnsupportedOperationException("Bad category, which=" + i);
            }
        }
    }

    public void showAddSharerDialog(Activity activity, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(activity).setTitle(R.string.add_sharer).setSingleChoiceItems(getShareItemTexts(activity), -1, onClickListener).setCancelable(true).show();
    }

    public void showKickUserDialog(Activity activity, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(activity).setTitle(R.string.kick_owner_user_title).setCancelable(true).setPositiveButton(R.string.kick_owner_user_positive_button_text, onClickListener).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
    }
}
