package com.miui.gallery.editor.photo.core.imports.doodle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.OperationUpdateListener;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractDoodleFragment;
import com.miui.gallery.editor.photo.core.common.model.DoodleData;
import com.miui.gallery.editor.photo.core.imports.doodle.DoodleEditorView;
import java.util.ArrayList;
import java.util.List;

public class DoodleFragment extends AbstractDoodleFragment {
    private int mCurrentColor = -16777216;
    private DoodleEditorView.DoodleCallback mDoodleCallback = new DoodleEditorView.DoodleCallback() {
        public void onDoodleGenerate(String str) {
            DoodleFragment.this.mStats.add(str);
        }
    };
    private DoodleEditorView mDoodleEditorView;
    private OperationUpdateListener mOperationUpdateListener;
    /* access modifiers changed from: private */
    public List<String> mStats = new ArrayList();

    public boolean canRevert() {
        return this.mDoodleEditorView.canRevert();
    }

    public boolean canRevoke() {
        return this.mDoodleEditorView.canRevoke();
    }

    public void clear() {
        this.mDoodleEditorView.onClear();
    }

    public void doRevert() {
        this.mDoodleEditorView.doRevert();
    }

    public void doRevoke() {
        this.mDoodleEditorView.doRevoke();
    }

    public boolean isEmpty() {
        return this.mDoodleEditorView.isEmpty();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.editor_view_container, viewGroup, false);
        this.mDoodleEditorView = new DoodleEditorView(getActivity());
        this.mDoodleEditorView.setBitmap(getBitmap());
        this.mDoodleEditorView.setColor(this.mCurrentColor);
        this.mDoodleEditorView.setDoodleCallback(this.mDoodleCallback);
        viewGroup2.addView(this.mDoodleEditorView, -1, -1);
        OperationUpdateListener operationUpdateListener = this.mOperationUpdateListener;
        if (operationUpdateListener != null) {
            this.mDoodleEditorView.setOperationUpdateListener(operationUpdateListener);
            this.mOperationUpdateListener = null;
        }
        return viewGroup2;
    }

    /* access modifiers changed from: protected */
    public RenderData onExport() {
        return new DoodleRenderData(this.mDoodleEditorView.export());
    }

    /* access modifiers changed from: protected */
    public List<String> onSample() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mStats);
        return arrayList;
    }

    public void setColor(int i) {
        this.mCurrentColor = i;
        DoodleEditorView doodleEditorView = this.mDoodleEditorView;
        if (doodleEditorView != null) {
            doodleEditorView.setColor(i);
        }
    }

    public void setDoodleData(DoodleData doodleData) {
        DoodleEditorView doodleEditorView = this.mDoodleEditorView;
        if (doodleEditorView != null) {
            doodleEditorView.setCurrentDoodleItem(((DoodleConfig) doodleData).getDoodleItem());
            this.mDoodleEditorView.clearActivation();
        }
    }

    public void setOperationUpdateListener(OperationUpdateListener operationUpdateListener) {
        DoodleEditorView doodleEditorView = this.mDoodleEditorView;
        if (doodleEditorView != null) {
            doodleEditorView.setOperationUpdateListener(operationUpdateListener);
        } else {
            this.mOperationUpdateListener = operationUpdateListener;
        }
    }

    public void setPaintSize(float f) {
        this.mDoodleEditorView.setPaintSize(f);
    }
}
