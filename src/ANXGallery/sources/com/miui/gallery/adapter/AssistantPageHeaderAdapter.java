package com.miui.gallery.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Guideline;
import com.miui.gallery.R;
import com.miui.gallery.activity.RecommendActivity;
import com.miui.gallery.editor.photo.app.filter.skytransfer.SkyCheckHelper;
import com.miui.gallery.editor.photo.app.filter.skytransfer.SkyDownloadStateListener;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.reddot.DisplayStatusManager;
import com.miui.gallery.util.ActionURIHandler;
import com.miui.gallery.util.FilterSkyEntranceUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MovieLibraryLoaderHelper;

public class AssistantPageHeaderAdapter extends BaseAdapter {
    /* access modifiers changed from: private */
    public Context mContext;
    private Entrance[] mEntrances = new Entrance[3];
    private LayoutInflater mLayoutInflater = LayoutInflater.from(this.mContext);

    private class CollageEntrance extends Entrance {
        public CollageEntrance(int i, int i2, int i3) {
            super(i, i2, i3, "collage");
        }

        public void onClick(View view) {
            super.onClick(view);
            ActionURIHandler.handleUri((Activity) AssistantPageHeaderAdapter.this.mContext, GalleryContract.Common.URI_COLLAGE_PAGE);
            GallerySamplingStatHelper.recordCountEvent("assistant", "home_page_menu_collage");
        }
    }

    private abstract class Entrance implements View.OnClickListener {
        String mFeatureName;
        int mIconRes;
        int mIndex;
        int mNameRes;

        public Entrance(int i, int i2, int i3, String str) {
            this.mIndex = i;
            this.mNameRes = i2;
            this.mIconRes = i3;
            this.mFeatureName = str;
        }

        public String getFeatureName() {
            return this.mFeatureName;
        }

        public void onClick(View view) {
            DisplayStatusManager.setRedDotClicked(this.mFeatureName);
        }
    }

    private class FilterSkyEntrance extends Entrance {
        public FilterSkyEntrance(int i, int i2, int i3) {
            super(i, i2, i3, "filter_sky");
        }

        public void onClick(View view) {
            super.onClick(view);
            if (SkyCheckHelper.getInstance().checkAbleOrDownload((Activity) AssistantPageHeaderAdapter.this.mContext)) {
                ActionURIHandler.handleUri((Activity) AssistantPageHeaderAdapter.this.mContext, GalleryContract.Common.URI_FILTER_SKY);
            }
            GallerySamplingStatHelper.recordCountEvent("assistant", "home_page_menu_filter_sky");
        }
    }

    private class PhotoMovieEntrance extends Entrance {
        public PhotoMovieEntrance(int i, int i2, int i3) {
            super(i, i2, i3, "photo_movie");
        }

        public void onClick(View view) {
            super.onClick(view);
            if (MovieLibraryLoaderHelper.getInstance().checkAbleOrDownload((Activity) AssistantPageHeaderAdapter.this.mContext)) {
                ActionURIHandler.handleUri((Activity) AssistantPageHeaderAdapter.this.mContext, GalleryContract.Common.URI_PHOTO_MOVIE);
            }
            GallerySamplingStatHelper.recordCountEvent("assistant", "home_page_menu_photo_movie");
        }
    }

    private class RecommendEntrance extends Entrance {
        public RecommendEntrance(int i, int i2, int i3) {
            super(i, i2, i3, "recommendation");
        }

        public void onClick(View view) {
            super.onClick(view);
            if (AssistantPageHeaderAdapter.this.mContext != null) {
                AssistantPageHeaderAdapter.this.mContext.startActivity(new Intent(AssistantPageHeaderAdapter.this.mContext, RecommendActivity.class));
            }
            GallerySamplingStatHelper.recordCountEvent("assistant", "assistant_page_discover");
        }
    }

    private static class ViewHolder implements View.OnAttachStateChangeListener {
        Guideline mGuideline;
        ImageView mIcon;
        View mItemView;
        private MovieLibraryLoaderHelper.DownloadStateListener mMovieDownloadStateListener;
        ImageView mRedDot;
        private SkyDownloadStateListener mSkyDownloadStateListener;
        TextView mTitle;

        public ViewHolder(View view) {
            this.mItemView = view;
            this.mGuideline = (Guideline) view.findViewById(R.id.guide_line);
            this.mIcon = (ImageView) view.findViewById(R.id.icon);
            this.mTitle = (TextView) view.findViewById(R.id.title);
            this.mRedDot = (ImageView) view.findViewById(R.id.red_dot);
            this.mItemView.addOnAttachStateChangeListener(this);
        }

        public void bindView(Context context, final Entrance entrance, int i) {
            this.mItemView.setOnClickListener(entrance);
            this.mIcon.setImageDrawable(context.getResources().getDrawable(entrance.mIconRes));
            this.mTitle.setText(entrance.mNameRes);
            int i2 = i % 3;
            if (i2 == 0) {
                this.mGuideline.setGuidelinePercent(0.53f);
            } else if (i2 == 1) {
                this.mGuideline.setGuidelinePercent(0.5f);
            } else if (i2 == 2) {
                this.mGuideline.setGuidelinePercent(0.47f);
            }
            this.mRedDot.setVisibility(DisplayStatusManager.getRedDotStatus(entrance.getFeatureName()) ? 0 : 4);
            if (!(entrance instanceof PhotoMovieEntrance)) {
                this.mMovieDownloadStateListener = null;
            } else if (this.mMovieDownloadStateListener == null) {
                this.mMovieDownloadStateListener = new MovieLibraryLoaderHelper.DownloadStateListener() {
                    public void onDownloading() {
                        ViewHolder.this.mTitle.setText(R.string.photo_movie_menu_loading);
                    }

                    public void onFinish(boolean z, int i) {
                        ViewHolder.this.mTitle.setText(entrance.mNameRes);
                    }
                };
            }
            if (!AssistantPageHeaderAdapter.replaceRecommend() || !(entrance instanceof FilterSkyEntrance)) {
                this.mSkyDownloadStateListener = null;
            } else if (this.mSkyDownloadStateListener == null) {
                this.mSkyDownloadStateListener = new SkyDownloadStateListener() {
                    public void onDownloadStart(int i) {
                    }

                    public void onDownloading(int i, int i2) {
                        ViewHolder.this.mTitle.setText(R.string.photo_movie_menu_loading);
                    }

                    public void onFinish(int i, boolean z, int i2) {
                        ViewHolder.this.mTitle.setText(entrance.mNameRes);
                    }
                };
            }
        }

        public void onViewAttachedToWindow(View view) {
            if (this.mMovieDownloadStateListener != null) {
                MovieLibraryLoaderHelper.getInstance().addDownloadStateListener(this.mMovieDownloadStateListener);
            }
            if (this.mSkyDownloadStateListener != null) {
                SkyCheckHelper.getInstance().addDownloadStateListener(this.mSkyDownloadStateListener);
            }
        }

        public void onViewDetachedFromWindow(View view) {
            if (this.mMovieDownloadStateListener != null) {
                MovieLibraryLoaderHelper.getInstance().removeDownloadStateListener(this.mMovieDownloadStateListener);
            }
            if (this.mSkyDownloadStateListener != null) {
                SkyCheckHelper.getInstance().removeDownloadStateListener(this.mSkyDownloadStateListener);
            }
        }
    }

    public AssistantPageHeaderAdapter(Context context) {
        this.mContext = context;
        this.mEntrances[0] = new CollageEntrance(0, R.string.home_menu_collage, R.drawable.assistant_page_collage);
        this.mEntrances[1] = new PhotoMovieEntrance(1, R.string.home_menu_photo_movie, R.drawable.assistant_page_photomovie);
        if (replaceRecommend()) {
            this.mEntrances[2] = new FilterSkyEntrance(2, R.string.filter_single_sky, R.drawable.assistant_page_filter_sky);
        } else {
            this.mEntrances[2] = new RecommendEntrance(2, R.string.assistant_page_discovery, R.drawable.assistant_page_recommend);
        }
    }

    public static boolean replaceRecommend() {
        return FilterSkyEntranceUtils.showSingleFilterSky();
    }

    public int getCount() {
        Entrance[] entranceArr = this.mEntrances;
        if (entranceArr != null) {
            return entranceArr.length;
        }
        return 0;
    }

    public Entrance getItem(int i) {
        Entrance[] entranceArr = this.mEntrances;
        if (entranceArr == null || i < 0 || i >= entranceArr.length) {
            return null;
        }
        return entranceArr[i];
    }

    public long getItemId(int i) {
        Entrance item = getItem(i);
        if (item != null) {
            return (long) item.mIndex;
        }
        return -1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mLayoutInflater.inflate(R.layout.assistant_page_header_grid_item, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }
        ((ViewHolder) view.getTag()).bindView(this.mContext, getItem(i), i);
        return view;
    }
}
