package com.miui.gallery.editor.photo.app.mosaic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.assistant.jni.filter.BaiduSceneResult;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.OperationUpdateListener;
import com.miui.gallery.editor.photo.app.menu.MosaicView;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractMosaicFragment;
import com.miui.gallery.editor.photo.core.common.model.MosaicData;
import com.miui.gallery.editor.photo.widgets.PaintSizePopupWindow;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class MosaicMenuFragment extends MenuFragment<AbstractMosaicFragment, SdkProvider<MosaicData, AbstractMosaicFragment>> {
    private View.OnClickListener mEraserClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            MosaicMenuFragment.this.mEraserView.setSelected(true);
            MosaicMenuFragment.this.mEraserViewBg.setSelected(true);
            MosaicMenuFragment.this.mMosaicAdapter.setSelection(-1);
            ((AbstractMosaicFragment) MosaicMenuFragment.this.getRenderFragment()).setMosaicData(MosaicMenuFragment.this.mEraserData);
        }
    };
    /* access modifiers changed from: private */
    public MosaicData mEraserData;
    /* access modifiers changed from: private */
    public ImageView mEraserView;
    /* access modifiers changed from: private */
    public ImageView mEraserViewBg;
    /* access modifiers changed from: private */
    public int mMenuHeight;
    /* access modifiers changed from: private */
    public MosaicAdapter mMosaicAdapter;
    private List<MosaicData> mMosaicDataList;
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            ScrollLinearLayoutManager.onItemClick(recyclerView, i);
            MosaicMenuFragment.this.mEraserView.setSelected(false);
            MosaicMenuFragment.this.mEraserViewBg.setSelected(false);
            MosaicMenuFragment.this.setSelection(i);
            return true;
        }
    };
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int round = Math.round(((float) (i * BaiduSceneResult.ID_CARD)) / 100.0f) + 35;
            MosaicMenuFragment.this.mPaintPopupWindow.setPaintSize(round);
            ((AbstractMosaicFragment) MosaicMenuFragment.this.getRenderFragment()).setMosaicPaintSize(round);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            MosaicMenuFragment.this.mPaintPopupWindow.showAtLocation(MosaicMenuFragment.this.mRecyclerView, 17, 0, (-MosaicMenuFragment.this.mMenuHeight) / 2);
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            MosaicMenuFragment.this.mPaintPopupWindow.dismiss();
        }
    };
    private View.OnClickListener mOperationClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.operation_revert:
                    ((AbstractMosaicFragment) MosaicMenuFragment.this.getRenderFragment()).doRevert();
                    return;
                case R.id.operation_revoke:
                    ((AbstractMosaicFragment) MosaicMenuFragment.this.getRenderFragment()).doRevoke();
                    return;
                default:
                    return;
            }
        }
    };
    private View mOperationPanel;
    private OperationUpdateListener mOperationUpdateListener = new OperationUpdateListener() {
        public void onOperationUpdate() {
            MosaicMenuFragment.this.refreshOperationPanel();
        }
    };
    /* access modifiers changed from: private */
    public PaintSizePopupWindow mPaintPopupWindow;
    /* access modifiers changed from: private */
    public SimpleRecyclerView mRecyclerView;
    private View mRevert;
    private View mRevoke;
    private SeekBar mSeekBar;
    private TextView mTitleView;

    public MosaicMenuFragment() {
        super(Effect.MOSAIC);
    }

    /* access modifiers changed from: private */
    public void refreshOperationPanel() {
        boolean canRevoke = ((AbstractMosaicFragment) getRenderFragment()).canRevoke();
        boolean canRevert = ((AbstractMosaicFragment) getRenderFragment()).canRevert();
        if (canRevoke || canRevert) {
            this.mTitleView.setVisibility(8);
            this.mOperationPanel.setVisibility(0);
        } else {
            this.mTitleView.setVisibility(0);
            this.mOperationPanel.setVisibility(8);
        }
        this.mRevoke.setEnabled(canRevoke);
        this.mRevert.setEnabled(canRevert);
    }

    /* access modifiers changed from: private */
    public void setSelection(int i) {
        this.mMosaicAdapter.setSelection(i);
        ((AbstractMosaicFragment) getRenderFragment()).setMosaicData(this.mMosaicDataList.get(i));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new MosaicView(viewGroup.getContext());
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mPaintPopupWindow = new PaintSizePopupWindow(getActivity());
        this.mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        this.mSeekBar.setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        this.mMosaicDataList = new ArrayList(this.mSdkProvider.list());
        this.mEraserData = this.mMosaicDataList.remove(0);
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.preview_list);
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(getActivity());
        scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(getActivity()));
        scrollLinearLayoutManager.setOrientation(0);
        this.mRecyclerView.setLayoutManager(scrollLinearLayoutManager);
        this.mRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(0, getResources().getDimensionPixelSize(R.dimen.editor_menu_mosaic_item_decoration), getResources().getDimensionPixelSize(R.dimen.editor_menu_mosaic_item_decoration), 0, 0, 0));
        this.mMosaicAdapter = new MosaicAdapter(getActivity(), this.mMosaicDataList);
        this.mRecyclerView.setAdapter(this.mMosaicAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mMosaicAdapter.setOnItemClickListener(this.mOnItemClickListener);
        this.mEraserView = (ImageView) view.findViewById(R.id.eraser);
        this.mEraserViewBg = (ImageView) view.findViewById(R.id.eraser_background);
        ImageLoader.getInstance().displayImage(this.mEraserData.iconPath, this.mEraserView);
        this.mEraserView.setOnClickListener(this.mEraserClickListener);
        this.mTitleView = (TextView) view.findViewById(R.id.title);
        this.mTitleView.setText(R.string.photo_editor_mosaic);
        setSelection(0);
        SeekBar seekBar = this.mSeekBar;
        seekBar.setProgress(seekBar.getMax() / 2);
        this.mOperationPanel = view.findViewById(R.id.operation_panel);
        this.mRevoke = view.findViewById(R.id.operation_revoke);
        this.mRevert = view.findViewById(R.id.operation_revert);
        this.mRevoke.setOnClickListener(this.mOperationClickListener);
        this.mRevert.setOnClickListener(this.mOperationClickListener);
        ((AbstractMosaicFragment) getRenderFragment()).setOperationUpdateListener(this.mOperationUpdateListener);
        this.mMenuHeight = getResources().getDimensionPixelSize(R.dimen.photo_editor_menu_panel_height);
    }
}
