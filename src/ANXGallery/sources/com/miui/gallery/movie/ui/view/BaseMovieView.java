package com.miui.gallery.movie.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.gallery.movie.MovieConfig;
import com.miui.gallery.movie.R;
import java.util.Locale;
import miui.view.animation.QuadraticEaseInOutInterpolator;

public abstract class BaseMovieView extends ViewGroup {
    protected View mDisplayView;
    private View mExtraContent;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                BaseMovieView.showView(BaseMovieView.this.mLoadingView, true);
            }
            super.handleMessage(message);
        }
    };
    private ImageView mIVDelete;
    private ImageView mIvPlay;
    /* access modifiers changed from: private */
    public View mLoadingView;
    /* access modifiers changed from: private */
    public float mPercent;
    private PlayProgressView mPlayProgress;
    private Button mPreviewBtn;
    /* access modifiers changed from: private */
    public IProgressChangeListener mProgressChangeListener;
    /* access modifiers changed from: private */
    public int mScaledTouchSlop;
    private TextView mTVDuration;
    /* access modifiers changed from: private */
    public boolean mTouchAvailable;
    /* access modifiers changed from: private */
    public float mViewWidth = 0.0f;

    public interface IProgressChangeListener {
        void onVideoProgressChanged();

        void onVideoProgressChanging(int i, float f);
    }

    public BaseMovieView(Context context) {
        super(context);
    }

    public BaseMovieView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseMovieView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static void showView(View view, boolean z) {
        if (view != null) {
            view.setVisibility(z ? 0 : 4);
        }
    }

    /* access modifiers changed from: protected */
    public abstract View createDisplayView();

    public void init() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.movie_view_display, this);
        this.mDisplayView = createDisplayView();
        this.mExtraContent = findViewById(R.id.extra_content);
        addView(this.mDisplayView, 0);
        this.mDisplayView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mIvPlay = (ImageView) findViewById(R.id.iv_play);
        this.mPlayProgress = (PlayProgressView) findViewById(R.id.play_progress);
        this.mTVDuration = (TextView) findViewById(R.id.tv_movie_duration);
        this.mIVDelete = (ImageView) findViewById(R.id.iv_movie_delete);
        this.mPreviewBtn = (Button) findViewById(R.id.preview_btn);
        this.mLoadingView = findViewById(R.id.progress_bar);
        this.mDisplayView.setOnTouchListener(new View.OnTouchListener() {
            private boolean changed;
            private float downX = 0.0f;
            private float dx = 0.0f;
            private int layout = -1;
            private float moveX = 0.0f;

            /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
                if (r5 != 3) goto L_0x0095;
             */
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!BaseMovieView.this.mTouchAvailable) {
                    return false;
                }
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action != 1) {
                        if (action == 2) {
                            this.moveX = motionEvent.getX();
                            this.dx = this.moveX - this.downX;
                            if (Math.abs(this.dx) > ((float) BaseMovieView.this.mScaledTouchSlop) && BaseMovieView.this.mViewWidth > 0.0f) {
                                float unused = BaseMovieView.this.mPercent = Math.abs(this.dx) / BaseMovieView.this.mViewWidth;
                                this.layout = this.dx < 0.0f ? 0 : 1;
                                this.changed = true;
                                if (BaseMovieView.this.mProgressChangeListener != null) {
                                    BaseMovieView.this.mProgressChangeListener.onVideoProgressChanging(this.layout, BaseMovieView.this.mPercent);
                                }
                            }
                        }
                        return false;
                    }
                    if (this.changed) {
                        if (BaseMovieView.this.mProgressChangeListener != null) {
                            BaseMovieView.this.mProgressChangeListener.onVideoProgressChanged();
                        }
                        return this.changed;
                    }
                    return false;
                }
                this.downX = motionEvent.getX();
                this.changed = false;
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
            int measuredHeight = (int) (((double) (i6 - this.mDisplayView.getMeasuredHeight())) / 1.6d);
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
        float movieRatio = MovieConfig.getMovieRatio();
        float f = (float) size;
        float f2 = (float) size2;
        float f3 = f / f2;
        if (Float.compare(movieRatio, -1.0f) == 0) {
            i4 = size;
        } else if (f3 < movieRatio) {
            i3 = (int) (f / movieRatio);
            i4 = size;
            this.mDisplayView.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
            this.mExtraContent.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
            this.mViewWidth = (float) i4;
            setMeasuredDimension(size, size2);
        } else {
            i4 = (int) (f2 * movieRatio);
        }
        i3 = size2;
        this.mDisplayView.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
        this.mExtraContent.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
        this.mViewWidth = (float) i4;
        setMeasuredDimension(size, size2);
    }

    public void setDeleteClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.mIVDelete;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void setDuration(int i) {
        TextView textView = this.mTVDuration;
        if (textView != null) {
            textView.setText(String.format(Locale.US, "00:%02d", new Object[]{Integer.valueOf(i)}));
        }
    }

    public void setIvPlayListener(View.OnClickListener onClickListener) {
        this.mIvPlay.setOnClickListener(onClickListener);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mDisplayView.setOnClickListener(onClickListener);
    }

    public void setPreviewBtnClickListener(View.OnClickListener onClickListener) {
        this.mPreviewBtn.setOnClickListener(onClickListener);
    }

    public void setProgressChangeListener(IProgressChangeListener iProgressChangeListener) {
        this.mProgressChangeListener = iProgressChangeListener;
    }

    public void setTouchAvailable(boolean z) {
        this.mTouchAvailable = z;
    }

    public void showDeleteIcon(boolean z) {
        showView(this.mIVDelete, z);
    }

    public void showDuration(boolean z) {
        showView(this.mTVDuration, z);
    }

    public void showExtraContent(boolean z) {
        View view = this.mExtraContent;
        Property property = View.ALPHA;
        float[] fArr = new float[2];
        float f = 0.0f;
        fArr[0] = z ? 0.0f : 1.0f;
        if (z) {
            f = 1.0f;
        }
        fArr[1] = f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, fArr);
        ofFloat.setInterpolator(new QuadraticEaseInOutInterpolator());
        ofFloat.setDuration(220);
        ofFloat.start();
    }

    public void showLoadingView(boolean z) {
        if (z) {
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 500);
            return;
        }
        this.mHandler.removeMessages(1);
        showView(this.mLoadingView, false);
    }

    public void showPlayBtn(boolean z) {
        showView(this.mIvPlay, z);
    }

    public void showPlayProgress(boolean z) {
        showView(this.mPlayProgress, z);
    }

    public void showPreviewBtn(boolean z) {
        showView(this.mPreviewBtn, z);
    }

    public void updatePlayProgress(float f) {
        this.mPlayProgress.setProgress(f);
    }
}
