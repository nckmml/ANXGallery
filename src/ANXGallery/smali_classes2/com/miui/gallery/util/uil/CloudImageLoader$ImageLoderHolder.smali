.class Lcom/miui/gallery/util/uil/CloudImageLoader$ImageLoderHolder;
.super Ljava/lang/Object;
.source "CloudImageLoader.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/util/uil/CloudImageLoader;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "ImageLoderHolder"
.end annotation


# static fields
.field private static sLoader:Lcom/miui/gallery/util/uil/CloudImageLoader;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Lcom/miui/gallery/util/uil/CloudImageLoader;

    invoke-static {}, Lcom/miui/gallery/util/StaticContext;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v1

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/util/uil/CloudImageLoader;-><init>(Landroid/content/Context;Lcom/miui/gallery/util/uil/CloudImageLoader$1;)V

    sput-object v0, Lcom/miui/gallery/util/uil/CloudImageLoader$ImageLoderHolder;->sLoader:Lcom/miui/gallery/util/uil/CloudImageLoader;

    return-void
.end method

.method static synthetic access$900()Lcom/miui/gallery/util/uil/CloudImageLoader;
    .locals 1

    sget-object v0, Lcom/miui/gallery/util/uil/CloudImageLoader$ImageLoderHolder;->sLoader:Lcom/miui/gallery/util/uil/CloudImageLoader;

    return-object v0
.end method
