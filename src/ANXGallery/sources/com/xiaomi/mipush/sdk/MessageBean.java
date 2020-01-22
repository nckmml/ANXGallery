package com.xiaomi.mipush.sdk;

import android.text.TextUtils;

/* compiled from: OperatePushHelper */
class MessageBean {
    int count = 0;
    String messageId = "";

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MessageBean)) {
            return false;
        }
        MessageBean messageBean = (MessageBean) obj;
        return !TextUtils.isEmpty(messageBean.messageId) && messageBean.messageId.equals(this.messageId);
    }
}
