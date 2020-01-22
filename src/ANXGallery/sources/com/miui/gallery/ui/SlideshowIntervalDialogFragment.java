package com.miui.gallery.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.miui.gallery.R;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.GalleryDialogFragment;
import miui.app.AlertDialog;

public class SlideshowIntervalDialogFragment extends GalleryDialogFragment {
    /* access modifiers changed from: private */
    public AlertDialog mDialog;
    /* access modifiers changed from: private */
    public CompleteListener mListener;

    public interface CompleteListener {
        void onComplete();
    }

    /* access modifiers changed from: private */
    public boolean isTextValid(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        try {
            int parseInt = Integer.parseInt(charSequence.toString());
            return parseInt >= 3 && parseInt <= 3600;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        int slideShowInterval = GalleryPreferences.SlideShow.getSlideShowInterval();
        final EditText editText = (EditText) LayoutInflater.from(activity).inflate(R.layout.edit_text_dialog, (ViewGroup) null, false);
        editText.setText(String.valueOf(slideShowInterval));
        editText.selectAll();
        editText.setRawInputType(2);
        this.mDialog = new AlertDialog.Builder(activity).setView(editText).setTitle(R.string.slideshow_interval_dialog_title).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
        this.mDialog.getWindow().setSoftInputMode(4);
        this.mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                SlideshowIntervalDialogFragment.this.mDialog.getButton(-1).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!SlideshowIntervalDialogFragment.this.isTextValid(editText.getText())) {
                            ToastUtils.makeText((Context) SlideshowIntervalDialogFragment.this.getActivity(), (int) R.string.slideshow_interval_invalid);
                            return;
                        }
                        GalleryPreferences.SlideShow.setSlideShowInterval(Integer.parseInt(editText.getText().toString()));
                        if (SlideshowIntervalDialogFragment.this.mListener != null) {
                            SlideshowIntervalDialogFragment.this.mListener.onComplete();
                        }
                        SlideshowIntervalDialogFragment.this.mDialog.dismiss();
                    }
                });
            }
        });
        return this.mDialog;
    }

    public void setCompleteListener(CompleteListener completeListener) {
        this.mListener = completeListener;
    }
}
