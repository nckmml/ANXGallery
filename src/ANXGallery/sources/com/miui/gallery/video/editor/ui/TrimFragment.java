package com.miui.gallery.video.editor.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.video.editor.VideoEditor;
import com.miui.gallery.video.editor.ui.menu.TrimView;
import com.miui.gallery.video.editor.widget.rangeseekbar.VideoEditorRangeSeekBar;
import com.miui.gallery.video.editor.widget.rangeseekbar.drawable.VideoThumbnailBackgroundDrawable;
import java.util.ArrayList;
import java.util.List;

public class TrimFragment extends MenuFragment implements VideoEditorRangeSeekBar.OnSeekBarChangeListener {
    private View mCancelView;
    private boolean mHasCliped;
    /* access modifiers changed from: private */
    public boolean mIsModified = false;
    private View mOkView;
    /* access modifiers changed from: private */
    public VideoEditorRangeSeekBar mRangeSeekBar;
    /* access modifiers changed from: private */
    public int mSavedEndRange = 0;
    /* access modifiers changed from: private */
    public int mSavedStartRange = 0;
    private MyStateChangeListener mStateChangeListener = new MyStateChangeListener();
    private TextView mTitleView;
    private TextView mTvVideoTime;
    /* access modifiers changed from: private */
    public boolean needResetTrimInfo = false;

    private class MyStateChangeListener implements VideoEditor.StateChangeListener {
        private MyStateChangeListener() {
        }

        public void onStateChanged(int i) {
            if (i == 1) {
                TrimFragment.this.mRangeSeekBar.setMax(TrimFragment.this.mVideoEditor.getProjectTotalTime());
                TrimFragment.this.updatePlayBtnView();
            }
        }

        public void onTimeChanged(int i) {
            TrimFragment.this.mRangeSeekBar.setProgress(i);
            TrimFragment.this.mRangeSeekBar.showProgressBar();
        }
    }

    private String getCurrentVideoTime(int i) {
        String str;
        int i2 = i / 60000;
        int i3 = (i - ((i2 * 60) * 1000)) / 1000;
        if (i2 >= 10) {
            str = i2 + "";
        } else if (i2 > 0) {
            str = MovieStatUtils.DOWNLOAD_SUCCESS + i2;
        } else {
            str = "00";
        }
        if (i3 >= 10) {
            return str + ":" + i3;
        } else if (i3 >= 1) {
            return str + ":0" + i3;
        } else {
            return str + ":01";
        }
    }

    private void initListener() {
        this.mOkView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                TrimFragment.this.lambda$initListener$127$TrimFragment(view);
            }
        });
        this.mCancelView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                TrimFragment.this.lambda$initListener$128$TrimFragment(view);
            }
        });
    }

    private void updateAutoTrimView() {
        if (this.mCallback != null) {
            this.mCallback.updateAutoTrimView();
        }
    }

    public boolean doApply() {
        VideoEditorRangeSeekBar videoEditorRangeSeekBar = this.mRangeSeekBar;
        if (videoEditorRangeSeekBar != null && videoEditorRangeSeekBar.isTouching()) {
            return false;
        }
        if (this.mVideoEditor == null) {
            Log.d("TrimFragment", "doApply: videoEditor is null.");
            return false;
        }
        final int startRange = this.mRangeSeekBar.getStartRange();
        final int endRange = this.mRangeSeekBar.getEndRange();
        if (startRange >= endRange || endRange - startRange <= 1000) {
            ToastUtils.makeText(this.mRangeSeekBar.getContext(), (int) R.string.video_editor_invalid_trim_arguments);
            onExitMode();
            return true;
        }
        this.mVideoEditor.setTrimInfo(startRange, endRange);
        this.mRangeSeekBar.lockRangeTo(startRange, endRange, new VideoEditorRangeSeekBar.ISeekbarZooming() {
            public void onAnimationEnd() {
                TrimFragment.this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                    public void onCompleted() {
                        if (TrimFragment.this.mVideoEditor != null) {
                            int unused = TrimFragment.this.mSavedStartRange = startRange;
                            int unused2 = TrimFragment.this.mSavedEndRange = endRange;
                            boolean unused3 = TrimFragment.this.needResetTrimInfo = true;
                            boolean unused4 = TrimFragment.this.mIsModified = false;
                            TrimFragment.this.mVideoEditor.play();
                            TrimFragment.this.recordEventWithApply();
                            TrimFragment.this.onExitMode();
                        }
                    }
                });
            }
        });
        return true;
    }

    public boolean doCancel() {
        if (this.mVideoEditor == null) {
            Log.d("TrimFragment", "doCancel: videoEditor is null.");
            return false;
        }
        VideoEditorRangeSeekBar videoEditorRangeSeekBar = this.mRangeSeekBar;
        if (videoEditorRangeSeekBar == null || videoEditorRangeSeekBar.isZooming() || this.mRangeSeekBar.isTouching()) {
            return false;
        }
        if (this.mHasCliped) {
            this.mVideoEditor.setTrimInfo(this.mSavedStartRange, this.mSavedEndRange);
            return this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                public void onCompleted() {
                    if (TrimFragment.this.mVideoEditor != null) {
                        boolean unused = TrimFragment.this.needResetTrimInfo = true;
                        TrimFragment.this.mVideoEditor.play();
                        TrimFragment.this.mRangeSeekBar.setStartRange(TrimFragment.this.mSavedStartRange);
                        TrimFragment.this.mRangeSeekBar.setEndRange(TrimFragment.this.mSavedEndRange);
                        boolean unused2 = TrimFragment.this.mIsModified = false;
                        TrimFragment.this.recordEventWithCancel();
                        TrimFragment.this.onExitMode();
                    }
                }
            });
        }
        onExitMode();
        return true;
    }

    public List<String> getCurrentEffect() {
        return new ArrayList();
    }

    public int getEffectId() {
        return R.id.video_editor_trim;
    }

    public /* synthetic */ void lambda$initListener$127$TrimFragment(View view) {
        doApply();
    }

    public /* synthetic */ void lambda$initListener$128$TrimFragment(View view) {
        doCancel();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new TrimView(viewGroup.getContext());
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mVideoEditor.removeStateChangeListener(this.mStateChangeListener);
        this.mRangeSeekBar = null;
        updateAutoTrimView();
    }

    public void onPlayButtonClicked() {
        if (this.mVideoEditor != null) {
            int state = this.mVideoEditor.getState();
            if ((state == 0 || state == 2) && this.mIsModified) {
                int startRange = this.mRangeSeekBar.getStartRange();
                int endRange = this.mRangeSeekBar.getEndRange();
                if (startRange >= endRange || endRange - startRange <= 1000) {
                    ToastUtils.makeText(this.mContext, (int) R.string.video_editor_invalid_trim_arguments);
                    return;
                }
                this.mVideoEditor.setTrimInfo(startRange, endRange);
                this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                    public void onCompleted() {
                        boolean unused = TrimFragment.this.needResetTrimInfo = true;
                        TrimFragment.this.mVideoEditor.play();
                    }
                });
                this.mIsModified = false;
            }
        }
    }

    public void onProgressChanged(VideoEditorRangeSeekBar videoEditorRangeSeekBar, int i, int i2, boolean z) {
        int endRange = this.mRangeSeekBar.getEndRange() - this.mRangeSeekBar.getStartRange();
        this.mRangeSeekBar.setStopSlide("00:01".equals(getCurrentVideoTime(endRange)));
        this.mVideoEditor.seek(this.mVideoEditor.getVideoStartTime() + i2, (VideoEditor.OnCompletedListener) null);
        this.mIsModified = true;
        this.mRangeSeekBar.hideProgressBar();
        this.mTvVideoTime.setText(getCurrentVideoTime(endRange));
        this.mHasCliped = true;
    }

    public void onProgressPreview(VideoEditorRangeSeekBar videoEditorRangeSeekBar, int i, int i2, boolean z) {
        if (this.mVideoEditor != null) {
            double projectTotalTime = ((double) i2) * ((double) this.mVideoEditor.getProjectTotalTime());
            this.mVideoEditor.seek((int) (projectTotalTime / ((double) this.mVideoEditor.getVideoTotalTime())), (VideoEditor.OnCompletedListener) null);
        }
    }

    public void onStartTrackingTouch(VideoEditorRangeSeekBar videoEditorRangeSeekBar, final int i, int i2) {
        if (this.needResetTrimInfo) {
            this.mVideoEditor.setTrimInfo(0, this.mVideoEditor.getVideoTotalTime());
            this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                public void onCompleted() {
                    if (TrimFragment.this.mVideoEditor != null) {
                        boolean unused = TrimFragment.this.needResetTrimInfo = false;
                        boolean unused2 = TrimFragment.this.mIsModified = true;
                        TrimFragment.this.mVideoEditor.seek(TrimFragment.this.mVideoEditor.getVideoStartTime() + i == 0 ? TrimFragment.this.mRangeSeekBar.getStartRange() : TrimFragment.this.mRangeSeekBar.getEndRange(), (VideoEditor.OnCompletedListener) null);
                    }
                }
            });
        }
        recordEventWithEffectChanged();
    }

    public void onVideoLoadCompleted() {
        VideoEditorRangeSeekBar videoEditorRangeSeekBar;
        super.onVideoLoadCompleted();
        if (this.mVideoEditor != null && (videoEditorRangeSeekBar = this.mRangeSeekBar) != null) {
            videoEditorRangeSeekBar.setTotal(this.mVideoEditor.getVideoTotalTime());
            this.mRangeSeekBar.setMax(this.mVideoEditor.getProjectTotalTime());
            this.mSavedEndRange = this.mVideoEditor.getVideoTotalTime();
            this.mSavedStartRange = 0;
            this.mRangeSeekBar.setStartRange(0);
            this.mRangeSeekBar.setEndRange(this.mVideoEditor.getVideoTotalTime());
            this.mRangeSeekBar.lockRangeTo(0, this.mVideoEditor.getVideoTotalTime(), (VideoEditorRangeSeekBar.ISeekbarZooming) null);
            this.mTvVideoTime.setText(getCurrentVideoTime(this.mVideoEditor.getProjectTotalTime()));
            this.mRangeSeekBar.setStopSlide("00:01".equals(getCurrentVideoTime(this.mVideoEditor.getVideoTotalTime())));
            updateAutoTrimView();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mCancelView = view.findViewById(R.id.cancel);
        this.mOkView = view.findViewById(R.id.ok);
        this.mTitleView = (TextView) view.findViewById(R.id.title);
        this.mTitleView.setText(this.mContext.getResources().getString(R.string.video_editor_trim));
        this.mRangeSeekBar = (VideoEditorRangeSeekBar) view.findViewById(R.id.video_editor_trim_range_seek_bar);
        this.mRangeSeekBar.setOnSeekBarChangeListener(this);
        this.mTvVideoTime = (TextView) view.findViewById(R.id.trim_tv_time);
        this.mRangeSeekBar.setTotal(this.mVideoEditor.getVideoTotalTime());
        this.mRangeSeekBar.setMax(this.mVideoEditor.getProjectTotalTime());
        this.mSavedStartRange = this.mRangeSeekBar.getStartRange();
        this.mSavedEndRange = this.mRangeSeekBar.getEndRange();
        this.needResetTrimInfo = true;
        this.mTvVideoTime.setText(getCurrentVideoTime(this.mVideoEditor.getProjectTotalTime()));
        this.mRangeSeekBar.setBitmapProvider(new VideoThumbnailBackgroundDrawable.BitmapProvider() {
            public Bitmap getBitmap(int i, int i2) {
                return TrimFragment.this.mVideoEditor.pickThumbnail(i);
            }
        });
        this.mRangeSeekBar.setThumbnailAspectRatio(this.mVideoEditor.getCurrentDisplayRatio());
        this.mVideoEditor.addStateChangeListener(this.mStateChangeListener);
        this.mVideoEditor.setOnVideoThumbnailChangedListener(new VideoEditor.OnVideoThumbnailChangedListener() {
            public void onVideoThumbnailChanged() {
                if (TrimFragment.this.mVideoEditor != null && TrimFragment.this.mRangeSeekBar != null) {
                    TrimFragment.this.mRangeSeekBar.setThumbnailAspectRatio(TrimFragment.this.mVideoEditor.getCurrentDisplayRatio());
                }
            }
        });
        updateAutoTrimView();
        initListener();
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        this.mRangeSeekBar.setTotal(this.mVideoEditor.getVideoTotalTime());
        this.mRangeSeekBar.setMax(this.mVideoEditor.getProjectTotalTime());
        this.mSavedStartRange = this.mRangeSeekBar.getStartRange();
        this.mSavedEndRange = this.mRangeSeekBar.getEndRange();
    }
}
