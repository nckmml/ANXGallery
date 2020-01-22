package com.miui.gallery.adapter.itemmodel;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.miui.epoxy.EpoxyAdapter;
import com.miui.epoxy.EpoxyModel;
import com.miui.gallery.R;
import com.miui.gallery.adapter.itemmodel.base.BaseItemModel;
import com.miui.gallery.adapter.itemmodel.base.BaseViewHolder;
import com.miui.gallery.ui.album.common.CommonAlbumItemViewBean;
import com.miui.gallery.ui.album.hiddenalbum.viewbean.HiddenAlbumItemViewBean;
import com.miui.gallery.util.imageloader.imageloadiotion.AlbumImageLoadOptions;
import com.miui.gallery.widget.ForegroundImageView;
import java.util.List;

public class HiddenAlbumItemModel extends BaseItemModel<HiddenAlbumItemViewBean, HiddenAlbumVH> {

    public static class HiddenAlbumVH extends BaseViewHolder {
        public Button mBtnCancelHidden;
        public ForegroundImageView mIvAlbumPhoto;
        /* access modifiers changed from: private */
        public TextView mTvAlbumPhotoNumber;
        /* access modifiers changed from: private */
        public TextView mTvAlbumTitle;

        public HiddenAlbumVH(View view) {
            super(view);
            this.mTvAlbumTitle = (TextView) view.findViewById(R.id.tvAlbumTitle);
            this.mTvAlbumPhotoNumber = (TextView) view.findViewById(R.id.tvSubTitle);
            this.mBtnCancelHidden = (Button) view.findViewById(R.id.btnCancelHidden);
            this.mIvAlbumPhoto = (ForegroundImageView) view.findViewById(R.id.ivAlbumPhoto);
            this.mIvAlbumPhoto.setForegroundResource(R.drawable.album_page_list_rect_item_pressed_with_color_fg);
        }
    }

    public HiddenAlbumItemModel(HiddenAlbumItemViewBean hiddenAlbumItemViewBean) {
        super(hiddenAlbumItemViewBean.getId(), hiddenAlbumItemViewBean);
    }

    public void bindData(HiddenAlbumVH hiddenAlbumVH, List<Object> list) {
        HiddenAlbumItemViewBean hiddenAlbumItemViewBean = (HiddenAlbumItemViewBean) getItemData();
        hiddenAlbumVH.mIvAlbumPhoto.setContentDescription(hiddenAlbumItemViewBean.getAlbumName());
        setText(hiddenAlbumVH.mTvAlbumTitle, hiddenAlbumItemViewBean.getAlbumName());
        setText(hiddenAlbumVH.mTvAlbumPhotoNumber, hiddenAlbumItemViewBean.getPhotoNumberText());
        bindImage(hiddenAlbumVH.mIvAlbumPhoto, hiddenAlbumItemViewBean.getAlbumCoverPath(), hiddenAlbumItemViewBean.getAlbumCover(), AlbumImageLoadOptions.getInstance().getHiddenAlbumImageOptions(hiddenAlbumItemViewBean.getCoverSize()));
    }

    public int getLayoutRes() {
        return R.layout.album_hidden_item;
    }

    public EpoxyAdapter.IViewHolderCreator<HiddenAlbumVH> getViewHolderCreator() {
        return new EpoxyAdapter.IViewHolderCreator<HiddenAlbumVH>() {
            public HiddenAlbumVH create(View view, View view2) {
                return new HiddenAlbumVH(view);
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
