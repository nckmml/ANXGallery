package com.miui.gallery.card.ui.detail;

import android.app.Fragment;
import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.activity.BaseActivity;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.CardManager;
import com.miui.gallery.card.CardUtil;
import com.miui.gallery.movie.utils.MovieBackgroundDownloadManager;
import com.miui.gallery.util.MiscUtil;

public class StoryAlbumActivity extends BaseActivity {
    private StoryAlbumFragment mStoryAlbumFragment;

    private boolean isValidCard(Card card) {
        return card != null && MiscUtil.isValid(card.getSelectedMediaSha1s()) && card.isValid();
    }

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return 16908290;
    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        StoryAlbumFragment storyAlbumFragment = this.mStoryAlbumFragment;
        if (storyAlbumFragment != null) {
            storyAlbumFragment.onAttachDialogFragment(fragment);
        }
    }

    /* JADX WARNING: type inference failed for: r5v12, types: [com.miui.gallery.card.ui.detail.StoryAlbumFragment, android.app.Fragment] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        long longExtra = getIntent().getLongExtra("card_id", 0);
        getWindow().setBackgroundDrawableResource(R.color.story_background_color);
        Card cardByCardId = CardManager.getInstance().getCardByCardId(longExtra);
        if (!isValidCard(cardByCardId)) {
            finish();
            return;
        }
        MovieBackgroundDownloadManager.getInstance().downloadTemplate(this, CardUtil.getMovieTemplateFromCard(cardByCardId));
        this.mStoryAlbumFragment = (StoryAlbumFragment) getFragmentManager().findFragmentByTag("StoryAlbumFragment");
        if (this.mStoryAlbumFragment == null) {
            this.mStoryAlbumFragment = new StoryAlbumFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putLong("card_id", longExtra);
            this.mStoryAlbumFragment.setArguments(bundle2);
            startFragment(this.mStoryAlbumFragment, "StoryAlbumFragment", false, true);
        }
    }
}
