package com.miui.gallery.picker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.picker.PickerActivity;
import com.miui.gallery.picker.helper.Picker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class PickPeopleActivity extends PickerActivity {
    private PickPeoplePageFragment mFragment;

    /* access modifiers changed from: protected */
    public void done(int i) {
        Intent intent = new Intent();
        intent.putExtra("internal_key_updated_selection", copyPicker(getPicker()));
        setResult(i, intent);
        finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != 0) {
            Set<String> set = (Set) intent.getSerializableExtra("internal_key_updated_selection");
            if (set != null) {
                ArrayList arrayList = new ArrayList();
                for (String str : this.mPicker) {
                    if (!set.contains(str)) {
                        arrayList.add(str);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.mPicker.remove((String) it.next());
                }
                for (String str2 : set) {
                    if (!this.mPicker.contains(str2)) {
                        this.mPicker.pick(str2);
                    }
                }
                if (i2 == -1) {
                    this.mPicker.done();
                    return;
                }
                return;
            }
            return;
        }
        this.mPicker.cancel();
    }

    public void onBackPressed() {
        done(2);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mPicker != null) {
            setContentView(R.layout.picker_people_page_activity);
            boolean booleanExtra = getIntent().getBooleanExtra("pick_people", false);
            this.mFragment = (PickPeoplePageFragment) getFragmentManager().findFragmentById(R.id.people_page);
            this.mFragment.setIsPickPeople(booleanExtra);
            if (booleanExtra) {
                String stringExtra = getIntent().getStringExtra("pick_people_candidate_name");
                if (TextUtils.isEmpty(stringExtra)) {
                    stringExtra = getString(R.string.choose_people);
                }
                setTitle(stringExtra);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Picker onCreatePicker() {
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra("pick-upper-bound", 0);
        int intExtra2 = intent.getIntExtra("pick-lower-bound", 1);
        Picker.MediaType mediaType = Picker.MediaType.values()[intent.getIntExtra("picker_media_type", 0)];
        Set set = (Set) intent.getSerializableExtra("picker_result_set");
        if (getIntent().getBooleanExtra("pick_people", false)) {
            set = new LinkedHashSet();
        }
        Picker.ResultType resultType = Picker.ResultType.values()[intent.getIntExtra("picker_result_type", 0)];
        PickerActivity.SimplePicker simplePicker = new PickerActivity.SimplePicker(this, intExtra, intExtra2, set);
        simplePicker.setMediaType(mediaType);
        simplePicker.setResultType(resultType);
        if (intent.hasExtra("extra_filter_media_type")) {
            simplePicker.setFilterMimeTypes(intent.getStringArrayExtra("extra_filter_media_type"));
        }
        return simplePicker;
    }

    /* access modifiers changed from: protected */
    public void onDone() {
        done(-1);
    }
}
