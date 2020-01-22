package com.miui.gallery.video.editor.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.miui.gallery.R;
import com.miui.gallery.video.editor.ImageLoadingProcess;
import com.miui.gallery.video.editor.util.ImageLoaderUtils;
import com.miui.gallery.video.editor.util.ToolsUtil;
import com.miui.gallery.video.editor.widget.DownloadView;
import com.miui.gallery.video.editor.widget.SingleChoiceRecyclerView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class WaterMarkHolder extends SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.SingleChoiceViewHolder {
    private static DisplayImageOptions mDisplayImageOptions = ImageLoaderUtils.mVideoEditorDisplayImageOptions;
    private DownloadView mDownloadView;
    private ImageView mIcon;
    private ImageAware mImageAware;
    private ImageLoadingProcess mImageLoadingProcess;
    private View mSelectBackground;
    private View mSelected;
    private ImageView mTextEditable;

    public WaterMarkHolder(View view) {
        super(view);
        this.mIcon = (ImageView) view.findViewById(R.id.video_editor_text_image);
        this.mDownloadView = (DownloadView) view.findViewById(R.id.item_download);
        this.mSelected = view.findViewById(R.id.selector);
        this.mTextEditable = (ImageView) view.findViewById(R.id.video_editor_edit);
        this.mSelectBackground = view.findViewById(R.id.select_edit_background);
        this.mDownloadView.setBackground(ToolsUtil.isRTLDirection() ? view.getResources().getDrawable(R.drawable.video_editor_icon_watermark_download_start) : view.getResources().getDrawable(R.drawable.video_editor_icon_watermark_download));
        this.mImageAware = new ImageViewAware(this.mIcon);
        this.mImageLoadingProcess = new ImageLoadingProcess(this.mIcon);
    }

    public void setIcon(String str, int i, int i2) {
        if (i != 0) {
            this.mIcon.setImageResource(i);
        } else if (!TextUtils.isEmpty(str)) {
            this.mImageLoadingProcess.setBgColor(i2);
            ImageLoader.getInstance().displayImage(str, this.mImageAware, mDisplayImageOptions, (ImageLoadingListener) this.mImageLoadingProcess);
        } else if (i2 != 0) {
            this.mIcon.setImageResource(i2);
        }
    }

    public void setSelect(boolean z) {
    }

    public void setStateImage(int i) {
        this.mDownloadView.setStateImage(i);
    }

    public void updateSelected(boolean z, boolean z2) {
        if (!z || !z2) {
            ToolsUtil.hideView(this.mSelected);
        } else {
            ToolsUtil.showView(this.mSelected);
        }
    }

    public void updateTextEditable(boolean z) {
        if (z) {
            ToolsUtil.showView(this.mTextEditable);
            ToolsUtil.showView(this.mSelectBackground);
            return;
        }
        ToolsUtil.hideView(this.mTextEditable);
        ToolsUtil.hideView(this.mSelectBackground);
    }
}
