package com.miui.gallery.assistant.utils;

import android.text.TextUtils;
import com.miui.gallery.assistant.model.ImageFeature;
import com.miui.gallery.assistant.model.ImageFeatureItem;
import com.miui.gallery.dao.GalleryEntityManager;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageFeatureItemUtils<E extends ImageFeatureItem> {
    public void bindImageFeatures(List<E> list) {
        if (MiscUtil.isValid(list)) {
            ArrayList arrayList = new ArrayList(list.size());
            for (E mediaId : list) {
                arrayList.add(Long.valueOf(mediaId.getMediaId()));
            }
            List<ImageFeature> query = GalleryEntityManager.getInstance().query(ImageFeature.class, String.format("%s IN (%s)", new Object[]{"imageId", TextUtils.join(",", arrayList)}), (String[]) null, "createTime DESC", (String) null);
            if (MiscUtil.isValid(query)) {
                for (E e : list) {
                    Iterator<ImageFeature> it = query.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ImageFeature next = it.next();
                        if (next.getImageId() == e.getMediaId()) {
                            e.setImageFeature(next);
                            break;
                        }
                    }
                }
            }
        }
    }
}
