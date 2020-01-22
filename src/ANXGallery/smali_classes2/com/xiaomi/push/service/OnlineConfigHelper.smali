.class public Lcom/xiaomi/push/service/OnlineConfigHelper;
.super Ljava/lang/Object;
.source "OnlineConfigHelper.java"


# direct methods
.method private static convertMessage(Ljava/util/List;Z)Ljava/util/List;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/xiaomi/xmpush/thrift/OnlineConfigItem;",
            ">;Z)",
            "Ljava/util/List<",
            "Landroid/util/Pair<",
            "Ljava/lang/Integer;",
            "Ljava/lang/Object;",
            ">;>;"
        }
    .end annotation

    invoke-static {p0}, Lcom/xiaomi/channel/commonutils/misc/CollectionUtils;->isEmpty(Ljava/util/Collection;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_7

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/xiaomi/xmpush/thrift/OnlineConfigItem;

    invoke-virtual {v2}, Lcom/xiaomi/xmpush/thrift/OnlineConfigItem;->getKey()I

    move-result v3

    invoke-virtual {v2}, Lcom/xiaomi/xmpush/thrift/OnlineConfigItem;->getType()I

    move-result v4

    invoke-static {v4}, Lcom/xiaomi/xmpush/thrift/ConfigType;->findByValue(I)Lcom/xiaomi/xmpush/thrift/ConfigType;

    move-result-object v4

    if-nez v4, :cond_1

    goto :goto_0

    :cond_1
    if-eqz p1, :cond_2

    iget-boolean v5, v2, Lcom/xiaomi/xmpush/thrift/OnlineConfigItem;->clear:Z

    if-eqz v5, :cond_2

    new-instance v2, Landroid/util/Pair;

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-direct {v2, v3, v1}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-interface {v0, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_2
    sget-object v5, Lcom/xiaomi/push/service/OnlineConfigHelper$1;->$SwitchMap$com$xiaomi$xmpush$thrift$ConfigType:[I

    invoke-virtual {v4}, Lcom/xiaomi/xmpush/thrift/ConfigType;->ordinal()I

    move-result v4

    aget v4, v5, v4

    const/4 v5, 0x1

    if-eq v4, v5, :cond_6

    const/4 v5, 0x2

    if-eq v4, v5, :cond_5

    const/4 v5, 0x3

    if-eq v4, v5, :cond_4

    const/4 v5, 0x4

    if-eq v4, v5, :cond_3

    move-object v4, v1

    goto :goto_1

    :cond_3
    new-instance v4, Landroid/util/Pair;

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v2}, Lcom/xiaomi/xmpush/thrift/OnlineConfigItem;->isBoolValue()Z

    move-result v2

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-direct {v4, v3, v2}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_1

    :cond_4
    new-instance v4, Landroid/util/Pair;

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v2}, Lcom/xiaomi/xmpush/thrift/OnlineConfigItem;->getStringValue()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v4, v3, v2}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_1

    :cond_5
    new-instance v4, Landroid/util/Pair;

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v2}, Lcom/xiaomi/xmpush/thrift/OnlineConfigItem;->getLongValue()J

    move-result-wide v5

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-direct {v4, v3, v2}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_1

    :cond_6
    new-instance v4, Landroid/util/Pair;

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v2}, Lcom/xiaomi/xmpush/thrift/OnlineConfigItem;->getIntValue()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-direct {v4, v3, v2}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    :goto_1
    invoke-interface {v0, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_7
    return-object v0
.end method

.method public static getVersion(Lcom/xiaomi/push/service/OnlineConfig;Lcom/xiaomi/xmpush/thrift/ConfigListType;)I
    .locals 3

    invoke-static {p1}, Lcom/xiaomi/push/service/OnlineConfigHelper;->getVersionKey(Lcom/xiaomi/xmpush/thrift/ConfigListType;)Ljava/lang/String;

    move-result-object v0

    sget-object v1, Lcom/xiaomi/push/service/OnlineConfigHelper$1;->$SwitchMap$com$xiaomi$xmpush$thrift$ConfigListType:[I

    invoke-virtual {p1}, Lcom/xiaomi/xmpush/thrift/ConfigListType;->ordinal()I

    move-result p1

    aget p1, v1, p1

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eq p1, v1, :cond_0

    const/4 v1, 0x2

    goto :goto_0

    :cond_0
    move v2, v1

    :goto_0
    iget-object p0, p0, Lcom/xiaomi/push/service/OnlineConfig;->preferences:Landroid/content/SharedPreferences;

    invoke-interface {p0, v0, v2}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result p0

    return p0
.end method

.method private static getVersionKey(Lcom/xiaomi/xmpush/thrift/ConfigListType;)Ljava/lang/String;
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "oc_version_"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lcom/xiaomi/xmpush/thrift/ConfigListType;->getValue()I

    move-result p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static setVersion(Lcom/xiaomi/push/service/OnlineConfig;Lcom/xiaomi/xmpush/thrift/ConfigListType;I)V
    .locals 0

    invoke-static {p1}, Lcom/xiaomi/push/service/OnlineConfigHelper;->getVersionKey(Lcom/xiaomi/xmpush/thrift/ConfigListType;)Ljava/lang/String;

    move-result-object p1

    iget-object p0, p0, Lcom/xiaomi/push/service/OnlineConfig;->preferences:Landroid/content/SharedPreferences;

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->commit()Z

    return-void
.end method

.method public static updateCustomConfigs(Lcom/xiaomi/push/service/OnlineConfig;Lcom/xiaomi/xmpush/thrift/XmPushActionCustomConfig;)V
    .locals 1

    invoke-virtual {p1}, Lcom/xiaomi/xmpush/thrift/XmPushActionCustomConfig;->getCustomConfigs()Ljava/util/List;

    move-result-object p1

    const/4 v0, 0x1

    invoke-static {p1, v0}, Lcom/xiaomi/push/service/OnlineConfigHelper;->convertMessage(Ljava/util/List;Z)Ljava/util/List;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/xiaomi/push/service/OnlineConfig;->updateCustomConfigs(Ljava/util/List;)V

    invoke-virtual {p0}, Lcom/xiaomi/push/service/OnlineConfig;->runCallback()V

    return-void
.end method

.method public static updateNormalConfigs(Lcom/xiaomi/push/service/OnlineConfig;Lcom/xiaomi/xmpush/thrift/XmPushActionNormalConfig;)V
    .locals 3

    invoke-virtual {p1}, Lcom/xiaomi/xmpush/thrift/XmPushActionNormalConfig;->getNormalConfigs()Ljava/util/List;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/xiaomi/xmpush/thrift/NormalConfig;

    invoke-virtual {v0}, Lcom/xiaomi/xmpush/thrift/NormalConfig;->getVersion()I

    move-result v1

    invoke-virtual {v0}, Lcom/xiaomi/xmpush/thrift/NormalConfig;->getType()Lcom/xiaomi/xmpush/thrift/ConfigListType;

    move-result-object v2

    invoke-static {p0, v2}, Lcom/xiaomi/push/service/OnlineConfigHelper;->getVersion(Lcom/xiaomi/push/service/OnlineConfig;Lcom/xiaomi/xmpush/thrift/ConfigListType;)I

    move-result v2

    if-gt v1, v2, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Lcom/xiaomi/xmpush/thrift/NormalConfig;->getType()Lcom/xiaomi/xmpush/thrift/ConfigListType;

    move-result-object v1

    invoke-virtual {v0}, Lcom/xiaomi/xmpush/thrift/NormalConfig;->getVersion()I

    move-result v2

    invoke-static {p0, v1, v2}, Lcom/xiaomi/push/service/OnlineConfigHelper;->setVersion(Lcom/xiaomi/push/service/OnlineConfig;Lcom/xiaomi/xmpush/thrift/ConfigListType;I)V

    iget-object v0, v0, Lcom/xiaomi/xmpush/thrift/NormalConfig;->configItems:Ljava/util/List;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/xiaomi/push/service/OnlineConfigHelper;->convertMessage(Ljava/util/List;Z)Ljava/util/List;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/xiaomi/push/service/OnlineConfig;->updateNormalConfigs(Ljava/util/List;)V

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/xiaomi/push/service/OnlineConfig;->runCallback()V

    return-void
.end method
