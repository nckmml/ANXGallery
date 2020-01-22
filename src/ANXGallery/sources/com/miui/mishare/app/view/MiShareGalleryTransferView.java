package com.miui.mishare.app.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.mishare.DeviceModel;
import com.miui.mishare.IMiShareDiscoverCallback;
import com.miui.mishare.IMiShareStateListener;
import com.miui.mishare.IMiShareTaskStateListener;
import com.miui.mishare.MiShareTask;
import com.miui.mishare.R;
import com.miui.mishare.RemoteDevice;
import com.miui.mishare.app.adapter.MiShareGalleryDeviceAdapter;
import com.miui.mishare.app.connect.MiShareGalleryConnectivity;
import com.miui.mishare.app.model.MiShareDevice;
import com.miui.mishare.app.util.MiShareFileUtil;
import com.miui.mishare.app.util.PrintHelper;
import java.io.FileNotFoundException;
import java.util.List;

public class MiShareGalleryTransferView extends LinearLayout implements View.OnClickListener, MiShareGalleryDeviceAdapter.OnDeviceClickListener, MiShareGalleryConnectivity.ServiceBindCallBack {
    /* access modifiers changed from: private */
    public MiShareGalleryDeviceAdapter mAdapter;
    private MiShareGalleryConnectivity mConnectivity;
    private int mCurrentDeviceStatus;
    private String mDeviceId;
    /* access modifiers changed from: private */
    public RecyclerView mDeviceLv;
    private final IMiShareDiscoverCallback.Stub mDiscoverCallback = new IMiShareDiscoverCallback.Stub() {
        public void onDeviceLost(final String str) throws RemoteException {
            MiShareGalleryTransferView.this.mHandler.post(new Runnable() {
                public void run() {
                    MiShareGalleryTransferView.this.mAdapter.removeDevice(str);
                    MiShareGalleryTransferView.this.mAdapter.notifyDataSetChanged();
                    if (MiShareGalleryTransferView.this.mAdapter.devicesEmpty()) {
                        MiShareGalleryTransferView.this.setDiscoveringView();
                    }
                }
            });
        }

        public void onDeviceUpdated(final RemoteDevice remoteDevice) throws RemoteException {
            MiShareGalleryTransferView.this.mHandler.post(new Runnable() {
                public void run() {
                    RemoteDevice remoteDevice = remoteDevice;
                    if (remoteDevice != null && remoteDevice.getExtras() != null) {
                        MiShareGalleryTransferView.this.mDividerView.setVisibility(4);
                        Bundle extras = remoteDevice.getExtras();
                        extras.setClassLoader(getClass().getClassLoader());
                        MiShareDevice miShareDevice = new MiShareDevice(extras.getInt("sgnt"));
                        miShareDevice.files = MiShareGalleryTransferView.this.mFiles;
                        RemoteDevice remoteDevice2 = remoteDevice;
                        miShareDevice.remoteDevice = remoteDevice2;
                        miShareDevice.deviceId = remoteDevice2.getDeviceId();
                        miShareDevice.deviceModelName = DeviceModel.getDeviceName(MiShareGalleryTransferView.this.getContext(), extras.getByte("mc"), extras.getInt("dc"));
                        miShareDevice.deviceType = MiShareGalleryTransferView.this.getDeviceType(extras.getByte("mc"));
                        miShareDevice.deviceName = extras.getString("nickname");
                        MiShareGalleryTransferView.this.mMiShareDisabled.setVisibility(4);
                        MiShareGalleryTransferView.this.mDeviceLv.setVisibility(0);
                        MiShareGalleryTransferView.this.mAdapter.addOrUpdateDevice(miShareDevice);
                        MiShareGalleryTransferView.this.mAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public View mDividerView;
    private View mEnableMiShareView;
    /* access modifiers changed from: private */
    public List<Uri> mFiles;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    private FilesNotYetSetListener mHasNoFilesListener;
    private Intent mIntent;
    private boolean mIsPrint;
    private View.OnClickListener mMiPrintClick;
    /* access modifiers changed from: private */
    public View mMiShareDisabled;
    private ImageButton mPrintBtn;
    private boolean mPrintEnabled;
    private ImageButton mScreenThrow;
    private View.OnClickListener mScreenThrowClick;
    private MiShareTaskStateReceiver mShareTaskStateReceive;
    /* access modifiers changed from: private */
    public int mState = 0;
    private IMiShareStateListener mStateListener = new IMiShareStateListener.Stub() {
        public void onStateChanged(final int i) throws RemoteException {
            ((Activity) MiShareGalleryTransferView.this.getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    MiShareGalleryTransferView.this.refreshView(MiShareGalleryTransferView.this.mState, i);
                    int unused = MiShareGalleryTransferView.this.mState = i;
                }
            });
        }
    };
    private final IMiShareTaskStateListener.Stub mTaskStateListener = new IMiShareTaskStateListener.Stub() {
        public void onTaskIdChanged(final MiShareTask miShareTask) throws RemoteException {
            MiShareGalleryTransferView.this.mHandler.post(new Runnable() {
                public void run() {
                    MiShareGalleryTransferView.this.mAdapter.replaceTaskId(miShareTask);
                }
            });
        }

        public void onTaskStateChanged(String str, int i) throws RemoteException {
        }
    };
    private TextView mTipsTv;

    public interface FilesNotYetSetListener {
        void fileNotYetSet();
    }

    private final class MiShareTaskStateReceiver extends BroadcastReceiver {
        private MiShareTaskStateReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "com.miui.mishare.connectivity.TASK_STATE".equals(intent.getAction())) {
                MiShareGalleryTransferView.this.mAdapter.setDeviceState(intent.getStringExtra("device_id"), intent.getIntExtra("state", 1));
            }
        }
    }

    public MiShareGalleryTransferView(Context context) {
        super(context);
        init();
    }

    public MiShareGalleryTransferView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0015, code lost:
        r0 = r4.mFiles.get(0);
     */
    private boolean canPrint() {
        Uri uri;
        List<Uri> list = this.mFiles;
        return (list == null || list.size() > 1 || this.mFiles.size() == 0 || uri == null || !MiShareFileUtil.isImageCanPrint(getContext(), uri)) ? false : true;
    }

    private void doPrint() {
        List<Uri> list = this.mFiles;
        if (list != null && list.size() == 1) {
            PrintHelper printHelper = new PrintHelper(getContext());
            printHelper.setScaleMode(1);
            Uri uri = this.mFiles.get(0);
            String fileNameFromUri = MiShareFileUtil.getFileNameFromUri(getContext(), uri);
            if (MiShareFileUtil.isImageCanPrint(getContext(), uri)) {
                try {
                    printHelper.printBitmap("MIUI:" + fileNameFromUri, uri, (PrintHelper.OnPrintFinishCallback) null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ClipData getClipData(List<Uri> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        ClipData clipData = new ClipData(new ClipDescription("mishare data", new String[]{""}), new ClipData.Item(list.get(0)));
        int size = list.size();
        for (int i = 1; i < size; i++) {
            Uri uri = list.get(i);
            if (uri != null) {
                clipData.addItem(new ClipData.Item(uri));
            }
        }
        return clipData;
    }

    /* access modifiers changed from: private */
    public int getDeviceType(byte b) {
        if (b >= 20 && b <= 29) {
            return 2;
        }
        if (b < 10 || b > 19) {
            return 1;
        }
        return b == 11 ? 4 : 3;
    }

    private boolean hasPrinter() {
        Intent intent = new Intent();
        intent.setClassName("com.android.printspooler", "com.android.printspooler.ui.MiuiPrintActivity");
        PackageManager packageManager = getContext().getPackageManager();
        return packageManager != null && packageManager.queryIntentActivities(intent, 0).size() > 0;
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_mishare_transfer_gallery, this);
        this.mConnectivity = MiShareGalleryConnectivity.getInstance(getContext());
        this.mTipsTv = (TextView) findViewById(R.id.tv_prompt_tips);
        this.mScreenThrow = (ImageButton) findViewById(R.id.iv_throwing_screen);
        this.mScreenThrow.setOnClickListener(this);
        this.mPrintBtn = (ImageButton) findViewById(R.id.iv_printer);
        this.mPrintBtn.setOnClickListener(this);
        this.mPrintBtn.setVisibility(hasPrinter() ? 0 : 8);
        this.mMiShareDisabled = findViewById(R.id.rl_prompt_switch);
        this.mEnableMiShareView = findViewById(R.id.btn_enable_midrop);
        this.mDividerView = findViewById(R.id.view_middle_divider);
        this.mAdapter = new MiShareGalleryDeviceAdapter(getContext());
        this.mAdapter.registerListener(this);
        this.mDeviceLv = (RecyclerView) findViewById(R.id.lv_scanned_device);
        this.mDeviceLv.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.mDeviceLv.setAdapter(this.mAdapter);
        this.mEnableMiShareView.setOnClickListener(this);
        initMiShareStatus();
        registerStateReceiver();
    }

    private void initMiShareStatus() {
        this.mMiShareDisabled.setVisibility(0);
        this.mDeviceLv.setVisibility(4);
    }

    private boolean isFileCanPrint(Uri uri) {
        return MiShareFileUtil.isImageCanPrint(getContext(), uri) || MiShareFileUtil.isFilePdf(getContext(), uri);
    }

    public static boolean isServiceAvailable(Context context) {
        return MiShareGalleryConnectivity.isAvailable(context);
    }

    private void print() {
        if (canPrint()) {
            doPrint();
            View.OnClickListener onClickListener = this.mMiPrintClick;
            if (onClickListener != null) {
                onClickListener.onClick(this.mPrintBtn);
                return;
            }
            return;
        }
        showToast(getResources().getString(R.string.file_cannot_print));
    }

    /* access modifiers changed from: private */
    public void refreshView(int i, int i2) {
        boolean z;
        boolean z2 = true;
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 7:
                z = false;
                break;
            case 4:
            case 5:
            case 6:
                z = true;
                z2 = false;
                break;
            default:
                return;
        }
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 7:
                z2 = false;
                break;
            case 4:
            case 5:
            case 6:
                z = false;
                break;
            default:
                return;
        }
        if (z2) {
            setDiscoveringView();
        } else if (z) {
            this.mAdapter.stopSort();
            this.mDividerView.setVisibility(0);
            this.mMiShareDisabled.setVisibility(0);
            this.mDeviceLv.setVisibility(4);
            this.mTipsTv.setText(R.string.tips_prompt_enable_midrop);
            this.mEnableMiShareView.setVisibility(0);
        }
    }

    private void registerStateReceiver() {
        this.mShareTaskStateReceive = new MiShareTaskStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.miui.mishare.connectivity.TASK_STATE");
        getContext().registerReceiver(this.mShareTaskStateReceive, intentFilter);
    }

    private void send(MiShareDevice miShareDevice) {
        if (miShareDevice != null) {
            this.mIsPrint = false;
            this.mCurrentDeviceStatus = 2;
            this.mDeviceId = miShareDevice.deviceId;
            FilesNotYetSetListener filesNotYetSetListener = this.mHasNoFilesListener;
            if (filesNotYetSetListener != null) {
                filesNotYetSetListener.fileNotYetSet();
            }
        }
    }

    private void sendToDevice(MiShareDevice miShareDevice) {
        if (miShareDevice != null) {
            List<Uri> list = this.mFiles;
            if ((list == null || list.isEmpty()) && this.mHasNoFilesListener != null) {
                miShareDevice.deviceStatus = 1;
                this.mAdapter.notifyDataSetChanged();
                return;
            }
            miShareDevice.files = this.mFiles;
            MiShareTask miShareTask = new MiShareTask();
            miShareTask.device = miShareDevice.remoteDevice;
            miShareTask.clipData = getClipData(miShareDevice.files);
            miShareTask.taskId = miShareDevice.taskId;
            miShareTask.count = miShareDevice.files.size();
            this.mConnectivity.sendData(miShareTask);
            miShareDevice.deviceStatus = this.mCurrentDeviceStatus;
            this.mAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public void setDiscoveringView() {
        this.mEnableMiShareView.setVisibility(8);
        this.mTipsTv.setText(R.string.scanning_device_tips);
        this.mDividerView.setVisibility(0);
        this.mMiShareDisabled.setVisibility(0);
        this.mDeviceLv.setVisibility(4);
        this.mAdapter.setInitialAddDevice();
    }

    private void showToast(String str) {
        Toast.makeText(getContext(), str, 0).show();
    }

    private void startDiscover() {
        Intent intent = this.mIntent;
        if (intent != null) {
            this.mConnectivity.startDiscoverWithIntent(this.mDiscoverCallback, intent);
        } else {
            this.mConnectivity.startDiscover(this.mDiscoverCallback);
        }
        this.mConnectivity.registerTaskStateListener(this.mTaskStateListener);
    }

    private void unregisterStateReceiver() {
        if (this.mShareTaskStateReceive != null) {
            getContext().unregisterReceiver(this.mShareTaskStateReceive);
        }
    }

    public void bind() {
        this.mConnectivity.bind(this);
    }

    public void onCancelTask(MiShareDevice miShareDevice) {
        MiShareTask miShareTask = new MiShareTask();
        miShareTask.device = miShareDevice.remoteDevice;
        miShareTask.taskId = miShareDevice.taskId;
        this.mConnectivity.cancel(miShareTask);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_enable_midrop) {
            startDiscover();
        } else if (id == R.id.iv_throwing_screen) {
            View.OnClickListener onClickListener = this.mScreenThrowClick;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        } else if (id != R.id.iv_printer) {
        } else {
            if (this.mPrintEnabled) {
                this.mIsPrint = true;
                FilesNotYetSetListener filesNotYetSetListener = this.mHasNoFilesListener;
                if (filesNotYetSetListener != null) {
                    filesNotYetSetListener.fileNotYetSet();
                } else {
                    print();
                }
            } else {
                showToast(getResources().getString(R.string.file_cannot_print));
            }
        }
    }

    public void onDeviceTaskRetry(MiShareDevice miShareDevice) {
        if (miShareDevice.isPC()) {
            miShareDevice.taskId = MiShareDevice.generatePCTaskId();
        }
        send(miShareDevice);
    }

    public void onDeviceTaskStart(MiShareDevice miShareDevice) {
        send(miShareDevice);
    }

    public void onServiceBound() {
        this.mState = this.mConnectivity.getServiceState();
        this.mConnectivity.registerStateListener(this.mStateListener);
        int i = this.mState;
        if (i == 3 || i == 4 || i == 5 || i == 6) {
            startDiscover();
        }
    }

    public void sendFiles(List<Uri> list) {
        setFiles(list);
        if (this.mIsPrint) {
            print();
            return;
        }
        MiShareDevice deviceById = this.mAdapter.getDeviceById(this.mDeviceId);
        if (deviceById == null) {
            showToast(getContext().getResources().getString(R.string.device_offline));
        } else {
            sendToDevice(deviceById);
        }
    }

    public void setDeliveryTitle(String str) {
        ((TextView) findViewById(R.id.tv_send_file_to)).setText(str);
    }

    public void setFileIfNotYet(FilesNotYetSetListener filesNotYetSetListener) {
        this.mHasNoFilesListener = filesNotYetSetListener;
    }

    public void setFiles(List<Uri> list) {
        this.mFiles = list;
        if (list == null || list.size() != 1) {
            setMiPrintEnable(false);
        } else {
            setMiPrintEnable(isFileCanPrint(list.get(0)));
        }
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    public void setMiPrintClickListener(View.OnClickListener onClickListener) {
        this.mMiPrintClick = onClickListener;
    }

    public void setMiPrintEnable(boolean z) {
        this.mPrintEnabled = z;
        this.mPrintBtn.setImageResource(z ? R.drawable.bg_ic_printer : R.drawable.ic_printer_pressed);
    }

    public void setScreenThrowClickListener(View.OnClickListener onClickListener) {
        this.mScreenThrowClick = onClickListener;
    }

    public void setScreenThrowEnable(boolean z) {
        this.mScreenThrow.setImageResource(z ? R.drawable.bg_ic_screen : R.drawable.ic_screen_pressed);
    }

    public void setScreenThrowHighLight(boolean z) {
        if (z) {
            this.mScreenThrow.setBackground(getContext().getResources().getDrawable(R.drawable.bg_screen_throw_hight_light));
            this.mScreenThrow.setImageResource(R.drawable.ic_screen_throwing);
            return;
        }
        this.mScreenThrow.setBackground(getContext().getResources().getDrawable(R.drawable.bg_mishare_tranfer_btn));
        this.mScreenThrow.setImageResource(R.drawable.bg_ic_screen);
    }

    public void stopDiscover() {
        this.mConnectivity.stopDiscover(this.mDiscoverCallback);
    }

    public void unbind() {
        if (this.mConnectivity.checkServiceBound()) {
            this.mConnectivity.unregisterStateListener(this.mStateListener);
            this.mConnectivity.unregisterTaskStateListener(this.mTaskStateListener);
            stopDiscover();
        }
        this.mConnectivity.unbind();
        this.mAdapter.unregister();
        this.mAdapter.stopSort();
        unregisterStateReceiver();
    }
}
