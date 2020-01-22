package com.miui.gallery.ui.photodetail.viewbean;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.data.LocationUtil;
import com.miui.gallery.model.PhotoDetailInfo;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.FormatUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StringUtils;
import java.util.Locale;
import miui.util.Pools;

public class PhotoDetailViewBean {
    private String cacheLocation;
    private String dateText;
    private long dateTime;
    private String displayFilePath;
    private String fileName;
    private long fileSize;
    private String fileSizeText;
    private long id;
    private boolean isFile;
    private double[] location;
    private String locationText;
    private String notDownLoad;
    private String phoneModel;
    private String takenParam;
    private String timeText;

    private String genAperture(String str) {
        String replaceAll = str.replaceAll("0+?$", "");
        if (replaceAll.endsWith(".")) {
            replaceAll = replaceAll + MovieStatUtils.DOWNLOAD_SUCCESS;
        }
        return "f/" + replaceAll;
    }

    private String genExposureTime(String str) {
        try {
            double doubleValue = Double.valueOf(str).doubleValue();
            if (doubleValue < 1.0d) {
                return String.format(Locale.US, "1/%d", new Object[]{Long.valueOf(Math.round(1.0d / doubleValue))});
            }
            int i = (int) doubleValue;
            double d = doubleValue - ((double) i);
            String str2 = String.valueOf(i) + "''";
            if (d <= 1.0E-4d) {
                return str2;
            }
            return str2 + String.format(Locale.US, " 1/%d", new Object[]{Long.valueOf(Math.round(1.0d / d))});
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return str;
        }
    }

    private String genFileSizeInfo(PhotoDetailInfo photoDetailInfo) {
        StringBuilder sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
        Long fileSize2 = photoDetailInfo.getFileSize();
        if (fileSize2 != null) {
            sb.append(FormatUtil.formatFileSize(GalleryApp.sGetAndroidContext(), fileSize2.longValue()));
            sb.append("    ");
        }
        Integer[] fileWidthAndHeight = photoDetailInfo.getFileWidthAndHeight();
        Integer orientation = photoDetailInfo.getOrientation();
        if (fileWidthAndHeight != null) {
            sb.append(genPixels(fileWidthAndHeight[0], fileWidthAndHeight[1], orientation));
            sb.append("    ");
        }
        Integer fileDuataion = photoDetailInfo.getFileDuataion();
        if (fileDuataion != null) {
            sb.append(FormatUtil.formatDuration(GalleryApp.sGetAndroidContext(), fileDuataion.intValue()));
        }
        String sb2 = sb.toString();
        Pools.getStringBuilderPool().release(sb);
        return sb2;
    }

    private String genFlashFired(int i) {
        return GalleryApp.sGetAndroidContext().getResources().getString(new PhotoDetailInfo.FlashState(i).isFlashFired() ? R.string.flash_on : R.string.flash_off);
    }

    private String genFocalLength(Double d) {
        return d + "mm";
    }

    private String genLocationText(double[] dArr) {
        if (dArr == null || !LocationUtil.isValidateCoordinate(dArr[0], dArr[1])) {
            return null;
        }
        return dArr[0] + ", " + dArr[1];
    }

    private String genPixels(Integer num, Integer num2, Integer num3) {
        if (num3 != null) {
            try {
                int exifOrientationToDegrees = ExifUtil.exifOrientationToDegrees(num3.intValue());
                if (exifOrientationToDegrees == 90 || exifOrientationToDegrees == 270) {
                    return num2 + "x" + num + "px";
                }
            } catch (Exception e) {
                Log.w("PhotoDetailViewBean", (Throwable) e);
            }
        }
        return num + "x" + num2 + "px";
    }

    private String genTimeMainTitle(PhotoDetailInfo photoDetailInfo) {
        return photoDetailInfo.getTakenTime(896);
    }

    private String genTimeSubTitle(PhotoDetailInfo photoDetailInfo) {
        if (photoDetailInfo.getTakenTime() == null) {
            return null;
        }
        StringBuilder sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
        sb.append(photoDetailInfo.getTakenTime(1024));
        sb.append("    ");
        sb.append(photoDetailInfo.getTakenTime(44));
        String sb2 = sb.toString();
        Pools.getStringBuilderPool().release(sb);
        return sb2;
    }

    public String genTakenMainTitle(PhotoDetailInfo photoDetailInfo) {
        StringBuilder sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
        String model = photoDetailInfo.getModel();
        if (model != null) {
            sb.append(model);
        }
        String make = photoDetailInfo.getMake();
        if (make != null) {
            sb.append(", ");
            sb.append(make);
        }
        String sb2 = sb.toString();
        Pools.getStringBuilderPool().release(sb);
        return sb2;
    }

    public String genTakenSubTitle(PhotoDetailInfo photoDetailInfo) {
        StringBuilder sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
        String aperture = photoDetailInfo.getAperture();
        if (aperture != null) {
            sb.append(genAperture(aperture));
            sb.append("    ");
        }
        String exposureTime = photoDetailInfo.getExposureTime();
        if (exposureTime != null) {
            sb.append(genExposureTime(exposureTime));
            sb.append("    ");
        }
        String iso = photoDetailInfo.getISO();
        if (iso != null) {
            sb.append(iso);
        }
        String sb2 = sb.toString();
        Pools.getStringBuilderPool().release(sb);
        return sb2;
    }

    public String genTakenThirdTitle(PhotoDetailInfo photoDetailInfo) {
        StringBuilder sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
        Double focalLength = photoDetailInfo.getFocalLength();
        if (focalLength != null) {
            sb.append(genFocalLength(focalLength));
            sb.append("    ");
        }
        String flashStatus = photoDetailInfo.getFlashStatus();
        if (flashStatus != null) {
            sb.append(genFlashFired(Integer.parseInt(flashStatus)));
        }
        String sb2 = sb.toString();
        Pools.getStringBuilderPool().release(sb);
        return sb2;
    }

    public String getCacheLocation() {
        return this.cacheLocation;
    }

    public String getDateText() {
        return this.dateText;
    }

    public long getDateTime() {
        return this.dateTime;
    }

    public String getDisplayFilePath() {
        return this.displayFilePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFileSizeText() {
        return this.fileSizeText;
    }

    public double[] getLocation() {
        return this.location;
    }

    public String getLocationText() {
        return this.locationText;
    }

    public String getNotDownLoad() {
        return this.notDownLoad;
    }

    public String getPhoneModel() {
        return this.phoneModel;
    }

    public String getTakenParam() {
        return this.takenParam;
    }

    public String getTimeText() {
        return this.timeText;
    }

    public boolean isFile() {
        return this.isFile;
    }

    public boolean isHaveCacheLocation() {
        return !StringUtils.isEmpty(getCacheLocation());
    }

    public boolean isHaveDateTime() {
        return getDateText() != null;
    }

    public boolean isHaveDownLoadTip() {
        return !StringUtils.isEmpty(getNotDownLoad());
    }

    public boolean isHaveFileInfo() {
        return !StringUtils.isEmpty(getFileSizeText());
    }

    public boolean isHaveFilePath() {
        return !StringUtils.isEmpty(getDisplayFilePath());
    }

    public boolean isHaveLocation() {
        return getLocation() != null;
    }

    public boolean isHaveTakenParams() {
        return !StringUtils.isEmpty(getTakenParam());
    }

    public void mapping(PhotoDetailInfo photoDetailInfo) {
        if (photoDetailInfo != null) {
            setDateText(genTimeMainTitle(photoDetailInfo));
            setTimeText(genTimeSubTitle(photoDetailInfo));
            setDateTime(photoDetailInfo.getTakenTime().longValue());
            setFileName(photoDetailInfo.getFileName());
            setFileSizeText(genFileSizeInfo(photoDetailInfo));
            setPhoneModel(genTakenMainTitle(photoDetailInfo));
            String genTakenSubTitle = genTakenSubTitle(photoDetailInfo);
            String genTakenThirdTitle = genTakenThirdTitle(photoDetailInfo);
            StringBuilder sb = (StringBuilder) Pools.getStringBuilderPool().acquire();
            if (!StringUtils.isEmpty(genTakenSubTitle)) {
                sb.append(genTakenSubTitle);
            }
            if (!StringUtils.isEmpty(genTakenThirdTitle)) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(genTakenThirdTitle);
            }
            setTakenParam(sb.toString());
            Pools.getStringBuilderPool().release(sb);
            setDisplayFilePath(photoDetailInfo.getFileLocalPath());
            setLocation(photoDetailInfo.getLocation());
            setFile(photoDetailInfo.isFilePath());
            setCacheLocation(photoDetailInfo.getCacheLocation());
        }
    }

    public void setCacheLocation(String str) {
        this.cacheLocation = str;
    }

    public void setDateText(String str) {
        this.dateText = str;
    }

    public void setDateTime(long j) {
        this.dateTime = j;
    }

    public void setDisplayFilePath(String str) {
        this.displayFilePath = str;
    }

    public void setFile(boolean z) {
        this.isFile = z;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
        if (j > 0) {
            this.fileSizeText = this.fileSizeText.replaceAll("[\\s\\S]+[K|k|M|m][B|b]", FormatUtil.formatFileSize(GalleryApp.sGetAndroidContext(), j));
        }
    }

    public void setFileSizeText(String str) {
        this.fileSizeText = str;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setLocation(double[] dArr) {
        if (dArr != null) {
            this.location = dArr;
            this.locationText = genLocationText(this.location);
        }
    }

    public void setPhoneModel(String str) {
        this.phoneModel = str;
    }

    public void setTakenParam(String str) {
        this.takenParam = str;
    }

    public void setTimeText(String str) {
        this.timeText = str;
    }
}
