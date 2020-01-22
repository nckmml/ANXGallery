package com.miui.gallery.editor.photo.screen.mosaic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.assistant.jni.filter.BaiduSceneResult;
import com.miui.gallery.editor.photo.screen.base.ScreenBaseMenuFragment;
import com.miui.gallery.editor.photo.screen.core.ScreenProviderManager;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicData;
import com.miui.gallery.editor.photo.screen.stat.ScreenEditorStatUtils;
import com.miui.gallery.editor.photo.widgets.PaintSizePopupWindow;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ScreenMosaicMenuFragment extends ScreenBaseMenuFragment<IScreenMosaicOperation> {
    /* access modifiers changed from: private */
    public int mMenuHeight;
    private MosaicAdapter mMosaicAdapter;
    private List<MosaicData> mMosaicDataList;
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public final boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            return ScreenMosaicMenuFragment.this.lambda$new$94$ScreenMosaicMenuFragment(recyclerView, view, i);
        }
    };
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int round = Math.round(((float) (i * BaiduSceneResult.ID_CARD)) / 100.0f) + 35;
            ScreenMosaicMenuFragment.this.mPaintPopupWindow.setPaintSize(round);
            ((IScreenMosaicOperation) ScreenMosaicMenuFragment.this.mScreenOperation).setMosaicPaintSize(round);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            ScreenMosaicMenuFragment.this.mPaintPopupWindow.showAtLocation(ScreenMosaicMenuFragment.this.mRecyclerView, 17, 0, (-ScreenMosaicMenuFragment.this.mMenuHeight) / 2);
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            ScreenMosaicMenuFragment.this.mPaintPopupWindow.dismiss();
            ScreenEditorStatUtils.statMosaicSizeChoose(seekBar.getProgress());
        }
    };
    /* access modifiers changed from: private */
    public PaintSizePopupWindow mPaintPopupWindow;
    /* access modifiers changed from: private */
    public SimpleRecyclerView mRecyclerView;
    private SeekBar mSeekBar;

    private void setSelection(int i) {
        this.mMosaicAdapter.setSelection(i);
        ((IScreenMosaicOperation) this.mScreenOperation).setMosaicData(this.mMosaicDataList.get(i), i);
    }

    /* access modifiers changed from: protected */
    public Class<IScreenMosaicOperation> getScreenOperationClass() {
        return IScreenMosaicOperation.class;
    }

    public /* synthetic */ boolean lambda$new$94$ScreenMosaicMenuFragment(RecyclerView recyclerView, View view, int i) {
        ScrollLinearLayoutManager.onItemClick(recyclerView, i);
        setSelection(i);
        ScreenEditorStatUtils.statMosaicMenuItemClick(i);
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.screen_mosaic_menu_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mPaintPopupWindow = new PaintSizePopupWindow(getActivity());
        this.mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        SeekBar seekBar = this.mSeekBar;
        seekBar.setProgress(seekBar.getMax() / 2);
        this.mSeekBar.setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        this.mMosaicDataList = new ArrayList(((ScreenMosaicProvider) ScreenProviderManager.INSTANCE.getProvider(ScreenMosaicProvider.class)).list());
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.preview_list);
        this.mMosaicAdapter = new MosaicAdapter(getActivity(), this.mMosaicDataList, ((IScreenMosaicOperation) this.mScreenOperation).getCurrentMenuItemIndex());
        this.mRecyclerView.setAdapter(this.mMosaicAdapter);
        this.mMosaicAdapter.setOnItemClickListener(this.mOnItemClickListener);
        int dimension = (int) getResources().getDimension(R.dimen.mosaic_menu_item_start_margin);
        this.mRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(dimension, dimension, (int) getResources().getDimension(R.dimen.mosaic_menu_item__hInterval), 0, 0, 0));
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mMenuHeight = getResources().getDimensionPixelSize(R.dimen.photo_editor_menu_panel_height);
    }
}
