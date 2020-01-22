.class public abstract Lcom/miui/gallery/reddot/RedDot;
.super Ljava/lang/Object;
.source "RedDot.java"

# interfaces
.implements Lcom/miui/gallery/reddot/Rules;


# instance fields
.field mKey:Ljava/lang/String;


# direct methods
.method constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/reddot/RedDot;->mKey:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public getKey()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/reddot/RedDot;->mKey:Ljava/lang/String;

    return-object v0
.end method
