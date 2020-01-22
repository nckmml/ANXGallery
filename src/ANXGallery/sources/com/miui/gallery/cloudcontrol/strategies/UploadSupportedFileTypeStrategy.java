package com.miui.gallery.cloudcontrol.strategies;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.miui.gallery.cloudcontrol.Merger;
import com.miui.gallery.util.LazyValue;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UploadSupportedFileTypeStrategy extends BaseStrategy {
    public static final LazyValue<Void, Merger<UploadSupportedFileTypeStrategy>> SUPPORTED_TYPE_MERGER = new LazyValue<Void, Merger<UploadSupportedFileTypeStrategy>>() {
        /* access modifiers changed from: private */
        public List<FileType> getMergedList(List<FileType> list, List<FileType> list2) {
            return (list == null || (list2 != null && list.size() <= list2.size())) ? list2 : list;
        }

        /* access modifiers changed from: protected */
        public Merger<UploadSupportedFileTypeStrategy> onInit(Void voidR) {
            return new Merger<UploadSupportedFileTypeStrategy>() {
                public UploadSupportedFileTypeStrategy merge(UploadSupportedFileTypeStrategy uploadSupportedFileTypeStrategy, UploadSupportedFileTypeStrategy uploadSupportedFileTypeStrategy2) {
                    UploadSupportedFileTypeStrategy uploadSupportedFileTypeStrategy3 = new UploadSupportedFileTypeStrategy();
                    List access$100 = AnonymousClass1.this.getMergedList(uploadSupportedFileTypeStrategy.mImageFileTypes, uploadSupportedFileTypeStrategy2.mImageFileTypes);
                    if (access$100 != null) {
                        List unused = uploadSupportedFileTypeStrategy3.mImageFileTypes = new ArrayList(access$100);
                    }
                    List access$1002 = AnonymousClass1.this.getMergedList(uploadSupportedFileTypeStrategy.mVideoFileTypes, uploadSupportedFileTypeStrategy2.mVideoFileTypes);
                    if (access$1002 != null) {
                        List unused2 = uploadSupportedFileTypeStrategy3.mVideoFileTypes = new ArrayList(access$1002);
                    }
                    uploadSupportedFileTypeStrategy3.doAdditionalProcessing();
                    return uploadSupportedFileTypeStrategy3;
                }
            };
        }
    };
    /* access modifiers changed from: private */
    @SerializedName("image")
    public List<FileType> mImageFileTypes;
    /* access modifiers changed from: private */
    @SerializedName("video")
    public List<FileType> mVideoFileTypes;

    static class FileType {
        @SerializedName("extension")
        public String extension;
        @SerializedName("mime-type")
        public String mimeType;

        public FileType(String str, String str2) {
            this.extension = str;
            this.mimeType = str2;
        }
    }

    public static UploadSupportedFileTypeStrategy createDefault() {
        UploadSupportedFileTypeStrategy uploadSupportedFileTypeStrategy = new UploadSupportedFileTypeStrategy();
        uploadSupportedFileTypeStrategy.mImageFileTypes = Arrays.asList(new FileType[]{new FileType("JPG", "image/jpeg"), new FileType("JPEG", "image/jpeg"), new FileType("GIF", "image/gif"), new FileType("PNG", "image/png"), new FileType("BMP", "image/x-ms-bmp"), new FileType("WEBP", "image/webp"), new FileType("WBMP", "image/vnd.wap.wbmp"), new FileType("DNG", "image/x-adobe-dng")});
        uploadSupportedFileTypeStrategy.mVideoFileTypes = Arrays.asList(new FileType[]{new FileType("MP4", "video/mp4"), new FileType("MOV", "video/quicktime"), new FileType("3GP", "video/3gpp")});
        return uploadSupportedFileTypeStrategy;
    }

    public HashMap<String, String> getSupportedFileTypeMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        if (MiscUtil.isValid(this.mImageFileTypes)) {
            for (FileType next : this.mImageFileTypes) {
                if (!TextUtils.isEmpty(next.extension) && !TextUtils.isEmpty(next.mimeType)) {
                    hashMap.put(next.extension, next.mimeType);
                }
            }
        }
        if (MiscUtil.isValid(this.mVideoFileTypes)) {
            for (FileType next2 : this.mVideoFileTypes) {
                if (!TextUtils.isEmpty(next2.extension) && !TextUtils.isEmpty(next2.mimeType)) {
                    hashMap.put(next2.extension, next2.mimeType);
                }
            }
        }
        return hashMap;
    }
}
