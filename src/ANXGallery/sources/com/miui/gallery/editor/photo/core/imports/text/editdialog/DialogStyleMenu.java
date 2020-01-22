package com.miui.gallery.editor.photo.core.imports.text.editdialog;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.text.editdialog.TextEditDialog;
import com.miui.gallery.editor.photo.core.imports.text.model.DialogStatusData;
import com.miui.gallery.editor.photo.core.imports.text.utils.AutoLineLayout;
import com.miui.gallery.editor.photo.widgets.seekbar.BubbleIndicator;
import com.miui.gallery.editor.photo.widgets.seekbar.CircleDrawable;
import com.miui.gallery.editor.photo.widgets.seekbar.ColorGradientDrawable;
import com.miui.gallery.editor.photo.widgets.seekbar.ColorPicker;
import com.miui.gallery.util.Log;

class DialogStyleMenu extends DialogSubMenu<TextEditDialog.ConfigChangeListener, DialogStatusData> implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private TextView mAlignButton;
    private final Drawable mAlignCenterDrawable;
    private final String mAlignCenterString;
    private int mAlignIndex = 0;
    private final Drawable mAlignLeftDrawable;
    private final String mAlignLeftString;
    private final Drawable mAlignRightDrawable;
    private final String mAlignRightString;
    private TextView mBoldButton;
    /* access modifiers changed from: private */
    public ColorPicker mColorPickSeekBar;
    /* access modifiers changed from: private */
    public TextView mShadowButton;
    private SeekBar mTransparentSeekBar;
    private ViewGroup mWholeView;

    /* renamed from: com.miui.gallery.editor.photo.core.imports.text.editdialog.DialogStyleMenu$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$editor$photo$core$imports$text$utils$AutoLineLayout$TextAlignment = new int[AutoLineLayout.TextAlignment.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$editor$photo$core$imports$text$utils$AutoLineLayout$TextAlignment[AutoLineLayout.TextAlignment.LEFT.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$editor$photo$core$imports$text$utils$AutoLineLayout$TextAlignment[AutoLineLayout.TextAlignment.CENTER.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$editor$photo$core$imports$text$utils$AutoLineLayout$TextAlignment[AutoLineLayout.TextAlignment.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private class BubbleCallback implements BubbleIndicator.Callback<View> {
        private BubbleCallback() {
        }

        public void updateProgress(View view, int i) {
            Log.d("DialogStyleMenu", "updateProgress progress: %d color: %s", Integer.valueOf(i), Integer.toHexString(DialogStyleMenu.this.mColorPickSeekBar.getColor()));
            ((GradientDrawable) view.getBackground()).setColor(DialogStyleMenu.this.mColorPickSeekBar.getColor());
        }
    }

    private class ColorPickerChangeListener implements SeekBar.OnSeekBarChangeListener {
        private ColorPickerChangeListener() {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (DialogStyleMenu.this.mListener != null) {
                DialogStyleMenu.this.mColorPickSeekBar.setThumbColor(DialogStyleMenu.this.mColorPickSeekBar.getColor());
                ((TextEditDialog.ConfigChangeListener) DialogStyleMenu.this.mListener).onColorChange(DialogStyleMenu.this.mColorPickSeekBar.getColor());
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (DialogStyleMenu.this.mListener != null) {
                DialogStyleMenu.this.mColorPickSeekBar.setThumbColor(DialogStyleMenu.this.mColorPickSeekBar.getColor());
                ((TextEditDialog.ConfigChangeListener) DialogStyleMenu.this.mListener).onColorChange(DialogStyleMenu.this.mColorPickSeekBar.getColor());
            }
        }
    }

    DialogStyleMenu(Context context, ViewGroup viewGroup, TextEditDialog.ConfigChangeListener configChangeListener) {
        super(context, viewGroup, R.string.text_edit_dialog_style, R.drawable.text_edit_dialog_tab_icon_style);
        this.mListener = configChangeListener;
        this.mAlignLeftDrawable = context.getResources().getDrawable(R.drawable.text_edit_dialog_tab_icon_align_left);
        this.mAlignCenterDrawable = context.getResources().getDrawable(R.drawable.text_edit_dialog_tab_icon_align_center);
        this.mAlignRightDrawable = context.getResources().getDrawable(R.drawable.text_edit_dialog_tab_icon_align_right);
        this.mAlignLeftString = context.getString(R.string.text_edit_dialog_style_align_left);
        this.mAlignCenterString = context.getString(R.string.text_edit_dialog_style_align_center);
        this.mAlignRightString = context.getString(R.string.text_edit_dialog_style_align_right);
    }

    private void initView(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.custom_seekbar_thumb_size);
        this.mColorPickSeekBar.setProgressDrawable(new ColorGradientDrawable(ColorPicker.COLORS));
        ColorPicker colorPicker = this.mColorPickSeekBar;
        colorPicker.setThumbColor(colorPicker.getColor());
        CircleDrawable circleDrawable = new CircleDrawable((float) context.getResources().getDimensionPixelSize(R.dimen.editor_seek_bar_progress_thumb_start), context.getResources());
        circleDrawable.setIntrinsicWidth(dimensionPixelSize);
        circleDrawable.setIntrinsicHeight(dimensionPixelSize);
        this.mTransparentSeekBar.setThumb(circleDrawable);
        if (this.mTransparentSeekBar.getLayerType() == 0) {
            this.mTransparentSeekBar.setLayerType(2, (Paint) null);
        }
        setViewClickListener(this.mShadowButton, this.mAlignButton, this.mBoldButton);
        this.mTransparentSeekBar.setOnSeekBarChangeListener(this);
        this.mColorPickSeekBar.setOnSeekBarChangeListener(new BubbleIndicator(View.inflate(context, R.layout.doodle_color_indicator, (ViewGroup) null), context.getResources().getDimensionPixelSize(R.dimen.photo_editor_bubble_indicator_offset), new BubbleCallback(), new ColorPickerChangeListener()));
    }

    private void setAlignButton(AutoLineLayout.TextAlignment textAlignment) {
        String str;
        Drawable drawable;
        int i = AnonymousClass2.$SwitchMap$com$miui$gallery$editor$photo$core$imports$text$utils$AutoLineLayout$TextAlignment[textAlignment.ordinal()];
        if (i == 1) {
            drawable = this.mAlignLeftDrawable;
            str = this.mAlignLeftString;
        } else if (i == 2) {
            drawable = this.mAlignCenterDrawable;
            str = this.mAlignCenterString;
        } else if (i != 3) {
            drawable = null;
            str = null;
        } else {
            str = this.mAlignRightString;
            drawable = this.mAlignRightDrawable;
        }
        this.mAlignButton.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        this.mAlignButton.setText(str);
        this.mAlignIndex = textAlignment.ordinal();
    }

    private void setViewClickListener(View... viewArr) {
        for (View onClickListener : viewArr) {
            onClickListener.setOnClickListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public ViewGroup initSubMenuView(Context context, ViewGroup viewGroup) {
        this.mWholeView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.text_edit_dialog_options_style_panel, viewGroup, false);
        this.mColorPickSeekBar = (ColorPicker) this.mWholeView.findViewById(R.id.text_edit_dialog_tab_style_color_picker);
        this.mTransparentSeekBar = (SeekBar) this.mWholeView.findViewById(R.id.text_edit_dialog_tab_style_transparent);
        this.mShadowButton = (TextView) this.mWholeView.findViewById(R.id.text_edit_dialog_tab_style_shadow);
        this.mAlignButton = (TextView) this.mWholeView.findViewById(R.id.text_edit_dialog_tab_style_align);
        this.mBoldButton = (TextView) this.mWholeView.findViewById(R.id.text_edit_dialog_tab_style_bold);
        initView(context);
        return this.mWholeView;
    }

    public void initializeData(DialogStatusData dialogStatusData) {
        this.mColorPickSeekBar.setProgress(this.mColorPickSeekBar.findProgressByColor(dialogStatusData.color));
        int max = this.mTransparentSeekBar.getMax();
        this.mTransparentSeekBar.setProgress(max - ((int) (((float) max) * dialogStatusData.transparentProgress)));
        this.mBoldButton.setSelected(dialogStatusData.textBold);
        this.mShadowButton.setSelected(dialogStatusData.textShadow);
        this.mShadowButton.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                Log.d("DialogStyleMenu", "onLayoutChange");
                DialogStyleMenu.this.mShadowButton.removeOnLayoutChangeListener(this);
                DialogStyleMenu.this.mShadowButton.refreshDrawableState();
            }
        });
        setAlignButton(dialogStatusData.textAlignment);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_edit_dialog_tab_style_align:
                AutoLineLayout.TextAlignment[] values = AutoLineLayout.TextAlignment.values();
                this.mAlignIndex++;
                this.mAlignIndex %= values.length;
                AutoLineLayout.TextAlignment textAlignment = values[this.mAlignIndex];
                setAlignButton(textAlignment);
                if (this.mListener != null) {
                    ((TextEditDialog.ConfigChangeListener) this.mListener).onTextAlignChange(textAlignment);
                    return;
                }
                return;
            case R.id.text_edit_dialog_tab_style_bold:
                boolean isSelected = this.mBoldButton.isSelected();
                this.mBoldButton.setSelected(!isSelected);
                if (this.mListener != null) {
                    ((TextEditDialog.ConfigChangeListener) this.mListener).onBoldChange(!isSelected);
                    return;
                }
                return;
            case R.id.text_edit_dialog_tab_style_shadow:
                boolean isSelected2 = this.mShadowButton.isSelected();
                this.mShadowButton.setSelected(!isSelected2);
                if (this.mListener != null) {
                    ((TextEditDialog.ConfigChangeListener) this.mListener).onShadowChange(!isSelected2);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (seekBar.getId() == R.id.text_edit_dialog_tab_style_transparent && this.mListener != null) {
            ((TextEditDialog.ConfigChangeListener) this.mListener).onTransparentChange(seekBar.getMax() - i);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.text_edit_dialog_tab_style_transparent && this.mListener != null) {
            ((TextEditDialog.ConfigChangeListener) this.mListener).onTransparentChange(seekBar.getMax() - seekBar.getProgress());
        }
    }
}
