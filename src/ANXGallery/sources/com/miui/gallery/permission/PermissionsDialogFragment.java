package com.miui.gallery.permission;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.agreement.BaseAgreementDialog;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.permission.core.OnPermissionIntroduced;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermissionsDialogFragment extends DialogFragment implements OnAgreementInvokedListener {
    private OnPermissionIntroduced mOnIntroducedListener;

    private static abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View view) {
            super(view);
        }

        public static View getView(ViewGroup viewGroup, int i) {
            return LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        }

        public abstract void bindPermission(PermissionWrapper permissionWrapper);
    }

    private static class CategoryViewHolder extends BaseViewHolder {
        private TextView mCategory;

        public CategoryViewHolder(View view) {
            super(view);
            this.mCategory = (TextView) view.findViewById(R.id.category);
        }

        public void bindPermission(PermissionWrapper permissionWrapper) {
            this.mCategory.setText(this.itemView.getContext().getResources().getString(permissionWrapper.mRequired ? R.string.permission_require_category : R.string.permission_optional_category));
        }
    }

    private static class PermissionDialog extends BaseAgreementDialog {
        private PermissionAdapter mAdapter = new PermissionAdapter();

        private class DividerDecoration extends BaseAgreementDialog.BaseDividerDecoration {
            private Drawable mSectionDividerDrawable;
            private int mSectionDividerHeight;

            private DividerDecoration() {
            }

            private void init(Context context) {
                if (this.mSectionDividerDrawable == null) {
                    this.mSectionDividerDrawable = context.getResources().getDrawable(R.drawable.section_divider_bg);
                    this.mSectionDividerHeight = context.getResources().getDimensionPixelSize(R.dimen.agreement_section_divider_height);
                }
            }

            /* access modifiers changed from: protected */
            public void drawSection(Canvas canvas, View view, int i, int i2) {
                float top = ((float) (view.getTop() - ((RecyclerView.LayoutParams) view.getLayoutParams()).topMargin)) + view.getTranslationY();
                int i3 = this.mSectionDividerHeight;
                int i4 = (int) (top - ((float) i3));
                this.mSectionDividerDrawable.setBounds(i, i4, i2, i3 + i4);
                this.mSectionDividerDrawable.draw(canvas);
            }

            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                if (childAdapterPosition > 0) {
                    init(view.getContext());
                    if (((PermissionWrapper) ((PermissionAdapter) recyclerView.getAdapter()).mPermissions.get(childAdapterPosition)).mIsCategory) {
                        rect.set(0, this.mSectionDividerHeight, 0, 0);
                        return;
                    }
                }
                super.getItemOffsets(rect, view, recyclerView, state);
            }

            public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
                int childCount = recyclerView.getChildCount();
                int paddingStart = recyclerView.getPaddingStart();
                int right = recyclerView.getRight() - recyclerView.getPaddingEnd();
                PermissionAdapter permissionAdapter = (PermissionAdapter) recyclerView.getAdapter();
                for (int i = 0; i < childCount; i++) {
                    View childAt = recyclerView.getChildAt(i);
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
                    if (childAdapterPosition != -1) {
                        if (childAdapterPosition <= 0 || !((PermissionWrapper) permissionAdapter.mPermissions.get(childAdapterPosition)).mIsCategory) {
                            drawTop(canvas, childAt, childAdapterPosition == 0 ? paddingStart : this.mPaddingStart + paddingStart, right);
                            if (childAdapterPosition == recyclerView.getAdapter().getItemCount() - 1) {
                                drawBottom(canvas, childAt, paddingStart, right);
                            }
                        } else {
                            drawSection(canvas, childAt, paddingStart, right);
                        }
                    }
                }
            }
        }

        private class PermissionAdapter extends RecyclerView.Adapter<BaseViewHolder> {
            /* access modifiers changed from: private */
            public ArrayList<PermissionWrapper> mPermissions;

            private PermissionAdapter() {
                this.mPermissions = new ArrayList<>();
            }

            public int getItemCount() {
                return this.mPermissions.size();
            }

            public int getItemViewType(int i) {
                return this.mPermissions.get(i).mIsCategory ? 1 : 0;
            }

            public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
                baseViewHolder.bindPermission(this.mPermissions.get(i));
            }

            public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return i != 1 ? new PermissionViewHolder(BaseViewHolder.getView(viewGroup, R.layout.user_permission_item)) : new CategoryViewHolder(BaseViewHolder.getView(viewGroup, R.layout.user_permission_category));
            }

            public void setPermissions(List<Permission> list) {
                this.mPermissions.clear();
                if (list != null) {
                    LinkedList linkedList = new LinkedList();
                    for (Permission next : list) {
                        if (next.mRequired) {
                            this.mPermissions.add(new PermissionWrapper(next, false));
                        } else {
                            linkedList.add(new PermissionWrapper(next, false));
                        }
                    }
                    if (!this.mPermissions.isEmpty()) {
                        this.mPermissions.add(0, new PermissionWrapper(new Permission("", "", "", true), true));
                    }
                    if (!linkedList.isEmpty()) {
                        this.mPermissions.add(new PermissionWrapper(new Permission("", "", "", false), true));
                        this.mPermissions.addAll(linkedList);
                    }
                }
                notifyDataSetChanged();
            }
        }

        public PermissionDialog(Context context) {
            super(context, false);
        }

        /* access modifiers changed from: protected */
        public RecyclerView.Adapter getAdapter() {
            return this.mAdapter;
        }

        /* access modifiers changed from: protected */
        public CharSequence getButtonText() {
            return getContext().getText(R.string.have_known);
        }

        /* access modifiers changed from: protected */
        public RecyclerView.ItemDecoration getDividerDecoration() {
            return new DividerDecoration();
        }

        /* access modifiers changed from: protected */
        public CharSequence getSummary() {
            return getContext().getResources().getString(R.string.permission_use_desc);
        }

        public void setPermissions(List<Permission> list) {
            this.mAdapter.setPermissions(list);
        }
    }

    private static class PermissionViewHolder extends BaseViewHolder {
        private TextView mDesc;
        private TextView mName;

        public PermissionViewHolder(View view) {
            super(view);
            this.mName = (TextView) view.findViewById(R.id.title);
            this.mDesc = (TextView) view.findViewById(R.id.desc);
        }

        public void bindPermission(PermissionWrapper permissionWrapper) {
            this.mName.setText(permissionWrapper.mName);
            this.mDesc.setText(permissionWrapper.mDesc);
        }
    }

    private static class PermissionWrapper extends Permission {
        /* access modifiers changed from: private */
        public final boolean mIsCategory;

        public PermissionWrapper(Permission permission, boolean z) {
            super(permission.mPermissionGroup, permission.mName, permission.mDesc, permission.mRequired);
            this.mIsCategory = z;
        }
    }

    public static PermissionsDialogFragment newInstance(ArrayList<Permission> arrayList, OnPermissionIntroduced onPermissionIntroduced) {
        PermissionsDialogFragment permissionsDialogFragment = new PermissionsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("extra_permissions", arrayList);
        permissionsDialogFragment.setArguments(bundle);
        permissionsDialogFragment.setOnIntroducedListener(onPermissionIntroduced);
        return permissionsDialogFragment;
    }

    public void onAgreementInvoked(boolean z) {
        OnPermissionIntroduced onPermissionIntroduced = this.mOnIntroducedListener;
        if (onPermissionIntroduced != null) {
            onPermissionIntroduced.onPermissionIntroduced(z);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        PermissionDialog permissionDialog = new PermissionDialog(getActivity());
        permissionDialog.setOnAgreementListener(this);
        permissionDialog.setPermissions(getArguments().getParcelableArrayList("extra_permissions"));
        return permissionDialog;
    }

    /* access modifiers changed from: package-private */
    public void setOnIntroducedListener(OnPermissionIntroduced onPermissionIntroduced) {
        this.mOnIntroducedListener = onPermissionIntroduced;
    }
}
