.class final Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;
.super Ljava/lang/Object;
.source "NoRepeatContentObserver.java"

# interfaces
.implements Lio/reactivex/FlowableOnSubscribe;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/album/NoRepeatContentObserver;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "ContentOnSubscribe"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;",
        ">",
        "Ljava/lang/Object;",
        "Lio/reactivex/FlowableOnSubscribe<",
        "TT;>;"
    }
.end annotation


# instance fields
.field private mEmitter:Lio/reactivex/FlowableEmitter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lio/reactivex/FlowableEmitter<",
            "TT;>;"
        }
    .end annotation
.end field

.field final synthetic this$0:Lcom/miui/gallery/ui/album/NoRepeatContentObserver;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/ui/album/NoRepeatContentObserver;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;->this$0:Lcom/miui/gallery/ui/album/NoRepeatContentObserver;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/album/NoRepeatContentObserver;Lcom/miui/gallery/ui/album/NoRepeatContentObserver$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;-><init>(Lcom/miui/gallery/ui/album/NoRepeatContentObserver;)V

    return-void
.end method


# virtual methods
.method public getEmitter()Lio/reactivex/FlowableEmitter;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/FlowableEmitter<",
            "TT;>;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;->mEmitter:Lio/reactivex/FlowableEmitter;

    return-object v0
.end method

.method public subscribe(Lio/reactivex/FlowableEmitter;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lio/reactivex/FlowableEmitter<",
            "TT;>;)V"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    iput-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;->mEmitter:Lio/reactivex/FlowableEmitter;

    return-void
.end method
