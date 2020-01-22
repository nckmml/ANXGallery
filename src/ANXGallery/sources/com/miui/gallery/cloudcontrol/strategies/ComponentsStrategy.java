package com.miui.gallery.cloudcontrol.strategies;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.miui.gallery.cloudcontrol.Merger;
import com.miui.gallery.util.BuildUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComponentsStrategy extends BaseStrategy {
    public static final Merger<ComponentsStrategy> CLOUD_FIRST_MERGER = new Merger<ComponentsStrategy>() {
        public ComponentsStrategy merge(ComponentsStrategy componentsStrategy, ComponentsStrategy componentsStrategy2) {
            if (componentsStrategy2.mPriorityInternational == null) {
                if (componentsStrategy.mPriorityInternational == null || componentsStrategy.mPriorityInternational.isEmpty()) {
                    ArrayList unused = componentsStrategy2.mPriorityInternational = new ArrayList();
                } else {
                    ArrayList unused2 = componentsStrategy2.mPriorityInternational = (ArrayList) componentsStrategy.mPriorityInternational.clone();
                }
            }
            if (componentsStrategy2.mPriority == null) {
                if (componentsStrategy.mPriority == null || componentsStrategy.mPriority.isEmpty()) {
                    ArrayList unused3 = componentsStrategy2.mPriority = new ArrayList();
                } else {
                    ArrayList unused4 = componentsStrategy2.mPriority = (ArrayList) componentsStrategy.mPriority.clone();
                }
            }
            return componentsStrategy2;
        }
    };
    public static final Comparator<Priority> MATCH_ORDER = new Comparator<Priority>() {
        public int compare(Priority priority, Priority priority2) {
            if (priority.mMatchOrder > priority2.mMatchOrder) {
                return 1;
            }
            return priority.mMatchOrder == priority2.mMatchOrder ? 0 : -1;
        }
    };
    /* access modifiers changed from: private */
    @SerializedName("component-priority")
    public ArrayList<Priority> mPriority;
    /* access modifiers changed from: private */
    @SerializedName("component-priority-international")
    public ArrayList<Priority> mPriorityInternational;
    @SerializedName("share-components")
    private List<Component> mShareComponents;
    @SerializedName("share-components-international")
    private List<Component> mShareComponentsInternational;

    public static class Component {
        @SerializedName("class-name")
        private String mClassName;
        @SerializedName("order")
        private int mOrder;
        @SerializedName("package-name")
        private String mPackageName;

        public Component(String str, String str2, int i) {
            this.mPackageName = str;
            this.mClassName = str2;
            this.mOrder = i;
        }

        public String getClassName() {
            return this.mClassName;
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public String toString() {
            return "Component{mPackageName='" + this.mPackageName + '\'' + ", mClassName='" + this.mClassName + '\'' + ", mOrder=" + this.mOrder + '}';
        }
    }

    public static class Priority {
        @SerializedName("class-name")
        public final String className;
        int mMatchOrder;
        @SerializedName("package-name")
        public final String packageName;
        @SerializedName("package-prefix")
        public final String packagePrefix;
        @SerializedName("system")
        public final Boolean system;
        @SerializedName("value")
        public final int value;

        /* access modifiers changed from: package-private */
        public void initMathOrder() {
            int i = this.system != null ? 3 : Reader.READ_DONE;
            if (this.packagePrefix != null) {
                i = 2;
            }
            if (this.packageName != null) {
                i = 1;
            }
            if (this.className != null) {
                i = 0;
            }
            this.mMatchOrder = i;
        }

        public boolean match(String str, String str2, boolean z) {
            String str3 = this.packageName;
            if (str3 != null) {
                return str3.equals(str) && (TextUtils.isEmpty(this.className) || this.className.equals(str2));
            }
            String str4 = this.packagePrefix;
            if (str4 != null) {
                return str.startsWith(str4);
            }
            Boolean bool = this.system;
            return bool != null && bool.booleanValue() == z;
        }

        public String toString() {
            return "Priority{packagePrefix='" + this.packagePrefix + '\'' + ", packageName='" + this.packageName + '\'' + ", className='" + this.className + '\'' + ", value=" + this.value + ", system=" + this.system + ", mMatchOrder=" + this.mMatchOrder + '}';
        }
    }

    public static ComponentsStrategy createDefault() {
        ComponentsStrategy componentsStrategy = new ComponentsStrategy();
        if (BuildUtil.isInternational()) {
            componentsStrategy.mShareComponentsInternational = new ArrayList();
            componentsStrategy.mShareComponentsInternational.add(new Component("com.twitter.android", "com.twitter.android.composer.ComposerActivity", 0));
            componentsStrategy.mShareComponentsInternational.add(new Component("com.instagram.android", "com.instagram.android.activity.ShareHandlerActivity", 1));
            componentsStrategy.mShareComponentsInternational.add(new Component("com.whatsapp", "com.whatsapp.ContactPicker", 2));
            componentsStrategy.mShareComponentsInternational.add(new Component("com.facebook.katana", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias", 3));
            componentsStrategy.mShareComponentsInternational.add(new Component("com.facebook.orca", "com.facebook.messenger.intents.ShareIntentHandler", 4));
            componentsStrategy.mShareComponentsInternational.add(new Component("org.telegram.messenger", "org.telegram.ui.LaunchActivity", 5));
            componentsStrategy.mShareComponentsInternational.add(new Component("com.bsb.hike", "com.bsb.hike.ui.ComposeChatActivity", 6));
            componentsStrategy.mShareComponentsInternational.add(new Component("com.twitter.android", "com.twitter.android.DMActivity", 7));
            componentsStrategy.mShareComponentsInternational.add(new Component("com.xiaomi.midrop", "com.xiaomi.midrop.sender.ui.TransmissionActivity", 8));
        } else {
            componentsStrategy.mShareComponents = new ArrayList();
            componentsStrategy.mShareComponents.add(new Component("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI", 0));
            componentsStrategy.mShareComponents.add(new Component("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI", 1));
            componentsStrategy.mShareComponents.add(new Component("com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity", 2));
            componentsStrategy.mShareComponents.add(new Component("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity", 3));
        }
        componentsStrategy.mPriorityInternational = new ArrayList<>();
        componentsStrategy.mPriority = new ArrayList<>();
        return componentsStrategy;
    }

    public void doAdditionalProcessing() {
        ArrayList<Priority> arrayList = BuildUtil.isInternational() ? this.mPriorityInternational : this.mPriority;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (Priority initMathOrder : arrayList) {
                initMathOrder.initMathOrder();
            }
            Collections.sort(arrayList, MATCH_ORDER);
        }
    }

    public List<Priority> getComponentPriority() {
        ArrayList<Priority> arrayList = BuildUtil.isInternational() ? this.mPriorityInternational : this.mPriority;
        return arrayList != null ? arrayList : new ArrayList();
    }

    public List<Component> getShareComponents() {
        List<Component> list = BuildUtil.isInternational() ? this.mShareComponentsInternational : this.mShareComponents;
        return list != null ? list : new ArrayList();
    }

    public String toString() {
        return "ComponentsStrategy{mShareComponents=" + this.mShareComponents + ", mShareComponentsInternational=" + this.mShareComponentsInternational + ", mPriority=" + this.mPriority + ", mPriorityInternational=" + this.mPriorityInternational + '}';
    }
}
