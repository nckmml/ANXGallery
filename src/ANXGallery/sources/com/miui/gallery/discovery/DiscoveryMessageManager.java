package com.miui.gallery.discovery;

import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;
import com.miui.gallery.loader.DiscoveryMessageLoader;
import com.miui.gallery.model.DiscoveryMessage;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DiscoveryMessageManager {
    public static final String[] PROJECTION = {"_id", nexExportFormat.TAG_FORMAT_TYPE, "actionUri", "messageSource", "message", "title", "subTitle", "priority", "expireTime", "receiveTime", "updateTime", "triggerTime", "isConsumed", "extraData"};
    private static ArrayList<DiscoveryMessageLoader.ForceLoadContentObserver> sContentObserverList;
    private static LoaderFactory sFactory;
    private static SparseArray<BaseMessageOperator> sMessageOperators;
    private static final Object sSyncLock = new Object();

    private static class Holder {
        static final DiscoveryMessageManager INSTANCE = new DiscoveryMessageManager();
    }

    private static class LoaderFactory {
        private LoaderFactory() {
        }

        public DiscoveryMessageLoader createLoader(Context context, int i) {
            if (i != 1) {
                return null;
            }
            return new DiscoveryMessageLoader(context, 3);
        }
    }

    private class MarkMsgAsReadByTypeJob extends MarkMsgAsReadJob {
        private int mMsgType;

        MarkMsgAsReadByTypeJob(Context context, int i) {
            super(context);
            this.mMsgType = i;
        }

        /* access modifiers changed from: protected */
        public List<DiscoveryMessage> getMessages() {
            Context context = (Context) this.mContextRef.get();
            if (context != null) {
                return DiscoveryMessageManager.this.loadMessage(context, this.mMsgType);
            }
            return null;
        }
    }

    private class MarkMsgAsReadJob implements ThreadPool.Job<Void> {
        protected WeakReference<Context> mContextRef;
        private List<DiscoveryMessage> mMessages;

        MarkMsgAsReadJob(Context context) {
            this.mContextRef = new WeakReference<>(context);
        }

        MarkMsgAsReadJob(Context context, DiscoveryMessage... discoveryMessageArr) {
            this.mContextRef = new WeakReference<>(context);
            if (discoveryMessageArr != null && discoveryMessageArr.length > 0) {
                this.mMessages = Arrays.asList(discoveryMessageArr);
            }
        }

        /* access modifiers changed from: protected */
        public List<DiscoveryMessage> getMessages() {
            return this.mMessages;
        }

        public Void run(ThreadPool.JobContext jobContext) {
            Context context;
            if (jobContext == null || jobContext.isCancelled() || (context = (Context) this.mContextRef.get()) == null) {
                return null;
            }
            List<DiscoveryMessage> messages = getMessages();
            if (!MiscUtil.isValid(messages)) {
                return null;
            }
            for (DiscoveryMessage markAsRead : messages) {
                DiscoveryMessageManager.this.markAsRead(context, markAsRead);
            }
            return null;
        }
    }

    public interface MessageFilter {
        boolean accept(DiscoveryMessage discoveryMessage);
    }

    private DiscoveryMessageManager() {
        sMessageOperators = new SparseArray<>();
        sContentObserverList = new ArrayList<>();
        sFactory = new LoaderFactory();
        registerMessageOperator(1, new RecentDiscoveryMessageOperator());
    }

    private Comparator<DiscoveryMessage> createComparatorByType(int i) {
        return new Comparator<DiscoveryMessage>() {
            public int compare(DiscoveryMessage discoveryMessage, DiscoveryMessage discoveryMessage2) {
                if (discoveryMessage.getPriority() != discoveryMessage2.getPriority()) {
                    return discoveryMessage.getPriority() > discoveryMessage2.getPriority() ? 1 : -1;
                }
                if (discoveryMessage.getUpdateTime() == discoveryMessage2.getUpdateTime()) {
                    return 0;
                }
                return discoveryMessage.getUpdateTime() > discoveryMessage2.getUpdateTime() ? -1 : 1;
            }
        };
    }

    private MessageFilter createFilterByType(int i) {
        return new MessageFilter() {
            public boolean accept(DiscoveryMessage discoveryMessage) {
                return !discoveryMessage.isConsumed();
            }
        };
    }

    private BaseMessageOperator findMessageOperatorByType(int i) {
        SparseArray<BaseMessageOperator> sparseArray = sMessageOperators;
        if (sparseArray != null) {
            return sparseArray.get(i);
        }
        return null;
    }

    public static DiscoveryMessageManager getInstance() {
        return Holder.INSTANCE;
    }

    private void notifyChangeByMessageType(int i) {
        synchronized (sSyncLock) {
            Iterator<DiscoveryMessageLoader.ForceLoadContentObserver> it = sContentObserverList.iterator();
            while (it.hasNext()) {
                final DiscoveryMessageLoader.ForceLoadContentObserver next = it.next();
                if ((next.getMessageTypeMask() & i) != 0) {
                    ThreadManager.getMainHandler().post(new Runnable() {
                        public void run() {
                            next.onChange(false);
                        }
                    });
                }
            }
        }
    }

    private void registerMessageOperator(int i, BaseMessageOperator baseMessageOperator) {
        sMessageOperators.put(i, baseMessageOperator);
    }

    private void wrapMessage(DiscoveryMessage discoveryMessage, String str) {
        BaseMessageOperator findMessageOperatorByType = findMessageOperatorByType(discoveryMessage.getType());
        if (findMessageOperatorByType != null) {
            findMessageOperatorByType.wrapMessage(discoveryMessage, str);
        }
    }

    public DiscoveryMessageLoader createLoader(Context context, int i) {
        return sFactory.createLoader(context, i);
    }

    public ArrayList<DiscoveryMessage> loadMessage(Context context, int i) {
        if (context == null) {
            return null;
        }
        Cursor query = context.getContentResolver().query(GalleryContract.DiscoveryMessage.URI, PROJECTION, "(type & " + i + ") != 0", (String[]) null, (String) null);
        ArrayList<DiscoveryMessage> arrayList = new ArrayList<>();
        if (query != null) {
            try {
                Comparator<DiscoveryMessage> createComparatorByType = createComparatorByType(i);
                MessageFilter createFilterByType = createFilterByType(i);
                while (query.moveToNext()) {
                    DiscoveryMessage.Builder builder = new DiscoveryMessage.Builder();
                    builder.consumed(query.getInt(12) > 0).type(query.getInt(1)).actionUri(query.getString(2)).message(query.getString(4)).expireTime(query.getLong(8)).triggerTime(query.getLong(11)).updateTime(query.getLong(10)).title(query.getString(5)).subTitle(query.getString(6)).priority(query.getInt(7)).receiveTime(query.getLong(9)).messageSource(query.getString(3)).messageId(query.getLong(0));
                    DiscoveryMessage build = builder.build();
                    wrapMessage(build, query.getString(13));
                    if (createFilterByType != null && createFilterByType.accept(build)) {
                        arrayList.add(build);
                    }
                }
                if (createComparatorByType != null) {
                    Collections.sort(arrayList, createComparatorByType);
                }
            } catch (Exception e) {
                Log.e("DiscoveryMessageManager", "encounter error when load messages:\n%s", (Object) e.getMessage());
                e.printStackTrace();
            } catch (Throwable th) {
                query.close();
                throw th;
            }
            query.close();
        }
        return arrayList;
    }

    public void markAsRead(Context context, DiscoveryMessage discoveryMessage) {
        BaseMessageOperator findMessageOperatorByType;
        if (context != null && discoveryMessage != null && discoveryMessage.getMessageId() > 0 && (findMessageOperatorByType = findMessageOperatorByType(discoveryMessage.getType())) != null && findMessageOperatorByType.markMessageAsRead(context, discoveryMessage)) {
            notifyChangeByMessageType(discoveryMessage.getType());
        }
    }

    public void markAsReadAsync(Context context, DiscoveryMessage discoveryMessage) {
        if (context != null && discoveryMessage != null) {
            ThreadManager.getMiscPool().submit(new MarkMsgAsReadJob(context, discoveryMessage));
        }
    }

    public void markAsReadByTypeAsync(Context context, int i) {
        if (context != null) {
            ThreadManager.getMiscPool().submit(new MarkMsgAsReadByTypeJob(context, i));
        }
    }

    public void registerContentObserver(DiscoveryMessageLoader.ForceLoadContentObserver forceLoadContentObserver) {
        if (forceLoadContentObserver != null) {
            synchronized (sSyncLock) {
                if (!sContentObserverList.contains(forceLoadContentObserver)) {
                    sContentObserverList.add(forceLoadContentObserver);
                } else {
                    Log.w("DiscoveryMessageManager", "Observer [%s] is already registered.", Integer.toHexString(System.identityHashCode(forceLoadContentObserver)));
                }
            }
            return;
        }
        throw new IllegalArgumentException("The observer is null.");
    }

    public <T> void saveMessage(Context context, int i, T t) {
        BaseMessageOperator findMessageOperatorByType;
        if (context != null && t != null && (findMessageOperatorByType = findMessageOperatorByType(i)) != null) {
            try {
                if (findMessageOperatorByType.saveMessage(context, t)) {
                    notifyChangeByMessageType(i);
                }
            } catch (ClassCastException e) {
                Log.e("DiscoveryMessageManager", "Generic type saveParams doesn't match the generic type defined in concrete implementation of BaseMessageOperator");
                e.printStackTrace();
            }
        }
    }

    public void unregisterContentObserver(DiscoveryMessageLoader.ForceLoadContentObserver forceLoadContentObserver) {
        if (forceLoadContentObserver != null) {
            synchronized (sSyncLock) {
                int indexOf = sContentObserverList.indexOf(forceLoadContentObserver);
                if (indexOf == -1) {
                    Log.w("DiscoveryMessageManager", "Observer [%s] is already unregistered.", Integer.toHexString(System.identityHashCode(forceLoadContentObserver)));
                } else {
                    sContentObserverList.remove(indexOf);
                }
            }
            return;
        }
        throw new IllegalArgumentException("The observer is null.");
    }
}
