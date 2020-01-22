package com.miui.gallery.assistant.recommend;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.cloudcontrol.RecommendItem;
import com.miui.gallery.listener.SingleClickListener;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MiscUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class RecommendFragmentAdapter extends RecyclerView.Adapter {
    /* access modifiers changed from: private */
    public Context mContext;
    private ArrayList<RecommendItem> mRecommendItems = new ArrayList<>();

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIcon = ((ImageView) this.itemView.findViewById(R.id.banner));

        public BannerViewHolder(ViewGroup viewGroup) {
            super(LayoutInflater.from(RecommendFragmentAdapter.this.mContext).inflate(R.layout.recommend_banner_view, viewGroup, false));
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        public View mGuideline = this.itemView.findViewById(R.id.guide_line);
        public ImageView mIcon = ((ImageView) this.itemView.findViewById(R.id.icon));
        public TextView mSubtitle = ((TextView) this.itemView.findViewById(R.id.subtitle));
        public TextView mTitle = ((TextView) this.itemView.findViewById(R.id.title));

        public ItemViewHolder(ViewGroup viewGroup) {
            super(LayoutInflater.from(RecommendFragmentAdapter.this.mContext).inflate(R.layout.recommend_item_view, viewGroup, false));
        }
    }

    public RecommendFragmentAdapter(Context context) {
        this.mContext = context;
    }

    private String constructBannerUrl(String str) {
        String str2;
        Uri parse = Uri.parse(str);
        String path = parse.getPath();
        if (Locale.getDefault().getLanguage().equals("en")) {
            str2 = path.replaceFirst("x*hdpi", "en/" + this.mContext.getResources().getString(R.string.which_hdpi));
        } else {
            str2 = path.replaceFirst("x*hdpi", this.mContext.getResources().getString(R.string.which_hdpi));
        }
        return new Uri.Builder().scheme(parse.getScheme()).authority(parse.getAuthority()).path(str2).query(parse.getQuery()).fragment(parse.getFragment()).build().toString();
    }

    private String constructIconUrl(String str) {
        Uri parse = Uri.parse(str);
        return new Uri.Builder().scheme(parse.getScheme()).authority(parse.getAuthority()).path(parse.getPath().replaceFirst("x*hdpi", this.mContext.getResources().getString(R.string.which_hdpi))).query(parse.getQuery()).fragment(parse.getFragment()).build().toString();
    }

    /* access modifiers changed from: private */
    public String constructLandingUrl(String str) {
        Uri parse = Uri.parse(str);
        String valueOf = String.valueOf(MiscUtil.getAppVersionCode());
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        return parse.buildUpon().appendQueryParameter("version", valueOf).appendQueryParameter("language", String.format("%s_%s", new Object[]{language, country})).build().toString();
    }

    public void changeRecommendItems(ArrayList<RecommendItem> arrayList) {
        this.mRecommendItems = arrayList;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        ArrayList<RecommendItem> arrayList = this.mRecommendItems;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public int getItemViewType(int i) {
        return i == 0 ? 1 : 2;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ArrayList<RecommendItem> arrayList = this.mRecommendItems;
        if (arrayList != null && arrayList.size() != 0) {
            final RecommendItem recommendItem = this.mRecommendItems.get(i);
            if (getItemViewType(i) == 1) {
                ImageLoader.getInstance().displayImage(constructBannerUrl(recommendItem.getIconUrl()), (ImageAware) new ImageViewAware(((BannerViewHolder) viewHolder).mIcon), new DisplayImageOptions.Builder().showImageOnLoading((int) R.drawable.recommend_banner_loading).showImageOnFail(R.drawable.recommend_banner_loading).cacheInMemory(true).build());
                return;
            }
            int i2 = i % 7;
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            if (i2 == 0) {
                i2 = 7;
            }
            objArr[0] = Integer.valueOf(i2);
            int identifier = this.mContext.getResources().getIdentifier(String.format(locale, "recommend_item%d_loading", objArr), "drawable", "com.miui.gallery");
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            ImageLoader.getInstance().displayImage(constructIconUrl(recommendItem.getIconUrl()), (ImageAware) new ImageViewAware(itemViewHolder.mIcon), new DisplayImageOptions.Builder().showImageOnLoading(identifier).showImageOnFail(identifier).cacheInMemory(true).build());
            itemViewHolder.mTitle.setText(recommendItem.getName());
            itemViewHolder.mSubtitle.setText(recommendItem.getDescription());
            viewHolder.itemView.setOnClickListener(new SingleClickListener() {
                /* access modifiers changed from: protected */
                public void onSingleClick(View view) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("item", recommendItem.getName());
                    GallerySamplingStatHelper.recordCountEvent("assistant_discover", "discover_item_clicked", hashMap);
                    Intent intent = new Intent("com.miui.gallery.action.VIEW_WEB_RECOMMEND");
                    intent.putExtra("url", RecommendFragmentAdapter.this.constructLandingUrl(recommendItem.getLandingUrl()));
                    intent.putExtra("from_recommend", true);
                    RecommendFragmentAdapter.this.mContext.startActivity(intent);
                }
            });
            if (i == this.mRecommendItems.size() - 1) {
                itemViewHolder.mGuideline.setVisibility(4);
            }
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i == 1 ? new BannerViewHolder(viewGroup) : new ItemViewHolder(viewGroup);
    }
}
