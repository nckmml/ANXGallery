.class public abstract Lcom/miui/gallery/ui/TimeLineGridHeaderItem;
.super Landroid/widget/LinearLayout;
.source "TimeLineGridHeaderItem.java"

# interfaces
.implements Lcom/miui/gallery/ui/CheckableView;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;
    }
.end annotation


# instance fields
.field protected mDateTime:Landroid/widget/TextView;

.field protected mLocation:Landroid/widget/TextView;

.field private mSelectGroupOrNot:Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    const/4 p1, 0x3

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->setLayoutDirection(I)V

    return-void
.end method


# virtual methods
.method public bindData(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mDateTime:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mLocation:Landroid/widget/TextView;

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mLocation:Landroid/widget/TextView;

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setVisibility(I)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mLocation:Landroid/widget/TextView;

    const/4 p2, 0x4

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setVisibility(I)V

    :goto_0
    return-void
.end method

.method public isChecked()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method protected onFinishInflate()V
    .locals 3

    invoke-super {p0}, Landroid/widget/LinearLayout;->onFinishInflate()V

    const v0, 0x7f0900e8

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mDateTime:Landroid/widget/TextView;

    const v0, 0x7f0901f1

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mLocation:Landroid/widget/TextView;

    new-instance v0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->getContext()Landroid/content/Context;

    move-result-object v1

    const v2, 0x7f0902f6

    invoke-virtual {p0, v2}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/view/ViewStub;

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;-><init>(Landroid/content/Context;Landroid/view/ViewStub;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mSelectGroupOrNot:Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;

    return-void
.end method

.method public setCheckable(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mSelectGroupOrNot:Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->setCheckable(Z)V

    return-void
.end method

.method public setCheckableListener(Landroid/view/View$OnClickListener;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mSelectGroupOrNot:Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->setCheckableListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method public setChecked(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->mSelectGroupOrNot:Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->setChecked(Z)V

    return-void
.end method

.method public toggle()V
    .locals 0

    return-void
.end method
