package com.miui.gallery.model;

import android.content.Context;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PeopleContactInfo {
    private static String JSON_TAG_PHONE_NUMBER = "phoneNumbers";
    private static String JSON_TAG_RELATION = "relation";
    private static String JSON_TAG_RELATIONSHIP = "relationship";
    private static String JSON_TAG_RELATIONTEXT = "relationText";
    public static String[] sRelationItemsValue;
    public String coverPath;
    public boolean isRepeatName;
    public String localGroupId;
    public String name;
    public String phone;
    public String relationWithMe;
    public String relationWithMeText;

    public enum Relation {
        unknown(0),
        friend(1),
        classmate(2),
        collegue(3),
        family(4),
        child(5),
        myself(6),
        userDefine(7);
        
        private final int mRelationType;

        private Relation(int i) {
            this.mRelationType = i;
        }

        public static Relation fromRelationType(int i) {
            Relation relation = friend;
            if (i == relation.mRelationType) {
                return relation;
            }
            Relation relation2 = classmate;
            if (i == relation2.mRelationType) {
                return relation2;
            }
            Relation relation3 = collegue;
            if (i == relation3.mRelationType) {
                return relation3;
            }
            Relation relation4 = family;
            if (i == relation4.mRelationType) {
                return relation4;
            }
            Relation relation5 = child;
            if (i == relation5.mRelationType) {
                return relation5;
            }
            Relation relation6 = myself;
            if (i == relation6.mRelationType) {
                return relation6;
            }
            Relation relation7 = userDefine;
            return i == relation7.mRelationType ? relation7 : unknown;
        }

        public int getRelationType() {
            return this.mRelationType;
        }
    }

    public static class UserDefineRelation {
        private static ArrayList<String> mUserDefineRelations;

        public static synchronized void addRelation(String str) {
            synchronized (UserDefineRelation.class) {
                if (!(mUserDefineRelations == null || str == null || mUserDefineRelations.indexOf(str) != -1)) {
                    mUserDefineRelations.add(str);
                    Collections.sort(mUserDefineRelations);
                }
            }
        }

        public static synchronized ArrayList<String> getUserDefineRelations() {
            synchronized (UserDefineRelation.class) {
                if (mUserDefineRelations == null) {
                    return null;
                }
                ArrayList<String> arrayList = new ArrayList<>(mUserDefineRelations);
                return arrayList;
            }
        }

        public static synchronized void setUserDefineRelations(ArrayList<String> arrayList) {
            synchronized (UserDefineRelation.class) {
                mUserDefineRelations = arrayList;
            }
        }
    }

    public PeopleContactInfo() {
        initRelationItemsValue();
    }

    public static PeopleContactInfo fromJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            PeopleContactInfo peopleContactInfo = new PeopleContactInfo();
            if (jSONObject.has(JSON_TAG_PHONE_NUMBER)) {
                peopleContactInfo.phone = (String) jSONObject.getJSONArray(JSON_TAG_PHONE_NUMBER).get(0);
            }
            if (jSONObject.has(JSON_TAG_RELATIONSHIP)) {
                peopleContactInfo.relationWithMe = jSONObject.getJSONObject(JSON_TAG_RELATIONSHIP).optString(JSON_TAG_RELATION);
                if (isUserDefineRelation(getRelationType(peopleContactInfo.relationWithMe))) {
                    peopleContactInfo.relationWithMeText = jSONObject.getJSONObject(JSON_TAG_RELATIONSHIP).optString(JSON_TAG_RELATIONTEXT);
                } else {
                    peopleContactInfo.relationWithMeText = peopleContactInfo.relationWithMe;
                }
            }
            return peopleContactInfo;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getDefaultNameForMyself(Context context) {
        return context.getResources().getString(R.string.mark_myself_default_name);
    }

    public static Relation getRelation(int i) {
        return Relation.fromRelationType(i);
    }

    public static String getRelationName(Relation relation) {
        initRelationItemsValue();
        String relationValue = getRelationValue(relation);
        String[] stringArray = GalleryApp.sGetAndroidContext().getResources().getStringArray(R.array.relation_with_me);
        for (int i = 0; i < stringArray.length; i++) {
            if (sRelationItemsValue[i].equals(relationValue)) {
                return stringArray[i];
            }
        }
        return null;
    }

    public static String getRelationOrderSql() {
        return "CASE relationType WHEN " + Relation.myself.getRelationType() + " THEN 0" + " WHEN " + Relation.child.getRelationType() + " THEN 1" + " WHEN " + Relation.family.getRelationType() + " THEN 2" + " WHEN " + Relation.collegue.getRelationType() + " THEN 3" + " WHEN " + Relation.classmate.getRelationType() + " THEN 4" + " WHEN " + Relation.friend.getRelationType() + " THEN 5" + " WHEN " + Relation.userDefine.getRelationType() + " THEN 6" + " WHEN " + Relation.unknown.getRelationType() + " THEN 7" + " ELSE 8 END ";
    }

    public static String getRelationShow(int i) {
        if (i < 0) {
            i = Relation.unknown.getRelationType();
        }
        Relation relation = getRelation(i);
        String[] stringArray = GalleryApp.sGetAndroidContext().getResources().getStringArray(R.array.relation_with_me);
        initRelationItemsValue();
        for (int i2 = 0; i2 < sRelationItemsValue.length; i2++) {
            if (relation.name().equalsIgnoreCase(sRelationItemsValue[i2])) {
                return stringArray[i2];
            }
        }
        return "";
    }

    public static int getRelationType(String str) {
        if (TextUtils.isEmpty(str)) {
            return Relation.unknown.getRelationType();
        }
        for (Relation relation : Relation.values()) {
            if (relation == Relation.valueOf(str)) {
                return relation.getRelationType();
            }
        }
        return Relation.unknown.getRelationType();
    }

    public static int getRelationTypesCount() {
        return Relation.values().length;
    }

    public static String getRelationValue(Relation relation) {
        if (relation == null) {
            return null;
        }
        initRelationItemsValue();
        for (String str : sRelationItemsValue) {
            if (relation == Relation.valueOf(str)) {
                return str;
            }
        }
        return null;
    }

    public static ArrayList<String> getSystemRelationNameItems() {
        initRelationItemsValue();
        String[] stringArray = GalleryApp.sGetAndroidContext().getResources().getStringArray(R.array.relation_with_me);
        String relation = Relation.unknown.toString();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < stringArray.length; i++) {
            if (!sRelationItemsValue[i].equals(relation)) {
                arrayList.add(stringArray[i]);
            }
        }
        return arrayList;
    }

    public static ArrayList<String> getSystemRelationValueItems() {
        initRelationItemsValue();
        ArrayList<String> arrayList = new ArrayList<>();
        String relation = Relation.unknown.toString();
        int i = 0;
        while (true) {
            String[] strArr = sRelationItemsValue;
            if (i >= strArr.length) {
                return arrayList;
            }
            if (!strArr[i].equals(relation)) {
                arrayList.add(sRelationItemsValue[i]);
            }
            i++;
        }
    }

    public static String getUserDefineRelation() {
        return Relation.userDefine.toString();
    }

    public static int getUserDefineRelationIndex() {
        return Relation.userDefine.getRelationType();
    }

    public static String getUserDefineRelationOrderSql() {
        return "(CASE WHEN relationType = 7 THEN relationText ELSE NULL END) COLLATE LOCALIZED ";
    }

    private static void initRelationItemsValue() {
        if (sRelationItemsValue == null) {
            sRelationItemsValue = GalleryApp.sGetAndroidContext().getResources().getStringArray(R.array.relation_with_me_value);
        }
    }

    public static boolean isBabyRelation(int i) {
        return i == Relation.child.getRelationType();
    }

    public static boolean isUnKnownRelation(int i) {
        return i == Relation.unknown.getRelationType();
    }

    public static boolean isUserDefineRelation(int i) {
        return i == Relation.userDefine.getRelationType();
    }

    public String formatContactJson() {
        boolean z = !TextUtils.isEmpty(this.phone);
        boolean z2 = !TextUtils.isEmpty(this.relationWithMe);
        if (!z && !z2) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        if (z) {
            try {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(0, this.phone);
                jSONObject.put(JSON_TAG_PHONE_NUMBER, jSONArray);
            } catch (JSONException unused) {
                return null;
            }
        }
        if (z2) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(JSON_TAG_RELATION, this.relationWithMe);
            jSONObject2.put(JSON_TAG_RELATIONTEXT, this.relationWithMeText);
            jSONObject.put(JSON_TAG_RELATIONSHIP, jSONObject2);
        }
        return jSONObject.toString();
    }

    public int getRelationType() {
        if (TextUtils.isEmpty(this.relationWithMe)) {
            return Relation.unknown.getRelationType();
        }
        for (Relation relation : Relation.values()) {
            if (relation == Relation.valueOf(this.relationWithMe)) {
                return relation.getRelationType();
            }
        }
        return Relation.unknown.getRelationType();
    }
}
