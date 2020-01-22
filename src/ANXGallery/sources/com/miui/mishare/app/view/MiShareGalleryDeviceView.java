package com.miui.mishare.app.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.mishare.R;

public class MiShareGalleryDeviceView extends LinearLayout {
    private View mDeviceIcon;
    private TextView mDeviceModelNameTv;
    private String mDeviceName;
    private TextView mDeviceNameTv;
    private View mDivider;
    private View mDividerEnd;
    private View mDividerStart;
    private View mPcView;
    private View mPhoneView;
    private ImageView mStatusIv;

    public MiShareGalleryDeviceView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_mishare_gallery_device_view, this);
        this.mDeviceIcon = findViewById(R.id.rl_device_icon);
        this.mDeviceNameTv = (TextView) findViewById(R.id.tv_device_name);
        this.mDeviceModelNameTv = (TextView) findViewById(R.id.tv_device_model);
        this.mStatusIv = (ImageView) findViewById(R.id.iv_sending_status);
        this.mPhoneView = findViewById(R.id.view_phone);
        this.mPcView = findViewById(R.id.iv_pc);
        this.mDivider = findViewById(R.id.view_divider);
        this.mDividerStart = findViewById(R.id.view_divider_start);
        this.mDividerEnd = findViewById(R.id.view_divider_right);
    }

    public View getIconView() {
        return this.mDeviceIcon;
    }

    public void setDeviceModelName(String str) {
        this.mDeviceModelNameTv.setText(str);
        if (TextUtils.isEmpty(this.mDeviceName)) {
            this.mDeviceModelNameTv.setMaxLines(2);
        } else {
            this.mDeviceModelNameTv.setMaxLines(1);
        }
        this.mDeviceModelNameTv.setEllipsize(TextUtils.TruncateAt.END);
    }

    public void setDeviceName(String str, boolean z) {
        this.mDeviceName = str;
        if (z) {
            this.mDeviceNameTv.setText(getResources().getString(R.string.device_name_with_ellipsize, new Object[]{str}));
        } else if (!TextUtils.isEmpty(str)) {
            this.mDeviceNameTv.setVisibility(0);
            this.mDeviceNameTv.setText(str);
        } else {
            this.mDeviceNameTv.setVisibility(8);
        }
    }

    public void setDevicePhoneOrPc(boolean z) {
        if (z) {
            this.mPhoneView.setVisibility(0);
            this.mPcView.setVisibility(8);
            return;
        }
        this.mPhoneView.setVisibility(8);
        this.mPcView.setVisibility(0);
    }

    public void setDeviceStatus(int i) {
        if (i == 1) {
            this.mStatusIv.setVisibility(8);
        } else if (i == 2) {
            this.mStatusIv.setVisibility(0);
            this.mStatusIv.setImageResource(R.drawable.ic_device_sending);
        } else if (i == 3) {
            this.mStatusIv.setVisibility(0);
            this.mStatusIv.setImageResource(R.drawable.ic_device_send_retry);
        } else if (i != 4) {
            this.mStatusIv.setVisibility(8);
        } else {
            this.mStatusIv.setVisibility(0);
            this.mStatusIv.setImageResource(R.drawable.ic_device_send_success);
        }
    }

    public void setDeviceType(int i) {
        if (i == 1) {
            this.mDeviceIcon.setBackgroundResource(R.drawable.bg_scanned_device_xiaomi);
        } else if (i == 2) {
            this.mDeviceIcon.setBackgroundResource(R.drawable.bg_scanned_device_vivo);
        } else if (i == 3) {
            this.mDeviceIcon.setBackgroundResource(R.drawable.bg_scanned_device_oppo);
        } else if (i != 4) {
            this.mDeviceIcon.setBackgroundResource(R.drawable.bg_scanned_device_xiaomi);
        } else {
            this.mDeviceIcon.setBackgroundResource(R.drawable.bg_scanned_device_realme);
        }
    }

    public void showDivider(boolean z) {
        this.mDivider.setVisibility(z ? 0 : 8);
    }

    public void showDividerEnd(boolean z) {
        this.mDividerEnd.setVisibility(z ? 0 : 8);
    }

    public void showDividerStart(boolean z) {
        this.mDividerStart.setVisibility(z ? 0 : 8);
    }
}
