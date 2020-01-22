package com.miui.gallery.search.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.util.ToastUtils;
import miui.widget.ClearableEditText;

public class SearchView extends LinearLayout implements View.OnClickListener {
    private ClearableEditText mEditText;
    /* access modifiers changed from: private */
    public SearchViewListener mSearchViewListener;
    private View mVoiceButton;
    private boolean mVoiceButtonEnabled;

    public interface SearchViewListener {
        void onCancelSearch(View view);

        void onFocusChanged(View view, boolean z);

        void onQueryTextChanged(View view, String str);

        void onQueryTextSubmit(View view, String str);

        void onStartVoiceAssistant(View view);
    }

    public SearchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVoiceButtonEnabled = false;
        LayoutInflater.from(context).inflate(R.layout.search_view_layout, this, true);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mEditText = findViewById(R.id.search_view_input);
        this.mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (SearchView.this.mSearchViewListener != null) {
                    SearchView.this.mSearchViewListener.onFocusChanged(SearchView.this, z);
                }
            }
        });
        this.mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 0 && i != 3) {
                    return false;
                }
                if (TextUtils.isEmpty(SearchView.this.getQueryText())) {
                    ToastUtils.makeText(SearchView.this.getContext(), (int) R.string.empty_query_text_msg);
                    return false;
                } else if (SearchView.this.mSearchViewListener == null) {
                    return false;
                } else {
                    SearchViewListener access$000 = SearchView.this.mSearchViewListener;
                    SearchView searchView = SearchView.this;
                    access$000.onQueryTextSubmit(searchView, searchView.getQueryText());
                    return true;
                }
            }
        });
        this.mEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (SearchView.this.mSearchViewListener != null) {
                    SearchViewListener access$000 = SearchView.this.mSearchViewListener;
                    SearchView searchView = SearchView.this;
                    access$000.onQueryTextChanged(searchView, searchView.getQueryText());
                }
                SearchView.this.updateVoiceButtonState();
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        findViewById(R.id.search_btn_cancel).setOnClickListener(this);
        this.mVoiceButton = findViewById(R.id.search_btn_voice);
        this.mVoiceButton.setOnClickListener(this);
        updateVoiceButtonState();
    }

    private InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getContext().getSystemService("input_method");
    }

    /* access modifiers changed from: private */
    public void updateVoiceButtonState() {
        if (!this.mVoiceButtonEnabled || !TextUtils.isEmpty(this.mEditText.getText().toString())) {
            this.mVoiceButton.setVisibility(8);
        } else {
            this.mVoiceButton.setVisibility(0);
        }
    }

    public void clearFocus() {
        super.clearFocus();
        hideInputMethod();
        this.mEditText.clearFocus();
    }

    public String getQueryText() {
        return this.mEditText.getText().toString();
    }

    public boolean hideInputMethod() {
        InputMethodManager inputMethodManager = getInputMethodManager();
        return inputMethodManager != null && inputMethodManager.hideSoftInputFromWindow(this.mEditText.getWindowToken(), 0);
    }

    public void onClick(View view) {
        if (view != null && this.mSearchViewListener != null) {
            switch (view.getId()) {
                case R.id.search_btn_cancel:
                    this.mSearchViewListener.onCancelSearch(this);
                    return;
                case R.id.search_btn_voice:
                    this.mSearchViewListener.onStartVoiceAssistant(this);
                    return;
                default:
                    return;
            }
        }
    }

    public void selectAll(boolean z) {
        if (z) {
            this.mEditText.selectAll();
        } else {
            this.mEditText.setSelection(getQueryText() != null ? getQueryText().length() : 0);
        }
    }

    public void setCursorVisible(boolean z) {
        this.mEditText.setCursorVisible(z);
    }

    public void setHint(String str) {
        this.mEditText.setHint(str);
    }

    public void setQueryText(String str) {
        this.mEditText.setText(str);
    }

    public void setSearchViewListener(SearchViewListener searchViewListener) {
        this.mSearchViewListener = searchViewListener;
    }

    public void setVoiceButtonEnabled(boolean z) {
        this.mVoiceButtonEnabled = z;
        updateVoiceButtonState();
    }

    public boolean showInputMethod() {
        InputMethodManager inputMethodManager = getInputMethodManager();
        if (inputMethodManager == null) {
            return false;
        }
        this.mEditText.requestFocus();
        return inputMethodManager.showSoftInput(this.mEditText, 0);
    }
}
