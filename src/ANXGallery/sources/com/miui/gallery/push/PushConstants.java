package com.miui.gallery.push;

import android.text.TextUtils;

public interface PushConstants {

    public enum MessageScope {
        DEBUG("debug"),
        RELEASE("release");
        
        private String value;

        private MessageScope(String str) {
            this.value = str;
        }

        public static MessageScope getScope(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            for (MessageScope messageScope : values()) {
                if (str.equals(messageScope.value)) {
                    return messageScope;
                }
            }
            return null;
        }
    }

    public enum MessageType {
        SYNC("sync"),
        DIRECT("direct");
        
        private String value;

        private MessageType(String str) {
            this.value = str;
        }

        public static MessageType getType(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            for (MessageType messageType : values()) {
                if (str.equalsIgnoreCase(messageType.value)) {
                    return messageType;
                }
            }
            return null;
        }
    }
}
