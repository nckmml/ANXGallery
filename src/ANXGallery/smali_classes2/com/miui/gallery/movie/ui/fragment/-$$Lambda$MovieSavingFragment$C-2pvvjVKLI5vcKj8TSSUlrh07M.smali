.class public final synthetic Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieSavingFragment$C-2pvvjVKLI5vcKj8TSSUlrh07M;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment$OnSavingFinishListener;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

.field private final synthetic f$1:Landroid/content/Context;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieSavingFragment$C-2pvvjVKLI5vcKj8TSSUlrh07M;->f$0:Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

    iput-object p2, p0, Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieSavingFragment$C-2pvvjVKLI5vcKj8TSSUlrh07M;->f$1:Landroid/content/Context;

    return-void
.end method


# virtual methods
.method public final onFinish(ZZLjava/lang/String;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieSavingFragment$C-2pvvjVKLI5vcKj8TSSUlrh07M;->f$0:Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieSavingFragment$C-2pvvjVKLI5vcKj8TSSUlrh07M;->f$1:Landroid/content/Context;

    invoke-virtual {v0, v1, p1, p2, p3}, Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;->lambda$showAndShare$19$MovieSavingFragment(Landroid/content/Context;ZZLjava/lang/String;)V

    return-void
.end method
