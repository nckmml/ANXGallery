package com.miui.gallery.cloud.thread;

import com.miui.gallery.cloud.thread.RequestCommandQueue;

public interface TaskFactory {
    BaseTask createTask(int i, int i2, int i3, int i4, RequestCommandQueue.OnItemChangedListener onItemChangedListener);
}
