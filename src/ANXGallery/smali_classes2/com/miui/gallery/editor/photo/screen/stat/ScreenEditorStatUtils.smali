.class public Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;
.super Ljava/lang/Object;
.source "ScreenEditorStatUtils.java"


# static fields
.field public static final TYPE_LONG_SCREEN_EDITOR:Ljava/lang/String; = "long_screen_editor"

.field public static final TYPE_NORMAL_EDITOR:Ljava/lang/String; = "normal_editor"


# direct methods
.method private static getNavName(Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;)Ljava/lang/String;
    .locals 1

    iget p0, p0, Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;->id:I

    const/4 v0, 0x1

    if-eq p0, v0, :cond_4

    const/4 v0, 0x2

    if-eq p0, v0, :cond_3

    const/4 v0, 0x3

    if-eq p0, v0, :cond_2

    const/4 v0, 0x4

    if-eq p0, v0, :cond_1

    const/4 v0, 0x5

    if-eq p0, v0, :cond_0

    const-string p0, "no_define"

    return-object p0

    :cond_0
    const-string p0, "crop"

    return-object p0

    :cond_1
    const-string p0, "mosaic"

    return-object p0

    :cond_2
    const-string p0, "text"

    return-object p0

    :cond_3
    const-string p0, "doodle"

    return-object p0

    :cond_4
    const-string p0, "send"

    return-object p0
.end method

.method public static statBtnDeletelClick(Z)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    if-eqz p0, :cond_0

    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_LONG_SCREEN_EDITOR:Ljava/lang/String;

    goto :goto_0

    :cond_0
    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_NORMAL_EDITOR:Ljava/lang/String;

    :goto_0
    const-string v1, "type"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "btn_delete_click"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statBtnSavelClick(Z)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    if-eqz p0, :cond_0

    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_LONG_SCREEN_EDITOR:Ljava/lang/String;

    goto :goto_0

    :cond_0
    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_NORMAL_EDITOR:Ljava/lang/String;

    :goto_0
    const-string v1, "type"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "btn_save_click"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statDoodleColorChoose(I)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    const-string v1, "colorProgress"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "doodle_color_choose"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statDoodleMenuItemClick(I)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    const-string v1, "position"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "doodle_menu_item_click"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statDoodleSizeClick(Ljava/lang/String;)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    const-string v1, "size"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "doodle_size_click"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statMosaicMenuItemClick(I)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    const-string v1, "position"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "mosaic_menu_item_click"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statMosaicSizeChoose(I)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    const-string v1, "sizeProgress"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "mosaic_size_choose"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statNavItemClick(ZLcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;Z)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    if-eqz p0, :cond_0

    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_LONG_SCREEN_EDITOR:Ljava/lang/String;

    goto :goto_0

    :cond_0
    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_NORMAL_EDITOR:Ljava/lang/String;

    :goto_0
    const-string v1, "type"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->getNavName(Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;)Ljava/lang/String;

    move-result-object p0

    const-string p1, "nav"

    invoke-virtual {v0, p1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    if-eqz p2, :cond_1

    const-string p0, "true"

    goto :goto_1

    :cond_1
    const-string p0, "false"

    :goto_1
    const-string p1, "showMenu"

    invoke-virtual {v0, p1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string p1, "nav_item_click"

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statRevertClick(Z)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    if-eqz p0, :cond_0

    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_LONG_SCREEN_EDITOR:Ljava/lang/String;

    goto :goto_0

    :cond_0
    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_NORMAL_EDITOR:Ljava/lang/String;

    :goto_0
    const-string v1, "type"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "revert_click"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statRevokeClick(Z)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    if-eqz p0, :cond_0

    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_LONG_SCREEN_EDITOR:Ljava/lang/String;

    goto :goto_0

    :cond_0
    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_NORMAL_EDITOR:Ljava/lang/String;

    :goto_0
    const-string v1, "type"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "revoke_click"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statShowExportFragment(Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;Z)V
    .locals 1

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->putStat(Ljava/util/HashMap;)V

    if-eqz p1, :cond_0

    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_LONG_SCREEN_EDITOR:Ljava/lang/String;

    goto :goto_0

    :cond_0
    sget-object p0, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorStatUtils;->TYPE_NORMAL_EDITOR:Ljava/lang/String;

    :goto_0
    const-string p1, "type"

    invoke-virtual {v0, p1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string p1, "export_saving"

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public static statTextMenuItemClick(I)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    const-string v1, "position"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "ScreenEditor"

    const-string v1, "text_menu_item_click"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/editor/photo/screen/stat/ScreenEditorSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method
