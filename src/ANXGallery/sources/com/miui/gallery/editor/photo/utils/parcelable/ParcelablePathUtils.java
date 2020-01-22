package com.miui.gallery.editor.photo.utils.parcelable;

import android.graphics.Path;
import android.graphics.PointF;
import java.util.List;

public class ParcelablePathUtils {
    public static Path getPathFromPointList(List<PointF> list) {
        Path path = new Path();
        PointF pointF = null;
        for (PointF next : list) {
            float f = next.x;
            float f2 = next.y;
            if (pointF == null) {
                path.moveTo(f, f2);
            } else {
                path.quadTo(pointF.x, pointF.y, (pointF.x + f) / 2.0f, (pointF.y + f2) / 2.0f);
            }
            pointF = next;
        }
        return path;
    }
}
