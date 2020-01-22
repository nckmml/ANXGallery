package com.miui.gallery.ui;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;
import com.miui.gallery.R;
import com.miui.gallery.util.ScreenUtils;

public class MonthViewTipFragment extends RichTipDialogFragment {
    /* access modifiers changed from: private */
    public VideoView mVideoView;

    private void initPlayer() {
        this.mVideoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.tip_month_view));
        if (Build.VERSION.SDK_INT >= 26) {
            this.mVideoView.setAudioFocusRequest(0);
        }
        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                MonthViewTipFragment.this.mVideoView.start();
            }
        });
    }

    private void updateLayoutParams(Configuration configuration) {
        FrameLayout.LayoutParams layoutParams = this.mVideoView.getLayoutParams() != null ? (FrameLayout.LayoutParams) this.mVideoView.getLayoutParams() : new FrameLayout.LayoutParams(-2, -2, 17);
        if (configuration.orientation == 2) {
            layoutParams.height = -2;
            layoutParams.width = -2;
        } else {
            layoutParams.width = (int) (((float) ScreenUtils.getScreenWidth()) * 0.58f);
            layoutParams.height = (int) (((float) layoutParams.width) * 1.016f);
        }
        this.mVideoView.setLayoutParams(layoutParams);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateLayoutParams(configuration);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setTitle(getResources().getString(R.string.month_view_tip_title));
        setSubTitle(getResources().getString(R.string.month_view_tip_sub_title));
        setPositiveButton(getResources().getString(R.string.month_view_tip_button_text));
        this.mVideoView = new VideoView(getActivity());
        updateLayoutParams(getResources().getConfiguration());
        setContentView(this.mVideoView);
        initPlayer();
        this.mVideoView.setZOrderOnTop(true);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mVideoView.stopPlayback();
        this.mVideoView = null;
    }

    public void onPause() {
        super.onPause();
        this.mVideoView.pause();
    }

    public void onResume() {
        super.onResume();
        this.mVideoView.start();
    }
}
