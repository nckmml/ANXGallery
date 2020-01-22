.class public Lcom/miui/gallery/ui/HomePageGridHeaderItem;
.super Lcom/miui/gallery/ui/TimeLineGridHeaderItem;
.source "HomePageGridHeaderItem.java"


# static fields
.field private static sHeaderBackground:Landroid/graphics/drawable/Drawable;

.field private static sLastNightMode:I


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method private static getHeaderBackground(Landroid/content/Context;)Landroid/graphics/drawable/Drawable;
    .locals 2

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    iget v0, v0, Landroid/content/res/Configuration;->uiMode:I

    and-int/lit8 v0, v0, 0x30

    sget-object v1, Lcom/miui/gallery/ui/HomePageGridHeaderItem;->sHeaderBackground:Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_0

    sget v1, Lcom/miui/gallery/ui/HomePageGridHeaderItem;->sLastNightMode:I

    if-eq v0, v1, :cond_1

    :cond_0
    const v1, 0x7f0702ee

    invoke-virtual {p0, v1}, Landroid/content/Context;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    sput-object p0, Lcom/miui/gallery/ui/HomePageGridHeaderItem;->sHeaderBackground:Landroid/graphics/drawable/Drawable;

    sput v0, Lcom/miui/gallery/ui/HomePageGridHeaderItem;->sLastNightMode:I

    :cond_1
    sget-object p0, Lcom/miui/gallery/ui/HomePageGridHeaderItem;->sHeaderBackground:Landroid/graphics/drawable/Drawable;

    return-object p0
.end method


# virtual methods
.method protected onFinishInflate()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/ui/TimeLineGridHeaderItem;->onFinishInflate()V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/HomePageGridHeaderItem;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/ui/HomePageGridHeaderItem;->getHeaderBackground(Landroid/content/Context;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/HomePageGridHeaderItem;->setBackground(Landroid/graphics/drawable/Drawable;)V

    return-void
.end method
