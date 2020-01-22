package uk.co.senab.photoview;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

public interface IPhotoView {
    public static final Interpolator DEFAULT_ZOOM_INTERPOLATOR = new AccelerateDecelerateInterpolator();
}
