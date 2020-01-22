package com.miui.gallery.editor.photo.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.edmodo.cropper.CropImageView;
import com.miui.gallery.R;
import com.miui.gallery.activity.BaseActivity;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

public class CropperActivity extends BaseActivity {
    private LoaderManager.LoaderCallbacks<DecodeResult> mCallbacks = new LoaderManager.LoaderCallbacks<DecodeResult>() {
        /* JADX WARNING: type inference failed for: r3v1, types: [com.miui.gallery.editor.photo.app.CropperActivity, android.content.Context] */
        public Loader<DecodeResult> onCreateLoader(int i, Bundle bundle) {
            ? r3 = CropperActivity.this;
            return new PrepareLoader(r3, r3.mData);
        }

        /* JADX WARNING: type inference failed for: r2v3, types: [com.miui.gallery.editor.photo.app.CropperActivity, android.content.Context] */
        public void onLoadFinished(Loader loader, DecodeResult decodeResult) {
            if (decodeResult.mBitmap != null) {
                CropperActivity.this.mCropView.setImageBitmap(decodeResult.mBitmap);
                CropperActivity.this.mSaveButton.setEnabled(true);
                return;
            }
            if (decodeResult.mException != null) {
                Log.w("CropperActivity", (Throwable) decodeResult.mException);
            }
            Toast.makeText(CropperActivity.this, R.string.image_decode_failed, 0).show();
            CropperActivity.this.finish();
        }

        public void onLoaderReset(Loader loader) {
        }
    };
    /* access modifiers changed from: private */
    public View mCancelButton;
    /* access modifiers changed from: private */
    public CropImageView mCropView;
    /* access modifiers changed from: private */
    public Uri mData;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == CropperActivity.this.mCancelButton) {
                CropperActivity.this.finish();
            } else if (view == CropperActivity.this.mSaveButton) {
                new ExportFragment().showAllowingStateLoss(CropperActivity.this.getFragmentManager(), "ExportFragment");
            }
        }
    };
    /* access modifiers changed from: private */
    public Uri mOutput;
    private float mOutputX;
    private float mOutputY;
    private boolean mReturnData;
    /* access modifiers changed from: private */
    public View mSaveButton;

    private static class DecodeResult {
        Bitmap mBitmap;
        Exception mException;

        DecodeResult(Bitmap bitmap, Exception exc) {
            this.mBitmap = bitmap;
            this.mException = exc;
        }
    }

    public static class ExportFragment extends GalleryDialogFragment {
        private LoaderManager.LoaderCallbacks<Boolean> mCallbacks = new LoaderManager.LoaderCallbacks<Boolean>() {
            public Loader<Boolean> onCreateLoader(int i, Bundle bundle) {
                return new ExportLoader(ExportFragment.this.mCropper, ExportFragment.this.mCropper.mOutput);
            }

            /* JADX WARNING: type inference failed for: r2v5, types: [com.miui.gallery.editor.photo.app.CropperActivity, android.content.Context] */
            public void onLoadFinished(Loader loader, Boolean bool) {
                if (bool.booleanValue()) {
                    ExportFragment.this.mCropper.setResult(-1, new Intent());
                } else {
                    Toast.makeText(ExportFragment.this.mCropper, R.string.main_save_error_msg, 0).show();
                }
                ExportFragment.this.mCropper.finish();
            }

            public void onLoaderReset(Loader loader) {
            }
        };
        /* access modifiers changed from: private */
        public CropperActivity mCropper;

        public void onAttach(Activity activity) {
            super.onAttach(activity);
            if (activity instanceof CropperActivity) {
                this.mCropper = (CropperActivity) activity;
                return;
            }
            throw new IllegalStateException("can't attach to install of " + activity.getClass().getSimpleName());
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            getLoaderManager().initLoader(0, (Bundle) null, this.mCallbacks);
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.editor.photo.app.CropperActivity, android.content.Context] */
        public Dialog onCreateDialog(Bundle bundle) {
            ProgressDialog progressDialog = new ProgressDialog(this.mCropper);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage(getActivity().getString(R.string.photo_editor_saving));
            return progressDialog;
        }
    }

    private static class ExportLoader extends AsyncTaskLoader<Boolean> {
        private WeakReference<CropperActivity> mActivityWeakReference;
        private Uri mOut;
        private Boolean mResult;

        /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.editor.photo.app.CropperActivity, android.content.Context, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        public ExportLoader(CropperActivity r1, Uri uri) {
            super(r1);
            this.mOut = uri;
            this.mActivityWeakReference = new WeakReference<>(r1);
        }

        public void deliverResult(Boolean bool) {
            super.deliverResult(bool);
            this.mResult = bool;
            if (isStarted()) {
                super.deliverResult(bool);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:44:0x00a3 A[SYNTHETIC, Splitter:B:44:0x00a3] */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00ad A[SYNTHETIC, Splitter:B:50:0x00ad] */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x00b7 A[SYNTHETIC, Splitter:B:56:0x00b7] */
        /* JADX WARNING: Removed duplicated region for block: B:64:0x00c7 A[SYNTHETIC, Splitter:B:64:0x00c7] */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:53:0x00b2=Splitter:B:53:0x00b2, B:47:0x00a8=Splitter:B:47:0x00a8, B:41:0x009e=Splitter:B:41:0x009e} */
        public Boolean loadInBackground() {
            Bitmap access$400;
            Bitmap.CompressFormat compressFormat;
            Log.d("CropperActivity", "start export in background.");
            CropperActivity cropperActivity = (CropperActivity) this.mActivityWeakReference.get();
            if (cropperActivity == null || (access$400 = cropperActivity.createOutput()) == null) {
                return false;
            }
            if ("file".equals(this.mOut.getScheme())) {
                compressFormat = GalleryUtils.convertExtensionToCompressFormat(FileUtils.getExtension(this.mOut.getPath()));
            } else {
                String type = getContext().getContentResolver().getType(this.mOut);
                compressFormat = "image/png".equals(type) ? Bitmap.CompressFormat.PNG : "image/webp".equals(type) ? Bitmap.CompressFormat.WEBP : Bitmap.CompressFormat.JPEG;
            }
            BufferedOutputStream bufferedOutputStream = null;
            try {
                OutputStream openOutputStream = getContext().getContentResolver().openOutputStream(this.mOut);
                if (openOutputStream != null) {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(openOutputStream);
                    try {
                        Boolean valueOf = Boolean.valueOf(access$400.compress(compressFormat, 100, bufferedOutputStream2));
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e) {
                            Log.w("CropperActivity", (Throwable) e);
                        } catch (Exception e2) {
                            Log.e("CropperActivity", (Throwable) e2);
                        }
                        return valueOf;
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        bufferedOutputStream = bufferedOutputStream2;
                        Log.w("CropperActivity", (Throwable) e);
                        if (bufferedOutputStream != null) {
                        }
                        return false;
                    } catch (SecurityException e4) {
                        e = e4;
                        bufferedOutputStream = bufferedOutputStream2;
                        Log.w("CropperActivity", (Throwable) e);
                        if (bufferedOutputStream != null) {
                        }
                        return false;
                    } catch (Exception e5) {
                        e = e5;
                        bufferedOutputStream = bufferedOutputStream2;
                        try {
                            Log.e("CropperActivity", (Throwable) e);
                            if (bufferedOutputStream != null) {
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (IOException e6) {
                                    Log.w("CropperActivity", (Throwable) e6);
                                } catch (Exception e7) {
                                    Log.e("CropperActivity", (Throwable) e7);
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                        }
                        throw th;
                    }
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                Log.w("CropperActivity", (Throwable) e);
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return false;
            } catch (SecurityException e9) {
                e = e9;
                Log.w("CropperActivity", (Throwable) e);
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return false;
            } catch (Exception e10) {
                e = e10;
                Log.e("CropperActivity", (Throwable) e);
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e11) {
                        Log.w("CropperActivity", (Throwable) e11);
                    } catch (Exception e12) {
                        Log.e("CropperActivity", (Throwable) e12);
                    }
                }
                return false;
            }
            return false;
        }

        /* access modifiers changed from: protected */
        public void onReset() {
            super.onReset();
        }

        /* access modifiers changed from: protected */
        public void onStartLoading() {
            super.onStartLoading();
            Boolean bool = this.mResult;
            if (bool == null) {
                forceLoad();
            } else {
                deliverResult(bool);
            }
        }
    }

    private static class PrepareLoader extends AsyncTaskLoader<DecodeResult> {
        private DecodeResult mDecodeResult;
        private Uri mUri;

        public PrepareLoader(Context context, Uri uri) {
            super(context);
            this.mUri = uri;
        }

        /* JADX WARNING: Removed duplicated region for block: B:27:0x0048 A[SYNTHETIC, Splitter:B:27:0x0048] */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0054 A[SYNTHETIC, Splitter:B:32:0x0054] */
        /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
        private int getPhotoRotation(Uri uri) {
            BufferedInputStream bufferedInputStream = null;
            try {
                bufferedInputStream = getContext().getContentResolver().openInputStream(uri);
                if (bufferedInputStream != null) {
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(bufferedInputStream);
                    try {
                        int rotationDegrees = ExifUtil.getRotationDegrees(ExifUtil.sSupportExifCreator.create((InputStream) bufferedInputStream2));
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e) {
                            Log.w("CropperActivity", (Throwable) e);
                        }
                        return rotationDegrees;
                    } catch (FileNotFoundException e2) {
                        FileNotFoundException fileNotFoundException = e2;
                        bufferedInputStream = bufferedInputStream2;
                        e = fileNotFoundException;
                        try {
                            Log.w("CropperActivity", (Throwable) e);
                            if (bufferedInputStream != null) {
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e3) {
                                    Log.w("CropperActivity", (Throwable) e3);
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        bufferedInputStream = bufferedInputStream2;
                        th = th3;
                        if (bufferedInputStream != null) {
                        }
                        throw th;
                    }
                } else {
                    Log.e("CropperActivity", "no stream opened");
                    if (bufferedInputStream == null) {
                        return 0;
                    }
                    try {
                        bufferedInputStream.close();
                        return 0;
                    } catch (IOException e4) {
                        Log.w("CropperActivity", (Throwable) e4);
                        return 0;
                    }
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                Log.w("CropperActivity", (Throwable) e);
                if (bufferedInputStream != null) {
                    return 0;
                }
                bufferedInputStream.close();
                return 0;
            }
        }

        private Bitmap rotateBitmap(int i, Bitmap bitmap) {
            Matrix matrix = new Matrix();
            matrix.preRotate((float) i);
            try {
                return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            } catch (OutOfMemoryError unused) {
                Log.e("CropperActivity", "rotateBitmap OutOfMemoryError");
                return null;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:29:0x005d A[SYNTHETIC, Splitter:B:29:0x005d] */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0068 A[SYNTHETIC, Splitter:B:35:0x0068] */
        private int sampleSize(Uri uri) {
            InputStream inputStream;
            InputStream inputStream2 = null;
            try {
                InputStream openInputStream = getContext().getContentResolver().openInputStream(uri);
                if (openInputStream != null) {
                    try {
                        inputStream = new BufferedInputStream(openInputStream);
                        try {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                            int max = Math.max(Math.round((float) (options.outHeight / 2048)), Math.round((float) (options.outWidth / 2048)));
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                Log.w("CropperActivity", (Throwable) e);
                            }
                            return max;
                        } catch (FileNotFoundException e2) {
                            e = e2;
                            inputStream2 = inputStream;
                            try {
                                Log.w("CropperActivity", (Throwable) e);
                                if (inputStream2 != null) {
                                }
                                return 1;
                            } catch (Throwable th) {
                                th = th;
                                inputStream = inputStream2;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e3) {
                                        Log.w("CropperActivity", (Throwable) e3);
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (inputStream != null) {
                            }
                            throw th;
                        }
                    } catch (FileNotFoundException e4) {
                        FileNotFoundException fileNotFoundException = e4;
                        inputStream2 = openInputStream;
                        e = fileNotFoundException;
                        Log.w("CropperActivity", (Throwable) e);
                        if (inputStream2 != null) {
                        }
                        return 1;
                    } catch (Throwable th3) {
                        inputStream = openInputStream;
                        th = th3;
                        if (inputStream != null) {
                        }
                        throw th;
                    }
                } else {
                    if (openInputStream != null) {
                        try {
                            openInputStream.close();
                        } catch (IOException e5) {
                            Log.w("CropperActivity", (Throwable) e5);
                        }
                    }
                    return 1;
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                Log.w("CropperActivity", (Throwable) e);
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                return 1;
            }
        }

        public void deliverResult(DecodeResult decodeResult) {
            if (!isReset()) {
                this.mDecodeResult = decodeResult;
            }
            if (isStarted()) {
                super.deliverResult(decodeResult);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:40:0x0093 A[SYNTHETIC, Splitter:B:40:0x0093] */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00a8 A[SYNTHETIC, Splitter:B:50:0x00a8] */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00b4 A[SYNTHETIC, Splitter:B:57:0x00b4] */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:37:0x0089=Splitter:B:37:0x0089, B:47:0x009e=Splitter:B:47:0x009e} */
        public DecodeResult loadInBackground() {
            InputStream inputStream;
            try {
                InputStream openInputStream = getContext().getContentResolver().openInputStream(this.mUri);
                if (openInputStream != null) {
                    try {
                        inputStream = new BufferedInputStream(openInputStream);
                        try {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = sampleSize(this.mUri);
                            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                            int photoRotation = getPhotoRotation(this.mUri);
                            if (photoRotation != 0) {
                                Log.d("CropperActivity", "rotate image by %d", (Object) Integer.valueOf(photoRotation));
                                decodeStream = rotateBitmap(photoRotation, decodeStream);
                            }
                            Log.d("CropperActivity", "sample size is %dx%d", Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
                            DecodeResult decodeResult = new DecodeResult(decodeStream, (Exception) null);
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                Log.w("CropperActivity", (Throwable) e);
                            }
                            return decodeResult;
                        } catch (FileNotFoundException e2) {
                            e = e2;
                            Log.e("CropperActivity", (Throwable) e);
                            DecodeResult decodeResult2 = new DecodeResult((Bitmap) null, e);
                            if (inputStream != null) {
                            }
                            return decodeResult2;
                        } catch (SecurityException e3) {
                            e = e3;
                            try {
                                Log.w("CropperActivity", (Throwable) e);
                                DecodeResult decodeResult3 = new DecodeResult((Bitmap) null, e);
                                if (inputStream != null) {
                                }
                                return decodeResult3;
                            } catch (Throwable th) {
                                th = th;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e4) {
                                        Log.w("CropperActivity", (Throwable) e4);
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (FileNotFoundException e5) {
                        FileNotFoundException fileNotFoundException = e5;
                        inputStream = openInputStream;
                        e = fileNotFoundException;
                        Log.e("CropperActivity", (Throwable) e);
                        DecodeResult decodeResult22 = new DecodeResult((Bitmap) null, e);
                        if (inputStream != null) {
                        }
                        return decodeResult22;
                    } catch (SecurityException e6) {
                        SecurityException securityException = e6;
                        inputStream = openInputStream;
                        e = securityException;
                        Log.w("CropperActivity", (Throwable) e);
                        DecodeResult decodeResult32 = new DecodeResult((Bitmap) null, e);
                        if (inputStream != null) {
                        }
                        return decodeResult32;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = openInputStream;
                        if (inputStream != null) {
                        }
                        throw th;
                    }
                } else {
                    Log.d("CropperActivity", "no stream return.");
                    DecodeResult decodeResult4 = new DecodeResult((Bitmap) null, (Exception) null);
                    if (openInputStream != null) {
                        try {
                            openInputStream.close();
                        } catch (IOException e7) {
                            Log.w("CropperActivity", (Throwable) e7);
                        }
                    }
                    return decodeResult4;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                inputStream = null;
                Log.e("CropperActivity", (Throwable) e);
                DecodeResult decodeResult222 = new DecodeResult((Bitmap) null, e);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e9) {
                        Log.w("CropperActivity", (Throwable) e9);
                    }
                }
                return decodeResult222;
            } catch (SecurityException e10) {
                e = e10;
                inputStream = null;
                Log.w("CropperActivity", (Throwable) e);
                DecodeResult decodeResult322 = new DecodeResult((Bitmap) null, e);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e11) {
                        Log.w("CropperActivity", (Throwable) e11);
                    }
                }
                return decodeResult322;
            } catch (Throwable th3) {
                inputStream = null;
                th = th3;
                if (inputStream != null) {
                }
                throw th;
            }
        }

        /* access modifiers changed from: protected */
        public void onReset() {
            super.onReset();
            this.mDecodeResult = null;
        }

        /* access modifiers changed from: protected */
        public void onStartLoading() {
            super.onStartLoading();
            DecodeResult decodeResult = this.mDecodeResult;
            if (decodeResult == null) {
                forceLoad();
            } else {
                deliverResult(decodeResult);
            }
        }
    }

    /* access modifiers changed from: private */
    public Bitmap createOutput() {
        long currentTimeMillis = System.currentTimeMillis();
        Bitmap croppedImage = this.mCropView.getCroppedImage();
        if (this.mOutputX > 0.0f && this.mOutputY > 0.0f) {
            Matrix matrix = new Matrix();
            matrix.setScale(this.mOutputX / ((float) croppedImage.getWidth()), this.mOutputY / ((float) croppedImage.getHeight()));
            croppedImage = Bitmap.createBitmap(croppedImage, 0, 0, croppedImage.getWidth(), croppedImage.getHeight(), matrix, true);
        }
        Log.d("CropperActivity", "create output costs %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return croppedImage;
    }

    /* access modifiers changed from: protected */
    public boolean needShowUserAgreements() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Object obj;
        super.onCreate(bundle);
        setContentView(R.layout.crop_activity);
        this.mSaveButton = findViewById(R.id.ok);
        this.mCancelButton = findViewById(R.id.cancel);
        this.mSaveButton.setOnClickListener(this.mOnClickListener);
        this.mCancelButton.setOnClickListener(this.mOnClickListener);
        this.mCropView = (CropImageView) findViewById(R.id.crop_view);
        this.mSaveButton.setEnabled(false);
        Intent intent = getIntent();
        this.mData = intent.getData();
        this.mOutput = (Uri) intent.getParcelableExtra("output");
        this.mReturnData = intent.getBooleanExtra("return-data", false);
        this.mOutputX = (float) intent.getIntExtra("outputX", -1);
        this.mOutputY = (float) intent.getIntExtra("outputY", -1);
        Uri uri = this.mData;
        if (uri == null || (obj = this.mOutput) == null) {
            Log.e("CropperActivity", "src or des missed, src: %s, des: %s", this.mData, this.mOutput);
            finish();
            return;
        }
        if (obj == null) {
            obj = "bytes";
        }
        Log.d("CropperActivity", "cropper's input: %s, output: %s", uri, obj);
        int intExtra = intent.getIntExtra("aspectX", -1);
        int intExtra2 = intent.getIntExtra("aspectY", -1);
        if (intExtra > 0 && intExtra2 > 0) {
            this.mCropView.setFixedAspectRatio(intent.getBooleanExtra("fixed_aspect_ratio", true));
            this.mCropView.setAspectRatio(intExtra, intExtra2);
        }
        getLoaderManager().initLoader(0, (Bundle) null, this.mCallbacks);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
    }
}
