.class Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;
.super Landroid/os/Handler;
.source "NumberPicker.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/widget/picker/NumberPicker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "SoundPlayHandler"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;
    }
.end annotation


# static fields
.field private static sPlayerContainer:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;


# direct methods
.method constructor <init>(Landroid/os/Looper;Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    sget-object p1, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->sPlayerContainer:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;

    if-nez p1, :cond_0

    invoke-static {p2}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->newInstance(Landroid/content/Context;)Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;

    move-result-object p1

    sput-object p1, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->sPlayerContainer:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;

    :cond_0
    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)V
    .locals 2

    invoke-super {p0, p1}, Landroid/os/Handler;->handleMessage(Landroid/os/Message;)V

    iget v0, p1, Landroid/os/Message;->what:I

    if-eqz v0, :cond_2

    const/4 v1, 0x1

    if-eq v0, v1, :cond_1

    const/4 v1, 0x2

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    sget-object v0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->sPlayerContainer:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;

    if-eqz v0, :cond_3

    iget p1, p1, Landroid/os/Message;->arg1:I

    invoke-virtual {v0, p1}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->release(I)V

    const/4 p1, 0x0

    sput-object p1, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->sPlayerContainer:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;

    goto :goto_0

    :cond_1
    sget-object p1, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->sPlayerContainer:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;

    if-eqz p1, :cond_3

    invoke-virtual {p1}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->play()V

    goto :goto_0

    :cond_2
    sget-object v0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->sPlayerContainer:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;

    if-eqz v0, :cond_3

    iget p1, p1, Landroid/os/Message;->arg1:I

    invoke-virtual {v0, p1}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->init(I)V

    :cond_3
    :goto_0
    return-void
.end method

.method init(I)V
    .locals 1

    const/4 v0, 0x0

    invoke-virtual {p0, v0, p1, v0}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->obtainMessage(III)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->sendMessage(Landroid/os/Message;)Z

    return-void
.end method

.method play()V
    .locals 1

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->sendMessage(Landroid/os/Message;)Z

    return-void
.end method

.method release(I)V
    .locals 2

    const/4 v0, 0x2

    const/4 v1, 0x0

    invoke-virtual {p0, v0, p1, v1}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->obtainMessage(III)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->sendMessage(Landroid/os/Message;)Z

    return-void
.end method

.method stop()V
    .locals 1

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->removeMessages(I)V

    return-void
.end method
