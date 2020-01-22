package com.miui.gallery.adapter.itemmodel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.epoxy.EpoxyAdapter;
import com.miui.epoxy.EpoxyModel;
import com.miui.gallery.R;
import com.miui.gallery.adapter.itemmodel.base.BaseItemModel;
import com.miui.gallery.adapter.itemmodel.base.BaseViewHolder;
import com.miui.gallery.ui.album.cloudalbum.viewbean.CloudAlbumItemViewBean;
import com.miui.gallery.ui.album.common.CommonAlbumItemViewBean;
import com.miui.gallery.util.imageloader.imageloadiotion.AlbumImageLoadOptions;
import java.util.List;
import miui.widget.SlidingButton;

public class CloudAlbumItemModel extends BaseItemModel<CloudAlbumItemViewBean, CloudAlbumVH> {

    public static class CloudAlbumVH extends BaseViewHolder {
        public SlidingButton mCbBackup = findViewById(R.id.cbBackup);
        /* access modifiers changed from: private */
        public ImageView mIvAlbumPhoto = ((ImageView) findViewById(R.id.ivAlbumPhoto));
        /* access modifiers changed from: private */
        public TextView mTvAlbumPhotoNumber = ((TextView) findViewById(R.id.tvSubTitle));
        /* access modifiers changed from: private */
        public TextView mTvAlbumTitle = ((TextView) findViewById(R.id.tvAlbumTitle));

        public CloudAlbumVH(View view) {
            super(view);
        }
    }

    public CloudAlbumItemModel(CloudAlbumItemViewBean cloudAlbumItemViewBean) {
        super(cloudAlbumItemViewBean.getId(), cloudAlbumItemViewBean);
    }

    public void bindData(CloudAlbumVH cloudAlbumVH, List<Object> list) {
        CloudAlbumItemViewBean cloudAlbumItemViewBean = (CloudAlbumItemViewBean) getItemData();
        setText(cloudAlbumVH.mTvAlbumTitle, cloudAlbumItemViewBean.getAlbumName());
        setText(cloudAlbumVH.mTvAlbumPhotoNumber, cloudAlbumItemViewBean.getPhotoNumberText());
        cloudAlbumVH.mCbBackup.setChecked(cloudAlbumItemViewBean.isBackup());
        cloudAlbumVH.mCbBackup.setTag(cloudAlbumItemViewBean);
        bindImage(cloudAlbumVH.mIvAlbumPhoto, cloudAlbumItemViewBean.getAlbumCoverPath(), cloudAlbumItemViewBean.getAlbumCover(), AlbumImageLoadOptions.getInstance().getHiddenAlbumImageOptions(cloudAlbumItemViewBean.getCoverSize()));
    }

    public int getLayoutRes() {
        return R.layout.album_cloud_item;
    }

    public EpoxyAdapter.IViewHolderCreator<CloudAlbumVH> getViewHolderCreator() {
        return new EpoxyAdapter.IViewHolderCreator<CloudAlbumVH>() {
            public CloudAlbumVH create(View view, View view2) {
                return new CloudAlbumVH(view);
            }
        };
    }

    public boolean isContentTheSame(EpoxyModel<?> epoxyModel) {
        if (!BaseItemModel.class.isInstance(epoxyModel)) {
            return super.isContentTheSame(epoxyModel);
        }
        return ((CommonAlbumItemViewBean) getItemData()).equals((CommonAlbumItemViewBean) ((BaseItemModel) epoxyModel).getItemData());
    }
}
