.class public final synthetic Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase$ChoiceManagerBase;

.field private final synthetic f$1:Ljava/util/ArrayList;

.field private final synthetic f$2:Ljava/util/ArrayList;

.field private final synthetic f$3:Ljava/util/ArrayList;

.field private final synthetic f$4:Ljava/util/ArrayList;

.field private final synthetic f$5:Ljava/util/ArrayList;

.field private final synthetic f$6:Ljava/util/ArrayList;

.field private final synthetic f$7:Ljava/util/ArrayList;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/ui/PhotoPageFragmentBase$ChoiceManagerBase;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase$ChoiceManagerBase;

    iput-object p2, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$1:Ljava/util/ArrayList;

    iput-object p3, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$2:Ljava/util/ArrayList;

    iput-object p4, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$3:Ljava/util/ArrayList;

    iput-object p5, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$4:Ljava/util/ArrayList;

    iput-object p6, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$5:Ljava/util/ArrayList;

    iput-object p7, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$6:Ljava/util/ArrayList;

    iput-object p8, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$7:Ljava/util/ArrayList;

    return-void
.end method


# virtual methods
.method public final onClick(Landroid/content/DialogInterface;I)V
    .locals 10

    iget-object v0, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase$ChoiceManagerBase;

    iget-object v1, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$1:Ljava/util/ArrayList;

    iget-object v2, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$2:Ljava/util/ArrayList;

    iget-object v3, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$3:Ljava/util/ArrayList;

    iget-object v4, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$4:Ljava/util/ArrayList;

    iget-object v5, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$5:Ljava/util/ArrayList;

    iget-object v6, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$6:Ljava/util/ArrayList;

    iget-object v7, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageFragmentBase$ChoiceManagerBase$y40CHnp7Nzz_leGRER0Z7_nLhyY;->f$7:Ljava/util/ArrayList;

    move-object v8, p1

    move v9, p2

    invoke-virtual/range {v0 .. v9}, Lcom/miui/gallery/ui/PhotoPageFragmentBase$ChoiceManagerBase;->lambda$onIntentSelected$5$PhotoPageFragmentBase$ChoiceManagerBase(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Landroid/content/DialogInterface;I)V

    return-void
.end method
