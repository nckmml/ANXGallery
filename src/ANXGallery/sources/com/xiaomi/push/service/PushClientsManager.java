package com.xiaomi.push.service;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PushClientsManager {
    private static PushClientsManager sInstance;
    private List<ClientChangeListener> clientChangeListeners = new ArrayList();
    private ConcurrentHashMap<String, HashMap<String, ClientLoginInfo>> mActiveClients = new ConcurrentHashMap<>();

    public interface ClientChangeListener {
        void onChange();
    }

    public static class ClientLoginInfo {
        public String authMethod;
        public String chid;
        public String clientExtra;
        public String cloudExtra;
        public Context context;
        private int currentRetrys = 0;
        private boolean hasPeerSupport = false;
        public boolean kick;
        public ClientEventDispatcher mClientEventDispatcher;
        /* access modifiers changed from: private */
        public XMPushService mPushService;
        ClientStatus notifiedStatus = null;
        final NotifyClientJob notifyClientJob = new NotifyClientJob();
        Messenger peer;
        IBinder.DeathRecipient peerWatcher = null;
        public String pkgName;
        public String security;
        public String session;
        ClientStatus status = ClientStatus.unbind;
        private List<ClientStatusListener> statusChangeListeners = new ArrayList();
        /* access modifiers changed from: private */
        public XMPushService.BindTimeoutJob timeOutJob = new XMPushService.BindTimeoutJob(this);
        public String token;
        public String userId;

        public interface ClientStatusListener {
            void onChange(ClientStatus clientStatus, ClientStatus clientStatus2, int i);
        }

        class NotifyClientJob extends XMPushService.Job {
            String errorType;
            int notifyType;
            int reason;
            String reasonMessage;

            public NotifyClientJob() {
                super(0);
            }

            public XMPushService.Job build(int i, int i2, String str, String str2) {
                this.notifyType = i;
                this.reason = i2;
                this.errorType = str2;
                this.reasonMessage = str;
                return this;
            }

            public String getDesc() {
                return "notify job";
            }

            public void process() {
                if (ClientLoginInfo.this.shouldNotifyClient(this.notifyType, this.reason, this.errorType)) {
                    ClientLoginInfo.this.notifyClientStatus(this.notifyType, this.reason, this.reasonMessage, this.errorType);
                    return;
                }
                MyLog.i(" ignore notify client :" + ClientLoginInfo.this.chid);
            }
        }

        class PeerWatcher implements IBinder.DeathRecipient {
            final ClientLoginInfo info;
            final Messenger peer;

            PeerWatcher(ClientLoginInfo clientLoginInfo, Messenger messenger) {
                this.info = clientLoginInfo;
                this.peer = messenger;
            }

            public void binderDied() {
                MyLog.i("peer died, chid = " + this.info.chid);
                ClientLoginInfo.this.mPushService.executeJobDelayed(new XMPushService.Job(0) {
                    public String getDesc() {
                        return "clear peer job";
                    }

                    public void process() {
                        if (PeerWatcher.this.peer == PeerWatcher.this.info.peer) {
                            MyLog.i("clean peer, chid = " + PeerWatcher.this.info.chid);
                            PeerWatcher.this.info.peer = null;
                        }
                    }
                }, 0);
                if ("9".equals(this.info.chid) && "com.xiaomi.xmsf".equals(ClientLoginInfo.this.mPushService.getPackageName())) {
                    ClientLoginInfo.this.mPushService.executeJobDelayed(new XMPushService.Job(0) {
                        public String getDesc() {
                            return "check peer job";
                        }

                        public void process() {
                            if (PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(PeerWatcher.this.info.chid, PeerWatcher.this.info.userId).peer == null) {
                                ClientLoginInfo.this.mPushService.closeChannel(PeerWatcher.this.info.chid, PeerWatcher.this.info.userId, 2, (String) null, (String) null);
                            }
                        }
                    }, 60000);
                }
            }
        }

        public ClientLoginInfo() {
        }

        public ClientLoginInfo(XMPushService xMPushService) {
            this.mPushService = xMPushService;
            addClientStatusListener(new ClientStatusListener() {
                public void onChange(ClientStatus clientStatus, ClientStatus clientStatus2, int i) {
                    if (clientStatus2 == ClientStatus.binding) {
                        ClientLoginInfo.this.mPushService.executeJobDelayed(ClientLoginInfo.this.timeOutJob, 60000);
                    } else {
                        ClientLoginInfo.this.mPushService.removeJobs((XMPushService.Job) ClientLoginInfo.this.timeOutJob);
                    }
                }
            });
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
            r0 = r3.lastIndexOf("/");
         */
        public static String getResource(String str) {
            int lastIndexOf;
            return (!TextUtils.isEmpty(str) && lastIndexOf != -1) ? str.substring(lastIndexOf + 1) : "";
        }

        private boolean isSpeicalErr(int i, int i2, String str) {
            if (i == 1) {
                return this.status != ClientStatus.binded && this.mPushService.isConnected() && i2 != 21 && (i2 != 7 || !"wait".equals(str));
            }
            if (i == 2) {
                return this.mPushService.isConnected();
            }
            if (i != 3) {
                return false;
            }
            return !"wait".equals(str);
        }

        /* access modifiers changed from: private */
        public void notifyClientStatus(int i, int i2, String str, String str2) {
            ClientStatus clientStatus = this.status;
            this.notifiedStatus = clientStatus;
            if (i == 2) {
                this.mClientEventDispatcher.notifyChannelClosed(this.context, this, i2);
            } else if (i == 3) {
                this.mClientEventDispatcher.notifyKickedByServer(this.context, this, str2, str);
            } else if (i == 1) {
                boolean z = clientStatus == ClientStatus.binded;
                if (!z && "wait".equals(str2)) {
                    this.currentRetrys++;
                } else if (z) {
                    this.currentRetrys = 0;
                    if (this.peer != null) {
                        try {
                            this.peer.send(Message.obtain((Handler) null, 16, this.mPushService.messenger));
                        } catch (RemoteException unused) {
                        }
                    }
                }
                this.mClientEventDispatcher.notifyChannelOpenResult(this.mPushService, this, z, i2, str);
            }
        }

        /* access modifiers changed from: private */
        public boolean shouldNotifyClient(int i, int i2, String str) {
            boolean z;
            ClientStatus clientStatus = this.notifiedStatus;
            if (clientStatus == null || !(z = this.hasPeerSupport)) {
                return true;
            }
            if (clientStatus == this.status) {
                MyLog.i(" status recovered, don't notify client:" + this.chid);
                return false;
            } else if (this.peer == null || !z) {
                MyLog.i("peer died, ignore notify " + this.chid);
                return false;
            } else {
                MyLog.i("Peer alive notify status to client:" + this.chid);
                return true;
            }
        }

        public void addClientStatusListener(ClientStatusListener clientStatusListener) {
            synchronized (this.statusChangeListeners) {
                this.statusChangeListeners.add(clientStatusListener);
            }
        }

        public String getDesc(int i) {
            return i != 1 ? i != 2 ? i != 3 ? "unknown" : "KICK" : "CLOSE" : "OPEN";
        }

        public long getNextRetryInterval() {
            return (((long) ((Math.random() * 20.0d) - 10.0d)) + ((long) ((this.currentRetrys + 1) * 15))) * 1000;
        }

        public void removeClientStatusListener(ClientStatusListener clientStatusListener) {
            synchronized (this.statusChangeListeners) {
                this.statusChangeListeners.remove(clientStatusListener);
            }
        }

        public void setStatus(ClientStatus clientStatus, int i, int i2, String str, String str2) {
            boolean z;
            synchronized (this.statusChangeListeners) {
                for (ClientStatusListener onChange : this.statusChangeListeners) {
                    onChange.onChange(this.status, clientStatus, i2);
                }
            }
            ClientStatus clientStatus2 = this.status;
            int i3 = 0;
            if (clientStatus2 != clientStatus) {
                MyLog.w(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", new Object[]{clientStatus2, clientStatus, getDesc(i), PushConstants.getErrorDesc(i2), str, str2, this.chid}));
                this.status = clientStatus;
            }
            if (this.mClientEventDispatcher == null) {
                MyLog.e("status changed while the client dispatcher is missing");
            } else if (clientStatus != ClientStatus.binding) {
                if (this.notifiedStatus != null && (z = this.hasPeerSupport)) {
                    i3 = (this.peer == null || !z) ? 10100 : 1000;
                }
                this.mPushService.removeJobs((XMPushService.Job) this.notifyClientJob);
                if (isSpeicalErr(i, i2, str2)) {
                    notifyClientStatus(i, i2, str, str2);
                } else {
                    this.mPushService.executeJobDelayed(this.notifyClientJob.build(i, i2, str, str2), (long) i3);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void unwatch() {
            try {
                Messenger messenger = this.peer;
                if (!(messenger == null || this.peerWatcher == null)) {
                    messenger.getBinder().unlinkToDeath(this.peerWatcher, 0);
                }
            } catch (Exception unused) {
            }
            this.notifiedStatus = null;
        }

        /* access modifiers changed from: package-private */
        public void watch(Messenger messenger) {
            unwatch();
            if (messenger != null) {
                try {
                    this.peer = messenger;
                    this.hasPeerSupport = true;
                    this.peerWatcher = new PeerWatcher(this, messenger);
                    messenger.getBinder().linkToDeath(this.peerWatcher, 0);
                } catch (Exception e) {
                    MyLog.i("peer linkToDeath err: " + e.getMessage());
                    this.peer = null;
                    this.hasPeerSupport = false;
                }
            } else {
                MyLog.i("peer linked with old sdk chid = " + this.chid);
            }
        }
    }

    public enum ClientStatus {
        unbind,
        binding,
        binded
    }

    private PushClientsManager() {
    }

    public static synchronized PushClientsManager getInstance() {
        PushClientsManager pushClientsManager;
        synchronized (PushClientsManager.class) {
            if (sInstance == null) {
                sInstance = new PushClientsManager();
            }
            pushClientsManager = sInstance;
        }
        return pushClientsManager;
    }

    private String getSmtpLocalPart(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("@");
        return indexOf > 0 ? str.substring(0, indexOf) : str;
    }

    public synchronized void addActiveClient(ClientLoginInfo clientLoginInfo) {
        HashMap hashMap = this.mActiveClients.get(clientLoginInfo.chid);
        if (hashMap == null) {
            hashMap = new HashMap();
            this.mActiveClients.put(clientLoginInfo.chid, hashMap);
        }
        hashMap.put(getSmtpLocalPart(clientLoginInfo.userId), clientLoginInfo);
        for (ClientChangeListener onChange : this.clientChangeListeners) {
            onChange.onChange();
        }
    }

    public synchronized void addClientChangeListener(ClientChangeListener clientChangeListener) {
        this.clientChangeListeners.add(clientChangeListener);
    }

    public synchronized void deactivateAllClientByChid(String str) {
        HashMap hashMap = this.mActiveClients.get(str);
        if (hashMap != null) {
            for (ClientLoginInfo unwatch : hashMap.values()) {
                unwatch.unwatch();
            }
            hashMap.clear();
            this.mActiveClients.remove(str);
        }
        for (ClientChangeListener onChange : this.clientChangeListeners) {
            onChange.onChange();
        }
    }

    public synchronized void deactivateClient(String str, String str2) {
        HashMap hashMap = this.mActiveClients.get(str);
        if (hashMap != null) {
            ClientLoginInfo clientLoginInfo = (ClientLoginInfo) hashMap.get(getSmtpLocalPart(str2));
            if (clientLoginInfo != null) {
                clientLoginInfo.unwatch();
            }
            hashMap.remove(getSmtpLocalPart(str2));
            if (hashMap.isEmpty()) {
                this.mActiveClients.remove(str);
            }
        }
        for (ClientChangeListener onChange : this.clientChangeListeners) {
            onChange.onChange();
        }
    }

    public synchronized int getActiveClientCount() {
        return this.mActiveClients.size();
    }

    public synchronized Collection<ClientLoginInfo> getAllClientLoginInfoByChid(String str) {
        if (!this.mActiveClients.containsKey(str)) {
            return new ArrayList();
        }
        return ((HashMap) this.mActiveClients.get(str).clone()).values();
    }

    public synchronized ArrayList<ClientLoginInfo> getAllClients() {
        ArrayList<ClientLoginInfo> arrayList;
        arrayList = new ArrayList<>();
        for (HashMap<String, ClientLoginInfo> values : this.mActiveClients.values()) {
            arrayList.addAll(values.values());
        }
        return arrayList;
    }

    public synchronized ClientLoginInfo getClientLoginInfoByChidAndUserId(String str, String str2) {
        HashMap hashMap = this.mActiveClients.get(str);
        if (hashMap == null) {
            return null;
        }
        return (ClientLoginInfo) hashMap.get(getSmtpLocalPart(str2));
    }

    public synchronized void notifyConnectionFailed(Context context) {
        for (HashMap<String, ClientLoginInfo> values : this.mActiveClients.values()) {
            for (ClientLoginInfo status : values.values()) {
                status.setStatus(ClientStatus.unbind, 1, 3, (String) null, (String) null);
            }
        }
    }

    public synchronized List<String> queryChannelIdByPackage(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (HashMap<String, ClientLoginInfo> values : this.mActiveClients.values()) {
            for (ClientLoginInfo clientLoginInfo : values.values()) {
                if (str.equals(clientLoginInfo.pkgName)) {
                    arrayList.add(clientLoginInfo.chid);
                }
            }
        }
        return arrayList;
    }

    public synchronized void removeActiveClients() {
        Iterator<ClientLoginInfo> it = getAllClients().iterator();
        while (it.hasNext()) {
            it.next().unwatch();
        }
        this.mActiveClients.clear();
    }

    public synchronized void removeAllClientChangeListeners() {
        this.clientChangeListeners.clear();
    }

    public synchronized void resetAllClients(Context context, int i) {
        for (HashMap<String, ClientLoginInfo> values : this.mActiveClients.values()) {
            for (ClientLoginInfo status : values.values()) {
                status.setStatus(ClientStatus.unbind, 2, i, (String) null, (String) null);
            }
        }
    }
}
