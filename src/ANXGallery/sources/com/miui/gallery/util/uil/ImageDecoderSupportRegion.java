package com.miui.gallery.util.uil;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.util.CryptoUtil;
import com.miui.gallery.util.DecodeRegionImageUtils;
import com.miui.gallery.util.SecretAlbumCryptoUtils;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageDecoderSupportRegion extends BaseImageDecoder {
    public ImageDecoderSupportRegion(boolean z) {
        super(z);
    }

    private InputStream getVideoThumbnailStream(String str) {
        Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 2);
        if (createVideoThumbnail == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createVideoThumbnail.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public Bitmap decode(ImageDecodingInfo imageDecodingInfo) throws IOException {
        if (imageDecodingInfo.getRegionDecodeRect() == null) {
            return super.decode(imageDecodingInfo);
        }
        InputStream imageStream = getImageStream(imageDecodingInfo);
        try {
            BaseImageDecoder.ImageFileInfo defineImageSizeAndRotation = defineImageSizeAndRotation(imageStream, imageDecodingInfo);
            imageStream = resetStream(imageStream, imageDecodingInfo);
            Bitmap decodeFaceRegion = imageDecodingInfo.isRegionDecodeFace() ? DecodeRegionImageUtils.decodeFaceRegion(imageDecodingInfo.getRegionDecodeRect(), imageStream, 1.5f, imageDecodingInfo.getTargetSize().getWidth(), defineImageSizeAndRotation.exif.exifOrientation) : DecodeRegionImageUtils.decodeRegion(imageDecodingInfo.getRegionDecodeRect(), imageStream, imageDecodingInfo.getTargetSize().getWidth());
            if (decodeFaceRegion != null) {
                return considerExactScaleAndOrientation(decodeFaceRegion, imageDecodingInfo, defineImageSizeAndRotation.exif.rotation, defineImageSizeAndRotation.exif.flipHorizontal);
            }
            L.e("Image can't be decoded [%s]", imageDecodingInfo.getImageKey());
            return decodeFaceRegion;
        } finally {
            IoUtils.closeSilently(imageStream);
        }
    }

    /* access modifiers changed from: protected */
    public InputStream getImageStream(ImageDecodingInfo imageDecodingInfo) throws IOException {
        Uri decryptVideo2CacheFolder;
        if (!imageDecodingInfo.isSecretImage() || imageDecodingInfo.getSecretKey() == null) {
            return super.getImageStream(imageDecodingInfo);
        }
        if (CloudUtils.SecretAlbumUtils.isEncryptedVideoByPath(imageDecodingInfo.getImageUri())) {
            if (ImageDownloader.Scheme.ofUri(imageDecodingInfo.getImageUri()) != ImageDownloader.Scheme.FILE || (decryptVideo2CacheFolder = SecretAlbumCryptoUtils.decryptVideo2CacheFolder(Uri.fromFile(new File(ImageDownloader.Scheme.FILE.crop(imageDecodingInfo.getImageUri()))), imageDecodingInfo.getSecretKey(), -1)) == null) {
                return null;
            }
            return getVideoThumbnailStream(decryptVideo2CacheFolder.getPath());
        } else if (CloudUtils.SecretAlbumUtils.isUnencryptedVideoByPath(imageDecodingInfo.getImageUri()) || CloudUtils.SecretAlbumUtils.isUnencryptedImageByPath(imageDecodingInfo.getImageUri())) {
            return super.getImageStream(imageDecodingInfo);
        } else {
            InputStream imageStream = super.getImageStream(imageDecodingInfo);
            if (imageStream != null) {
                return CryptoUtil.getDecryptCipherInputStream(imageStream, imageDecodingInfo.getSecretKey());
            }
            return null;
        }
    }
}
