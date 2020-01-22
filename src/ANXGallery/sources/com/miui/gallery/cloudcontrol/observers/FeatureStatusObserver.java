package com.miui.gallery.cloudcontrol.observers;

import androidx.core.util.Pair;
import com.miui.gallery.cloudcontrol.FeatureProfile;
import io.reactivex.observers.DisposableObserver;

public abstract class FeatureStatusObserver extends DisposableObserver<Pair<String, FeatureProfile.Status>> {
    public final void onComplete() {
    }

    public final void onError(Throwable th) {
    }

    public final void onNext(Pair<String, FeatureProfile.Status> pair) {
        onStatusChanged((String) pair.first, (FeatureProfile.Status) pair.second);
    }

    public abstract void onStatusChanged(String str, FeatureProfile.Status status);
}
