.class Lcom/miui/gallery/ui/ShareFilePrepareFragment$OutputGenerator;
.super Ljava/lang/Object;
.source "ShareFilePrepareFragment.java"

# interfaces
.implements Lcom/miui/gallery/editor/photo/sdk/RenderTask$Generator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/ShareFilePrepareFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "OutputGenerator"
.end annotation


# instance fields
.field private mCacheFolder:Ljava/io/File;

.field private mFormat:Ljava/text/SimpleDateFormat;

.field final synthetic this$0:Lcom/miui/gallery/ui/ShareFilePrepareFragment;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/ui/ShareFilePrepareFragment;)V
    .locals 2

    iput-object p1, p0, Lcom/miui/gallery/ui/ShareFilePrepareFragment$OutputGenerator;->this$0:Lcom/miui/gallery/ui/ShareFilePrepareFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p1, Ljava/text/SimpleDateFormat;

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const-string v1, "yyyy-MM-dd_HH-mm-ss_SSS"

    invoke-direct {p1, v1, v0}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;Ljava/util/Locale;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/ShareFilePrepareFragment$OutputGenerator;->mFormat:Ljava/text/SimpleDateFormat;

    new-instance p1, Ljava/io/File;

    iget-object v0, p0, Lcom/miui/gallery/ui/ShareFilePrepareFragment$OutputGenerator;->this$0:Lcom/miui/gallery/ui/ShareFilePrepareFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/ShareFilePrepareFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Activity;->getExternalCacheDir()Ljava/io/File;

    move-result-object v0

    const-string v1, "render_tmp_files"

    invoke-direct {p1, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/ShareFilePrepareFragment$OutputGenerator;->mCacheFolder:Ljava/io/File;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/ShareFilePrepareFragment;Lcom/miui/gallery/ui/ShareFilePrepareFragment$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/ShareFilePrepareFragment$OutputGenerator;-><init>(Lcom/miui/gallery/ui/ShareFilePrepareFragment;)V

    return-void
.end method


# virtual methods
.method public generate(Landroid/net/Uri;)Landroid/net/Uri;
    .locals 5

    sget-object p1, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v0, 0x1

    new-array v0, v0, [Ljava/lang/Object;

    iget-object v1, p0, Lcom/miui/gallery/ui/ShareFilePrepareFragment$OutputGenerator;->mFormat:Ljava/text/SimpleDateFormat;

    new-instance v2, Ljava/util/Date;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    invoke-direct {v2, v3, v4}, Ljava/util/Date;-><init>(J)V

    invoke-virtual {v1, v2}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    aput-object v1, v0, v2

    const-string v1, "%s.jpg"

    invoke-static {p1, v1, v0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/miui/gallery/ui/ShareFilePrepareFragment$OutputGenerator;->mCacheFolder:Ljava/io/File;

    invoke-direct {v0, v1, p1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-static {v0}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object p1

    return-object p1
.end method
