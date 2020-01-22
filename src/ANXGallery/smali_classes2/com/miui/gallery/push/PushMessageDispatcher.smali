.class public Lcom/miui/gallery/push/PushMessageDispatcher;
.super Ljava/lang/Object;
.source "PushMessageDispatcher.java"


# direct methods
.method public static dispatch(Landroid/content/Context;Lcom/xiaomi/mipush/sdk/MiPushMessage;)V
    .locals 8

    invoke-virtual {p1}, Lcom/xiaomi/mipush/sdk/MiPushMessage;->getContent()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    const-string v2, "mipush"

    const-string v3, "PushMessageDispatcher"

    if-eqz v1, :cond_0

    const-string p0, "Message content is empty"

    invoke-static {v3, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    const-string p0, "message_content_is_empty"

    invoke-static {v2, p0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    invoke-static {v0}, Lcom/miui/gallery/push/GalleryPushMessage;->fromJson(Ljava/lang/String;)Lcom/miui/gallery/push/GalleryPushMessage;

    move-result-object v1

    if-nez v1, :cond_1

    const-string p0, "Parse message content failed: %s"

    invoke-static {v3, p0, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const-string p0, "message_content_parse_failed"

    invoke-static {v2, p0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_1
    invoke-virtual {v1}, Lcom/miui/gallery/push/GalleryPushMessage;->getMessageScope()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/push/PushConstants$MessageScope;->getScope(Ljava/lang/String;)Lcom/miui/gallery/push/PushConstants$MessageScope;

    move-result-object v0

    sget-object v4, Lcom/miui/gallery/push/PushConstants$MessageScope;->RELEASE:Lcom/miui/gallery/push/PushConstants$MessageScope;

    if-eq v0, v4, :cond_3

    sget-object v4, Lcom/miui/gallery/push/PushConstants$MessageScope;->DEBUG:Lcom/miui/gallery/push/PushConstants$MessageScope;

    if-ne v0, v4, :cond_2

    sget-boolean v0, Lcom/miui/gallery/util/BuildUtil;->IS_DEBUG_BUILD:Z

    if-nez v0, :cond_3

    :cond_2
    invoke-virtual {v1}, Lcom/miui/gallery/push/GalleryPushMessage;->getMessageScope()Ljava/lang/String;

    move-result-object p0

    const-string p1, "Message scope does not match: %s"

    invoke-static {v3, p1, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-instance p0, Ljava/util/HashMap;

    invoke-direct {p0}, Ljava/util/HashMap;-><init>()V

    invoke-virtual {v1}, Lcom/miui/gallery/push/GalleryPushMessage;->getMessageScope()Ljava/lang/String;

    move-result-object p1

    const-string v0, "msg_scope"

    invoke-virtual {p0, v0, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "unknown_message_scope_doesnt_match"

    invoke-static {v2, p1, p0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void

    :cond_3
    invoke-virtual {v1}, Lcom/miui/gallery/push/GalleryPushMessage;->getType()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/push/PushConstants$MessageType;->getType(Ljava/lang/String;)Lcom/miui/gallery/push/PushConstants$MessageType;

    move-result-object v0

    if-nez v0, :cond_4

    invoke-virtual {v1}, Lcom/miui/gallery/push/GalleryPushMessage;->getType()Ljava/lang/String;

    move-result-object p0

    const-string p1, "Unknown message type: %s"

    invoke-static {v3, p1, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-instance p0, Ljava/util/HashMap;

    invoke-direct {p0}, Ljava/util/HashMap;-><init>()V

    invoke-virtual {v1}, Lcom/miui/gallery/push/GalleryPushMessage;->getType()Ljava/lang/String;

    move-result-object p1

    const-string v0, "msg_type"

    invoke-virtual {p0, v0, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "unknown_message_type"

    invoke-static {v2, p1, p0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void

    :cond_4
    invoke-virtual {p1}, Lcom/xiaomi/mipush/sdk/MiPushMessage;->getUserAccount()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    const/4 v5, 0x0

    if-eqz v4, :cond_5

    move-object p1, v5

    goto :goto_0

    :cond_5
    invoke-virtual {p1}, Lcom/xiaomi/mipush/sdk/MiPushMessage;->getUserAccount()Ljava/lang/String;

    move-result-object p1

    :goto_0
    invoke-static {}, Lcom/miui/gallery/cloud/AccountCache;->getAccount()Landroid/accounts/Account;

    move-result-object v4

    if-eqz v4, :cond_6

    invoke-static {}, Lcom/miui/gallery/cloud/AccountCache;->getAccount()Landroid/accounts/Account;

    move-result-object v4

    iget-object v4, v4, Landroid/accounts/Account;->name:Ljava/lang/String;

    invoke-virtual {v4}, Ljava/lang/String;->getBytes()[B

    move-result-object v4

    invoke-static {v4}, Lcom/miui/gallery/util/Encode;->SHA1Encode([B)Ljava/lang/String;

    move-result-object v5

    :cond_6
    const/4 v4, 0x2

    const/4 v6, 0x1

    if-eqz p1, :cond_7

    invoke-static {p1, v5}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v7

    if-nez v7, :cond_7

    const-string p0, "UserAccount doesn\'t match, skip handle"

    invoke-static {v3, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    new-instance p0, Ljava/util/HashMap;

    invoke-direct {p0}, Ljava/util/HashMap;-><init>()V

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    new-array v1, v4, [Ljava/lang/Object;

    const/4 v3, 0x0

    aput-object p1, v1, v3

    aput-object v5, v1, v6

    const-string p1, "%s_%s"

    invoke-static {v0, p1, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "pushAccount_localAccount"

    invoke-interface {p0, v0, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "push_user_account_doesnt_match"

    invoke-static {v2, p1, p0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void

    :cond_7
    invoke-virtual {v1}, Lcom/miui/gallery/push/GalleryPushMessage;->getBusinessModule()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/push/MessageHandlerFactory;->create(Ljava/lang/String;)Lcom/miui/gallery/push/messagehandler/MessageHandler;

    move-result-object v2

    if-nez v2, :cond_8

    const-string p0, "MessageHandler is undefined: %s"

    invoke-static {v3, p0, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void

    :cond_8
    sget-object p1, Lcom/miui/gallery/push/PushMessageDispatcher$1;->$SwitchMap$com$miui$gallery$push$PushConstants$MessageType:[I

    invoke-virtual {v0}, Lcom/miui/gallery/push/PushConstants$MessageType;->ordinal()I

    move-result v0

    aget p1, p1, v0

    if-eq p1, v6, :cond_a

    if-eq p1, v4, :cond_9

    goto :goto_1

    :cond_9
    invoke-virtual {v2, p0, v1}, Lcom/miui/gallery/push/messagehandler/MessageHandler;->handleDirect(Landroid/content/Context;Lcom/miui/gallery/push/GalleryPushMessage;)V

    goto :goto_1

    :cond_a
    invoke-virtual {v2, p0, v1}, Lcom/miui/gallery/push/messagehandler/MessageHandler;->handlePull(Landroid/content/Context;Lcom/miui/gallery/push/GalleryPushMessage;)V

    :goto_1
    return-void
.end method
