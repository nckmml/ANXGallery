package com.miui.gallery.collage.core.layout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.miui.gallery.R;
import com.miui.gallery.collage.app.common.AbstractLayoutFragment;
import com.miui.gallery.collage.core.RenderData;
import com.miui.gallery.collage.core.poster.PosterModel;
import com.miui.gallery.collage.render.CollageRender;
import com.miui.gallery.collage.widget.CollageImageView;
import com.miui.gallery.collage.widget.CollageLayout;
import java.util.HashMap;
import java.util.Map;

public class LayoutFragment extends AbstractLayoutFragment {
    private CollageLayout.BitmapExchangeListener mBitmapExchangeListener = new CollageLayout.BitmapExchangeListener() {
        public void onBitmapExchange(Bitmap bitmap, Bitmap bitmap2) {
            LayoutFragment.this.mImageViewMap.put(bitmap, (CollageImageView) LayoutFragment.this.mImageViewMap.get(bitmap2));
            LayoutFragment.this.mImageViewMap.put(bitmap2, (CollageImageView) LayoutFragment.this.mImageViewMap.get(bitmap));
        }
    };
    private CollageLayout mCollageLayout;
    /* access modifiers changed from: private */
    public Map<Bitmap, CollageImageView> mImageViewMap = new HashMap();
    private boolean mInit = false;
    private LayoutModel mLayoutModel;
    private boolean mModelReady = false;
    private boolean mViewReady = false;

    private void generateCollageLayout(LayoutModel layoutModel) {
        this.mCollageLayout.removeAllViews();
        LayoutItemModel[] layoutItemModelArr = layoutModel.items;
        for (int i = 0; i < layoutItemModelArr.length; i++) {
            Bitmap bitmap = this.mBitmaps[i];
            LayoutItemModel layoutItemModel = layoutItemModelArr[i];
            CollageImageView collageImageView = new CollageImageView(getActivity());
            if (i < this.mBitmaps.length) {
                collageImageView.setBitmap(bitmap);
                this.mImageViewMap.put(bitmap, collageImageView);
            }
            this.mCollageLayout.addView(collageImageView, new CollageLayout.LayoutParams(layoutItemModel.clipType, layoutItemModel.data));
        }
    }

    private void refreshCollageLayout(LayoutModel layoutModel) {
        LayoutItemModel[] layoutItemModelArr = layoutModel.items;
        int childCount = this.mCollageLayout.getChildCount();
        for (int i = 0; i < Math.min(layoutItemModelArr.length, childCount); i++) {
            LayoutItemModel layoutItemModel = layoutItemModelArr[i];
            this.mCollageLayout.getChildAt(i).setLayoutParams(new CollageLayout.LayoutParams(layoutItemModel.clipType, layoutItemModel.data));
        }
    }

    private void refreshLayout() {
        if (this.mInit) {
            refreshCollageLayout(this.mLayoutModel);
        } else if (this.mModelReady && this.mBitmapReady && this.mViewReady) {
            this.mCollageLayout.setBackgroundColor(-1);
            generateCollageLayout(this.mLayoutModel);
            this.mCollageLayout.setReplaceImageListener(this.mReplaceImageListener);
            this.mCollageLayout.setBitmapExchangeListener(this.mBitmapExchangeListener);
            this.mInit = true;
        }
    }

    public void dismissControlWindow() {
        CollageLayout collageLayout = this.mCollageLayout;
        if (collageLayout != null) {
            collageLayout.dismissControlWindow();
        }
    }

    public RenderData export() {
        this.mLayoutModel.ratio = Float.parseFloat(((ConstraintLayout.LayoutParams) this.mCollageLayout.getLayoutParams()).dimensionRatio);
        Activity activity = getActivity();
        LayoutModel layoutModel = this.mLayoutModel;
        CollageLayout collageLayout = this.mCollageLayout;
        return new LayoutRenderData(CollageRender.generateRenderData(activity, (PosterModel) null, layoutModel, collageLayout, collageLayout.getWidth()));
    }

    public boolean isActivating() {
        CollageLayout collageLayout = this.mCollageLayout;
        return collageLayout != null && collageLayout.isActivating();
    }

    public void onBitmapReplace(Bitmap bitmap, Bitmap bitmap2) {
        CollageImageView collageImageView = this.mImageViewMap.get(bitmap);
        collageImageView.setBitmap(bitmap2);
        this.mImageViewMap.remove(bitmap);
        this.mImageViewMap.put(bitmap2, collageImageView);
    }

    /* access modifiers changed from: protected */
    public void onBitmapsReceive() {
        refreshLayout();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.collage_layout_render, viewGroup, false);
    }

    public void onSelectMargin(float f) {
        this.mCollageLayout.setCollageMargin(f, false);
    }

    public void onSelectModel(LayoutModel layoutModel) {
        this.mLayoutModel = layoutModel;
        this.mModelReady = true;
        refreshLayout();
    }

    public void onSelectRatio(float f) {
        ((ConstraintLayout.LayoutParams) this.mCollageLayout.getLayoutParams()).dimensionRatio = String.valueOf(f);
        this.mCollageLayout.requestLayout();
    }

    public HashMap<String, String> onSimple() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("collage_save_type", "Layout");
        hashMap.put("collage_image_size", String.valueOf(this.mBitmaps == null ? 0 : this.mBitmaps.length));
        LayoutModel layoutModel = this.mLayoutModel;
        hashMap.put("Layout", layoutModel == null ? null : layoutModel.name);
        return hashMap;
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mCollageLayout = (CollageLayout) view.findViewById(R.id.collage_layout);
        this.mViewReady = true;
        refreshLayout();
    }
}
