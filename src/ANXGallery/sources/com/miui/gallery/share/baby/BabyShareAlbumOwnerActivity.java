package com.miui.gallery.share.baby;

import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.share.AlbumShareOperations;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.share.CloudUserCacheEntry;
import com.miui.gallery.share.ShareAlbumOwnerBaseActivity;
import com.miui.gallery.share.ShareUserAdapterBase;
import java.util.Iterator;
import java.util.List;

public class BabyShareAlbumOwnerActivity extends ShareAlbumOwnerBaseActivity {
    private static final RelationEntry[] sDefaultRelationEntries = {new RelationEntry("father", R.string.relation_text_father), new RelationEntry("mother", R.string.relation_text_mother), new RelationEntry("grandfather", R.string.relation_text_grandfather), new RelationEntry("grandmother", R.string.relation_text_grandmother), new RelationEntry("maternalGrandfather", R.string.relation_text_maternal_grandfather), new RelationEntry("maternalGrandmother", R.string.relation_text_maternal_grandmother)};

    private static class RelationEntry {
        String relation;
        int relationTextId;

        RelationEntry(String str, int i) {
            this.relation = str;
            this.relationTextId = i;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.share.baby.BabyShareAlbumOwnerActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public ShareUserAdapterBase createShareUserAdapter() {
        return new BabyAlbumShareUserAdapter(this, GalleryCloudUtils.getAccountName());
    }

    /* access modifiers changed from: protected */
    public int getContentView() {
        return R.layout.baby_share_album_owner;
    }

    public String getPageName() {
        return "album_baby_share_owner_info";
    }

    /* access modifiers changed from: protected */
    public int getPreferencesResourceId() {
        return R.xml.baby_share_album_preference;
    }

    /* access modifiers changed from: protected */
    public List<CloudUserCacheEntry> getShareUsers() {
        boolean z;
        List<CloudUserCacheEntry> shareUsers = super.getShareUsers();
        shareUsers.add(getOwnerEntry(GalleryCloudUtils.getAccountName()));
        for (RelationEntry relationEntry : sDefaultRelationEntries) {
            Iterator<CloudUserCacheEntry> it = shareUsers.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (TextUtils.equals(relationEntry.relation, it.next().mRelation)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                shareUsers.add(new CloudUserCacheEntry(this.mAlbumId, (String) null, 0, relationEntry.relation, getString(relationEntry.relationTextId), (String) null, (String) null));
            }
        }
        shareUsers.add(new CloudUserCacheEntry(this.mAlbumId, (String) null, 0, "family", getString(R.string.family_member_invitation_text), (String) null, (String) null));
        return shareUsers;
    }

    /* access modifiers changed from: protected */
    public void requestInvitation(CloudUserCacheEntry cloudUserCacheEntry, AlbumShareUIManager.OnCompletionListener<Void, AlbumShareOperations.OutgoingInvitation> onCompletionListener) {
        if (cloudUserCacheEntry != null) {
            String str = cloudUserCacheEntry.mRelationText;
            if (TextUtils.isEmpty(str) && TextUtils.equals(cloudUserCacheEntry.mRelation, "family")) {
                str = getString(R.string.relation_text_family);
            }
            String str2 = str;
            CloudUserCacheEntry ownerEntry = getOwnerEntry(GalleryCloudUtils.getAccountName());
            requestInvitation(cloudUserCacheEntry.mRelation, str2, ownerEntry.mRelation, ownerEntry.mRelationText, onCompletionListener);
        }
    }
}
