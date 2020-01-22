.class Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;
.super Ljava/lang/Object;
.source "ScreenEditorActivity.java"

# interfaces
.implements Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper$AnimatorViewCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;


# direct methods
.method constructor <init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getBottomLayoutView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$2200(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroid/widget/FrameLayout;

    move-result-object v0

    return-object v0
.end method

.method public getCommonChangeLine()Landroidx/constraintlayout/widget/Guideline;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$2900(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;

    move-result-object v0

    return-object v0
.end method

.method public getEditBottomLine()Landroidx/constraintlayout/widget/Guideline;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$3200(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;

    move-result-object v0

    return-object v0
.end method

.method public getEditTopLine()Landroidx/constraintlayout/widget/Guideline;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$3300(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;

    move-result-object v0

    return-object v0
.end method

.method public getEditTopView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$2300(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/ConstraintLayout;

    move-result-object v0

    return-object v0
.end method

.method public getMenuBottomLine()Landroidx/constraintlayout/widget/Guideline;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$3000(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;

    move-result-object v0

    return-object v0
.end method

.method public getMenuLayoutView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$2600(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroid/widget/FrameLayout;

    move-result-object v0

    return-object v0
.end method

.method public getMenuTopLine()Landroidx/constraintlayout/widget/Guideline;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$3100(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;

    move-result-object v0

    return-object v0
.end method

.method public getScreenEditorBgView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$2500(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroid/widget/FrameLayout;

    move-result-object v0

    return-object v0
.end method

.method public getShareBottomLine()Landroidx/constraintlayout/widget/Guideline;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$2700(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;

    move-result-object v0

    return-object v0
.end method

.method public getShareTopLine()Landroidx/constraintlayout/widget/Guideline;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$2800(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;

    move-result-object v0

    return-object v0
.end method

.method public getShareTopView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;->this$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->access$2400(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/ConstraintLayout;

    move-result-object v0

    return-object v0
.end method
