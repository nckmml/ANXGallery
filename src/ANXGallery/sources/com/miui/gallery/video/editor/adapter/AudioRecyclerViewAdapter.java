package com.miui.gallery.video.editor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.video.editor.LocalAudio;
import com.miui.gallery.video.editor.widget.SingleChoiceRecyclerView;
import java.util.List;

public class AudioRecyclerViewAdapter extends SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter<AudioViewHolder> {
    private final int VIEW_TYPE_NORMAL = 0;
    private List<LocalAudio> mAudioList;
    private int mFirstMarginLeft = 0;
    private LayoutInflater mLayoutInflater;

    public AudioRecyclerViewAdapter(Context context, List<LocalAudio> list) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mAudioList = list;
        this.mFirstMarginLeft = context.getResources().getDimensionPixelSize(R.dimen.video_editor_list_item_margin_left);
    }

    public LocalAudio getAudio(int i) {
        List<LocalAudio> list = this.mAudioList;
        if (list == null || i >= list.size()) {
            return null;
        }
        return this.mAudioList.get(i);
    }

    public int getItemCount() {
        List<LocalAudio> list = this.mAudioList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public void onBindView(AudioViewHolder audioViewHolder, int i) {
        boolean z = false;
        audioViewHolder.setMarginLeft(i == 0 ? this.mFirstMarginLeft : 0);
        LocalAudio localAudio = this.mAudioList.get(i);
        audioViewHolder.setName(localAudio.getNameResId());
        audioViewHolder.setIcon(localAudio.getIconUrl(), localAudio.getIconResId(), localAudio.getBgColor());
        if (getSelectedItemPosition() == i) {
            z = true;
        }
        audioViewHolder.updateSelected(z, localAudio.isDownloaded());
        audioViewHolder.setStateImage(localAudio.getDownloadState());
    }

    public void onBindViewHolder(AudioViewHolder audioViewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            onBindViewHolder(audioViewHolder, i);
            return;
        }
        LocalAudio localAudio = this.mAudioList.get(i);
        audioViewHolder.setStateImage(localAudio.getDownloadState());
        audioViewHolder.updateSelected(getSelectedItemPosition() == i, localAudio.isDownloaded());
        if (localAudio.isDownloadSuccess()) {
            localAudio.setDownloadState(17);
        }
    }

    public AudioViewHolder onCreateSingleChoiceViewHolder(ViewGroup viewGroup, int i) {
        return new AudioViewHolder(this.mLayoutInflater.inflate(R.layout.video_editor_smart_effect_download_item, viewGroup, false));
    }
}
