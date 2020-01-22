package com.miui.gallery.ui.album.hiddenalbum;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.epoxy.EpoxyModel;
import com.miui.epoxy.eventhook.OnClickEventHook;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.adapter.itemmodel.HiddenAlbumItemModel;
import com.miui.gallery.ui.album.hiddenalbum.HiddenAlbumContract;
import com.miui.gallery.ui.album.hiddenalbum.viewbean.HiddenAlbumItemViewBean;
import com.miui.gallery.util.DialogUtil;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.ToastUtils;
import java.util.ArrayList;
import java.util.List;

public class HiddenAlbumFragment extends HiddenAlbumContract.V<HiddenAlbumItemViewBean, HiddenAlbumPresenter> {
    private boolean isFirstAddEventHook = true;

    private void addEventHook(List<HiddenAlbumItemViewBean> list) {
        if (list != null && !list.isEmpty() && this.isFirstAddEventHook) {
            this.mAdapter.addEventHook(new OnClickEventHook<HiddenAlbumItemModel.HiddenAlbumVH>(HiddenAlbumItemModel.HiddenAlbumVH.class) {
                public List<? extends View> onBindMany(HiddenAlbumItemModel.HiddenAlbumVH hiddenAlbumVH) {
                    ArrayList arrayList = new ArrayList(2);
                    arrayList.add(hiddenAlbumVH.mBtnCancelHidden);
                    arrayList.add(hiddenAlbumVH.mIvAlbumPhoto);
                    return arrayList;
                }

                public void onClick(View view, HiddenAlbumItemModel.HiddenAlbumVH hiddenAlbumVH, int i, EpoxyModel epoxyModel) {
                    if (!(epoxyModel instanceof HiddenAlbumItemModel)) {
                        return;
                    }
                    if (view.getId() == R.id.btnCancelHidden) {
                        HiddenAlbumFragment.this.showConfirmDialog((HiddenAlbumItemModel) epoxyModel);
                    } else if (view.getId() == R.id.ivAlbumPhoto) {
                        IntentUtil.gotoAlbumDetailPage(HiddenAlbumFragment.this.getSafeActivity(), ((HiddenAlbumItemViewBean) ((HiddenAlbumItemModel) epoxyModel).getItemData()).getSource(), 1001, -1);
                    }
                }
            });
            this.isFirstAddEventHook = false;
        }
    }

    public static HiddenAlbumFragment newInstance() {
        Bundle bundle = new Bundle();
        HiddenAlbumFragment hiddenAlbumFragment = new HiddenAlbumFragment();
        hiddenAlbumFragment.setArguments(bundle);
        return hiddenAlbumFragment;
    }

    /* access modifiers changed from: private */
    public void showConfirmDialog(final HiddenAlbumItemModel hiddenAlbumItemModel) {
        DialogUtil.showInfoDialog((Context) getSafeActivity(), (int) R.string.remove_hidden_status_tip, (int) R.string.remove_hidden_status_title, (int) R.string.ok, (int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((HiddenAlbumContract.P) HiddenAlbumFragment.this.getPresenter()).unAlbumHide((HiddenAlbumItemViewBean) hiddenAlbumItemModel.getItemData());
            }
        }, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }

    /* access modifiers changed from: protected */
    public RecyclerView.ItemDecoration getRecycleViewDecoration() {
        final int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.album_vertical_head_item_vertical_spacing);
        final int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.album_vertical_head_first_item_margin_top);
        return new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (recyclerView.getChildAdapterPosition(view) == 0) {
                    rect.top = dimensionPixelSize2;
                    rect.bottom = dimensionPixelSize;
                    return;
                }
                int i = dimensionPixelSize;
                rect.bottom = i;
                rect.top = i;
            }
        };
    }

    /* access modifiers changed from: protected */
    public void initRecycleView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getSafeActivity(), 1, false));
        setEmptyPage(getDefaultEmptyConfig().setTitle(R.string.empty_album_hidden_list_title));
        setContentPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.album_default_recycleview_padding_bottom));
        recyclerView.setClipToPadding(false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ((HiddenAlbumContract.P) getPresenter()).initData();
    }

    public void showHiddenListData(List<HiddenAlbumItemViewBean> list) {
        setDatas(list);
        addEventHook(list);
    }

    public void showUnHiddenAlbumIsFaild() {
    }

    public void showUnHiddenAlbumIsSuccess(HiddenAlbumItemViewBean hiddenAlbumItemViewBean) {
        this.mAdapter.removeData(hiddenAlbumItemViewBean);
        ToastUtils.makeText(GalleryApp.sGetAndroidContext(), (CharSequence) getString(R.string.remove_hidden_status_success, new Object[]{hiddenAlbumItemViewBean.getAlbumName()}));
    }

    public void updateHiddenListData(List<HiddenAlbumItemViewBean> list) {
        updateDatas(list);
        addEventHook(list);
    }
}
