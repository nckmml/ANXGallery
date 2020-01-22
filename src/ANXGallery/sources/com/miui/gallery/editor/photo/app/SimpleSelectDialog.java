package com.miui.gallery.editor.photo.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.util.ArrayList;
import java.util.List;
import miui.app.AlertDialog;

public class SimpleSelectDialog extends GalleryDialogFragment {
    private AlertDialog mDialog;
    /* access modifiers changed from: private */
    public OnOperationSelectedListener mOnOperationSelectedListener;
    /* access modifiers changed from: private */
    public List<SelectModel> mSelectModels;

    public interface OnOperationSelectedListener {
        boolean onOperationSelected(int i);
    }

    public static class SelectModel {
        public int creationId;
        public int iconResourceId;
        public String informationString;
        public boolean isRemainWhenClick = false;
        public boolean showIcon;
        public int titleResourceId;

        public SelectModel(int i, int i2) {
            this.creationId = i;
            this.titleResourceId = i2;
        }
    }

    private class SimpleOptionAdapter extends BaseAdapter implements View.OnClickListener {
        private List<SelectModel> mCreationModelList;

        private class ViewHolder {
            ImageView icon;
            TextView information;
            int position;
            TextView title;

            private ViewHolder() {
            }

            public void bindView(SelectModel selectModel, int i) {
                if (selectModel.showIcon) {
                    this.icon.setImageResource(selectModel.iconResourceId);
                }
                this.title.setText(selectModel.titleResourceId);
                if (!TextUtils.isEmpty(selectModel.informationString)) {
                    this.information.setVisibility(0);
                    this.information.setText(selectModel.informationString);
                } else {
                    this.information.setVisibility(8);
                    this.information.setText("");
                }
                this.position = i;
            }
        }

        public SimpleOptionAdapter(List<SelectModel> list) {
            this.mCreationModelList = list;
        }

        public int getCount() {
            return this.mCreationModelList.size();
        }

        public SelectModel getItem(int i) {
            if (i < 0 || i >= this.mCreationModelList.size()) {
                return null;
            }
            return this.mCreationModelList.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_select_dialog_item, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.icon = (ImageView) view.findViewById(R.id.creation_icon);
                viewHolder.title = (TextView) view.findViewById(R.id.creation_text);
                viewHolder.information = (TextView) view.findViewById(R.id.information);
                view.setTag(viewHolder);
                view.setOnClickListener(this);
            }
            ((ViewHolder) view.getTag()).bindView(getItem(i), i);
            return view;
        }

        public void onClick(View view) {
            int i = ((ViewHolder) view.getTag()).position;
            if (i < SimpleSelectDialog.this.mSelectModels.size() && SimpleSelectDialog.this.mOnOperationSelectedListener != null) {
                Log.d("SimpleSelectDialog", "Creation select : %d", (Object) Integer.valueOf(i));
                SelectModel selectModel = (SelectModel) SimpleSelectDialog.this.mSelectModels.get(i);
                if (!selectModel.isRemainWhenClick && SimpleSelectDialog.this.mOnOperationSelectedListener.onOperationSelected(selectModel.creationId)) {
                    SimpleSelectDialog.this.dismissSafely();
                }
            }
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setAdapter(new SimpleOptionAdapter(this.mSelectModels), (DialogInterface.OnClickListener) null);
        this.mDialog = builder.create();
        return this.mDialog;
    }

    public void setCreationModels(int... iArr) {
        this.mSelectModels = new ArrayList(iArr.length);
        for (int i : iArr) {
            this.mSelectModels.add(new SelectModel(i, i));
        }
    }

    public void setOnOperationSelectedListener(OnOperationSelectedListener onOperationSelectedListener) {
        this.mOnOperationSelectedListener = onOperationSelectedListener;
    }
}
