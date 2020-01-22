package com.miui.gallery.push;

import android.content.Context;
import android.text.TextUtils;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.push.PushConstants;
import com.miui.gallery.push.messagehandler.MessageHandler;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.Encode;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.xiaomi.mipush.sdk.MiPushMessage;
import java.util.HashMap;
import java.util.Locale;

public class PushMessageDispatcher {

    /* renamed from: com.miui.gallery.push.PushMessageDispatcher$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$push$PushConstants$MessageType = new int[PushConstants.MessageType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$gallery$push$PushConstants$MessageType[PushConstants.MessageType.SYNC.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$push$PushConstants$MessageType[PushConstants.MessageType.DIRECT.ordinal()] = 2;
        }
    }

    public static void dispatch(Context context, MiPushMessage miPushMessage) {
        String content = miPushMessage.getContent();
        if (TextUtils.isEmpty(content)) {
            Log.e("PushMessageDispatcher", "Message content is empty");
            GallerySamplingStatHelper.recordErrorEvent("mipush", "message_content_is_empty");
            return;
        }
        GalleryPushMessage fromJson = GalleryPushMessage.fromJson(content);
        if (fromJson == null) {
            Log.e("PushMessageDispatcher", "Parse message content failed: %s", (Object) content);
            GallerySamplingStatHelper.recordErrorEvent("mipush", "message_content_parse_failed");
            return;
        }
        PushConstants.MessageScope scope = PushConstants.MessageScope.getScope(fromJson.getMessageScope());
        if (scope == PushConstants.MessageScope.RELEASE || (scope == PushConstants.MessageScope.DEBUG && BuildUtil.IS_DEBUG_BUILD)) {
            PushConstants.MessageType type = PushConstants.MessageType.getType(fromJson.getType());
            if (type == null) {
                Log.e("PushMessageDispatcher", "Unknown message type: %s", (Object) fromJson.getType());
                HashMap hashMap = new HashMap();
                hashMap.put("msg_type", fromJson.getType());
                GallerySamplingStatHelper.recordErrorEvent("mipush", "unknown_message_type", hashMap);
                return;
            }
            String str = null;
            String userAccount = TextUtils.isEmpty(miPushMessage.getUserAccount()) ? null : miPushMessage.getUserAccount();
            if (AccountCache.getAccount() != null) {
                str = Encode.SHA1Encode(AccountCache.getAccount().name.getBytes());
            }
            if (userAccount == null || TextUtils.equals(userAccount, str)) {
                String businessModule = fromJson.getBusinessModule();
                MessageHandler create = MessageHandlerFactory.create(businessModule);
                if (create == null) {
                    Log.e("PushMessageDispatcher", "MessageHandler is undefined: %s", (Object) businessModule);
                    return;
                }
                int i = AnonymousClass1.$SwitchMap$com$miui$gallery$push$PushConstants$MessageType[type.ordinal()];
                if (i == 1) {
                    create.handlePull(context, fromJson);
                } else if (i == 2) {
                    create.handleDirect(context, fromJson);
                }
            } else {
                Log.e("PushMessageDispatcher", "UserAccount doesn't match, skip handle");
                HashMap hashMap2 = new HashMap();
                hashMap2.put("pushAccount_localAccount", String.format(Locale.US, "%s_%s", new Object[]{userAccount, str}));
                GallerySamplingStatHelper.recordErrorEvent("mipush", "push_user_account_doesnt_match", hashMap2);
            }
        } else {
            Log.e("PushMessageDispatcher", "Message scope does not match: %s", (Object) fromJson.getMessageScope());
            HashMap hashMap3 = new HashMap();
            hashMap3.put("msg_scope", fromJson.getMessageScope());
            GallerySamplingStatHelper.recordErrorEvent("mipush", "unknown_message_scope_doesnt_match", hashMap3);
        }
    }
}
