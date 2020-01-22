.class Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate$SingletonHolder;
.super Ljava/lang/Object;
.source "ImageLoaderSupportDelegate.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "SingletonHolder"
.end annotation


# static fields
.field private static final INSTANCE:Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;-><init>(Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate$1;)V

    sput-object v0, Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate$SingletonHolder;->INSTANCE:Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;

    return-void
.end method

.method static synthetic access$000()Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;
    .locals 1

    sget-object v0, Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate$SingletonHolder;->INSTANCE:Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;

    return-object v0
.end method
