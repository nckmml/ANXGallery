package com.miui.gallery.cleaner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import androidx.core.content.ContextCompat;
import com.miui.gallery.R;

public class ScanResult {
    /* access modifiers changed from: private */
    public int mAction;
    /* access modifiers changed from: private */
    public int mCount;
    /* access modifiers changed from: private */
    public int mCountText;
    /* access modifiers changed from: private */
    public int mDescription;
    /* access modifiers changed from: private */
    public ResultImage[] mImages;
    /* access modifiers changed from: private */
    public OnScanResultClickListener mOnClickListener;
    /* access modifiers changed from: private */
    public long mSize;
    /* access modifiers changed from: private */
    public int mTitle;
    /* access modifiers changed from: private */
    public int mType;

    public static class Builder {
        private int mCount;
        private int mCountText = R.plurals.photo_count_format;
        private ResultImage[] mImages;
        private OnScanResultClickListener mOnClickListener;
        private long mSize;
        private int mType = -1;

        public ScanResult build() {
            ScanResult scanResult = new ScanResult();
            int i = this.mType;
            if (i != -1) {
                if (i == 0) {
                    int unused = scanResult.mTitle = R.string.cleaner_slim_title;
                    int unused2 = scanResult.mDescription = R.string.cleaner_slim_description;
                    int unused3 = scanResult.mAction = R.string.cleaner_slim_action;
                } else if (i == 1) {
                    int unused4 = scanResult.mTitle = R.string.cleaner_screen_shot_title;
                    int unused5 = scanResult.mDescription = R.string.cleaner_screen_shot_description;
                    int unused6 = scanResult.mAction = R.string.cleaner_screen_shot_action;
                } else if (i == 2) {
                    int unused7 = scanResult.mTitle = R.string.cleaner_video_title;
                    int unused8 = scanResult.mDescription = R.string.cleaner_video_description;
                    int unused9 = scanResult.mAction = R.string.cleaner_video_action;
                } else if (i == 3) {
                    int unused10 = scanResult.mTitle = R.string.cleaner_similar_title;
                    int unused11 = scanResult.mDescription = R.string.cleaner_similar_description;
                    int unused12 = scanResult.mAction = R.string.cleaner_similar_action;
                } else if (i == 4) {
                    int unused13 = scanResult.mTitle = R.string.cleaner_raw_title;
                    int unused14 = scanResult.mDescription = R.string.cleaner_raw_description;
                    int unused15 = scanResult.mAction = R.string.cleaner_raw_action;
                }
                if (scanResult.mTitle == 0) {
                    throw new RuntimeException("the title must not be empty.");
                } else if (scanResult.mAction == 0) {
                    throw new RuntimeException("the action must not be empty.");
                } else if (this.mOnClickListener != null) {
                    int unused16 = scanResult.mType = this.mType;
                    long j = this.mSize;
                    if (j < 0) {
                        j = 0;
                    }
                    long unused17 = scanResult.mSize = j;
                    ResultImage[] unused18 = scanResult.mImages = this.mImages;
                    int unused19 = scanResult.mCount = this.mCount;
                    int unused20 = scanResult.mCountText = this.mCountText;
                    OnScanResultClickListener unused21 = scanResult.mOnClickListener = this.mOnClickListener;
                    return scanResult;
                } else {
                    throw new RuntimeException("the OnScanResultClickListener must not be null");
                }
            } else {
                throw new RuntimeException("the type must set.");
            }
        }

        public Builder setCount(int i) {
            this.mCount = i;
            return this;
        }

        public Builder setImages(ResultImage[] resultImageArr) {
            this.mImages = resultImageArr;
            return this;
        }

        public Builder setOnScanResultClickListener(OnScanResultClickListener onScanResultClickListener) {
            this.mOnClickListener = onScanResultClickListener;
            return this;
        }

        public Builder setSize(long j) {
            this.mSize = j;
            return this;
        }

        public Builder setType(int i) {
            this.mType = i;
            return this;
        }
    }

    private static class CenteredImageSpan extends ImageSpan {
        CenteredImageSpan(Drawable drawable) {
            super(drawable);
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable drawable = getDrawable();
            canvas.save();
            canvas.translate(f, ((float) (i5 - drawable.getBounds().bottom)) / 2.0f);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    public interface OnScanResultClickListener {
        void onClick(Context context);
    }

    public static class ResultImage {
        public long mId;
        public String mPath;

        public ResultImage(long j, String str) {
            this.mId = j;
            this.mPath = str;
        }
    }

    private ScanResult() {
    }

    public int getAction() {
        return this.mAction;
    }

    public int getCount() {
        return this.mCount;
    }

    public int getCountText() {
        return this.mCountText;
    }

    public int getDescription() {
        return this.mDescription;
    }

    public ResultImage[] getImages() {
        return this.mImages;
    }

    public CharSequence getMergedTitle(Context context) {
        String string = context.getString(getTitle());
        if (getCount() <= 0) {
            return string;
        }
        String quantityString = context.getResources().getQuantityString(getCountText(), getCount(), new Object[]{Integer.valueOf(getCount())});
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.info_divider);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        CenteredImageSpan centeredImageSpan = new CenteredImageSpan(drawable);
        SpannableString spannableString = new SpannableString(string + "+" + quantityString);
        spannableString.setSpan(centeredImageSpan, string.length(), string.length() + 1, 33);
        return spannableString;
    }

    public long getSize() {
        return this.mSize;
    }

    public int getTitle() {
        return this.mTitle;
    }

    public int getType() {
        return this.mType;
    }

    public void onClick(Context context) {
        OnScanResultClickListener onScanResultClickListener = this.mOnClickListener;
        if (onScanResultClickListener != null) {
            onScanResultClickListener.onClick(context);
        }
    }
}
