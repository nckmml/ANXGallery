.class public Lmicloud/compat/v18/utils/PrivacyIntentHelperCompat;
.super Ljava/lang/Object;
.source "PrivacyIntentHelperCompat.java"


# static fields
.field private static final IMPL:Lmicloud/compat/v18/utils/IPrivacyIntentHelperCompat;

.field private static final version:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    sget v0, Lcom/xiaomi/micloudsdk/utils/MiCloudSdkBuild;->CURRENT_VERSION:I

    sput v0, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompat;->version:I

    sget v0, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompat;->version:I

    const/16 v1, 0x18

    if-lt v0, v1, :cond_0

    new-instance v0, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompatMiCloud_V24;

    invoke-direct {v0}, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompatMiCloud_V24;-><init>()V

    sput-object v0, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompat;->IMPL:Lmicloud/compat/v18/utils/IPrivacyIntentHelperCompat;

    goto :goto_0

    :cond_0
    new-instance v0, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompatMiCloud_Base;

    invoke-direct {v0}, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompatMiCloud_Base;-><init>()V

    sput-object v0, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompat;->IMPL:Lmicloud/compat/v18/utils/IPrivacyIntentHelperCompat;

    :goto_0
    return-void
.end method

.method public static notifyPrivacyDenied(Landroid/content/Context;)V
    .locals 1

    sget-object v0, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompat;->IMPL:Lmicloud/compat/v18/utils/IPrivacyIntentHelperCompat;

    invoke-interface {v0, p0}, Lmicloud/compat/v18/utils/IPrivacyIntentHelperCompat;->notifyPrivacyDenied(Landroid/content/Context;)V

    return-void
.end method
