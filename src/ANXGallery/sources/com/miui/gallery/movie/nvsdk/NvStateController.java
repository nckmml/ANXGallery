package com.miui.gallery.movie.nvsdk;

import com.meicam.sdk.NvsRational;
import com.meicam.sdk.NvsStreamingContext;
import com.meicam.sdk.NvsTimeline;
import com.miui.gallery.movie.core.IMovieController;
import com.miui.gallery.movie.core.MovieManager;
import com.miui.gallery.util.Log;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class NvStateController implements NvsStreamingContext.PlaybackCallback, NvsStreamingContext.PlaybackCallback2, NvsStreamingContext.StreamingEngineCallback, IMovieController {
    private NvsRational mNvsRational = new NvsRational(3, 4);
    private CopyOnWriteArrayList<MovieManager.StateChangeListener> mStateChangeListeners;
    private NvsStreamingContext mStreamingContext;
    private NvsTimeline mTimeline;

    public NvStateController(NvsStreamingContext nvsStreamingContext, NvsTimeline nvsTimeline) {
        this.mStreamingContext = nvsStreamingContext;
        this.mTimeline = nvsTimeline;
    }

    private int getCurrentEngineState() {
        return this.mStreamingContext.getStreamingEngineState();
    }

    private int getStateFromNvEngine(int i) {
        if (i == 0) {
            return 2;
        }
        if (i == 3) {
            return 1;
        }
        if (i != 4) {
            return i != 5 ? -500 : 200;
        }
        return 3;
    }

    private void seekLong(long j) {
        this.mStreamingContext.seekTimeline(this.mTimeline, j, this.mNvsRational, 0);
    }

    public void addStateChangeListener(MovieManager.StateChangeListener stateChangeListener) {
        if (stateChangeListener != null) {
            if (this.mStateChangeListeners == null) {
                this.mStateChangeListeners = new CopyOnWriteArrayList<>();
            }
            if (!this.mStateChangeListeners.contains(stateChangeListener)) {
                this.mStateChangeListeners.add(stateChangeListener);
            }
        }
    }

    public void cancelExport() {
        Log.d("NvStateController", "cancelExport");
        if (getCurrentEngineState() == 5) {
            this.mStreamingContext.stop(2);
        }
        Log.d("NvStateController", "cancelExport done");
    }

    public boolean isResume() {
        return getCurrentEngineState() == 4 || getCurrentEngineState() == 0;
    }

    public /* synthetic */ void lambda$onPlaybackEOF$29$NvStateController() {
        Iterator<MovieManager.StateChangeListener> it = this.mStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackEOF();
        }
    }

    public void onFirstVideoFramePresented(NvsTimeline nvsTimeline) {
        Iterator<MovieManager.StateChangeListener> it = this.mStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackPreloadingCompletion();
        }
        Log.d("NvMovieManager", "onPlaybackPreloadingCompletion");
    }

    public void onPause() {
        pause();
    }

    public void onPlaybackEOF(NvsTimeline nvsTimeline) {
        AndroidSchedulers.mainThread().createWorker().schedule(new Runnable() {
            public final void run() {
                NvStateController.this.lambda$onPlaybackEOF$29$NvStateController();
            }
        });
    }

    public void onPlaybackPreloadingCompletion(NvsTimeline nvsTimeline) {
    }

    public void onPlaybackStopped(NvsTimeline nvsTimeline) {
    }

    public void onPlaybackTimelinePosition(NvsTimeline nvsTimeline, long j) {
        Iterator<MovieManager.StateChangeListener> it = this.mStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackTimeChanged((int) (j / 1000));
        }
    }

    public void onResume() {
    }

    public void onStreamingEngineStateChanged(int i) {
        Log.d("NvStateController", "state: %d", (Object) Integer.valueOf(i));
        int stateFromNvEngine = getStateFromNvEngine(i);
        Iterator<MovieManager.StateChangeListener> it = this.mStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(stateFromNvEngine);
        }
    }

    public void pause() {
        if (getCurrentEngineState() != 5) {
            this.mStreamingContext.stop(2);
        }
    }

    public void pauseOrResume() {
        if (getCurrentEngineState() == 4 || getCurrentEngineState() == 0) {
            resume();
        } else {
            pause();
        }
    }

    public void play(long j) {
        NvsStreamingContext nvsStreamingContext = this.mStreamingContext;
        NvsTimeline nvsTimeline = this.mTimeline;
        nvsStreamingContext.playbackTimeline(nvsTimeline, j, nvsTimeline.getDuration(), this.mNvsRational, true, 0);
    }

    public void removeStateChangeListener(MovieManager.StateChangeListener stateChangeListener) {
        CopyOnWriteArrayList<MovieManager.StateChangeListener> copyOnWriteArrayList = this.mStateChangeListeners;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(stateChangeListener);
        }
    }

    public void replay() {
        play(0);
    }

    public void resume() {
        if (getCurrentEngineState() != 3) {
            long timelineCurrentPosition = this.mStreamingContext.getTimelineCurrentPosition(this.mTimeline);
            NvsStreamingContext nvsStreamingContext = this.mStreamingContext;
            NvsTimeline nvsTimeline = this.mTimeline;
            nvsStreamingContext.playbackTimeline(nvsTimeline, timelineCurrentPosition, nvsTimeline.getDuration(), this.mNvsRational, true, 0);
        }
    }

    public void seek(int i) {
        seekLong((long) (i * 1000));
    }
}
