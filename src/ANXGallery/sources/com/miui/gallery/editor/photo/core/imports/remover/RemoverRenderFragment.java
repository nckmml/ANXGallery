package com.miui.gallery.editor.photo.core.imports.remover;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.android.internal.WindowCompat;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.RenderRecord;
import com.miui.gallery.editor.photo.app.remover.Inpaint;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractRemoverFragment;
import com.miui.gallery.editor.photo.core.common.model.RemoverData;
import com.miui.gallery.editor.photo.core.imports.remover.RemoverGestureView;
import com.miui.gallery.editor.photo.widgets.MenuUpdateListener;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class RemoverRenderFragment extends AbstractRemoverFragment implements RenderRecord {
    private RemoverGestureView.RemoverCallback mCallback = new RemoverGestureView.RemoverCallback() {
        public void onRemoveStart() {
            RemoverRenderFragment.this.mRemoverGestureView.setIsProcessing(true);
            if (RemoverRenderFragment.this.mMenuUpdateListener != null) {
                RemoverRenderFragment.this.mMenuUpdateListener.onMenuEnabled(false);
            }
            RemoverRenderFragment.this.mHandler.postDelayed(RemoverRenderFragment.this.mProgressRunnable, 500);
        }

        public void removeDone(RemoverPaintData removerPaintData) {
            RemoverRenderFragment.this.mHandler.removeCallbacks(RemoverRenderFragment.this.mProgressRunnable);
            RemoverRenderFragment.this.mProgressBar.setVisibility(8);
            if (removerPaintData != null) {
                RemoverPaintData unused = RemoverRenderFragment.this.mRemoverPaintData = removerPaintData;
                RemoverRenderFragment.this.recordCurrent();
                if (RemoverRenderFragment.this.mMenuUpdateListener != null) {
                    RemoverRenderFragment.this.mMenuUpdateListener.onMenuUpdated(RemoverRenderFragment.this.mRecordCurr != RemoverRenderFragment.this.mRecordHead, false);
                }
            }
            RemoverRenderFragment.this.mRemoverGestureView.setIsProcessing(false);
            if (RemoverRenderFragment.this.mMenuUpdateListener != null) {
                RemoverRenderFragment.this.mMenuUpdateListener.onMenuEnabled(true);
            }
            RemoverRenderFragment.this.mCompareButton.setVisibility(0);
        }
    };
    private boolean mCanBackToOrigin = true;
    /* access modifiers changed from: private */
    public Button mCompareButton;
    private ListIterator<RemoverPaintData> mCurrIterator = this.mRemoverPaintDataList.listIterator();
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(new CustomCallback());
    /* access modifiers changed from: private */
    public MenuUpdateListener mMenuUpdateListener;
    private float mPaintSize;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    /* access modifiers changed from: private */
    public ProgressRunnable mProgressRunnable = new ProgressRunnable();
    /* access modifiers changed from: private */
    public int mRecordCurr = 0;
    /* access modifiers changed from: private */
    public int mRecordHead = 0;
    private int mRecordListTail;
    private int mRecordTail;
    /* access modifiers changed from: private */
    public RemoverGestureView mRemoverGestureView;
    /* access modifiers changed from: private */
    public RemoverPaintData mRemoverPaintData;
    private List<RemoverPaintData> mRemoverPaintDataList = new ArrayList();

    private class CustomCallback implements Handler.Callback {
        private CustomCallback() {
        }

        public boolean handleMessage(Message message) {
            return true;
        }
    }

    private class ProgressRunnable implements Runnable {
        private ProgressRunnable() {
        }

        public void run() {
            RemoverRenderFragment.this.mProgressBar.setVisibility(0);
        }
    }

    public void clear() {
        this.mRemoverGestureView.onClear();
    }

    public boolean isEmpty() {
        List<RemoverPaintData> list = this.mRemoverPaintDataList;
        return list == null || list.isEmpty();
    }

    public /* synthetic */ boolean lambda$onCreateView$101$RemoverRenderFragment(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mRemoverGestureView.drawOrigin(true);
            this.mCompareButton.setSelected(true);
            HashMap hashMap = new HashMap();
            hashMap.put("page", this.mEffect.name());
            GallerySamplingStatHelper.recordCountEvent("photo_editor", "compare_button_touch", hashMap);
        } else if (1 == motionEvent.getAction() || 3 == motionEvent.getAction()) {
            this.mRemoverGestureView.drawOrigin(false);
            this.mCompareButton.setSelected(false);
        }
        return true;
    }

    public void nextRecord() {
        boolean z = true;
        this.mRecordCurr = (this.mRecordCurr + 1) % 10;
        if (this.mCurrIterator.hasNext()) {
            this.mCurrIterator.next();
            this.mRecordListTail = this.mCurrIterator.previousIndex();
        }
        this.mRemoverGestureView.renderNextBuffer();
        MenuUpdateListener menuUpdateListener = this.mMenuUpdateListener;
        if (menuUpdateListener != null) {
            boolean z2 = this.mRecordCurr != this.mRecordHead;
            if (this.mRecordCurr == this.mRecordTail) {
                z = false;
            }
            menuUpdateListener.onMenuUpdated(z2, z);
        }
        this.mCompareButton.setVisibility(0);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Inpaint.initialize();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        int i3 = 0;
        FrameLayout frameLayout = (FrameLayout) layoutInflater.inflate(R.layout.remover_preview_container, viewGroup, false);
        this.mRemoverGestureView = (RemoverGestureView) frameLayout.findViewById(R.id.remover_gesture_view);
        this.mProgressBar = (ProgressBar) frameLayout.findViewById(R.id.progress);
        this.mCompareButton = (Button) frameLayout.findViewById(R.id.compare_btn);
        this.mRemoverGestureView.setBitmap(getBitmap());
        this.mRemoverGestureView.setRemoverCallback(this.mCallback);
        this.mRemoverGestureView.setStrokeSize((int) this.mPaintSize);
        this.mCompareButton.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return RemoverRenderFragment.this.lambda$onCreateView$101$RemoverRenderFragment(view, motionEvent);
            }
        });
        this.mCanBackToOrigin = true;
        if (WindowCompat.isNotch(getActivity())) {
            i3 = WindowCompat.getTopNotchHeight(getActivity());
        }
        if (!MiscUtil.checkNavigationBarShow(getActivity(), getActivity().getWindow())) {
            if (isNear3V4()) {
                i = getResources().getDimensionPixelSize(R.dimen.editor_menu_remove_compare_btn_margin_top_3V4);
                i2 = getResources().getDimensionPixelSize(R.dimen.editor_menu_remove_compare_btn_margin_end_3V4);
            } else {
                i = getResources().getDimensionPixelSize(R.dimen.editor_menu_remove_compare_btn_margin_top_9V16);
                i2 = getResources().getDimensionPixelSize(R.dimen.editor_menu_remove_compare_btn_margin_end_9V16);
            }
        } else if (isNear3V4()) {
            i = getResources().getDimensionPixelSize(R.dimen.editor_menu_remove_compare_btn_margin_top_has_nav_bar_3V4);
            i2 = getResources().getDimensionPixelSize(R.dimen.editor_menu_remove_compare_btn_margin_end_has_nav_bar_3V4);
        } else {
            i = getResources().getDimensionPixelSize(R.dimen.editor_menu_remove_compare_btn_margin_top_has_nav_bar_9V16);
            i2 = getResources().getDimensionPixelSize(R.dimen.editor_menu_remove_compare_btn_margin_end_has_nav_bar_9V16);
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mCompareButton.getLayoutParams();
        layoutParams.topMargin = i + i3;
        layoutParams.setMarginEnd(i2);
        this.mCompareButton.setLayoutParams(layoutParams);
        return frameLayout;
    }

    /* access modifiers changed from: protected */
    public RenderData onExport() {
        ArrayList arrayList = new ArrayList();
        List<RemoverPaintData> list = this.mRemoverPaintDataList;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i <= this.mRecordListTail; i++) {
                arrayList.add(this.mRemoverPaintDataList.get(i));
            }
        }
        return new RemoverRenderData((List<RemoverPaintData>) arrayList);
    }

    /* access modifiers changed from: protected */
    public List<String> onSample() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.valueOf(this.mRecordListTail + 1));
        return arrayList;
    }

    public void previousRecord() {
        boolean z = true;
        this.mRecordCurr = (this.mRecordCurr - 1) % 10;
        int i = this.mRecordCurr;
        if (i < 0) {
            this.mRecordCurr = i + 10;
        }
        Log.d("RemoverRenderFragment", "previousRecord  mRecordCurr : %d", (Object) Integer.valueOf(this.mRecordCurr));
        if (this.mCurrIterator.hasPrevious()) {
            this.mCurrIterator.previous();
            this.mRecordListTail = this.mCurrIterator.previousIndex();
        }
        this.mRemoverGestureView.renderPreviousBuffer();
        MenuUpdateListener menuUpdateListener = this.mMenuUpdateListener;
        if (menuUpdateListener != null) {
            boolean z2 = this.mRecordCurr != this.mRecordHead;
            if (this.mRecordCurr == this.mRecordTail) {
                z = false;
            }
            menuUpdateListener.onMenuUpdated(z2, z);
        }
        if (this.mRecordCurr == 0 && this.mCanBackToOrigin) {
            this.mCompareButton.setVisibility(8);
        }
    }

    public void recordCurrent() {
        if (this.mRecordCurr + 1 == 10) {
            this.mCanBackToOrigin = false;
        }
        this.mRecordCurr = (this.mRecordCurr + 1) % 10;
        int i = this.mRecordCurr;
        this.mRecordTail = i;
        Log.d("RemoverRenderFragment", "recordCurrent  mRecordCurr : %d", (Object) Integer.valueOf(i));
        int i2 = this.mRecordCurr;
        if (i2 == this.mRecordHead) {
            this.mRecordHead = (i2 + 1) % 10;
        }
        if (this.mCurrIterator.hasNext()) {
            this.mCurrIterator.next();
            this.mCurrIterator.set(this.mRemoverPaintData);
            this.mRecordListTail = this.mCurrIterator.previousIndex();
        } else {
            this.mCurrIterator.add(this.mRemoverPaintData);
            this.mRecordListTail = this.mCurrIterator.previousIndex();
        }
        this.mRemoverGestureView.writeRecordFile();
    }

    public void setMenuUpdateListener(MenuUpdateListener menuUpdateListener) {
        this.mMenuUpdateListener = menuUpdateListener;
    }

    public void setPaintSize(float f) {
        this.mPaintSize = f;
        RemoverGestureView removerGestureView = this.mRemoverGestureView;
        if (removerGestureView != null) {
            removerGestureView.setStrokeSize((int) f);
        }
    }

    public void setRemoverData(RemoverData removerData) {
        if (removerData != null) {
            if (removerData.mType == 0) {
                this.mRemoverGestureView.setElementType(RemoverGestureView.ElementType.FREE);
            } else if (removerData.mType == 1) {
                this.mRemoverGestureView.setElementType(RemoverGestureView.ElementType.LINE);
            }
        }
    }
}
