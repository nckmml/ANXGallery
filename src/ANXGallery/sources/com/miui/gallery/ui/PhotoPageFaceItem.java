package com.miui.gallery.ui;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.activity.facebaby.FacePageActivity;
import com.miui.gallery.cloud.peopleface.PeopleFace;
import com.miui.gallery.data.CacheOfAllFacesInOnePhoto;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.CloudItem;
import com.miui.gallery.model.PeopleContactInfo;
import com.miui.gallery.provider.deprecated.NormalPeopleFaceMediaSet;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.ui.PhotoPageItem;
import com.miui.gallery.ui.renameface.FaceAlbumHandlerBase;
import com.miui.gallery.ui.renameface.FaceAlbumRenameHandler;
import com.miui.gallery.ui.renameface.InputFaceNameFragment;
import com.miui.gallery.ui.renameface.RemoveFromFaceAlbumHandler;
import com.miui.gallery.util.photoview.ItemViewInfo;
import com.miui.gallery.util.photoview.PhotoPageDataCache;
import java.util.ArrayList;
import java.util.Iterator;

public class PhotoPageFaceItem extends PhotoPageItem implements CacheOfAllFacesInOnePhoto.PhotoViewProvider {
    /* access modifiers changed from: private */
    public FaceHandler mFaceHandler;
    private FaceHighLightManager mFaceHighLightManager;
    private FaceNameLabelsManager mFaceNameLablesManager;

    private class FaceHandler {
        PeopleFace mCurrentFace;
        FaceAlbumRenameHandler mFaceAlbumRenameHandler;
        RemoveFromFaceAlbumHandler mRemoveFromFaceAlbumHandler;
        private FaceAlbumHandlerBase.FaceAlbumHandlerListener mRemoveFromFaceAlbumListener;

        private FaceHandler() {
            this.mRemoveFromFaceAlbumListener = new FaceAlbumHandlerBase.FaceAlbumHandlerListener() {
                private void doRemove(FaceAlbumHandlerBase.FaceFolderItem faceFolderItem) {
                    NormalPeopleFaceMediaSet.doMoveFacesToAnother(faceFolderItem, new long[]{Long.parseLong(FaceHandler.this.mCurrentFace._id)});
                    CacheOfAllFacesInOnePhoto.getInstance().clearCache();
                    if (PhotoPageFaceItem.this.mPhotoPageInteractionListener != null) {
                        PhotoPageFaceItem.this.mPhotoPageInteractionListener.notifyDataChanged();
                    }
                }

                public void onGetFolderItem(FaceAlbumHandlerBase.FaceFolderItem faceFolderItem) {
                    doRemove(faceFolderItem);
                }
            };
        }

        public void exit() {
            ((Activity) PhotoPageFaceItem.this.getContext()).onBackPressed();
        }

        public String getRecommendFaceId() {
            return PhotoPageDataCache.getInstance().getString("recommend_face_id", (String) null);
        }

        public void notifyChanged() {
            if (PhotoPageFaceItem.this.isAttachedToWindow()) {
                ThreadManager.getMainHandler().post(new Runnable() {
                    public void run() {
                        PhotoPageFaceItem.this.bindFaceAnymore(PhotoPageFaceItem.this.mDataItem);
                    }
                });
            }
        }

        public void removeFace(PeopleFace peopleFace) {
            this.mRemoveFromFaceAlbumHandler = new RemoveFromFaceAlbumHandler((Activity) PhotoPageFaceItem.this.getContext(), new NormalPeopleFaceMediaSet(Long.parseLong(peopleFace.localGroupId), peopleFace.groupId, peopleFace.peopleName), this.mRemoveFromFaceAlbumListener);
            this.mCurrentFace = peopleFace;
            this.mRemoveFromFaceAlbumHandler.show();
        }

        public void renameFace(PeopleFace peopleFace) {
            this.mFaceAlbumRenameHandler = new FaceAlbumRenameHandler((Activity) PhotoPageFaceItem.this.getContext(), new NormalPeopleFaceMediaSet(Long.parseLong(peopleFace.localGroupId), peopleFace.groupId, peopleFace.peopleName), new FaceAlbumRenameHandler.ConfirmListener() {
                public void onConfirm(String str, boolean z) {
                    CacheOfAllFacesInOnePhoto.getInstance().clearCache();
                    if (PhotoPageFaceItem.this.mPhotoPageInteractionListener != null) {
                        PhotoPageFaceItem.this.mPhotoPageInteractionListener.notifyDataChanged();
                    }
                }
            }, !PeopleContactInfo.isUnKnownRelation(peopleFace.relationType));
            this.mFaceAlbumRenameHandler.show();
        }

        public boolean shouldShow() {
            return false;
        }
    }

    private class FaceHighLightManager implements View.OnClickListener {
        private boolean isAnimatorStarted;
        private boolean isExiting;
        private ValueAnimator mAnimator;
        /* access modifiers changed from: private */
        public float mCurrentAlpha;
        /* access modifiers changed from: private */
        public int mCurrentRadius;
        /* access modifiers changed from: private */
        public int mCurrentX;
        /* access modifiers changed from: private */
        public int mCurrentY;
        /* access modifiers changed from: private */
        public float mExitAlpha;
        private int mOrientation;
        private Paint mPaint;
        private PeopleFace mPeopleFace;
        /* access modifiers changed from: private */
        public int mStartRadius;
        /* access modifiers changed from: private */
        public int mStartX;
        /* access modifiers changed from: private */
        public int mStartY;
        /* access modifiers changed from: private */
        public int mTargetRadius;
        /* access modifiers changed from: private */
        public int mTargetX;
        /* access modifiers changed from: private */
        public int mTargetY;
        private PorterDuffXfermode mXfermode;

        private FaceHighLightManager() {
            this.mOrientation = 0;
            this.mExitAlpha = 1.0f;
            this.isAnimatorStarted = false;
            this.isExiting = false;
            this.mPaint = new Paint();
            this.mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        }

        private void initTargetValues() {
            if (this.mPeopleFace != null) {
                RectF drawableSize = PhotoPageFaceItem.this.mPhotoView.getDrawableSize();
                if (drawableSize.width() != 0.0f && drawableSize.height() != 0.0f) {
                    RectF access$900 = PhotoPageFaceItem.getFaceRect(drawableSize.width(), drawableSize.height(), this.mPeopleFace, this.mOrientation);
                    PhotoPageFaceItem.this.mPhotoView.getBaseMatrix().mapRect(access$900);
                    this.mTargetRadius = (int) Math.max(access$900.width(), access$900.height());
                    this.mTargetX = (int) access$900.centerX();
                    this.mTargetY = (int) access$900.centerY();
                }
            }
        }

        /* access modifiers changed from: protected */
        public void bindViewAndData(CloudItem cloudItem, ArrayList<PeopleFace> arrayList) {
            if (isVisible() && !this.isAnimatorStarted) {
                PhotoPageFaceItem.this.setOnClickListener(this);
                if (cloudItem != null && !TextUtils.isEmpty(cloudItem.getServerId()) && arrayList != null) {
                    this.mOrientation = cloudItem.getOrientation();
                    Iterator<PeopleFace> it = arrayList.iterator();
                    while (it.hasNext()) {
                        PeopleFace next = it.next();
                        if (PhotoPageFaceItem.this.mFaceHandler.getRecommendFaceId().equals(next.serverId)) {
                            this.mPeopleFace = next;
                            initTargetValues();
                            PhotoPageFaceItem.this.invalidate();
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public boolean isExiting() {
            return this.isExiting;
        }

        /* access modifiers changed from: protected */
        public boolean isVisible() {
            return (PhotoPageFaceItem.this.mFaceHandler == null || PhotoPageFaceItem.this.mFaceHandler.getRecommendFaceId() == null) ? false : true;
        }

        public void onClick(View view) {
            if (PhotoPageFaceItem.this.mFaceHandler != null) {
                PhotoPageFaceItem.this.mFaceHandler.exit();
            }
        }

        /* access modifiers changed from: protected */
        public void onDestroy() {
            this.mTargetRadius = 0;
            this.mPeopleFace = null;
            this.isAnimatorStarted = false;
            this.isExiting = false;
            this.mExitAlpha = 1.0f;
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.mAnimator.end();
            }
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            if (this.mPeopleFace != null && this.mTargetRadius > 0) {
                if (!this.isAnimatorStarted) {
                    this.mAnimator = new ValueAnimator();
                    this.mAnimator.setFloatValues(new float[]{this.mCurrentAlpha, 0.5f});
                    this.mAnimator.setDuration(400);
                    this.mAnimator.setInterpolator(new DecelerateInterpolator(1.5f));
                    this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            float unused = FaceHighLightManager.this.mCurrentAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            float access$1200 = FaceHighLightManager.this.mCurrentAlpha / 0.5f;
                            if (FaceHighLightManager.this.isExiting()) {
                                FaceHighLightManager faceHighLightManager = FaceHighLightManager.this;
                                float unused2 = faceHighLightManager.mCurrentAlpha = faceHighLightManager.mCurrentAlpha / 2.0f;
                                float unused3 = FaceHighLightManager.this.mExitAlpha = ((double) access$1200) > 0.875d ? 1.0f : access$1200 / 0.875f;
                            } else {
                                FaceHighLightManager faceHighLightManager2 = FaceHighLightManager.this;
                                int unused4 = faceHighLightManager2.mCurrentX = (int) ((((float) (faceHighLightManager2.mTargetX - FaceHighLightManager.this.mStartX)) * access$1200) + ((float) FaceHighLightManager.this.mStartX));
                                FaceHighLightManager faceHighLightManager3 = FaceHighLightManager.this;
                                int unused5 = faceHighLightManager3.mCurrentY = (int) ((((float) (faceHighLightManager3.mTargetY - FaceHighLightManager.this.mStartY)) * access$1200) + ((float) FaceHighLightManager.this.mStartY));
                                FaceHighLightManager faceHighLightManager4 = FaceHighLightManager.this;
                                int unused6 = faceHighLightManager4.mCurrentRadius = (int) ((access$1200 * ((float) (faceHighLightManager4.mTargetRadius - FaceHighLightManager.this.mStartRadius))) + ((float) FaceHighLightManager.this.mStartRadius));
                            }
                            PhotoPageFaceItem.this.invalidate();
                        }
                    });
                    this.mAnimator.start();
                    this.isAnimatorStarted = true;
                }
                int saveLayerAlpha = canvas.saveLayerAlpha(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), (int) (this.mCurrentAlpha * 255.0f), 31);
                if (isExiting()) {
                    canvas.setMatrix(PhotoPageFaceItem.this.mPhotoView.getSuppMatrix());
                    Drawable drawable = PhotoPageFaceItem.this.mPhotoView.getDrawable();
                    if (drawable != null) {
                        drawable.setAlpha((int) (this.mExitAlpha * 255.0f));
                    }
                }
                this.mPaint.setAntiAlias(true);
                this.mPaint.setColor(-16777216);
                canvas.drawPaint(this.mPaint);
                this.mPaint.setXfermode(this.mXfermode);
                canvas.drawCircle((float) this.mCurrentX, (float) this.mCurrentY, (float) this.mCurrentRadius, this.mPaint);
                this.mPaint.setXfermode((Xfermode) null);
                canvas.restoreToCount(saveLayerAlpha);
            }
        }

        /* access modifiers changed from: protected */
        public boolean onExiting() {
            ValueAnimator valueAnimator;
            if (this.isAnimatorStarted && (valueAnimator = this.mAnimator) != null && !this.isExiting) {
                if (valueAnimator.isStarted()) {
                    this.mAnimator.pause();
                }
                this.mAnimator.setDuration(200);
                this.mAnimator.reverse();
                this.mAnimator.resume();
                this.isExiting = true;
            }
            return this.isExiting;
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            if (z && !this.isAnimatorStarted) {
                int width = PhotoPageFaceItem.this.getWidth();
                int height = PhotoPageFaceItem.this.getHeight();
                int sqrt = (int) (Math.sqrt(Math.pow((double) width, 2.0d) + Math.pow((double) height, 2.0d)) / 2.0d);
                int i5 = width / 2;
                this.mStartX = i5;
                this.mCurrentX = i5;
                int i6 = height / 2;
                this.mStartY = i6;
                this.mCurrentY = i6;
                this.mStartRadius = sqrt;
                this.mCurrentRadius = sqrt;
            }
        }

        public final void onMatrixChanged() {
            if (isVisible()) {
                initTargetValues();
                PhotoPageFaceItem.this.invalidate();
            }
        }
    }

    private class FaceNameLabelsManager {
        private ArrayList<View> mLables;
        private ArrayList<PeopleFace> mLastFaces;
        private int mOrientation;

        private FaceNameLabelsManager() {
            this.mLables = new ArrayList<>();
            this.mLastFaces = new ArrayList<>();
            this.mOrientation = 0;
        }

        /* access modifiers changed from: private */
        public void bindViewAndData(final CloudItem cloudItem, ArrayList<PeopleFace> arrayList) {
            if (!isVisible()) {
                removeFacelabel();
            } else if (cloudItem == null || TextUtils.isEmpty(cloudItem.getServerId())) {
                removeFacelabel();
            } else if (arrayList != null && !notChangedFaces(arrayList)) {
                this.mOrientation = cloudItem.getOrientation();
                removeFacelabel();
                this.mLastFaces = (ArrayList) arrayList.clone();
                Iterator<PeopleFace> it = arrayList.iterator();
                while (it.hasNext()) {
                    final PeopleFace next = it.next();
                    TextView textView = new TextView(PhotoPageFaceItem.this.mPhotoView.getContext());
                    textView.setText(!TextUtils.isEmpty(next.peopleName) ? next.peopleName : PhotoPageFaceItem.this.mPhotoView.getContext().getString(R.string.who_is_this));
                    textView.setTag(R.id.face_info, next);
                    textView.setGravity(17);
                    textView.setTextSize(0, (float) PhotoPageFaceItem.this.mPhotoView.getContext().getResources().getDimensionPixelSize(R.dimen.face_label_font_size));
                    textView.setTextColor(PhotoPageFaceItem.this.getResources().getColor(R.color.white_80_transparent));
                    textView.setBackgroundResource(R.drawable.face_tag);
                    PhotoPageFaceItem.this.addView(textView, new RelativeLayout.LayoutParams(-2, -2));
                    textView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (!TextUtils.isEmpty(next.peopleName)) {
                                FaceNameLabelsManager.this.showMenuDialog((PeopleFace) view.getTag(R.id.face_info), cloudItem);
                            } else if (PhotoPageFaceItem.this.mFaceHandler != null) {
                                PhotoPageFaceItem.this.mFaceHandler.renameFace(next);
                            }
                        }
                    });
                    this.mLables.add(textView);
                }
            }
        }

        /* access modifiers changed from: private */
        public boolean isVisible() {
            return PhotoPageFaceItem.this.mFaceHandler != null && PhotoPageFaceItem.this.mFaceHandler.shouldShow() && !PhotoPageFaceItem.this.isCheckInAction();
        }

        private boolean notChangedFaces(ArrayList<PeopleFace> arrayList) {
            if (arrayList.size() == this.mLastFaces.size()) {
                int i = 0;
                while (i < arrayList.size() && arrayList.get(i).equalMainInfoWith(this.mLastFaces.get(i))) {
                    i++;
                }
                if (i == arrayList.size()) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            RectF drawableSize = PhotoPageFaceItem.this.mPhotoView.getDrawableSize();
            if (drawableSize.width() != 0.0f && drawableSize.height() != 0.0f) {
                Iterator<View> it = this.mLables.iterator();
                while (it.hasNext()) {
                    View next = it.next();
                    RectF access$900 = PhotoPageFaceItem.getFaceRect(drawableSize.width(), drawableSize.height(), (PeopleFace) next.getTag(R.id.face_info), this.mOrientation);
                    PhotoPageFaceItem.this.mPhotoView.getAbsoluteRect(access$900);
                    int measuredWidth = ((((int) access$900.left) + ((int) access$900.right)) / 2) - (next.getMeasuredWidth() / 2);
                    int measuredHeight = (((int) access$900.top) - next.getMeasuredHeight()) - 20;
                    next.layout(measuredWidth, measuredHeight, next.getMeasuredWidth() + measuredWidth, next.getMeasuredHeight() + measuredHeight);
                }
            }
        }

        private void removeFacelabel() {
            if (this.mLables.size() > 0) {
                Iterator<View> it = this.mLables.iterator();
                while (it.hasNext()) {
                    PhotoPageFaceItem.this.removeView(it.next());
                }
                this.mLables.clear();
            }
            this.mLastFaces.clear();
        }

        /* access modifiers changed from: private */
        public void showMenuDialog(final PeopleFace peopleFace, BaseDataItem baseDataItem) {
            new AlertDialog.Builder(PhotoPageFaceItem.this.mPhotoView.getContext()).setSingleChoiceItems(new String[]{PhotoPageFaceItem.this.mPhotoView.getContext().getString(R.string.view_face_album), PhotoPageFaceItem.this.mPhotoView.getContext().getString(R.string.operation_remove_from_face_album, new Object[]{peopleFace.peopleName})}, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    if (i == 0) {
                        FaceNameLabelsManager.this.viewAlbum(peopleFace);
                    } else if (i != 1) {
                        throw new IllegalStateException("unknown item clicked: " + i);
                    } else if (PhotoPageFaceItem.this.mFaceHandler != null) {
                        PhotoPageFaceItem.this.mFaceHandler.removeFace(peopleFace);
                    }
                }
            }).create().show();
        }

        /* access modifiers changed from: private */
        public void viewAlbum(PeopleFace peopleFace) {
            Intent intent = new Intent();
            String str = peopleFace.groupId;
            String str2 = peopleFace.localGroupId;
            intent.putExtra("server_id_of_album", str);
            intent.putExtra("local_id_of_album", str2);
            intent.putExtra("album_name", peopleFace.peopleName);
            intent.setClass(PhotoPageFaceItem.this.mPhotoView.getContext(), FacePageActivity.class);
            PhotoPageFaceItem.this.mPhotoView.getContext().startActivity(intent);
        }

        public final void onMatrixChanged(RectF rectF) {
            if (isVisible()) {
                onLayout(true, (int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
                PhotoPageFaceItem.this.invalidate();
                return;
            }
            removeFacelabel();
        }
    }

    public PhotoPageFaceItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: private */
    public void bindFaceAnymore(BaseDataItem baseDataItem) {
        CloudItem cloudItem = (CloudItem) baseDataItem;
        ArrayList<PeopleFace> requestFacesOfThePhoto = ((this.mFaceHighLightManager.isVisible() || this.mFaceNameLablesManager.isVisible()) && baseDataItem != null && !TextUtils.isEmpty(cloudItem.getServerId())) ? CacheOfAllFacesInOnePhoto.getInstance().requestFacesOfThePhoto(this, cloudItem.getServerId()) : null;
        this.mFaceNameLablesManager.bindViewAndData(cloudItem, requestFacesOfThePhoto);
        this.mFaceHighLightManager.bindViewAndData(cloudItem, requestFacesOfThePhoto);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0041, code lost:
        r0 = (1.0d - r0) - r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        r14 = (double) r12;
        r12 = (float) (r0 * r14);
        r0 = (double) r13;
        r13 = (float) (r2 * r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0055, code lost:
        return new android.graphics.RectF(r12, r13, ((float) (r14 * r4)) + r12, ((float) (r0 * r6)) + r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0011, code lost:
        r0 = (1.0d - r14.faceYScale) - r14.faceHScale;
        r2 = r14.faceXScale;
        r4 = r14.faceHScale;
        r6 = r14.faceWScale;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0020, code lost:
        r0 = r14.faceYScale;
        r2 = (1.0d - r14.faceXScale) - r14.faceWScale;
        r4 = r14.faceHScale;
        r6 = r14.faceWScale;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002f, code lost:
        r0 = (1.0d - r14.faceXScale) - r14.faceWScale;
        r2 = (1.0d - r14.faceYScale) - r14.faceHScale;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003f, code lost:
        if (r11 == false) goto L_0x0044;
     */
    public static RectF getFaceRect(float f, float f2, PeopleFace peopleFace, int i) {
        double d = peopleFace.faceXScale;
        double d2 = peopleFace.faceYScale;
        double d3 = peopleFace.faceWScale;
        double d4 = peopleFace.faceHScale;
        boolean z = false;
        switch (i) {
            case 2:
                z = true;
                break;
            case 3:
                break;
            case 4:
                z = true;
                break;
            case 5:
                z = true;
                break;
            case 6:
                break;
            case 7:
                z = true;
                break;
            case 8:
                break;
        }
    }

    /* access modifiers changed from: private */
    public boolean isCheckInAction() {
        return this.mCheckManager.inAction();
    }

    public void animExit(ItemViewInfo itemViewInfo, PhotoPageItem.TransitionListener transitionListener) {
        if (this.mFaceHighLightManager.isVisible() && !this.mFaceHighLightManager.isExiting()) {
            this.mFaceHighLightManager.onExiting();
        }
        super.animExit(itemViewInfo, transitionListener);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mFaceHighLightManager.isVisible()) {
            this.mFaceHighLightManager.onDraw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void doOnMatrixChanged(RectF rectF) {
        super.doOnMatrixChanged(rectF);
        FaceNameLabelsManager faceNameLabelsManager = this.mFaceNameLablesManager;
        if (faceNameLabelsManager != null) {
            faceNameLabelsManager.onMatrixChanged(rectF);
        }
        FaceHighLightManager faceHighLightManager = this.mFaceHighLightManager;
        if (faceHighLightManager != null) {
            faceHighLightManager.onMatrixChanged();
        }
    }

    public void onActionBarVisibleChanged(Boolean bool, int i) {
        super.onActionBarVisibleChanged(bool, i);
        bindFaceAnymore(this.mDataItem);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        PeopleContactInfo peopleContactInfo = null;
        if (i == 16) {
            if (intent != null && i2 == -1) {
                peopleContactInfo = InputFaceNameFragment.getContactInfo(getContext(), intent);
            }
            if (this.mFaceHandler.mFaceAlbumRenameHandler != null) {
                this.mFaceHandler.mFaceAlbumRenameHandler.finishWhenGetContact(peopleContactInfo);
            }
        } else if (i == 17) {
            if (intent != null && i2 == -1) {
                peopleContactInfo = InputFaceNameFragment.getContactInfo(getContext(), intent);
            }
            if (this.mFaceHandler.mRemoveFromFaceAlbumHandler != null) {
                this.mFaceHandler.mRemoveFromFaceAlbumHandler.finishWhenGetContact(peopleContactInfo);
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FaceHighLightManager faceHighLightManager = this.mFaceHighLightManager;
        if (faceHighLightManager != null) {
            faceHighLightManager.onDestroy();
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mFaceNameLablesManager = new FaceNameLabelsManager();
        this.mFaceHighLightManager = new FaceHighLightManager();
        this.mFaceHandler = new FaceHandler();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mFaceHighLightManager.isVisible()) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void onInvalidated() {
        FaceHandler faceHandler = this.mFaceHandler;
        if (faceHandler != null) {
            faceHandler.notifyChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mFaceNameLablesManager.isVisible()) {
            this.mFaceNameLablesManager.onLayout(z, i, i2, i3, i4);
        }
        if (this.mFaceHighLightManager.isVisible()) {
            this.mFaceHighLightManager.onLayout(z, i, i2, i3, i4);
        }
    }

    public void swapItem(BaseDataItem baseDataItem) {
        super.swapItem(baseDataItem);
        bindFaceAnymore(baseDataItem);
    }
}
