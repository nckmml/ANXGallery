package com.miui.gallery.video.editor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.video.editor.TextStyle;
import com.miui.gallery.video.editor.widget.SingleChoiceRecyclerView;
import java.util.List;

public class WatermarkRecyclerViewAdapter extends SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter<WaterMarkHolder> {
    private LayoutInflater mLayoutInflater;
    private List<TextStyle> mTextStyles;

    public WatermarkRecyclerViewAdapter(Context context, List<TextStyle> list) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mTextStyles = list;
    }

    public int getItemCount() {
        List<TextStyle> list = this.mTextStyles;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public TextStyle getTextStyle(int i) {
        if (MiscUtil.isValid(this.mTextStyles) && i >= 0 && i < this.mTextStyles.size()) {
            return this.mTextStyles.get(i);
        }
        return null;
    }

    public void onBindView(WaterMarkHolder waterMarkHolder, int i) {
        TextStyle textStyle = this.mTextStyles.get(i);
        boolean z = true;
        waterMarkHolder.updateSelected(getSelectedItemPosition() == i, textStyle.isDownloaded());
        waterMarkHolder.setIcon(textStyle.getIconUrl(), textStyle.getIconResId(), textStyle.getBgColor());
        if (!textStyle.isLocal() || getSelectedItemPosition() != i) {
            z = false;
        }
        waterMarkHolder.updateTextEditable(z);
        waterMarkHolder.setStateImage(textStyle.getDownloadState());
    }

    public void onBindViewHolder(WaterMarkHolder waterMarkHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            onBindViewHolder(waterMarkHolder, i);
            return;
        }
        TextStyle textStyle = this.mTextStyles.get(i);
        boolean z = true;
        waterMarkHolder.updateTextEditable(textStyle.isLocal() && getSelectedItemPosition() == i);
        if (getSelectedItemPosition() != i) {
            z = false;
        }
        waterMarkHolder.updateSelected(z, textStyle.isDownloaded());
        waterMarkHolder.setStateImage(textStyle.getDownloadState());
        if (textStyle.isDownloadSuccess()) {
            textStyle.setDownloadState(17);
        }
    }

    public WaterMarkHolder onCreateSingleChoiceViewHolder(ViewGroup viewGroup, int i) {
        return new WaterMarkHolder(this.mLayoutInflater.inflate(R.layout.video_editor_text_download_item, viewGroup, false));
    }
}
