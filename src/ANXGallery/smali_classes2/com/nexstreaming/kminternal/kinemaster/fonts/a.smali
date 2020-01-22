.class Lcom/nexstreaming/kminternal/kinemaster/fonts/a;
.super Ljava/lang/Object;
.source "BuiltInFonts.java"


# direct methods
.method private static a(Ljava/lang/String;)Ljava/lang/String;
    .locals 8

    new-instance v0, Ljava/lang/StringBuffer;

    invoke-direct {v0}, Ljava/lang/StringBuffer;-><init>()V

    invoke-virtual {p0}, Ljava/lang/String;->toCharArray()[C

    move-result-object p0

    array-length v1, p0

    const/4 v2, 0x0

    const/4 v3, 0x1

    move v4, v2

    move v5, v3

    :goto_0
    if-ge v4, v1, :cond_4

    aget-char v6, p0, v4

    if-eqz v5, :cond_1

    const/16 v5, 0x61

    if-lt v6, v5, :cond_0

    const/16 v5, 0x7a

    if-gt v6, v5, :cond_0

    add-int/lit8 v6, v6, -0x20

    int-to-char v6, v6

    :cond_0
    invoke-virtual {v0, v6}, Ljava/lang/StringBuffer;->append(C)Ljava/lang/StringBuffer;

    move v5, v2

    goto :goto_2

    :cond_1
    const/16 v7, 0x5f

    if-eq v6, v7, :cond_3

    const/16 v7, 0x2d

    if-ne v6, v7, :cond_2

    goto :goto_1

    :cond_2
    invoke-virtual {v0, v6}, Ljava/lang/StringBuffer;->append(C)Ljava/lang/StringBuffer;

    goto :goto_2

    :cond_3
    :goto_1
    const/16 v5, 0x20

    invoke-virtual {v0, v5}, Ljava/lang/StringBuffer;->append(C)Ljava/lang/StringBuffer;

    move v5, v3

    :goto_2
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_4
    invoke-virtual {v0}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static a()Ljava/util/List;
    .locals 16
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;",
            ">;"
        }
    .end annotation

    const-string v0, "bevan.ttf"

    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    const/16 v2, 0x10

    new-array v2, v2, [Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v4, "sans-serif-thin"

    const/4 v5, 0x0

    invoke-static {v4, v5}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v6

    const-string v7, "android"

    const-string v8, "system.robotothin"

    const-string v9, "Roboto Thin"

    invoke-direct {v3, v8, v7, v6, v9}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    aput-object v3, v2, v5

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v6, "sans-serif-light"

    invoke-static {v6, v5}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v8

    const-string v9, "system.robotolight"

    const-string v10, "Roboto Light"

    invoke-direct {v3, v9, v7, v8, v10}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/4 v8, 0x1

    aput-object v3, v2, v8

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v9, "sans-serif"

    invoke-static {v9, v5}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v10

    const-string v11, "system.droidsans"

    const-string v12, "Roboto Regular"

    invoke-direct {v3, v11, v7, v10, v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/4 v10, 0x2

    aput-object v3, v2, v10

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v9, v8}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v11

    const-string v12, "system.droidsansb"

    const-string v13, "Roboto Bold"

    invoke-direct {v3, v12, v7, v11, v13}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/4 v11, 0x3

    aput-object v3, v2, v11

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v4, v10}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v4

    const-string v12, "system.robotonthini"

    const-string v13, "Roboto Thin Italic"

    invoke-direct {v3, v12, v7, v4, v13}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/4 v4, 0x4

    aput-object v3, v2, v4

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v6, v10}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v6

    const-string v12, "system.robotolighti"

    const-string v13, "Roboto Light Italic"

    invoke-direct {v3, v12, v7, v6, v13}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/4 v6, 0x5

    aput-object v3, v2, v6

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v9, v10}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v6

    const-string v12, "system.robotoi"

    const-string v13, "Roboto Regular Italic"

    invoke-direct {v3, v12, v7, v6, v13}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/4 v6, 0x6

    aput-object v3, v2, v6

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v9, v11}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v6

    const-string v9, "system.robotobi"

    const-string v12, "Roboto Bold Italic"

    invoke-direct {v3, v9, v7, v6, v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/4 v6, 0x7

    aput-object v3, v2, v6

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v6, "sans-serif-condensed"

    invoke-static {v6, v5}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v9

    const-string v12, "system.robotocond"

    const-string v13, "Roboto Condensed Regular"

    invoke-direct {v3, v12, v7, v9, v13}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/16 v9, 0x8

    aput-object v3, v2, v9

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v6, v10}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v9

    const-string v12, "system.robotocondi"

    const-string v13, "Roboto Condensed Italic"

    invoke-direct {v3, v12, v7, v9, v13}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/16 v9, 0x9

    aput-object v3, v2, v9

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v6, v8}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v9

    const-string v12, "system.robotocondb"

    const-string v13, "Roboto Condensed Bold"

    invoke-direct {v3, v12, v7, v9, v13}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/16 v9, 0xa

    aput-object v3, v2, v9

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v6, v11}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v6

    const-string v9, "system.robotocondbi"

    const-string v12, "Roboto Condensed Bold Italic"

    invoke-direct {v3, v9, v7, v6, v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/16 v6, 0xb

    aput-object v3, v2, v6

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    sget-object v6, Landroid/graphics/Typeface;->SERIF:Landroid/graphics/Typeface;

    invoke-static {v6, v5}, Landroid/graphics/Typeface;->create(Landroid/graphics/Typeface;I)Landroid/graphics/Typeface;

    move-result-object v6

    const-string v9, "system.droidserif"

    const-string v12, "Noto Serif Regular"

    invoke-direct {v3, v9, v7, v6, v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/16 v6, 0xc

    aput-object v3, v2, v6

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    sget-object v6, Landroid/graphics/Typeface;->SERIF:Landroid/graphics/Typeface;

    invoke-static {v6, v8}, Landroid/graphics/Typeface;->create(Landroid/graphics/Typeface;I)Landroid/graphics/Typeface;

    move-result-object v6

    const-string v9, "system.droidserifb"

    const-string v12, "Noto Serif Bold"

    invoke-direct {v3, v9, v7, v6, v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/16 v6, 0xd

    aput-object v3, v2, v6

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    sget-object v6, Landroid/graphics/Typeface;->SERIF:Landroid/graphics/Typeface;

    invoke-static {v6, v10}, Landroid/graphics/Typeface;->create(Landroid/graphics/Typeface;I)Landroid/graphics/Typeface;

    move-result-object v6

    const-string v9, "system.droidserifi"

    const-string v12, "Noto Serif Italic"

    invoke-direct {v3, v9, v7, v6, v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/16 v6, 0xe

    aput-object v3, v2, v6

    new-instance v3, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    sget-object v6, Landroid/graphics/Typeface;->SERIF:Landroid/graphics/Typeface;

    invoke-static {v6, v11}, Landroid/graphics/Typeface;->create(Landroid/graphics/Typeface;I)Landroid/graphics/Typeface;

    move-result-object v6

    const-string v9, "system.droidserifbi"

    const-string v12, "Noto Serif Bold Italic"

    invoke-direct {v3, v9, v7, v6, v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/16 v6, 0xf

    aput-object v3, v2, v6

    invoke-static {v2}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    invoke-static {}, Lcom/nexstreaming/kminternal/kinemaster/config/a;->a()Lcom/nexstreaming/kminternal/kinemaster/config/a;

    move-result-object v2

    invoke-virtual {v2}, Lcom/nexstreaming/kminternal/kinemaster/config/a;->b()Landroid/content/Context;

    move-result-object v2

    :try_start_0
    invoke-virtual {v2}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v2

    const-string v3, ""

    invoke-virtual {v2, v3}, Landroid/content/res/AssetManager;->list(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v2

    array-length v3, v2

    move v6, v5

    :goto_0
    if-ge v6, v3, :cond_11

    aget-object v9, v2, v6

    const-string v12, ".ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v12

    if-eqz v12, :cond_10

    invoke-virtual {v9, v0}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    const-string v13, "latin"

    if-nez v12, :cond_0

    :try_start_1
    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.bevan"

    const-string v14, "Bevan"

    invoke-direct {v9, v12, v13, v0, v14}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_0
    const-string v12, "creepster-regular.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_1

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.creepster"

    const-string v14, "creepster-regular.ttf"

    const-string v15, "Creepster"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_1
    const-string v12, "goudy_stm_italic.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_2

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.sortsmillgoudyital"

    const-string v14, "goudy_stm_italic.ttf"

    const-string v15, "Sorts Mill Goudy Italic"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_2
    const-string v12, "greatvibes-regular.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_3

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.greatvibes"

    const-string v14, "greatvibes-regular.ttf"

    const-string v15, "Great Vibes"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_3
    const-string v12, "junction.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_4

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.junction"

    const-string v14, "junction.ttf"

    const-string v15, "Junction"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_4
    const-string v12, "knewave.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_5

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.knewave"

    const-string v14, "knewave.ttf"

    const-string v15, "Knewave"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_5
    const-string v12, "lato-bold.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_6

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.latobold"

    const-string v14, "lato-bold.ttf"

    const-string v15, "Lato Bold"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_6
    const-string v12, "leaguegothic.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_7

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.leaguegothic"

    const-string v14, "leaguegothic.ttf"

    const-string v15, "League Gothic"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_7
    const-string v12, "leaguescript.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_8

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.leaguescriptthin"

    const-string v14, "leaguescript.ttf"

    const-string v15, "League Script"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_8
    const-string v12, "lindenhill.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_9

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.lindenhillregular"

    const-string v14, "lindenhill.ttf"

    const-string v15, "Linden Hill"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_9
    const-string v12, "orbitron-bold.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_a

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.orbitronbold"

    const-string v14, "orbitron-bold.ttf"

    const-string v15, "Orbitron Bold"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_a
    const-string v12, "orbitron-medium.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_b

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.orbitronmedium"

    const-string v14, "orbitron-medium.ttf"

    const-string v15, "Orbitron Medium"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :cond_b
    const-string v12, "raleway_thin.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_c

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.ralewaythin"

    const-string v14, "raleway_thin.ttf"

    const-string v15, "Raleway Thin"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_c
    const-string v12, "redressed.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_d

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.redressedregular"

    const-string v14, "redressed.ttf"

    const-string v15, "Redressed"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_d
    const-string v12, "sniglet.ttf"

    invoke-virtual {v9, v12}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v12

    if-nez v12, :cond_e

    new-instance v9, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v12, "builtin.font.sniglet"

    const-string v14, "sniglet.ttf"

    const-string v15, "Sniglet"

    invoke-direct {v9, v12, v13, v14, v15}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_e
    const-string v12, "_H_"

    invoke-virtual {v9, v12}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v12

    if-eqz v12, :cond_f

    goto :goto_1

    :cond_f
    invoke-virtual {v9}, Ljava/lang/String;->length()I

    move-result v12

    sub-int/2addr v12, v4

    invoke-virtual {v9, v5, v12}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v12

    new-instance v14, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "builtin.font."

    invoke-virtual {v15, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/a;->b(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v15, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v15}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v11

    invoke-static {v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/a;->a(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12

    invoke-direct {v14, v11, v13, v9, v12}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v14}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0

    :cond_10
    :goto_1
    add-int/lit8 v6, v6, 0x1

    const/4 v11, 0x3

    goto/16 :goto_0

    :catch_0
    move-exception v0

    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    :cond_11
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x15

    if-lt v0, v2, :cond_12

    new-array v0, v4, [Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    new-instance v2, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v3, "sans-serif-medium"

    invoke-static {v3, v5}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v4

    const-string v6, "system.robotomed"

    const-string v9, "Roboto Medium"

    invoke-direct {v2, v6, v7, v4, v9}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    aput-object v2, v0, v5

    new-instance v2, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v3, v10}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v3

    const-string v4, "system.robotomedi"

    const-string v6, "Roboto Medium Italic"

    invoke-direct {v2, v4, v7, v3, v6}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    aput-object v2, v0, v8

    new-instance v2, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v3, "sans-serif-black"

    invoke-static {v3, v5}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v4

    const-string v6, "system.robotoblk"

    const-string v9, "Roboto Black"

    invoke-direct {v2, v6, v7, v4, v9}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    aput-object v2, v0, v10

    new-instance v2, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v3, v10}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v3

    const-string v4, "system.robotoblki"

    const-string v6, "Roboto Black Italic"

    invoke-direct {v2, v4, v7, v3, v6}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    const/4 v3, 0x3

    aput-object v2, v0, v3

    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    invoke-interface {v1, v0}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    new-array v0, v3, [Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    new-instance v2, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v3, "cursive"

    invoke-static {v3, v5}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v4

    const-string v6, "system.cursive"

    const-string v9, "Dancing Script Regular"

    invoke-direct {v2, v6, v7, v4, v9}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    aput-object v2, v0, v5

    new-instance v2, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    invoke-static {v3, v8}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v3

    const-string v4, "system.cursiveb"

    const-string v6, "Dancing Script Bold"

    invoke-direct {v2, v4, v7, v3, v6}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    aput-object v2, v0, v8

    new-instance v2, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;

    const-string v3, "monospace"

    invoke-static {v3, v5}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object v3

    const-string v4, "system.mono"

    const-string v5, "Droid Sans Mono"

    invoke-direct {v2, v4, v7, v3, v5}, Lcom/nexstreaming/kminternal/kinemaster/fonts/Font;-><init>(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Typeface;Ljava/lang/String;)V

    aput-object v2, v0, v10

    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    invoke-interface {v1, v0}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    :cond_12
    return-object v1
.end method

.method private static b(Ljava/lang/String;)Ljava/lang/String;
    .locals 5

    new-instance v0, Ljava/lang/StringBuffer;

    invoke-direct {v0}, Ljava/lang/StringBuffer;-><init>()V

    invoke-virtual {p0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->toCharArray()[C

    move-result-object p0

    array-length v1, p0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_2

    aget-char v3, p0, v2

    const/16 v4, 0x5f

    if-eq v3, v4, :cond_1

    const/16 v4, 0x2d

    if-eq v3, v4, :cond_1

    const/16 v4, 0x20

    if-ne v3, v4, :cond_0

    goto :goto_1

    :cond_0
    invoke-virtual {v0, v3}, Ljava/lang/StringBuffer;->append(C)Ljava/lang/StringBuffer;

    :cond_1
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    invoke-virtual {v0}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
