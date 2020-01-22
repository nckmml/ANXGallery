package com.miui.gallery.ui;

import android.content.Context;
import android.os.Build;
import com.miui.gallery.util.LazyValue;
import com.miui.mishare.app.view.MiShareGalleryTransferView;
import java.util.ArrayList;
import java.util.List;

public class ShareStateRouter {
    public static final LazyValue<Context, Boolean> IS_MISHARE_AVAILABLE = new LazyValue<Context, Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean onInit(Context context) {
            return Boolean.valueOf(Build.VERSION.SDK_INT >= 28 && MiShareGalleryTransferView.isServiceAvailable(context));
        }
    };
    private static ShareStateRouter sShareStateRouter;
    private List<PrintStateObserver> mPrintStateObservers = new ArrayList();
    private List<ProjectStateObserver> mProjectStateObservers = new ArrayList();

    interface PrintStateObserver {
        void onObservePrintState(boolean z);
    }

    interface ProjectStateObserver {
        void onObserveProjectState(int i);
    }

    private ShareStateRouter() {
    }

    public static synchronized ShareStateRouter getInstance() {
        ShareStateRouter shareStateRouter;
        synchronized (ShareStateRouter.class) {
            if (sShareStateRouter == null) {
                sShareStateRouter = new ShareStateRouter();
            }
            shareStateRouter = sShareStateRouter;
        }
        return shareStateRouter;
    }

    public void broadcastPrintState(boolean z) {
        for (PrintStateObserver onObservePrintState : this.mPrintStateObservers) {
            onObservePrintState.onObservePrintState(z);
        }
    }

    public void broadcastProjectState(int i) {
        for (ProjectStateObserver onObserveProjectState : this.mProjectStateObservers) {
            onObserveProjectState.onObserveProjectState(i);
        }
    }

    public void registerPrintStateObserver(PrintStateObserver printStateObserver) {
        this.mPrintStateObservers.add(printStateObserver);
    }

    public void registerProjectStateObserver(ProjectStateObserver projectStateObserver) {
        this.mProjectStateObservers.add(projectStateObserver);
    }

    public void removePrintStateObserver(PrintStateObserver printStateObserver) {
        this.mPrintStateObservers.remove(printStateObserver);
    }

    public void removeProjectStateObserver(ProjectStateObserver projectStateObserver) {
        this.mProjectStateObservers.remove(projectStateObserver);
    }
}
