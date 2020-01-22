package com.nexstreaming.app.common.task;

import com.nexstreaming.app.common.task.Task;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ResultTask<T> extends Task {
    private T mResult = null;
    private long mResultTime;

    public interface OnResultAvailableListener<T> {
        void onResultAvailable(ResultTask<T> resultTask, Task.Event event, T t);
    }

    public static <INDIVIDUAL_RESULT_TYPE, RESULT_COLLECTION extends Collection<INDIVIDUAL_RESULT_TYPE>, TASK_COLLECTION extends Collection<ResultTask<RESULT_COLLECTION>>> ResultTask<List<INDIVIDUAL_RESULT_TYPE>> combineResults(final TASK_COLLECTION task_collection) {
        ResultTask<List<INDIVIDUAL_RESULT_TYPE>> resultTask = new ResultTask<>();
        final HashMap hashMap = new HashMap();
        Iterator it = task_collection.iterator();
        while (it.hasNext()) {
            ((ResultTask) it.next()).onResultAvailable(new OnResultAvailableListener<RESULT_COLLECTION>(resultTask) {
                final /* synthetic */ ResultTask a;

                {
                    this.a = r1;
                }

                /* renamed from: a */
                public void onResultAvailable(ResultTask<RESULT_COLLECTION> resultTask, Task.Event event, RESULT_COLLECTION result_collection) {
                    if (this.a.isRunning()) {
                        hashMap.put(resultTask, result_collection);
                        if (hashMap.size() >= task_collection.size()) {
                            ArrayList arrayList = new ArrayList();
                            for (ResultTask resultTask2 : task_collection) {
                                arrayList.addAll((Collection) hashMap.get(resultTask2));
                            }
                            this.a.sendResult(arrayList);
                        }
                    }
                }
            }).onFailure((Task.OnFailListener) new Task.OnFailListener(resultTask) {
                final /* synthetic */ ResultTask a;

                {
                    this.a = r1;
                }

                public void onFail(Task task, Task.Event event, Task.TaskError taskError) {
                    this.a.sendFailure(taskError);
                    hashMap.clear();
                }
            });
        }
        return resultTask;
    }

    public static <T> ResultTask<List<T>> combineResults(ResultTask<Collection<T>>... resultTaskArr) {
        return combineResults(Arrays.asList(resultTaskArr));
    }

    public static <T> ResultTask<T> completedResultTask(T t) {
        ResultTask<T> resultTask = new ResultTask<>();
        resultTask.sendResult(t);
        return resultTask;
    }

    public static <T> ResultTask<T> failedResultTask(Task.TaskError taskError) {
        ResultTask<T> resultTask = new ResultTask<>();
        resultTask.sendFailure(taskError);
        return resultTask;
    }

    public T awaitResult() throws Task.TaskErrorException {
        makeWaitable();
        awaitTaskCompletion();
        if (didSignalEvent(Task.Event.FAIL)) {
            throw new Task.TaskErrorException(getTaskError());
        } else if (didSignalEvent(Task.Event.RESULT_AVAILABLE)) {
            return getResult();
        } else {
            throw new Task.TaskErrorException(Task.NO_RESULT_AVAILABLE);
        }
    }

    public T getResult() {
        if (didSignalEvent(Task.Event.RESULT_AVAILABLE)) {
            return this.mResult;
        }
        throw new RuntimeException("Result not available");
    }

    public long getTimeSinceResult() {
        if (!didSignalEvent(Task.Event.RESULT_AVAILABLE)) {
            return -1;
        }
        return (System.nanoTime() - this.mResultTime) / 1000000;
    }

    public ResultTask<T> onCancel(Task.OnTaskEventListener onTaskEventListener) {
        super.onCancel(onTaskEventListener);
        return this;
    }

    public ResultTask<T> onComplete(Task.OnTaskEventListener onTaskEventListener) {
        super.onComplete(onTaskEventListener);
        return this;
    }

    public ResultTask<T> onFailure(Task.OnFailListener onFailListener) {
        super.onFailure(onFailListener);
        return this;
    }

    public ResultTask<T> onProgress(Task.OnProgressListener onProgressListener) {
        super.onProgress(onProgressListener);
        return this;
    }

    public ResultTask<T> onResultAvailable(final OnResultAvailableListener<T> onResultAvailableListener) {
        onEvent(Task.Event.RESULT_AVAILABLE, new Task.OnTaskEventListener() {
            public void onTaskEvent(Task task, Task.Event event) {
                ResultTask resultTask = (ResultTask) task;
                onResultAvailableListener.onResultAvailable(resultTask, event, resultTask.getResult());
                ResultTask.this.removeListenerForFail();
            }
        });
        return this;
    }

    public ResultTask<T> onSuccess(Task.OnTaskEventListener onTaskEventListener) {
        super.onSuccess(onTaskEventListener);
        return this;
    }

    public ResultTask<T> onUpdateOrResultAvailable(final OnResultAvailableListener<T> onResultAvailableListener) {
        onEvent(Task.Event.UPDATE_OR_RESULT_AVAILABLE, new Task.OnTaskEventListener() {
            public void onTaskEvent(Task task, Task.Event event) {
                ResultTask resultTask = (ResultTask) task;
                onResultAvailableListener.onResultAvailable(resultTask, event, resultTask.getResult());
            }
        });
        if (didSignalEvent(Task.Event.RESULT_AVAILABLE)) {
            onResultAvailableListener.onResultAvailable(this, Task.Event.UPDATE_OR_RESULT_AVAILABLE, this.mResult);
        }
        return this;
    }

    public void sendResult(T t) {
        setResult(t);
        super.signalEvent(Task.Event.RESULT_AVAILABLE, Task.Event.SUCCESS, Task.Event.COMPLETE);
    }

    public void setResult(T t) {
        boolean z = this.mResult != t;
        this.mResult = t;
        this.mResultTime = System.nanoTime();
        signalEvent(Task.Event.RESULT_AVAILABLE);
        if (z) {
            signalEvent(Task.Event.UPDATE_OR_RESULT_AVAILABLE);
        }
    }

    public ResultTask<T> setTimeout(long j) {
        super.setTimeout(j);
        return this;
    }
}
