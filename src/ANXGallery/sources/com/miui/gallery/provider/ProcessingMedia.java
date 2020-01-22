package com.miui.gallery.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import com.miui.gallery.photosapi.PhotosOemApi;
import com.miui.gallery.photosapi.ProcessingMetadataQuery;

public class ProcessingMedia {
    private final long mMediaStoreId;
    private final int mMediaType;
    private final String mPath;
    private final ProcessingMetadata mProcessingMetadata;
    private final String mSpecialTypeId;
    private final Uri mUri;

    static final class Factory {
        private final Context context;

        Factory(Context context2) {
            this.context = context2;
        }

        private Uri getMediaStoreUri(long j, int i) {
            return i == 3 ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(String.valueOf(j)).build() : MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(String.valueOf(j)).build();
        }

        private Uri getProcessingUri(long j) {
            return PhotosOemApi.getQueryProcessingUri(this.context, j);
        }

        /* access modifiers changed from: package-private */
        public ProcessingMedia build(long j, String str, int i, ProcessingMetadata processingMetadata) {
            return new ProcessingMedia(i == 0 ? getProcessingUri(j) : getMediaStoreUri(j, i), j, str, (String) null, i, processingMetadata);
        }
    }

    public static final class ProcessingMetadata {
        private final int mProgressPercentage;
        private final ProcessingMetadataQuery.ProgressStatus mProgressStatus;

        ProcessingMetadata(ProcessingMetadataQuery.ProgressStatus progressStatus, int i) {
            this.mProgressStatus = progressStatus;
            this.mProgressPercentage = i;
        }

        public int getProgressPercentage() {
            return this.mProgressPercentage;
        }

        public ProcessingMetadataQuery.ProgressStatus getProgressStatus() {
            return this.mProgressStatus;
        }

        public String toString() {
            return "ProcessingMetadata{mProgressStatus=" + this.mProgressStatus + ", mProgressPercentage=" + this.mProgressPercentage + '}';
        }
    }

    private ProcessingMedia(Uri uri, long j, String str, String str2, int i, ProcessingMetadata processingMetadata) {
        this.mUri = uri;
        this.mMediaStoreId = j;
        this.mPath = str;
        this.mSpecialTypeId = str2;
        this.mMediaType = i;
        this.mProcessingMetadata = processingMetadata;
    }

    public long getMediaStoreId() {
        return this.mMediaStoreId;
    }

    public String getPath() {
        return this.mPath;
    }

    public ProcessingMetadata getProcessingMetadata() {
        return this.mProcessingMetadata;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public String toString() {
        return "ProcessingMedia{mUri=" + this.mUri + ", mMediaStoreId=" + this.mMediaStoreId + ", mPath='" + this.mPath + '\'' + ", mSpecialTypeId='" + this.mSpecialTypeId + '\'' + ", mMediaType=" + this.mMediaType + ", mProcessingMetadata=" + this.mProcessingMetadata + '}';
    }
}
