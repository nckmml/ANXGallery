package com.miui.gallery.editor.photo.app.remover;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.menu.RemoverView;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractRemoverFragment;
import com.miui.gallery.editor.photo.core.common.model.RemoverData;
import com.miui.gallery.editor.photo.core.imports.remover.RemoverRenderFragment;
import com.miui.gallery.editor.photo.widgets.CommonBottomMenuWithUndo;
import com.miui.gallery.editor.photo.widgets.PaintSizePopupWindow;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.editor.photo.widgets.seekbar.BasicSeekBar;
import com.miui.gallery.listener.SingleClickListener;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import miui.app.ProgressDialog;

public class RemoverMenuFragment extends MenuFragment<AbstractRemoverFragment, SdkProvider<RemoverData, AbstractRemoverFragment>> {
    /* access modifiers changed from: private */
    public RemoverAdapter mAdapter;
    private CommonBottomMenuWithUndo mBottomMenuPanel;
    /* access modifiers changed from: private */
    public int mCurrentType = 0;
    /* access modifiers changed from: private */
    public List<RemoverData> mDataList;
    /* access modifiers changed from: private */
    public boolean mIsNightMode;
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            if (RemoverMenuFragment.this.mAdapter.getSelection() == i) {
                return false;
            }
            RemoverData removerData = (RemoverData) RemoverMenuFragment.this.mDataList.get(i);
            int unused = RemoverMenuFragment.this.mCurrentType = removerData.mType;
            ((AbstractRemoverFragment) RemoverMenuFragment.this.getRenderFragment()).setRemoverData(removerData);
            RemoverMenuFragment.this.mAdapter.setSelection(i);
            RemoverMenuFragment removerMenuFragment = RemoverMenuFragment.this;
            ((AbstractRemoverFragment) RemoverMenuFragment.this.getRenderFragment()).setPaintSize((float) removerMenuFragment.getPaintSizeByProgress(removerMenuFragment.mSeekBar.getProgress()));
            return true;
        }
    };
    /* access modifiers changed from: private */
    public PaintSizePopupWindow mPaintSizePopupWindow;
    private ProgressDialog mProgressDialog;
    /* access modifiers changed from: private */
    public SimpleRecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public BasicSeekBar mSeekBar;

    public RemoverMenuFragment() {
        super(Effect.REMOVER);
    }

    /* access modifiers changed from: private */
    public int getPaintSizeByProgress(int i) {
        int i2;
        int i3;
        if (this.mCurrentType != 1) {
            i3 = 35;
            i2 = 161;
        } else {
            i3 = 20;
            i2 = 100;
        }
        return i3 + Math.round(((float) ((i2 - i3) * i)) / ((float) this.mSeekBar.getMax()));
    }

    /* access modifiers changed from: protected */
    public void hideProcessDialog() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDataList = new ArrayList(this.mSdkProvider.list());
        this.mIsNightMode = MiscUtil.isNightMode(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new RemoverView(viewGroup.getContext());
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.recycler_view);
        this.mAdapter = new RemoverAdapter(getActivity(), this.mDataList, new Selectable.Selector(getResources().getColor(R.color.photo_editor_highlight_color)));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this.mOnItemClickListener);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mAdapter.setSelection(0);
        this.mPaintSizePopupWindow = new PaintSizePopupWindow(getActivity());
        this.mSeekBar = (BasicSeekBar) view.findViewById(R.id.seekbar);
        BasicSeekBar basicSeekBar = this.mSeekBar;
        basicSeekBar.setProgress(basicSeekBar.getMax() / 2);
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int access$000 = RemoverMenuFragment.this.getPaintSizeByProgress(i);
                RemoverMenuFragment.this.mPaintSizePopupWindow.setPaintSize(access$000);
                ((AbstractRemoverFragment) RemoverMenuFragment.this.getRenderFragment()).setPaintSize((float) access$000);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                RemoverMenuFragment.this.mPaintSizePopupWindow.setPaintSize(RemoverMenuFragment.this.getPaintSizeByProgress(seekBar.getProgress()));
                RemoverMenuFragment.this.mPaintSizePopupWindow.showAtLocation(RemoverMenuFragment.this.mRecyclerView, 17, 0, (-RemoverMenuFragment.this.getResources().getDimensionPixelSize(R.dimen.photo_editor_menu_panel_height)) / 2);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                RemoverMenuFragment.this.mPaintSizePopupWindow.dismiss();
            }
        });
        ((AbstractRemoverFragment) getRenderFragment()).setPaintSize((float) (this.mSeekBar.getProgress() + 35));
        this.mBottomMenuPanel = (CommonBottomMenuWithUndo) view.findViewById(R.id.bottom_bar);
        this.mBottomMenuPanel.setTitle(R.string.photo_editor_remover);
        TextView title = this.mBottomMenuPanel.getTitle();
        title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.editor_ic_help, 0);
        title.setCompoundDrawablePadding((int) getResources().getDimension(R.dimen.editor_menu_remove_text_img_padding_end));
        title.setOnClickListener(new SingleClickListener() {
            /* access modifiers changed from: protected */
            public void onSingleClick(View view) {
                Intent intent = new Intent("com.miui.gallery.action.VIEW_WEB");
                Locale locale = Locale.US;
                Object[] objArr = new Object[2];
                objArr[0] = RemoverMenuFragment.this.mIsNightMode ? "&mode=dark" : "";
                objArr[1] = Locale.getDefault();
                intent.putExtra("url", String.format(locale, "https://i.mi.com/static2?filename=MicloudWebBill/event/gallery/MagicAllh5.html%1$s#%2$s", objArr));
                RemoverMenuFragment.this.startActivity(intent);
                GallerySamplingStatHelper.recordCountEvent("photo_editor", "remove_tips_click");
            }
        });
        AbstractRemoverFragment abstractRemoverFragment = (AbstractRemoverFragment) getRenderFragment();
        if (abstractRemoverFragment instanceof RemoverRenderFragment) {
            RemoverRenderFragment removerRenderFragment = (RemoverRenderFragment) abstractRemoverFragment;
            this.mBottomMenuPanel.setRenderRecordListener(removerRenderFragment);
            removerRenderFragment.setMenuUpdateListener(this.mBottomMenuPanel);
        }
        this.mBottomMenuPanel.updateBottomBar(true);
    }

    /* access modifiers changed from: protected */
    public void showProcessDialog() {
        this.mProgressDialog = genProgressDialog(getActivity().getString(R.string.remover_menu_processing));
        this.mProgressDialog.show();
    }
}
