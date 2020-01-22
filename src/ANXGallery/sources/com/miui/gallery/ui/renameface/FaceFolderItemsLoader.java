package com.miui.gallery.ui.renameface;

import android.app.Activity;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.model.DisplayFolderItem;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.ui.renameface.FolderItemsLoader;
import com.miui.gallery.util.face.PeopleCursorHelper;
import java.util.ArrayList;

/* compiled from: FaceAlbumHandlerBase */
class FaceFolderItemsLoader extends FolderItemsLoader {
    public FaceFolderItemsLoader(Activity activity, String str, FolderItemsLoader.LoaderUpdatedItems loaderUpdatedItems, long[] jArr) {
        super(activity, str, loaderUpdatedItems, jArr, false);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0050, code lost:
        if (r2 != null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005a, code lost:
        if (r2 == null) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005c, code lost:
        r2.close();
     */
    public ArrayList<DisplayFolderItem> refreshCloudFolderItems() {
        ArrayList<DisplayFolderItem> arrayList = new ArrayList<>();
        Activity activity = (Activity) this.mActivityRef.get();
        if (activity != null) {
            Cursor cursor = null;
            try {
                cursor = activity.getContentResolver().query(GalleryContract.PeopleFace.PERSONS_URI, PeopleCursorHelper.PROJECTION, (String) null, (String[]) null, (String) null);
                while (cursor != null && cursor.moveToNext()) {
                    String thumbnailPath = PeopleCursorHelper.getThumbnailPath(cursor);
                    String peopleName = PeopleCursorHelper.getPeopleName(cursor);
                    if (!TextUtils.isEmpty(peopleName) && isMediaSetCandidate(PeopleCursorHelper.getPeopleLocalId(cursor))) {
                        arrayList.add(new FaceDisplayFolderItem(peopleName, thumbnailPath, PeopleCursorHelper.getPeopleLocalId(cursor), PeopleCursorHelper.getFaceRegionRectF(cursor)));
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return arrayList;
    }
}
