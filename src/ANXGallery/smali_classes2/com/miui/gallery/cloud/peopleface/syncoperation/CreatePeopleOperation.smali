.class public Lcom/miui/gallery/cloud/peopleface/syncoperation/CreatePeopleOperation;
.super Lcom/miui/gallery/cloud/peopleface/syncoperation/FaceRequestOperationBase;
.source "CreatePeopleOperation.java"


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/cloud/peopleface/syncoperation/FaceRequestOperationBase;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method protected buildRequest(Landroid/accounts/Account;Lcom/miui/gallery/cloud/RequestItemBase;)Lcom/miui/gallery/cloud/RequestOperationBase$Request;
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    check-cast p2, Lcom/miui/gallery/cloud/peopleface/RequestFaceItem;

    invoke-static {}, Lcom/miui/gallery/cloud/HostManager$PeopleFace;->getPeopleCreateUrl()Ljava/lang/String;

    move-result-object p1

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    new-instance v1, Lorg/apache/http/message/BasicNameValuePair;

    iget-object v2, p2, Lcom/miui/gallery/cloud/peopleface/RequestFaceItem;->face:Lcom/miui/gallery/cloud/peopleface/PeopleFace;

    iget-object v2, v2, Lcom/miui/gallery/cloud/peopleface/PeopleFace;->peopleName:Ljava/lang/String;

    const-string v3, "peopleName"

    invoke-direct {v1, v3, v2}, Lorg/apache/http/message/BasicNameValuePair;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, p2, Lcom/miui/gallery/cloud/peopleface/RequestFaceItem;->face:Lcom/miui/gallery/cloud/peopleface/PeopleFace;

    iget-object v1, v1, Lcom/miui/gallery/cloud/peopleface/PeopleFace;->peopleContactJson:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    new-instance v1, Lorg/apache/http/message/BasicNameValuePair;

    iget-object v2, p2, Lcom/miui/gallery/cloud/peopleface/RequestFaceItem;->face:Lcom/miui/gallery/cloud/peopleface/PeopleFace;

    iget-object v2, v2, Lcom/miui/gallery/cloud/peopleface/PeopleFace;->peopleContactJson:Ljava/lang/String;

    const-string v3, "peopleContact"

    invoke-direct {v1, v3, v2}, Lorg/apache/http/message/BasicNameValuePair;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_0
    new-instance v1, Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;

    invoke-direct {v1}, Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;-><init>()V

    const/4 v2, 0x2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;->setMethod(I)Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;

    move-result-object v1

    invoke-virtual {v1, p1}, Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;->setUrl(Ljava/lang/String;)Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;

    move-result-object p1

    invoke-virtual {p1, v0}, Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;->setParams(Ljava/util/List;)Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;

    move-result-object p1

    iget v0, p2, Lcom/miui/gallery/cloud/peopleface/RequestFaceItem;->createRetryTimes:I

    invoke-virtual {p1, v0}, Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;->setRetryTimes(I)Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;

    move-result-object p1

    iget-boolean p2, p2, Lcom/miui/gallery/cloud/peopleface/RequestFaceItem;->needReRequest:Z

    invoke-virtual {p1, p2}, Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;->setNeedReRequest(Z)Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/cloud/RequestOperationBase$Request$Builder;->build()Lcom/miui/gallery/cloud/RequestOperationBase$Request;

    move-result-object p1

    return-object p1
.end method

.method protected onPreRequest(Lcom/miui/gallery/cloud/RequestItemBase;)Lcom/miui/gallery/cloud/base/GallerySyncCode;
    .locals 1

    instance-of v0, p1, Lcom/miui/gallery/cloud/peopleface/RequestFaceItem;

    if-nez v0, :cond_0

    sget-object p1, Lcom/miui/gallery/cloud/base/GallerySyncCode;->NOT_RETRY_ERROR:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    return-object p1

    :cond_0
    invoke-super {p0, p1}, Lcom/miui/gallery/cloud/peopleface/syncoperation/FaceRequestOperationBase;->onPreRequest(Lcom/miui/gallery/cloud/RequestItemBase;)Lcom/miui/gallery/cloud/base/GallerySyncCode;

    move-result-object p1

    return-object p1
.end method

.method protected onRequestError(Lcom/miui/gallery/cloud/base/GallerySyncCode;Lcom/miui/gallery/cloud/RequestItemBase;Lorg/json/JSONObject;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    sget-object p3, Lcom/miui/gallery/cloud/base/GallerySyncCode;->OK:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-eq p1, p3, :cond_0

    iget p1, p2, Lcom/miui/gallery/cloud/RequestItemBase;->createRetryTimes:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p2, Lcom/miui/gallery/cloud/RequestItemBase;->createRetryTimes:I

    :cond_0
    return-void
.end method

.method protected onRequestSuccess(Lcom/miui/gallery/cloud/RequestItemBase;Lorg/json/JSONObject;)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    check-cast p1, Lcom/miui/gallery/cloud/peopleface/RequestFaceItem;

    const-string v0, "record"

    invoke-virtual {p2, v0}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-static {p2}, Lcom/miui/gallery/cloud/peopleface/FaceAlbumUtil;->getContentValuesForPeopleFace(Lorg/json/JSONObject;)Landroid/content/ContentValues;

    move-result-object p2

    sget-object v0, Lcom/miui/gallery/cloud/peopleface/FaceDataManager;->PEOPLE_FACE_URI:Landroid/net/Uri;

    iget-object p1, p1, Lcom/miui/gallery/cloud/peopleface/RequestFaceItem;->face:Lcom/miui/gallery/cloud/peopleface/PeopleFace;

    iget-object p1, p1, Lcom/miui/gallery/cloud/peopleface/PeopleFace;->_id:Ljava/lang/String;

    invoke-static {v0, p2, p1}, Lcom/miui/gallery/cloud/CloudUtils;->updateToLocalDBForSync(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;)V

    return-void
.end method
