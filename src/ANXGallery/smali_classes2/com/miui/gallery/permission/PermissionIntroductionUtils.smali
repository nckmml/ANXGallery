.class public Lcom/miui/gallery/permission/PermissionIntroductionUtils;
.super Ljava/lang/Object;
.source "PermissionIntroductionUtils.java"


# direct methods
.method public static showPermissionIntroduction(Landroid/app/Activity;Ljava/lang/String;Lcom/miui/gallery/permission/core/OnPermissionIntroduced;)V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/agreement/AgreementsUtils;->isKoreaRegion()Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Lcom/miui/gallery/permission/introduction/RuntimePermissionsIntroduction;

    invoke-direct {v0}, Lcom/miui/gallery/permission/introduction/RuntimePermissionsIntroduction;-><init>()V

    invoke-virtual {v0, p0, p1, p2}, Lcom/miui/gallery/permission/introduction/RuntimePermissionsIntroduction;->introduce(Landroid/app/Activity;Ljava/lang/String;Lcom/miui/gallery/permission/core/OnPermissionIntroduced;)V

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/util/BaseBuildUtil;->isInternational()Z

    move-result v0

    const/4 v1, 0x1

    if-nez v0, :cond_3

    invoke-static {p1}, Lcom/miui/gallery/permission/cta/CtaPermissions;->isPrivacyAllowed(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p2, v1}, Lcom/miui/gallery/permission/core/OnPermissionIntroduced;->onPermissionIntroduced(Z)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lcom/miui/gallery/preference/BaseGalleryPreferences$PermissionIntroduction;->containCtaPrivacyPermission(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_2

    sget-object v0, Lcom/miui/gallery/permission/core/PermissionUtils;->CUSTOM_REQUEST_PERMISSION:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-static {p1, v1}, Lcom/miui/gallery/preference/BaseGalleryPreferences$PermissionIntroduction;->setCtaPrivacyPermissionsAllowed(Ljava/lang/String;Z)V

    invoke-interface {p2, v1}, Lcom/miui/gallery/permission/core/OnPermissionIntroduced;->onPermissionIntroduced(Z)V

    goto :goto_0

    :cond_2
    new-instance v0, Lcom/miui/gallery/permission/introduction/CTAPermissionIntroduction;

    invoke-direct {v0}, Lcom/miui/gallery/permission/introduction/CTAPermissionIntroduction;-><init>()V

    invoke-virtual {v0, p0, p1, p2}, Lcom/miui/gallery/permission/introduction/CTAPermissionIntroduction;->introduce(Landroid/app/Activity;Ljava/lang/String;Lcom/miui/gallery/permission/core/OnPermissionIntroduced;)V

    :goto_0
    return-void

    :cond_3
    invoke-interface {p2, v1}, Lcom/miui/gallery/permission/core/OnPermissionIntroduced;->onPermissionIntroduced(Z)V

    return-void
.end method
