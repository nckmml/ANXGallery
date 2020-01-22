.class public Lmiui/gallery/support/MiuiSdkCompat;
.super Ljava/lang/Object;
.source "MiuiSdkCompat.java"


# static fields
.field private static final DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/util/LazyValue<",
            "Landroid/content/Context;",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end field

.field private static final MIUI_SDK_LEVEL:Lcom/miui/gallery/util/LazyValue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/util/LazyValue<",
            "Landroid/content/Context;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static final MIUI_VERSION_CODE:Lcom/miui/gallery/util/LazyValue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/util/LazyValue<",
            "Ljava/lang/Void;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lmiui/gallery/support/MiuiSdkCompat$1;

    invoke-direct {v0}, Lmiui/gallery/support/MiuiSdkCompat$1;-><init>()V

    sput-object v0, Lmiui/gallery/support/MiuiSdkCompat;->MIUI_SDK_LEVEL:Lcom/miui/gallery/util/LazyValue;

    new-instance v0, Lmiui/gallery/support/MiuiSdkCompat$2;

    invoke-direct {v0}, Lmiui/gallery/support/MiuiSdkCompat$2;-><init>()V

    sput-object v0, Lmiui/gallery/support/MiuiSdkCompat;->MIUI_VERSION_CODE:Lcom/miui/gallery/util/LazyValue;

    new-instance v0, Lmiui/gallery/support/MiuiSdkCompat$3;

    invoke-direct {v0}, Lmiui/gallery/support/MiuiSdkCompat$3;-><init>()V

    sput-object v0, Lmiui/gallery/support/MiuiSdkCompat;->DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    return-void
.end method

.method private static isMiui10StyleUsable(Landroid/content/Context;)Z
    .locals 1

    sget-object v0, Lmiui/gallery/support/MiuiSdkCompat;->MIUI_SDK_LEVEL:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    const/16 v0, 0xf

    if-ge p0, v0, :cond_1

    sget-object p0, Lmiui/gallery/support/MiuiSdkCompat;->MIUI_VERSION_CODE:Lcom/miui/gallery/util/LazyValue;

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    const/16 v0, 0x8

    if-lt p0, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p0, 0x1

    :goto_1
    return p0
.end method

.method private static isNightMode(Landroid/content/Context;)Z
    .locals 1

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object p0

    iget p0, p0, Landroid/content/res/Configuration;->uiMode:I

    and-int/lit8 p0, p0, 0x30

    const/16 v0, 0x20

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static setEditActionModeButton(Landroid/content/Context;Landroid/widget/Button;I)V
    .locals 1

    if-eqz p0, :cond_2

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isMiui10StyleUsable(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {p0, p1, p2}, Lmiui/gallery/support/MiuiSdkCompat;->setEditActionModeButtonByIcon(Landroid/content/Context;Landroid/widget/Button;I)V

    goto :goto_0

    :cond_1
    invoke-static {p1, p2}, Lmiui/gallery/support/MiuiSdkCompat;->setEditActionModeButtonByText(Landroid/widget/Button;I)V

    :cond_2
    :goto_0
    return-void
.end method

.method public static setEditActionModeButton(Landroid/content/Context;Lmiui/view/EditActionMode;II)V
    .locals 2

    if-eqz p0, :cond_2

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isMiui10StyleUsable(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_1

    :try_start_0
    invoke-static {p0, p1, p2, p3}, Lmiui/gallery/support/MiuiSdkCompat;->setEditActionModeButtonByIcon(Landroid/content/Context;Lmiui/view/EditActionMode;II)V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    const-string v0, "MiuiSdkCompat"

    const-string v1, "set EditActionMode button icon is not supported"

    invoke-static {v0, v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    invoke-static {p1, p2, p3}, Lmiui/gallery/support/MiuiSdkCompat;->setEditActionModeButtonByText(Lmiui/view/EditActionMode;II)V

    goto :goto_0

    :cond_1
    invoke-static {p1, p2, p3}, Lmiui/gallery/support/MiuiSdkCompat;->setEditActionModeButtonByText(Lmiui/view/EditActionMode;II)V

    :cond_2
    :goto_0
    return-void
.end method

.method private static setEditActionModeButtonByIcon(Landroid/content/Context;Landroid/widget/Button;I)V
    .locals 2

    const-string v0, ""

    invoke-virtual {p1, v0}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    if-eqz p2, :cond_6

    const/4 v1, 0x1

    if-eq p2, v1, :cond_4

    const/4 v1, 0x2

    if-eq p2, v1, :cond_2

    const/4 v1, 0x3

    if-eq p2, v1, :cond_0

    goto/16 :goto_4

    :cond_0
    sget-object p2, Lmiui/gallery/support/MiuiSdkCompat;->DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p2, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Boolean;

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_1

    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isNightMode(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_1

    sget p0, Lmiui/R$drawable;->action_mode_title_button_cancel_dark:I

    goto :goto_0

    :cond_1
    sget p0, Lmiui/R$drawable;->action_mode_title_button_cancel_light:I

    :goto_0
    invoke-virtual {p1, p0}, Landroid/widget/Button;->setBackgroundResource(I)V

    const/high16 p0, 0x1040000

    invoke-virtual {v0, p0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Landroid/widget/Button;->setContentDescription(Ljava/lang/CharSequence;)V

    goto/16 :goto_4

    :cond_2
    sget-object p2, Lmiui/gallery/support/MiuiSdkCompat;->DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p2, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Boolean;

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_3

    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isNightMode(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_3

    sget p0, Lmiui/R$drawable;->action_mode_title_button_confirm_dark:I

    goto :goto_1

    :cond_3
    sget p0, Lmiui/R$drawable;->action_mode_title_button_confirm_light:I

    :goto_1
    invoke-virtual {p1, p0}, Landroid/widget/Button;->setBackgroundResource(I)V

    const p0, 0x104000a

    invoke-virtual {v0, p0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Landroid/widget/Button;->setContentDescription(Ljava/lang/CharSequence;)V

    goto :goto_4

    :cond_4
    sget-object p2, Lmiui/gallery/support/MiuiSdkCompat;->DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p2, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Boolean;

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_5

    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isNightMode(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_5

    sget p0, Lmiui/R$drawable;->action_mode_title_button_deselect_all_dark:I

    goto :goto_2

    :cond_5
    sget p0, Lmiui/R$drawable;->action_mode_title_button_deselect_all_light:I

    :goto_2
    invoke-virtual {p1, p0}, Landroid/widget/Button;->setBackgroundResource(I)V

    sget p0, Lmiui/R$string;->deselect_all:I

    invoke-virtual {v0, p0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Landroid/widget/Button;->setContentDescription(Ljava/lang/CharSequence;)V

    goto :goto_4

    :cond_6
    sget-object p2, Lmiui/gallery/support/MiuiSdkCompat;->DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p2, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Boolean;

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_7

    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isNightMode(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_7

    sget p0, Lmiui/R$drawable;->action_mode_title_button_select_all_dark:I

    goto :goto_3

    :cond_7
    sget p0, Lmiui/R$drawable;->action_mode_title_button_select_all_light:I

    :goto_3
    invoke-virtual {p1, p0}, Landroid/widget/Button;->setBackgroundResource(I)V

    sget p0, Lmiui/R$string;->select_all:I

    invoke-virtual {v0, p0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Landroid/widget/Button;->setContentDescription(Ljava/lang/CharSequence;)V

    :goto_4
    return-void
.end method

.method private static setEditActionModeButtonByIcon(Landroid/content/Context;Lmiui/view/EditActionMode;II)V
    .locals 2

    const-string v0, ""

    if-eqz p3, :cond_6

    const/4 v1, 0x1

    if-eq p3, v1, :cond_4

    const/4 v1, 0x2

    if-eq p3, v1, :cond_2

    const/4 v1, 0x3

    if-eq p3, v1, :cond_0

    goto/16 :goto_4

    :cond_0
    sget-object p3, Lmiui/gallery/support/MiuiSdkCompat;->DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p3, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/Boolean;

    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p3

    if-eqz p3, :cond_1

    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isNightMode(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_1

    sget p0, Lmiui/R$drawable;->action_mode_title_button_cancel_dark:I

    goto :goto_0

    :cond_1
    sget p0, Lmiui/R$drawable;->action_mode_title_button_cancel_light:I

    :goto_0
    invoke-interface {p1, p2, v0, p0}, Lmiui/view/EditActionMode;->setButton(ILjava/lang/CharSequence;I)V

    goto :goto_4

    :cond_2
    sget-object p3, Lmiui/gallery/support/MiuiSdkCompat;->DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p3, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/Boolean;

    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p3

    if-eqz p3, :cond_3

    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isNightMode(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_3

    sget p0, Lmiui/R$drawable;->action_mode_title_button_confirm_dark:I

    goto :goto_1

    :cond_3
    sget p0, Lmiui/R$drawable;->action_mode_title_button_confirm_light:I

    :goto_1
    invoke-interface {p1, p2, v0, p0}, Lmiui/view/EditActionMode;->setButton(ILjava/lang/CharSequence;I)V

    goto :goto_4

    :cond_4
    sget-object p3, Lmiui/gallery/support/MiuiSdkCompat;->DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p3, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/Boolean;

    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p3

    if-eqz p3, :cond_5

    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isNightMode(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_5

    sget p0, Lmiui/R$drawable;->action_mode_title_button_deselect_all_dark:I

    goto :goto_2

    :cond_5
    sget p0, Lmiui/R$drawable;->action_mode_title_button_deselect_all_light:I

    :goto_2
    invoke-interface {p1, p2, v0, p0}, Lmiui/view/EditActionMode;->setButton(ILjava/lang/CharSequence;I)V

    goto :goto_4

    :cond_6
    sget-object p3, Lmiui/gallery/support/MiuiSdkCompat;->DARK_ICON_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p3, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/Boolean;

    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p3

    if-eqz p3, :cond_7

    invoke-static {p0}, Lmiui/gallery/support/MiuiSdkCompat;->isNightMode(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_7

    sget p0, Lmiui/R$drawable;->action_mode_title_button_select_all_dark:I

    goto :goto_3

    :cond_7
    sget p0, Lmiui/R$drawable;->action_mode_title_button_select_all_light:I

    :goto_3
    invoke-interface {p1, p2, v0, p0}, Lmiui/view/EditActionMode;->setButton(ILjava/lang/CharSequence;I)V

    :goto_4
    return-void
.end method

.method private static setEditActionModeButtonByText(Landroid/widget/Button;I)V
    .locals 1

    if-eqz p1, :cond_3

    const/4 v0, 0x1

    if-eq p1, v0, :cond_2

    const/4 v0, 0x2

    if-eq p1, v0, :cond_1

    const/4 v0, 0x3

    if-eq p1, v0, :cond_0

    goto :goto_0

    :cond_0
    const/high16 p1, 0x1040000

    invoke-virtual {p0, p1}, Landroid/widget/Button;->setText(I)V

    goto :goto_0

    :cond_1
    const p1, 0x104000a

    invoke-virtual {p0, p1}, Landroid/widget/Button;->setText(I)V

    goto :goto_0

    :cond_2
    sget p1, Lmiui/R$string;->deselect_all:I

    invoke-virtual {p0, p1}, Landroid/widget/Button;->setText(I)V

    goto :goto_0

    :cond_3
    sget p1, Lmiui/R$string;->select_all:I

    invoke-virtual {p0, p1}, Landroid/widget/Button;->setText(I)V

    :goto_0
    return-void
.end method

.method private static setEditActionModeButtonByText(Lmiui/view/EditActionMode;II)V
    .locals 1

    if-eqz p2, :cond_3

    const/4 v0, 0x1

    if-eq p2, v0, :cond_2

    const/4 v0, 0x2

    if-eq p2, v0, :cond_1

    const/4 v0, 0x3

    if-eq p2, v0, :cond_0

    goto :goto_0

    :cond_0
    const/high16 p2, 0x1040000

    invoke-interface {p0, p1, p2}, Lmiui/view/EditActionMode;->setButton(II)V

    goto :goto_0

    :cond_1
    const p2, 0x104000a

    invoke-interface {p0, p1, p2}, Lmiui/view/EditActionMode;->setButton(II)V

    goto :goto_0

    :cond_2
    sget p2, Lmiui/R$string;->deselect_all:I

    invoke-interface {p0, p1, p2}, Lmiui/view/EditActionMode;->setButton(II)V

    goto :goto_0

    :cond_3
    sget p2, Lmiui/R$string;->select_all:I

    invoke-interface {p0, p1, p2}, Lmiui/view/EditActionMode;->setButton(II)V

    :goto_0
    return-void
.end method
