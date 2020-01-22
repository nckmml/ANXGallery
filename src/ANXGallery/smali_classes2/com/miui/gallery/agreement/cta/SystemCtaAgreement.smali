.class public Lcom/miui/gallery/agreement/cta/SystemCtaAgreement;
.super Ljava/lang/Object;
.source "SystemCtaAgreement.java"


# static fields
.field public static SUPPORT_SYSTEM_CTA:Lcom/miui/gallery/util/LazyValue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/util/LazyValue<",
            "Landroid/content/Context;",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/agreement/cta/SystemCtaAgreement$1;

    invoke-direct {v0}, Lcom/miui/gallery/agreement/cta/SystemCtaAgreement$1;-><init>()V

    sput-object v0, Lcom/miui/gallery/agreement/cta/SystemCtaAgreement;->SUPPORT_SYSTEM_CTA:Lcom/miui/gallery/util/LazyValue;

    return-void
.end method
