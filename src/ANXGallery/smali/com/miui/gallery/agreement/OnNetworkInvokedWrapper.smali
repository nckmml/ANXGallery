.class Lcom/miui/gallery/agreement/OnNetworkInvokedWrapper;
.super Ljava/lang/Object;
.source "OnNetworkInvokedWrapper.java"

# interfaces
.implements Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;


# instance fields
.field private final mWrapped:Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;


# direct methods
.method constructor <init>(Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/agreement/OnNetworkInvokedWrapper;->mWrapped:Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;

    return-void
.end method


# virtual methods
.method public onAgreementInvoked(Z)V
    .locals 1

    const/4 v0, 0x1

    if-eqz p1, :cond_0

    invoke-static {v0}, Lcom/miui/gallery/preference/BaseGalleryPreferences$CTA;->setCanConnectNetwork(Z)V

    goto :goto_0

    :cond_0
    invoke-static {v0}, Lcom/miui/gallery/preference/BaseGalleryPreferences$CTA;->setToAllowUseOnOfflineGlobal(Z)V

    :goto_0
    iget-object v0, p0, Lcom/miui/gallery/agreement/OnNetworkInvokedWrapper;->mWrapped:Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;

    if-eqz v0, :cond_1

    invoke-interface {v0, p1}, Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;->onAgreementInvoked(Z)V

    :cond_1
    return-void
.end method
