package com.miui.gallery.editor.photo.core.imports.text;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.Metadata;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractEffectFragment;
import com.miui.gallery.editor.photo.core.imports.text.TextEditorView;
import com.miui.gallery.editor.photo.core.imports.text.base.ITextDialogConfig;
import com.miui.gallery.editor.photo.core.imports.text.dialog.BaseDialogModel;
import com.miui.gallery.editor.photo.core.imports.text.editdialog.TextEditDialog;
import com.miui.gallery.editor.photo.core.imports.text.model.DialogStatusData;
import com.miui.gallery.editor.photo.core.imports.text.typeface.TextStyle;
import com.miui.gallery.editor.photo.core.imports.text.utils.AutoLineLayout;
import com.miui.gallery.util.Log;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TextFragment extends AbstractEffectFragment {
    private String mBubbleText;
    /* access modifiers changed from: private */
    public int mCurrentIndex = 0;
    /* access modifiers changed from: private */
    public TextStyle mCurrentTextStyle;
    /* access modifiers changed from: private */
    public StatusListener mStatusListener = new StatusListener();
    private SparseArray<DialogStatusData> mTextConfigDataArray = new SparseArray<>();
    /* access modifiers changed from: private */
    public TextEditDialog mTextEditDialog;
    private TextEditorView.TextEditorListener mTextEditorListener = new TextEditorView.TextEditorListener() {
        public void onClear() {
            int unused = TextFragment.this.mCurrentIndex = -1;
            TextFragment.this.mTextEditorView.onClear();
        }

        public void onItemEdit() {
            Activity activity;
            if (!TextFragment.this.isDetached() && (activity = TextFragment.this.getActivity()) != null && !activity.isFinishing() && !TextFragment.this.mTextEditDialog.isShowing()) {
                String itemText = TextFragment.this.mTextEditorView.getItemText();
                TextFragment.this.mTextEditDialog.setWillEditText(itemText, TextFragment.this.getString(R.string.text_append_hint).equals(itemText));
                TextFragment textFragment = TextFragment.this;
                DialogStatusData textConfigDataByIndex = textFragment.getTextConfigDataByIndex(textFragment.mCurrentIndex);
                TextFragment.this.mTextEditorView.getCurrentItemStatus(textConfigDataByIndex);
                TextFragment.this.mTextEditDialog.setInitializeData(textConfigDataByIndex);
                TextFragment.this.mTextEditDialog.showAllowingStateLoss(TextFragment.this.getFragmentManager(), "TextEditDialog");
            }
        }

        public void onModify() {
        }
    };
    /* access modifiers changed from: private */
    public TextEditorView mTextEditorView;
    private TextWatcher mTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            TextFragment.this.mTextEditorView.setItemText(charSequence.toString());
            TextFragment.this.mStatusListener.onTextChange();
        }
    };

    private class ConfigListener implements TextEditDialog.ConfigChangeListener {
        private ConfigListener() {
        }

        public void onBoldChange(boolean z) {
            TextFragment.this.mTextEditorView.setItemBold(z);
        }

        public void onColorChange(int i) {
            TextFragment.this.mTextEditorView.setItemTextColor(i);
        }

        public void onShadowChange(boolean z) {
            TextFragment.this.mTextEditorView.setItemShadow(z);
        }

        public void onTextAlignChange(AutoLineLayout.TextAlignment textAlignment) {
            TextFragment.this.mTextEditorView.setItemTextAlignment(textAlignment);
        }

        public void onTransparentChange(int i) {
            TextFragment.this.mTextEditorView.setItemTransparent(((float) i) / 100.0f);
        }

        public void onTypefaceChange(TextStyle textStyle) {
            TextStyle unused = TextFragment.this.mCurrentTextStyle = textStyle;
            TextFragment.this.mTextEditorView.setItemTypeface(textStyle);
        }
    }

    private class StatusListener implements TextEditDialog.StatusListener {
        private int mDialogBottom;

        private StatusListener() {
        }

        public void onBottomChange(int i) {
            this.mDialogBottom = i;
            Log.d("TextFragment", "onBottomChange: %d", (Object) Integer.valueOf(i));
            int activationItemBottom = TextFragment.this.mTextEditorView.getActivationItemBottom();
            Log.d("TextFragment", "text bottom: %d", (Object) Integer.valueOf(activationItemBottom));
            if (activationItemBottom > i) {
                TextFragment.this.mTextEditorView.offsetWithAnimator((float) (i - activationItemBottom));
            } else {
                TextFragment.this.mTextEditorView.offsetWithAnimator(0.0f);
            }
        }

        public void onDismiss() {
        }

        public void onShow() {
        }

        /* access modifiers changed from: package-private */
        public void onTextChange() {
            onBottomChange(this.mDialogBottom);
        }
    }

    private void performSetDialog(TextConfig textConfig, int i) {
        boolean z;
        BaseDialogModel baseDialogModel = textConfig.getBaseDialogModel();
        DialogStatusData dialogStatusData = this.mTextConfigDataArray.get(i);
        if (dialogStatusData == null) {
            dialogStatusData = getTextConfigDataByIndex(i);
            if (textConfig.isWatermark()) {
                dialogStatusData.watermarkInitSelf(textConfig.getWatermarkInfo());
            } else {
                dialogStatusData.configSelfByInit(baseDialogModel);
            }
            z = true;
        } else {
            z = false;
        }
        if (!textConfig.isWatermark()) {
            dialogStatusData.text = this.mBubbleText;
        }
        this.mTextEditorView.enableStatusForCurrentItem(dialogStatusData, z);
        this.mCurrentIndex = i;
        this.mTextEditorView.setItemDialogModel(baseDialogModel);
    }

    public void add(Metadata metadata, Object obj) {
        if (isAdded()) {
            int intValue = ((Integer) obj).intValue();
            TextConfig textConfig = (TextConfig) metadata;
            int i = this.mCurrentIndex;
            if (intValue != i) {
                DialogStatusData textConfigDataByIndex = getTextConfigDataByIndex(i);
                this.mTextEditorView.getDialogStatusData(textConfigDataByIndex);
                ITextDialogConfig itemTextDialogConfig = this.mTextEditorView.getItemTextDialogConfig();
                if (itemTextDialogConfig != null && !itemTextDialogConfig.isWatermark()) {
                    this.mBubbleText = textConfigDataByIndex.text;
                }
                this.mTextEditorView.removeLastItem();
                this.mTextEditorView.addNewItem(textConfig);
                if (!this.mTextEditorView.isItemActivation()) {
                    this.mTextEditorView.setLastItemActivation();
                }
                performSetDialog(textConfig, intValue);
            }
        }
    }

    public void clear() {
    }

    public DialogStatusData getTextConfigDataByIndex(int i) {
        DialogStatusData dialogStatusData = this.mTextConfigDataArray.get(i);
        if (dialogStatusData != null) {
            return dialogStatusData;
        }
        DialogStatusData dialogStatusData2 = new DialogStatusData();
        dialogStatusData2.setEmpty();
        this.mTextConfigDataArray.put(i, dialogStatusData2);
        return dialogStatusData2;
    }

    public boolean isEmpty() {
        return this.mTextEditorView.isEmpty();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = (FrameLayout) layoutInflater.inflate(R.layout.editor_view_container, viewGroup, false);
        this.mTextEditorView = new TextEditorView(getActivity());
        this.mTextEditDialog = new TextEditDialog();
        this.mTextEditDialog.setConfigChangeListener(new ConfigListener());
        this.mTextEditorView.setTextEditorListener(this.mTextEditorListener);
        this.mTextEditDialog.setTextWatch(this.mTextWatcher);
        this.mTextEditDialog.setStatusListener(this.mStatusListener);
        this.mTextEditorView.setBitmap(getBitmap());
        frameLayout.addView(this.mTextEditorView, -1, -1);
        return frameLayout;
    }

    /* access modifiers changed from: protected */
    public RenderData onExport() {
        return new TextRenderData(this.mTextEditorView.export());
    }

    /* access modifiers changed from: protected */
    public List<String> onSample() {
        ArrayList arrayList = new ArrayList();
        ITextDialogConfig itemTextDialogConfig = this.mTextEditorView.getItemTextDialogConfig();
        if (itemTextDialogConfig != null) {
            arrayList.add(itemTextDialogConfig.getSampleName());
        }
        if (this.mCurrentTextStyle != null) {
            arrayList.add("font_" + this.mCurrentTextStyle.getName());
        }
        arrayList.add("color_" + Integer.toHexString(this.mTextEditorView.getItemColor()));
        if (this.mTextEditorView.getItemBold()) {
            arrayList.add("bold");
        }
        if (this.mTextEditorView.getItemShadow()) {
            arrayList.add("shadow");
        }
        AutoLineLayout.TextAlignment itemAlignment = this.mTextEditorView.getItemAlignment();
        if (itemAlignment != AutoLineLayout.TextAlignment.LEFT) {
            arrayList.add("align_" + itemAlignment);
        }
        float itemTransparent = this.mTextEditorView.getItemTransparent();
        if (itemTransparent > 0.0f) {
            DecimalFormat decimalFormat = new DecimalFormat(".0");
            arrayList.add("transparent_" + decimalFormat.format((double) itemTransparent));
        }
        return arrayList;
    }

    public void remove(Metadata metadata) {
    }

    public void render() {
    }
}
