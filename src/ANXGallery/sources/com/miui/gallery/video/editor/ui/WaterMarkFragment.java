package com.miui.gallery.video.editor.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.miui.gallery.R;
import com.miui.gallery.net.base.ErrorCode;
import com.miui.gallery.net.base.ResponseListener;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.video.editor.TextStyle;
import com.miui.gallery.video.editor.VideoEditor;
import com.miui.gallery.video.editor.adapter.WatermarkRecyclerViewAdapter;
import com.miui.gallery.video.editor.factory.WaterMartFactory;
import com.miui.gallery.video.editor.manager.NexAssetTemplateManager;
import com.miui.gallery.video.editor.manager.WaterMarkManager;
import com.miui.gallery.video.editor.model.LocalResource;
import com.miui.gallery.video.editor.model.VideoEditorBaseLocalResource;
import com.miui.gallery.video.editor.model.VideoEditorBaseModel;
import com.miui.gallery.video.editor.net.ResourceDownloadManager;
import com.miui.gallery.video.editor.net.VideoEditorResourceRequest;
import com.miui.gallery.video.editor.ui.WaterMarkFragment;
import com.miui.gallery.video.editor.ui.menu.WaterMarkView;
import com.miui.gallery.video.editor.util.ToolsUtil;
import com.miui.gallery.video.editor.widget.SingleChoiceRecyclerView;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import miui.view.animation.CubicEaseOutInterpolator;

public class WaterMarkFragment extends MenuFragment implements View.OnClickListener {
    private final int AUTO_TEXT_WATER_MARK_INDEX = 1;
    /* access modifiers changed from: private */
    public WatermarkRecyclerViewAdapter mAdapter;
    private TextView mAllActionButton;
    /* access modifiers changed from: private */
    public EditText mAutoText;
    /* access modifiers changed from: private */
    public int mCurrentSelectIndex;
    private TextView mEndActionButton;
    /* access modifiers changed from: private */
    public String mInputText = "";
    /* access modifiers changed from: private */
    public boolean mIsKeyboardShowing = false;
    private ImageView mIvCancel;
    private ImageView mIvOk;
    /* access modifiers changed from: private */
    public int mLastSelectIndex;
    private TextStyle mLastSelectedTextStyle;
    private ScrollLinearLayoutManager mLinearLayoutManager;
    /* access modifiers changed from: private */
    public LoadWaterMarkTemplateTask mLoadWaterMarkTemplateTask;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            Log.d("WaterMarkFragment", "---onGlobalLayout start---");
            View rootView = WaterMarkFragment.this.mTextRoot.getRootView();
            int exactScreenHeight = ScreenUtils.getExactScreenHeight(WaterMarkFragment.this.getActivity());
            int height = rootView.getHeight();
            int[] iArr = new int[2];
            rootView.getLocationOnScreen(iArr);
            int i = (exactScreenHeight - height) - iArr[1];
            if (!WaterMarkFragment.this.mIsKeyboardShowing && i > 0) {
                Log.d("WaterMarkFragment", "soft keyboard start to show!");
                boolean unused = WaterMarkFragment.this.mIsKeyboardShowing = true;
                WaterMarkFragment.this.mTextAppearAnimator.start();
            }
            if (i == 0 && WaterMarkFragment.this.mIsKeyboardShowing) {
                Log.d("WaterMarkFragment", "soft keyboard start to dismiss!");
                WaterMarkFragment.this.dismissTextPopWindow();
                WaterMarkFragment waterMarkFragment = WaterMarkFragment.this;
                boolean unused2 = waterMarkFragment.mIsKeyboardShowing = true ^ waterMarkFragment.applyPlay();
            }
        }
    };
    private final long mPopWindowEnterAnimTime = 280;
    /* access modifiers changed from: private */
    public ResourceDownloadManager mResourceDownloadManager;
    /* access modifiers changed from: private */
    public int mSavedSelectedWaterMarkIndex;
    private ConstraintLayout mSelectTimeLayout;
    private TextStyle mSelectedTextStyle;
    /* access modifiers changed from: private */
    public int mSelectedTextTime = 0;
    /* access modifiers changed from: private */
    public SingleChoiceRecyclerView mSingleChoiceRecyclerView;
    private TextView mStartActionButton;
    /* access modifiers changed from: private */
    public ObjectAnimator mTextAppearAnimator;
    private ImageView mTextCancel;
    /* access modifiers changed from: private */
    public boolean mTextEditable = false;
    private PopupWindow mTextPopWindow;
    /* access modifiers changed from: private */
    public View mTextRoot;
    private ImageView mTextSave;
    /* access modifiers changed from: private */
    public ArrayList<TextStyle> mTextStyles = new ArrayList<>();
    private ConstraintLayout mTitleLayout;
    private VideoEditorResourceRequest mVideoEditorResourceRequest;
    /* access modifiers changed from: private */
    public WaterMarkManager mWaterMarkManager;

    public static class LoadWaterMarkTemplateTask {
        /* access modifiers changed from: private */
        public Callback mCallback;
        private Future<String[]> mFuture;

        interface Callback {
            void onLoadTemplateSuccess(String[] strArr);
        }

        /* access modifiers changed from: private */
        public void execute(Callback callback) {
            Future<String[]> future = this.mFuture;
            if (future != null) {
                future.cancel();
            }
            this.mCallback = callback;
            this.mFuture = ThreadManager.getMiscPool().submit($$Lambda$WaterMarkFragment$LoadWaterMarkTemplateTask$FZDiGkDMqXFRWuqeL7ePAOKazo.INSTANCE, new FutureHandler<String[]>() {
                public void onPostExecute(Future<String[]> future) {
                    if (!future.isCancelled() && LoadWaterMarkTemplateTask.this.mCallback != null) {
                        LoadWaterMarkTemplateTask.this.mCallback.onLoadTemplateSuccess(future.get());
                    }
                }
            });
        }

        static /* synthetic */ String[] lambda$execute$139(ThreadPool.JobContext jobContext) {
            String[][] strArr = {null};
            NexAssetTemplateManager.getInstance().loadWaterMarkTemplateList(new NexAssetTemplateManager.ILoadWaterMarkListener(strArr) {
                private final /* synthetic */ String[][] f$0;

                {
                    this.f$0 = r1;
                }

                public final void onFinished(String[] strArr) {
                    WaterMarkFragment.LoadWaterMarkTemplateTask.lambda$null$138(this.f$0, strArr);
                }
            });
            return strArr[0];
        }

        static /* synthetic */ void lambda$null$138(String[][] strArr, String[] strArr2) {
            strArr[0] = strArr2;
        }

        public void cancel() {
            Future<String[]> future = this.mFuture;
            if (future != null) {
                future.cancel();
                this.mFuture = null;
            }
            this.mCallback = null;
        }
    }

    private class MyTextItemSelectChangeListener implements SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.ItemSelectChangeListener {
        private MyTextItemSelectChangeListener() {
        }

        public boolean onItemSelect(SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter singleChoiceRecyclerViewAdapter, final int i, boolean z) {
            if (!WaterMarkFragment.this.mHasLoadData) {
                return false;
            }
            ScrollLinearLayoutManager.onItemClick(WaterMarkFragment.this.mSingleChoiceRecyclerView, i);
            final TextStyle textStyle = ((WatermarkRecyclerViewAdapter) singleChoiceRecyclerViewAdapter).getTextStyle(i);
            if (textStyle != null && (z || textStyle.isLocal())) {
                if (textStyle.isNone()) {
                    WaterMarkFragment.this.updateBottomBtnTitle(i);
                    boolean unused = WaterMarkFragment.this.mTextEditable = false;
                    WaterMarkFragment.this.mVideoEditor.setAutoWaterMark("", WaterMarkFragment.this.mSelectedTextTime);
                    WaterMarkFragment.this.mVideoEditor.setWarterMark(WaterMarkFragment.this.mSelectedTextTime, "");
                    return WaterMarkFragment.this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                        public void onCompleted() {
                            WaterMarkFragment.this.mVideoEditor.play();
                            WaterMarkFragment.this.updateSelectTextStyleStatus(textStyle, i);
                            WaterMarkFragment.this.updateSelectedItemPosition(i);
                            WaterMarkFragment.this.recordEventWithEffectChanged();
                            WaterMarkFragment.this.updatePlayBtnView();
                        }
                    });
                } else if (textStyle.isExtra()) {
                    boolean unused2 = WaterMarkFragment.this.mTextEditable = false;
                    if (textStyle.isDownloaded()) {
                        WaterMarkFragment.this.updateBottomBtnTitle(i);
                        WaterMarkFragment.this.updateBottomBtnState();
                        WaterMarkFragment.this.mVideoEditor.setAutoWaterMark("", WaterMarkFragment.this.mSelectedTextTime);
                        WaterMarkFragment.this.mVideoEditor.setWarterMark(WaterMarkFragment.this.mSelectedTextTime, textStyle.getTemplateId());
                        return WaterMarkFragment.this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                            public void onCompleted() {
                                WaterMarkFragment.this.mVideoEditor.play();
                                WaterMarkFragment.this.updateSelectTextStyleStatus(textStyle, i);
                                WaterMarkFragment.this.updateSelectedItemPosition(i);
                                WaterMarkFragment.this.recordEventWithEffectChanged();
                                WaterMarkFragment.this.updatePlayBtnView();
                            }
                        });
                    }
                    WaterMarkFragment.this.mResourceDownloadManager.createDownloadCommand(textStyle, i);
                } else if (textStyle.isLocal()) {
                    WaterMarkFragment.this.updateSelectTextStyleStatus(textStyle, i);
                    if (TextUtils.isEmpty(WaterMarkFragment.this.mInputText)) {
                        WaterMarkFragment.this.showEditPopWindow();
                        WaterMarkFragment.this.showSoftInput();
                        WaterMarkFragment.this.mVideoEditor.pause();
                    } else if (!WaterMarkFragment.this.mTextEditable) {
                        WaterMarkFragment.this.updateEffectInfo();
                        boolean unused3 = WaterMarkFragment.this.applyPlay();
                    } else {
                        WaterMarkFragment.this.showEditPopWindow();
                        WaterMarkFragment.this.showSoftInput();
                        WaterMarkFragment.this.mVideoEditor.pause();
                    }
                    WaterMarkFragment.this.updateBottomBtnState();
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public boolean applyPlay() {
        return this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
            public void onCompleted() {
                WaterMarkFragment.this.mVideoEditor.play();
                WaterMarkFragment.this.recordEventWithEffectChanged();
                WaterMarkFragment.this.updatePlayBtnView();
            }
        });
    }

    /* access modifiers changed from: private */
    public void dismissTextPopWindow() {
        this.mTextPopWindow.dismiss();
    }

    private void hideSoftInput() {
        EditText editText = this.mAutoText;
        if (editText != null) {
            Context context = editText.getContext();
            Context context2 = this.mContext;
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(this.mAutoText.getWindowToken(), 0);
            }
        }
    }

    private void initEditPopWindow() {
        this.mTextRoot = LayoutInflater.from(getActivity()).inflate(R.layout.video_editor_fragment_text_pop_window, (ViewGroup) null);
        this.mAutoText = (EditText) this.mTextRoot.findViewById(R.id.et_pop_window);
        this.mTextCancel = (ImageView) this.mTextRoot.findViewById(R.id.video_editor_btn_cancel);
        this.mTextSave = (ImageView) this.mTextRoot.findViewById(R.id.video_editor_btn_ok);
        this.mTextPopWindow = new PopupWindow(this.mTextRoot, -1, -1, true);
        this.mTextPopWindow.setFocusable(true);
        this.mTextPopWindow.setOutsideTouchable(true);
        this.mTextPopWindow.setBackgroundDrawable(new BitmapDrawable());
        this.mTextPopWindow.setSoftInputMode(16);
        this.mTextCancel.setOnClickListener(this);
        this.mTextSave.setOnClickListener(this);
    }

    private void initEnterAnim() {
        if (this.mTextRoot != null) {
            this.mTextAppearAnimator = new ObjectAnimator();
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.video_editor_water_mark_in_distance);
            PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{(float) dimensionPixelSize, 0.0f});
            PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{0.0f, 1.0f});
            this.mTextAppearAnimator.setValues(new PropertyValuesHolder[]{ofFloat, ofFloat2});
            this.mTextAppearAnimator.setInterpolator(new CubicEaseOutInterpolator());
            this.mTextAppearAnimator.setDuration(280);
            this.mTextAppearAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    Log.d("WaterMarkFragment", "water mark popwinow appear animation cancel!");
                }

                public void onAnimationEnd(Animator animator) {
                    if (!TextUtils.isEmpty(WaterMarkFragment.this.mInputText)) {
                        WaterMarkFragment.this.mAutoText.setText(WaterMarkFragment.this.mInputText);
                        WaterMarkFragment.this.mAutoText.setSelection(WaterMarkFragment.this.mInputText.length());
                    }
                    Log.d("WaterMarkFragment", "water mark popwinow appear animation end!");
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    Log.d("WaterMarkFragment", "water mark popwinow appear animation start!");
                }
            });
            this.mTextAppearAnimator.setTarget(this.mTextRoot);
        }
    }

    private void initListener() {
        this.mStartActionButton.setOnClickListener(this);
        this.mEndActionButton.setOnClickListener(this);
        this.mAllActionButton.setOnClickListener(this);
        this.mIvCancel.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WaterMarkFragment.this.lambda$initListener$135$WaterMarkFragment(view);
            }
        });
        this.mIvOk.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WaterMarkFragment.this.lambda$initListener$136$WaterMarkFragment(view);
            }
        });
        this.mResourceDownloadManager.setDownloadTaskListener(new ResourceDownloadManager.IDownloadTaskListener() {
            public /* synthetic */ void lambda$onCommandSuccess$137$WaterMarkFragment$1(VideoEditorBaseModel videoEditorBaseModel, int i, String[] strArr) {
                WaterMarkManager.updateDataWithTemplate(strArr, (TextStyle) videoEditorBaseModel);
                WaterMarkFragment.this.notifyDateSetChanged(i);
            }

            public void onCommandFail(VideoEditorBaseModel videoEditorBaseModel, int i) {
                if (videoEditorBaseModel != null) {
                    videoEditorBaseModel.setDownloadState(20);
                    WaterMarkFragment.this.notifyDateSetChanged(i);
                }
            }

            public void onCommandStart(VideoEditorBaseModel videoEditorBaseModel, int i) {
                if (videoEditorBaseModel != null) {
                    videoEditorBaseModel.setDownloadState(18);
                    WaterMarkFragment.this.notifyDateSetChanged(i);
                }
            }

            public void onCommandSuccess(VideoEditorBaseModel videoEditorBaseModel, int i) {
                WaterMarkFragment.this.mLoadWaterMarkTemplateTask.execute(new LoadWaterMarkTemplateTask.Callback(videoEditorBaseModel, i) {
                    private final /* synthetic */ VideoEditorBaseModel f$1;
                    private final /* synthetic */ int f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onLoadTemplateSuccess(String[] strArr) {
                        WaterMarkFragment.AnonymousClass1.this.lambda$onCommandSuccess$137$WaterMarkFragment$1(this.f$1, this.f$2, strArr);
                    }
                });
            }

            public void onTaskCancel(VideoEditorBaseModel videoEditorBaseModel, int i) {
                int downloadState;
                if (videoEditorBaseModel != null && (downloadState = videoEditorBaseModel.getDownloadState()) != 0 && downloadState != 17) {
                    videoEditorBaseModel.setDownloadState(20);
                    WaterMarkFragment.this.notifyDateSetChanged(i);
                }
            }
        });
    }

    private void initRecyclerView(View view) {
        this.mSingleChoiceRecyclerView = (SingleChoiceRecyclerView) view.findViewById(R.id.recycler_view);
        this.mLinearLayoutManager = new ScrollLinearLayoutManager(this.mContext, 0, false);
        this.mLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(this.mContext));
        this.mSingleChoiceRecyclerView.setLayoutManager(this.mLinearLayoutManager);
        this.mAdapter = new WatermarkRecyclerViewAdapter(this.mContext, this.mTextStyles);
        this.mAdapter.setItemSelectChangeListener(new MyTextItemSelectChangeListener());
        this.mSingleChoiceRecyclerView.setAdapter(this.mAdapter);
        this.mSingleChoiceRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(getResources(), R.dimen.editor_water_mark_item_gap, 0));
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mSingleChoiceRecyclerView);
        this.mAdapter.setSelectedItemPosition(this.mSavedSelectedWaterMarkIndex);
        updateBottomBtnTitle(this.mSavedSelectedWaterMarkIndex);
    }

    /* access modifiers changed from: private */
    public void refreshData(List<VideoEditorBaseLocalResource> list) {
        List<LocalResource> localTemplateEntities = this.mModuleFactory.getLocalTemplateEntities(this.mContext);
        if (list != null) {
            localTemplateEntities.addAll(list);
        }
        if (localTemplateEntities.size() > 0) {
            localTemplateEntities.add(1, new LocalResource(R.drawable.video_editor_water_mark_text, "ve_type_local"));
        }
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(WaterMarkManager.loadWaterMarks(localTemplateEntities));
        NexAssetTemplateManager.getInstance().loadWaterMarkTemplateList(new NexAssetTemplateManager.ILoadWaterMarkListener() {
            public void onFinished(String[] strArr) {
                WaterMarkFragment.this.mWaterMarkManager.initDataWithTemplate(strArr, arrayList);
                ThreadManager.runOnMainThread(new Runnable() {
                    public void run() {
                        WaterMarkFragment.this.mTextStyles.clear();
                        WaterMarkFragment.this.mTextStyles.addAll(arrayList);
                        if (WaterMarkFragment.this.mAdapter != null) {
                            WaterMarkFragment.this.mAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void showEditPopWindow() {
        this.mTextRoot.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        this.mTextPopWindow.showAtLocation(this.mSingleChoiceRecyclerView, 51, 0, 0);
        this.mTextRoot.setAlpha(0.0f);
    }

    /* access modifiers changed from: private */
    public void showSoftInput() {
        EditText editText = this.mAutoText;
        if (editText != null) {
            editText.requestFocus();
            this.mAutoText.requestFocusFromTouch();
            this.mAutoText.post(new Runnable() {
                public void run() {
                    Context context = WaterMarkFragment.this.mAutoText.getContext();
                    WaterMarkFragment.this.getActivity();
                    InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
                    if (inputMethodManager != null) {
                        inputMethodManager.showSoftInput(WaterMarkFragment.this.mAutoText, 0);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateBottomBtnState() {
        int i = this.mSelectedTextTime;
        if (i == 0) {
            this.mStartActionButton.setSelected(true);
            this.mEndActionButton.setSelected(false);
            this.mAllActionButton.setSelected(false);
        } else if (i == 1) {
            this.mStartActionButton.setSelected(false);
            this.mEndActionButton.setSelected(true);
            this.mAllActionButton.setSelected(false);
        } else if (i == 2) {
            this.mStartActionButton.setSelected(false);
            this.mEndActionButton.setSelected(false);
            this.mAllActionButton.setSelected(true);
        }
    }

    /* access modifiers changed from: private */
    public void updateBottomBtnTitle(int i) {
        int i2 = 0;
        if (i == 0 && this.mAllActionButton.isSelected()) {
            this.mAllActionButton.setSelected(false);
        }
        this.mSelectTimeLayout.setVisibility(i == 0 ? 8 : 0);
        ConstraintLayout constraintLayout = this.mTitleLayout;
        if (i != 0) {
            i2 = 8;
        }
        constraintLayout.setVisibility(i2);
    }

    /* access modifiers changed from: private */
    public void updateEffectInfo() {
        TextStyle textStyle = this.mSelectedTextStyle;
        if (textStyle == null) {
            return;
        }
        if (textStyle.isLocal()) {
            if (TextUtils.isEmpty(this.mInputText)) {
                updateWithInputNoText();
                return;
            }
            this.mVideoEditor.setWarterMark(this.mSelectedTextTime, "");
            this.mVideoEditor.setAutoWaterMark(this.mInputText, this.mSelectedTextTime);
            updateSelectedItemPosition(this.mCurrentSelectIndex);
            updateBottomBtnTitle(this.mCurrentSelectIndex);
            this.mTextEditable = true;
        } else if (this.mSelectedTextStyle.isNone()) {
            this.mVideoEditor.setAutoWaterMark("", this.mSelectedTextTime);
            this.mVideoEditor.setWarterMark(this.mSelectedTextTime, "");
        } else if (this.mSelectedTextStyle.isExtra() && this.mSelectedTextStyle.isDownloaded()) {
            this.mVideoEditor.setWarterMark(this.mSelectedTextTime, this.mSelectedTextStyle.getTemplateId());
        }
    }

    /* access modifiers changed from: private */
    public void updateSelectTextStyleStatus(TextStyle textStyle, int i) {
        if (textStyle != null && i >= 0) {
            this.mLastSelectedTextStyle = this.mSelectedTextStyle;
            this.mSelectedTextStyle = textStyle;
            this.mLastSelectIndex = this.mCurrentSelectIndex;
            this.mCurrentSelectIndex = i;
        }
    }

    /* access modifiers changed from: private */
    public void updateSelectedItemPosition(int i) {
        WatermarkRecyclerViewAdapter watermarkRecyclerViewAdapter = this.mAdapter;
        if (watermarkRecyclerViewAdapter != null) {
            watermarkRecyclerViewAdapter.setSelectedItemPosition(i);
            this.mAdapter.clearLastSelectedPostion();
        }
    }

    private void updateWithInputNoText() {
        WatermarkRecyclerViewAdapter watermarkRecyclerViewAdapter = this.mAdapter;
        if (watermarkRecyclerViewAdapter != null) {
            int selectedItemPosition = watermarkRecyclerViewAdapter.getSelectedItemPosition();
            TextStyle textStyle = this.mLastSelectedTextStyle;
            if (textStyle == null || textStyle.isLocal()) {
                updateSelectedItemPosition(selectedItemPosition);
                updateBottomBtnTitle(selectedItemPosition);
                this.mVideoEditor.setAutoWaterMark("", this.mSelectedTextTime);
                return;
            }
            this.mSelectedTextStyle = this.mLastSelectedTextStyle;
            updateSelectedItemPosition(this.mCurrentSelectIndex);
            updateBottomBtnTitle(this.mCurrentSelectIndex);
            this.mVideoEditor.setAutoWaterMark("", this.mSelectedTextTime);
            this.mVideoEditor.setWarterMark(this.mSelectedTextTime, this.mSelectedTextStyle.getTemplateId());
        }
    }

    public boolean doApply() {
        if (this.mVideoEditor != null) {
            return this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                public void onCompleted() {
                    if (WaterMarkFragment.this.mVideoEditor != null) {
                        WaterMarkFragment waterMarkFragment = WaterMarkFragment.this;
                        int unused = waterMarkFragment.mSavedSelectedWaterMarkIndex = waterMarkFragment.mAdapter.getSelectedItemPosition();
                        WaterMarkFragment.this.mVideoEditor.saveEditState();
                        WaterMarkFragment.this.recordEventWithApply();
                        WaterMarkFragment.this.onExitMode();
                        WaterMarkFragment.this.mVideoEditor.play();
                    }
                }
            });
        }
        Log.d("WaterMarkFragment", "doApply: videoEditor is null.");
        return false;
    }

    public boolean doCancel() {
        if (this.mVideoEditor == null) {
            Log.d("WaterMarkFragment", "doCancel: videoEditor is null.");
            return false;
        }
        this.mVideoEditor.restoreEditState();
        return this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
            public void onCompleted() {
                if (WaterMarkFragment.this.mVideoEditor != null) {
                    WaterMarkFragment.this.mVideoEditor.play();
                    WaterMarkFragment.this.recordEventWithCancel();
                    WaterMarkFragment.this.onExitMode();
                }
            }
        });
    }

    public List<String> getCurrentEffect() {
        TextStyle textStyle;
        WatermarkRecyclerViewAdapter watermarkRecyclerViewAdapter = this.mAdapter;
        if (watermarkRecyclerViewAdapter == null || (textStyle = watermarkRecyclerViewAdapter.getTextStyle(watermarkRecyclerViewAdapter.getSelectedItemPosition())) == null) {
            return null;
        }
        return Arrays.asList(new String[]{textStyle.getLabel()});
    }

    public int getEffectId() {
        return R.id.video_editor_water_mark;
    }

    public /* synthetic */ void lambda$initListener$135$WaterMarkFragment(View view) {
        doCancel();
    }

    public /* synthetic */ void lambda$initListener$136$WaterMarkFragment(View view) {
        doApply();
    }

    public void loadResourceData() {
        this.mHasLoadData = false;
        this.mVideoEditorResourceRequest = new VideoEditorResourceRequest(getEffectId(), this.mModuleFactory);
        this.mVideoEditorResourceRequest.execute(new ResponseListener() {
            public void onResponse(Object... objArr) {
                WaterMarkFragment waterMarkFragment = WaterMarkFragment.this;
                waterMarkFragment.mHasLoadData = true;
                waterMarkFragment.refreshData(objArr[0]);
            }

            public void onResponseError(final ErrorCode errorCode, String str, Object obj) {
                Log.d("WaterMarkFragment", "errorCode: %s", (Object) errorCode);
                WaterMarkFragment.this.mHasLoadData = true;
                ThreadManager.runOnMainThread(new Runnable() {
                    public void run() {
                        if (WaterMarkFragment.this.mTextStyles != null && WaterMarkFragment.this.mTextStyles.size() == 0) {
                            TextStyle textStyle = new TextStyle(R.drawable.video_editor_icon_water_mark_none, "ve_type_none");
                            TextStyle textStyle2 = new TextStyle(R.drawable.video_editor_water_mark_text, "ve_type_local");
                            WaterMarkFragment.this.mTextStyles.add(textStyle);
                            WaterMarkFragment.this.mTextStyles.add(textStyle2);
                            if (WaterMarkFragment.this.mAdapter != null) {
                                WaterMarkFragment.this.mAdapter.notifyDataSetChanged();
                            }
                        }
                        if (errorCode == ErrorCode.NETWORK_NOT_CONNECTED) {
                            ThreadManager.runOnMainThread(new Runnable() {
                                public void run() {
                                    ToastUtils.makeText(WaterMarkFragment.this.mContext, (int) R.string.video_editor_download_failed_for_notwork);
                                }
                            });
                        }
                        int unused = WaterMarkFragment.this.mCurrentSelectIndex = 0;
                        int unused2 = WaterMarkFragment.this.mLastSelectIndex = 0;
                    }
                });
            }
        });
    }

    public void notifyDateSetChanged(int i) {
        WatermarkRecyclerViewAdapter watermarkRecyclerViewAdapter = this.mAdapter;
        if (watermarkRecyclerViewAdapter != null) {
            watermarkRecyclerViewAdapter.notifyItemChanged(i, 1);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.video_editor_text_start) {
            this.mSelectedTextTime = 0;
            updateBottomBtnState();
            updateEffectInfo();
        } else if (view.getId() == R.id.video_editor_text_end) {
            this.mSelectedTextTime = 1;
            updateBottomBtnState();
            updateEffectInfo();
        } else if (view.getId() == R.id.video_editor_text_all) {
            this.mSelectedTextTime = 2;
            updateBottomBtnState();
            updateEffectInfo();
        } else if (view.getId() == R.id.video_editor_btn_ok) {
            this.mIsKeyboardShowing = false;
            this.mInputText = this.mAutoText.getText().toString();
            updateEffectInfo();
            hideSoftInput();
            dismissTextPopWindow();
        } else if (view.getId() == R.id.video_editor_btn_cancel) {
            this.mIsKeyboardShowing = false;
            updateEffectInfo();
            hideSoftInput();
            dismissTextPopWindow();
        }
        applyPlay();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mWaterMarkManager = new WaterMarkManager();
        this.mTextStyles = WaterMarkManager.initDataWithBgColor();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new WaterMarkView(viewGroup.getContext());
    }

    public void onDestroyView() {
        WatermarkRecyclerViewAdapter watermarkRecyclerViewAdapter = this.mAdapter;
        if (watermarkRecyclerViewAdapter != null) {
            watermarkRecyclerViewAdapter.setItemSelectChangeListener((SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.ItemSelectChangeListener) null);
            this.mAdapter = null;
        }
        VideoEditorResourceRequest videoEditorResourceRequest = this.mVideoEditorResourceRequest;
        if (videoEditorResourceRequest != null) {
            videoEditorResourceRequest.cancel();
        }
        View view = this.mTextRoot;
        if (view != null) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        }
        PopupWindow popupWindow = this.mTextPopWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        ObjectAnimator objectAnimator = this.mTextAppearAnimator;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
            this.mTextAppearAnimator.cancel();
        }
        ResourceDownloadManager resourceDownloadManager = this.mResourceDownloadManager;
        if (resourceDownloadManager != null) {
            resourceDownloadManager.cancel();
            this.mResourceDownloadManager = null;
        }
        LoadWaterMarkTemplateTask loadWaterMarkTemplateTask = this.mLoadWaterMarkTemplateTask;
        if (loadWaterMarkTemplateTask != null) {
            loadWaterMarkTemplateTask.cancel();
            this.mLoadWaterMarkTemplateTask = null;
        }
        super.onDestroyView();
    }

    public void onPause() {
        super.onPause();
        hideSoftInput();
        this.mTextPopWindow.dismiss();
        this.mIsKeyboardShowing = false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStartActionButton = (TextView) view.findViewById(R.id.video_editor_text_start);
        this.mEndActionButton = (TextView) view.findViewById(R.id.video_editor_text_end);
        this.mAllActionButton = (TextView) view.findViewById(R.id.video_editor_text_all);
        this.mSelectTimeLayout = (ConstraintLayout) view.findViewById(R.id.select_time_layout);
        this.mTitleLayout = (ConstraintLayout) view.findViewById(R.id.title_layout);
        this.mIvCancel = (ImageView) view.findViewById(R.id.cancel);
        this.mIvOk = (ImageView) view.findViewById(R.id.ok);
        if (ToolsUtil.isRTLDirection()) {
            this.mStartActionButton.setGravity(8388627);
            this.mEndActionButton.setGravity(8388629);
        }
        this.mModuleFactory = new WaterMartFactory();
        this.mResourceDownloadManager = new ResourceDownloadManager(view.getContext(), getFragmentManager(), this.mModuleFactory);
        this.mLoadWaterMarkTemplateTask = new LoadWaterMarkTemplateTask();
        initRecyclerView(view);
        updateBottomBtnState();
        initListener();
        initEditPopWindow();
        initEnterAnim();
        loadResourceData();
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        this.mSavedSelectedWaterMarkIndex = this.mAdapter.getSelectedItemPosition();
    }
}
