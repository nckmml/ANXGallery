package com.miui.gallery.movie.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import com.miui.gallery.imodule.loader.ModuleOperator;
import com.miui.gallery.imodule.modules.MovieDependsModule;
import com.miui.gallery.movie.R;
import com.miui.gallery.movie.core.MovieManager;
import com.miui.gallery.movie.entity.MovieInfo;
import com.miui.gallery.movie.ui.fragment.MovieSavingFragment;
import com.miui.gallery.movie.ui.listener.IShareDataCallback;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.movie.utils.MovieStorage;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.BaseFileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaStoreUtils;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.io.File;
import miui.app.ProgressDialog;

public class MovieSavingFragment extends GalleryDialogFragment {
    private long mLastBackPressedTime;
    private OnSavingFinishListener mOnSavingFinishListener;
    private ProgressDialog mProgressDialog;
    private IShareDataCallback mShareCallback;

    public interface OnSavingFinishListener {
        void onFinish(boolean z, boolean z2, String str);
    }

    private boolean backPress() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastBackPressedTime > 3000) {
            this.mLastBackPressedTime = currentTimeMillis;
            ToastUtils.makeText((Context) getActivity(), (CharSequence) getString(R.string.movie_save_stop_tips), 0);
            return true;
        }
        this.mLastBackPressedTime = 0;
        dismissSafely();
        IShareDataCallback iShareDataCallback = this.mShareCallback;
        if (iShareDataCallback == null) {
            return true;
        }
        iShareDataCallback.cancelExport();
        return true;
    }

    private void doSaving(Context context, FragmentManager fragmentManager, MovieManager movieManager, OnSavingFinishListener onSavingFinishListener) {
        if (!movieManager.isReadyForExport()) {
            Log.w("MovieSavingFragment", "is not readyForExport");
            return;
        }
        String tempFilePath = MovieStorage.getTempFilePath();
        final FragmentManager fragmentManager2 = fragmentManager;
        final String str = tempFilePath;
        final Context context2 = context;
        final OnSavingFinishListener onSavingFinishListener2 = onSavingFinishListener;
        movieManager.export(tempFilePath, new MovieManager.EncodeStateInterface() {
            static /* synthetic */ String lambda$onEncodeEnd$20(String str, Context context, ThreadPool.JobContext jobContext) {
                String outputMediaFilePath = MovieStorage.getOutputMediaFilePath();
                BaseFileUtils.move(new File(str), new File(outputMediaFilePath));
                MovieDependsModule movieDependsModule = (MovieDependsModule) ModuleOperator.getModule(MovieDependsModule.class);
                if (movieDependsModule != null) {
                    movieDependsModule.scanSingleFile(context, outputMediaFilePath);
                }
                MediaStoreUtils.insert(context, outputMediaFilePath, 2);
                MediaStoreUtils.sendScannerBroadcast(context, outputMediaFilePath);
                return outputMediaFilePath;
            }

            public void onEncodeEnd(final boolean z, final boolean z2, int i) {
                Log.d("MovieSavingFragment", "save result: %b,code: %d,path: %s", Boolean.valueOf(z), Integer.valueOf(i), str);
                if (z) {
                    ThreadManager.getMiscPool().submit(new ThreadPool.Job(str, context2) {
                        private final /* synthetic */ String f$0;
                        private final /* synthetic */ Context f$1;

                        {
                            this.f$0 = r1;
                            this.f$1 = r2;
                        }

                        public final Object run(ThreadPool.JobContext jobContext) {
                            return MovieSavingFragment.AnonymousClass1.lambda$onEncodeEnd$20(this.f$0, this.f$1, jobContext);
                        }
                    }, new FutureHandler<String>() {
                        public void onPostExecute(Future<String> future) {
                            MovieSavingFragment.this.dismissSafely();
                            if (onSavingFinishListener2 != null) {
                                onSavingFinishListener2.onFinish(z, z2, future.get());
                            }
                        }
                    });
                    return;
                }
                MovieSavingFragment.this.dismissSafely();
                OnSavingFinishListener onSavingFinishListener = onSavingFinishListener2;
                if (onSavingFinishListener != null) {
                    onSavingFinishListener.onFinish(z, z2, (String) null);
                }
            }

            public void onEncodeProgress(int i) {
                MovieSavingFragment.this.setProgress(i);
            }

            public void onEncodeStart() {
                Log.d("MovieSavingFragment", "save start");
                if (!MovieSavingFragment.this.isAdded()) {
                    MovieSavingFragment.this.showAllowingStateLoss(fragmentManager2, "MovieSavingFragment");
                }
            }
        });
    }

    public /* synthetic */ boolean lambda$onCreateDialog$17$MovieSavingFragment(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 0) {
            return backPress();
        }
        return false;
    }

    public /* synthetic */ void lambda$show$18$MovieSavingFragment(Context context, boolean z, boolean z2, String str) {
        if (z) {
            OnSavingFinishListener onSavingFinishListener = this.mOnSavingFinishListener;
            if (onSavingFinishListener != null) {
                onSavingFinishListener.onFinish(z, z2, str);
            }
            ToastUtils.makeText(context, R.string.movie_save_successfully, 0);
        } else if (!z2) {
            ToastUtils.makeText(context, R.string.movie_save_failed, 0);
        }
        MovieStatUtils.statSaveResult(z ? MovieStatUtils.DOWNLOAD_SUCCESS : MovieStatUtils.DOWNLOAD_FAILED);
    }

    public /* synthetic */ void lambda$showAndShare$19$MovieSavingFragment(Context context, boolean z, boolean z2, String str) {
        if (z) {
            ToastUtils.makeText(context, R.string.movie_save_successfully, 0);
            IShareDataCallback iShareDataCallback = this.mShareCallback;
            if (iShareDataCallback != null) {
                iShareDataCallback.handleShareEvent(str);
            }
        } else if (!z2) {
            ToastUtils.makeText(context, R.string.movie_save_failed, 0);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IShareDataCallback) {
            this.mShareCallback = (IShareDataCallback) context;
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.mProgressDialog = new ProgressDialog(getActivity());
        this.mProgressDialog.setMessage(getResources().getString(R.string.movie_saving));
        this.mProgressDialog.setProgressStyle(1);
        this.mProgressDialog.setIndeterminate(false);
        this.mProgressDialog.setMax(100);
        this.mProgressDialog.setCanceledOnTouchOutside(false);
        this.mProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return MovieSavingFragment.this.lambda$onCreateDialog$17$MovieSavingFragment(dialogInterface, i, keyEvent);
            }
        });
        setCancelable(false);
        return this.mProgressDialog;
    }

    public void onDetach() {
        super.onDetach();
        this.mShareCallback = null;
        this.mOnSavingFinishListener = null;
    }

    public void setProgress(int i) {
        this.mProgressDialog.setProgress(i);
    }

    public void show(Activity activity, MovieManager movieManager, MovieInfo movieInfo, boolean z, OnSavingFinishListener onSavingFinishListener) {
        MovieStatUtils.statSaveClick(z, movieInfo);
        Context applicationContext = activity.getApplicationContext();
        this.mOnSavingFinishListener = onSavingFinishListener;
        doSaving(applicationContext, activity.getFragmentManager(), movieManager, new OnSavingFinishListener(applicationContext) {
            private final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            public final void onFinish(boolean z, boolean z2, String str) {
                MovieSavingFragment.this.lambda$show$18$MovieSavingFragment(this.f$1, z, z2, str);
            }
        });
    }

    public void showAndShare(Activity activity, MovieManager movieManager, MovieInfo movieInfo) {
        MovieStatUtils.statShareClick(movieInfo);
        Context applicationContext = activity.getApplicationContext();
        doSaving(applicationContext, activity.getFragmentManager(), movieManager, new OnSavingFinishListener(applicationContext) {
            private final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            public final void onFinish(boolean z, boolean z2, String str) {
                MovieSavingFragment.this.lambda$showAndShare$19$MovieSavingFragment(this.f$1, z, z2, str);
            }
        });
    }
}
