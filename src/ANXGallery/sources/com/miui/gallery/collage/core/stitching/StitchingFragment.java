package com.miui.gallery.collage.core.stitching;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.collage.app.common.AbstractStitchingFragment;
import com.miui.gallery.collage.core.RenderData;
import com.miui.gallery.collage.widget.CollageStitchingLayout;
import java.util.Arrays;
import java.util.HashMap;

public class StitchingFragment extends AbstractStitchingFragment {
    private boolean mInit = false;
    private boolean mModelReady = false;
    private CollageStitchingLayout mStitchingLayout;
    private StitchingModel mStitchingModel;
    private boolean mViewReady = false;

    private void generateStitchingLayout(StitchingModel stitchingModel) {
        this.mStitchingLayout.setBitmaps((Bitmap[]) Arrays.copyOf(this.mBitmaps, this.mBitmaps.length));
        this.mStitchingLayout.setStitchingModel(stitchingModel);
    }

    private void refreshLayout() {
        if (this.mInit) {
            refreshStitchingLayout(this.mStitchingModel);
        } else if (this.mModelReady && this.mBitmapReady && this.mViewReady) {
            generateStitchingLayout(this.mStitchingModel);
            this.mStitchingLayout.setReplaceImageListener(this.mReplaceImageListener);
            this.mInit = true;
        }
    }

    private void refreshStitchingLayout(StitchingModel stitchingModel) {
        this.mStitchingLayout.setStitchingModel(stitchingModel);
    }

    public void dismissControlWindow() {
        CollageStitchingLayout collageStitchingLayout = this.mStitchingLayout;
        if (collageStitchingLayout != null) {
            collageStitchingLayout.dismissControlWindow();
        }
    }

    public RenderData export() {
        StitchingRenderData stitchingRenderData = new StitchingRenderData(this.mStitchingLayout.generateRenderData());
        stitchingRenderData.mWidth = 1080;
        return stitchingRenderData;
    }

    public void onBitmapReplace(Bitmap bitmap, Bitmap bitmap2) {
        this.mStitchingLayout.notifyBitmapReplace(bitmap, bitmap2);
    }

    /* access modifiers changed from: protected */
    public void onBitmapsReceive() {
        refreshLayout();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.collage_stitching_render, viewGroup, false);
    }

    public void onSelectModel(StitchingModel stitchingModel) {
        this.mStitchingModel = stitchingModel;
        this.mModelReady = true;
        refreshLayout();
    }

    public HashMap<String, String> onSimple() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("collage_save_type", "Stitching");
        hashMap.put("collage_image_size", String.valueOf(this.mBitmaps == null ? 0 : this.mBitmaps.length));
        StitchingModel stitchingModel = this.mStitchingModel;
        hashMap.put("Stitching", stitchingModel == null ? null : stitchingModel.name);
        return hashMap;
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mStitchingLayout = (CollageStitchingLayout) view.findViewById(R.id.drag_layout);
        this.mViewReady = true;
        refreshLayout();
    }
}
