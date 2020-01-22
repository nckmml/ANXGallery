package com.miui.gallery.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.documentfile.provider.DocumentFile;
import com.android.internal.storage.StorageInfo;
import com.android.internal.storage.StorageManager;
import com.miui.gallery.R;
import com.miui.gallery.preference.BaseGalleryPreferences;
import com.miui.gallery.preference.DocumentProviderPreference;
import com.miui.gallery.stat.BaseSamplingStatHelper;
import com.miui.gallery.ui.AlertDialogFragment;
import com.miui.gallery.ui.DocumentsUIFragment;
import java.io.File;

public class DocumentProviderUtils extends BaseDocumentProviderUtils {
    @TargetApi(24)
    private static void createAccessIntent(final Activity activity, String str, final int i) {
        StorageInfo storageInfo = StorageManager.getInstance().getStorageInfo(activity, new File(str));
        if (storageInfo == null) {
            firstEntrancyPermFailed(activity);
            return;
        }
        final Intent createAccessIntent = storageInfo.createAccessIntent(activity);
        if (createAccessIntent == null) {
            firstEntrancyPermFailed(activity);
            return;
        }
        new AlertDialogFragment.Builder().setCancelable(false).setTitle(activity.getString(R.string.ext_sdcard_permission_request_title)).setMessage(activity.getString(R.string.ext_sdcard_permission_request_reason)).setPositiveButton(activity.getString(R.string.ext_sdcard_permission_request_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.startActivityForResult(createAccessIntent, i);
            }
        }).create().showAllowingStateLoss(activity.getFragmentManager(), "DocumentProviderUtils");
    }

    private static void firstEntrancyPermFailed(Activity activity) {
        increaseAccessIntentCount();
        new AlertDialogFragment.Builder().setCancelable(false).setTitle(activity.getResources().getString(R.string.ext_sdcard_first_entrance_request_failed_title)).setMessage(activity.getResources().getString(R.string.ext_sdcard_first_entrance_request_failed_text)).setPositiveButton(activity.getResources().getString(R.string.ext_sdcard_request_failed_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                StorageUtils.setPriorStorage(false);
            }
        }).create().showAllowingStateLoss(activity.getFragmentManager(), "DocumentProviderUtils");
    }

    public static void handleRequestPermissionResult(Activity activity, int i, int i2, Intent intent) {
        if (activity != null) {
            if (i == 49) {
                DocumentProviderPreference.setFirstEntrance(false);
                if (i2 != -1 || !isUriExternalSDCardRoot(intent.getData())) {
                    firstEntrancyPermFailed(activity);
                    BaseSamplingStatHelper.recordStringPropertyEvent("document_provider_permission_request", "document_provider_first_entrance", String.valueOf(false));
                    return;
                }
                handleRequestSucceed(activity, intent);
                BaseSamplingStatHelper.recordStringPropertyEvent("document_provider_permission_request", "document_provider_first_entrance", String.valueOf(true));
            } else if (i == 47) {
                if (i2 == -1) {
                    handleRequestSucceed(activity, intent);
                    BaseSamplingStatHelper.recordStringPropertyEvent("document_provider_permission_request", "document_provider_permission_requested", String.valueOf(true));
                    return;
                }
                increaseAccessIntentCount();
                showOperationFailedDialog(activity);
                BaseSamplingStatHelper.recordStringPropertyEvent("document_provider_permission_request", "document_provider_permission_requested", String.valueOf(false));
            } else if (i != 48) {
            } else {
                if (i2 != -1 || !isUriExternalSDCardRoot(intent.getData())) {
                    showOperationFailedDialog(activity);
                    BaseSamplingStatHelper.recordStringPropertyEvent("document_provider_permission_request", "document_provider_permission_requested", String.valueOf(false));
                    return;
                }
                handleRequestSucceed(activity, intent);
                BaseSamplingStatHelper.recordStringPropertyEvent("document_provider_permission_request", "document_provider_permission_requested", String.valueOf(true));
            }
        }
    }

    private static void handleRequestSucceed(Activity activity, Intent intent) {
        persistDocumentProviderUri(activity, intent);
        ToastUtils.makeText((Context) activity, (CharSequence) activity.getResources().getString(R.string.ext_sdcard_request_succeed_toast_text));
    }

    private static void increaseAccessIntentCount() {
        DocumentProviderPreference.setOpenExternalDocumentCount(DocumentProviderPreference.getOpenExternalDocumentCount() + 1);
    }

    private static boolean isUriExternalSDCardRoot(Uri uri) {
        if (uri != null && StorageUtils.hasExternalSDCard(StaticContext.sGetAndroidContext())) {
            String secondaryStoragePath = StorageUtils.getSecondaryStoragePath();
            String str = ".miuigallery" + System.currentTimeMillis();
            DocumentFile createFile = DocumentFile.fromTreeUri(StaticContext.sGetAndroidContext(), uri).createFile("any/any", str);
            if (new File(secondaryStoragePath, str).exists()) {
                createFile.delete();
                return true;
            }
        }
        return false;
    }

    private static void persistDocumentProviderUri(Context context, Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            try {
                context.getContentResolver().takePersistableUriPermission(data, 3);
                BaseGalleryPreferences.BaseDocumentProvider.setExternalSDCardUri(data.toString());
            } catch (Exception e) {
                Log.e("DocumentProviderUtils", (Throwable) e);
            }
        }
    }

    private static void showOperationFailedDialog(Activity activity) {
        new AlertDialogFragment.Builder().setCancelable(false).setTitle(activity.getResources().getString(R.string.ext_sdcard_operation_failed_title)).setMessage(activity.getResources().getString(R.string.ext_sdcard_operation_failed_text)).setPositiveButton(activity.getResources().getString(R.string.ext_sdcard_request_failed_button_text), (DialogInterface.OnClickListener) null).create().showAllowingStateLoss(activity.getFragmentManager(), "DocumentProviderUtils");
    }

    private static void startDocumentTreeIntent(Activity activity, int i) {
        if (activity != null) {
            DocumentsUIFragment.newInstance(1, i).showAllowingStateLoss(activity.getFragmentManager(), "DocumentsUIFragment");
        }
    }

    @TargetApi(24)
    public static void startExtSDCardPermissionActivityForResult(Activity activity) {
        String secondaryStoragePath = StorageUtils.getSecondaryStoragePath();
        if (activity != null && !TextUtils.isEmpty(secondaryStoragePath) && needRequestExternalSDCardPermission(activity)) {
            if (BaseGalleryPreferences.BaseDocumentProvider.getExternalSDCardUri() != null) {
                DocumentProviderPreference.setOpenExternalDocumentCount(0);
            }
            startPermissionActivityForResult(activity, secondaryStoragePath, 47, 48);
        }
    }

    @TargetApi(24)
    public static void startFirstEntrancyPermissionActivityForResult(Activity activity) {
        if (activity == null) {
            Log.d("DocumentProviderUtils", "startExtSDCardPermissionActivityForResult activity null");
        } else {
            startPermissionActivityForResult(activity, StorageUtils.getSecondaryStoragePath(), 49, 49);
        }
    }

    private static void startPermissionActivityForResult(Activity activity, String str, int i, int i2) {
        if (Build.VERSION.SDK_INT > 28 || DocumentProviderPreference.getOpenExternalDocumentCount() >= 2) {
            startDocumentTreeIntent(activity, i2);
        } else {
            createAccessIntent(activity, str, i);
        }
    }
}
