package com.xiaomi.push.service;

import android.content.SharedPreferences;
import android.util.Base64;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.xiaomi.channel.commonutils.android.DeviceInfo;
import com.xiaomi.channel.commonutils.android.SystemUtils;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.channel.commonutils.network.NameValuePair;
import com.xiaomi.network.HttpUtils;
import com.xiaomi.push.protobuf.ChannelConfig;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.smack.util.TaskExecutor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ServiceConfig {
    private static String sDeviceUUID;
    private static ServiceConfig sInstance = new ServiceConfig();
    /* access modifiers changed from: private */
    public ChannelConfig.PushServiceConfig mConfig;
    /* access modifiers changed from: private */
    public List<Listener> mListener = new ArrayList();
    /* access modifiers changed from: private */
    public SerializedAsyncTaskProcessor.SerializedAsyncTask mPendingFetchTask;

    public static abstract class Listener {
        public void onConfigChange(ChannelConfig.PushServiceConfig pushServiceConfig) {
        }

        public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg pushServiceConfigMsg) {
        }
    }

    private ServiceConfig() {
    }

    private void checkLoad() {
        if (this.mConfig == null) {
            load();
        }
    }

    private void fetchConfig() {
        if (this.mPendingFetchTask == null) {
            this.mPendingFetchTask = new SerializedAsyncTaskProcessor.SerializedAsyncTask() {
                boolean success = false;

                public void postProcess() {
                    Listener[] listenerArr;
                    SerializedAsyncTaskProcessor.SerializedAsyncTask unused = ServiceConfig.this.mPendingFetchTask = null;
                    if (this.success) {
                        synchronized (ServiceConfig.this) {
                            listenerArr = (Listener[]) ServiceConfig.this.mListener.toArray(new Listener[ServiceConfig.this.mListener.size()]);
                        }
                        for (Listener onConfigChange : listenerArr) {
                            onConfigChange.onConfigChange(ServiceConfig.this.mConfig);
                        }
                    }
                }

                public void process() {
                    try {
                        ChannelConfig.PushServiceConfig parseFrom = ChannelConfig.PushServiceConfig.parseFrom(Base64.decode(HttpUtils.get(SystemUtils.getContext(), "http://resolver.msg.xiaomi.net/psc/?t=a", (List<NameValuePair>) null), 10));
                        if (parseFrom != null) {
                            ChannelConfig.PushServiceConfig unused = ServiceConfig.this.mConfig = parseFrom;
                            this.success = true;
                            ServiceConfig.this.save();
                        }
                    } catch (Exception e) {
                        MyLog.w("fetch config failure: " + e.getMessage());
                    }
                }
            };
            TaskExecutor.execute(this.mPendingFetchTask);
        }
    }

    public static synchronized String getDeviceUUID() {
        String str;
        synchronized (ServiceConfig.class) {
            if (sDeviceUUID == null) {
                SharedPreferences sharedPreferences = SystemUtils.getContext().getSharedPreferences("XMPushServiceConfig", 0);
                sDeviceUUID = sharedPreferences.getString("DeviceUUID", (String) null);
                if (sDeviceUUID == null) {
                    sDeviceUUID = DeviceInfo.getDeviceId(SystemUtils.getContext(), false);
                    if (sDeviceUUID != null) {
                        sharedPreferences.edit().putString("DeviceUUID", sDeviceUUID).commit();
                    }
                }
            }
            str = sDeviceUUID;
        }
        return str;
    }

    public static ServiceConfig getInstance() {
        return sInstance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    private void load() {
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(SystemUtils.getContext().openFileInput("XMCloudCfg"));
            try {
                this.mConfig = ChannelConfig.PushServiceConfig.parseFrom(CodedInputStreamMicro.newInstance((InputStream) bufferedInputStream2));
                bufferedInputStream2.close();
                IOUtils.closeQuietly(bufferedInputStream2);
            } catch (Exception e) {
                e = e;
                bufferedInputStream = bufferedInputStream2;
                try {
                    MyLog.w("load config failure: " + e.getMessage());
                    IOUtils.closeQuietly(bufferedInputStream);
                    if (this.mConfig == null) {
                    }
                } catch (Throwable th) {
                    th = th;
                    IOUtils.closeQuietly(bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                IOUtils.closeQuietly(bufferedInputStream);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            MyLog.w("load config failure: " + e.getMessage());
            IOUtils.closeQuietly(bufferedInputStream);
            if (this.mConfig == null) {
            }
        }
        if (this.mConfig == null) {
            this.mConfig = new ChannelConfig.PushServiceConfig();
        }
    }

    /* access modifiers changed from: private */
    public void save() {
        try {
            if (this.mConfig != null) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(SystemUtils.getContext().openFileOutput("XMCloudCfg", 0));
                CodedOutputStreamMicro newInstance = CodedOutputStreamMicro.newInstance((OutputStream) bufferedOutputStream);
                this.mConfig.writeTo(newInstance);
                newInstance.flush();
                bufferedOutputStream.close();
            }
        } catch (Exception e) {
            MyLog.w("save config failure: " + e.getMessage());
        }
    }

    public synchronized void addListener(Listener listener) {
        this.mListener.add(listener);
    }

    /* access modifiers changed from: package-private */
    public synchronized void clear() {
        this.mListener.clear();
    }

    public ChannelConfig.PushServiceConfig getConfig() {
        checkLoad();
        return this.mConfig;
    }

    /* access modifiers changed from: package-private */
    public int getConfigVersion() {
        checkLoad();
        ChannelConfig.PushServiceConfig pushServiceConfig = this.mConfig;
        if (pushServiceConfig != null) {
            return pushServiceConfig.getConfigVersion();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void handle(ChannelMessage.PushServiceConfigMsg pushServiceConfigMsg) {
        Listener[] listenerArr;
        if (pushServiceConfigMsg.hasCloudVersion() && pushServiceConfigMsg.getCloudVersion() > getConfigVersion()) {
            fetchConfig();
        }
        synchronized (this) {
            listenerArr = (Listener[]) this.mListener.toArray(new Listener[this.mListener.size()]);
        }
        for (Listener onConfigMsgReceive : listenerArr) {
            onConfigMsgReceive.onConfigMsgReceive(pushServiceConfigMsg);
        }
    }
}
