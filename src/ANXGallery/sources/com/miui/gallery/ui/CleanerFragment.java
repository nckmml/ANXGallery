package com.miui.gallery.ui;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.cleaner.ScanResult;
import com.miui.gallery.cleaner.ScanResultAdapter;
import com.miui.gallery.cleaner.ScannerManager;
import com.miui.gallery.util.DividerItemDecoration;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.widget.recyclerview.CleanerItemAnimator;
import java.util.HashMap;
import java.util.List;
import miui.app.AlertDialog;
import miui.view.animation.CubicEaseInOutInterpolator;

public class CleanerFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public ActionLayout mActionLayout;
    private ScanResultAdapter mAdapter;
    private Dialog mExitConfirmDialog;
    /* access modifiers changed from: private */
    public int mExpandHeight;
    private boolean mIsScanStarted;
    /* access modifiers changed from: private */
    public CleanerRotateProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public int mRetractHeight;
    /* access modifiers changed from: private */
    public ScanLayout mScanLayout;
    private ScannerManager.ScanObserver mScanObserver = new ScannerManager.ScanObserver() {
        public void onScanCanceled() {
        }

        public void onScanFinish(long j) {
            if (CleanerFragment.this.mScannerManager.isScanResultEmpty()) {
                CleanerFragment.this.mProgressBar.showEmptyAnim();
                CleanerFragment.this.mActionLayout.showNoScanResultTip();
                return;
            }
            CleanerFragment.this.updateScanResult();
            CleanerFragment.this.mProgressBar.setNumber(j, true, new Runnable() {
                public void run() {
                    CleanerFragment.this.mActionLayout.hide(true);
                    CleanerFragment.this.mScanLayout.retract(true);
                    CleanerFragment.this.mProgressBar.hideRingCircle(true);
                }
            });
        }

        public void onScanProgress(long j) {
            CleanerFragment.this.mProgressBar.setNumber(j, true);
        }

        public void onScanResultUpdate(long j) {
            if (!CleanerFragment.this.mScannerManager.isScanFinish()) {
                return;
            }
            if (CleanerFragment.this.mScannerManager.isScanResultEmpty()) {
                CleanerFragment.this.mProgressBar.showEmptyAnim();
                CleanerFragment.this.mScanLayout.expand(true);
                CleanerFragment.this.mActionLayout.show(true);
                CleanerFragment.this.mActionLayout.showNoScanResultTip();
                return;
            }
            CleanerFragment.this.updateScanResult();
            if (!CleanerFragment.this.mProgressBar.isRotating()) {
                CleanerFragment.this.mProgressBar.hideRingCircle(false);
                CleanerFragment.this.mProgressBar.setNumber(CleanerFragment.this.mScannerManager.getScanSize());
            }
        }

        public void onScanStart() {
            CleanerFragment.this.mActionLayout.show(false);
            CleanerFragment.this.mScanLayout.expand(false);
            CleanerFragment.this.mProgressBar.showRingCircle(false);
            CleanerFragment.this.mProgressBar.setNumber(0, true);
        }
    };
    /* access modifiers changed from: private */
    public ScannerManager mScannerManager;
    /* access modifiers changed from: private */
    public int mScreenHeight;

    private class ActionLayout implements View.OnClickListener {
        private AlphaAnimation mAlphaAnimation = null;
        private TextView mButton;
        private TextView mDescription;
        private ViewGroup.MarginLayoutParams mLayoutParams;
        private TextView mTitle;
        /* access modifiers changed from: private */
        public View mView;

        public ActionLayout(View view) {
            this.mView = view;
            this.mView.setVisibility(8);
            this.mLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            this.mTitle = (TextView) view.findViewById(R.id.title);
            this.mDescription = (TextView) view.findViewById(R.id.description);
            this.mButton = (TextView) view.findViewById(R.id.stop_scan);
            this.mButton.setOnClickListener(this);
        }

        private void cancelAnimation() {
            AlphaAnimation alphaAnimation = this.mAlphaAnimation;
            if (alphaAnimation != null) {
                alphaAnimation.setAnimationListener((Animation.AnimationListener) null);
                this.mAlphaAnimation.cancel();
                this.mAlphaAnimation = null;
                this.mView.clearAnimation();
            }
        }

        /* access modifiers changed from: private */
        public void showNoScanResultTip() {
            this.mTitle.setText(R.string.cleaner_scan_finish);
            this.mDescription.setText(R.string.cleaner_scan_no_result);
            this.mButton.setText(R.string.cleaner_scan_ok);
        }

        public void hide(boolean z) {
            cancelAnimation();
            if (!z || !isVisible()) {
                this.mView.setVisibility(8);
                return;
            }
            this.mAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            this.mAlphaAnimation.setDuration(350);
            this.mAlphaAnimation.setInterpolator(new CubicEaseInOutInterpolator());
            this.mAlphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    ActionLayout.this.mView.setVisibility(8);
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            this.mView.startAnimation(this.mAlphaAnimation);
        }

        public boolean isVisible() {
            return this.mView.getVisibility() == 0;
        }

        public void onClick(View view) {
            if (isVisible()) {
                CleanerFragment.this.onBackPressed();
            }
        }

        public void setHeight(int i, int i2) {
            ViewGroup.MarginLayoutParams marginLayoutParams = this.mLayoutParams;
            marginLayoutParams.topMargin = i;
            marginLayoutParams.height = i2;
            this.mView.setLayoutParams(marginLayoutParams);
        }

        public void setMarginTop(int i) {
            ViewGroup.MarginLayoutParams marginLayoutParams = this.mLayoutParams;
            marginLayoutParams.topMargin = i;
            this.mView.setLayoutParams(marginLayoutParams);
        }

        public void show(boolean z) {
            show(z, 0);
        }

        public void show(boolean z, long j) {
            cancelAnimation();
            if (z && !isVisible()) {
                this.mAlphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                this.mAlphaAnimation.setStartOffset(j);
                this.mAlphaAnimation.setDuration(500);
                this.mAlphaAnimation.setInterpolator(new CubicEaseInOutInterpolator());
                this.mView.startAnimation(this.mAlphaAnimation);
            }
            this.mView.setVisibility(0);
        }
    }

    private class ScanLayout {
        private ValueAnimator mFlexAnimator = null;
        private ViewGroup.LayoutParams mLayoutParams;
        public View mView;

        public ScanLayout(View view) {
            this.mView = view;
            this.mLayoutParams = new RecyclerView.LayoutParams(-1, CleanerFragment.this.mExpandHeight);
            this.mView.setLayoutParams(this.mLayoutParams);
            this.mView.setBackgroundResource(R.color.cleaner_scan_layout_background);
        }

        private void cancelAnimation() {
            ValueAnimator valueAnimator = this.mFlexAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                this.mFlexAnimator = null;
            }
        }

        private void initFlexAnimator(int i, int i2) {
            this.mFlexAnimator = new ValueAnimator();
            this.mFlexAnimator.setIntValues(new int[]{i, i2});
            this.mFlexAnimator.setDuration(500);
            this.mFlexAnimator.setInterpolator(new CubicEaseInOutInterpolator());
            this.mFlexAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Integer num = (Integer) valueAnimator.getAnimatedValue();
                    if (num != null) {
                        ScanLayout.this.setHeight(num.intValue());
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        public void setHeight(int i) {
            ViewGroup.LayoutParams layoutParams = this.mLayoutParams;
            layoutParams.height = i;
            this.mView.setLayoutParams(layoutParams);
            CleanerFragment.this.mActionLayout.setMarginTop(i);
        }

        public void expand(boolean z) {
            expand(z, 0);
        }

        public void expand(boolean z, long j) {
            cancelAnimation();
            if (!z) {
                setHeight(CleanerFragment.this.mExpandHeight);
                return;
            }
            int i = this.mLayoutParams.height;
            if (i != CleanerFragment.this.mExpandHeight) {
                initFlexAnimator(i, CleanerFragment.this.mExpandHeight);
                this.mFlexAnimator.setStartDelay(j);
                this.mFlexAnimator.start();
            }
        }

        public boolean isAnimationRunning() {
            ValueAnimator valueAnimator = this.mFlexAnimator;
            return valueAnimator != null && valueAnimator.isRunning();
        }

        public void retract(boolean z) {
            retract(z, 0);
        }

        public void retract(boolean z, long j) {
            cancelAnimation();
            if (!z) {
                setHeight(CleanerFragment.this.mRetractHeight);
                return;
            }
            int i = this.mLayoutParams.height;
            if (i != CleanerFragment.this.mRetractHeight) {
                initFlexAnimator(i, CleanerFragment.this.mRetractHeight);
                this.mFlexAnimator.setStartDelay(j);
                this.mFlexAnimator.start();
            }
        }
    }

    private class ScanResultDiffCallback extends DiffUtil.Callback {
        private List<ScanResult> mNewList;
        private List<ScanResult> mOldList;

        public ScanResultDiffCallback(List<ScanResult> list, List<ScanResult> list2) {
            this.mOldList = list;
            this.mNewList = list2;
        }

        public boolean areContentsTheSame(int i, int i2) {
            List<ScanResult> list = this.mOldList;
            long j = 0;
            long size = (list == null || i >= list.size()) ? 0 : this.mOldList.get(i).getSize();
            List<ScanResult> list2 = this.mNewList;
            if (list2 != null && i2 < list2.size()) {
                j = this.mNewList.get(i2).getSize();
            }
            return size == j;
        }

        public boolean areItemsTheSame(int i, int i2) {
            List<ScanResult> list = this.mOldList;
            int i3 = -1;
            int type = (list == null || i >= list.size()) ? -1 : this.mOldList.get(i).getType();
            List<ScanResult> list2 = this.mNewList;
            if (list2 != null && i2 < list2.size()) {
                i3 = this.mNewList.get(i2).getType();
            }
            return type == i3;
        }

        public int getNewListSize() {
            List<ScanResult> list = this.mNewList;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public int getOldListSize() {
            List<ScanResult> list = this.mOldList;
            if (list == null) {
                return 0;
            }
            return list.size();
        }
    }

    private class ScanResultLayoutManager extends LinearLayoutManager {
        public ScanResultLayoutManager(Context context) {
            super(context);
        }

        public boolean canScrollVertically() {
            return !CleanerFragment.this.mActionLayout.isVisible() && !CleanerFragment.this.mScanLayout.isAnimationRunning();
        }

        /* access modifiers changed from: protected */
        public int getExtraLayoutSpace(RecyclerView.State state) {
            return CleanerFragment.this.mScreenHeight;
        }
    }

    private void initLayoutHeight(int i) {
        this.mScreenHeight = i;
        int integer = getResources().getInteger(R.integer.cleaner_scan_layout_expand_percent);
        int i2 = this.mScreenHeight;
        this.mExpandHeight = (integer * i2) / 100;
        int i3 = this.mExpandHeight;
        this.mRetractHeight = i2 - i3;
        this.mScanLayout.setHeight(i3);
        this.mActionLayout.setHeight(this.mExpandHeight, this.mRetractHeight);
    }

    /* access modifiers changed from: private */
    public void onExit() {
        this.mScannerManager.resetScan();
        this.mActivity.finish();
    }

    /* access modifiers changed from: private */
    public void onExitCancel() {
        GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_scan_exit_cancel");
    }

    private void setLayoutByScanResult() {
        if (!this.mIsScanStarted) {
            if (this.mScannerManager.isScanning()) {
                this.mActionLayout.show(false);
                this.mScanLayout.expand(false);
                this.mProgressBar.showRingCircle(false);
            } else if (this.mScannerManager.isScanResultEmpty()) {
                this.mScanLayout.expand(true, 300);
                this.mActionLayout.show(true, 300);
                this.mProgressBar.showEmptyAnim(300);
                this.mActionLayout.showNoScanResultTip();
            } else {
                this.mScanLayout.retract(false);
                this.mProgressBar.hideRingCircle(false);
                this.mActionLayout.hide(false);
                this.mProgressBar.setNumber(this.mScannerManager.getScanSize());
                updateScanResult();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateScanResult() {
        List<ScanResult> dataList = this.mAdapter.getDataList();
        List<ScanResult> scanResults = this.mScannerManager.getScanResults();
        DiffUtil.calculateDiff(new ScanResultDiffCallback(dataList, scanResults)).dispatchUpdatesTo((RecyclerView.Adapter) this.mAdapter);
        this.mAdapter.updateDataList(scanResults);
    }

    public String getPageName() {
        return "cleaner";
    }

    /* access modifiers changed from: protected */
    public int getThemeRes() {
        return 2131820868;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    public void onActivityAttachedToWindow() {
        initLayoutHeight(ScreenUtils.getExactScreenHeight(this.mActivity));
        setLayoutByScanResult();
    }

    /* JADX WARNING: type inference failed for: r2v7, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public boolean onBackPressed() {
        if (this.mProgressBar.isRotating() || this.mScannerManager.isScanning()) {
            if (this.mExitConfirmDialog == null) {
                this.mExitConfirmDialog = new AlertDialog.Builder(this.mActivity).setPositiveButton(R.string.cleaner_scan_exit_dialog_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CleanerFragment.this.onExit();
                        GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_scan_exit_confirm");
                    }
                }).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CleanerFragment.this.onExitCancel();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        CleanerFragment.this.onExitCancel();
                    }
                }).setTitle(R.string.cleaner_scan_exit_dialog_title).setMessage(R.string.cleaner_scan_exit_dialog_message).create();
            }
            this.mExitConfirmDialog.show();
            HashMap hashMap = new HashMap();
            hashMap.put("durationBeforeExit", GallerySamplingStatHelper.formatValueStage((float) ((int) ((System.currentTimeMillis() - this.mScannerManager.getStartTime()) / 1000)), ScannerManager.TIME_COST_STAGE));
            GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_scan_exit", hashMap);
            return true;
        }
        onExit();
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        Dialog dialog = this.mExitConfirmDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mExitConfirmDialog.dismiss();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r6v5, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cleaner_fragment, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        this.mRecyclerView.setLayoutManager(new ScanResultLayoutManager(this.mActivity));
        this.mRecyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.cleaner_divider), 1, 1));
        this.mAdapter = new ScanResultAdapter(this.mActivity);
        this.mRecyclerView.setAdapter(this.mAdapter);
        CleanerItemAnimator cleanerItemAnimator = new CleanerItemAnimator();
        cleanerItemAnimator.setSupportsChangeAnimations(false);
        this.mRecyclerView.setItemAnimator(cleanerItemAnimator);
        this.mActionLayout = new ActionLayout(inflate.findViewById(R.id.action_layout));
        this.mProgressBar = new CleanerRotateProgressBar(this.mActivity);
        this.mScanLayout = new ScanLayout(this.mProgressBar);
        this.mAdapter.setHeader(this.mScanLayout.mView);
        this.mScannerManager = ScannerManager.getInstance();
        return inflate;
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    public void onStart() {
        int exactScreenHeight;
        super.onStart();
        this.mScannerManager.registerObserver(this.mScanObserver);
        if (this.mScreenHeight == 0 && (exactScreenHeight = ScreenUtils.getExactScreenHeight(this.mActivity)) != this.mScreenHeight) {
            initLayoutHeight(exactScreenHeight);
        }
        if (this.mScannerManager.isReset()) {
            this.mScannerManager.startScan();
            this.mIsScanStarted = true;
            return;
        }
        this.mIsScanStarted = false;
        if (this.mScreenHeight != 0) {
            setLayoutByScanResult();
        }
    }

    public void onStop() {
        super.onStop();
        this.mScannerManager.unregisterObserver(this.mScanObserver);
    }
}
