.class Lcom/miui/gallery/ui/PeoplePageFragment$8$1;
.super Ljava/lang/Object;
.source "PeoplePageFragment.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/PeoplePageFragment$8;->onConfirm(Ljava/lang/String;Z)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/PeoplePageFragment$8;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PeoplePageFragment$8;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PeoplePageFragment$8$1;->this$1:Lcom/miui/gallery/ui/PeoplePageFragment$8;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PeoplePageFragment$8$1;->this$1:Lcom/miui/gallery/ui/PeoplePageFragment$8;

    iget-object v0, v0, Lcom/miui/gallery/ui/PeoplePageFragment$8;->val$mode:Landroid/view/ActionMode;

    invoke-virtual {v0}, Landroid/view/ActionMode;->finish()V

    return-void
.end method
