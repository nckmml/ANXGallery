.class public Lcom/miui/gallery/hybrid/hybridclient/HybridClientFactory;
.super Ljava/lang/Object;
.source "HybridClientFactory.java"


# direct methods
.method public static createHybridClient(Landroid/content/Context;Landroid/content/Intent;)Lcom/miui/gallery/hybrid/hybridclient/HybridClient;
    .locals 4

    const/4 v0, 0x0

    if-eqz p0, :cond_8

    if-nez p1, :cond_0

    goto/16 :goto_0

    :cond_0
    invoke-virtual {p1}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v1

    const-string v2, "android.intent.action.VIEW"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    const-string v3, "url"

    if-eqz v2, :cond_2

    invoke-virtual {p1}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object v0

    invoke-virtual {v0, v3}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-virtual {p1}, Landroid/content/Intent;->getDataString()Ljava/lang/String;

    move-result-object v0

    :cond_1
    new-instance p1, Lcom/miui/gallery/hybrid/hybridclient/GalleryHybridClient;

    invoke-direct {p1, p0, v0}, Lcom/miui/gallery/hybrid/hybridclient/GalleryHybridClient;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    return-object p1

    :cond_2
    const-string v2, "com.miui.gallery.action.VIEW_WEB"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    invoke-virtual {p1, v3}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/hybrid/hybridclient/GalleryHybridClient;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/hybrid/hybridclient/GalleryHybridClient;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    return-object v0

    :cond_3
    const-string v2, "com.miui.gallery.action.VIEW_WEB_RECOMMEND"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-virtual {p1, v3}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/hybrid/hybridclient/RecommendHybridClient;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/hybrid/hybridclient/RecommendHybridClient;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    return-object v0

    :cond_4
    const-string v2, "com.miui.gallery.action.VIEW_WEB_DEVICE_ID"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_5

    invoke-virtual {p1, v3}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/hybrid/hybridclient/DeviceIdHybridClient;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/hybrid/hybridclient/DeviceIdHybridClient;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    return-object v0

    :cond_5
    const-string v2, "com.miui.gallery.action.VIEW_WEB_LOGIN"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_6

    invoke-virtual {p1, v3}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/hybrid/hybridclient/LoginHybridClient;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/hybrid/hybridclient/LoginHybridClient;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    return-object v0

    :cond_6
    const-string v2, "com.miui.gallery.action.VIEW_WEB_OPERATION_STORY"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_7

    invoke-virtual {p1, v3}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    return-object v0

    :cond_7
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string p1, "Not supported action "

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const-string p1, "HybridClientFactory"

    invoke-static {p1, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    :cond_8
    :goto_0
    return-object v0
.end method
