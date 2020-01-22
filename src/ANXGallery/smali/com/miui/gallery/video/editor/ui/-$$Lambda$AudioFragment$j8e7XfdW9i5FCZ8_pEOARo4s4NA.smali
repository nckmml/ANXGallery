.class public final synthetic Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$j8e7XfdW9i5FCZ8_pEOARo4s4NA;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/video/editor/VideoEditor$OnCompletedListener;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$j8e7XfdW9i5FCZ8_pEOARo4s4NA;->f$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    return-void
.end method


# virtual methods
.method public final onCompleted()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$j8e7XfdW9i5FCZ8_pEOARo4s4NA;->f$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->lambda$updateVoiceState$131$AudioFragment()V

    return-void
.end method
