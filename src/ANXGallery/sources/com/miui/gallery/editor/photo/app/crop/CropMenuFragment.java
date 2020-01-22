package com.miui.gallery.editor.photo.app.crop;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.menu.CropView;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractCropFragment;
import com.miui.gallery.editor.photo.core.common.model.CropData;
import com.miui.gallery.editor.photo.widgets.TouchTransLinearLayout;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.editor.photo.widgets.seekbar.BiDirectionSeekBar;
import com.miui.gallery.editor.photo.widgets.seekbar.BubbleIndicator;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import miui.gallery.support.linearmotor.LinearMotorHelper;

public class CropMenuFragment extends MenuFragment<AbstractCropFragment, SdkProvider<CropData, AbstractCropFragment>> {
    /* access modifiers changed from: private */
    public CropAdapter mAdapter;
    /* access modifiers changed from: private */
    public AutoCropData mAutoCropData = new AutoCropData();
    private View.OnClickListener mAutoCropListener = new View.OnClickListener() {
        public void onClick(View view) {
            CropMenuFragment.this.doAutoCrop(true);
            GallerySamplingStatHelper.recordCountEvent("photo_editor", "crop_auto_click", new HashMap());
        }
    };
    /* access modifiers changed from: private */
    public BubbleIndicator mBubbleIndicator;
    /* access modifiers changed from: private */
    public View.OnClickListener mClearListener = new View.OnClickListener() {
        public void onClick(View view) {
            ((AbstractCropFragment) CropMenuFragment.this.getRenderFragment()).restore();
            GallerySamplingStatHelper.recordCountEvent("photo_editor", "crop_restore_click", new HashMap());
        }
    };
    /* access modifiers changed from: private */
    public int mCurrentProgress = 0;
    /* access modifiers changed from: private */
    public List<CropData> mDataList;
    private BubbleIndicator.Callback<TextView> mIndicatorCallback = new BubbleIndicator.Callback<TextView>() {
        public void updateProgress(TextView textView, int i) {
            textView.setText(textView.getResources().getString(R.string.photo_editor_crop_rotation_format, new Object[]{Integer.valueOf(CropMenuFragment.this.getDegreeShow(i))}));
        }
    };
    private AbstractCropFragment.OnCropStateChangedListener mOnCropStateChangedListener = new AbstractCropFragment.OnCropStateChangedListener() {
        public void changeRotationState(boolean z) {
            CropMenuFragment.this.mSeekBar.setEnabled(z);
        }

        public void onCropped() {
            CropMenuFragment.this.mTitle.setText(R.string.photo_editor_crop_reset);
            CropMenuFragment.this.mTitle.setTextColor(CropMenuFragment.this.getResources().getColor(R.color.photo_editor_highlight_color));
            CropMenuFragment.this.mTitle.setOnClickListener(CropMenuFragment.this.mClearListener);
        }

        public void onRestored() {
            CropMenuFragment.this.setNormalTitle();
            CropMenuFragment.this.mSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
            CropMenuFragment.this.mSeekBar.setCurrentValue(0);
            CropMenuFragment.this.mSeekBar.setOnSeekBarChangeListener(CropMenuFragment.this.mBubbleIndicator);
            CropMenuFragment.this.mAdapter.setSelection(2);
        }
    };
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            CropData cropData = (CropData) CropMenuFragment.this.mDataList.get(i);
            if (cropData == CropData.MIRROR) {
                ((AbstractCropFragment) CropMenuFragment.this.getRenderFragment()).doMirror();
            } else if (cropData == CropData.ROTATE) {
                ((AbstractCropFragment) CropMenuFragment.this.getRenderFragment()).doRotate();
            } else if (cropData instanceof CropData.AspectRatio) {
                CropMenuFragment.this.mAdapter.setSelection(i);
                ((AbstractCropFragment) CropMenuFragment.this.getRenderFragment()).setAspectRatio((CropData.AspectRatio) CropMenuFragment.this.mAdapter.getSelectedItem());
            }
            ScrollLinearLayoutManager.onItemClick(recyclerView, i);
            return true;
        }
    };
    private SimpleRecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public BiDirectionSeekBar mSeekBar;
    private SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        boolean mStart;

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (!this.mStart || Math.abs(i - CropMenuFragment.this.mCurrentProgress) >= 6) {
                this.mStart = false;
                int unused = CropMenuFragment.this.mCurrentProgress = i;
                ((AbstractCropFragment) CropMenuFragment.this.getRenderFragment()).tuning(CropMenuFragment.this.getDegree(i));
            }
            if (i == 0) {
                LinearMotorHelper.performHapticFeedback(CropMenuFragment.this.getActivity(), LinearMotorHelper.EFFECT_KEY_MESH_NORMAL);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            ((AbstractCropFragment) CropMenuFragment.this.getRenderFragment()).prepareTuning();
            this.mStart = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            ((AbstractCropFragment) CropMenuFragment.this.getRenderFragment()).finishTuning();
        }
    };
    /* access modifiers changed from: private */
    public TextView mTitle;

    private class AutoCropTask extends AsyncTask<Void, Void, Void> {
        private AutoCropTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            AutoCropUtils.getAutoCropData(CropMenuFragment.this.getPreview(), CropMenuFragment.this.mAutoCropData);
            HashMap hashMap = new HashMap();
            hashMap.put("result", String.valueOf(CropMenuFragment.this.mAutoCropData.rotationResult));
            GallerySamplingStatHelper.recordCountEvent("photo_editor", "crop_auto_rotation_result", hashMap);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            if (!CropMenuFragment.this.isAdded() || CropMenuFragment.this.isRemoving()) {
                Log.w("CropMenuFragment", "AutoCrop fragment isRemoved");
                return;
            }
            CropMenuFragment.this.mTitle.setVisibility(0);
            CropMenuFragment.this.setNormalTitle();
        }
    }

    public CropMenuFragment() {
        super(Effect.CROP);
    }

    /* access modifiers changed from: private */
    public void doAutoCrop(final boolean z) {
        this.mTitle.setOnClickListener((View.OnClickListener) null);
        ((AbstractCropFragment) getRenderFragment()).autoCrop(this.mAutoCropData.getDegree());
        if (z) {
            this.mTitle.setText(R.string.photo_editor_crop_reset);
            this.mTitle.setOnClickListener(this.mClearListener);
        }
        this.mTitle.postDelayed(new Runnable() {
            public void run() {
                if (!z) {
                    CropMenuFragment.this.mTitle.setText(R.string.photo_editor_crop_reset);
                    CropMenuFragment.this.mTitle.setOnClickListener(CropMenuFragment.this.mClearListener);
                }
                CropMenuFragment.this.mSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
                BiDirectionSeekBar access$500 = CropMenuFragment.this.mSeekBar;
                CropMenuFragment cropMenuFragment = CropMenuFragment.this;
                access$500.setCurrentValue(cropMenuFragment.getProgressFromDegree(cropMenuFragment.mAutoCropData.getDegree()));
                CropMenuFragment.this.mSeekBar.setOnSeekBarChangeListener(CropMenuFragment.this.mBubbleIndicator);
            }
        }, 400);
    }

    /* access modifiers changed from: private */
    public float getDegree(int i) {
        return (((float) i) / 90.0f) * 45.0f;
    }

    /* access modifiers changed from: private */
    public int getDegreeShow(int i) {
        if (i == 0) {
            return 0;
        }
        int round = Math.round(getDegree(i));
        return round == 0 ? i > 0 ? 1 : -1 : round;
    }

    /* access modifiers changed from: private */
    public int getProgressFromDegree(float f) {
        int round = Math.round((90.0f * f) / 45.0f);
        return round == 0 ? f > 0.0f ? 1 : -1 : round;
    }

    /* access modifiers changed from: private */
    public void setNormalTitle() {
        if (this.mAutoCropData.canAutoRotation()) {
            this.mTitle.setText(R.string.photo_editor_crop_auto);
            this.mTitle.setTextColor(getResources().getColor(R.color.photo_editor_highlight_color));
            this.mTitle.setOnClickListener(this.mAutoCropListener);
            return;
        }
        this.mTitle.setText(R.string.photo_editor_crop_and_rotate);
        this.mTitle.setTextColor(getResources().getColor(R.color.photo_editor_menu_bottom_bar_title_color));
        this.mTitle.setOnClickListener((View.OnClickListener) null);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDataList = new ArrayList();
        this.mDataList.add(CropData.MIRROR);
        this.mDataList.add(CropData.ROTATE);
        this.mDataList.addAll(new ArrayList(this.mSdkProvider.list()));
        new AutoCropTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new CropView(viewGroup.getContext());
    }

    public void onDestroyView() {
        super.onDestroyView();
        ((AbstractCropFragment) getRenderFragment()).setOnCropChangedListener((AbstractCropFragment.OnCropStateChangedListener) null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((AbstractCropFragment) getRenderFragment()).setOnCropChangedListener(this.mOnCropStateChangedListener);
        ((TouchTransLinearLayout) view.findViewById(R.id.top_panel)).setOnTouchEvent(new TouchTransLinearLayout.OnTouchEvent() {
            public boolean onTouchEvent(MotionEvent motionEvent) {
                return ((AbstractCropFragment) CropMenuFragment.this.getRenderFragment()).doTouchEvent(motionEvent);
            }
        });
        this.mSeekBar = (BiDirectionSeekBar) view.findViewById(R.id.seekbar);
        this.mSeekBar.setMaxValue(90);
        this.mSeekBar.setCurrentValue(0);
        this.mBubbleIndicator = new BubbleIndicator((TextView) View.inflate(getActivity(), R.layout.seekbar_bubble_indicator_text, (ViewGroup) null), getActivity().getResources().getDimensionPixelSize(R.dimen.photo_editor_bubble_indicator_offset), this.mIndicatorCallback, this.mSeekBarChangeListener);
        this.mSeekBar.setOnSeekBarChangeListener(this.mBubbleIndicator);
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.recycler_view);
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(getActivity());
        scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(getActivity()));
        scrollLinearLayoutManager.setOrientation(0);
        this.mRecyclerView.setLayoutManager(scrollLinearLayoutManager);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mAdapter = new CropAdapter(getActivity(), this.mDataList, new Selectable.Selector(getResources().getColor(R.color.photo_editor_highlight_color)));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this.mOnItemClickListener);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mTitle.setVisibility(4);
    }
}
