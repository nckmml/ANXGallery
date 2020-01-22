.class public Lcom/miui/gallery/movie/picker/MovieDependsModuleImpl;
.super Ljava/lang/Object;
.source "MovieDependsModuleImpl.java"

# interfaces
.implements Lcom/miui/gallery/imodule/base/IModuleImpl;
.implements Lcom/miui/gallery/imodule/modules/MovieDependsModule;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getMovieLibraryPath()Ljava/lang/String;
    .locals 1

    invoke-static {}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/util/MovieLibraryLoaderHelper;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->getLibraryDirPath()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getPhotoPagerClass()Ljava/lang/Class;
    .locals 1

    const-class v0, Lcom/miui/gallery/activity/ExternalPhotoPageActivity;

    return-object v0
.end method

.method public getStoryPickClass()Ljava/lang/Class;
    .locals 1

    const-class v0, Lcom/miui/gallery/movie/picker/MovieStoryPickActivity;

    return-object v0
.end method

.method public scanSingleFile(Landroid/content/Context;Ljava/lang/String;)V
    .locals 0

    invoke-static {p1, p2}, Lcom/miui/gallery/scanner/MediaScanner;->scanSingleFile(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method
