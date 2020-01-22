package com.miui.gallery.collage.core.stitching;

import android.graphics.Bitmap;
import com.miui.gallery.collage.render.CollageRender;
import com.miui.gallery.collage.widget.CollageStitchingLayout;

public class StitchingModel {
    public float horizontalOffset;
    public boolean isSquare;
    public String mask;
    public String name;
    public int radius;
    public String relativePath;
    public float verticalOffset;

    private interface BitmapCollection {
        Bitmap get(int i);

        int size();
    }

    private static void countHeight(int i, StitchingModel stitchingModel, CollageStitchingLayout.BitmapPositionHolder bitmapPositionHolder, BitmapCollection bitmapCollection) {
        float f = (float) i;
        int round = Math.round(stitchingModel.horizontalOffset * f);
        int round2 = Math.round(stitchingModel.verticalOffset * f);
        boolean z = stitchingModel.isSquare;
        int i2 = i - (round * 2);
        int i3 = 0;
        if (bitmapCollection != null && bitmapCollection.size() > 0) {
            int i4 = 0;
            while (i3 < bitmapCollection.size()) {
                Bitmap bitmap = bitmapCollection.get(i3);
                int round3 = z ? i2 : Math.round(((float) i2) * (((float) bitmap.getHeight()) / ((float) bitmap.getWidth())));
                i4 += round3;
                bitmapPositionHolder.bitmapHeights[i3] = round3;
                i3++;
            }
            i3 = i4 + ((round > 0 ? bitmapCollection.size() + 1 : bitmapCollection.size() - 1) * round2);
        }
        bitmapPositionHolder.bitmapWidth = i2;
        bitmapPositionHolder.verticalOffset = round2;
        bitmapPositionHolder.horizontalOffset = round;
        bitmapPositionHolder.height = i3;
    }

    public void countHeight(int i, CollageStitchingLayout.BitmapPositionHolder bitmapPositionHolder, final Bitmap[] bitmapArr) {
        countHeight(i, this, bitmapPositionHolder, new BitmapCollection() {
            public Bitmap get(int i) {
                return bitmapArr[i];
            }

            public int size() {
                return bitmapArr.length;
            }
        });
    }

    public void countHeight(int i, CollageStitchingLayout.BitmapPositionHolder bitmapPositionHolder, final CollageRender.BitmapRenderData[] bitmapRenderDataArr) {
        countHeight(i, this, bitmapPositionHolder, new BitmapCollection() {
            public Bitmap get(int i) {
                return bitmapRenderDataArr[i].bitmap;
            }

            public int size() {
                return bitmapRenderDataArr.length;
            }
        });
    }
}
