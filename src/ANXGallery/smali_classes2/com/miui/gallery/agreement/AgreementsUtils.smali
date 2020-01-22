.class public Lcom/miui/gallery/agreement/AgreementsUtils;
.super Ljava/lang/Object;
.source "AgreementsUtils.java"


# direct methods
.method private static getAgreements(Landroid/content/Context;)Ljava/util/ArrayList;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            ")",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/agreement/core/Agreement;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    new-instance v1, Lcom/miui/gallery/agreement/core/Agreement;

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lcom/miui/gallery/permission/R$string;->user_agreement2:I

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/miui/gallery/agreement/cta/CtaAgreement$Licence;->getUserAgreementUrl()Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x1

    invoke-direct {v1, v2, v3, v4}, Lcom/miui/gallery/agreement/core/Agreement;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    new-instance v2, Lcom/miui/gallery/agreement/core/Agreement;

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    sget v3, Lcom/miui/gallery/permission/R$string;->user_agreement4:I

    invoke-virtual {p0, v3}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {}, Lcom/miui/gallery/agreement/cta/CtaAgreement$Licence;->getPrivacyUrl()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, p0, v3, v4}, Lcom/miui/gallery/agreement/core/Agreement;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0
.end method

.method public static isKoreaRegion()Z
    .locals 2

    invoke-static {}, Lcom/miui/gallery/util/BaseBuildUtil;->getRegion()Ljava/lang/String;

    move-result-object v0

    const-string v1, "KR"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method public static isNetworkingAgreementAccepted()Z
    .locals 2

    invoke-static {}, Lcom/miui/gallery/util/BaseBuildUtil;->isInternational()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/preference/BaseGalleryPreferences$CTA;->canConnectNetwork()Z

    move-result v0

    return v0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/agreement/AgreementsUtils;->isKoreaRegion()Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_3

    invoke-static {}, Lcom/miui/gallery/preference/BaseGalleryPreferences$Agreement;->isRequiredAgreementsAllowed()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-static {}, Lcom/miui/gallery/preference/BaseGalleryPreferences$CTA;->canConnectNetwork()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {v1}, Lcom/miui/gallery/preference/BaseGalleryPreferences$CTA;->setCanConnectNetwork(Z)V

    :cond_1
    return v1

    :cond_2
    invoke-static {}, Lcom/miui/gallery/preference/BaseGalleryPreferences$CTA;->canConnectNetwork()Z

    move-result v0

    return v0

    :cond_3
    invoke-static {}, Lcom/miui/gallery/preference/BaseGalleryPreferences$CTA;->canConnectNetwork()Z

    move-result v0

    if-nez v0, :cond_4

    invoke-static {v1}, Lcom/miui/gallery/preference/BaseGalleryPreferences$CTA;->setCanConnectNetwork(Z)V

    :cond_4
    return v1
.end method

.method public static showNetworkingAgreement(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V
    .locals 2

    sget-object v0, Lcom/miui/gallery/agreement/cta/SystemCtaAgreement;->SUPPORT_SYSTEM_CTA:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Lcom/miui/gallery/agreement/cta/SystemNetworkAgreementInjector;

    invoke-direct {v0}, Lcom/miui/gallery/agreement/cta/SystemNetworkAgreementInjector;-><init>()V

    new-instance v1, Lcom/miui/gallery/agreement/OnNetworkInvokedWrapper;

    invoke-direct {v1, p1}, Lcom/miui/gallery/agreement/OnNetworkInvokedWrapper;-><init>(Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    invoke-virtual {v0, p0, v1}, Lcom/miui/gallery/agreement/cta/SystemNetworkAgreementInjector;->invoke(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/miui/gallery/agreement/cta/NetworkAgreementFragment;

    invoke-direct {v0}, Lcom/miui/gallery/agreement/cta/NetworkAgreementFragment;-><init>()V

    new-instance v1, Lcom/miui/gallery/agreement/OnNetworkInvokedWrapper;

    invoke-direct {v1, p1}, Lcom/miui/gallery/agreement/OnNetworkInvokedWrapper;-><init>(Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    invoke-virtual {v0, p0, v1}, Lcom/miui/gallery/agreement/cta/NetworkAgreementFragment;->invoke(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    :goto_0
    return-void
.end method

.method public static showUserAgreements(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/agreement/AgreementsUtils;->isKoreaRegion()Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_2

    invoke-static {}, Lcom/miui/gallery/preference/BaseGalleryPreferences$Agreement;->isRequiredAgreementsAllowed()Z

    move-result v0

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    invoke-interface {p1, v1}, Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;->onAgreementInvoked(Z)V

    :cond_0
    return-void

    :cond_1
    invoke-static {p0}, Lcom/miui/gallery/agreement/AgreementsUtils;->getAgreements(Landroid/content/Context;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/agreement/AgreementDialogFragment;->newInstance(Ljava/util/ArrayList;)Lcom/miui/gallery/agreement/AgreementDialogFragment;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/agreement/AgreementsUtils$1;

    invoke-direct {v1, p0, p1}, Lcom/miui/gallery/agreement/AgreementsUtils$1;-><init>(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    invoke-virtual {v0, p0, v1}, Lcom/miui/gallery/agreement/AgreementDialogFragment;->invoke(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    return-void

    :cond_2
    invoke-static {}, Lcom/miui/gallery/util/BaseBuildUtil;->isInternational()Z

    move-result v0

    if-nez v0, :cond_3

    invoke-static {p0, p1}, Lcom/miui/gallery/agreement/AgreementsUtils;->showNetworkingAgreement(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    return-void

    :cond_3
    invoke-static {v1}, Lcom/miui/gallery/preference/BaseGalleryPreferences$CTA;->setCanConnectNetwork(Z)V

    if-eqz p1, :cond_4

    invoke-interface {p1, v1}, Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;->onAgreementInvoked(Z)V

    :cond_4
    return-void
.end method

.method public static viewAgreement(Landroid/content/Context;Lcom/miui/gallery/agreement/core/Agreement;)V
    .locals 3

    const-string v0, "AgreementsUtils"

    if-eqz p1, :cond_1

    iget-object v1, p1, Lcom/miui/gallery/agreement/core/Agreement;->mLink:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_1

    :cond_0
    new-instance v1, Landroid/content/Intent;

    const-string v2, "android.intent.action.VIEW"

    invoke-direct {v1, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    iget-object p1, p1, Lcom/miui/gallery/agreement/core/Agreement;->mLink:Ljava/lang/String;

    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p1

    invoke-virtual {v1, p1}, Landroid/content/Intent;->setData(Landroid/net/Uri;)Landroid/content/Intent;

    :try_start_0
    invoke-virtual {p0, v1}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :goto_0
    return-void

    :cond_1
    :goto_1
    const-string p0, "agreement can\'t view"

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method
