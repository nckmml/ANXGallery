package cn.kuaipan.android.kss.download;

import cn.kuaipan.android.kss.download.LoadMap;
import java.io.IOException;

public class LoadRecorder {
    private LoadMap map;
    private final LoadMap.Space space;

    LoadRecorder(LoadMap loadMap, LoadMap.Space space2) {
        this.map = loadMap;
        this.space = space2;
    }

    public void add(int i) throws IOException {
        if (this.map != null) {
            this.space.remove(i);
            this.map.onSpaceRemoved(i);
            return;
        }
        throw new IOException("The recoder has been recycled");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        recycle();
        super.finalize();
    }

    /* access modifiers changed from: package-private */
    public LoadMap.Space getSpace() {
        return this.space;
    }

    /* access modifiers changed from: package-private */
    public long getStart() {
        return this.space.getStart();
    }

    public void recycle() {
        LoadMap loadMap = this.map;
        if (loadMap != null) {
            loadMap.recycleRecorder(this);
            this.map = null;
        }
    }

    public long size() {
        return this.space.size();
    }
}
