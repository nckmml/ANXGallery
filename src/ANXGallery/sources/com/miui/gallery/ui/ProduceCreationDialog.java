package com.miui.gallery.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.adapter.CheckableAdapter;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.cloudcontrol.strategies.CreationStrategy;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.MovieLibraryLoaderHelper;
import com.miui.gallery.util.PhotoMovieEntranceUtils;
import com.miui.gallery.util.PrintInstallHelper;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.util.LinkedList;
import java.util.List;
import miui.app.AlertDialog;

public class ProduceCreationDialog extends GalleryDialogFragment {
    /* access modifiers changed from: private */
    public List<CreationModel> mCreationModels = new LinkedList();
    private AlertDialog mDialog;
    private MovieLibraryLoaderHelper.DownloadStateListener mDownloadStateListener = new MovieLibraryLoaderHelper.DownloadStateListener() {
        public void onDownloading() {
            ProduceCreationDialog.this.mProduceCreationAdapter.setRemainWhenClick(1, true);
            ProduceCreationDialog.this.mProduceCreationAdapter.setInformation(1, GalleryApp.sGetAndroidContext().getString(R.string.photo_movie_menu_loading));
            ProduceCreationDialog.this.mProduceCreationAdapter.notifyDataSetChanged();
        }

        public void onFinish(boolean z, int i) {
            ProduceCreationDialog.this.mProduceCreationAdapter.setRemainWhenClick(1, false);
            ProduceCreationDialog.this.mProduceCreationAdapter.setInformation(1, (String) null);
            ProduceCreationDialog.this.mProduceCreationAdapter.notifyDataSetChanged();
        }
    };
    private PrintInstallHelper.InstallStateListener mInstallStateListener = new PrintInstallHelper.InstallStateListener() {
        public void onFinish(boolean z, int i, int i2) {
            ProduceCreationDialog.this.mProduceCreationAdapter.setRemainWhenClick(2, false);
            ProduceCreationDialog.this.mProduceCreationAdapter.setInformation(2, (String) null);
            ProduceCreationDialog.this.mProduceCreationAdapter.notifyDataSetChanged();
        }

        public void onInstallLimited() {
        }

        public void onInstalling() {
            ProduceCreationDialog.this.mProduceCreationAdapter.setRemainWhenClick(2, true);
            ProduceCreationDialog.this.mProduceCreationAdapter.setInformation(2, GalleryApp.sGetAndroidContext().getString(R.string.photo_print_menu_loading));
            ProduceCreationDialog.this.mProduceCreationAdapter.notifyDataSetChanged();
        }
    };
    /* access modifiers changed from: private */
    public OnOperationSelectedListener mOnOperationSelectedListener;
    /* access modifiers changed from: private */
    public ProduceCreationAdapter mProduceCreationAdapter;

    public static class CreationModel {
        public int creationId;
        public int iconResourceId;
        public String informationString;
        public boolean isRemainWhenClick = false;
        public int titleResourceId;

        public CreationModel(int i, int i2, int i3) {
            this.creationId = i;
            this.iconResourceId = i2;
            this.titleResourceId = i3;
        }
    }

    public interface OnOperationSelectedListener {
        boolean onOperationSelected(int i);
    }

    private class ProduceCreationAdapter extends BaseAdapter implements View.OnClickListener {
        private List<CreationModel> mCreationModelList;

        private class ViewHolder {
            ImageView icon;
            TextView information;
            int position;
            TextView title;

            private ViewHolder() {
            }

            public void bindView(CreationModel creationModel, int i) {
                this.icon.setImageResource(creationModel.iconResourceId);
                this.title.setText(creationModel.titleResourceId);
                if (!TextUtils.isEmpty(creationModel.informationString)) {
                    this.information.setVisibility(0);
                    this.information.setText(creationModel.informationString);
                } else {
                    this.information.setVisibility(8);
                    this.information.setText("");
                }
                this.position = i;
            }
        }

        public ProduceCreationAdapter(List<CreationModel> list) {
            this.mCreationModelList = list;
        }

        public int getCount() {
            return this.mCreationModelList.size();
        }

        public CreationModel getItem(int i) {
            if (i < 0 || i >= this.mCreationModelList.size()) {
                return null;
            }
            return this.mCreationModelList.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.creation_list_dialog_item, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.icon = (ImageView) view.findViewById(R.id.creation_icon);
                viewHolder.title = (TextView) view.findViewById(R.id.creation_text);
                viewHolder.information = (TextView) view.findViewById(R.id.information);
                view.setTag(viewHolder);
                view.setOnClickListener(this);
            }
            ((ViewHolder) view.getTag()).bindView(getItem(i), i);
            return view;
        }

        public void onClick(View view) {
            int i = ((ViewHolder) view.getTag()).position;
            if (i < ProduceCreationDialog.this.mCreationModels.size() && ProduceCreationDialog.this.mOnOperationSelectedListener != null) {
                Log.d("ProduceCreationDialog", "Creation select : %d", (Object) Integer.valueOf(i));
                CreationModel creationModel = (CreationModel) ProduceCreationDialog.this.mCreationModels.get(i);
                if (!creationModel.isRemainWhenClick && ProduceCreationDialog.this.mOnOperationSelectedListener.onOperationSelected(creationModel.creationId)) {
                    ProduceCreationDialog.this.dismissSafely();
                }
            }
        }

        public void setInformation(int i, String str) {
            for (CreationModel next : this.mCreationModelList) {
                if (next.creationId == i) {
                    next.informationString = str;
                    return;
                }
            }
        }

        public void setRemainWhenClick(int i, boolean z) {
            for (CreationModel next : this.mCreationModelList) {
                if (next.creationId == i) {
                    next.isRemainWhenClick = z;
                    return;
                }
            }
        }
    }

    public ProduceCreationDialog() {
        CreationStrategy creationStrategy = CloudControlStrategyHelper.getCreationStrategy();
        if (creationStrategy.isCollageEntryEnable()) {
            this.mCreationModels.add(new CreationModel(0, R.drawable.home_menu_collage, R.string.home_menu_collage));
        }
        if (creationStrategy.isPhotoMovieEntryEnable() && PhotoMovieEntranceUtils.isPhotoMovieAvailable()) {
            this.mCreationModels.add(new CreationModel(1, R.drawable.home_menu_photo_movie, R.string.home_menu_photo_movie));
        }
        if (PrintInstallHelper.getInstance().isPhotoPrintEnable() && creationStrategy.isPrintEntryEnable()) {
            this.mCreationModels.add(new CreationModel(2, R.drawable.home_menu_print, R.string.home_menu_print));
        }
    }

    public static boolean checkCreationCondition(Context context, int i, List<CheckableAdapter.CheckedItem> list) {
        if (!MiscUtil.isValid(list)) {
            Log.e("ProduceCreationDialog", "Checked Items is null");
            return false;
        }
        if (i == 0) {
            if (containVideo(list)) {
                ToastUtils.makeText(context, (CharSequence) context.getString(R.string.unsupport_video, new Object[]{getCreationName(context, i)}));
                return false;
            } else if (list.size() > IntentUtil.getCollageMaxImageSize()) {
                int collageMaxImageSize = IntentUtil.getCollageMaxImageSize();
                ToastUtils.makeText(context, (CharSequence) context.getResources().getQuantityString(R.plurals.collage_select_image_dynamic_range, collageMaxImageSize, new Object[]{Integer.valueOf(collageMaxImageSize)}));
                return false;
            }
        } else if (i == 1) {
            if (containVideo(list) && containRawFile(list)) {
                ToastUtils.makeText(context, (CharSequence) context.getString(R.string.unsupport_raw_and_video, new Object[]{getCreationName(context, i)}));
                return false;
            } else if (containRawFile(list)) {
                ToastUtils.makeText(context, (CharSequence) context.getString(R.string.unsupport_raw, new Object[]{getCreationName(context, i)}));
                return false;
            } else if (containVideo(list)) {
                ToastUtils.makeText(context, (CharSequence) context.getString(R.string.unsupport_video, new Object[]{getCreationName(context, i)}));
                return false;
            } else if (list.size() > 20 || list.size() < 3) {
                ToastUtils.makeText(context, (int) R.string.photo_movie_select_image_range);
                return false;
            }
        } else if (i == 2) {
            if (containVideo(list)) {
                ToastUtils.makeText(context, (CharSequence) context.getString(R.string.unsupport_video, new Object[]{getCreationName(context, i)}));
                return false;
            }
            int printMaxImageCount = CloudControlStrategyHelper.getCreationStrategy().getPrintMaxImageCount();
            if (list.size() > printMaxImageCount) {
                ToastUtils.makeText(context, (CharSequence) context.getResources().getQuantityString(R.plurals.creation_max_image, printMaxImageCount, new Object[]{Integer.valueOf(printMaxImageCount)}));
                return false;
            }
        }
        return IntentUtil.checkCreationCondition((Activity) context, i);
    }

    private static boolean containRawFile(List<CheckableAdapter.CheckedItem> list) {
        for (CheckableAdapter.CheckedItem mimeType : list) {
            if (FileMimeUtil.isRawFromMimeType(mimeType.getMimeType())) {
                return true;
            }
        }
        return false;
    }

    private static boolean containVideo(List<CheckableAdapter.CheckedItem> list) {
        for (CheckableAdapter.CheckedItem mimeType : list) {
            if (FileMimeUtil.isVideoFromMimeType(mimeType.getMimeType())) {
                return true;
            }
        }
        return false;
    }

    private static String getCreationName(Context context, int i) {
        return i != 0 ? i != 1 ? context.getString(R.string.home_menu_print) : context.getString(R.string.home_menu_photo_movie) : context.getString(R.string.home_menu_collage);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mProduceCreationAdapter = new ProduceCreationAdapter(this.mCreationModels);
        if (PrintInstallHelper.getInstance().isPrintInstalling()) {
            this.mProduceCreationAdapter.setRemainWhenClick(2, true);
            this.mProduceCreationAdapter.setInformation(2, GalleryApp.sGetAndroidContext().getString(R.string.photo_print_menu_loading));
        }
        if (MovieLibraryLoaderHelper.getInstance().getLoaderState() == 1) {
            this.mProduceCreationAdapter.setRemainWhenClick(1, true);
            this.mProduceCreationAdapter.setInformation(1, GalleryApp.sGetAndroidContext().getString(R.string.photo_movie_menu_loading));
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (!BuildUtil.isMiui10(activity)) {
            builder.setTitle(R.string.operation_produce);
        }
        builder.setAdapter(this.mProduceCreationAdapter, (DialogInterface.OnClickListener) null);
        this.mDialog = builder.create();
        return this.mDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        PrintInstallHelper.getInstance().addInstallStateListener(this.mInstallStateListener);
        MovieLibraryLoaderHelper.getInstance().addDownloadStateListener(this.mDownloadStateListener);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
        PrintInstallHelper.getInstance().removeInstallStateListener(this.mInstallStateListener);
        MovieLibraryLoaderHelper.getInstance().removeDownloadStateListener(this.mDownloadStateListener);
    }

    public void setOnOperationSelectedListener(OnOperationSelectedListener onOperationSelectedListener) {
        this.mOnOperationSelectedListener = onOperationSelectedListener;
    }
}
