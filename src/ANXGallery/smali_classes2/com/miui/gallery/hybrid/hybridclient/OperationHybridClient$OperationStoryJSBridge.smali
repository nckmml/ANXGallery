.class public Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;
.super Ljava/lang/Object;
.source "OperationHybridClient.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "OperationStoryJSBridge"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;


# direct methods
.method constructor <init>(Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public saveImage(Ljava/lang/String;)Z
    .locals 9
    .annotation runtime Landroid/webkit/JavascriptInterface;
    .end annotation

    const-string v0, "saveImage %s"

    const-string v1, "saveImage() failed %s"

    const-string v2, "OperationHybridClient"

    iget-object v3, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    const-string v4, "save_click"

    invoke-virtual {v3, v4}, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;->recordSaveEvent(Ljava/lang/String;)V

    iget-object v3, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    iget-object v3, v3, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;->mCurrentUrl:Ljava/lang/String;

    invoke-static {v3}, Lcom/miui/gallery/request/HostManager;->isInternalUrl(Ljava/lang/String;)Z

    move-result v3

    const/4 v4, 0x0

    if-nez v3, :cond_0

    return v4

    :cond_0
    const-class v3, Lcom/miui/gallery/hybrid/hybridclient/GalleryHybridClient$ImageInfo;

    invoke-static {p1, v3}, Lcom/miui/gallery/util/GsonUtils;->fromJson(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/hybrid/hybridclient/GalleryHybridClient$ImageInfo;

    if-eqz p1, :cond_3

    iget-object v3, p1, Lcom/miui/gallery/hybrid/hybridclient/GalleryHybridClient$ImageInfo;->data:Ljava/lang/String;

    if-eqz v3, :cond_3

    :try_start_0
    iget-object p1, p1, Lcom/miui/gallery/hybrid/hybridclient/GalleryHybridClient$ImageInfo;->data:Ljava/lang/String;

    invoke-static {p1, v4}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object p1

    array-length v3, p1

    invoke-static {p1, v4, v3}, Landroid/graphics/BitmapFactory;->decodeByteArray([BII)Landroid/graphics/Bitmap;

    move-result-object p1

    const/4 v3, 0x1

    if-eqz p1, :cond_2

    const-string v5, "save image from html,image size: %d X %d"

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v6

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v7

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-static {v2, v5, v6, v7}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    const-string v5, "yyyyMMdd_HHmmss"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    invoke-static {v5, v6, v7}, Landroid/text/format/DateFormat;->format(Ljava/lang/CharSequence;J)Ljava/lang/CharSequence;

    move-result-object v5

    sget-object v6, Ljava/util/Locale;->US:Ljava/util/Locale;

    const-string v7, "IMG_%s.jpg"

    new-array v8, v3, [Ljava/lang/Object;

    aput-object v5, v8, v4

    invoke-static {v6, v7, v8}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    new-instance v6, Ljava/io/File;

    invoke-static {}, Lcom/miui/gallery/util/StorageUtils;->getCreativeDirectory()Ljava/lang/String;

    move-result-object v7

    invoke-direct {v6, v7, v5}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v6}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v5

    new-instance v7, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge$1;

    invoke-direct {v7, p0, p1}, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge$1;-><init>(Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;Landroid/graphics/Bitmap;)V

    invoke-static {v5, v7}, Lcom/miui/gallery/util/DocumentProviderUtils;->safeWriteFile(Ljava/lang/String;Lcom/miui/gallery/util/BaseDocumentProviderUtils$WriteHandler;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    iget-object v5, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    iget-object v5, v5, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;->mWebView:Landroid/webkit/WebView;

    const-string v7, "javascript:onImageSaveResult()"

    const/4 v8, 0x0

    invoke-virtual {p1, v5, v7, v8}, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;->callJsMethod(Landroid/webkit/WebView;Ljava/lang/String;Landroid/webkit/ValueCallback;)V

    iget-object p1, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    iget-object p1, p1, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;->mContext:Landroid/content/Context;

    invoke-virtual {v6}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v5

    invoke-static {p1, v5}, Lcom/miui/gallery/scanner/MediaScanner;->scanSingleFile(Landroid/content/Context;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    iget-object p1, p1, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;->mContext:Landroid/content/Context;

    invoke-static {p1, v6, v3}, Lcom/miui/gallery/util/MediaStoreUtils;->insert(Landroid/content/Context;Ljava/io/File;I)Landroid/net/Uri;

    iget-object p1, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    const-string v5, "save_success"

    invoke-virtual {p1, v5}, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;->recordSaveEvent(Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    invoke-virtual {v6}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v5

    invoke-static {p1, v5}, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;->access$200(Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient$OperationStoryJSBridge;->this$0:Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;

    iget-object p1, p1, Lcom/miui/gallery/hybrid/hybridclient/OperationHybridClient;->mContext:Landroid/content/Context;

    const v5, 0x7f100548

    invoke-static {p1, v5}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    const-string p1, "save image fail,bitmap compress error"

    invoke-static {v2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    :cond_2
    const-string p1, "save image fail,no bitmap got from web"

    invoke-static {v2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :goto_0
    return v3

    :catch_0
    move-exception p1

    invoke-static {v2, v0, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_1

    :catch_1
    move-exception p1

    invoke-static {v2, v0, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_1

    :catch_2
    move-exception p1

    invoke-static {v2, v1, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_1

    :catch_3
    move-exception p1

    invoke-static {v2, v1, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_3
    :goto_1
    return v4
.end method
