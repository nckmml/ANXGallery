package com.miui.gallery.ui;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.common.base.Strings;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.adapter.SyncStateDisplay;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.BindImageHelper;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.FormatUtil;
import com.miui.gallery.util.SpecialTypeMediaUtils;
import com.miui.gallery.widget.ForegroundImageView;
import com.miui.gallery.widget.editwrapper.PickAnimationHelper;
import com.miui.gallery.widget.recyclerview.transition.TransitionalItem;
import com.miui.gallery.widget.recyclerview.transition.TransitionalView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import java.util.ArrayList;
import java.util.List;

public abstract class MicroThumbGridItem extends RelativeLayout implements CheckableView, PickAnimationHelper.BackgroundImageViewable, PickAnimationHelper.ShowNumberWhenPicking, TransitionalItem, TransitionalView {
    private View mBottomIndicatorContainer;
    private CheckBox mCheckBox;
    private View mCheckBoxContainer;
    private View mFavoriteIndicator;
    protected ImageViewAware mImageAware;
    protected ImageSize mImageSize;
    protected ImageView mImageView;
    private boolean mIsSimilarBestImage;
    private long mItemId;
    private String mItemPath = null;
    private ViewStub mItemStub;
    private long mLastSyncId = -1;
    private ImageView mMask;
    private TextView mPickingNumberIndicator;
    private ImageView mSimilarBestMark;
    private ImageView mSyncIndicator;
    private int mSyncState = Reader.READ_DONE;
    private View mTopIndicatorContainer;
    private TextView mTypeIndicator;

    public MicroThumbGridItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void checkStubInflate() {
        ViewStub viewStub = this.mItemStub;
        if (viewStub != null) {
            View inflate = viewStub.inflate();
            this.mMask = (ImageView) inflate.findViewById(R.id.mask);
            this.mCheckBox = (CheckBox) inflate.findViewById(16908289);
            this.mCheckBoxContainer = inflate.findViewById(R.id.checkbox_container);
            this.mSyncIndicator = (ImageView) inflate.findViewById(R.id.sync_indicator);
            this.mTopIndicatorContainer = inflate.findViewById(R.id.top_indicator_container);
            this.mPickingNumberIndicator = (TextView) inflate.findViewById(R.id.pick_num_indicator);
            this.mSimilarBestMark = (ImageView) inflate.findViewById(R.id.similar_best_mark);
            this.mItemStub = null;
        }
    }

    private static boolean displaySyncableState(int i) {
        return (i & 2) != 0;
    }

    private static boolean displaySyncedState(int i) {
        return (i & 8) != 0;
    }

    private static boolean displaySyncingState(int i) {
        return (i & 4) != 0;
    }

    private static boolean displayUnSyncableState(int i) {
        return (i & 1) != 0;
    }

    private boolean isInCheckMode() {
        View view = this.mCheckBoxContainer;
        return (view == null ? 8 : view.getVisibility()) == 0;
    }

    private boolean needRefreshSyncIndicator(long j, int i, int i2) {
        if (this.mLastSyncId != j) {
            this.mSyncState = Reader.READ_DONE;
            return true;
        } else if (this.mSyncState != i) {
            return true;
        } else {
            ImageView imageView = this.mSyncIndicator;
            int visibility = imageView == null ? 8 : imageView.getVisibility();
            if (needShowSyncIndicator(i, i2)) {
                if (visibility != 0) {
                    return true;
                }
            } else if (visibility == 0) {
                return true;
            }
            return false;
        }
    }

    private boolean needShowSyncIndicator(int i, int i2) {
        if (i == 0 || i == 1) {
            return displaySyncedState(i2);
        }
        if (i == 2) {
            return displaySyncingState(i2);
        }
        if (i == 3) {
            return displaySyncableState(i2);
        }
        if (i != 4) {
            return false;
        }
        return displayUnSyncableState(i2);
    }

    private boolean needShowSyncIndicator(SyncStateDisplay.DisplayScene displayScene) {
        if (displayScene == SyncStateDisplay.DisplayScene.SCENE_ALWAYS) {
            return true;
        }
        return displayScene == SyncStateDisplay.DisplayScene.SCENE_IN_CHECK_MODE && isInCheckMode();
    }

    private void setSyncIndicatorVisibility(int i) {
        if (i == 0) {
            checkStubInflate();
            this.mSyncIndicator.setVisibility(0);
        } else {
            ImageView imageView = this.mSyncIndicator;
            if (imageView != null) {
                imageView.setVisibility(8);
            }
        }
        updateTopIndicatorBg();
    }

    private void setTypeIndicatorVisibility(int i) {
        if (i == 0) {
            this.mTypeIndicator.setVisibility(0);
        } else {
            TextView textView = this.mTypeIndicator;
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
        updateBottomIndicatorBg();
    }

    private void updateSyncIndicator(int i, int i2, Animation animation, Animation animation2) {
        if (!(this.mSyncIndicator == null && i2 == 0)) {
            checkStubInflate();
            this.mSyncIndicator.setImageResource(i2);
        }
        setSyncIndicatorVisibility(i);
        if (animation != null) {
            checkStubInflate();
            this.mSyncIndicator.startAnimation(animation);
        } else {
            ImageView imageView = this.mSyncIndicator;
            if (imageView != null) {
                imageView.clearAnimation();
            }
        }
        if (animation2 != null) {
            checkStubInflate();
            this.mTopIndicatorContainer.startAnimation(animation2);
            return;
        }
        View view = this.mTopIndicatorContainer;
        if (view != null) {
            view.clearAnimation();
        }
    }

    public void bindFavoriteIndicator(boolean z) {
        updateFavoriteIndicatorVisibility((!z || isInCheckMode()) ? 8 : 0);
    }

    public void bindImage(long j, String str, Uri uri, DownloadType downloadType, DisplayImageOptions displayImageOptions) {
        this.mItemId = j;
        this.mItemPath = str;
        BindImageHelper.bindImage(str, uri, downloadType, this.mImageView, displayImageOptions, this.mImageSize);
    }

    public void bindImage(long j, String str, Uri uri, DisplayImageOptions displayImageOptions) {
        bindImage(j, str, uri, DownloadType.MICRO, displayImageOptions);
    }

    public void bindImage(String str, Uri uri, DownloadType downloadType, DisplayImageOptions displayImageOptions) {
        bindImage(-1, str, uri, downloadType, displayImageOptions);
    }

    public void bindImage(String str, Uri uri, DisplayImageOptions displayImageOptions) {
        bindImage(-1, str, uri, DownloadType.MICRO, displayImageOptions);
    }

    public void bindIndicator(String str, long j, long j2) {
        int i;
        String str2 = "";
        int i2 = 0;
        if (FileMimeUtil.isGifFromMimeType(str)) {
            str2 = "GIF";
        } else if (FileMimeUtil.isRawFromMimeType(str)) {
            str2 = getResources().getString(R.string.special_type_raw);
        } else {
            if (FileMimeUtil.isVideoFromMimeType(str)) {
                i = SpecialTypeMediaUtils.tryGetHFRIndicatorResId(j2);
                if (i != 0) {
                    str2 = FormatUtil.formatVideoDuration(j);
                } else {
                    str2 = FormatUtil.formatVideoDuration(j);
                    i = R.drawable.type_indicator_video;
                }
            } else if (SpecialTypeMediaUtils.isRefocusSupported(getContext(), j2)) {
                i = R.drawable.type_indicator_refocus;
            } else if (SpecialTypeMediaUtils.isMotionPhoto(getContext(), j2)) {
                i = R.drawable.type_indicator_motion_photo;
            } else if (SpecialTypeMediaUtils.isBurstPhoto(j2)) {
                i = R.drawable.type_indicator_burst_photo;
            } else {
                str2 = null;
                i2 = 8;
                i = 0;
            }
            updateTypeIndicator(i2, str2, i);
        }
        i = 0;
        updateTypeIndicator(i2, str2, i);
    }

    public void bindSyncIndicator(long j, int i, SyncStateDisplay.DisplayScene displayScene) {
        bindSyncIndicator(j, i, displayScene, 7);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (displaySyncableState(r12) != false) goto L_0x003e;
     */
    public void bindSyncIndicator(long j, int i, SyncStateDisplay.DisplayScene displayScene, int i2) {
        Animation animation;
        if (!needShowSyncIndicator(displayScene)) {
            setSyncIndicatorVisibility(8);
            ImageView imageView = this.mSyncIndicator;
            if (imageView != null && imageView.getAnimation() != null) {
                this.mSyncIndicator.clearAnimation();
            }
        } else if (needRefreshSyncIndicator(j, i, i2)) {
            int i3 = R.drawable.photo_status_syncable;
            int i4 = 0;
            Animation animation2 = null;
            if (i != 0 && i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != Integer.MAX_VALUE) {
                                throw new IllegalArgumentException("unknow status: " + i);
                            }
                        } else if (displayUnSyncableState(i2)) {
                            i3 = R.drawable.photo_status_unsyncable;
                            animation = null;
                            this.mLastSyncId = j;
                            this.mSyncState = i;
                            updateSyncIndicator(i4, i3, animation2, animation);
                        }
                    }
                } else if (displaySyncingState(i2)) {
                    animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.photo_status_syncing_rotate_anim);
                    animation = null;
                    this.mLastSyncId = j;
                    this.mSyncState = i;
                    updateSyncIndicator(i4, i3, animation2, animation);
                }
                i3 = 0;
                animation = null;
                i4 = 8;
                this.mLastSyncId = j;
                this.mSyncState = i;
                updateSyncIndicator(i4, i3, animation2, animation);
            } else if (displaySyncedState(i2)) {
                i3 = R.drawable.photo_status_sync_succ;
                animation = null;
                this.mLastSyncId = j;
                this.mSyncState = i;
                updateSyncIndicator(i4, i3, animation2, animation);
            } else {
                if (this.mSyncState == 2) {
                    if (displaySyncingState(i2)) {
                        animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.photo_status_sync_succ_disappear_anim);
                        animation = AnimationUtils.loadAnimation(getContext(), R.anim.photo_status_sync_succ_disappear_anim);
                        i4 = 8;
                        i3 = R.drawable.photo_status_sync_succ;
                        this.mLastSyncId = j;
                        this.mSyncState = i;
                        updateSyncIndicator(i4, i3, animation2, animation);
                    }
                }
                i3 = 0;
                animation = null;
                i4 = 8;
                this.mLastSyncId = j;
                this.mSyncState = i;
                updateSyncIndicator(i4, i3, animation2, animation);
            }
            i3 = 0;
            animation = null;
            this.mLastSyncId = j;
            this.mSyncState = i;
            updateSyncIndicator(i4, i3, animation2, animation);
        }
    }

    public ImageView getBackgroundImageView() {
        return this.mImageView;
    }

    public ImageView getBackgroundMask() {
        checkStubInflate();
        return this.mMask;
    }

    public CheckBox getCheckBox() {
        checkStubInflate();
        return this.mCheckBox;
    }

    public TextView getShowNumberTextView() {
        checkStubInflate();
        return this.mPickingNumberIndicator;
    }

    public Drawable getTransitDrawable() {
        Drawable.ConstantState constantState;
        Drawable drawable = BindImageHelper.isViewBindDrawable(this.mImageView) ? this.mImageView.getDrawable() : null;
        return (drawable == null || (constantState = drawable.getConstantState()) == null) ? drawable : constantState.newDrawable(getResources());
    }

    public RectF getTransitFrame() {
        return new RectF((float) getLeft(), (float) getTop(), (float) getRight(), (float) getBottom());
    }

    public long getTransitId() {
        return this.mItemId;
    }

    public String getTransitPath() {
        return this.mItemPath;
    }

    public ImageView.ScaleType getTransitScaleType() {
        return this.mImageView.getScaleType();
    }

    public List<TransitionalItem> getTransitionalItems() {
        ArrayList arrayList = new ArrayList(1);
        if (this.mItemId > 0) {
            arrayList.add(this);
        }
        return arrayList;
    }

    public boolean isChecked() {
        CheckBox checkBox = this.mCheckBox;
        if (checkBox == null) {
            return false;
        }
        return checkBox.isChecked();
    }

    /* access modifiers changed from: protected */
    public boolean isSquareItem() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mItemStub = (ViewStub) findViewById(R.id.item_stub);
        this.mImageView = (ImageView) findViewById(R.id.micro_thumb);
        this.mTypeIndicator = (TextView) findViewById(R.id.type_indicator);
        this.mFavoriteIndicator = findViewById(R.id.favorites_indicator);
        this.mBottomIndicatorContainer = findViewById(R.id.bottom_indicator_container);
        this.mImageAware = new ImageViewAware(this.mImageView);
        this.mImageSize = Config.ThumbConfig.get().sMicroTargetSize;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (isSquareItem()) {
            super.onMeasure(i, i);
        } else {
            super.onMeasure(i, i2);
        }
    }

    public void setCheckable(boolean z) {
        if (z) {
            checkStubInflate();
            boolean z2 = false;
            this.mCheckBoxContainer.setVisibility(0);
            if (this.mFavoriteIndicator.getVisibility() == 0) {
                z2 = true;
            }
            bindFavoriteIndicator(z2);
        } else {
            View view = this.mCheckBoxContainer;
            if (view != null) {
                view.setVisibility(8);
            }
        }
        setSimilarMarkEnable(z);
    }

    public void setCheckableListener(View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            View view = this.mCheckBoxContainer;
            if (view != null) {
                view.setOnClickListener((View.OnClickListener) null);
                return;
            }
            return;
        }
        checkStubInflate();
        this.mCheckBoxContainer.setOnClickListener(onClickListener);
    }

    public void setChecked(boolean z) {
        if (z) {
            checkStubInflate();
            this.mCheckBox.setChecked(true);
            return;
        }
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setChecked(false);
        }
    }

    public void setImageForeground(int i) {
        ImageView imageView = this.mImageView;
        if (imageView instanceof ForegroundImageView) {
            ((ForegroundImageView) imageView).setForegroundResource(i);
        }
    }

    public void setImageSize(ImageSize imageSize) {
        if (imageSize != null) {
            this.mImageSize = imageSize;
        }
    }

    public void setIsSimilarBestImage(boolean z) {
        this.mIsSimilarBestImage = z;
        View view = this.mCheckBoxContainer;
        if (view != null && view.getVisibility() == 0) {
            setSimilarMarkEnable(true);
        }
    }

    public void setSimilarMarkEnable(boolean z) {
        if (!z || !this.mIsSimilarBestImage) {
            ImageView imageView = this.mSimilarBestMark;
            if (imageView != null) {
                imageView.setVisibility(8);
                return;
            }
            return;
        }
        checkStubInflate();
        this.mSimilarBestMark.setVisibility(0);
    }

    public void toggle() {
        checkStubInflate();
        this.mCheckBox.toggle();
    }

    /* access modifiers changed from: protected */
    public void updateBottomIndicatorBg() {
        View view;
        TextView textView = this.mTypeIndicator;
        if ((textView == null || textView.getVisibility() != 0) && ((view = this.mFavoriteIndicator) == null || view.getVisibility() != 0)) {
            View view2 = this.mBottomIndicatorContainer;
            if (view2 != null) {
                view2.setVisibility(8);
                return;
            }
            return;
        }
        this.mBottomIndicatorContainer.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void updateFavoriteIndicatorVisibility(int i) {
        if (i == 0) {
            this.mFavoriteIndicator.setVisibility(0);
        } else {
            View view = this.mFavoriteIndicator;
            if (view != null) {
                view.setVisibility(8);
            }
        }
        updateBottomIndicatorBg();
    }

    /* access modifiers changed from: protected */
    public void updateTopIndicatorBg() {
        ImageView imageView = this.mSyncIndicator;
        if (imageView == null || imageView.getVisibility() != 0 || this.mSyncIndicator.getDrawable() == null) {
            View view = this.mTopIndicatorContainer;
            if (view != null) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        checkStubInflate();
        this.mTopIndicatorContainer.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void updateTypeIndicator(int i, String str, int i2) {
        TextView textView = this.mTypeIndicator;
        if (textView != null) {
            if (i == 0 || i != textView.getVisibility()) {
                String nullToEmpty = Strings.nullToEmpty(str);
                if (!TextUtils.equals(this.mTypeIndicator.getText(), nullToEmpty)) {
                    this.mTypeIndicator.setText(nullToEmpty);
                }
                Drawable drawable = i2 != 0 ? getContext().getDrawable(i2) : null;
                if (this.mTypeIndicator.getCompoundDrawablesRelative()[0] != drawable) {
                    this.mTypeIndicator.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                }
                setTypeIndicatorVisibility(i);
            }
        }
    }
}
