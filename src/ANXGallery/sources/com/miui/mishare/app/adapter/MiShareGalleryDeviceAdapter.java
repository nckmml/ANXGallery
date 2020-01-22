package com.miui.mishare.app.adapter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.mishare.MiShareTask;
import com.miui.mishare.R;
import com.miui.mishare.app.model.MiShareDevice;
import com.miui.mishare.app.util.MiShareFileUtil;
import com.miui.mishare.app.view.MiShareGalleryDeviceView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import miui.app.AlertDialog;

public class MiShareGalleryDeviceAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {
    /* access modifiers changed from: private */
    public DeviceRssiComparator mComparator = new DeviceRssiComparator();
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public List<MiShareDevice> mDevices = new ArrayList();
    /* access modifiers changed from: private */
    public AlertDialog mDialog;
    private Handler mHandler = new IdleHandler(this);
    private boolean mIsInitialAddDevice = true;
    /* access modifiers changed from: private */
    public OnDeviceClickListener mListener;
    private final Receiver mReceiver = new Receiver();
    private Handler mSortHandler = new Handler();
    private final Runnable mSortRunnable = new Runnable() {
        public void run() {
            if (!MiShareGalleryDeviceAdapter.this.mStopSort && MiShareGalleryDeviceAdapter.this.mDevices != null && !MiShareGalleryDeviceAdapter.this.mDevices.isEmpty()) {
                Collections.sort(MiShareGalleryDeviceAdapter.this.mDevices, MiShareGalleryDeviceAdapter.this.mComparator);
                MiShareGalleryDeviceAdapter.this.notifyDataSetChanged();
                MiShareGalleryDeviceAdapter.this.startSort();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mStopSort;

    private final class DeviceRssiComparator implements Comparator<MiShareDevice> {
        private DeviceRssiComparator() {
        }

        public int compare(MiShareDevice miShareDevice, MiShareDevice miShareDevice2) {
            int i = 0;
            int i2 = (miShareDevice == null || miShareDevice.remoteDevice == null || miShareDevice.remoteDevice.getExtras() == null) ? 0 : miShareDevice.remoteDevice.getExtras().getInt("rssi");
            if (!(miShareDevice2 == null || miShareDevice2.remoteDevice == null || miShareDevice2.remoteDevice.getExtras() == null)) {
                i = miShareDevice2.remoteDevice.getExtras().getInt("rssi");
            }
            return i - i2;
        }
    }

    private static class IdleHandler extends Handler {
        private WeakReference<MiShareGalleryDeviceAdapter> mOuter;

        IdleHandler(MiShareGalleryDeviceAdapter miShareGalleryDeviceAdapter) {
            this.mOuter = new WeakReference<>(miShareGalleryDeviceAdapter);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                String str = (String) message.obj;
                if (this.mOuter.get() != null) {
                    ((MiShareGalleryDeviceAdapter) this.mOuter.get()).setDeviceState(str, 1);
                }
            }
        }
    }

    public interface OnDeviceClickListener {
        void onCancelTask(MiShareDevice miShareDevice);

        void onDeviceTaskRetry(MiShareDevice miShareDevice);

        void onDeviceTaskStart(MiShareDevice miShareDevice);
    }

    public final class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (MiShareGalleryDeviceAdapter.this.mDialog != null && (MiShareGalleryDeviceAdapter.this.mContext instanceof Activity) && !((Activity) MiShareGalleryDeviceAdapter.this.mContext).isFinishing()) {
                MiShareGalleryDeviceAdapter.this.mDialog.dismiss();
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MiShareGalleryDeviceView mDeviceView;

        ViewHolder(MiShareGalleryDeviceView miShareGalleryDeviceView) {
            super(miShareGalleryDeviceView);
            this.mDeviceView = miShareGalleryDeviceView;
        }
    }

    public MiShareGalleryDeviceAdapter(Context context) {
        this.mContext = context;
        this.mContext.registerReceiver(this.mReceiver, new IntentFilter("com.miui.mishare.NO_NEED_CANCEL"));
    }

    private void delayIdle(String str, int i) {
        if (i == 4 || i == 3) {
            Message message = new Message();
            message.what = 1;
            message.obj = str;
            this.mHandler.sendMessageDelayed(message, 3000);
        }
    }

    private void showCancelConfirm(final MiShareDevice miShareDevice) {
        String str;
        if (miShareDevice != null && miShareDevice.remoteDevice != null && miShareDevice.remoteDevice.getExtras() != null) {
            boolean isAllImageOverview = MiShareFileUtil.isAllImageOverview(this.mContext, miShareDevice.files);
            String string = miShareDevice.remoteDevice.getExtras().getString("nickname");
            if (isAllImageOverview) {
                str = this.mContext.getResources().getString(R.string.confirm_cancel_task_images, new Object[]{string});
            } else {
                str = this.mContext.getResources().getString(R.string.confirm_cancel_task_files, new Object[]{string});
            }
            this.mDialog = new AlertDialog.Builder(this.mContext).setMessage(str).setCancelable(false).setNegativeButton(this.mContext.getResources().getString(R.string.btn_cancel), (DialogInterface.OnClickListener) null).setPositiveButton(this.mContext.getResources().getString(R.string.btn_sure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    miShareDevice.deviceStatus = 1;
                    MiShareGalleryDeviceAdapter.this.notifyDataSetChanged();
                    if (MiShareGalleryDeviceAdapter.this.mListener != null) {
                        MiShareGalleryDeviceAdapter.this.mListener.onCancelTask(miShareDevice);
                    }
                }
            }).create();
            this.mDialog.show();
        }
    }

    /* access modifiers changed from: private */
    public void startSort() {
        this.mStopSort = false;
        this.mSortHandler.postDelayed(this.mSortRunnable, 2000);
    }

    public void addOrUpdateDevice(MiShareDevice miShareDevice) {
        for (int i = 0; i < this.mDevices.size(); i++) {
            MiShareDevice miShareDevice2 = this.mDevices.get(i);
            if (TextUtils.equals(miShareDevice2.deviceId, miShareDevice.deviceId)) {
                miShareDevice.deviceStatus = miShareDevice2.deviceStatus;
                miShareDevice.taskId = miShareDevice2.taskId;
                this.mDevices.set(i, miShareDevice);
                return;
            }
        }
        this.mDevices.add(miShareDevice);
        if (this.mIsInitialAddDevice) {
            this.mIsInitialAddDevice = false;
            startSort();
        }
    }

    public boolean devicesEmpty() {
        List<MiShareDevice> list = this.mDevices;
        return list == null || list.size() == 0;
    }

    public MiShareDevice getDeviceById(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (MiShareDevice next : this.mDevices) {
            if (next != null && TextUtils.equals(next.deviceId, str)) {
                return next;
            }
        }
        return null;
    }

    public MiShareDevice getItem(int i) {
        if (i < 0 || i >= getItemCount()) {
            return null;
        }
        return this.mDevices.get(i);
    }

    public int getItemCount() {
        List<MiShareDevice> list = this.mDevices;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public long getItemId(int i) {
        return 0;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MiShareDevice item = getItem(i);
        boolean z = false;
        if (item != null) {
            boolean z2 = (item.remoteDevice == null || item.remoteDevice.getExtras() == null || !item.remoteDevice.getExtras().getBoolean("nickname_has_more")) ? false : true;
            viewHolder.mDeviceView.setDeviceType(item.deviceType);
            viewHolder.mDeviceView.setDeviceName(item.deviceName, z2);
            viewHolder.mDeviceView.setDeviceStatus(item.deviceStatus);
            boolean z3 = !item.isPC();
            viewHolder.mDeviceView.setDevicePhoneOrPc(z3);
            if (z3) {
                viewHolder.mDeviceView.setDeviceModelName(item.deviceModelName);
            } else {
                viewHolder.mDeviceView.setDeviceModelName(this.mContext.getString(R.string.pc_device_model_name));
            }
        }
        viewHolder.mDeviceView.showDivider(true);
        viewHolder.mDeviceView.showDividerStart(i == 0);
        MiShareGalleryDeviceView miShareGalleryDeviceView = viewHolder.mDeviceView;
        if (i == getItemCount() - 1) {
            z = true;
        }
        miShareGalleryDeviceView.showDividerEnd(z);
        viewHolder.mDeviceView.getIconView().setTag(R.id.position, Integer.valueOf(i));
        viewHolder.mDeviceView.getIconView().setOnClickListener(this);
    }

    public void onClick(View view) {
        OnDeviceClickListener onDeviceClickListener;
        MiShareDevice item = getItem(((Integer) view.getTag(R.id.position)).intValue());
        if (item != null) {
            int i = item.deviceStatus;
            if (i == 1) {
                OnDeviceClickListener onDeviceClickListener2 = this.mListener;
                if (onDeviceClickListener2 != null) {
                    onDeviceClickListener2.onDeviceTaskStart(item);
                }
            } else if (i == 2) {
                showCancelConfirm(item);
            } else if (i == 3) {
                OnDeviceClickListener onDeviceClickListener3 = this.mListener;
                if (onDeviceClickListener3 != null) {
                    onDeviceClickListener3.onDeviceTaskRetry(item);
                }
            } else if (i == 4 && (onDeviceClickListener = this.mListener) != null) {
                onDeviceClickListener.onDeviceTaskStart(item);
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(new MiShareGalleryDeviceView(this.mContext));
    }

    public void registerListener(OnDeviceClickListener onDeviceClickListener) {
        this.mListener = onDeviceClickListener;
    }

    public void removeDevice(String str) {
        for (int i = 0; i < this.mDevices.size(); i++) {
            if (TextUtils.equals(this.mDevices.get(i).deviceId, str)) {
                this.mDevices.remove(i);
                return;
            }
        }
    }

    public void replaceTaskId(MiShareTask miShareTask) {
        if (miShareTask != null && miShareTask.device != null) {
            for (int i = 0; i < this.mDevices.size(); i++) {
                MiShareDevice miShareDevice = this.mDevices.get(i);
                if (TextUtils.equals(miShareDevice.deviceId, miShareTask.device.getDeviceId())) {
                    miShareDevice.taskId = miShareTask.taskId;
                    return;
                }
            }
        }
    }

    public void setDeviceState(String str, int i) {
        for (MiShareDevice next : this.mDevices) {
            if (next != null && next.remoteDevice != null && TextUtils.equals(str, next.taskId)) {
                if (next.deviceStatus != 2 || i != 1) {
                    next.deviceStatus = i;
                    notifyDataSetChanged();
                    delayIdle(str, i);
                    return;
                }
                return;
            }
        }
    }

    public void setInitialAddDevice() {
        this.mIsInitialAddDevice = true;
    }

    public void stopSort() {
        this.mStopSort = true;
        this.mSortHandler.removeCallbacks(this.mSortRunnable);
    }

    public void unregister() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }
}
