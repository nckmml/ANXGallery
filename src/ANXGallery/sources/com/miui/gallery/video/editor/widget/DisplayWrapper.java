package com.miui.gallery.video.editor.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.video.editor.util.ToolsUtil;

public abstract class DisplayWrapper extends ViewGroup {
    public static int LEFT = 0;
    public static int RIGHT = 1;
    /* access modifiers changed from: private */
    public int LAYOUT = -1;
    /* access modifiers changed from: private */
    public float downX = 0.0f;
    /* access modifiers changed from: private */
    public float dx = 0.0f;
    private float mAspectRatio = -1.0f;
    private ImageView mAudioVoice;
    private TextView mAutoTrimButton;
    private View mDisplayView;
    private View mExtraContent;
    /* access modifiers changed from: private */
    public IProgress mIProgress;
    private ImageView mIvPlay;
    /* access modifiers changed from: private */
    public float mPercent;
    private VideoPlayProgress mPlayProgress;
    /* access modifiers changed from: private */
    public int mScaledTouchSlop;
    /* access modifiers changed from: private */
    public ImageView mThumbnailImageView;
    /* access modifiers changed from: private */
    public float mViewWidth = 0.0f;
    /* access modifiers changed from: private */
    public float moveX = 0.0f;
    /* access modifiers changed from: private */
    public float upX = 0.0f;

    public interface IProgress {
        void onVideoProgressChanged();

        void onVideoProgressChanging(int i, float f);
    }

    public DisplayWrapper(Context context) {
        super(context);
    }

    public DisplayWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DisplayWrapper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public abstract View createDisplayView();

    public boolean getAudioVoiceSelected() {
        return this.mAudioVoice.isSelected();
    }

    public View getDisplayView() {
        return this.mDisplayView;
    }

    public float getViewWidth() {
        return this.mViewWidth;
    }

    public void hideThumbnail() {
        if (this.mThumbnailImageView.getVisibility() == 0) {
            postDelayed(new Runnable() {
                public void run() {
                    DisplayWrapper.this.mThumbnailImageView.setImageBitmap((Bitmap) null);
                    DisplayWrapper.this.mThumbnailImageView.setVisibility(8);
                }
            }, 50);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mDisplayView = createDisplayView();
        this.mViewWidth = (float) getWidth();
        this.mExtraContent = findViewById(R.id.extra_content);
        addView(this.mDisplayView, 0);
        this.mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mThumbnailImageView = (ImageView) findViewById(R.id.thumbnail);
        this.mIvPlay = (ImageView) findViewById(R.id.iv_play);
        this.mAudioVoice = (ImageView) findViewById(R.id.audio_voice);
        this.mPlayProgress = (VideoPlayProgress) findViewById(R.id.play_progress);
        this.mAutoTrimButton = (TextView) findViewById(R.id.video_editor_btn_auto_trim);
        this.mDisplayView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action == 1) {
                        float unused = DisplayWrapper.this.upX = motionEvent.getX();
                        DisplayWrapper displayWrapper = DisplayWrapper.this;
                        float unused2 = displayWrapper.dx = displayWrapper.upX - DisplayWrapper.this.downX;
                        if (DisplayWrapper.this.mIProgress != null) {
                            DisplayWrapper.this.mIProgress.onVideoProgressChanged();
                        }
                    } else if (action == 2) {
                        float unused3 = DisplayWrapper.this.moveX = motionEvent.getX();
                        DisplayWrapper displayWrapper2 = DisplayWrapper.this;
                        float unused4 = displayWrapper2.dx = displayWrapper2.moveX - DisplayWrapper.this.downX;
                        if (Math.abs(DisplayWrapper.this.dx) > ((float) DisplayWrapper.this.mScaledTouchSlop) && DisplayWrapper.this.mViewWidth > 0.0f) {
                            DisplayWrapper displayWrapper3 = DisplayWrapper.this;
                            float unused5 = displayWrapper3.mPercent = Math.abs(displayWrapper3.dx) / DisplayWrapper.this.mViewWidth;
                            DisplayWrapper displayWrapper4 = DisplayWrapper.this;
                            int unused6 = displayWrapper4.LAYOUT = displayWrapper4.dx < 0.0f ? DisplayWrapper.LEFT : DisplayWrapper.RIGHT;
                            if (DisplayWrapper.this.mIProgress != null) {
                                DisplayWrapper.this.mIProgress.onVideoProgressChanging(DisplayWrapper.this.LAYOUT, DisplayWrapper.this.mPercent);
                            }
                        }
                    }
                    return false;
                }
                float unused7 = DisplayWrapper.this.downX = motionEvent.getX();
                return false;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        View view = this.mDisplayView;
        if (view != null) {
            int measuredWidth = (i5 - view.getMeasuredWidth()) / 2;
            int measuredWidth2 = this.mDisplayView.getMeasuredWidth() + measuredWidth;
            int measuredHeight = (i6 - this.mDisplayView.getMeasuredHeight()) / 2;
            int measuredHeight2 = this.mDisplayView.getMeasuredHeight() + measuredHeight;
            this.mDisplayView.layout(measuredWidth, measuredHeight, measuredWidth2, measuredHeight2);
            this.mExtraContent.layout(measuredWidth, measuredHeight, measuredWidth2, measuredHeight2);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        float f = (float) size;
        float f2 = (float) size2;
        float f3 = f / f2;
        if (Float.compare(this.mAspectRatio, -1.0f) != 0) {
            float f4 = this.mAspectRatio;
            if (f3 < f4) {
                i3 = (int) (f / f4);
                i4 = size;
                this.mDisplayView.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
                this.mExtraContent.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
                this.mViewWidth = (float) i4;
                setMeasuredDimension(size, size2);
            }
            i4 = (int) (f2 * f4);
        } else {
            i4 = size;
        }
        i3 = size2;
        this.mDisplayView.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
        this.mExtraContent.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
        this.mViewWidth = (float) i4;
        setMeasuredDimension(size, size2);
    }

    public void setAspectRatio(float f) {
        this.mAspectRatio = f;
        requestLayout();
    }

    public void setAudioVoiceListener(View.OnClickListener onClickListener) {
        this.mAudioVoice.setOnClickListener(onClickListener);
    }

    public void setAudioVoiceSelected(boolean z) {
        this.mAudioVoice.setSelected(z);
    }

    public void setAutoTrimEnable(boolean z) {
        this.mAutoTrimButton.setEnabled(z);
    }

    public void setAutoTrimListener(View.OnClickListener onClickListener) {
        this.mAutoTrimButton.setOnClickListener(onClickListener);
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        this.mDisplayView.setOnClickListener(onClickListener);
    }

    public void setIProgress(IProgress iProgress) {
        this.mIProgress = iProgress;
    }

    public void setIvPlayListener(View.OnClickListener onClickListener) {
        this.mIvPlay.setOnClickListener(onClickListener);
    }

    public void showAudioVoice(boolean z) {
        if (z) {
            ToolsUtil.showView(this.mAudioVoice);
        } else {
            ToolsUtil.hideView(this.mAudioVoice);
        }
    }

    public void showAutoTrimBtn(boolean z) {
        if (z) {
            ToolsUtil.showView(this.mAutoTrimButton);
        } else {
            ToolsUtil.hideView(this.mAutoTrimButton);
        }
    }

    public void showPlayBtn(boolean z) {
        if (z) {
            ToolsUtil.showView(this.mIvPlay);
        } else {
            ToolsUtil.hideView(this.mIvPlay);
        }
    }

    public void showPlayProgress(boolean z) {
        if (z) {
            ToolsUtil.showView(this.mPlayProgress);
        } else {
            ToolsUtil.hideView(this.mPlayProgress);
        }
    }

    public void showThumbnail(Bitmap bitmap) {
        this.mThumbnailImageView.setImageBitmap(bitmap);
    }

    public void updatePlayProgress(int i, int i2, int i3, int i4) {
        this.mPlayProgress.updateWidth(i, i2, i3, i4);
    }
}
