.class public final synthetic Lcom/miui/gallery/cloudcontrol/-$$Lambda$ProfileCache$nSFm-6LyJlRPU_eV5_a8HjP6F2g;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/cloudcontrol/ProfileCache;

.field private final synthetic f$1:J


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/cloudcontrol/ProfileCache;J)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/cloudcontrol/-$$Lambda$ProfileCache$nSFm-6LyJlRPU_eV5_a8HjP6F2g;->f$0:Lcom/miui/gallery/cloudcontrol/ProfileCache;

    iput-wide p2, p0, Lcom/miui/gallery/cloudcontrol/-$$Lambda$ProfileCache$nSFm-6LyJlRPU_eV5_a8HjP6F2g;->f$1:J

    return-void
.end method


# virtual methods
.method public final handle(Landroid/database/Cursor;)Ljava/lang/Object;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/cloudcontrol/-$$Lambda$ProfileCache$nSFm-6LyJlRPU_eV5_a8HjP6F2g;->f$0:Lcom/miui/gallery/cloudcontrol/ProfileCache;

    iget-wide v1, p0, Lcom/miui/gallery/cloudcontrol/-$$Lambda$ProfileCache$nSFm-6LyJlRPU_eV5_a8HjP6F2g;->f$1:J

    invoke-virtual {v0, v1, v2, p1}, Lcom/miui/gallery/cloudcontrol/ProfileCache;->lambda$loadFromDB$12$ProfileCache(JLandroid/database/Cursor;)Ljava/lang/Object;

    move-result-object p1

    return-object p1
.end method
