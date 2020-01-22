package com.nostra13.universalimageloader.core.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.miui.gallery.imageloader.GalleryImageLoaderCache;
import com.miui.gallery.util.BaseBitmapUtils;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.MediaStoreUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class BaseImageDecoder implements ImageDecoder {
    private static final String[] SUPPORT_EXIF_MIMETYPE = {"image/jpeg", "image/x-adobe-dng"};
    protected final boolean loggingEnabled;

    protected static class ExifInfo {
        public final int exifOrientation;
        public final boolean flipHorizontal;
        public final int rotation;

        protected ExifInfo() {
            this.rotation = 0;
            this.flipHorizontal = false;
            this.exifOrientation = 0;
        }

        protected ExifInfo(int i, boolean z, int i2) {
            this.rotation = i;
            this.flipHorizontal = z;
            this.exifOrientation = i2;
        }
    }

    protected static class ImageFileInfo {
        public final ExifInfo exif;
        public final ImageSize imageSize;

        protected ImageFileInfo(ImageSize imageSize2, ExifInfo exifInfo) {
            this.imageSize = imageSize2;
            this.exif = exifInfo;
        }
    }

    public BaseImageDecoder(boolean z) {
        this.loggingEnabled = z;
    }

    private boolean canDefineExifParams(String str, String str2) {
        for (String equalsIgnoreCase : SUPPORT_EXIF_MIMETYPE) {
            if (equalsIgnoreCase.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    private String convertToFileScheme(String str) {
        ImageDownloader.Scheme ofUri = ImageDownloader.Scheme.ofUri(str);
        if (ofUri == ImageDownloader.Scheme.FILE) {
            return str;
        }
        if (ofUri != ImageDownloader.Scheme.CONTENT) {
            return null;
        }
        String mediaFilePath = MediaStoreUtils.getMediaFilePath(str);
        if (!TextUtils.isEmpty(mediaFilePath)) {
            return ImageDownloader.Scheme.FILE.wrap(mediaFilePath);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0099  */
    private Bitmap decodeInternal(ImageDecodingInfo imageDecodingInfo, InputStream inputStream, boolean z) throws IOException {
        ImageFileInfo imageFileInfo;
        InputStream inputStream2;
        BitmapFactory.Options options;
        Bitmap bitmap = null;
        try {
            if (inputStream.markSupported()) {
                inputStream.mark(inputStream.available());
            }
            imageFileInfo = z ? defineImageSizeAndRotation(inputStream, imageDecodingInfo) : defineImageSize(inputStream);
            try {
                inputStream2 = resetStream(inputStream, imageDecodingInfo);
                try {
                    options = prepareDecodingOptions(imageFileInfo.imageSize, imageDecodingInfo);
                } catch (Throwable unused) {
                    options = null;
                    L.e("decode (%1$s) (inSampleSize=%2$d) error use inBitmap (width=%3$d height=%4$d)", imageFileInfo.imageSize, Integer.valueOf(options.inSampleSize), Integer.valueOf(options.inBitmap.getWidth()), Integer.valueOf(options.inBitmap.getHeight()));
                    if (!options.inBitmap.isRecycled()) {
                    }
                    options.inBitmap = null;
                    inputStream2 = resetStream(inputStream2, imageDecodingInfo);
                    bitmap = BitmapFactory.decodeStream(inputStream2, (Rect) null, options);
                    if (bitmap == null) {
                    }
                    if (inputStream2 != inputStream) {
                    }
                    return bitmap;
                }
                try {
                    bitmap = BitmapFactory.decodeStream(inputStream2, (Rect) null, options);
                } catch (Throwable unused2) {
                    L.e("decode (%1$s) (inSampleSize=%2$d) error use inBitmap (width=%3$d height=%4$d)", imageFileInfo.imageSize, Integer.valueOf(options.inSampleSize), Integer.valueOf(options.inBitmap.getWidth()), Integer.valueOf(options.inBitmap.getHeight()));
                    if (!options.inBitmap.isRecycled()) {
                    }
                    options.inBitmap = null;
                    inputStream2 = resetStream(inputStream2, imageDecodingInfo);
                    bitmap = BitmapFactory.decodeStream(inputStream2, (Rect) null, options);
                    if (bitmap == null) {
                    }
                    if (inputStream2 != inputStream) {
                    }
                    return bitmap;
                }
            } catch (Throwable unused3) {
                inputStream2 = inputStream;
                options = null;
                L.e("decode (%1$s) (inSampleSize=%2$d) error use inBitmap (width=%3$d height=%4$d)", imageFileInfo.imageSize, Integer.valueOf(options.inSampleSize), Integer.valueOf(options.inBitmap.getWidth()), Integer.valueOf(options.inBitmap.getHeight()));
                if (!options.inBitmap.isRecycled()) {
                }
                options.inBitmap = null;
                inputStream2 = resetStream(inputStream2, imageDecodingInfo);
                bitmap = BitmapFactory.decodeStream(inputStream2, (Rect) null, options);
                if (bitmap == null) {
                }
                if (inputStream2 != inputStream) {
                }
                return bitmap;
            }
        } catch (Throwable unused4) {
            inputStream2 = inputStream;
            imageFileInfo = null;
            options = null;
            if (!(options == null || options.inBitmap == null)) {
                L.e("decode (%1$s) (inSampleSize=%2$d) error use inBitmap (width=%3$d height=%4$d)", imageFileInfo.imageSize, Integer.valueOf(options.inSampleSize), Integer.valueOf(options.inBitmap.getWidth()), Integer.valueOf(options.inBitmap.getHeight()));
                if (!options.inBitmap.isRecycled()) {
                    options.inBitmap.recycle();
                }
                options.inBitmap = null;
                inputStream2 = resetStream(inputStream2, imageDecodingInfo);
                bitmap = BitmapFactory.decodeStream(inputStream2, (Rect) null, options);
            }
            if (bitmap == null) {
            }
            if (inputStream2 != inputStream) {
            }
            return bitmap;
        }
        if (bitmap == null) {
            L.e("Image can't be decoded [%s]", imageDecodingInfo.getImageKey());
        } else {
            bitmap = considerExactScaleAndOrientation(bitmap, imageDecodingInfo, imageFileInfo.exif.rotation, imageFileInfo.exif.flipHorizontal);
        }
        if (inputStream2 != inputStream) {
            IoUtils.closeSilently(inputStream2);
        }
        return bitmap;
    }

    private ExifInfo defineExifOrientation(InputStream inputStream) {
        try {
            ExifInterface create = ExifUtil.sSupportExifCreator.create(inputStream);
            if (create != null) {
                return packageExifInfo(create.getAttributeInt("Orientation", 1));
            }
        } catch (Exception unused) {
            L.w("Can't read EXIF tags from stream", new Object[0]);
        }
        return new ExifInfo();
    }

    private ExifInfo defineExifOrientation(String str, byte[] bArr) {
        ExifUtil.ExifInfo parseRotationInfo = ExifUtil.parseRotationInfo(str, bArr);
        return parseRotationInfo != null ? new ExifInfo(parseRotationInfo.rotation, parseRotationInfo.flip, parseRotationInfo.exifOrientation) : new ExifInfo();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        r3 = r1;
        r1 = 0;
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        return new com.nostra13.universalimageloader.core.decode.BaseImageDecoder.ExifInfo(r1, r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0008, code lost:
        r1 = 90;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000c, code lost:
        r1 = com.nexstreaming.nexeditorsdk.nexClip.kClip_Rotate_270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r1 = com.nexstreaming.nexeditorsdk.nexClip.kClip_Rotate_180;
     */
    private ExifInfo packageExifInfo(int i) {
        boolean z = false;
        boolean z2 = true;
        switch (i) {
            case 1:
                z2 = false;
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                z = true;
                break;
            case 5:
                z = true;
                break;
            case 6:
                break;
            case 7:
                z = true;
                break;
            case 8:
                break;
            default:
                int i2 = 0;
                break;
        }
    }

    private boolean usePowerOf2(ImageScaleType imageScaleType) {
        return imageScaleType == ImageScaleType.IN_SAMPLE_POWER_OF_2 || imageScaleType == ImageScaleType.EXACTLY || imageScaleType == ImageScaleType.EXACTLY_STRETCHED;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c0 A[RETURN] */
    public Bitmap considerExactScaleAndOrientation(Bitmap bitmap, ImageDecodingInfo imageDecodingInfo, int i, boolean z) {
        boolean z2;
        Matrix matrix = new Matrix();
        ImageScaleType imageScaleType = imageDecodingInfo.getImageScaleType();
        if (imageScaleType == ImageScaleType.EXACTLY || imageScaleType == ImageScaleType.EXACTLY_STRETCHED) {
            ImageSize imageSize = new ImageSize(bitmap.getWidth(), bitmap.getHeight(), i);
            float computeImageScale = ImageSizeUtils.computeImageScale(imageSize, imageDecodingInfo.getTargetSize(), imageDecodingInfo.getViewScaleType(), imageScaleType == ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(computeImageScale, 1.0f) != 0) {
                matrix.setScale(computeImageScale, computeImageScale);
                if (this.loggingEnabled) {
                    L.d("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", imageSize, imageSize.scale(computeImageScale), Float.valueOf(computeImageScale), imageDecodingInfo.getImageKey());
                }
                z2 = true;
                if (z) {
                    matrix.postScale(-1.0f, 1.0f);
                    if (this.loggingEnabled) {
                        L.d("Flip image horizontally [%s]", imageDecodingInfo.getImageKey());
                    }
                    z2 = true;
                }
                if (i != 0) {
                    matrix.postRotate((float) i);
                    if (this.loggingEnabled) {
                        L.d("Rotate image on %1$d° [%2$s]", Integer.valueOf(i), imageDecodingInfo.getImageKey());
                    }
                    z2 = true;
                }
                if (z2) {
                    return bitmap;
                }
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                if (createBitmap != bitmap) {
                    if (imageDecodingInfo.getInBitmapCache() != null) {
                        imageDecodingInfo.getInBitmapCache().put(bitmap);
                    } else {
                        bitmap.recycle();
                    }
                }
                return createBitmap;
            }
        }
        z2 = false;
        if (z) {
        }
        if (i != 0) {
        }
        if (z2) {
        }
    }

    public Bitmap decode(ImageDecodingInfo imageDecodingInfo) throws IOException {
        InputStream inputStream;
        String str;
        boolean z;
        Bitmap bitmap;
        GalleryImageLoaderCache.IPhotoBytesCache photoBytesCache;
        GalleryImageLoaderCache.IPhotoBytesCache photoBytesCache2;
        try {
            String imageUri = imageDecodingInfo.getImageUri();
            long fileLength = imageDecodingInfo.isConsiderFileLength() ? imageDecodingInfo.getFileLength() : 0;
            if (!imageDecodingInfo.isLoadFromBigPhotoCache() || (str = convertToFileScheme(imageUri)) == null) {
                str = imageUri;
                z = false;
            } else {
                ImageLoader.getInstance().waitLockForUri(str, 1, TimeUnit.SECONDS);
                z = true;
            }
            byte[] data = ((imageDecodingInfo.isCacheBigPhoto() || z) && (photoBytesCache2 = GalleryImageLoaderCache.getInstance().getPhotoBytesCache()) != null) ? photoBytesCache2.getData(MemoryCacheUtils.generateBigPhotoKey(str, fileLength)) : null;
            if (data == null || data.length <= 0) {
                inputStream = null;
                bitmap = null;
            } else {
                if (z) {
                    Log.d(ImageLoader.TAG, "try load from big photo cache successfully");
                }
                inputStream = new ByteArrayInputStream(data);
                try {
                    bitmap = decodeInternal(imageDecodingInfo, inputStream, false);
                    Log.i(ImageLoader.TAG, "decode from cache " + imageDecodingInfo.getImageUri());
                } catch (Throwable th) {
                    th = th;
                    IoUtils.closeSilently(inputStream);
                    throw th;
                }
            }
            if (bitmap == null || bitmap.isRecycled()) {
                IoUtils.closeSilently(inputStream);
                String generateBigPhotoKey = imageDecodingInfo.isCacheBigPhoto() ? MemoryCacheUtils.generateBigPhotoKey(imageDecodingInfo.getImageUri(), fileLength) : null;
                inputStream = getImageStream(imageDecodingInfo);
                if (inputStream == null) {
                    L.e("No stream for image [%s]", imageDecodingInfo.getImageKey());
                    IoUtils.closeSilently(inputStream);
                    return null;
                }
                bitmap = decodeInternal(imageDecodingInfo, inputStream, true);
                if (imageDecodingInfo.isCacheBigPhoto()) {
                    byte[] compressBitmapAsPng = imageDecodingInfo.isCacheBigPhotoAsPng() ? BaseBitmapUtils.compressBitmapAsPng(bitmap) : BaseBitmapUtils.compressToBytes(bitmap, 100);
                    if (!(compressBitmapAsPng == null || compressBitmapAsPng.length <= 0 || (photoBytesCache = GalleryImageLoaderCache.getInstance().getPhotoBytesCache()) == null)) {
                        photoBytesCache.putData(generateBigPhotoKey, compressBitmapAsPng);
                        if (imageDecodingInfo.isCacheBigPhotoAsPng()) {
                            Log.d(ImageLoader.TAG, "cache big photo as png, bytes size: " + compressBitmapAsPng.length);
                        }
                    }
                }
            }
            IoUtils.closeSilently(inputStream);
            return bitmap;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            IoUtils.closeSilently(inputStream);
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    public ExifInfo defineExifOrientation(ImageDecodingInfo imageDecodingInfo) {
        ? r2 = 0;
        if (ImageDownloader.Scheme.ofUri(imageDecodingInfo.getImageUri()) == ImageDownloader.Scheme.FILE) {
            if (imageDecodingInfo.isSecretImage() && imageDecodingInfo.getSecretKey() != null) {
                r2 = imageDecodingInfo.getSecretKey();
            }
            return defineExifOrientation(ImageDownloader.Scheme.FILE.crop(imageDecodingInfo.getImageUri()), r2);
        }
        try {
            InputStream imageStream = getImageStream(imageDecodingInfo);
            r2 = imageStream;
            if (imageStream != null) {
                ExifInfo defineExifOrientation = defineExifOrientation(imageStream);
                IoUtils.closeSilently(imageStream);
                return defineExifOrientation;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            IoUtils.closeSilently((Closeable) null);
            throw th;
        }
        IoUtils.closeSilently(r2);
        return new ExifInfo();
    }

    /* access modifiers changed from: protected */
    public ImageFileInfo defineImageSize(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        ExifInfo exifInfo = new ExifInfo();
        return new ImageFileInfo(new ImageSize(options.outWidth, options.outHeight, exifInfo.rotation), exifInfo);
    }

    /* access modifiers changed from: protected */
    public ImageFileInfo defineImageSizeAndRotation(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        ExifInfo exifInfo = (!imageDecodingInfo.shouldConsiderExifParams() || !canDefineExifParams(imageDecodingInfo.getImageUri(), options.outMimeType)) ? new ExifInfo() : defineExifOrientation(imageDecodingInfo);
        return new ImageFileInfo(new ImageSize(options.outWidth, options.outHeight, exifInfo.rotation), exifInfo);
    }

    /* access modifiers changed from: protected */
    public InputStream getImageStream(ImageDecodingInfo imageDecodingInfo) throws IOException {
        return imageDecodingInfo.getDownloader().getStream(imageDecodingInfo.getImageUri(), imageDecodingInfo.getExtraForDownloader());
    }

    /* access modifiers changed from: protected */
    public BitmapFactory.Options prepareDecodingOptions(ImageSize imageSize, ImageDecodingInfo imageDecodingInfo) {
        int i;
        ImageScaleType imageScaleType = imageDecodingInfo.getImageScaleType();
        if (imageScaleType == ImageScaleType.NONE) {
            i = 1;
        } else if (imageScaleType == ImageScaleType.NONE_SAFE) {
            i = ImageSizeUtils.computeMinImageSampleSize(imageSize, imageDecodingInfo.getDecodingOptions().inPreferredConfig);
        } else {
            i = ImageSizeUtils.computeImageSampleSize(imageSize, imageDecodingInfo.getTargetSize(), imageDecodingInfo.getViewScaleType(), usePowerOf2(imageScaleType));
        }
        if (i > 1 && this.loggingEnabled) {
            L.d("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", imageSize, imageSize.scaleDown(i), Integer.valueOf(i), imageDecodingInfo.getImageKey());
        }
        BitmapFactory.Options decodingOptions = imageDecodingInfo.getDecodingOptions();
        decodingOptions.inSampleSize = i;
        if (imageDecodingInfo.getInBitmapCache() != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.outWidth = imageSize.getWidth();
            options.outHeight = imageSize.getHeight();
            options.inSampleSize = decodingOptions.inSampleSize;
            options.inPreferredConfig = decodingOptions.inPreferredConfig;
            decodingOptions.inBitmap = imageDecodingInfo.getInBitmapCache().get(options);
            decodingOptions.inMutable = true;
        }
        return decodingOptions;
    }

    /* access modifiers changed from: protected */
    public InputStream resetStream(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) throws IOException {
        if (inputStream.markSupported()) {
            try {
                inputStream.reset();
                return inputStream;
            } catch (IOException unused) {
            }
        }
        IoUtils.closeSilently(inputStream);
        return getImageStream(imageDecodingInfo);
    }
}
