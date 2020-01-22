package com.google.protobuf;

class GeneratedMessageInfoFactory implements MessageInfoFactory {
    private static final GeneratedMessageInfoFactory instance = new GeneratedMessageInfoFactory();

    private GeneratedMessageInfoFactory() {
    }

    public static GeneratedMessageInfoFactory getInstance() {
        return instance;
    }

    public boolean isSupported(Class<?> cls) {
        return GeneratedMessageLite.class.isAssignableFrom(cls);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    public MessageInfo messageInfoFor(Class<?> r5) {
        if (GeneratedMessageLite.class.isAssignableFrom(r5)) {
            try {
                return (MessageInfo) GeneratedMessageLite.getDefaultInstance(r5.asSubclass(GeneratedMessageLite.class)).buildMessageInfo();
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for " + r5.getName(), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: " + r5.getName());
        }
    }
}
