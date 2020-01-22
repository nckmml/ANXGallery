package com.miui.gallery.projection;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.projection.IConnectController;
import com.miui.gallery.util.FormatUtil;
import com.miui.gallery.util.Log;

public class ProjectionVideoController extends RelativeLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, IConnectController.OnMediaPlayListener {
    /* access modifiers changed from: private */
    public ConnectController mConnectControl;
    /* access modifiers changed from: private */
    public boolean mDragging;
    private OnFinishListener mFinishListener;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 100) {
                int access$000 = ProjectionVideoController.this.showProgress();
                Log.i("RemoteVideoControl", "show progress %s pos %d", Boolean.valueOf(ProjectionVideoController.this.mDragging), Integer.valueOf(access$000));
                if (!ProjectionVideoController.this.mDragging && ProjectionVideoController.this.mConnectControl.isPlaying()) {
                    message = obtainMessage(100);
                    removeMessages(100);
                    sendMessageDelayed(message, (long) (1000 - (access$000 % 1000)));
                }
            }
            super.handleMessage(message);
        }
    };
    private ImageView mIvPause;
    private LinearLayout mLayoutQuit;
    private SeekBar mSbSeek;
    private TextView mTvCurPos;
    private TextView mTvDuration;

    public interface OnFinishListener {
        void onFinish();
    }

    public ProjectionVideoController(Context context) {
        super(context);
    }

    public ProjectionVideoController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ProjectionVideoController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: private */
    public int showProgress() {
        ConnectController connectController = this.mConnectControl;
        if (connectController == null || this.mDragging) {
            return 0;
        }
        int currentPosition = connectController.getCurrentPosition();
        int duration = this.mConnectControl.getDuration();
        updateStatus();
        Log.v("RemoteVideoControl", "position %d, duration %d", Integer.valueOf(currentPosition), Integer.valueOf(duration));
        if (duration == -1) {
            return 0;
        }
        SeekBar seekBar = this.mSbSeek;
        if (seekBar != null && duration > 0) {
            long j = (((long) currentPosition) * 100) / ((long) duration);
            seekBar.setProgress((int) j);
            Log.v("RemoteVideoControl", "seek set %d", Long.valueOf(j));
        }
        if (duration == 0) {
            return currentPosition;
        }
        TextView textView = this.mTvDuration;
        if (textView != null) {
            textView.setText(FormatUtil.formatVideoDuration((long) (duration / 1000)));
        }
        TextView textView2 = this.mTvCurPos;
        if (textView2 != null) {
            textView2.setText(FormatUtil.formatVideoDuration((long) (currentPosition / 1000)));
        }
        return currentPosition;
    }

    /* access modifiers changed from: protected */
    public void clearStatus() {
        TextView textView = this.mTvDuration;
        if (textView != null) {
            textView.setText("");
        }
        TextView textView2 = this.mTvCurPos;
        if (textView2 != null) {
            textView2.setText("");
        }
        SeekBar seekBar = this.mSbSeek;
        if (seekBar != null) {
            seekBar.setProgress(0);
        }
        updateStatus();
    }

    /* access modifiers changed from: protected */
    public int getPauseImageResId() {
        return R.drawable.projection_video_pause;
    }

    /* access modifiers changed from: protected */
    public int getPlayImageResId() {
        return R.drawable.projection_video_play;
    }

    public void initView() {
        this.mConnectControl = ConnectControllerSingleton.getInstance();
        this.mLayoutQuit = (LinearLayout) findViewById(R.id.layout_quit);
        this.mLayoutQuit.setOnClickListener(this);
        this.mIvPause = (ImageView) findViewById(R.id.iv_pause);
        this.mIvPause.setOnClickListener(this);
        this.mTvCurPos = (TextView) findViewById(R.id.tv_cur_time);
        this.mTvDuration = (TextView) findViewById(R.id.tv_duration);
        this.mSbSeek = (SeekBar) findViewById(R.id.sb_seek);
        this.mSbSeek.setOnSeekBarChangeListener(this);
    }

    public void onClick(View view) {
        if (view == this.mLayoutQuit) {
            stopPlay();
        } else if (view == this.mIvPause) {
            if (this.mConnectControl.isPlaying()) {
                this.mConnectControl.pause();
            } else {
                this.mConnectControl.resume();
            }
            updateStatus();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        stopPlay();
        super.onDetachedFromWindow();
    }

    public void onLoading() {
    }

    public void onPaused() {
        updateStatus();
    }

    public void onPlaying() {
        updateStatus();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (seekBar == this.mSbSeek && z) {
            this.mConnectControl.getCurrentPosition();
            long duration = (((long) this.mConnectControl.getDuration()) * ((long) i)) / 100;
            this.mConnectControl.seekTo((int) duration);
            TextView textView = this.mTvCurPos;
            if (textView != null) {
                textView.setText(FormatUtil.formatVideoDuration(duration / 1000));
            }
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar == this.mSbSeek) {
            this.mDragging = true;
            this.mConnectControl.pause();
            updateStatus();
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar == this.mSbSeek) {
            this.mDragging = false;
            this.mConnectControl.resume();
            updateStatus();
        }
    }

    public void onStopped() {
        updateStatus();
        stopPlay();
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.mFinishListener = onFinishListener;
    }

    public void startPlay(String str, String str2) {
        clearStatus();
        this.mConnectControl.playVideo(str, str2);
        this.mConnectControl.resume();
        this.mConnectControl.registerMediaPlayListener(this);
        this.mHandler.removeMessages(100);
        this.mHandler.sendEmptyMessage(100);
    }

    public void stopPlay() {
        this.mConnectControl.stop();
        this.mConnectControl.unregisterMediaPlayListener(this);
        clearStatus();
        this.mHandler.removeMessages(100);
        OnFinishListener onFinishListener = this.mFinishListener;
        if (onFinishListener != null) {
            onFinishListener.onFinish();
        }
    }

    public void updateStatus() {
        if (this.mConnectControl.isPlaying()) {
            this.mIvPause.setImageResource(getPauseImageResId());
            this.mHandler.removeMessages(100);
            this.mHandler.sendEmptyMessage(100);
            return;
        }
        this.mIvPause.setImageResource(getPlayImageResId());
        this.mHandler.removeMessages(100);
    }
}
