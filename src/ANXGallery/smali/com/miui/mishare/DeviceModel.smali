.class public Lcom/miui/mishare/DeviceModel;
.super Ljava/lang/Object;
.source "DeviceModel.java"


# direct methods
.method public static getDeviceName(Landroid/content/Context;BI)Ljava/lang/String;
    .locals 0

    invoke-static {p1, p2}, Lcom/miui/mishare/DeviceModel;->getDeviceRes(BI)I

    move-result p1

    invoke-virtual {p0, p1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static getDeviceRes(BI)I
    .locals 1

    const/16 v0, 0x14

    if-lt p0, v0, :cond_0

    const/16 v0, 0x1d

    if-gt p0, v0, :cond_0

    sget p0, Lcom/miui/mishare/R$string;->vivo_phone:I

    return p0

    :cond_0
    const/16 v0, 0xa

    if-lt p0, v0, :cond_1

    const/16 v0, 0x13

    if-gt p0, v0, :cond_1

    sget p0, Lcom/miui/mishare/R$string;->oppo_phone:I

    return p0

    :cond_1
    const/16 v0, 0x1e

    if-lt p0, v0, :cond_2

    const/16 v0, 0x27

    if-gt p0, v0, :cond_2

    packed-switch p1, :pswitch_data_0

    packed-switch p1, :pswitch_data_1

    packed-switch p1, :pswitch_data_2

    packed-switch p1, :pswitch_data_3

    packed-switch p1, :pswitch_data_4

    packed-switch p1, :pswitch_data_5

    sget p0, Lcom/miui/mishare/R$string;->xiaomi_phone:I

    return p0

    :pswitch_0
    sget p0, Lcom/miui/mishare/R$string;->mi_a3:I

    return p0

    :pswitch_1
    sget p0, Lcom/miui/mishare/R$string;->mi_9t_pro:I

    return p0

    :pswitch_2
    sget p0, Lcom/miui/mishare/R$string;->mi_9t:I

    return p0

    :pswitch_3
    sget p0, Lcom/miui/mishare/R$string;->redmi_k20_pro:I

    return p0

    :pswitch_4
    sget p0, Lcom/miui/mishare/R$string;->redmi_k20:I

    return p0

    :pswitch_5
    sget p0, Lcom/miui/mishare/R$string;->redmi_note_5:I

    return p0

    :pswitch_6
    sget p0, Lcom/miui/mishare/R$string;->redmi_7a:I

    return p0

    :pswitch_7
    sget p0, Lcom/miui/mishare/R$string;->redmi_6_pro:I

    return p0

    :pswitch_8
    sget p0, Lcom/miui/mishare/R$string;->redmi_6a:I

    return p0

    :pswitch_9
    sget p0, Lcom/miui/mishare/R$string;->redmi_6:I

    return p0

    :pswitch_a
    sget p0, Lcom/miui/mishare/R$string;->redmi_note_7_pro:I

    return p0

    :pswitch_b
    sget p0, Lcom/miui/mishare/R$string;->redmi_note_7:I

    return p0

    :pswitch_c
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_note_3:I

    return p0

    :pswitch_d
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_max_3:I

    return p0

    :pswitch_e
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_poco_f1:I

    return p0

    :pswitch_f
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_cc_9_custom:I

    return p0

    :pswitch_10
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_cc_9e:I

    return p0

    :pswitch_11
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_cc_9:I

    return p0

    :pswitch_12
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_mix_3_5g:I

    return p0

    :pswitch_13
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_mix_3:I

    return p0

    :pswitch_14
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_mix_2s:I

    return p0

    :pswitch_15
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_mix_2:I

    return p0

    :pswitch_16
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_6:I

    return p0

    :pswitch_17
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_8_exp:I

    return p0

    :pswitch_18
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_8_young:I

    return p0

    :pswitch_19
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_8_ud:I

    return p0

    :pswitch_1a
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_8_se:I

    return p0

    :pswitch_1b
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_8:I

    return p0

    :pswitch_1c
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_9_se:I

    return p0

    :pswitch_1d
    sget p0, Lcom/miui/mishare/R$string;->xiaomi_9:I

    return p0

    :cond_2
    sget p0, Lcom/miui/mishare/R$string;->unrecognized_model:I

    return p0

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1d
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_16
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x101
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x201
        :pswitch_11
        :pswitch_10
        :pswitch_f
    .end packed-switch

    :pswitch_data_3
    .packed-switch 0xf01
        :pswitch_e
        :pswitch_d
        :pswitch_c
    .end packed-switch

    :pswitch_data_4
    .packed-switch 0x1001
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
    .end packed-switch

    :pswitch_data_5
    .packed-switch 0x2001
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
