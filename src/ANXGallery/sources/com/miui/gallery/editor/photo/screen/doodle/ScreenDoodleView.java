package com.miui.gallery.editor.photo.screen.doodle;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.miui.gallery.editor.photo.app.doodle.DoodlePaintItem;
import com.miui.gallery.editor.photo.core.common.model.DoodleData;
import com.miui.gallery.editor.photo.core.imports.doodle.DoodleConfig;
import com.miui.gallery.editor.photo.core.imports.doodle.PaintElementOperationDrawable;
import com.miui.gallery.editor.photo.core.imports.doodle.painter.DoodleItem;
import com.miui.gallery.editor.photo.core.imports.doodle.painter.DoodleNode;
import com.miui.gallery.editor.photo.screen.base.ScreenBaseGestureView;
import com.miui.gallery.editor.photo.screen.base.ScreenVirtualEditorView;
import com.miui.gallery.editor.photo.screen.home.ScreenEditorView;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public class ScreenDoodleView extends ScreenVirtualEditorView implements IScreenDoodleOperation {
    /* access modifiers changed from: private */
    public int mActivationIndex = -1;
    private int mColor = -35801;
    /* access modifiers changed from: private */
    public DoodleItem mCurrentDoodleItem = DoodleItem.PATH;
    private int mCurrentMenuItemIndex;
    /* access modifiers changed from: private */
    public ArrayList<DoodleNode> mDoodleNodeList = new ArrayList<>();
    private ScreenBaseGestureView.FeatureGesListener mGesListener = new GesListener(this, (AnonymousClass1) null);
    /* access modifiers changed from: private */
    public boolean mIsAddNew = false;
    /* access modifiers changed from: private */
    public Matrix mMatrix = new Matrix();
    /* access modifiers changed from: private */
    public PaintElementOperationDrawable mOperationDrawable;
    private DoodlePaintItem.PaintType mPaintType = DoodlePaintItem.PaintType.MEDIUM;
    /* access modifiers changed from: private */
    public RectF mRectFTemp = new RectF();

    /* renamed from: com.miui.gallery.editor.photo.screen.doodle.ScreenDoodleView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$editor$photo$screen$doodle$ScreenDoodleView$TouchAction = new int[TouchAction.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            $SwitchMap$com$miui$gallery$editor$photo$screen$doodle$ScreenDoodleView$TouchAction[TouchAction.DELETE.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$editor$photo$screen$doodle$ScreenDoodleView$TouchAction[TouchAction.NONE.ordinal()] = 2;
            $SwitchMap$com$miui$gallery$editor$photo$screen$doodle$ScreenDoodleView$TouchAction[TouchAction.ROTATE.ordinal()] = 3;
            try {
                $SwitchMap$com$miui$gallery$editor$photo$screen$doodle$ScreenDoodleView$TouchAction[TouchAction.SCALE.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private class GesListener implements ScreenBaseGestureView.FeatureGesListener {
        private DoodleNode mActivationNode;
        private DoodleNode mCurrentNode;
        private int mDownIndex;
        private DoodleNode mDownNode;
        private float mDownX;
        private float mDownY;
        private float[] mPointTemp1;
        private float[] mPointTemp2;
        private float[] mPointTemp3;
        private boolean mScaleMode;
        private float mScrollX;
        private float mScrollY;
        private TouchAction mTouchAction;

        private GesListener() {
            this.mTouchAction = TouchAction.NONE;
            this.mDownIndex = -1;
            this.mScaleMode = false;
            this.mPointTemp1 = new float[2];
            this.mPointTemp2 = new float[2];
            this.mPointTemp3 = new float[2];
        }

        /* synthetic */ GesListener(ScreenDoodleView screenDoodleView, AnonymousClass1 r2) {
            this();
        }

        private int findItemByEvent(float f, float f2) {
            if (ScreenDoodleView.this.mActivationIndex != -1 && ((DoodleNode) ScreenDoodleView.this.mDoodleNodeList.get(ScreenDoodleView.this.mActivationIndex)).contains(f, f2)) {
                return ScreenDoodleView.this.mActivationIndex;
            }
            for (int size = ScreenDoodleView.this.mDoodleNodeList.size() - 1; size >= 0; size--) {
                DoodleNode doodleNode = (DoodleNode) ScreenDoodleView.this.mDoodleNodeList.get(size);
                if (doodleNode.contains(f, f2) && doodleNode.isCanSelected()) {
                    return size;
                }
            }
            return -1;
        }

        private TouchAction findTouchActionWithAction(float f, float f2) {
            ScreenDoodleView.this.mOperationDrawable.getDecorationRect(PaintElementOperationDrawable.Action.DELETE, ScreenDoodleView.this.mRectFTemp);
            if (ScreenDoodleView.this.mRectFTemp.contains(f, f2)) {
                return TouchAction.DELETE;
            }
            ScreenDoodleView.this.mOperationDrawable.getDecorationRect(PaintElementOperationDrawable.Action.ROTATE, ScreenDoodleView.this.mRectFTemp);
            if (ScreenDoodleView.this.mRectFTemp.contains(f, f2)) {
                return TouchAction.ROTATE;
            }
            ScreenDoodleView.this.mOperationDrawable.getDecorationRect(PaintElementOperationDrawable.Action.SCALE, ScreenDoodleView.this.mRectFTemp);
            return ScreenDoodleView.this.mRectFTemp.contains(f, f2) ? TouchAction.SCALE : TouchAction.NONE;
        }

        private void generateDoodle(float f, float f2) {
            if (this.mCurrentNode == null) {
                this.mCurrentNode = ScreenDoodleView.this.mCurrentDoodleItem.getDoodleDrawable(ScreenDoodleView.this.mEditorView.getContext().getResources());
                ScreenDoodleView.this.addNewItem(this.mCurrentNode);
                boolean unused = ScreenDoodleView.this.mIsAddNew = true;
            }
            this.mCurrentNode.receivePosition(f, f2);
        }

        private void moveDoodle(float f, float f2, DoodleNode doodleNode) {
            doodleNode.getStrokeRectF(ScreenDoodleView.this.mRectFTemp);
            ScreenDoodleView.this.mMatrix.reset();
            ScreenDoodleView.this.mMatrix.postRotate(doodleNode.getRotateDegrees(), doodleNode.getRotateX(), doodleNode.getRotateY());
            ScreenDoodleView.this.mMatrix.postTranslate(doodleNode.getUserLocationX(), doodleNode.getUserLocationY());
            ScreenDoodleView.this.mMatrix.postConcat(ScreenDoodleView.this.getBitmapGestureParamsHolder().mCanvasMatrix);
            ScreenDoodleView.this.mMatrix.mapRect(ScreenDoodleView.this.mRectFTemp);
            int rectOverScrollStatus = ScreenDoodleView.this.mEditorView.getRectOverScrollStatus(ScreenDoodleView.this.mRectFTemp);
            Log.d("ScreenDoodleView", "scroll rect : %s", (Object) ScreenDoodleView.this.mRectFTemp);
            float f3 = 0.0f;
            if ((rectOverScrollStatus & 8) == 0 ? !((rectOverScrollStatus & 4) == 0 || f >= 0.0f) : f > 0.0f) {
                f = 0.0f;
            }
            if ((rectOverScrollStatus & 2) == 0 ? (rectOverScrollStatus & 1) == 0 || f2 >= 0.0f : f2 <= 0.0f) {
                f3 = f2;
            }
            doodleNode.appendUserLocationX(-f);
            doodleNode.appendUserLocationY(-f3);
            ScreenDoodleView.this.mOperationDrawable.setDrawDecoration(false);
        }

        public void onActionUp(float f, float f2) {
            if (ScreenDoodleView.this.mIsAddNew) {
                DoodleNode doodleNode = (DoodleNode) ScreenDoodleView.this.mDoodleNodeList.get(ScreenDoodleView.this.mDoodleNodeList.size() - 1);
                doodleNode.countSize();
                if (doodleNode.getDoodleType() != DoodleNode.DoodleDrawableType.PATH) {
                    ScreenDoodleView screenDoodleView = ScreenDoodleView.this;
                    screenDoodleView.setActivation(screenDoodleView.mDoodleNodeList.size() - 1);
                    ScreenDoodleView.this.invalidate();
                }
            } else {
                DoodleNode doodleNode2 = this.mActivationNode;
                if (doodleNode2 != null) {
                    doodleNode2.processOnUp();
                    ScreenDoodleView.this.invalidate();
                }
            }
            ScreenDoodleView.this.mOperationDrawable.setDrawDecoration(true);
            ScreenDoodleView.this.invalidate();
        }

        public boolean onDown(MotionEvent motionEvent) {
            Log.d("ScreenDoodleView", "onDown");
            ScreenDoodleView.this.mEditorView.convertPointToViewPortCoordinate(motionEvent, this.mPointTemp1);
            float[] fArr = this.mPointTemp1;
            this.mDownX = fArr[0];
            this.mDownY = fArr[1];
            this.mScrollX = this.mDownX;
            this.mScrollY = this.mDownY;
            this.mTouchAction = TouchAction.NONE;
            this.mCurrentNode = null;
            this.mDownNode = null;
            this.mActivationNode = null;
            boolean unused = ScreenDoodleView.this.mIsAddNew = false;
            this.mScaleMode = false;
            if (ScreenDoodleView.this.mActivationIndex != -1) {
                this.mActivationNode = (DoodleNode) ScreenDoodleView.this.mDoodleNodeList.get(ScreenDoodleView.this.mActivationIndex);
            }
            this.mDownIndex = findItemByEvent(this.mDownX, this.mDownY);
            if (this.mDownIndex != -1) {
                this.mDownNode = (DoodleNode) ScreenDoodleView.this.mDoodleNodeList.get(this.mDownIndex);
            }
            if (this.mActivationNode != null) {
                this.mTouchAction = findTouchActionWithAction(motionEvent.getX(), motionEvent.getY());
                this.mActivationNode.processOnDownEvent(this.mDownX, this.mDownY);
            } else {
                this.mTouchAction = TouchAction.NONE;
            }
            Log.d("ScreenDoodleView", "mTouchAction %s", (Object) this.mTouchAction);
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            Log.d("ScreenDoodleView", "onScale : %f", (Object) Float.valueOf(scaleFactor));
            DoodleNode doodleNode = this.mActivationNode;
            if (doodleNode != null) {
                doodleNode.appendScale(scaleFactor);
            }
            ScreenDoodleView.this.invalidate();
            return false;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            if (ScreenDoodleView.this.mActivationIndex == -1) {
                return false;
            }
            this.mActivationNode = (DoodleNode) ScreenDoodleView.this.mDoodleNodeList.get(ScreenDoodleView.this.mActivationIndex);
            this.mScaleMode = true;
            return false;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        public void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            MotionEvent motionEvent3 = motionEvent;
            MotionEvent motionEvent4 = motionEvent2;
            float[] fArr = this.mPointTemp1;
            float[] fArr2 = this.mPointTemp2;
            float convertDistanceX = ScreenDoodleView.this.mEditorView.convertDistanceX(f);
            float convertDistanceY = ScreenDoodleView.this.mEditorView.convertDistanceY(f2);
            this.mScrollX -= convertDistanceX;
            this.mScrollY -= convertDistanceY;
            int i = AnonymousClass1.$SwitchMap$com$miui$gallery$editor$photo$screen$doodle$ScreenDoodleView$TouchAction[this.mTouchAction.ordinal()];
            if (i != 2) {
                if (i == 3) {
                    ScreenDoodleView.this.mEditorView.convertPointToViewPortCoordinate(motionEvent3, fArr);
                    ScreenDoodleView.this.mEditorView.convertPointToViewPortCoordinate(motionEvent4, fArr2);
                    ScreenDoodleView.this.mOperationDrawable.getDecorationRect(PaintElementOperationDrawable.Action.ROTATE, ScreenDoodleView.this.mRectFTemp);
                    float[] fArr3 = this.mPointTemp3;
                    fArr3[0] = ScreenDoodleView.this.mRectFTemp.centerX();
                    fArr3[1] = ScreenDoodleView.this.mRectFTemp.centerY();
                    ScreenDoodleView.this.mEditorView.convertPointToViewPortCoordinate(fArr3);
                    this.mActivationNode.processRotateEvent(fArr[0], fArr[1], fArr2[0], fArr2[1], convertDistanceX, convertDistanceY, fArr3[0], fArr3[1]);
                    ScreenDoodleView.this.mOperationDrawable.setDrawDecoration(false);
                } else if (i == 4) {
                    ScreenDoodleView.this.mEditorView.convertPointToViewPortCoordinate(motionEvent3, fArr);
                    ScreenDoodleView.this.mEditorView.convertPointToViewPortCoordinate(motionEvent4, fArr2);
                    ScreenDoodleView.this.mOperationDrawable.getDecorationRect(PaintElementOperationDrawable.Action.SCALE, ScreenDoodleView.this.mRectFTemp);
                    float[] fArr4 = this.mPointTemp3;
                    fArr4[0] = ScreenDoodleView.this.mRectFTemp.centerX();
                    fArr4[1] = ScreenDoodleView.this.mRectFTemp.centerY();
                    ScreenDoodleView.this.mEditorView.convertPointToViewPortCoordinate(fArr4);
                    this.mActivationNode.processScaleEvent(fArr[0], fArr[1], fArr2[0], fArr2[1], convertDistanceX, convertDistanceY, fArr4[0], fArr4[1]);
                    ScreenDoodleView.this.mOperationDrawable.setDrawDecoration(false);
                }
            } else if (this.mScaleMode) {
                DoodleNode doodleNode = this.mActivationNode;
                if (doodleNode != null) {
                    moveDoodle(convertDistanceX, convertDistanceY, doodleNode);
                }
            } else {
                DoodleNode doodleNode2 = this.mActivationNode;
                if (doodleNode2 != null) {
                    DoodleNode doodleNode3 = this.mDownNode;
                    if (doodleNode3 != null && doodleNode3 == doodleNode2) {
                        moveDoodle(convertDistanceX, convertDistanceY, doodleNode2);
                    } else if (motionEvent2.getPointerCount() == 1) {
                        generateDoodle(this.mScrollX, this.mScrollY);
                    }
                } else {
                    if (this.mCurrentNode == null) {
                        generateDoodle(this.mDownX, this.mDownY);
                    }
                    ScreenDoodleView.this.mEditorView.convertPointToViewPortCoordinate(motionEvent4, fArr2);
                    if (motionEvent2.getPointerCount() == 1) {
                        generateDoodle(fArr2[0], fArr2[1]);
                    }
                }
            }
            DoodleNode doodleNode4 = this.mActivationNode;
            if (doodleNode4 != null) {
                ScreenDoodleView.this.configOperationPosition(doodleNode4);
            }
            ScreenDoodleView.this.invalidate();
        }

        public void onSingleTapUp(MotionEvent motionEvent) {
            if (this.mTouchAction == TouchAction.NONE) {
                int i = this.mDownIndex;
                if (i == -1) {
                    ScreenDoodleView.this.clearActivation();
                } else if (i != ScreenDoodleView.this.mActivationIndex) {
                    ScreenDoodleView.this.setActivation(this.mDownIndex);
                }
            } else if (AnonymousClass1.$SwitchMap$com$miui$gallery$editor$photo$screen$doodle$ScreenDoodleView$TouchAction[this.mTouchAction.ordinal()] == 1) {
                ScreenDoodleView.this.deleteItem(this.mActivationNode);
            }
        }
    }

    private enum TouchAction {
        NONE,
        DELETE,
        SCALE,
        ROTATE
    }

    public ScreenDoodleView(ScreenEditorView screenEditorView) {
        super(screenEditorView);
        this.mEditorView.setFeatureGestureListener(this.mGesListener);
        this.mOperationDrawable = new PaintElementOperationDrawable(this.mContext.getResources());
    }

    /* access modifiers changed from: private */
    public void addNewItem(DoodleNode doodleNode) {
        if (doodleNode != null) {
            doodleNode.setPaintSize((this.mPaintType.paintSize * this.mContext.getResources().getDisplayMetrics().density) / getBitmapGestureParamsHolder().getMatrixValues()[0]);
            doodleNode.setPaintColor(this.mColor);
            this.mDoodleNodeList.add(doodleNode);
            addDrawNode(doodleNode);
        }
    }

    private void configOperationDecoration(DoodleNode doodleNode) {
        if (doodleNode.getDoodleType() != DoodleNode.DoodleDrawableType.VECTOR) {
            this.mOperationDrawable.configActionPosition(PaintElementOperationDrawable.Action.DELETE, PaintElementOperationDrawable.Action.ROTATE, PaintElementOperationDrawable.Action.SCALE, (PaintElementOperationDrawable.Action) null, this.mEditorView.getResources());
        } else if (doodleNode.getDoodleName().equals(DoodleItem.ARROW.name())) {
            this.mOperationDrawable.configActionPosition((PaintElementOperationDrawable.Action) null, PaintElementOperationDrawable.Action.DELETE, (PaintElementOperationDrawable.Action) null, PaintElementOperationDrawable.Action.SCALE, this.mEditorView.getResources());
        } else {
            this.mOperationDrawable.configActionPosition(PaintElementOperationDrawable.Action.DELETE, (PaintElementOperationDrawable.Action) null, PaintElementOperationDrawable.Action.SCALE, (PaintElementOperationDrawable.Action) null, this.mEditorView.getResources());
        }
    }

    /* access modifiers changed from: private */
    public void configOperationPosition(DoodleNode doodleNode) {
        float userLocationX = doodleNode.getUserLocationX();
        float userLocationY = doodleNode.getUserLocationY();
        doodleNode.getStrokeRectF(this.mRectFTemp);
        this.mRectFTemp.offset(userLocationX, userLocationY);
        this.mOperationDrawable.configDecorationPositon(this.mRectFTemp, getBitmapGestureParamsHolder().mCanvasMatrix, doodleNode.getRotateDegrees(), doodleNode.getRotateX() + userLocationX, doodleNode.getRotateY() + userLocationY);
    }

    /* access modifiers changed from: private */
    public void deleteItem(DoodleNode doodleNode) {
        this.mActivationIndex = -1;
        this.mDoodleNodeList.remove(doodleNode);
        removeDrawNode(doodleNode);
        invalidate();
        this.mEditorView.disableChildHandleMode();
    }

    /* access modifiers changed from: private */
    public void setActivation(int i) {
        this.mActivationIndex = i;
        int i2 = this.mActivationIndex;
        if (i2 != -1) {
            DoodleNode doodleNode = this.mDoodleNodeList.get(i2);
            configOperationPosition(doodleNode);
            configOperationDecoration(doodleNode);
        }
        invalidate();
        this.mEditorView.enableChildHandleMode();
    }

    public void canvasMatrixChange() {
        int i = this.mActivationIndex;
        if (i != -1) {
            configOperationPosition(this.mDoodleNodeList.get(i));
        }
    }

    public void clearActivation() {
        this.mActivationIndex = -1;
        invalidate();
        this.mEditorView.disableChildHandleMode();
    }

    public void drawOverlay(Canvas canvas) {
        canvas.save();
        canvas.clipRect(getBitmapGestureParamsHolder().mBitmapDisplayRect);
        if (this.mActivationIndex != -1) {
            this.mOperationDrawable.draw(canvas);
        }
        canvas.restore();
    }

    public int getCurrentMenuItemIndex() {
        return this.mCurrentMenuItemIndex;
    }

    public DoodlePaintItem.PaintType getPaintType() {
        return this.mPaintType;
    }

    public void onChangeOperation(boolean z) {
        if (z) {
            this.mEditorView.setFeatureGestureListener(this.mGesListener);
            return;
        }
        Iterator<DoodleNode> it = this.mDoodleNodeList.iterator();
        while (it.hasNext()) {
            it.next().setCanSelected(false);
        }
        clearActivation();
    }

    public void onDetachedFromWindow() {
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    public void setCurrentDoodleItem(DoodleItem doodleItem) {
        this.mCurrentDoodleItem = doodleItem;
    }

    public void setDoodleData(DoodleData doodleData, int i) {
        setCurrentDoodleItem(((DoodleConfig) doodleData).getDoodleItem());
        clearActivation();
        this.mCurrentMenuItemIndex = i;
    }

    public void setPaintType(DoodlePaintItem.PaintType paintType) {
        this.mPaintType = paintType;
    }
}
