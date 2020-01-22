package com.miui.gallery.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.model.PeopleContactInfo;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.PeopleRelationCreatorDialogFragment;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.util.ArrayList;
import java.util.List;

public class PeopleRelationSetDialogFragment extends GalleryDialogFragment {
    private ArrayList<String> mRelationNameItems = new ArrayList<>();
    /* access modifiers changed from: private */
    public RelationSelectedListener mRelationSelectedListener;
    /* access modifiers changed from: private */
    public ArrayList<String> mRelationValueItems = new ArrayList<>();

    public interface RelationSelectedListener {
        void onRelationSelected(String str, String str2);
    }

    public static void createRelationSetDialog(final Activity activity, final String str, final String str2, final int i, final RelationSelectedListener relationSelectedListener) {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Bundle>() {
            public Bundle run(ThreadPool.JobContext jobContext) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(PeopleContactInfo.getSystemRelationNameItems());
                arrayList.addAll(PeopleContactInfo.getSystemRelationValueItems());
                boolean z = i <= 1;
                if (z) {
                    List<Long> queryPeopleIdOfRelation = FaceManager.queryPeopleIdOfRelation(activity, PeopleContactInfo.Relation.myself.getRelationType());
                    z = queryPeopleIdOfRelation == null || queryPeopleIdOfRelation.size() <= 0;
                }
                if (!z) {
                    int indexOf = arrayList.indexOf(PeopleContactInfo.getRelationValue(PeopleContactInfo.Relation.myself));
                    arrayList.remove(indexOf);
                    arrayList2.remove(indexOf);
                }
                ArrayList<String> userDefineRelations = PeopleContactInfo.UserDefineRelation.getUserDefineRelations();
                if (userDefineRelations == null) {
                    userDefineRelations = FaceManager.queryAllUserDefineRelationsOfPeople(activity);
                }
                if (MiscUtil.isValid(userDefineRelations)) {
                    arrayList.addAll(userDefineRelations);
                    arrayList2.addAll(userDefineRelations);
                }
                PeopleContactInfo.UserDefineRelation.setUserDefineRelations(userDefineRelations);
                Bundle bundle = new Bundle();
                bundle.putString("people_relation_set_title", str);
                bundle.putString("default_relation", str2);
                bundle.putStringArrayList("relation_names", arrayList2);
                bundle.putStringArrayList("relation_values", arrayList);
                return bundle;
            }
        }, new FutureHandler<Bundle>() {
            public void onPostExecute(Future<Bundle> future) {
                if (future != null && future.get() != null) {
                    PeopleRelationSetDialogFragment.doCreateDialog(activity, future.get(), relationSelectedListener);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void doCreateDialog(Activity activity, Bundle bundle, RelationSelectedListener relationSelectedListener) {
        PeopleRelationSetDialogFragment peopleRelationSetDialogFragment = new PeopleRelationSetDialogFragment();
        peopleRelationSetDialogFragment.setArguments(bundle);
        peopleRelationSetDialogFragment.setRelationSelectedListener(relationSelectedListener);
        peopleRelationSetDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "PeopleRelationSetDialogFragment");
    }

    private int getDefaultIndexOfDialog() {
        String string = getArguments().getString("default_relation");
        if (TextUtils.isEmpty(string)) {
            return -1;
        }
        return this.mRelationValueItems.indexOf(string);
    }

    private void initDialogAdapter() {
        this.mRelationNameItems.addAll(getArguments().getStringArrayList("relation_names"));
        this.mRelationValueItems.addAll(getArguments().getStringArrayList("relation_values"));
        this.mRelationNameItems.add(getString(R.string.define_by_user));
        this.mRelationValueItems.add(getString(R.string.define_by_user));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDialogAdapter();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getString("people_relation_set_title"));
        int defaultIndexOfDialog = getDefaultIndexOfDialog();
        ArrayList<String> arrayList = this.mRelationNameItems;
        builder.setSingleChoiceItems((CharSequence[]) arrayList.toArray(new String[arrayList.size()]), defaultIndexOfDialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (i == PeopleRelationSetDialogFragment.this.mRelationValueItems.indexOf(PeopleRelationSetDialogFragment.this.getString(R.string.define_by_user))) {
                    PeopleRelationCreatorDialogFragment peopleRelationCreatorDialogFragment = new PeopleRelationCreatorDialogFragment();
                    peopleRelationCreatorDialogFragment.setOnCreatedListener(new PeopleRelationCreatorDialogFragment.OnRelationCreatedListener() {
                        public void onRelationCreated(String str) {
                            if (PeopleRelationSetDialogFragment.this.mRelationSelectedListener != null) {
                                PeopleRelationSetDialogFragment.this.mRelationSelectedListener.onRelationSelected(PeopleContactInfo.getUserDefineRelation(), str);
                            }
                        }
                    });
                    peopleRelationCreatorDialogFragment.showAllowingStateLoss(PeopleRelationSetDialogFragment.this.getActivity().getFragmentManager(), "PeopleRelationCreatorDialogFragment");
                    return;
                }
                String str = (String) PeopleRelationSetDialogFragment.this.mRelationValueItems.get(i);
                String userDefineRelation = PeopleContactInfo.getSystemRelationValueItems().contains(str) ? str : PeopleContactInfo.getUserDefineRelation();
                if (PeopleRelationSetDialogFragment.this.mRelationSelectedListener != null) {
                    PeopleRelationSetDialogFragment.this.mRelationSelectedListener.onRelationSelected(userDefineRelation, str);
                }
            }
        });
        return builder.create();
    }

    public void setRelationSelectedListener(RelationSelectedListener relationSelectedListener) {
        this.mRelationSelectedListener = relationSelectedListener;
    }
}
