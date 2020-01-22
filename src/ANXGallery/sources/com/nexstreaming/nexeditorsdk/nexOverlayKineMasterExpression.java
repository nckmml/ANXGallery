package com.nexstreaming.nexeditorsdk;

import com.nexstreaming.nexeditorsdk.exception.InvalidRangeException;
import java.util.Arrays;
import java.util.List;

@Deprecated
public final class nexOverlayKineMasterExpression implements Cloneable {
    public static final nexOverlayKineMasterExpression DRIFTING = new nexOverlayKineMasterExpression("Drifting", 8, "FadeIn", "FadeOut", "DriftingOverall");
    public static final nexOverlayKineMasterExpression DROP = new nexOverlayKineMasterExpression("Drop", 5, "DropIn", "FadeOut", "none");
    public static final nexOverlayKineMasterExpression FADE = new nexOverlayKineMasterExpression("Fade", 1, "FadeIn", "FadeOut", "none");
    public static final nexOverlayKineMasterExpression FLOATING = new nexOverlayKineMasterExpression("Floating", 7, "FadeIn", "FadeOut", "FloatingOverall");
    public static final nexOverlayKineMasterExpression NONE = new nexOverlayKineMasterExpression("None", 0, "none", "none", "none");
    public static final nexOverlayKineMasterExpression POP = new nexOverlayKineMasterExpression("Pop", 2, "PopIn", "ScaleUpOut", "none");
    public static final nexOverlayKineMasterExpression SCALE = new nexOverlayKineMasterExpression("Scale", 6, "ScaleUpIn", "ScaleDownOut", "none");
    public static final nexOverlayKineMasterExpression SLIDE = new nexOverlayKineMasterExpression("Slide", 3, "SlideRightIn", "SlideRightOut", "none");
    public static final nexOverlayKineMasterExpression SPIN = new nexOverlayKineMasterExpression("Spin", 4, "SpinCCWIn", "SpinCWOut", "none");
    public static final nexOverlayKineMasterExpression SQUISHING = new nexOverlayKineMasterExpression("Squishing", 9, "FadeIn", "FadeOut", "SquishingOverall");
    private static final nexOverlayKineMasterExpression[] values = {NONE, FADE, POP, SLIDE, SPIN, DROP, SCALE, FLOATING, DRIFTING, SQUISHING};
    private String KMinID;
    private String KMoutID;
    private String KMoverallID;
    private int KineMasterID;
    private String name;

    private nexOverlayKineMasterExpression(String str, int i, String str2, String str3, String str4) {
        this.name = str;
        this.KineMasterID = i;
        this.KMinID = str2;
        this.KMoutID = str3;
        this.KMoverallID = str4;
    }

    @Deprecated
    public static nexOverlayKineMasterExpression getExpression(int i) {
        nexOverlayKineMasterExpression[] nexoverlaykinemasterexpressionArr = values;
        if (i >= nexoverlaykinemasterexpressionArr.length) {
            throw new InvalidRangeException(i, nexoverlaykinemasterexpressionArr.length);
        } else if (i >= 0) {
            return nexoverlaykinemasterexpressionArr[i];
        } else {
            throw new InvalidRangeException(i);
        }
    }

    @Deprecated
    public static String[] getNames() {
        String[] strArr = new String[values.length];
        int i = 0;
        while (true) {
            nexOverlayKineMasterExpression[] nexoverlaykinemasterexpressionArr = values;
            if (i >= nexoverlaykinemasterexpressionArr.length) {
                return strArr;
            }
            strArr[i] = nexoverlaykinemasterexpressionArr[i].name;
            i++;
        }
    }

    @Deprecated
    public static List<nexOverlayKineMasterExpression> getPresetList() {
        return Arrays.asList(values);
    }

    @Deprecated
    public static nexOverlayKineMasterExpression[] values() {
        return values;
    }

    @Deprecated
    public int getID() {
        return this.KineMasterID;
    }

    /* access modifiers changed from: package-private */
    public String getNames(int i) {
        if (i == 0) {
            return this.KMinID;
        }
        if (i == 1) {
            return this.KMoutID;
        }
        if (i == 2) {
            return this.KMoverallID;
        }
        return null;
    }
}
