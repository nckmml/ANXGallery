.class Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment$3;
.super Ljava/lang/Object;
.source "AdjustMenuFragment.java"

# interfaces
.implements Landroid/widget/SeekBar$OnSeekBarChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment$3;->this$0:Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onProgressChanged(Landroid/widget/SeekBar;IZ)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment$3;->this$0:Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment;

    invoke-static {p1, p2}, Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment;->access$200(Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment;I)V

    if-eqz p3, :cond_0

    if-nez p2, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment$3;->this$0:Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/app/adjust/AdjustMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object p1

    sget-object p2, Lmiui/gallery/support/linearmotor/LinearMotorHelper;->EFFECT_KEY_MESH_NORMAL:Ljava/lang/String;

    invoke-static {p1, p2}, Lmiui/gallery/support/linearmotor/LinearMotorHelper;->performHapticFeedback(Landroid/content/Context;Ljava/lang/String;)Z

    :cond_0
    return-void
.end method

.method public onStartTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 0

    return-void
.end method

.method public onStopTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 0

    return-void
.end method
