.class public final synthetic Lcom/miui/gallery/ui/-$$Lambda$AlbumPageFragment$FbTk6tQCtyYaLAkRJbYJ8FBN2WM;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/ui/AlbumPageFragment;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/ui/AlbumPageFragment;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageFragment$FbTk6tQCtyYaLAkRJbYJ8FBN2WM;->f$0:Lcom/miui/gallery/ui/AlbumPageFragment;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageFragment$FbTk6tQCtyYaLAkRJbYJ8FBN2WM;->f$0:Lcom/miui/gallery/ui/AlbumPageFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/AlbumPageFragment;->lambda$onStatusChanged$44$AlbumPageFragment()V

    return-void
.end method
