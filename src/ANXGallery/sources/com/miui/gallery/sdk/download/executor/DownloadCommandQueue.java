package com.miui.gallery.sdk.download.executor;

import com.miui.gallery.sdk.download.assist.DownloadItem;
import com.miui.gallery.sdk.download.executor.queue.Queue;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DownloadCommandQueue {
    private final int mAllowBatchMax;
    private final Map<String, DownloadCommand> mExecutings;
    private final ReadWriteLock mLock = new ReentrantReadWriteLock();
    private final Queue<DownloadCommand> mPendings;
    private final String mTag;

    public DownloadCommandQueue(int i, int i2, String str) {
        this.mPendings = new Queue<>(i2);
        this.mExecutings = new HashMap();
        this.mAllowBatchMax = i;
        this.mTag = str;
    }

    private void lock(boolean z) {
        if (z) {
            this.mLock.writeLock().lock();
        } else {
            this.mLock.readLock().lock();
        }
    }

    private void unlock(boolean z) {
        if (z) {
            this.mLock.writeLock().unlock();
        } else {
            this.mLock.readLock().unlock();
        }
    }

    public int cancel(String str) {
        lock(true);
        int i = -1;
        try {
            DownloadCommand remove = this.mPendings.remove(str);
            if (remove != null) {
                i = 0;
            } else {
                remove = this.mExecutings.remove(str);
                if (remove != null) {
                    i = 1;
                }
            }
            if (remove != null && remove.getItem().compareAnsSetStatus(0, 1)) {
                DownloadItem.callbackCancel(remove.getItem());
            }
            return i;
        } finally {
            unlock(true);
        }
    }

    public List<DownloadCommand> cancelAll() {
        lock(true);
        try {
            ArrayList<DownloadCommand> arrayList = new ArrayList<>();
            Collection<DownloadCommand> values = this.mExecutings.values();
            if (values != null) {
                for (DownloadCommand add : values) {
                    arrayList.add(add);
                }
            }
            this.mExecutings.clear();
            Collection<DownloadCommand> values2 = this.mPendings.values();
            if (values2 != null) {
                for (DownloadCommand add2 : values2) {
                    arrayList.add(add2);
                }
            }
            this.mPendings.clear();
            for (DownloadCommand downloadCommand : arrayList) {
                if (downloadCommand.getItem().compareAnsSetStatus(0, 1)) {
                    DownloadItem.callbackCancel(downloadCommand.getItem());
                }
            }
            return arrayList;
        } finally {
            unlock(true);
        }
    }

    public int contains(String str) {
        lock(false);
        try {
            if (this.mPendings.get(str) != null) {
                return 0;
            }
            int i = this.mExecutings.get(str) != null ? 1 : -1;
            unlock(false);
            return i;
        } finally {
            unlock(false);
        }
    }

    public int getBatchSize() {
        return this.mAllowBatchMax;
    }

    public int getPendingSize() {
        lock(false);
        try {
            return this.mPendings.size();
        } finally {
            unlock(false);
        }
    }

    public List<DownloadCommand> interrupt() {
        lock(true);
        try {
            LinkedList<DownloadCommand> linkedList = new LinkedList<>();
            for (DownloadCommand next : this.mExecutings.values()) {
                if (next.getItem().compareAnsSetStatus(0, 2)) {
                    linkedList.add(next);
                }
            }
            this.mExecutings.clear();
            for (DownloadCommand next2 : this.mPendings.values()) {
                if (next2.getItem().compareAnsSetStatus(0, 2)) {
                    linkedList.add(next2);
                }
            }
            this.mPendings.clear();
            LinkedList linkedList2 = new LinkedList();
            for (DownloadCommand downloadCommand : linkedList) {
                linkedList2.add(new DownloadCommand(downloadCommand));
            }
            this.mPendings.putAtLast(linkedList2);
            return linkedList;
        } finally {
            unlock(true);
        }
    }

    public List<DownloadCommand> pollToExecute() {
        lock(true);
        try {
            List<DownloadCommand> poll = this.mPendings.poll(this.mAllowBatchMax);
            if (poll != null) {
                for (DownloadCommand next : poll) {
                    this.mExecutings.put(next.getKey(), next);
                }
            }
            Log.d(this.mTag, "pollToExecute: remove count=%d, remain count=%d", Integer.valueOf(poll.size()), Integer.valueOf(this.mPendings.size()));
            return poll;
        } finally {
            unlock(true);
        }
    }

    public int put(DownloadCommand downloadCommand, boolean z) {
        return put((List<DownloadCommand>) Arrays.asList(new DownloadCommand[]{downloadCommand}), z);
    }

    public int put(List<DownloadCommand> list, boolean z) {
        lock(true);
        try {
            ArrayList arrayList = new ArrayList();
            for (DownloadCommand next : list) {
                if (!this.mExecutings.containsKey(next.getKey())) {
                    arrayList.add(next);
                }
            }
            int putAtFirst = z ? this.mPendings.putAtFirst(arrayList) : this.mPendings.putAtLast(arrayList);
            Log.d(this.mTag, "put: add count=%d, atFirst=%s", Integer.valueOf(putAtFirst), Boolean.valueOf(z));
            return putAtFirst;
        } finally {
            unlock(true);
        }
    }

    public DownloadCommand remove(String str) {
        lock(true);
        try {
            DownloadCommand remove = this.mExecutings.remove(str);
            if (remove == null) {
                remove = this.mPendings.remove(str);
            }
            return remove;
        } finally {
            unlock(true);
        }
    }

    public DownloadCommand removeFromExecuting(String str) {
        lock(true);
        try {
            return this.mExecutings.remove(str);
        } finally {
            unlock(true);
        }
    }
}
