package com.miui.gallery.cloud.thread;

import android.os.SystemClock;
import com.google.common.collect.Maps;
import com.miui.gallery.cloud.thread.Command;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CommandQueue<T extends Command> {
    private final LinkedList<T>[] mLists;
    private final HashMap<String, T> mMap = Maps.newHashMap();
    private final int mMaxSize;

    public interface OnQueueChangedListener<T extends Command> {
        void onAdd(T t);

        void onRemove(T t);
    }

    public CommandQueue(int i, int i2) {
        this.mLists = (LinkedList[]) new LinkedList[i];
        this.mMaxSize = i2;
    }

    private boolean needAdd(T t, long j, OnQueueChangedListener<T> onQueueChangedListener) {
        Command command = (Command) this.mMap.get(t.getKey());
        if (command == null) {
            return true;
        }
        if (command.getDelay(j) <= t.getDelay(j) && command.getPriority() >= t.getPriority() && !command.needProcess()) {
            return false;
        }
        if (onQueueChangedListener != null) {
            onQueueChangedListener.onRemove(command);
        }
        remove(command);
        return true;
    }

    private boolean putInternal(T t, boolean z, long j, OnQueueChangedListener<T> onQueueChangedListener) {
        if (!needAdd(t, j, onQueueChangedListener)) {
            return false;
        }
        if (onQueueChangedListener != null) {
            onQueueChangedListener.onAdd(t);
        }
        this.mMap.put(t.getKey(), t);
        int priority = t.getPriority();
        LinkedList<T>[] linkedListArr = this.mLists;
        if (linkedListArr[priority] == null) {
            linkedListArr[priority] = new LinkedList<>();
        }
        if (z) {
            this.mLists[priority].addFirst(t);
            if (this.mMaxSize <= 0 || size() <= this.mMaxSize) {
                return true;
            }
            this.mMap.remove(((Command) this.mLists[priority].removeLast()).getKey());
            return true;
        }
        this.mLists[priority].addLast(t);
        return true;
    }

    private void remove(T t) {
        this.mMap.remove(t.getKey());
        LinkedList<T> linkedList = this.mLists[t.getPriority()];
        if (linkedList != null) {
            linkedList.remove(t);
        }
    }

    public long getMinDelay(long j) {
        long j2 = Long.MAX_VALUE;
        for (T delay : this.mMap.values()) {
            long delay2 = delay.getDelay(j);
            if (j2 > delay2) {
                j2 = delay2;
            }
        }
        return j2;
    }

    public boolean hasDelayedItem() {
        long uptimeMillis = SystemClock.uptimeMillis();
        for (T delay : this.mMap.values()) {
            if (delay.getDelay(uptimeMillis) > 0) {
                return true;
            }
        }
        return false;
    }

    public void poll(List<T> list, int i, long j) {
        for (int length = this.mLists.length - 1; length >= 0; length--) {
            LinkedList<T> linkedList = this.mLists[length];
            if (linkedList != null && !linkedList.isEmpty()) {
                Command command = null;
                ListIterator listIterator = linkedList.listIterator();
                while (listIterator.hasNext() && list.size() < i) {
                    Command command2 = (Command) listIterator.next();
                    boolean z = false;
                    if (command == null) {
                        if (command2.getDelay(j) <= 0) {
                            z = true;
                            command = command2;
                        }
                    } else if (!command.canMergeWith(command2)) {
                        break;
                    } else if (command2.getDelay(j) <= 0) {
                        z = true;
                    }
                    if (z) {
                        list.add(command2);
                        listIterator.remove();
                        this.mMap.remove(command2.getKey());
                    } else if (command != null) {
                        break;
                    }
                }
                if (!list.isEmpty()) {
                    return;
                }
            }
        }
    }

    public int putAtFrist(List<T> list, long j, OnQueueChangedListener<T> onQueueChangedListener) {
        ListIterator<T> listIterator = list.listIterator(list.size());
        int i = 0;
        while (listIterator.hasPrevious()) {
            i += putInternal((Command) listIterator.previous(), true, j, onQueueChangedListener) ? 1 : 0;
        }
        return i;
    }

    public int putAtLast(List<T> list, long j, OnQueueChangedListener<T> onQueueChangedListener) {
        int i = 0;
        ListIterator<T> listIterator = list.listIterator(0);
        while (listIterator.hasNext()) {
            i += putInternal((Command) listIterator.next(), false, j, onQueueChangedListener) ? 1 : 0;
        }
        return i;
    }

    public T remove(String str) {
        T t = (Command) this.mMap.remove(str);
        if (t != null) {
            this.mLists[t.getPriority()].remove(t);
        }
        return t;
    }

    public int size() {
        return this.mMap.size();
    }

    public Collection<T> values() {
        return Collections.unmodifiableCollection(this.mMap.values());
    }
}
