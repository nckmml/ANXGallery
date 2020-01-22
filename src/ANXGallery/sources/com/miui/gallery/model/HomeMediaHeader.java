package com.miui.gallery.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.gson.JsonIOException;
import com.miui.gallery.dao.base.Entity;
import com.miui.gallery.dao.base.TableColumn;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.List;

public class HomeMediaHeader extends Entity {
    private static final String[] PROJECTION = {"groupItemCount", "groupItemStartPos", "groupLocations"};
    private String mGroupItemCount;
    private String mGroupItemStartPos;
    private String mGroupLocations;

    public static ArrayList<Integer> getGroupItemCount(HomeMediaHeader homeMediaHeader) {
        String[] split;
        if (homeMediaHeader == null || TextUtils.isEmpty(homeMediaHeader.mGroupItemCount) || (split = homeMediaHeader.mGroupItemCount.split(",", -1)) == null) {
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String parseInt : split) {
            arrayList.add(Integer.valueOf(Integer.parseInt(parseInt)));
        }
        return arrayList;
    }

    public static ArrayList<Integer> getGroupItemStartPos(HomeMediaHeader homeMediaHeader) {
        String[] split;
        if (homeMediaHeader == null || TextUtils.isEmpty(homeMediaHeader.mGroupItemStartPos) || (split = homeMediaHeader.mGroupItemStartPos.split(",", -1)) == null) {
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String parseInt : split) {
            arrayList.add(Integer.valueOf(Integer.parseInt(parseInt)));
        }
        return arrayList;
    }

    public static ArrayList<String> getGroupLocations(HomeMediaHeader homeMediaHeader) {
        String[] split;
        if (homeMediaHeader == null || TextUtils.isEmpty(homeMediaHeader.mGroupLocations) || (split = homeMediaHeader.mGroupLocations.split(",", -1)) == null) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String add : split) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static HomeMediaHeader packageMediaHeader(List<Integer> list, List<Integer> list2, List<String> list3) throws JsonIOException {
        HomeMediaHeader homeMediaHeader = new HomeMediaHeader();
        if (MiscUtil.isValid(list)) {
            homeMediaHeader.mGroupItemCount = TextUtils.join(",", list);
        }
        if (MiscUtil.isValid(list2)) {
            homeMediaHeader.mGroupItemStartPos = TextUtils.join(",", list2);
        }
        if (MiscUtil.isValid(list3)) {
            homeMediaHeader.mGroupLocations = TextUtils.join(",", list3);
        }
        return homeMediaHeader;
    }

    /* access modifiers changed from: protected */
    public List<TableColumn> getTableColumns() {
        ArrayList arrayList = new ArrayList();
        for (String addColumn : PROJECTION) {
            addColumn(arrayList, addColumn, "TEXT");
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onConvertToContents(ContentValues contentValues) {
        contentValues.put(PROJECTION[0], this.mGroupItemCount);
        contentValues.put(PROJECTION[1], this.mGroupItemStartPos);
        contentValues.put(PROJECTION[2], this.mGroupLocations);
    }

    /* access modifiers changed from: protected */
    public void onInitFromCursor(Cursor cursor) {
        this.mGroupItemCount = getString(cursor, PROJECTION[0]);
        this.mGroupItemStartPos = getString(cursor, PROJECTION[1]);
        this.mGroupLocations = getString(cursor, PROJECTION[2]);
    }
}
