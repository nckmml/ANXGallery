package com.miui.gallery.editor.photo.core.imports.text.editdialog;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.text.TextFontConfig;
import com.miui.gallery.editor.photo.core.imports.text.editdialog.TextEditDialog;
import com.miui.gallery.editor.photo.core.imports.text.model.DialogStatusData;
import com.miui.gallery.editor.photo.core.imports.text.typeface.DownloadCallback;
import com.miui.gallery.editor.photo.core.imports.text.typeface.FontDownloadManager;
import com.miui.gallery.editor.photo.core.imports.text.typeface.FontResourceRequest;
import com.miui.gallery.editor.photo.core.imports.text.typeface.TextStyle;
import com.miui.gallery.editor.photo.core.imports.text.typeface.TypeFaceAdapter;
import com.miui.gallery.editor.photo.core.imports.text.utils.TextTools;
import com.miui.gallery.net.base.ErrorCode;
import com.miui.gallery.net.base.ResponseListener;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.widget.recyclerview.SingleChoiceRecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class DialogFontMenu extends DialogSubMenu<TextEditDialog.ConfigChangeListener, DialogStatusData> {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public TextStyle mCurrentTextStyle;
    private FontDownloadManager mFontDownloadManager;
    private FontResourceRequest mFontRequest;
    /* access modifiers changed from: private */
    public DialogStatusData mInitializeData;
    private SingleChoiceRecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public List<TextStyle> mTextStyles;
    /* access modifiers changed from: private */
    public TypeFaceAdapter mTypeFaceAdapter;
    /* access modifiers changed from: private */
    public boolean mTypeFaceInited = false;
    private ViewGroup mWholeView;

    class TypeFaceItemSelectChangeListener implements SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.ItemSelectChangeListener {
        TypeFaceItemSelectChangeListener() {
        }

        public boolean onItemSelect(SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter singleChoiceRecyclerViewAdapter, int i, boolean z) {
            DialogFontMenu dialogFontMenu = DialogFontMenu.this;
            TextStyle unused = dialogFontMenu.mCurrentTextStyle = (TextStyle) dialogFontMenu.mTextStyles.get(i);
            if (DialogFontMenu.this.mCurrentTextStyle.isLocal()) {
                DialogFontMenu.this.updateSelectedItemPosition(i);
                if (DialogFontMenu.this.mListener == null) {
                    return true;
                }
                ((TextEditDialog.ConfigChangeListener) DialogFontMenu.this.mListener).onTypefaceChange(DialogFontMenu.this.mCurrentTextStyle);
                return true;
            } else if (!DialogFontMenu.this.mCurrentTextStyle.isExtra()) {
                return true;
            } else {
                if (DialogFontMenu.this.mCurrentTextStyle.isNeedDownload()) {
                    DialogFontMenu dialogFontMenu2 = DialogFontMenu.this;
                    dialogFontMenu2.downloadResource(dialogFontMenu2.mContext, DialogFontMenu.this.mCurrentTextStyle, i);
                    return true;
                } else if (!DialogFontMenu.this.mCurrentTextStyle.isDownloaded()) {
                    return true;
                } else {
                    DialogFontMenu.this.updateSelectedItemPosition(i);
                    if (DialogFontMenu.this.mListener == null) {
                        return true;
                    }
                    ((TextEditDialog.ConfigChangeListener) DialogFontMenu.this.mListener).onTypefaceChange(DialogFontMenu.this.mCurrentTextStyle);
                    return true;
                }
            }
        }
    }

    DialogFontMenu(Context context, ViewGroup viewGroup, TextEditDialog.ConfigChangeListener configChangeListener) {
        super(context, viewGroup, R.string.text_edit_dialog_font, R.drawable.text_edit_dialog_tab_icon_font);
        this.mContext = context;
        this.mListener = configChangeListener;
    }

    /* access modifiers changed from: private */
    public void downloadResource(Context context, final TextStyle textStyle, final int i) {
        if (this.mFontDownloadManager == null) {
            this.mFontDownloadManager = new FontDownloadManager();
        }
        this.mFontDownloadManager.downloadFontResource(context, textStyle, new DownloadCallback() {
            public void onCompleted(boolean z) {
                Log.d("DialogFontMenu", "%s download is : %s", textStyle.getText(), Boolean.valueOf(z));
                if (z) {
                    ThreadManager.getMiscPool().submit(new ThreadPool.Job<Object>() {
                        public Object run(ThreadPool.JobContext jobContext) {
                            String filePath = textStyle.getFilePath();
                            if (!FileUtils.isFileExist(filePath)) {
                                return null;
                            }
                            textStyle.setTypeFace(Typeface.createFromFile(filePath));
                            return null;
                        }
                    }, new FutureHandler<Object>() {
                        public void onPostExecute(Future<Object> future) {
                            textStyle.setState(0);
                            DialogFontMenu.this.notifyItemChanged(i);
                        }
                    });
                    return;
                }
                textStyle.setState(20);
                DialogFontMenu.this.notifyItemChanged(i);
            }

            public void onStart() {
                textStyle.setState(18);
                DialogFontMenu.this.notifyItemChanged(i);
            }
        });
    }

    private boolean isEquals(Typeface typeface, Typeface typeface2) {
        return (typeface == null && typeface2 == null) || (typeface != null && typeface.equals(typeface2));
    }

    private void loadResourceData() {
        if (!TextTools.isZhCNLanguage()) {
            TextTools.checkResourceExist(this.mTextStyles);
            this.mTypeFaceAdapter.notifyDataSetChanged();
            return;
        }
        Log.d("DialogFontMenu", "loadResourceData start.");
        final long currentTimeMillis = System.currentTimeMillis();
        this.mFontRequest = new FontResourceRequest();
        this.mFontRequest.execute(new ResponseListener() {
            public void onResponse(Object... objArr) {
                Log.d("DialogFontMenu", "loadResourceData success, use time %s  ms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                List<TextStyle> list = objArr[0];
                if (MiscUtil.isValid(list)) {
                    Collections.sort(list);
                    boolean isNightMode = MiscUtil.isNightMode(DialogFontMenu.this.mContext);
                    final ArrayList arrayList = new ArrayList();
                    arrayList.add(TextStyle.getLocalTextStyle());
                    for (TextStyle textStyle : list) {
                        if (!isNightMode && !textStyle.getExtraInfo().isDarkModeData()) {
                            arrayList.add(textStyle);
                        }
                        if (isNightMode && textStyle.getExtraInfo().isDarkModeData()) {
                            arrayList.add(textStyle);
                        }
                    }
                    TextTools.checkResourceExist(arrayList);
                    ThreadManager.runOnMainThread(new Runnable() {
                        public void run() {
                            DialogFontMenu.this.mTextStyles.clear();
                            DialogFontMenu.this.mTextStyles.addAll(arrayList);
                            boolean unused = DialogFontMenu.this.mTypeFaceInited = true;
                            DialogFontMenu.this.mTypeFaceAdapter.notifyDataSetChanged();
                            DialogFontMenu.this.initializeData(DialogFontMenu.this.mInitializeData);
                        }
                    });
                }
            }

            public void onResponseError(ErrorCode errorCode, String str, Object obj) {
                Log.d("DialogFontMenu", "loadResourceData error: %s, errorCode: %s", str, errorCode.toString());
                final ArrayList arrayList = new ArrayList();
                arrayList.add(TextStyle.getLocalTextStyle());
                TextTools.checkResourceExist(arrayList);
                ThreadManager.runOnMainThread(new Runnable() {
                    public void run() {
                        DialogFontMenu.this.mTextStyles.clear();
                        DialogFontMenu.this.mTextStyles.addAll(arrayList);
                        if (DialogFontMenu.this.mTypeFaceAdapter != null) {
                            DialogFontMenu.this.mTypeFaceAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateSelectedItemPosition(int i) {
        if (i != this.mTypeFaceAdapter.getSelectedItemPosition()) {
            this.mTypeFaceAdapter.setSelectedItemPosition(i);
            this.mTypeFaceAdapter.clearLastSelectedPostion();
        }
    }

    /* access modifiers changed from: package-private */
    public ViewGroup initSubMenuView(Context context, ViewGroup viewGroup) {
        TextFontConfig.init(context);
        this.mWholeView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.text_edit_dialog_options_font_panel, viewGroup, false);
        this.mRecyclerView = (SingleChoiceRecyclerView) this.mWholeView.findViewById(R.id.text_edit_dialog_tab_font_recycler);
        this.mTextStyles = new ArrayList();
        this.mTextStyles.add(TextStyle.getLocalTextStyle());
        this.mTypeFaceAdapter = new TypeFaceAdapter(context, this.mTextStyles);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.mRecyclerView.setAdapter(this.mTypeFaceAdapter);
        this.mTypeFaceAdapter.setItemSelectChangeListener(new TypeFaceItemSelectChangeListener());
        loadResourceData();
        return this.mWholeView;
    }

    public void initializeData(DialogStatusData dialogStatusData) {
        this.mInitializeData = dialogStatusData;
        if (this.mTypeFaceInited && dialogStatusData != null) {
            Typeface typeFace = dialogStatusData.textStyle == null ? Typeface.DEFAULT : dialogStatusData.textStyle.getTypeFace();
            if (this.mTypeFaceAdapter != null) {
                for (int i = 0; i < this.mTextStyles.size(); i++) {
                    if (isEquals(typeFace, this.mTextStyles.get(i).getTypeFace())) {
                        updateSelectedItemPosition(i);
                        this.mTypeFaceAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    public void notifyItemChanged(int i) {
        TypeFaceAdapter typeFaceAdapter = this.mTypeFaceAdapter;
        if (typeFaceAdapter != null) {
            typeFaceAdapter.notifyItemChanged(i, 1);
        }
    }

    public void release() {
        TextStyle next;
        FontResourceRequest fontResourceRequest = this.mFontRequest;
        if (fontResourceRequest != null) {
            fontResourceRequest.cancel();
        }
        FontDownloadManager fontDownloadManager = this.mFontDownloadManager;
        if (fontDownloadManager != null) {
            fontDownloadManager.cancelAll();
        }
        if (MiscUtil.isValid(this.mTextStyles)) {
            Iterator<TextStyle> it = this.mTextStyles.iterator();
            while (it.hasNext() && (next = it.next()) != null) {
                if (next.isDownloading()) {
                    next.setState(20);
                }
            }
        }
    }
}
