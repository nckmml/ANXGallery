package com.miui.gallery.projection;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.R;
import miui.app.AlertDialog;
import miui.app.ProgressDialog;

public class DeviceListController implements DialogInterface.OnClickListener {
    public static String MOBILE_NAME;
    private int mActiveDeviceIndex = -1;
    private WeakReference<Activity> mActivityWeakReference;
    /* access modifiers changed from: private */
    public DeviceSelectorAdapter mDeviceAdapter;
    private ArrayList<String> mDeviceList = new ArrayList<>();
    /* access modifiers changed from: private */
    public AlertDialog mDeviceListDialog;
    /* access modifiers changed from: private */
    public AlertDialog mDeviceNotFoundDialog;
    /* access modifiers changed from: private */
    public ProgressDialog mDeviceScanDialog;
    private OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public AtomicBoolean mNeedShowDeviceList = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public Runnable mScanTimeoutRunnable = new Runnable() {
        public void run() {
            DeviceListController deviceListController = DeviceListController.this;
            deviceListController.dismissSafely(deviceListController.mDeviceScanDialog);
            if (!DeviceListController.this.hasAirkanDevice()) {
                DeviceListController.this.showDeviceNotFoundDialog();
            }
        }
    };

    private static class DeviceSelectorAdapter extends BaseAdapter {
        private ArrayList<String> mDeviceList;
        private LayoutInflater mLayoutInflater;

        public DeviceSelectorAdapter(Context context, ArrayList<String> arrayList) {
            this.mDeviceList = arrayList;
            this.mLayoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public int getCount() {
            ArrayList<String> arrayList = this.mDeviceList;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        public String getItem(int i) {
            ArrayList<String> arrayList = this.mDeviceList;
            if (arrayList == null || i >= arrayList.size()) {
                return null;
            }
            return this.mDeviceList.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mLayoutInflater.inflate(R.layout.select_dialog_singlechoice, viewGroup, false);
            }
            ((TextView) view.findViewById(16908308)).setText(getItem(i));
            return view;
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(String str);
    }

    private boolean activityAlive() {
        WeakReference<Activity> weakReference = this.mActivityWeakReference;
        return weakReference != null && weakReference.get() != null && !((Activity) this.mActivityWeakReference.get()).isDestroyed() && !((Activity) this.mActivityWeakReference.get()).isFinishing();
    }

    /* access modifiers changed from: private */
    public void dismissSafely(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void invalidate() {
        ThreadManager.getMainHandler().removeCallbacks(this.mScanTimeoutRunnable);
        ThreadManager.runOnMainThread(new Runnable() {
            public void run() {
                if (DeviceListController.this.mDeviceAdapter != null) {
                    DeviceListController.this.mDeviceAdapter.notifyDataSetChanged();
                }
                if (!DeviceListController.this.mNeedShowDeviceList.get()) {
                    return;
                }
                if (DeviceListController.this.hasAirkanDevice()) {
                    DeviceListController deviceListController = DeviceListController.this;
                    deviceListController.dismissSafely(deviceListController.mDeviceScanDialog);
                    DeviceListController deviceListController2 = DeviceListController.this;
                    deviceListController2.dismissSafely(deviceListController2.mDeviceNotFoundDialog);
                    DeviceListController.this.showDeviceListDialog();
                    return;
                }
                DeviceListController deviceListController3 = DeviceListController.this;
                deviceListController3.dismissSafely(deviceListController3.mDeviceListDialog);
                if (DeviceListController.this.mDeviceNotFoundDialog == null || !DeviceListController.this.mDeviceNotFoundDialog.isShowing()) {
                    DeviceListController.this.showDeviceNotFoundDialog();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean isActive() {
        return this.mActiveDeviceIndex != -1;
    }

    /* access modifiers changed from: private */
    public void showDeviceListDialog() {
        if (activityAlive()) {
            AlertDialog alertDialog = this.mDeviceListDialog;
            int i = com.miui.gallery.R.string.projection_stop;
            if (alertDialog == null) {
                if (this.mDeviceAdapter == null) {
                    this.mDeviceAdapter = new DeviceSelectorAdapter((Context) this.mActivityWeakReference.get(), this.mDeviceList);
                }
                AlertDialog.Builder onCancelListener = new AlertDialog.Builder((Context) this.mActivityWeakReference.get()).setTitle(com.miui.gallery.R.string.projection_multi_title).setSingleChoiceItems(this.mDeviceAdapter, this.mActiveDeviceIndex, this).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        DeviceListController.this.mNeedShowDeviceList.set(false);
                    }
                });
                if (!isActive()) {
                    i = com.miui.gallery.R.string.projection_cancel;
                }
                this.mDeviceListDialog = onCancelListener.setNegativeButton(i, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (DeviceListController.this.isActive()) {
                            DeviceListController.this.switchTo(-1);
                        }
                    }
                }).create();
            } else {
                Button button = alertDialog.getButton(-2);
                if (!isActive()) {
                    i = com.miui.gallery.R.string.projection_cancel;
                }
                button.setText(i);
            }
            if (!this.mDeviceListDialog.isShowing()) {
                this.mDeviceListDialog.show();
            }
            ListView listView = this.mDeviceListDialog.getListView();
            listView.setItemChecked(this.mActiveDeviceIndex, true);
            listView.setSelection(this.mActiveDeviceIndex);
        }
    }

    /* access modifiers changed from: private */
    public void showDeviceNotFoundDialog() {
        if (activityAlive()) {
            if (this.mDeviceNotFoundDialog == null) {
                this.mDeviceNotFoundDialog = new AlertDialog.Builder((Context) this.mActivityWeakReference.get()).setTitle(com.miui.gallery.R.string.cast_devices_unavailable_title).setMessage(com.miui.gallery.R.string.cast_devices_unavailable_desc).setCancelable(true).setPositiveButton(com.miui.gallery.R.string.have_known, (DialogInterface.OnClickListener) null).setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        DeviceListController.this.mNeedShowDeviceList.set(false);
                    }
                }).create();
            }
            this.mDeviceNotFoundDialog.show();
        }
    }

    private void showScanDialog() {
        if (activityAlive()) {
            if (this.mDeviceScanDialog == null) {
                this.mDeviceScanDialog = new ProgressDialog((Context) this.mActivityWeakReference.get());
                this.mDeviceScanDialog.setIndeterminate(true);
                this.mDeviceScanDialog.setMessage(((Activity) this.mActivityWeakReference.get()).getString(com.miui.gallery.R.string.searching_cast_devices));
                this.mDeviceScanDialog.setCancelable(true);
                this.mDeviceScanDialog.setCanceledOnTouchOutside(false);
                this.mDeviceScanDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        ThreadManager.getMainHandler().removeCallbacks(DeviceListController.this.mScanTimeoutRunnable);
                        DeviceListController.this.mNeedShowDeviceList.set(false);
                    }
                });
            }
            this.mDeviceScanDialog.show();
            ThreadManager.getMainHandler().removeCallbacks(this.mScanTimeoutRunnable);
            ThreadManager.getMainHandler().postDelayed(this.mScanTimeoutRunnable, 5000);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void switchTo(int i) {
        if (this.mItemClickListener != null) {
            if (i >= this.mDeviceList.size()) {
                dismissSafely(this.mDeviceListDialog);
                return;
            }
            this.mActiveDeviceIndex = i;
            this.mItemClickListener.onItemClicked(this.mActiveDeviceIndex == -1 ? MOBILE_NAME : this.mDeviceList.get(this.mActiveDeviceIndex));
        }
    }

    public synchronized void addNewDevice(String str, boolean z) {
        ThreadManager.getMainHandler().removeCallbacks(this.mScanTimeoutRunnable);
        if (!this.mDeviceList.contains(str)) {
            this.mDeviceList.add(str);
            if (this.mDeviceScanDialog != null && this.mDeviceScanDialog.isShowing()) {
                this.mDeviceScanDialog.dismiss();
            }
            if (z) {
                this.mActiveDeviceIndex = this.mDeviceList.size() - 1;
            }
            invalidate();
        }
    }

    public void attachActivity(WeakReference<Activity> weakReference) {
        if (weakReference == null || weakReference.get() == null) {
            Log.e("projection_device_list", "null activity for device list controller");
            return;
        }
        this.mActivityWeakReference = weakReference;
        this.mDeviceAdapter = null;
        this.mDeviceListDialog = null;
        this.mDeviceScanDialog = null;
        this.mDeviceNotFoundDialog = null;
        MOBILE_NAME = ((Activity) this.mActivityWeakReference.get()).getString(com.miui.gallery.R.string.projection_local_mobile);
    }

    public void detachActivity(WeakReference<Activity> weakReference) {
        if (this.mActivityWeakReference != null) {
            if (weakReference == null || weakReference.get() == null || !((Activity) this.mActivityWeakReference.get()).equals(weakReference.get())) {
                Log.e("projection_device_list", "null activity for device list controller");
            } else {
                this.mActivityWeakReference = null;
            }
        }
    }

    public synchronized void dismiss() {
        ThreadManager.getMainHandler().removeCallbacks(this.mScanTimeoutRunnable);
        this.mNeedShowDeviceList.set(false);
        dismissSafely(this.mDeviceScanDialog);
        dismissSafely(this.mDeviceListDialog);
        dismissSafely(this.mDeviceNotFoundDialog);
    }

    public synchronized boolean hasAirkanDevice() {
        return this.mDeviceList.size() > 0;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switchTo(i);
    }

    public void removeActive() {
        if (this.mActiveDeviceIndex != -1) {
            Log.v("projection_device_list", "~~~~remove the active for device");
            this.mActiveDeviceIndex = -1;
        }
    }

    public synchronized boolean removeDevice(String str) {
        boolean z;
        int size = this.mDeviceList.size();
        z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            } else if (this.mDeviceList.get(i).equals(str)) {
                this.mDeviceList.remove(str);
                if (this.mActiveDeviceIndex == i) {
                    this.mActiveDeviceIndex = -1;
                    z = true;
                }
                invalidate();
            } else {
                i++;
            }
        }
        return z;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public synchronized void show() {
        dismiss();
        this.mNeedShowDeviceList.set(true);
        if (!MiscUtil.isValid(this.mDeviceList)) {
            showScanDialog();
        } else {
            showDeviceListDialog();
        }
    }
}
