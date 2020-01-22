package com.miui.gallery.model.repository.config;

import com.miui.gallery.model.repository.AlbumRepository;
import com.miui.gallery.model.repository.LocationRepository;
import com.miui.gallery.model.repository.PhotoRepository;
import com.miui.gallery.model.repository.datesource.album.IAlbumRepository;
import com.miui.gallery.model.repository.datesource.location.ILocationRepository;
import com.miui.gallery.model.repository.datesource.photo.IPhotoRepository;
import java.util.HashMap;

public class ModelConfig {
    private static final HashMap<String, Class<?>> mConfigs = new HashMap<>(2);

    public interface ModelNames {
        public static final String ALBUM_REPOSITORY = IAlbumRepository.class.getName();
        public static final String LOCATION_REPOSITORY = ILocationRepository.class.getName();
        public static final String PHOTO_REPOSITORY = IPhotoRepository.class.getName();
    }

    public static HashMap<String, Class<?>> getModelConfigs() {
        HashMap<String, Class<?>> hashMap;
        if (!mConfigs.isEmpty()) {
            return mConfigs;
        }
        synchronized (mConfigs) {
            if (mConfigs.isEmpty()) {
                initModels(mConfigs);
            }
            hashMap = mConfigs;
        }
        return hashMap;
    }

    private static void initModels(HashMap<String, Class<?>> hashMap) {
        hashMap.put(ModelNames.PHOTO_REPOSITORY, PhotoRepository.class);
        hashMap.put(ModelNames.LOCATION_REPOSITORY, LocationRepository.class);
        hashMap.put(ModelNames.ALBUM_REPOSITORY, AlbumRepository.class);
    }
}
