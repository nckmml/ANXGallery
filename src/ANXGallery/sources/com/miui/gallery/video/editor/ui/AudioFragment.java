package com.miui.gallery.video.editor.ui;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.net.base.ErrorCode;
import com.miui.gallery.net.base.ResponseListener;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.util.ConvertFilepathUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.video.editor.LocalAudio;
import com.miui.gallery.video.editor.VideoEditor;
import com.miui.gallery.video.editor.adapter.AudioRecyclerViewAdapter;
import com.miui.gallery.video.editor.factory.AudioFactory;
import com.miui.gallery.video.editor.manager.AudioManager;
import com.miui.gallery.video.editor.model.LocalResource;
import com.miui.gallery.video.editor.model.VideoEditorBaseLocalResource;
import com.miui.gallery.video.editor.model.VideoEditorBaseModel;
import com.miui.gallery.video.editor.net.ResourceDownloadManager;
import com.miui.gallery.video.editor.net.VideoEditorResourceRequest;
import com.miui.gallery.video.editor.ui.AudioFragment;
import com.miui.gallery.video.editor.ui.menu.AudioView;
import com.miui.gallery.video.editor.widget.SingleChoiceRecyclerView;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import com.nexstreaming.nexeditorsdk.nexClip;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AudioFragment extends MenuFragment {
    /* access modifiers changed from: private */
    public AudioRecyclerViewAdapter mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<LocalAudio> mAudios = new ArrayList<>();
    private boolean mBgSoundEnable = true;
    private View mCancelView;
    private ScrollLinearLayoutManager mLinearLayoutManager;
    private View mOkView;
    /* access modifiers changed from: private */
    public ResourceDownloadManager mResourceDownloadManager;
    private int mSavedSelectedAudioIndex;
    /* access modifiers changed from: private */
    public SingleChoiceRecyclerView mSingleChoiceRecyclerView;
    private TextView mTitleView;
    private VideoEditorResourceRequest mVideoEditorResourceRequest;
    private boolean mVoiceClicked = false;

    private class MyAudioItemSelectChangeListener implements SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.ItemSelectChangeListener {
        private MyAudioItemSelectChangeListener() {
        }

        public boolean onItemSelect(SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter singleChoiceRecyclerViewAdapter, int i, boolean z) {
            if (!AudioFragment.this.mHasLoadData) {
                return false;
            }
            ScrollLinearLayoutManager.onItemClick(AudioFragment.this.mSingleChoiceRecyclerView, i);
            LocalAudio audio = ((AudioRecyclerViewAdapter) singleChoiceRecyclerViewAdapter).getAudio(i);
            if (audio != null) {
                if (audio.isCustom()) {
                    Intent intent = new Intent();
                    intent.setType("audio/*");
                    intent.setAction("android.intent.action.GET_CONTENT");
                    intent.setPackage("com.miui.player");
                    try {
                        AudioFragment.this.startActivityForResult(intent, 1000);
                    } catch (ActivityNotFoundException unused) {
                        Log.e("AudioFragment", "com.miui.player not found,try all picker");
                        try {
                            Intent intent2 = new Intent();
                            intent2.setType("audio/*");
                            intent2.setAction("android.intent.action.GET_CONTENT");
                            AudioFragment.this.startActivityForResult(intent2, 1000);
                        } catch (ActivityNotFoundException unused2) {
                            Log.e("AudioFragment", "picker not found");
                        }
                    }
                    AudioFragment.this.updateSelectedItemPosition(i);
                    return false;
                } else if (audio.isNone() && z) {
                    AudioFragment.this.updateSelectedItemPosition(i);
                    AudioFragment.this.successProcess(audio);
                } else if (audio.isExtra() && z) {
                    if (audio.isDownloaded()) {
                        AudioFragment.this.updateSelectedItemPosition(i);
                        AudioFragment.this.successProcess(audio);
                        return true;
                    }
                    AudioFragment.this.mResourceDownloadManager.createDownloadCommand(audio, i);
                }
            }
            return false;
        }
    }

    private void initListener() {
        this.mOkView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AudioFragment.this.lambda$initListener$129$AudioFragment(view);
            }
        });
        this.mCancelView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AudioFragment.this.lambda$initListener$130$AudioFragment(view);
            }
        });
        this.mResourceDownloadManager.setDownloadTaskListener(new ResourceDownloadManager.IDownloadTaskListener() {
            public void onCommandFail(VideoEditorBaseModel videoEditorBaseModel, int i) {
                if (videoEditorBaseModel != null) {
                    videoEditorBaseModel.setDownloadState(20);
                    AudioFragment.this.notifyDateSetChanged(i);
                }
            }

            public void onCommandStart(VideoEditorBaseModel videoEditorBaseModel, int i) {
                if (videoEditorBaseModel != null) {
                    videoEditorBaseModel.setDownloadState(18);
                    AudioFragment.this.notifyDateSetChanged(i);
                }
            }

            public void onCommandSuccess(VideoEditorBaseModel videoEditorBaseModel, int i) {
                if (videoEditorBaseModel != null) {
                    videoEditorBaseModel.setDownloadState(0);
                    AudioFragment.this.notifyDateSetChanged(i);
                }
            }

            public void onTaskCancel(VideoEditorBaseModel videoEditorBaseModel, int i) {
                int downloadState;
                if (videoEditorBaseModel != null && (downloadState = videoEditorBaseModel.getDownloadState()) != 0 && downloadState != 17) {
                    videoEditorBaseModel.setDownloadState(20);
                    AudioFragment.this.notifyDateSetChanged(i);
                }
            }
        });
    }

    private void initRecyclerView(View view) {
        this.mSingleChoiceRecyclerView = (SingleChoiceRecyclerView) view.findViewById(R.id.recycler_view);
        this.mLinearLayoutManager = new ScrollLinearLayoutManager(this.mContext, 0, false);
        this.mLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(this.mContext));
        this.mSingleChoiceRecyclerView.setLayoutManager(this.mLinearLayoutManager);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.editor_smart_effect_item_start);
        this.mSingleChoiceRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(dimensionPixelSize, dimensionPixelSize, getResources().getDimensionPixelSize(R.dimen.editor_smart_effect_item_horizontal_interval), 0, 0, 0));
        this.mAdapter = new AudioRecyclerViewAdapter(this.mContext, this.mAudios);
        this.mAdapter.setItemSelectChangeListener(new MyAudioItemSelectChangeListener());
        this.mSingleChoiceRecyclerView.setAdapter(this.mAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mSingleChoiceRecyclerView);
        this.mAdapter.setSelectedItemPosition(this.mSavedSelectedAudioIndex);
    }

    /* access modifiers changed from: private */
    public void refreshData(List<VideoEditorBaseLocalResource> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (VideoEditorBaseLocalResource next : list) {
            if (next != null) {
                if ("ve_type_none".equals(next.type)) {
                    arrayList.add(next);
                } else if ("ve_type_extra".equals(next.type)) {
                    arrayList2.add(next);
                } else if ("ve_type_custom".equals(next.type)) {
                    arrayList3.add(next);
                }
            }
        }
        List<LocalResource> localTemplateEntities = this.mModuleFactory.getLocalTemplateEntities(this.mContext);
        if (list != null) {
            localTemplateEntities.addAll(arrayList);
            localTemplateEntities.addAll(arrayList2);
            localTemplateEntities.addAll(arrayList3);
        }
        ArrayList arrayList4 = new ArrayList();
        arrayList4.addAll(AudioManager.loadAudioData(this.mModuleFactory, localTemplateEntities));
        ThreadManager.runOnMainThread(new Runnable(arrayList4) {
            private final /* synthetic */ ArrayList f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AudioFragment.this.lambda$refreshData$134$AudioFragment(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    public void successProcess(LocalAudio localAudio) {
        this.mVideoEditor.setBackgroundMusic(localAudio.getSrcPath());
        this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
            public void onCompleted() {
                AudioFragment.this.mVideoEditor.play();
                AudioFragment.this.recordEventWithEffectChanged();
                AudioFragment.this.updatePlayBtnView();
            }
        });
    }

    private void updateAudioVoiceView(boolean z) {
        if (this.mCallback != null) {
            this.mCallback.updateAudioVoiceView(z);
        }
    }

    /* access modifiers changed from: private */
    public void updateSelectedItemPosition(int i) {
        AudioRecyclerViewAdapter audioRecyclerViewAdapter = this.mAdapter;
        if (audioRecyclerViewAdapter != null) {
            audioRecyclerViewAdapter.setSelectedItemPosition(i);
            this.mAdapter.clearLastSelectedPostion();
        }
    }

    public boolean doApply() {
        if (this.mVideoEditor == null) {
            Log.d("AudioFragment", "doApply: videoEditor is null.");
            return false;
        }
        this.mSavedSelectedAudioIndex = this.mAdapter.getSelectedItemPosition();
        this.mVideoEditor.saveEditState();
        recordEventWithApply();
        onExitMode();
        return true;
    }

    public boolean doCancel() {
        if (this.mVideoEditor == null) {
            Log.d("AudioFragment", "doCancel: videoEditor is null.");
            return false;
        }
        if (this.mVoiceClicked) {
            this.mBgSoundEnable = !this.mBgSoundEnable;
        }
        this.mVideoEditor.restoreEditState();
        return this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
            public void onCompleted() {
                if (AudioFragment.this.mVideoEditor != null) {
                    AudioFragment.this.mVideoEditor.play();
                    AudioFragment.this.recordEventWithCancel();
                    AudioFragment.this.onExitMode();
                }
            }
        });
    }

    public List<String> getCurrentEffect() {
        LocalAudio audio;
        AudioRecyclerViewAdapter audioRecyclerViewAdapter = this.mAdapter;
        if (audioRecyclerViewAdapter == null || (audio = audioRecyclerViewAdapter.getAudio(audioRecyclerViewAdapter.getSelectedItemPosition())) == null) {
            return null;
        }
        String str = "source";
        if (audio.isCustom()) {
            String[] strArr = new String[2];
            strArr[0] = "custom";
            if (!this.mVideoEditor.isSourceAudioEnable()) {
                str = "mute";
            }
            strArr[1] = str;
            return Arrays.asList(strArr);
        } else if (!audio.isExtra()) {
            return null;
        } else {
            String[] strArr2 = new String[2];
            strArr2[0] = audio.getLabel();
            if (!this.mVideoEditor.isSourceAudioEnable()) {
                str = "mute";
            }
            strArr2[1] = str;
            return Arrays.asList(strArr2);
        }
    }

    public int getEffectId() {
        return R.id.video_editor_audio;
    }

    public /* synthetic */ void lambda$initListener$129$AudioFragment(View view) {
        doApply();
    }

    public /* synthetic */ void lambda$initListener$130$AudioFragment(View view) {
        doCancel();
    }

    public /* synthetic */ void lambda$refreshData$134$AudioFragment(ArrayList arrayList) {
        this.mAudios.clear();
        this.mAudios.addAll(arrayList);
        AudioRecyclerViewAdapter audioRecyclerViewAdapter = this.mAdapter;
        if (audioRecyclerViewAdapter != null) {
            audioRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    public /* synthetic */ void lambda$updateVoiceState$131$AudioFragment() {
        this.mVideoEditor.play();
        updatePlayBtnView();
        recordEventWithEffectChanged();
    }

    public void loadResourceData() {
        this.mHasLoadData = false;
        this.mVideoEditorResourceRequest = new VideoEditorResourceRequest(getEffectId(), this.mModuleFactory);
        this.mVideoEditorResourceRequest.execute(new ResponseListener() {
            public /* synthetic */ void lambda$null$132$AudioFragment$2() {
                ToastUtils.makeText(AudioFragment.this.mContext, (int) R.string.video_editor_download_failed_for_notwork);
            }

            public /* synthetic */ void lambda$onResponseError$133$AudioFragment$2(ErrorCode errorCode) {
                if (AudioFragment.this.mAudios != null && AudioFragment.this.mAudios.size() == 0) {
                    AudioFragment.this.mAudios.add(new LocalAudio("audio_none", R.drawable.video_editor_icon_audio_none, "ve_type_none", false));
                    if (AudioFragment.this.mAdapter != null) {
                        AudioFragment.this.mAdapter.notifyDataSetChanged();
                    }
                }
                if (errorCode == ErrorCode.NETWORK_NOT_CONNECTED) {
                    ThreadManager.runOnMainThread(new Runnable() {
                        public final void run() {
                            AudioFragment.AnonymousClass2.this.lambda$null$132$AudioFragment$2();
                        }
                    });
                }
            }

            public void onResponse(Object... objArr) {
                AudioFragment audioFragment = AudioFragment.this;
                audioFragment.mHasLoadData = true;
                audioFragment.refreshData(objArr[0]);
            }

            public void onResponseError(ErrorCode errorCode, String str, Object obj) {
                Log.d("AudioFragment", "errorCode: %s", (Object) errorCode);
                AudioFragment.this.mHasLoadData = true;
                ThreadManager.runOnMainThread(new Runnable(errorCode) {
                    private final /* synthetic */ ErrorCode f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        AudioFragment.AnonymousClass2.this.lambda$onResponseError$133$AudioFragment$2(this.f$1);
                    }
                });
            }
        });
    }

    public void notifyDateSetChanged(int i) {
        AudioRecyclerViewAdapter audioRecyclerViewAdapter = this.mAdapter;
        if (audioRecyclerViewAdapter != null) {
            audioRecyclerViewAdapter.notifyItemChanged(i, 1);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        String str;
        super.onActivityResult(i, i2, intent);
        if (i == 1000 && i2 == -1) {
            ClipData clipData = intent.getClipData();
            if (clipData == null) {
                str = ConvertFilepathUtil.getPath(getActivity(), intent.getData());
            } else {
                str = ConvertFilepathUtil.getPath(getActivity(), clipData.getItemAt(0).getUri());
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (nexClip.getSupportedClip(str) != null) {
                this.mVideoEditor.setBackgroundMusic(ImageDownloader.Scheme.FILE.wrap(str));
                this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                    public void onCompleted() {
                        AudioFragment.this.mVideoEditor.play();
                        AudioFragment.this.recordEventWithEffectChanged();
                        AudioFragment.this.updatePlayBtnView();
                        if (AudioFragment.this.mAdapter != null) {
                            AudioFragment audioFragment = AudioFragment.this;
                            audioFragment.updateSelectedItemPosition(audioFragment.mAdapter.getItemCount() - 1);
                        }
                    }
                });
                return;
            }
            ToastUtils.makeText((Context) getActivity(), (int) R.string.video_editor_unsupport_audio_file);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAudios = AudioManager.initDataWithBgColor();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AudioView audioView = new AudioView(viewGroup.getContext());
        initRecyclerView(audioView);
        this.mCancelView = audioView.findViewById(R.id.cancel);
        this.mOkView = audioView.findViewById(R.id.ok);
        this.mTitleView = (TextView) audioView.findViewById(R.id.title);
        this.mTitleView.setText(this.mContext.getResources().getString(R.string.video_editor_audio));
        this.mModuleFactory = new AudioFactory();
        this.mResourceDownloadManager = new ResourceDownloadManager(viewGroup.getContext(), getFragmentManager(), this.mModuleFactory);
        updateAudioVoiceView(this.mBgSoundEnable);
        initListener();
        loadResourceData();
        return audioView;
    }

    public void onDestroyView() {
        super.onDestroyView();
        updateAudioVoiceView(false);
        AudioRecyclerViewAdapter audioRecyclerViewAdapter = this.mAdapter;
        if (audioRecyclerViewAdapter != null) {
            audioRecyclerViewAdapter.setItemSelectChangeListener((SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.ItemSelectChangeListener) null);
            this.mAdapter = null;
        }
        VideoEditorResourceRequest videoEditorResourceRequest = this.mVideoEditorResourceRequest;
        if (videoEditorResourceRequest != null) {
            videoEditorResourceRequest.cancel();
        }
        this.mVoiceClicked = false;
        ResourceDownloadManager resourceDownloadManager = this.mResourceDownloadManager;
        if (resourceDownloadManager != null) {
            resourceDownloadManager.cancel();
            this.mResourceDownloadManager = null;
        }
    }

    public void updateVoiceState(boolean z) {
        this.mBgSoundEnable = z;
        this.mVideoEditor.setSourceAudioEnable(Boolean.valueOf(z));
        this.mVoiceClicked = true;
        this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
            public final void onCompleted() {
                AudioFragment.this.lambda$updateVoiceState$131$AudioFragment();
            }
        });
    }
}
