package com.miui.gallery.widget.editwrapper;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.util.LongSparseArray;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.adapter.BaseMediaAdapter;
import com.miui.gallery.adapter.CheckableAdapter;
import com.miui.gallery.adapter.HeaderFooterRecyclerAdapterWrapper;
import com.miui.gallery.ui.Checkable;
import com.miui.gallery.ui.CheckableView;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SyncSortUtil;
import com.miui.gallery.widget.ScalableImageView;
import com.miui.gallery.widget.editwrapper.PickAnimationHelper;
import com.miui.gallery.widget.recyclerview.GalleryRecyclerView;
import com.miui.gallery.widget.recyclerview.ItemClickSupport;
import com.miui.gallery.widget.stickyheader.core.HeaderViewHolder;
import com.miui.gallery.widget.stickyheader.core.StickyHeaderAdapter;
import com.miui.gallery.widget.stickyheader.core.StickyHeaderLayoutManager;
import com.miui.gallery.widget.stickyheader.core.StickyHeaderRecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import miui.gallery.support.MiuiSdkCompat;
import miui.view.EditActionMode;
import miui.view.animation.CubicEaseOutInterpolator;

public class EditableListViewWrapper {
    private SimpleWrapper mAdapterWrapper;
    private ArrayMap<RecyclerView.Adapter, SimpleWrapper> mAdapterWrappers;
    /* access modifiers changed from: private */
    public AnimationManager mAnimationManager = new AnimationManager();
    private ItemClickSupport.OnItemClickListener mCheckItemListener = new ItemClickSupport.OnItemClickListener() {
        public boolean onItemClick(RecyclerView recyclerView, View view, int i, long j, float f, float f2) {
            if (!(view instanceof Checkable)) {
                return false;
            }
            boolean z = !EditableListViewWrapper.this.mCheckState.getCheckState(i);
            EditableListViewWrapper.this.mCheckState.setCheckState(i, z);
            ((Checkable) view).setChecked(z);
            EditableListViewWrapper.this.updateActionMode();
            EditableListViewWrapper.this.mMultiChoiceModeCallback.mWrapped.onItemCheckedStateChanged(EditableListViewWrapper.this.mChoiceActionMode, i, j, z);
            if (view instanceof PickAnimationHelper.BackgroundImageViewable) {
                EditableListViewWrapper.this.mAnimationManager.startScaleItemImageViewAnimation(((PickAnimationHelper.BackgroundImageViewable) view).getBackgroundImageView(), i);
            }
            if (z && (view instanceof PickAnimationHelper.ShowNumberWhenPicking)) {
                EditableListViewWrapper.this.mAnimationManager.startPickingNumberAnimation((PickAnimationHelper.ShowNumberWhenPicking) view);
            }
            return true;
        }
    };
    /* access modifiers changed from: private */
    public CheckStateWithGroup mCheckState;
    /* access modifiers changed from: private */
    public ActionMode mChoiceActionMode;
    /* access modifiers changed from: private */
    public EditActionMode mEditActionMode;
    private ItemClickSupport.OnItemLongClickListener mEnterActionModeListener = new ItemClickSupport.OnItemLongClickListener() {
        public boolean onItemLongClick(RecyclerView recyclerView, View view, int i, long j) {
            if (EditableListViewWrapper.this.isListHeaderOrFooter(i)) {
                return false;
            }
            EditableListViewWrapper.this.startActionMode();
            EditableListViewWrapper.this.mAnimationManager.setLongTouchPosition(i);
            return true;
        }
    };
    private StickyHeaderAdapterWrapper mHeaderAdapterWrapper;
    /* access modifiers changed from: private */
    public boolean mIsInActionMode;
    /* access modifiers changed from: private */
    public boolean mIsInChoiceMode;
    private ItemClickSupport.OnItemClickListener mItemClickDelegate;
    /* access modifiers changed from: private */
    public int mListScrollState = 0;
    /* access modifiers changed from: private */
    public MultiChoiceModeCallback mMultiChoiceModeCallback;
    /* access modifiers changed from: private */
    public GalleryRecyclerView mRecyclerView;
    private RecyclerView.AdapterDataObserver mRestoreCheckStateObserver = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            super.onChanged();
            EditableListViewWrapper.this.handleDataChanged();
        }

        public void onItemRangeChanged(int i, int i2) {
            super.onItemRangeChanged(i, i2);
            EditableListViewWrapper.this.handleDataChanged();
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            super.onItemRangeChanged(i, i2, obj);
            EditableListViewWrapper.this.handleDataChanged();
        }

        public void onItemRangeInserted(int i, int i2) {
            super.onItemRangeInserted(i, i2);
            EditableListViewWrapper.this.handleDataChanged();
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            super.onItemRangeMoved(i, i2, i3);
            EditableListViewWrapper.this.handleDataChanged();
        }

        public void onItemRangeRemoved(int i, int i2) {
            super.onItemRangeRemoved(i, i2);
            EditableListViewWrapper.this.handleDataChanged();
        }
    };
    private RecyclerView.OnScrollListener mScrollDelegate = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            int unused = EditableListViewWrapper.this.mListScrollState = i;
            if (EditableListViewWrapper.this.mScrollListener != null) {
                EditableListViewWrapper.this.mScrollListener.onScrollStateChanged(recyclerView, i);
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (EditableListViewWrapper.this.mScrollListener != null) {
                EditableListViewWrapper.this.mScrollListener.onScrolled(recyclerView, i, i2);
            }
        }
    };
    /* access modifiers changed from: private */
    public RecyclerView.OnScrollListener mScrollListener;

    class AnimationManager implements View.OnTouchListener {
        private boolean isTurnOnScaleImageViewAni = true;
        private ImageView.ScaleType mOriginalScaleType = null;
        private Scroll2PickHelper mScroll2PickHelper = new Scroll2PickHelper();

        private class MyScaleItemImageViewAnimatorListener implements ValueAnimator.AnimatorUpdateListener {
            private ImageView mImage;
            public Matrix mPrimaryMatrix;

            public MyScaleItemImageViewAnimatorListener(ImageView imageView) {
                this.mPrimaryMatrix = new Matrix(imageView.getImageMatrix());
                this.mImage = imageView;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimationManager.this.setImageViewScale(this.mImage, ((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        }

        class Scroll2PickHelper implements View.OnTouchListener {
            private boolean mHasEverPickLongTouchPosition;
            private int mLastPosition;
            private float mLastX;
            private float mLastY;
            private int mLongTouchPosition = -1;
            private Stack<Integer> mMathStack = new Stack<>();
            private int mMotionPosition;
            private float mMotionX;
            private boolean mScollXMode2PickEnable;
            private boolean mScollYMode2PickEnable;
            private boolean mScollYMode2PickEnableAfterLeft;
            private boolean mScollYMode2PickEnableAfterRight;

            Scroll2PickHelper() {
            }

            private void ifScrollYModeEnable(float f, float f2) {
                View findChildViewUnderForExternal;
                if (!this.mScollYMode2PickEnable) {
                    boolean z = true;
                    if (f - this.mMotionX > 10.0f && !this.mScollYMode2PickEnableAfterRight) {
                        this.mScollYMode2PickEnableAfterRight = true;
                    } else if (f - this.mMotionX < -10.0f && !this.mScollYMode2PickEnableAfterLeft) {
                        this.mScollYMode2PickEnableAfterLeft = true;
                    }
                    if (!this.mScollYMode2PickEnableAfterRight && !this.mScollYMode2PickEnableAfterLeft) {
                        z = false;
                    }
                    this.mScollYMode2PickEnable = z;
                    if (this.mScollYMode2PickEnable && (findChildViewUnderForExternal = EditableListViewWrapper.this.mRecyclerView.findChildViewUnderForExternal(f, f2)) != null) {
                        this.mMotionPosition = EditableListViewWrapper.this.mRecyclerView.getChildAdapterPositionForExternal(findChildViewUnderForExternal);
                        this.mMathStack.push(Integer.valueOf(this.mMotionPosition));
                    }
                }
            }

            private boolean interceptEvent() {
                return this.mScollXMode2PickEnable || this.mScollYMode2PickEnable;
            }

            private boolean onTouchMove(MotionEvent motionEvent) {
                View findChildViewUnderForExternal;
                int childAdapterPositionForExternal;
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (Math.abs(x - this.mLastX) >= Math.abs(y - this.mLastY) && Math.abs(x - this.mLastX) > 10.0f) {
                    View findChildViewUnderForExternal2 = EditableListViewWrapper.this.mRecyclerView.findChildViewUnderForExternal(x, y);
                    if (findChildViewUnderForExternal2 != null) {
                        int childAdapterPositionForExternal2 = EditableListViewWrapper.this.mRecyclerView.getChildAdapterPositionForExternal(findChildViewUnderForExternal2);
                        if (!(childAdapterPositionForExternal2 == -1 || childAdapterPositionForExternal2 == this.mLastPosition)) {
                            if (pickOrNotByPosition(childAdapterPositionForExternal2, !EditableListViewWrapper.this.mCheckState.getCheckState(childAdapterPositionForExternal2))) {
                                EditableListViewWrapper.this.updateActionMode();
                                EditableListViewWrapper.this.mMultiChoiceModeCallback.mWrapped.onAllItemsCheckedStateChanged(EditableListViewWrapper.this.mChoiceActionMode, true);
                                this.mScollXMode2PickEnable = true;
                                if (childAdapterPositionForExternal2 == this.mLongTouchPosition) {
                                    this.mHasEverPickLongTouchPosition = true;
                                }
                            }
                            this.mLastPosition = childAdapterPositionForExternal2;
                        }
                        ifScrollYModeEnable(x, y);
                    }
                } else if (this.mScollYMode2PickEnable && Math.abs(y - this.mLastY) > 10.0f && Math.abs(x - this.mLastX) < Math.abs(y - this.mLastY) && (findChildViewUnderForExternal = EditableListViewWrapper.this.mRecyclerView.findChildViewUnderForExternal(x, y)) != null && (childAdapterPositionForExternal = EditableListViewWrapper.this.mRecyclerView.getChildAdapterPositionForExternal(findChildViewUnderForExternal)) != -1 && childAdapterPositionForExternal != this.mLastPosition) {
                    pickOrNotByPosition(childAdapterPositionForExternal, true);
                    this.mLastPosition = childAdapterPositionForExternal;
                    if (childAdapterPositionForExternal < this.mMotionPosition) {
                        if (this.mMathStack.isEmpty() || childAdapterPositionForExternal < this.mMathStack.peek().intValue()) {
                            for (int i = this.mMotionPosition - 1; i >= childAdapterPositionForExternal; i--) {
                                if (this.mMathStack.isEmpty() || i < this.mMathStack.peek().intValue()) {
                                    this.mMathStack.push(Integer.valueOf(i));
                                }
                            }
                        } else {
                            while (this.mMathStack.peek().intValue() < childAdapterPositionForExternal && !this.mMathStack.isEmpty()) {
                                pickOrNotByPosition(this.mMathStack.pop().intValue(), false);
                            }
                        }
                        for (int i2 = this.mMotionPosition; i2 >= childAdapterPositionForExternal; i2--) {
                            pickOrNotByPosition(i2, true);
                        }
                    } else {
                        if (this.mMathStack.isEmpty() || childAdapterPositionForExternal > this.mMathStack.peek().intValue()) {
                            for (int i3 = this.mMotionPosition + 1; i3 <= childAdapterPositionForExternal; i3++) {
                                if (this.mMathStack.isEmpty() || i3 > this.mMathStack.peek().intValue()) {
                                    this.mMathStack.push(Integer.valueOf(i3));
                                }
                            }
                        } else {
                            while (!this.mMathStack.isEmpty() && this.mMathStack.peek().intValue() > childAdapterPositionForExternal) {
                                pickOrNotByPosition(this.mMathStack.pop().intValue(), false);
                            }
                        }
                        for (int i4 = this.mMotionPosition; i4 <= childAdapterPositionForExternal; i4++) {
                            pickOrNotByPosition(i4, true);
                        }
                    }
                    EditableListViewWrapper.this.updateActionMode();
                    EditableListViewWrapper.this.mMultiChoiceModeCallback.mWrapped.onAllItemsCheckedStateChanged(EditableListViewWrapper.this.mChoiceActionMode, true);
                }
                this.mLastX = x;
                this.mLastY = y;
                return interceptEvent();
            }

            private boolean pickOrNotByPosition(int i, boolean z) {
                RecyclerView.ViewHolder findViewHolderForAdapterPositionForExternal = EditableListViewWrapper.this.mRecyclerView.findViewHolderForAdapterPositionForExternal(i);
                if (findViewHolderForAdapterPositionForExternal == null) {
                    return false;
                }
                View view = findViewHolderForAdapterPositionForExternal.itemView;
                if (!(view instanceof PickAnimationHelper.BackgroundImageViewable)) {
                    return false;
                }
                AnimationManager.this.startScaleItemImageViewAnimationByScrollPicked(((PickAnimationHelper.BackgroundImageViewable) view).getBackgroundImageView(), i, z);
                EditableListViewWrapper.this.mCheckState.setCheckState(i, z);
                ((Checkable) view).setChecked(z);
                return true;
            }

            /* access modifiers changed from: private */
            public void setLongPressPosition(int i) {
                this.mLongTouchPosition = i;
                this.mHasEverPickLongTouchPosition = false;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
                if (r9 != 3) goto L_0x011c;
             */
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked != 0) {
                    if (actionMasked != 1) {
                        if (actionMasked == 2) {
                            if (!EditableListViewWrapper.this.isInChoiceMode()) {
                                return false;
                            }
                            boolean interceptEvent = interceptEvent();
                            boolean onTouchMove = onTouchMove(motionEvent);
                            if (EditableListViewWrapper.this.mListScrollState != 0 && !interceptEvent && onTouchMove) {
                                EditableListViewWrapper.this.mRecyclerView.setOnTouchListener((View.OnTouchListener) null);
                                EditableListViewWrapper.this.mRecyclerView.requestDisallowInterceptTouchEvent(true);
                                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                                obtain.setAction(1);
                                EditableListViewWrapper.this.mRecyclerView.dispatchTouchEvent(obtain);
                                EditableListViewWrapper.this.mRecyclerView.requestDisallowInterceptTouchEvent(false);
                                EditableListViewWrapper.this.mRecyclerView.setOnTouchListener(EditableListViewWrapper.this.mAnimationManager);
                            }
                            return onTouchMove;
                        }
                    }
                    if (!EditableListViewWrapper.this.isInChoiceMode()) {
                        return false;
                    }
                    this.mMathStack.clear();
                    if (!this.mHasEverPickLongTouchPosition && this.mLongTouchPosition != -1) {
                        this.mHasEverPickLongTouchPosition = true;
                        RecyclerView.ViewHolder findViewHolderForAdapterPositionForExternal = EditableListViewWrapper.this.mRecyclerView.findViewHolderForAdapterPositionForExternal(this.mLongTouchPosition);
                        if (findViewHolderForAdapterPositionForExternal != null) {
                            View view2 = findViewHolderForAdapterPositionForExternal.itemView;
                            if (view2 instanceof PickAnimationHelper.BackgroundImageViewable) {
                                AnimationManager.this.startScaleItemImageViewAnimation(((PickAnimationHelper.BackgroundImageViewable) view2).getBackgroundImageView(), this.mLongTouchPosition, true, 0, 200);
                                EditableListViewWrapper.this.mCheckState.setCheckState(this.mLongTouchPosition, true);
                                ((Checkable) view2).setChecked(true);
                                EditableListViewWrapper.this.updateActionMode();
                                EditableListViewWrapper.this.mMultiChoiceModeCallback.mWrapped.onAllItemsCheckedStateChanged(EditableListViewWrapper.this.mChoiceActionMode, true);
                            }
                        }
                    }
                    return interceptEvent();
                }
                this.mScollXMode2PickEnable = false;
                this.mMotionX = motionEvent.getX();
                this.mLastX = motionEvent.getX();
                this.mLastY = motionEvent.getY();
                this.mLastPosition = 0;
                this.mScollYMode2PickEnable = false;
                this.mScollYMode2PickEnableAfterLeft = false;
                this.mScollYMode2PickEnableAfterRight = false;
                if (!this.mMathStack.isEmpty()) {
                    this.mMathStack.clear();
                }
                return false;
            }
        }

        AnimationManager() {
        }

        private void finishImageViewScaleAnimatorIfNecessary(ImageView imageView) {
            ValueAnimator valueAnimator = (ValueAnimator) imageView.getTag(R.id.tag_animator);
            if (valueAnimator != null && valueAnimator.isRunning()) {
                valueAnimator.end();
            }
            if (valueAnimator != null) {
                float imageViewScale = getImageViewScale(imageView);
                float floatValue = ((Float) imageView.getTag(R.id.tag_target_scale)).floatValue();
                if (Math.abs(imageViewScale - floatValue) > Float.MIN_NORMAL) {
                    setImageViewScale(imageView, floatValue);
                }
            }
        }

        private float getImageViewScale(ImageView imageView) {
            if (imageView instanceof ScalableImageView) {
                return ((ScalableImageView) imageView).getMatrixScale();
            }
            return 0.0f;
        }

        /* access modifiers changed from: private */
        public void setImageViewScale(ImageView imageView, float f) {
            if (imageView instanceof ScalableImageView) {
                ((ScalableImageView) imageView).setMatrixScale(f);
            }
        }

        private void setItemImageView2OriginalScaleAfterEnlarge(ImageView imageView) {
            finishImageViewScaleAnimatorIfNecessary(imageView);
            setImageViewScale(imageView, 1.0f);
            ImageView.ScaleType scaleType = this.mOriginalScaleType;
            if (scaleType != null) {
                imageView.setScaleType(scaleType);
            }
            imageView.setTag(R.id.tag_matrix, (Object) null);
            imageView.setTag(R.id.tag_animator, (Object) null);
        }

        private void setItemImageViewEnlargeAfterChecked(ImageView imageView, int i) {
            finishImageViewScaleAnimatorIfNecessary(imageView);
            imageView.setTag(R.id.tag_matrix, imageView.getImageMatrix());
            setImageViewScale(imageView, 1.2f);
            imageView.setTag(R.id.tag_animator, (Object) null);
        }

        private void startScaleItemImageViewAnimationInternal(ImageView imageView, boolean z, int i, int i2, int i3) {
            ValueAnimator valueAnimator;
            if (this.isTurnOnScaleImageViewAni) {
                finishImageViewScaleAnimatorIfNecessary(imageView);
                Drawable drawable = imageView.getDrawable();
                if (drawable != null && drawable.getIntrinsicWidth() > 0) {
                    if (z) {
                        valueAnimator = ValueAnimator.ofFloat(new float[]{1.0f, 1.2f});
                        valueAnimator.addUpdateListener(new MyScaleItemImageViewAnimatorListener(imageView));
                        valueAnimator.setInterpolator(new CubicEaseOutInterpolator());
                        imageView.setTag(R.id.tag_matrix, imageView.getImageMatrix());
                        if (this.mOriginalScaleType == null) {
                            this.mOriginalScaleType = imageView.getScaleType();
                        }
                        imageView.setTag(R.id.tag_target_scale, Float.valueOf(1.2f));
                    } else {
                        valueAnimator = ValueAnimator.ofFloat(new float[]{1.2f, 1.0f});
                        valueAnimator.addUpdateListener(new MyScaleItemImageViewAnimatorListener(imageView));
                        valueAnimator.setInterpolator(new CubicEaseOutInterpolator());
                        imageView.setTag(R.id.tag_matrix, (Object) null);
                        imageView.setTag(R.id.tag_target_scale, Float.valueOf(1.0f));
                    }
                    valueAnimator.setDuration((long) i3);
                    valueAnimator.setStartDelay((long) i2);
                    imageView.setScaleType(ImageView.ScaleType.MATRIX);
                    imageView.setTag(R.id.tag_animator, valueAnimator);
                    valueAnimator.start();
                }
            }
        }

        private void startScaleListViewAnimation(GalleryRecyclerView galleryRecyclerView, boolean z) {
            float f = 0.92f;
            float f2 = 1.0f;
            if (!z) {
                f2 = 0.92f;
                f = 1.0f;
            }
            for (int i = 0; i < galleryRecyclerView.getChildCount(); i++) {
                View childAt = galleryRecyclerView.getChildAt(i);
                if (childAt instanceof Checkable) {
                    ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(childAt, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{f, f2}), PropertyValuesHolder.ofFloat("scaleY", new float[]{f, f2})}).setDuration(300);
                    duration.setInterpolator(new CubicEaseOutInterpolator());
                    duration.start();
                    childAt.setTag(R.id.tag_scale_factor, Float.valueOf(f2));
                    if (z && (childAt instanceof PickAnimationHelper.BackgroundImageViewable)) {
                        ImageView backgroundImageView = ((PickAnimationHelper.BackgroundImageViewable) childAt).getBackgroundImageView();
                        if (backgroundImageView.getTag(R.id.tag_matrix) != null) {
                            startScaleItemImageViewAnimationInternal(backgroundImageView, false, galleryRecyclerView.getChildAdapterPositionForExternal(childAt), 0, 300);
                        }
                    }
                }
            }
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return this.mScroll2PickHelper.onTouch(view, motionEvent);
        }

        public void resetViewPropertyIfNeed(View view, View view2, int i) {
            Object tag = view.getTag(R.id.tag_scale_factor);
            if (EditableListViewWrapper.this.isInChoiceMode()) {
                if (tag == null || !MiscUtil.floatEquals(((Float) tag).floatValue(), 0.92f)) {
                    view.setScaleX(0.92f);
                    view.setScaleY(0.92f);
                    view.setTag(R.id.tag_scale_factor, Float.valueOf(0.92f));
                }
            } else if (tag == null || !MiscUtil.floatEquals(((Float) tag).floatValue(), 1.0f)) {
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
                view.setTag(R.id.tag_scale_factor, Float.valueOf(1.0f));
            }
            if (this.isTurnOnScaleImageViewAni) {
                if (view instanceof PickAnimationHelper.BackgroundImageViewable) {
                    ImageView backgroundImageView = ((PickAnimationHelper.BackgroundImageViewable) view).getBackgroundImageView();
                    if (EditableListViewWrapper.this.isInChoiceMode()) {
                        if (!(view2.getTag(R.id.tag_pick_position) == null || ((Integer) view2.getTag(R.id.tag_pick_position)).intValue() == i || EditableListViewWrapper.this.mCheckState.getCheckState(i))) {
                            setItemImageView2OriginalScaleAfterEnlarge(backgroundImageView);
                        }
                        if (EditableListViewWrapper.this.mCheckState.getCheckState(i) && backgroundImageView.getTag(R.id.tag_matrix) == null) {
                            setItemImageViewEnlargeAfterChecked(backgroundImageView, i);
                        }
                    } else if (backgroundImageView.getTag(R.id.tag_matrix) != null) {
                        setItemImageView2OriginalScaleAfterEnlarge(backgroundImageView);
                    }
                }
                view2.setTag(R.id.tag_pick_position, Integer.valueOf(i));
            }
        }

        public void setLongTouchPosition(int i) {
            this.mScroll2PickHelper.setLongPressPosition(i);
        }

        public void startEnterActionModeAni() {
            startScaleListViewAnimation(EditableListViewWrapper.this.mRecyclerView, false);
        }

        public void startExistActionModeAni() {
            startScaleListViewAnimation(EditableListViewWrapper.this.mRecyclerView, true);
        }

        public void startPickingNumberAnimation(PickAnimationHelper.ShowNumberWhenPicking showNumberWhenPicking) {
            ImageView backgroundMask = showNumberWhenPicking.getBackgroundMask();
            backgroundMask.setVisibility(0);
            TextView showNumberTextView = showNumberWhenPicking.getShowNumberTextView();
            showNumberTextView.setText(String.format("%d", new Object[]{Integer.valueOf(EditableListViewWrapper.this.mCheckState.getCount())}));
            ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(showNumberTextView, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofFloat("scaleX", new float[]{0.8f, 1.0f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{0.8f, 1.0f})}).setDuration(300);
            duration.setInterpolator(new CubicEaseOutInterpolator());
            ObjectAnimator duration2 = ObjectAnimator.ofPropertyValuesHolder(showNumberTextView, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f})}).setDuration(200);
            duration2.setInterpolator(new CubicEaseOutInterpolator());
            duration2.setStartDelay(200);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{duration, duration2});
            animatorSet.start();
            ObjectAnimator duration3 = ObjectAnimator.ofPropertyValuesHolder(backgroundMask, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f})}).setDuration(700);
            duration3.setInterpolator(new CubicEaseOutInterpolator());
            duration3.start();
        }

        public void startScaleAllItemImageViewAnimation(GalleryRecyclerView galleryRecyclerView, boolean z) {
            int childCount = galleryRecyclerView.getChildCount();
            if (childCount > 0) {
                int i = 0;
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = galleryRecyclerView.getChildAt(i2);
                    if (childAt instanceof PickAnimationHelper.BackgroundImageViewable) {
                        startScaleItemImageViewAnimation(((PickAnimationHelper.BackgroundImageViewable) childAt).getBackgroundImageView(), galleryRecyclerView.getChildAdapterPositionForExternal(childAt), z, i * 40);
                        i++;
                    }
                }
            }
        }

        public void startScaleItemImageViewAnimation(ImageView imageView, int i) {
            if (imageView.getTag(R.id.tag_matrix) == null) {
                startScaleItemImageViewAnimationInternal(imageView, true, i, 100, 300);
            } else {
                startScaleItemImageViewAnimationInternal(imageView, false, i, 100, 300);
            }
        }

        public void startScaleItemImageViewAnimation(ImageView imageView, int i, boolean z, int i2) {
            startScaleItemImageViewAnimation(imageView, i, z, i2, 300);
        }

        public void startScaleItemImageViewAnimation(ImageView imageView, int i, boolean z, int i2, int i3) {
            if (imageView.getTag(R.id.tag_matrix) == null && z) {
                startScaleItemImageViewAnimationInternal(imageView, true, i, i2, i3);
            }
            if (imageView.getTag(R.id.tag_matrix) != null && !z) {
                startScaleItemImageViewAnimationInternal(imageView, false, i, i2, i3);
            }
        }

        public void startScaleItemImageViewAnimationByScrollPicked(ImageView imageView, int i, boolean z) {
            startScaleItemImageViewAnimation(imageView, i, z, 100);
        }
    }

    private static class CheckState {
        protected Mode mCheckMode;
        protected SparseBooleanArray mCheckState;
        protected long mIndex;
        private long mReverseIndex;
        protected LongSparseArray<Long> mReverseModeSelectedIdIndex;
        protected Source mSource;
        protected Status mStatus;
        protected LongSparseArray<Long> mUserSelectedIdIndex;

        protected enum Mode {
            NORMAL,
            REVERSE
        }

        public interface Source {
            int getSourceEncryptPosition(int i);

            int getSourceItemCount();

            long getSourceItemId(int i);
        }

        protected enum Status {
            DIRTY,
            CLEAN
        }

        private CheckState() {
            this.mUserSelectedIdIndex = new LongSparseArray<>(0);
            this.mReverseModeSelectedIdIndex = new LongSparseArray<>(0);
            this.mCheckMode = Mode.NORMAL;
            this.mCheckState = new SparseBooleanArray(0);
            this.mStatus = Status.CLEAN;
            this.mIndex = 0;
            this.mReverseIndex = 0;
        }

        private int[] getSortedPositionsByIndex(int[] iArr, long[] jArr) {
            if (iArr.length != jArr.length) {
                return iArr;
            }
            SyncSortUtil.sort(iArr, jArr, 0, iArr.length - 1);
            return iArr;
        }

        private void refreshCheckStateWhenDirty() {
            if (this.mStatus == Status.DIRTY) {
                this.mCheckState = new SparseBooleanArray(this.mSource.getSourceItemCount());
                for (int i = 0; i < this.mSource.getSourceItemCount(); i++) {
                    long sourceItemId = this.mSource.getSourceItemId(i);
                    this.mCheckState.append(i, (this.mCheckMode == Mode.NORMAL && this.mUserSelectedIdIndex.indexOfKey(sourceItemId) >= 0) || (this.mCheckMode == Mode.REVERSE && this.mUserSelectedIdIndex.indexOfKey(sourceItemId) < 0));
                }
                this.mStatus = Status.CLEAN;
            }
        }

        public void bind(Source source) {
            if (source != null) {
                this.mSource = source;
            }
        }

        public void clear() {
            this.mCheckMode = Mode.NORMAL;
            this.mStatus = Status.CLEAN;
            this.mCheckState.clear();
            this.mUserSelectedIdIndex.clear();
            this.mReverseModeSelectedIdIndex.clear();
            this.mIndex = 0;
            this.mReverseIndex = 0;
        }

        public boolean getCheckState(int i) {
            return this.mStatus == Status.CLEAN ? this.mCheckState.get(i, false) : getCheckState(this.mSource.getSourceItemId(i));
        }

        public boolean getCheckState(long j) {
            if (this.mCheckMode == Mode.REVERSE) {
                if (this.mUserSelectedIdIndex.get(j) == null) {
                    return true;
                }
            } else if (this.mUserSelectedIdIndex.get(j) != null) {
                return true;
            }
            return false;
        }

        public int[] getCheckedItemOrderedPositions() {
            int count = getCount();
            int i = 0;
            if (count == 0) {
                return new int[0];
            }
            if (this.mCheckMode == Mode.NORMAL) {
                int[] iArr = new int[this.mUserSelectedIdIndex.size()];
                long[] jArr = new long[this.mUserSelectedIdIndex.size()];
                int i2 = 0;
                while (i < this.mSource.getSourceItemCount() && i2 < count) {
                    long sourceItemId = this.mSource.getSourceItemId(i);
                    if (this.mUserSelectedIdIndex.indexOfKey(sourceItemId) >= 0) {
                        iArr[i2] = i;
                        jArr[i2] = this.mUserSelectedIdIndex.get(sourceItemId).longValue();
                        i2++;
                    }
                    i++;
                }
                return getSortedPositionsByIndex(iArr, jArr);
            }
            int[] iArr2 = new int[count];
            int[] iArr3 = new int[this.mReverseModeSelectedIdIndex.size()];
            long[] jArr2 = new long[this.mReverseModeSelectedIdIndex.size()];
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < this.mSource.getSourceItemCount(); i5++) {
                long sourceItemId2 = this.mSource.getSourceItemId(i5);
                if (this.mUserSelectedIdIndex.indexOfKey(sourceItemId2) < 0 && this.mReverseModeSelectedIdIndex.indexOfKey(sourceItemId2) < 0) {
                    iArr2[i3] = i5;
                    i3++;
                }
                if (this.mReverseModeSelectedIdIndex.size() >= 0 && this.mReverseModeSelectedIdIndex.indexOfKey(sourceItemId2) >= 0) {
                    iArr3[i4] = i5;
                    jArr2[i4] = this.mReverseModeSelectedIdIndex.get(sourceItemId2).longValue();
                    i4++;
                }
            }
            if (this.mReverseModeSelectedIdIndex.size() > 0) {
                int[] sortedPositionsByIndex = getSortedPositionsByIndex(iArr3, jArr2);
                while (i < sortedPositionsByIndex.length && i3 < count) {
                    iArr2[i3] = sortedPositionsByIndex[i];
                    i++;
                    i3++;
                }
            }
            return iArr2;
        }

        public SparseBooleanArray getCheckedItemPositions() {
            refreshCheckStateWhenDirty();
            return this.mCheckState.clone();
        }

        public int getCount() {
            return this.mCheckMode == Mode.NORMAL ? this.mUserSelectedIdIndex.size() : this.mSource.getSourceItemCount() - this.mUserSelectedIdIndex.size();
        }

        public void handleDataChanged() {
            this.mCheckState.clear();
            this.mStatus = Status.DIRTY;
            refreshCheckStateWhenDirty();
        }

        public void setAllChecked(boolean z) {
            this.mStatus = Status.CLEAN;
            this.mCheckMode = z ? Mode.REVERSE : Mode.NORMAL;
            this.mUserSelectedIdIndex.clear();
            this.mReverseModeSelectedIdIndex.clear();
            this.mIndex = 0;
            this.mReverseIndex = 0;
            for (int i = 0; i < this.mSource.getSourceItemCount(); i++) {
                this.mCheckState.append(this.mSource.getSourceEncryptPosition(i), z);
            }
        }

        public void setCheckState(int i, boolean z) {
            if (this.mStatus == Status.CLEAN) {
                this.mCheckState.put(i, z);
            }
            long sourceItemId = this.mSource.getSourceItemId(i);
            if (this.mCheckMode == Mode.NORMAL) {
                if (z) {
                    LongSparseArray<Long> longSparseArray = this.mUserSelectedIdIndex;
                    long j = this.mIndex;
                    this.mIndex = 1 + j;
                    longSparseArray.put(sourceItemId, Long.valueOf(j));
                    return;
                }
                this.mUserSelectedIdIndex.remove(sourceItemId);
            } else if (z) {
                this.mUserSelectedIdIndex.remove(sourceItemId);
                LongSparseArray<Long> longSparseArray2 = this.mReverseModeSelectedIdIndex;
                long j2 = this.mReverseIndex;
                this.mReverseIndex = 1 + j2;
                longSparseArray2.put(sourceItemId, Long.valueOf(j2));
            } else {
                LongSparseArray<Long> longSparseArray3 = this.mUserSelectedIdIndex;
                long j3 = this.mIndex;
                this.mIndex = 1 + j3;
                longSparseArray3.put(sourceItemId, Long.valueOf(j3));
                this.mReverseModeSelectedIdIndex.remove(sourceItemId);
            }
        }
    }

    private static class CheckStateWithGroup extends CheckState {
        private SparseBooleanArray mGroupCheckState = new SparseBooleanArray(0);
        private AnimationManager mListAnimationManager;
        private GalleryRecyclerView mListView;
        private StickyHeaderAdapter mStickyHeaderAdapter;

        public CheckStateWithGroup(GalleryRecyclerView galleryRecyclerView, AnimationManager animationManager) {
            super();
            this.mListView = galleryRecyclerView;
            this.mListAnimationManager = animationManager;
        }

        private void setCheckStateInternal(int i, boolean z, boolean z2) {
            super.setCheckState(i, z);
            if (this.mStickyHeaderAdapter != null && this.mStatus == CheckState.Status.CLEAN && z2) {
                int headerIndex = this.mStickyHeaderAdapter.getHeaderIndex(i);
                boolean groupCheckState = getGroupCheckState(headerIndex);
                int[] itemPositionInterval = this.mStickyHeaderAdapter.getItemPositionInterval(headerIndex);
                boolean z3 = false;
                int i2 = itemPositionInterval[0];
                while (true) {
                    if (i2 > itemPositionInterval[1]) {
                        z3 = true;
                        break;
                    } else if (!getCheckState(i2)) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (groupCheckState != z3) {
                    this.mGroupCheckState.put(headerIndex, z3);
                    this.mListView.getAdapter().notifyDataSetChanged();
                }
            }
        }

        public void bindStickyHeaderAdapter(StickyHeaderAdapter stickyHeaderAdapter) {
            this.mStickyHeaderAdapter = stickyHeaderAdapter;
        }

        public void clear() {
            super.clear();
            this.mGroupCheckState.clear();
        }

        public boolean getGroupCheckState(int i) {
            return this.mGroupCheckState.get(i);
        }

        public void setAllChecked(boolean z) {
            super.setAllChecked(z);
            if (this.mStickyHeaderAdapter != null) {
                for (int i = 0; i < this.mStickyHeaderAdapter.getHeaderCount(); i++) {
                    this.mGroupCheckState.put(i, z);
                }
                this.mListView.getAdapter().notifyDataSetChanged();
            }
        }

        public void setCheckState(int i, boolean z) {
            setCheckStateInternal(i, z, true);
        }

        public boolean setGroupCheckState(int i) {
            View view;
            boolean z = !getGroupCheckState(i);
            this.mGroupCheckState.put(i, z);
            int[] itemPositionInterval = this.mStickyHeaderAdapter.getItemPositionInterval(i);
            int i2 = 0;
            for (int i3 = itemPositionInterval[0]; i3 <= itemPositionInterval[1]; i3++) {
                setCheckStateInternal(i3, z, false);
                RecyclerView.ViewHolder findViewHolderForAdapterPositionForExternal = this.mListView.findViewHolderForAdapterPositionForExternal(i3);
                if (!(findViewHolderForAdapterPositionForExternal == null || (view = findViewHolderForAdapterPositionForExternal.itemView) == null || !(view instanceof PickAnimationHelper.BackgroundImageViewable))) {
                    this.mListAnimationManager.startScaleItemImageViewAnimation(((PickAnimationHelper.BackgroundImageViewable) view).getBackgroundImageView(), i3, z, i2 * 40);
                    i2++;
                }
            }
            return z;
        }
    }

    private class MultiChoiceModeCallback implements ActionMode.Callback {
        /* access modifiers changed from: private */
        public MultiChoiceModeListener mWrapped;

        private MultiChoiceModeCallback() {
        }

        public boolean hasWrappedCallback() {
            return this.mWrapped != null;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (this.mWrapped.onActionItemClicked(actionMode, menuItem)) {
                return true;
            }
            switch (menuItem.getItemId()) {
                case 16908313:
                    actionMode.finish();
                    return true;
                case 16908314:
                    EditableListViewWrapper editableListViewWrapper = EditableListViewWrapper.this;
                    editableListViewWrapper.setAllItemsCheckState(!editableListViewWrapper.isAllItemsChecked());
                    return true;
                default:
                    return false;
            }
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            if (!this.mWrapped.onCreateActionMode(actionMode, menu)) {
                return false;
            }
            actionMode.setTitle(miui.R.string.select_item);
            if (actionMode instanceof EditActionMode) {
                EditActionMode editActionMode = (EditActionMode) actionMode;
                MiuiSdkCompat.setEditActionModeButton(EditableListViewWrapper.this.mRecyclerView.getContext(), editActionMode, 16908313, 3);
                MiuiSdkCompat.setEditActionModeButton(EditableListViewWrapper.this.mRecyclerView.getContext(), editActionMode, 16908314, 0);
            }
            EditableListViewWrapper.this.enterChoiceMode();
            boolean unused = EditableListViewWrapper.this.mIsInActionMode = true;
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            EditActionMode unused = EditableListViewWrapper.this.mEditActionMode = null;
            ActionMode unused2 = EditableListViewWrapper.this.mChoiceActionMode = null;
            boolean unused3 = EditableListViewWrapper.this.mIsInChoiceMode = false;
            EditableListViewWrapper.this.exitChoiceMode();
            this.mWrapped.onDestroyActionMode(actionMode);
            boolean unused4 = EditableListViewWrapper.this.mIsInActionMode = false;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }

        public void setWrapped(MultiChoiceModeListener multiChoiceModeListener) {
            this.mWrapped = multiChoiceModeListener;
        }
    }

    private class SimpleWrapper extends RecyclerView.Adapter implements CheckState.Source {
        private RecyclerView.Adapter mWrapped;

        public SimpleWrapper(RecyclerView.Adapter adapter) {
            this.mWrapped = adapter;
        }

        private void bindCheckState(RecyclerView.ViewHolder viewHolder, int i) {
            View view = viewHolder.itemView;
            if (view instanceof Checkable) {
                Checkable checkable = (Checkable) view;
                checkable.setCheckable(EditableListViewWrapper.this.isInChoiceMode());
                checkable.setChecked(EditableListViewWrapper.this.mCheckState.getCheckState(i));
                EditableListViewWrapper.this.mAnimationManager.resetViewPropertyIfNeed(view, view, i);
            }
        }

        private int getListHeadersCount() {
            RecyclerView.Adapter adapter = this.mWrapped;
            if (adapter instanceof HeaderFooterRecyclerAdapterWrapper) {
                return ((HeaderFooterRecyclerAdapterWrapper) adapter).getHeadersCount();
            }
            return 0;
        }

        public int getItemCount() {
            return this.mWrapped.getItemCount();
        }

        public long getItemId(int i) {
            return this.mWrapped.getItemId(i);
        }

        public int getItemViewType(int i) {
            return this.mWrapped.getItemViewType(i);
        }

        public int getSourceEncryptPosition(int i) {
            return i + getListHeadersCount();
        }

        public int getSourceItemCount() {
            RecyclerView.Adapter adapter = this.mWrapped;
            return adapter instanceof HeaderFooterRecyclerAdapterWrapper ? ((HeaderFooterRecyclerAdapterWrapper) adapter).getWrappedAdapter().getItemCount() : adapter.getItemCount();
        }

        public long getSourceItemId(int i) {
            return this.mWrapped.getItemId(i);
        }

        public RecyclerView.Adapter getWrapped() {
            return this.mWrapped;
        }

        public boolean isHeaderOrFooterPosition(int i) {
            RecyclerView.Adapter adapter = this.mWrapped;
            if (!(adapter instanceof HeaderFooterRecyclerAdapterWrapper)) {
                return false;
            }
            HeaderFooterRecyclerAdapterWrapper headerFooterRecyclerAdapterWrapper = (HeaderFooterRecyclerAdapterWrapper) adapter;
            return headerFooterRecyclerAdapterWrapper.isHeaderPosition(i) || headerFooterRecyclerAdapterWrapper.isFooterPosition(i);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            this.mWrapped.onAttachedToRecyclerView(recyclerView);
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            bindCheckState(viewHolder, i);
            this.mWrapped.onBindViewHolder(viewHolder, i);
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
            bindCheckState(viewHolder, i);
            this.mWrapped.onBindViewHolder(viewHolder, i, list);
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return this.mWrapped.onCreateViewHolder(viewGroup, i);
        }

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            this.mWrapped.onDetachedFromRecyclerView(recyclerView);
        }

        public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
            return this.mWrapped.onFailedToRecycleView(viewHolder);
        }

        public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
            this.mWrapped.onViewAttachedToWindow(viewHolder);
        }

        public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
            this.mWrapped.onViewDetachedFromWindow(viewHolder);
        }

        public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
            this.mWrapped.onViewRecycled(viewHolder);
        }

        public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
            this.mWrapped.registerAdapterDataObserver(adapterDataObserver);
        }

        public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
            this.mWrapped.unregisterAdapterDataObserver(adapterDataObserver);
        }
    }

    private class StickyHeaderAdapterWrapper implements StickyHeaderAdapter {
        private StickyHeaderAdapter mWrapped;

        public StickyHeaderAdapterWrapper(StickyHeaderAdapter stickyHeaderAdapter) {
            this.mWrapped = stickyHeaderAdapter;
        }

        public int getHeaderCount() {
            return this.mWrapped.getHeaderCount();
        }

        public long getHeaderId(int i) {
            return this.mWrapped.getHeaderId(i);
        }

        public int getHeaderIndex(int i) {
            return this.mWrapped.getHeaderIndex(i);
        }

        public int getHeaderViewType(int i) {
            return this.mWrapped.getHeaderViewType(i);
        }

        public int[] getItemPositionInterval(int i) {
            return this.mWrapped.getItemPositionInterval(i);
        }

        public void onBindHeaderViewHolder(HeaderViewHolder headerViewHolder, int i) {
            this.mWrapped.onBindHeaderViewHolder(headerViewHolder, i);
            View view = headerViewHolder.itemView;
            if (view instanceof CheckableView) {
                EditableListViewWrapper.this.setCheckableHeaderView((CheckableView) view, i);
            }
        }

        public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
            return this.mWrapped.onCreateHeaderViewHolder(viewGroup, i);
        }
    }

    public EditableListViewWrapper(GalleryRecyclerView galleryRecyclerView) {
        if (galleryRecyclerView != null) {
            this.mRecyclerView = galleryRecyclerView;
            this.mAdapterWrappers = new ArrayMap<>();
            this.mRecyclerView.setOnTouchListener(this.mAnimationManager);
            this.mRecyclerView.addOnScrollListener(this.mScrollDelegate);
            this.mCheckState = new CheckStateWithGroup(this.mRecyclerView, this.mAnimationManager);
            return;
        }
        throw new IllegalArgumentException("recyclerView can't be null");
    }

    private void checkMultiChoiceModeCallback() {
        MultiChoiceModeCallback multiChoiceModeCallback = this.mMultiChoiceModeCallback;
        if (multiChoiceModeCallback == null || !multiChoiceModeCallback.hasWrappedCallback()) {
            throw new IllegalStateException("no MultiChoiceModeListener is set");
        }
    }

    /* access modifiers changed from: private */
    public void enterChoiceMode() {
        this.mRecyclerView.setLongClickable(false);
        this.mRecyclerView.setOnItemClickListener(this.mCheckItemListener);
        for (int i = 0; i < this.mRecyclerView.getChildCount(); i++) {
            View childAt = this.mRecyclerView.getChildAt(i);
            if (childAt instanceof Checkable) {
                ((Checkable) childAt).setCheckable(true);
            }
        }
        this.mAnimationManager.startEnterActionModeAni();
        this.mCheckState.clear();
        notifyDataChanged();
    }

    /* access modifiers changed from: private */
    public void exitChoiceMode() {
        this.mRecyclerView.setLongClickable(true);
        this.mRecyclerView.setOnItemClickListener(this.mItemClickDelegate);
        for (int i = 0; i < this.mRecyclerView.getChildCount(); i++) {
            View childAt = this.mRecyclerView.getChildAt(i);
            if (childAt instanceof Checkable) {
                Checkable checkable = (Checkable) childAt;
                checkable.setCheckable(false);
                checkable.setChecked(false);
            }
        }
        this.mAnimationManager.startExistActionModeAni();
        this.mCheckState.clear();
        notifyDataChanged();
    }

    /* access modifiers changed from: private */
    public void handleDataChanged() {
        this.mCheckState.handleDataChanged();
        updateActionMode();
    }

    /* access modifiers changed from: private */
    public boolean isListHeaderOrFooter(int i) {
        return this.mAdapterWrapper.isHeaderOrFooterPosition(i);
    }

    /* access modifiers changed from: private */
    public void notifyDataChanged() {
        this.mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void setCheckableHeaderView(CheckableView checkableView, final int i) {
        checkableView.setCheckable(isInChoiceMode());
        if (isInChoiceMode()) {
            checkableView.setCheckableListener(new View.OnClickListener() {
                public void onClick(View view) {
                    boolean unused = EditableListViewWrapper.this.setGroupCheck(i);
                    EditableListViewWrapper.this.notifyDataChanged();
                    if (EditableListViewWrapper.this.mMultiChoiceModeCallback != null) {
                        EditableListViewWrapper.this.mMultiChoiceModeCallback.mWrapped.onAllItemsCheckedStateChanged(EditableListViewWrapper.this.mChoiceActionMode, true);
                    }
                }
            });
            checkableView.setChecked(this.mCheckState.getGroupCheckState(i));
        }
    }

    /* access modifiers changed from: private */
    public boolean setGroupCheck(int i) {
        if (this.mHeaderAdapterWrapper == null) {
            return false;
        }
        boolean groupCheckState = this.mCheckState.setGroupCheckState(i);
        updateOnScreenViewsState();
        updateActionMode();
        return groupCheckState;
    }

    /* access modifiers changed from: private */
    public void updateActionMode() {
        if (this.mChoiceActionMode != null) {
            if (this.mCheckState.getCount() == 0) {
                this.mChoiceActionMode.setTitle(this.mRecyclerView.getResources().getString(miui.R.string.select_item));
            } else {
                this.mChoiceActionMode.setTitle(this.mRecyclerView.getResources().getQuantityString(miui.R.plurals.items_selected, this.mCheckState.getCount(), new Object[]{Integer.valueOf(this.mCheckState.getCount())}));
            }
            if (this.mEditActionMode != null) {
                MiuiSdkCompat.setEditActionModeButton(this.mRecyclerView.getContext(), this.mEditActionMode, 16908314, isAllItemsChecked() ? 1 : 0);
            }
        }
    }

    private void updateOnScreenViewsState() {
        for (int i = 0; i < this.mRecyclerView.getChildCount(); i++) {
            View childAt = this.mRecyclerView.getChildAt(i);
            if (childAt instanceof Checkable) {
                ((Checkable) childAt).setChecked(this.mCheckState.getCheckState(this.mRecyclerView.getChildItemId(childAt)));
            }
        }
    }

    public void enableChoiceMode(boolean z) {
        this.mRecyclerView.setLongClickable(z);
        this.mRecyclerView.setOnItemLongClickListener(z ? this.mEnterActionModeListener : null);
    }

    public int getCheckedItemCount() {
        return this.mCheckState.getCount();
    }

    public int[] getCheckedItemOrderedPositions() {
        return this.mCheckState.getCheckedItemOrderedPositions();
    }

    public SparseBooleanArray getCheckedItemPositions() {
        return this.mCheckState.getCheckedItemPositions();
    }

    public List<CheckableAdapter.CheckedItem> getCheckedItems() {
        ArrayList arrayList = new ArrayList();
        SimpleWrapper simpleWrapper = this.mAdapterWrapper;
        if (simpleWrapper != null && (simpleWrapper.getWrapped() instanceof BaseMediaAdapter)) {
            BaseMediaAdapter baseMediaAdapter = (BaseMediaAdapter) this.mAdapterWrapper.getWrapped();
            int[] checkedItemOrderedPositions = getCheckedItemOrderedPositions();
            if (checkedItemOrderedPositions != null && checkedItemOrderedPositions.length > 0) {
                for (int checkedItem : checkedItemOrderedPositions) {
                    CheckableAdapter.CheckedItem checkedItem2 = baseMediaAdapter.getCheckedItem(checkedItem);
                    if (checkedItem2 != null) {
                        arrayList.add(checkedItem2);
                    }
                }
            }
        }
        return arrayList;
    }

    public void handleDataInvalid() {
        this.mCheckState.clear();
        updateActionMode();
    }

    public boolean isAllItemsChecked() {
        int count = this.mCheckState.getCount();
        return count > 0 && count == this.mAdapterWrapper.getSourceItemCount();
    }

    public boolean isCheckedItemContainVideo() {
        SimpleWrapper simpleWrapper = this.mAdapterWrapper;
        if (simpleWrapper != null && (simpleWrapper.getWrapped() instanceof BaseMediaAdapter)) {
            BaseMediaAdapter baseMediaAdapter = (BaseMediaAdapter) this.mAdapterWrapper.getWrapped();
            SparseBooleanArray checkedItemPositions = getCheckedItemPositions();
            if (checkedItemPositions != null && checkedItemPositions.size() > 0) {
                for (int i = 0; i < checkedItemPositions.size(); i++) {
                    if (checkedItemPositions.valueAt(i) && FileMimeUtil.isVideoFromMimeType(baseMediaAdapter.getMimeType(checkedItemPositions.keyAt(i)))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isInActionMode() {
        return this.mIsInActionMode;
    }

    public boolean isInChoiceMode() {
        return this.mIsInChoiceMode;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        SimpleWrapper simpleWrapper = this.mAdapterWrapper;
        this.mAdapterWrapper = this.mAdapterWrappers.get(adapter);
        if (simpleWrapper == null || simpleWrapper != this.mAdapterWrapper) {
            this.mCheckState.clear();
            SimpleWrapper simpleWrapper2 = this.mAdapterWrapper;
            if (simpleWrapper2 != null) {
                simpleWrapper2.unregisterAdapterDataObserver(this.mRestoreCheckStateObserver);
            }
            if (adapter == null) {
                this.mAdapterWrapper = null;
                this.mRecyclerView.setAdapter((RecyclerView.Adapter) null);
                handleDataInvalid();
                return;
            }
            if (this.mAdapterWrapper == null) {
                this.mAdapterWrapper = new SimpleWrapper(adapter);
                this.mAdapterWrappers.put(adapter, this.mAdapterWrapper);
            }
            this.mAdapterWrapper.setHasStableIds(adapter.hasStableIds());
            this.mAdapterWrapper.registerAdapterDataObserver(this.mRestoreCheckStateObserver);
            this.mRecyclerView.setAdapter(this.mAdapterWrapper);
            this.mCheckState.bind(this.mAdapterWrapper);
            handleDataChanged();
            return;
        }
        Log.d("EditableListViewWrapper", "setAdapter the same adapter");
    }

    public void setAllItemsCheckState(boolean z) {
        this.mCheckState.setAllChecked(z);
        updateOnScreenViewsState();
        updateActionMode();
        this.mMultiChoiceModeCallback.mWrapped.onAllItemsCheckedStateChanged(this.mChoiceActionMode, z);
        this.mAnimationManager.startScaleAllItemImageViewAnimation(this.mRecyclerView, z);
    }

    public void setEmptyView(View view) {
        this.mRecyclerView.setEmptyView(view);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.mRecyclerView.setLayoutManager(layoutManager);
    }

    public void setMultiChoiceModeListener(MultiChoiceModeListener multiChoiceModeListener) {
        if (this.mMultiChoiceModeCallback == null) {
            this.mMultiChoiceModeCallback = new MultiChoiceModeCallback();
        }
        this.mMultiChoiceModeCallback.setWrapped(multiChoiceModeListener);
    }

    public void setOnItemClickListener(ItemClickSupport.OnItemClickListener onItemClickListener) {
        this.mItemClickDelegate = onItemClickListener;
        this.mRecyclerView.setOnItemClickListener(onItemClickListener);
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    public void setStickyHeaderAdapter(StickyHeaderAdapter stickyHeaderAdapter) {
        if (this.mRecyclerView instanceof StickyHeaderRecyclerView) {
            this.mHeaderAdapterWrapper = new StickyHeaderAdapterWrapper(stickyHeaderAdapter);
            ((StickyHeaderRecyclerView) this.mRecyclerView).setStickyHeaderAdapter(this.mHeaderAdapterWrapper);
            this.mCheckState.bindStickyHeaderAdapter(stickyHeaderAdapter);
            return;
        }
        throw new IllegalArgumentException("the recycler view should be StickyHeaderRecyclerView");
    }

    public void setStickyHeaderLayoutManager(StickyHeaderLayoutManager stickyHeaderLayoutManager) {
        GalleryRecyclerView galleryRecyclerView = this.mRecyclerView;
        if (galleryRecyclerView instanceof StickyHeaderRecyclerView) {
            ((StickyHeaderRecyclerView) galleryRecyclerView).setStickyHeaderLayoutManager(stickyHeaderLayoutManager);
            return;
        }
        throw new IllegalArgumentException("the recycler view should be StickyHeaderRecyclerView");
    }

    public void startActionMode() {
        checkMultiChoiceModeCallback();
        if (!this.mIsInChoiceMode) {
            this.mChoiceActionMode = this.mRecyclerView.startActionMode(this.mMultiChoiceModeCallback);
            EditActionMode editActionMode = this.mChoiceActionMode;
            if (editActionMode instanceof EditActionMode) {
                this.mEditActionMode = editActionMode;
            }
            this.mIsInChoiceMode = true;
            this.mMultiChoiceModeCallback.mWrapped.onAllItemsCheckedStateChanged(this.mChoiceActionMode, false);
        }
    }

    public void stopActionMode() {
        ActionMode actionMode = this.mChoiceActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
    }
}
