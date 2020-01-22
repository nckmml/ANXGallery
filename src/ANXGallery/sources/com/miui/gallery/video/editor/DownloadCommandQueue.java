package com.miui.gallery.video.editor;

import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.List;
import miui.util.concurrent.Queue;

public class DownloadCommandQueue implements Queue<DownloadCommand> {
    private List<DownloadCommand> mCommands = new ArrayList();

    public int clear() {
        this.mCommands.clear();
        return 0;
    }

    public DownloadCommand get() {
        if (this.mCommands.size() > 0) {
            return this.mCommands.get(0);
        }
        return null;
    }

    public DownloadCommand get(int i) {
        if (!isEmpty() && i >= 0 && i <= this.mCommands.size()) {
            return this.mCommands.get(i);
        }
        return null;
    }

    public int getCapacity() {
        return this.mCommands.size();
    }

    public boolean isEmpty() {
        return this.mCommands.isEmpty();
    }

    public boolean put(DownloadCommand downloadCommand) {
        if (!this.mCommands.contains(downloadCommand) && this.mCommands.size() < 5) {
            return this.mCommands.add(downloadCommand);
        }
        Log.d("DownloadCommandQueue", "the queue add fail.");
        return false;
    }

    public int remove(Queue.Predicate<DownloadCommand> predicate) {
        return 0;
    }

    public boolean remove(DownloadCommand downloadCommand) {
        return this.mCommands.remove(downloadCommand);
    }
}
