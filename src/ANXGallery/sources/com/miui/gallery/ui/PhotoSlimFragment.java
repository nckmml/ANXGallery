package com.miui.gallery.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.cleaner.slim.SlimController;
import com.miui.gallery.util.GallerySamplingStatHelper;
import java.util.HashMap;
import miui.app.AlertDialog;

public class PhotoSlimFragment extends BaseFragment implements SlimController.SpaceSlimObserver {
    private Button mActionButton;
    private View.OnClickListener mActionButtonClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            PhotoSlimFragment.this.onBackPressed();
        }
    };
    private TextView mActionDescription;
    private TextView mActionTitle;
    private AlertDialog mExitConfirmDialog;
    private SlimRotateProgressBar mProgressBar;
    private SlimController mSlimController;

    /* access modifiers changed from: private */
    public void onExit() {
        SlimController.getInstance().stop();
        this.mActivity.finish();
    }

    /* access modifiers changed from: private */
    public void onExitCancel() {
        SlimController.getInstance().resume();
        GallerySamplingStatHelper.recordCountEvent("cleaner", "slim_exit_cancel");
    }

    private String remainTime(int i) {
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 > 0 && i3 > 0) {
            return getResources().getString(R.string.slim_cost_time_minute_and_second, new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)});
        } else if (i3 > 0) {
            return getResources().getString(R.string.slim_cost_time_second, new Object[]{Integer.valueOf(i3)});
        } else {
            return getResources().getString(R.string.slim_cost_time_minute, new Object[]{Integer.valueOf(i2)});
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    private void showExitConfirmDialog() {
        AlertDialog alertDialog = this.mExitConfirmDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            SlimController slimController = this.mSlimController;
            int remainCount = SlimController.getInstance().getRemainCount();
            if (this.mExitConfirmDialog == null) {
                this.mExitConfirmDialog = new AlertDialog.Builder(this.mActivity).setPositiveButton(R.string.slim_exit_dialog_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PhotoSlimFragment.this.onExit();
                        GallerySamplingStatHelper.recordCountEvent("cleaner", "slim_exit_confirm");
                    }
                }).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PhotoSlimFragment.this.onExitCancel();
                    }
                }).setTitle(R.string.slim_exit_dialog_title).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        PhotoSlimFragment.this.onExitCancel();
                    }
                }).create();
            }
            this.mExitConfirmDialog.setMessage(getResources().getQuantityString(R.plurals.slim_exit_dialog_message, remainCount, new Object[]{Integer.valueOf(remainCount)}));
            this.mExitConfirmDialog.show();
        }
    }

    /* access modifiers changed from: private */
    public void showFinishState() {
        if (getActivity() != null) {
            int slimCount = this.mSlimController.getSlimCount();
            this.mActionDescription.setText(getResources().getQuantityString(R.plurals.slim_complete_photo_count, slimCount, new Object[]{Integer.valueOf(slimCount)}));
            this.mActionTitle.setText(R.string.slim_complete_action_title);
            this.mActionButton.setText(R.string.slim_complete);
            this.mActionButton.setBackgroundResource(R.drawable.btn_bg_slim_light);
            this.mActionButton.setTextColor(this.mActivity.getResources().getColorStateList(R.color.button_text_color_dark));
        }
    }

    public String getPageName() {
        return "photo_slim";
    }

    /* access modifiers changed from: protected */
    public int getThemeRes() {
        return 2131820868;
    }

    public boolean onBackPressed() {
        if (!this.mSlimController.isSlimming()) {
            onExit();
            return true;
        }
        this.mSlimController.pause();
        showExitConfirmDialog();
        HashMap hashMap = new HashMap();
        hashMap.put("durationBeforeExit", GallerySamplingStatHelper.formatValueStage((float) ((int) ((System.currentTimeMillis() - this.mSlimController.getStartTime()) / 1000)), SlimController.TIME_COST_STAGE));
        GallerySamplingStatHelper.recordCountEvent("cleaner", "slim_exit", hashMap);
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.mExitConfirmDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mExitConfirmDialog.dismiss();
        }
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.photo_slim_fragment, viewGroup, false);
        this.mActionTitle = (TextView) inflate.findViewById(R.id.title);
        this.mActionTitle.setText(R.string.slim_running);
        this.mActionDescription = (TextView) inflate.findViewById(R.id.description);
        this.mActionButton = (Button) inflate.findViewById(R.id.action_button);
        this.mActionButton.setText(R.string.slim_stop);
        this.mActionButton.setOnClickListener(this.mActionButtonClickListener);
        this.mProgressBar = (SlimRotateProgressBar) inflate.findViewById(R.id.progress_bar);
        this.mProgressBar.setDescription(getString(R.string.slim_space));
        this.mProgressBar.setNumber(0, false, (Runnable) null);
        this.mSlimController = SlimController.getInstance();
        return inflate;
    }

    public void onSlimPaused() {
        SlimController slimController = this.mSlimController;
        int remainCount = SlimController.getInstance().getRemainCount();
        this.mActionDescription.setText(getResources().getQuantityString(R.plurals.slim_remain_photo_count, remainCount, new Object[]{Integer.valueOf(remainCount)}));
        this.mProgressBar.pause();
    }

    public void onSlimProgress(long j, long j2, int i) {
        if (getActivity() != null && !this.mSlimController.isSlimPaused()) {
            this.mActionTitle.setText(remainTime((int) Math.ceil((double) (((float) (i + 1)) * 0.2f))));
            if (i >= 1) {
                this.mProgressBar.setNumber(j2, false, (Runnable) null);
                this.mActionDescription.setText(getResources().getQuantityString(R.plurals.slim_remain_photo_count, i, new Object[]{Integer.valueOf(i)}));
                return;
            }
            this.mProgressBar.setNumber(j2, true, new Runnable() {
                public void run() {
                    PhotoSlimFragment.this.showFinishState();
                }
            });
        }
    }

    public void onSlimResumed() {
        this.mProgressBar.resume();
    }

    public void onStart() {
        super.onStart();
        if (!this.mSlimController.isSlimStarted()) {
            this.mActivity.finish();
        }
        this.mSlimController.registerObserver(this);
        if (this.mSlimController.isSlimming()) {
            long releaseSize = this.mSlimController.getReleaseSize();
            this.mProgressBar.setNumber(releaseSize, false);
            if (this.mSlimController.isSlimPaused()) {
                showExitConfirmDialog();
                onSlimPaused();
                return;
            }
            onSlimProgress(-1, releaseSize, this.mSlimController.getRemainCount());
            return;
        }
        this.mProgressBar.setNumber(this.mSlimController.getReleaseSize());
        showFinishState();
    }

    public void onStop() {
        super.onStop();
        this.mSlimController.unregisterObserver(this);
    }
}
