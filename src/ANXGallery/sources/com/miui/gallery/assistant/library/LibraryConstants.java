package com.miui.gallery.assistant.library;

import com.miui.gallery.assistant.manager.ImageFeatureManager;

public class LibraryConstants {
    public static final String CURRENT_ABI = LibraryUtils.getCurrentAbi();
    public static final Long[] sAllLibraries = LibraryUtils.getAllLibraries();
    public static final Long[] sImageFeatureSelectionLibraries = {1002001L, 1004001L};
    public static final Long[] sImageProcessLibraries = (ImageFeatureManager.isDeviceSupportStoryFunction() ? sStoryLibraries : ImageFeatureManager.isImageFeatureCalculationEnable() ? sImageFeatureSelectionLibraries : new Long[0]);
    public static final Long[] sPhotoMovieLibraries = {1011L};
    public static final Long[] sSkyTransferLibraries = {1012L};
    public static final Long[] sStoryLibraries = {1002001L, 1004001L, 1005L};
}
