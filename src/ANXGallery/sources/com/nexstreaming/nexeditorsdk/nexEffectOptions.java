package com.nexstreaming.nexeditorsdk;

import com.nexstreaming.app.common.util.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class nexEffectOptions implements Serializable {
    private static final long serialVersionUID = 1;
    private List<ColorOpt> mColorOptions = new ArrayList();
    String mEffectID;
    int mEffectType;
    private Set<String> mOptionIds = new HashSet();
    private List<RangeOpt> mRangeOptions = new ArrayList();
    private List<SelectOpt> mSelectOptions = new ArrayList();
    private List<SwitchOpt> mSwitchOptions = new ArrayList();
    private List<TextOpt> mTextOptions = new ArrayList();
    private List<ColorOpt> m_externalView_colors = null;
    private List<RangeOpt> m_externalView_ranges = null;
    private List<SelectOpt> m_externalView_selects = null;
    private List<SwitchOpt> m_externalView_switch = null;
    private List<TextOpt> m_externalView_texts = null;
    /* access modifiers changed from: private */
    public boolean updated;

    public class ColorOpt extends Option {
        protected int argb_color;
        protected int default_argb_color;

        private ColorOpt() {
            super();
        }

        protected ColorOpt(String str, String str2, String str3) {
            super(str, str2);
            this.argb_color = c.a(str3);
            this.default_argb_color = this.argb_color;
        }

        public int getARGBformat() {
            return this.argb_color;
        }

        public void setARGBColor(int i) {
            if (this.argb_color != i) {
                boolean unused = nexEffectOptions.this.updated = true;
            }
            this.argb_color = i;
        }
    }

    public class Option implements Serializable {
        private static final long serialVersionUID = 1;
        private String mId;
        private String mLabel;

        private Option() {
        }

        protected Option(String str, String str2) {
            this.mLabel = str2;
            this.mId = str;
        }

        public String getId() {
            return this.mId;
        }

        public String getLabel() {
            return this.mLabel;
        }
    }

    public class RangeOpt extends Option {
        private static final long serialVersionUID = 1;
        protected int default_value;
        protected int mValue;
        protected int max_value;
        protected int min_value;

        private RangeOpt() {
            super();
        }

        protected RangeOpt(String str, String str2, int i, int i2, int i3) {
            super(str, str2);
            this.default_value = i;
            this.max_value = i3;
            this.min_value = i2;
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public int max() {
            return this.max_value;
        }

        public int min() {
            return this.min_value;
        }

        public void setValue(int i) {
            int i2 = this.min_value;
            if (i >= i2 && i <= (i2 = this.max_value)) {
                i2 = i;
            }
            if (this.mValue != i2) {
                boolean unused = nexEffectOptions.this.updated = true;
            }
            this.mValue = i2;
        }
    }

    public class SelectOpt extends Option {
        private static final long serialVersionUID = 1;
        protected int default_select_index;
        private String[] mItems;
        private String[] mValues;
        protected int select_index;

        private SelectOpt() {
            super();
        }

        protected SelectOpt(String str, String str2, String[] strArr, String[] strArr2, int i) {
            super(str, str2);
            this.mItems = strArr;
            this.mValues = strArr2;
            this.select_index = i;
            this.default_select_index = this.select_index;
        }

        public String[] getItems() {
            return this.mItems;
        }

        public int getSelectIndex() {
            return this.select_index;
        }

        public String getSelectValue() {
            return this.mValues[this.select_index];
        }

        public void setSelectIndex(int i) {
            String[] strArr;
            if (i >= 0 && (strArr = this.mItems) != null && i < strArr.length) {
                this.select_index = i;
            }
        }

        /* access modifiers changed from: protected */
        public int setValue(String str) {
            int i = 0;
            while (true) {
                String[] strArr = this.mValues;
                if (i >= strArr.length) {
                    break;
                } else if (strArr[i].compareTo(str) == 0) {
                    if (this.select_index != i) {
                        boolean unused = nexEffectOptions.this.updated = true;
                    }
                    this.select_index = i;
                } else {
                    i++;
                }
            }
            return 0;
        }
    }

    public class SwitchOpt extends Option {
        protected boolean default_on;
        protected boolean on;

        private SwitchOpt() {
            super();
        }

        protected SwitchOpt(String str, String str2, boolean z) {
            super(str, str2);
            this.default_on = z;
            this.on = z;
        }

        public boolean getValue() {
            return this.on;
        }

        public void setValue(boolean z) {
            if (this.on != z) {
                boolean unused = nexEffectOptions.this.updated = true;
            }
            this.on = z;
        }
    }

    public class TextOpt extends Option {
        private static final long serialVersionUID = 1;
        private boolean isMultiLines;
        private String mTitle;

        private TextOpt() {
            super();
        }

        protected TextOpt(String str, String str2, int i) {
            super(str, str2);
            if (i == 0) {
                this.isMultiLines = false;
            } else {
                this.isMultiLines = true;
            }
        }

        public String getText() {
            return this.mTitle;
        }

        public void setText(String str) {
            this.mTitle = str;
            boolean unused = nexEffectOptions.this.updated = true;
        }
    }

    private nexEffectOptions() {
    }

    protected nexEffectOptions(String str, int i) {
        this.mEffectID = str;
        this.mEffectType = i;
    }

    /* access modifiers changed from: protected */
    public void addColorOpt(String str, String str2, String str3) {
        this.mOptionIds.add(str);
        this.mColorOptions.add(new ColorOpt(str, str2, str3));
    }

    /* access modifiers changed from: protected */
    public void addRangeOpt(String str, String str2, int i, int i2, int i3) {
        this.mOptionIds.add(str);
        this.mRangeOptions.add(new RangeOpt(str, str2, i, i2, i3));
    }

    /* access modifiers changed from: protected */
    public void addSelectOpt(String str, String str2, String[] strArr, String[] strArr2, int i) {
        this.mOptionIds.add(str);
        this.mSelectOptions.add(new SelectOpt(str, str2, strArr, strArr2, i));
    }

    /* access modifiers changed from: protected */
    public void addSwitchOpt(String str, String str2, boolean z) {
        this.mOptionIds.add(str);
        this.mSwitchOptions.add(new SwitchOpt(str, str2, z));
    }

    /* access modifiers changed from: protected */
    public void addTextOpt(String str, String str2, int i) {
        this.mOptionIds.add(str);
        this.mTextOptions.add(new TextOpt(str, str2, i));
    }

    /* access modifiers changed from: package-private */
    public void clearUpadted() {
        this.updated = false;
    }

    public List<ColorOpt> getColorOptions() {
        if (this.m_externalView_colors == null) {
            this.m_externalView_colors = Collections.unmodifiableList(this.mColorOptions);
        }
        return this.m_externalView_colors;
    }

    public String getEffectID() {
        return this.mEffectID;
    }

    public Option getOptionById(String str) {
        for (TextOpt next : this.mTextOptions) {
            if (next.getId().compareTo(str) == 0) {
                return next;
            }
        }
        for (ColorOpt next2 : this.mColorOptions) {
            if (next2.getId().compareTo(str) == 0) {
                return next2;
            }
        }
        for (SelectOpt next3 : this.mSelectOptions) {
            if (next3.getId().compareTo(str) == 0) {
                return next3;
            }
        }
        for (RangeOpt next4 : this.mRangeOptions) {
            if (next4.getId().compareTo(str) == 0) {
                return next4;
            }
        }
        for (SwitchOpt next5 : this.mSwitchOptions) {
            if (next5.getId().compareTo(str) == 0) {
                return next5;
            }
        }
        return null;
    }

    public String[] getOptionIds() {
        Set<String> set = this.mOptionIds;
        return (String[]) set.toArray(new String[set.size()]);
    }

    public List<RangeOpt> getRangeOptions() {
        if (this.m_externalView_ranges == null) {
            this.m_externalView_ranges = Collections.unmodifiableList(this.mRangeOptions);
        }
        return this.m_externalView_ranges;
    }

    public List<SelectOpt> getSelectOptions() {
        if (this.m_externalView_selects == null) {
            this.m_externalView_selects = Collections.unmodifiableList(this.mSelectOptions);
        }
        return this.m_externalView_selects;
    }

    public List<SwitchOpt> getSwitchOptions() {
        if (this.m_externalView_switch == null) {
            this.m_externalView_switch = Collections.unmodifiableList(this.mSwitchOptions);
        }
        return this.m_externalView_switch;
    }

    public int getTextFieldCount() {
        List<TextOpt> list = this.mTextOptions;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<TextOpt> getTextOptions() {
        if (this.m_externalView_texts == null) {
            this.m_externalView_texts = Collections.unmodifiableList(this.mTextOptions);
        }
        return this.m_externalView_texts;
    }

    /* access modifiers changed from: package-private */
    public boolean isUpdated() {
        return this.updated;
    }

    public void setDefaultValue() {
        List<ColorOpt> list = this.mColorOptions;
        if (list != null) {
            for (ColorOpt next : list) {
                next.argb_color = next.default_argb_color;
            }
        }
        List<SelectOpt> list2 = this.mSelectOptions;
        if (list2 != null) {
            for (SelectOpt next2 : list2) {
                next2.setSelectIndex(next2.default_select_index);
            }
        }
        List<RangeOpt> list3 = this.mRangeOptions;
        if (list3 != null) {
            for (RangeOpt next3 : list3) {
                next3.setValue(next3.default_value);
            }
        }
        List<SwitchOpt> list4 = this.mSwitchOptions;
        if (list4 != null) {
            for (SwitchOpt next4 : list4) {
                next4.setValue(next4.default_on);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setEffectType(int i) {
        this.mEffectType = i;
    }
}
