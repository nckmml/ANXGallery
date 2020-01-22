package com.miui.gallery.editor.photo.app.frame;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.FrameData;

public class FrameItemHolder extends RecyclerView.ViewHolder {
    private Rect mBound = new Rect();
    private FrameItemPreviewDrawable mDrawable;
    private TextView mTextView;

    public FrameItemHolder(Context context, View view) {
        super(view);
        this.mTextView = (TextView) view.findViewById(R.id.text);
        this.mDrawable = new FrameItemPreviewDrawable(context.getResources());
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.frame_menu_image_size);
        this.mBound.set(0, 0, dimensionPixelSize, dimensionPixelSize);
    }

    /* access modifiers changed from: package-private */
    public void setFrameData(FrameData frameData) {
        this.mTextView.setText(frameData.toString());
        this.mDrawable.setRatio(frameData.iconRatio);
        this.mDrawable.setBounds(this.mBound);
        this.mTextView.setCompoundDrawables((Drawable) null, this.mDrawable, (Drawable) null, (Drawable) null);
    }
}
