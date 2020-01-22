.class Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;
.super Ljava/lang/Object;
.source "TimeLineGridHeaderItem.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/TimeLineGridHeaderItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "SelectGroupHelper"
.end annotation


# instance fields
.field private isChecked:Z

.field private mContext:Landroid/content/Context;

.field private mSelectClickListener:Landroid/view/View$OnClickListener;

.field private mSelectStub:Landroid/view/ViewStub;

.field private mSelectView:Landroid/view/View;


# direct methods
.method constructor <init>(Landroid/content/Context;Landroid/view/ViewStub;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mContext:Landroid/content/Context;

    iput-object p2, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectStub:Landroid/view/ViewStub;

    return-void
.end method


# virtual methods
.method setCheckable(Z)V
    .locals 1

    if-eqz p1, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectView:Landroid/view/View;

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectStub:Landroid/view/ViewStub;

    invoke-virtual {p1}, Landroid/view/ViewStub;->inflate()Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectView:Landroid/view/View;

    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->setCheckableListener(Landroid/view/View$OnClickListener;)V

    iget-boolean p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->isChecked:Z

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->setChecked(Z)V

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectView:Landroid/view/View;

    invoke-virtual {p1}, Landroid/view/View;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    if-nez p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectView:Landroid/view/View;

    const v0, 0x7f0702c3

    invoke-virtual {p1, v0}, Landroid/view/View;->setBackgroundResource(I)V

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectView:Landroid/view/View;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    goto :goto_0

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectView:Landroid/view/View;

    if-eqz p1, :cond_3

    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    :cond_3
    :goto_0
    return-void
.end method

.method setCheckableListener(Landroid/view/View$OnClickListener;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectView:Landroid/view/View;

    if-nez v0, :cond_0

    iput-object p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectClickListener:Landroid/view/View$OnClickListener;

    goto :goto_0

    :cond_0
    invoke-virtual {v0, p1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    :goto_0
    return-void
.end method

.method setChecked(Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mSelectView:Landroid/view/View;

    if-nez v0, :cond_0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->isChecked:Z

    goto :goto_1

    :cond_0
    check-cast v0, Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/gallery/ui/TimeLineGridHeaderItem$SelectGroupHelper;->mContext:Landroid/content/Context;

    if-eqz p1, :cond_1

    sget p1, Lmiui/R$string;->deselect_all:I

    goto :goto_0

    :cond_1
    sget p1, Lmiui/R$string;->select_all:I

    :goto_0
    invoke-virtual {v1, p1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    :goto_1
    return-void
.end method
