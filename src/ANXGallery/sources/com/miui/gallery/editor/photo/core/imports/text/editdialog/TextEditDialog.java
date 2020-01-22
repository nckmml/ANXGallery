package com.miui.gallery.editor.photo.core.imports.text.editdialog;

import android.animation.LayoutTransition;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.text.model.DialogStatusData;
import com.miui.gallery.editor.photo.core.imports.text.typeface.TextStyle;
import com.miui.gallery.editor.photo.core.imports.text.utils.AutoLineLayout;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.util.ArrayList;
import java.util.List;

public class TextEditDialog extends GalleryDialogFragment {
    private ConfigChangeListener mConfigChangeListener;
    /* access modifiers changed from: private */
    public FrameLayout mContainerView;
    /* access modifiers changed from: private */
    public int mCurrentHeight = 0;
    /* access modifiers changed from: private */
    public Tab mCurrentTab = Tab.KEYBOARD;
    /* access modifiers changed from: private */
    public List<DialogSubMenu> mDialogSubMenuList = new ArrayList();
    /* access modifiers changed from: private */
    public EditText mEditText;
    private RelativeLayout mEditView;
    private DialogStatusData mInitializeData;
    /* access modifiers changed from: private */
    public int mKeyBoardHeight = 0;
    private View.OnClickListener mNavigationClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int ordinal = TextEditDialog.this.mCurrentTab.ordinal();
            for (int i = 0; i < TextEditDialog.this.mDialogSubMenuList.size(); i++) {
                DialogSubMenu dialogSubMenu = (DialogSubMenu) TextEditDialog.this.mDialogSubMenuList.get(i);
                if (view != dialogSubMenu.getNavigationButton()) {
                    dialogSubMenu.setChecked(false);
                } else if (ordinal != i) {
                    dialogSubMenu.setChecked(true);
                    if (i == 0) {
                        TextEditDialog.this.showKeyboard();
                    } else {
                        if (TextEditDialog.this.mCurrentTab == Tab.KEYBOARD) {
                            Tab unused = TextEditDialog.this.mCurrentTab = Tab.values()[i];
                            TextEditDialog.this.hideKeyboard();
                        }
                        ViewGroup subMenuView = dialogSubMenu.getSubMenuView();
                        TextEditDialog.this.mTabContainer.removeAllViews();
                        TextEditDialog.this.mTabContainer.addView(subMenuView, new FrameLayout.LayoutParams(-1, -1));
                    }
                    Tab unused2 = TextEditDialog.this.mCurrentTab = Tab.values()[i];
                    Log.d("TextEditDialog", "current click index : %d", (Object) Integer.valueOf(i));
                }
            }
        }
    };
    private LinearLayout mNavigationContainer;
    /* access modifiers changed from: private */
    public int mScreenHeight = 0;
    private StatusListener mStatusListener;
    /* access modifiers changed from: private */
    public FrameLayout mTabContainer;
    private TextWatcher mTextWatcher;
    private ViewGroup mWholeView;
    private String mWillEditText;
    private boolean mWillSelection = false;

    /* renamed from: com.miui.gallery.editor.photo.core.imports.text.editdialog.TextEditDialog$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$editor$photo$core$imports$text$editdialog$TextEditDialog$Tab = new int[Tab.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$editor$photo$core$imports$text$editdialog$TextEditDialog$Tab[Tab.KEYBOARD.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$editor$photo$core$imports$text$editdialog$TextEditDialog$Tab[Tab.STYLE.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$editor$photo$core$imports$text$editdialog$TextEditDialog$Tab[Tab.FONT.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public interface ConfigChangeListener {
        void onBoldChange(boolean z);

        void onColorChange(int i);

        void onShadowChange(boolean z);

        void onTextAlignChange(AutoLineLayout.TextAlignment textAlignment);

        void onTransparentChange(int i);

        void onTypefaceChange(TextStyle textStyle);
    }

    private class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private Rect mRect;

        private LayoutListener() {
            this.mRect = new Rect();
        }

        public void onGlobalLayout() {
            Dialog dialog = TextEditDialog.this.getDialog();
            if (dialog != null && dialog.getWindow() != null) {
                int access$400 = TextEditDialog.getHeightDifference(TextEditDialog.this.mContainerView, TextEditDialog.this.mScreenHeight, this.mRect);
                Log.d("TextEditDialog", "heightDifference : %d:%d:%d", Integer.valueOf(access$400), Integer.valueOf(TextEditDialog.this.mKeyBoardHeight), Integer.valueOf(TextEditDialog.this.mScreenHeight));
                if (access$400 != 0) {
                    if (TextEditDialog.this.mKeyBoardHeight < access$400) {
                        int unused = TextEditDialog.this.mKeyBoardHeight = access$400;
                    }
                    if (access$400 <= 0) {
                        int unused2 = TextEditDialog.this.mKeyBoardHeight = 0;
                    } else {
                        int unused3 = TextEditDialog.this.mKeyBoardHeight = Math.max(access$400, (int) ScreenUtils.dpToPixel(200.0f));
                    }
                    if (TextEditDialog.this.mCurrentHeight <= 0 && TextEditDialog.this.mCurrentHeight != access$400) {
                        int unused4 = TextEditDialog.this.mCurrentHeight = access$400;
                        TextEditDialog.this.checkMenuIndex(Tab.KEYBOARD.ordinal());
                    }
                    TextEditDialog.this.notifyKeyboardHeightChange();
                } else {
                    if (TextEditDialog.this.mCurrentHeight > 0 && TextEditDialog.this.mCurrentTab == Tab.KEYBOARD) {
                        Log.d("TextEditDialog", "onGlobalLayout dismiss");
                        TextEditDialog.this.dismissSafely();
                    }
                    int unused5 = TextEditDialog.this.mCurrentHeight = access$400;
                }
                TextEditDialog textEditDialog = TextEditDialog.this;
                textEditDialog.notifyHeightChange(textEditDialog.mKeyBoardHeight);
                Log.d("TextEditDialog", "onGlobalLayout mCurrentHeight %d", (Object) Integer.valueOf(TextEditDialog.this.mCurrentHeight));
            }
        }
    }

    public interface StatusListener {
        void onBottomChange(int i);

        void onDismiss();

        void onShow();
    }

    public enum Tab {
        KEYBOARD,
        STYLE,
        FONT;

        public DialogSubMenu getSubMenu(Context context, ConfigChangeListener configChangeListener, ViewGroup viewGroup) {
            int i = AnonymousClass3.$SwitchMap$com$miui$gallery$editor$photo$core$imports$text$editdialog$TextEditDialog$Tab[ordinal()];
            if (i == 1) {
                return new DialogKeyboardMenu(context);
            }
            if (i == 2) {
                return new DialogStyleMenu(context, viewGroup, configChangeListener);
            }
            if (i != 3) {
                return null;
            }
            return new DialogFontMenu(context, viewGroup, configChangeListener);
        }
    }

    /* access modifiers changed from: private */
    public void checkMenuIndex(int i) {
        if (i <= 0 || i >= this.mDialogSubMenuList.size()) {
            this.mNavigationClickListener.onClick(this.mDialogSubMenuList.get(i).getNavigationButton());
        }
    }

    /* access modifiers changed from: private */
    public static int getHeightDifference(View view, int i, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        view.getWindowVisibleDisplayFrame(rect);
        return (i - rect.bottom) - ((i - iArr[1]) - view.getHeight());
    }

    /* access modifiers changed from: private */
    public void hideKeyboard() {
        if (isAdded()) {
            Log.d("TextEditDialog", "hideKeyboard");
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mEditText.getWindowToken(), 0);
        }
    }

    private void initView(LayoutInflater layoutInflater) {
        this.mWholeView = (ViewGroup) layoutInflater.inflate(R.layout.text_edit_text_dialog, (ViewGroup) null);
        this.mNavigationContainer = (LinearLayout) this.mWholeView.findViewById(R.id.text_append_edit_text_tab_group);
        this.mTabContainer = (FrameLayout) this.mWholeView.findViewById(R.id.text_append_edit_text_tab_container);
        this.mEditView = (RelativeLayout) this.mWholeView.findViewById(R.id.text_append_edit_text_layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        for (Tab tab : Tab.values()) {
            DialogSubMenu subMenu = tab.getSubMenu(getActivity(), this.mConfigChangeListener, this.mTabContainer);
            if (tab.ordinal() == 0) {
                subMenu.setChecked(true);
            }
            View navigationButton = subMenu.getNavigationButton();
            navigationButton.setOnClickListener(this.mNavigationClickListener);
            this.mNavigationContainer.addView(navigationButton, layoutParams);
            this.mDialogSubMenuList.add(subMenu);
        }
        this.mEditText = (EditText) this.mWholeView.findViewById(R.id.text_append_edit_text);
        this.mWholeView.findViewById(R.id.text_append_edit_text_delete).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                TextEditDialog.this.lambda$initView$63$TextEditDialog(view);
            }
        });
        this.mWholeView.findViewById(R.id.text_append_edit_text_submit).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                TextEditDialog.this.lambda$initView$64$TextEditDialog(view);
            }
        });
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.enableTransitionType(4);
        this.mWholeView.setLayoutTransition(layoutTransition);
    }

    private void notifyDismiss() {
        StatusListener statusListener = this.mStatusListener;
        if (statusListener != null) {
            statusListener.onDismiss();
        }
    }

    /* access modifiers changed from: private */
    public void notifyHeightChange(int i) {
        if (this.mStatusListener != null) {
            int height = this.mNavigationContainer.getHeight() + ((LinearLayout.LayoutParams) this.mEditView.getLayoutParams()).topMargin + this.mEditView.getHeight();
            this.mScreenHeight = ScreenUtils.getScreenHeight();
            int i2 = (this.mScreenHeight - i) - height;
            Log.d("TextEditDialog", "navigation: %d height:%d,bottom:%d,%d", Integer.valueOf(height), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.mScreenHeight));
            if (i == 0) {
                this.mStatusListener.onBottomChange(this.mScreenHeight);
                Log.d("TextEditDialog", "notifyHeightChange: %d", (Object) Integer.valueOf(this.mScreenHeight));
                return;
            }
            this.mStatusListener.onBottomChange(i2);
            Log.d("TextEditDialog", "notifyHeightChange: %d", (Object) Integer.valueOf(i2));
        }
    }

    /* access modifiers changed from: private */
    public void notifyKeyboardHeightChange() {
        if (this.mKeyBoardHeight != 0) {
            if (this.mTabContainer.getVisibility() != 0 || this.mTabContainer.getHeight() != this.mKeyBoardHeight) {
                this.mTabContainer.setVisibility(0);
                this.mTabContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, this.mKeyBoardHeight));
            }
        }
    }

    private void notifyShow() {
        StatusListener statusListener = this.mStatusListener;
        if (statusListener != null) {
            statusListener.onShow();
        }
    }

    /* access modifiers changed from: private */
    public void showKeyboard() {
        this.mEditText.post(new Runnable() {
            public void run() {
                if (TextEditDialog.this.isAdded()) {
                    ((InputMethodManager) GalleryApp.sGetAndroidContext().getSystemService("input_method")).showSoftInput(TextEditDialog.this.mEditText, 1);
                }
            }
        });
    }

    public boolean isShowing() {
        Dialog dialog = getDialog();
        return dialog != null && dialog.isShowing();
    }

    public /* synthetic */ void lambda$initView$63$TextEditDialog(View view) {
        this.mEditText.setText("");
    }

    public /* synthetic */ void lambda$initView$64$TextEditDialog(View view) {
        dismissSafely();
    }

    public /* synthetic */ void lambda$onCreateDialog$65$TextEditDialog(DialogInterface dialogInterface) {
        Log.d("TextEditDialog", "onShow");
        this.mEditText.setFocusable(true);
        this.mEditText.setFocusableInTouchMode(true);
        this.mEditText.requestFocus();
        if (this.mCurrentTab == Tab.KEYBOARD) {
            showKeyboard();
        }
        String str = this.mWillEditText;
        if (str != null) {
            this.mEditText.setText(str);
            if (this.mWillSelection) {
                this.mEditText.setSelection(0, this.mWillEditText.length());
            } else {
                this.mEditText.setSelection(this.mWillEditText.length());
            }
        }
        TextWatcher textWatcher = this.mTextWatcher;
        if (textWatcher != null) {
            this.mEditText.addTextChangedListener(textWatcher);
        }
        notifyShow();
        notifyHeightChange(this.mKeyBoardHeight);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(getActivity(), 2131820890);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public final void onShow(DialogInterface dialogInterface) {
                TextEditDialog.this.lambda$onCreateDialog$65$TextEditDialog(dialogInterface);
            }
        });
        Window window = dialog.getWindow();
        if (window != null) {
            Point point = new Point();
            window.getWindowManager().getDefaultDisplay().getSize(point);
            this.mScreenHeight = point.y;
            window.getAttributes().windowAnimations = 2131820890;
        }
        return dialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mContainerView = new FrameLayout(getActivity());
        this.mContainerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.mContainerView.getViewTreeObserver().addOnGlobalLayoutListener(new LayoutListener());
        if (this.mWholeView == null) {
            initView(layoutInflater);
        }
        this.mContainerView.addView(this.mWholeView);
        return this.mContainerView;
    }

    public void onDestroyView() {
        for (DialogSubMenu next : this.mDialogSubMenuList) {
            if (next != null) {
                next.release();
            }
        }
        this.mContainerView.removeAllViews();
        super.onDestroyView();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        notifyHeightChange(0);
        notifyDismiss();
        TextWatcher textWatcher = this.mTextWatcher;
        if (textWatcher != null) {
            this.mEditText.removeTextChangedListener(textWatcher);
        }
    }

    public void onPause() {
        dismissSafely();
        super.onPause();
    }

    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setGravity(80);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            attributes.dimAmount = 0.0f;
            window.setAttributes(attributes);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Window window2 = getActivity().getWindow();
        if (window2 != null) {
            window2.setSoftInputMode(48);
        }
        this.mCurrentHeight = 0;
        Log.d("TextEditDialog", "onStart");
        notifyKeyboardHeightChange();
        for (DialogSubMenu initializeData : this.mDialogSubMenuList) {
            initializeData.initializeData(this.mInitializeData);
        }
    }

    public void setConfigChangeListener(ConfigChangeListener configChangeListener) {
        this.mConfigChangeListener = configChangeListener;
    }

    public void setInitializeData(DialogStatusData dialogStatusData) {
        this.mInitializeData = dialogStatusData;
    }

    public void setStatusListener(StatusListener statusListener) {
        this.mStatusListener = statusListener;
    }

    public void setTextWatch(TextWatcher textWatcher) {
        this.mTextWatcher = textWatcher;
    }

    public void setWillEditText(String str, boolean z) {
        this.mWillEditText = str;
        this.mWillSelection = z;
    }
}
