.class public Lcom/miui/gallery/activity/CloudSettingsActivity;
.super Landroid/app/Activity;
.source "CloudSettingsActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    const p1, 0x7f0b00b8

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/CloudSettingsActivity;->setContentView(I)V

    return-void
.end method
