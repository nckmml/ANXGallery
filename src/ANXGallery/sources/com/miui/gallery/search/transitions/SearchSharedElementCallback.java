package com.miui.gallery.search.transitions;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.SharedElementCallback;
import com.miui.gallery.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchSharedElementCallback extends SharedElementCallback {
    private SparseArray<Map<String, Object>> mOriginalSharedElementStates;

    private Map<String, Object> onCaptureSharedElementStates(View view) {
        HashMap hashMap = new HashMap();
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null) {
                hashMap.put("searchSharedElement:snapshot:image_drawable", drawable);
            }
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            hashMap.put("searchSharedElement:snapshot:textview_text", StringUtils.nullToEmpty(textView.getText()));
            hashMap.put("searchSharedElement:snapshot:textview_hint", StringUtils.nullToEmpty(textView.getHint()));
            hashMap.put("searchSharedElement:snapshot:textview_text_color", textView.getTextColors());
            hashMap.put("searchSharedElement:snapshot:textview_hint_color", textView.getHintTextColors());
        }
        Drawable background = view.getBackground();
        if (background != null) {
            hashMap.put("searchSharedElement:snapshot:view_background", background);
        }
        return hashMap;
    }

    public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
        Bundle bundle = new Bundle();
        if (view != null) {
            bundle.putString("searchSharedElement:snapshot:view_class", view.getClass().getName());
            boolean z = view instanceof TextView;
            if (z) {
                TextView textView = (TextView) view;
                bundle.putString("searchSharedElement:snapshot:textview_text", StringUtils.nullToEmpty(textView.getText()));
                bundle.putString("searchSharedElement:snapshot:textview_hint", StringUtils.nullToEmpty(textView.getHint()));
                bundle.putInt("searchSharedElement:snapshot:textview_text_color", textView.getCurrentTextColor());
                bundle.putInt("searchSharedElement:snapshot:textview_hint_color", textView.getCurrentHintTextColor());
                textView.setText((CharSequence) null);
                textView.setHint((CharSequence) null);
            }
            Parcelable onCaptureSharedElementSnapshot = super.onCaptureSharedElementSnapshot(view, matrix, rectF);
            if (onCaptureSharedElementSnapshot != null) {
                bundle.putParcelable("searchSharedElement:snapshot:parent", onCaptureSharedElementSnapshot);
            }
            if (z) {
                TextView textView2 = (TextView) view;
                textView2.setText(bundle.getString("searchSharedElement:snapshot:textview_text"));
                textView2.setHint(bundle.getString("searchSharedElement:snapshot:textview_hint"));
            }
        }
        return bundle;
    }

    /*  JADX ERROR: IF instruction can be used only in fallback mode
        jadx.core.utils.exceptions.CodegenException: IF instruction can be used only in fallback mode
        	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:579)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:485)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.util.ArrayList.forEach(ArrayList.java:1257)
        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
        */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00a9, code lost:
        if (r2 == null) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00ab, code lost:
        r0.setBackground(r2.getBackground());
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0047, code lost:
        if (r2 == null) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0049, code lost:
        r0.setImageDrawable(r2.getBackground());
        r0 = r0;
     */
    public android.view.View onCreateSnapshotView(android.content.Context r7, android.os.Parcelable r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof android.os.Bundle
            java.lang.String r1 = "SearchSharedElementCallback"
            if (r0 == 0) goto L_0x00b3
            r0 = r8
            android.os.Bundle r0 = (android.os.Bundle) r0
            java.lang.String r2 = "searchSharedElement:snapshot:parent"
            android.os.Parcelable r2 = r0.getParcelable(r2)
            android.view.View r2 = super.onCreateSnapshotView(r7, r2)
            java.lang.Class<android.widget.ImageView> r3 = android.widget.ImageView.class
            java.lang.String r3 = r3.getName()
            java.lang.String r4 = "searchSharedElement:snapshot:view_class"
            java.lang.String r5 = r0.getString(r4)
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0052
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r7)
            boolean r3 = r2 instanceof android.widget.ImageView
            if (r3 == 0) goto L_0x0047
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            android.graphics.drawable.Drawable r3 = r2.getDrawable()
            r0.setImageDrawable(r3)
            android.widget.ImageView$ScaleType r3 = r2.getScaleType()
            r0.setScaleType(r3)
            android.graphics.Matrix r2 = r2.getImageMatrix()
            r0.setImageMatrix(r2)
            goto L_0x00bd
        L_0x0047:
            if (r2 == 0) goto L_0x00bd
            android.graphics.drawable.Drawable r2 = r2.getBackground()
            r0.setImageDrawable(r2)
            goto L_0x00bd
        L_0x0052:
            java.lang.Class<android.widget.TextView> r3 = android.widget.TextView.class
            java.lang.String r3 = r3.getName()
            java.lang.String r4 = r0.getString(r4)
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x008d
            android.widget.TextView r3 = new android.widget.TextView
            r3.<init>(r7)
            java.lang.String r4 = "searchSharedElement:snapshot:textview_text"
            java.lang.String r4 = r0.getString(r4)
            r3.setText(r4)
            java.lang.String r4 = "searchSharedElement:snapshot:textview_hint"
            java.lang.String r4 = r0.getString(r4)
            r3.setHint(r4)
            java.lang.String r4 = "searchSharedElement:snapshot:textview_text_color"
            int r4 = r0.getInt(r4)
            r3.setTextColor(r4)
            java.lang.String r4 = "searchSharedElement:snapshot:textview_hint_color"
            int r0 = r0.getInt(r4)
            r3.setHintTextColor(r0)
            r0 = r3
            goto L_0x009b
        L_0x008d:
            if (r2 == 0) goto L_0x0091
            r0 = r2
            goto L_0x009b
        L_0x0091:
            java.lang.String r3 = "What? Invalid params, %s"
            com.miui.gallery.search.utils.SearchLog.e(r1, r3, r0)
            android.view.View r0 = new android.view.View
            r0.<init>(r7)
        L_0x009b:
            boolean r3 = r2 instanceof android.widget.ImageView
            if (r3 == 0) goto L_0x00a9
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            android.graphics.drawable.Drawable r2 = r2.getDrawable()
            r0.setBackground(r2)
            goto L_0x00bd
        L_0x00a9:
            if (r2 == 0) goto L_0x00bd
            android.graphics.drawable.Drawable r2 = r2.getBackground()
            r0.setBackground(r2)
            goto L_0x00bd
        L_0x00b3:
            boolean r0 = r8 instanceof android.graphics.Bitmap
            if (r0 == 0) goto L_0x00bc
            android.view.View r0 = super.onCreateSnapshotView(r7, r8)
            goto L_0x00bd
        L_0x00bc:
            r0 = 0
        L_0x00bd:
            if (r0 != 0) goto L_0x00c9
            java.lang.String r0 = "What? Invalid snapshot, %s"
            com.miui.gallery.search.utils.SearchLog.e(r1, r0, r8)
            android.view.View r0 = new android.view.View
            r0.<init>(r7)
        L_0x00c9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.gallery.search.transitions.SearchSharedElementCallback.onCreateSnapshotView(android.content.Context, android.os.Parcelable):android.view.View");
    }

    public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
        Map map;
        super.onSharedElementEnd(list, list2, list3);
        if (this.mOriginalSharedElementStates != null) {
            for (int i = 0; i < list2.size(); i++) {
                View view = list2.get(i);
                if (!(view == null || (map = this.mOriginalSharedElementStates.get(view.getId())) == null)) {
                    if (view instanceof ImageView) {
                        ((ImageView) view).setImageDrawable((Drawable) map.get("searchSharedElement:snapshot:image_drawable"));
                    } else if (view instanceof TextView) {
                        TextView textView = (TextView) view;
                        textView.setText((String) map.get("searchSharedElement:snapshot:textview_text"));
                        textView.setHint((String) map.get("searchSharedElement:snapshot:textview_hint"));
                        textView.setTextColor((ColorStateList) map.get("searchSharedElement:snapshot:textview_text_color"));
                        textView.setHintTextColor((ColorStateList) map.get("searchSharedElement:snapshot:textview_hint_color"));
                    }
                    view.setBackground((Drawable) map.get("searchSharedElement:snapshot:view_background"));
                }
            }
            this.mOriginalSharedElementStates = null;
        }
    }

    public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
        super.onSharedElementStart(list, list2, list3);
        int min = Math.min(list2.size(), list3.size());
        this.mOriginalSharedElementStates = new SparseArray<>(min);
        for (int i = 0; i < min; i++) {
            View view = list2.get(i);
            View view2 = list3.get(i);
            if (!(view == null || view2 == null)) {
                Map<String, Object> onCaptureSharedElementStates = onCaptureSharedElementStates(view);
                if (onCaptureSharedElementStates != null) {
                    this.mOriginalSharedElementStates.put(view.getId(), onCaptureSharedElementStates);
                }
                if ((view instanceof ImageView) && (view2 instanceof ImageView)) {
                    ((ImageView) view).setImageDrawable(((ImageView) view2).getDrawable());
                } else if ((view instanceof TextView) && (view2 instanceof TextView)) {
                    TextView textView = (TextView) view;
                    TextView textView2 = (TextView) view2;
                    textView.setText(textView2.getText());
                    textView.setHint(textView2.getHint());
                    textView.setTextColor(textView2.getCurrentTextColor());
                    textView.setHintTextColor(textView2.getCurrentHintTextColor());
                }
                view.setBackground(view2.getBackground());
            }
        }
    }
}
