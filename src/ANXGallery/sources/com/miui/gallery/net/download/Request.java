package com.miui.gallery.net.download;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.util.Locale;

public final class Request {
    private boolean mAllowedOverMetered;
    private File mDestination;
    private int mHashCode;
    private volatile Listener mListener;
    private int mMaxRetryTimes = 3;
    private Uri mUri;
    private Verifier mVerifier;

    public interface Listener {
        void onComplete(int i);

        void onProgressUpdate(int i);

        void onStart();
    }

    public Request(Uri uri, File file) {
        this.mUri = uri;
        String scheme = uri.getScheme();
        if (TextUtils.equals(scheme, "http") || TextUtils.equals(scheme, "https")) {
            this.mDestination = file;
            return;
        }
        throw new IllegalArgumentException("not support scheme: " + scheme);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Request)) {
            return false;
        }
        Request request = (Request) obj;
        return request.mUri.equals(this.mUri) && request.mDestination.equals(this.mDestination);
    }

    public File getDestination() {
        return this.mDestination;
    }

    public Listener getListener() {
        return this.mListener;
    }

    public int getMaxRetryTimes() {
        return this.mMaxRetryTimes;
    }

    /* access modifiers changed from: package-private */
    public int getNetworkType() {
        return this.mAllowedOverMetered ^ true ? 1 : 0;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public Verifier getVerifier() {
        return this.mVerifier;
    }

    public int hashCode() {
        if (this.mHashCode == 0) {
            this.mHashCode = String.format(Locale.US, "uri: %s, file: %s", new Object[]{this.mUri, this.mDestination}).hashCode();
        }
        return this.mHashCode;
    }

    public void setAllowedOverMetered(boolean z) {
        this.mAllowedOverMetered = z;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setVerifier(Verifier verifier) {
        this.mVerifier = verifier;
    }

    public String toString() {
        return String.format(Locale.US, "uri: %s, file: %s", new Object[]{this.mUri, this.mDestination});
    }
}
