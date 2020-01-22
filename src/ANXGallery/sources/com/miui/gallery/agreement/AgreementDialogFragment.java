package com.miui.gallery.agreement;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.agreement.core.Agreement;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.permission.R;
import java.util.ArrayList;
import java.util.List;

public class AgreementDialogFragment extends DialogFragment {
    private OnAgreementInvokedListener mListener;

    private static class AgreementDialog extends BaseAgreementDialog {
        private AgreementAdapter mAdapter = new AgreementAdapter();

        private class AgreementAdapter extends RecyclerView.Adapter<AgreementViewHolder> implements AgreementViewHolder.OnCheckChangedListener {
            private List<AgreementWrapper> mAgreements;

            private AgreementAdapter() {
                this.mAgreements = new ArrayList();
            }

            public int getItemCount() {
                return this.mAgreements.size();
            }

            public void onBindViewHolder(AgreementViewHolder agreementViewHolder, int i) {
                agreementViewHolder.bindAgreement(this.mAgreements.get(i), this);
            }

            public void onCheckChanged(AgreementWrapper agreementWrapper) {
                boolean z;
                boolean z2 = true;
                if (agreementWrapper.isSelectItem) {
                    z = false;
                    for (AgreementWrapper next : this.mAgreements) {
                        if (next.mChecked != agreementWrapper.mChecked) {
                            boolean unused = next.mChecked = agreementWrapper.mChecked;
                            z = true;
                        }
                    }
                } else {
                    AgreementWrapper agreementWrapper2 = null;
                    boolean z3 = true;
                    for (AgreementWrapper next2 : this.mAgreements) {
                        if (next2.isSelectItem) {
                            agreementWrapper2 = next2;
                        } else if (agreementWrapper.mChecked != next2.mChecked) {
                            z3 = false;
                        }
                    }
                    if (z3) {
                        z = agreementWrapper2.mChecked != agreementWrapper.mChecked;
                        boolean unused2 = agreementWrapper2.mChecked = agreementWrapper.mChecked;
                    } else {
                        z = agreementWrapper2.mChecked;
                        boolean unused3 = agreementWrapper2.mChecked = false;
                    }
                }
                if (z) {
                    notifyDataSetChanged();
                }
                for (AgreementWrapper next3 : this.mAgreements) {
                    if (next3.mRequired && !next3.mChecked) {
                        z2 = false;
                    }
                }
                AgreementDialog.this.setActionButtonEnabled(z2);
            }

            public AgreementViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new AgreementViewHolder(AgreementViewHolder.getView(viewGroup, R.layout.user_agreement_item));
            }

            public void setAgreements(List<Agreement> list) {
                this.mAgreements.clear();
                if (list != null) {
                    for (Agreement agreementWrapper : list) {
                        AgreementWrapper agreementWrapper2 = new AgreementWrapper(agreementWrapper, false);
                        boolean unused = agreementWrapper2.mChecked = true;
                        this.mAgreements.add(agreementWrapper2);
                    }
                    if (!this.mAgreements.isEmpty()) {
                        AgreementWrapper agreementWrapper3 = new AgreementWrapper(new Agreement(AgreementDialog.this.getContext().getResources().getString(R.string.select_all), (String) null, false), true);
                        boolean unused2 = agreementWrapper3.mChecked = true;
                        this.mAgreements.add(0, agreementWrapper3);
                    }
                }
                notifyDataSetChanged();
            }
        }

        public AgreementDialog(Context context) {
            super(context, true);
        }

        /* access modifiers changed from: protected */
        public RecyclerView.Adapter getAdapter() {
            return this.mAdapter;
        }

        /* access modifiers changed from: protected */
        public CharSequence getSummary() {
            return getContext().getResources().getString(R.string.agreement_summary);
        }

        public void setAgreements(List<Agreement> list) {
            this.mAdapter.setAgreements(list);
        }
    }

    private static class AgreementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AgreementWrapper mAgreement;
        CheckBox mCheckBox;
        private OnCheckChangedListener mCheckChangedListener;
        TextView mText;

        private interface OnCheckChangedListener {
            void onCheckChanged(AgreementWrapper agreementWrapper);
        }

        public AgreementViewHolder(View view) {
            super(view);
            this.mText = (TextView) view.findViewById(R.id.title);
            this.mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        }

        public static View getView(ViewGroup viewGroup, int i) {
            return LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        }

        private CharSequence parseAgreementText(AgreementWrapper agreementWrapper) {
            StringBuilder sb = new StringBuilder();
            sb.append(agreementWrapper.mText);
            if (!agreementWrapper.isSelectItem) {
                sb.append(" ");
                Resources resources = this.itemView.getContext().getResources();
                if (agreementWrapper.mRequired) {
                    sb.append(resources.getString(R.string.agreement_required_tip));
                } else {
                    sb.append(resources.getString(R.string.agreement_optional_tip));
                }
            }
            return sb.toString();
        }

        public void bindAgreement(AgreementWrapper agreementWrapper, OnCheckChangedListener onCheckChangedListener) {
            this.mAgreement = agreementWrapper;
            this.mText.setText(parseAgreementText(agreementWrapper));
            Resources resources = this.itemView.getContext().getResources();
            if (!TextUtils.isEmpty(agreementWrapper.mLink)) {
                this.mText.setCompoundDrawablePadding(resources.getDimensionPixelSize(R.dimen.agreement_item_arrow_padding));
                this.mText.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, this.itemView.getContext().getDrawable(miui.R.drawable.arrow_right), (Drawable) null);
            } else {
                this.mText.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            if (agreementWrapper.isSelectItem) {
                this.mText.setTextAppearance(this.itemView.getContext(), R.style.TextAppearance_PreferenceList);
            } else {
                this.mText.setTextAppearance(this.itemView.getContext(), R.style.TextAppearance_Agreement);
            }
            if (TextUtils.isEmpty(agreementWrapper.mLink)) {
                this.mText.setOnClickListener((View.OnClickListener) null);
            } else {
                this.mText.setOnClickListener(this);
            }
            this.mCheckBox.setChecked(agreementWrapper.mChecked);
            this.mCheckBox.setOnClickListener(this);
            this.mCheckChangedListener = onCheckChangedListener;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.title) {
                AgreementsUtils.viewAgreement(view.getContext(), this.mAgreement);
            }
            if (view.getId() == R.id.checkbox) {
                boolean unused = this.mAgreement.mChecked = this.mCheckBox.isChecked();
                OnCheckChangedListener onCheckChangedListener = this.mCheckChangedListener;
                if (onCheckChangedListener != null) {
                    onCheckChangedListener.onCheckChanged(this.mAgreement);
                }
            }
        }
    }

    private static class AgreementWrapper extends Agreement {
        /* access modifiers changed from: private */
        public final boolean isSelectItem;
        /* access modifiers changed from: private */
        public boolean mChecked;

        public AgreementWrapper(Agreement agreement, boolean z) {
            super(agreement.mText, agreement.mLink, agreement.mRequired);
            this.isSelectItem = z;
        }
    }

    public static AgreementDialogFragment newInstance(ArrayList<Agreement> arrayList) {
        AgreementDialogFragment agreementDialogFragment = new AgreementDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("extra_agreements", arrayList);
        agreementDialogFragment.setArguments(bundle);
        return agreementDialogFragment;
    }

    public void invoke(Activity activity, OnAgreementInvokedListener onAgreementInvokedListener) {
        this.mListener = onAgreementInvokedListener;
        show(activity.getFragmentManager(), "AgreementDialogFragment");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AgreementDialog agreementDialog = new AgreementDialog(getActivity());
        agreementDialog.setOnAgreementListener(this.mListener);
        agreementDialog.setAgreements(getArguments().getParcelableArrayList("extra_agreements"));
        return agreementDialog;
    }
}
