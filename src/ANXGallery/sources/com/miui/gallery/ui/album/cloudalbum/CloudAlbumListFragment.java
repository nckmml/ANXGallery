package com.miui.gallery.ui.album.cloudalbum;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.epoxy.EpoxyAdapter;
import com.miui.epoxy.eventhook.EventHook;
import com.miui.gallery.R;
import com.miui.gallery.adapter.itemmodel.CloudAlbumItemModel;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.ui.album.cloudalbum.CloudAlbumListContract;
import com.miui.gallery.ui.album.cloudalbum.viewbean.CloudAlbumItemViewBean;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.ToastUtils;
import java.util.List;
import miui.widget.SlidingButton;

public class CloudAlbumListFragment extends CloudAlbumListContract.V<CloudAlbumItemViewBean, CloudAlbumListPresenter> {
    private boolean isFirstAddEventHook = true;

    private void addEventHook(List<CloudAlbumItemViewBean> list) {
        if (list != null && !list.isEmpty() && this.isFirstAddEventHook) {
            this.mAdapter.addEventHook(new EventHook<CloudAlbumItemModel.CloudAlbumVH>(CloudAlbumItemModel.CloudAlbumVH.class) {
                public View onBind(CloudAlbumItemModel.CloudAlbumVH cloudAlbumVH) {
                    return cloudAlbumVH.itemView;
                }

                public void onEvent(View view, CloudAlbumItemModel.CloudAlbumVH cloudAlbumVH, EpoxyAdapter epoxyAdapter) {
                    if (view.getId() == R.id.item_cloud_album) {
                        cloudAlbumVH.itemView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                SlidingButton findViewById = view.findViewById(R.id.cbBackup);
                                if (findViewById != null) {
                                    findViewById.toggle();
                                }
                            }
                        });
                        cloudAlbumVH.mCbBackup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                                if (compoundButton.isPressed() && compoundButton.isInTouchMode()) {
                                    CloudAlbumListFragment.this.doChangeAutoUpload(compoundButton.getTag(), z);
                                }
                            }
                        });
                        cloudAlbumVH.mCbBackup.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                                CloudAlbumListFragment.this.doChangeAutoUpload(compoundButton.getTag(), z);
                            }
                        });
                    }
                }
            });
            this.isFirstAddEventHook = false;
        }
    }

    public static CloudAlbumListFragment newInstance() {
        Bundle bundle = new Bundle();
        CloudAlbumListFragment cloudAlbumListFragment = new CloudAlbumListFragment();
        cloudAlbumListFragment.setArguments(bundle);
        return cloudAlbumListFragment;
    }

    /* access modifiers changed from: protected */
    public void doChangeAutoUpload(Object obj, boolean z) {
        Account account = AccountCache.getAccount();
        if (account == null) {
            return;
        }
        if (!z || ContentResolver.getSyncAutomatically(account, "com.miui.gallery.cloud.provider") || SyncUtil.setSyncAutomatically(getActivity(), true)) {
            ((CloudAlbumListContract.P) getPresenter()).doChangeAlbumUploadStatus(obj);
        } else {
            showChangeAlbumUploadStatusFailed(((CloudAlbumListContract.P) getPresenter()).converterTagBeanToCloudAlbumItemItemViewBean(obj));
        }
    }

    /* access modifiers changed from: protected */
    public RecyclerView.ItemDecoration getRecycleViewDecoration() {
        final int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.album_vertical_head_first_item_margin_top) - getResources().getDimensionPixelSize(R.dimen.album_vertical_head_item_vertical_spacing);
        return new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (recyclerView.getChildAdapterPosition(view) == 0) {
                    rect.top = dimensionPixelSize;
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void initRecycleView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getSafeActivity(), 1, false));
        setEmptyPage(getDefaultEmptyConfig().setTitle(R.string.empty_album_cloud_list_title));
        setContentPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.album_default_recycleview_padding_bottom));
        recyclerView.setClipToPadding(false);
        recyclerView.setMotionEventSplittingEnabled(false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ((CloudAlbumListContract.P) getPresenter()).initData();
    }

    public void showChangeAlbumCantBeShareAlbum(CloudAlbumItemViewBean cloudAlbumItemViewBean) {
        ToastUtils.makeText((Context) getActivity(), (int) R.string.share_album_needs_auto_upload_tip);
        this.mAdapter.notifyDataChanged(cloudAlbumItemViewBean);
    }

    public void showChangeAlbumUploadStatusFailed(CloudAlbumItemViewBean cloudAlbumItemViewBean) {
        this.mAdapter.notifyDataChanged(cloudAlbumItemViewBean);
    }

    public void showChangeAlbumUploadStatusSuccess(CloudAlbumItemViewBean cloudAlbumItemViewBean) {
        ToastUtils.makeTextLong((Context) getActivity(), cloudAlbumItemViewBean.isBackup() ? R.string.auto_upload_enable_toast_long_press_menu : R.string.auto_upload_disable_toast_long_press_menu);
    }

    public void showCloudList(List<CloudAlbumItemViewBean> list) {
        setDatas(list);
        addEventHook(list);
    }

    public void updateCloudList(List<CloudAlbumItemViewBean> list) {
        updateDatas(list);
        addEventHook(list);
    }
}
