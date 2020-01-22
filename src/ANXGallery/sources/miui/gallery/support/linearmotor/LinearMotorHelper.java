package miui.gallery.support.linearmotor;

import android.content.Context;
import com.miui.gallery.util.LazyValue;
import com.miui.gallery.util.Log;
import miui.gallery.support.core.SdkHelper;
import miui.os.SystemProperties;
import miui.util.HapticFeedbackUtil;

public class LinearMotorHelper {
    public static String EFFECT_KEY_MESH_LIGHT = "mesh_light";
    public static String EFFECT_KEY_MESH_NORMAL = "mesh_normal";
    public static String EFFECT_KEY_SWITCH = "switch";
    public static int HAPTIC_MESH_NORMAL = 268435461;
    public static int HAPTIC_STRENGTH_ID_0 = 0;
    public static int HAPTIC_STRENGTH_ID_1 = 1;
    public static int HAPTIC_STRENGTH_ID_2 = 2;
    public static int HAPTIC_SWITCH = 268435459;
    private static final LazyValue<Void, Boolean> LINEAR_MOTOR_SUPPORTED = new LazyValue<Void, Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean onInit(Void voidR) {
            return Boolean.valueOf("linear".equals(SystemProperties.get("sys.haptic.motor")));
        }
    };

    public static boolean performHapticFeedback(Context context, String str) {
        boolean z = false;
        try {
            if (SdkHelper.IS_MIUI) {
                HapticFeedbackUtil hapticFeedbackUtil = null;
                if (LINEAR_MOTOR_SUPPORTED.get(null).booleanValue()) {
                    if (context == null) {
                        return false;
                    }
                    try {
                        HapticFeedbackUtil hapticFeedbackUtil2 = new HapticFeedbackUtil(context, false);
                        try {
                            z = hapticFeedbackUtil2.performHapticFeedback(str, false);
                            hapticFeedbackUtil2.release();
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            hapticFeedbackUtil = hapticFeedbackUtil2;
                            hapticFeedbackUtil.release();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        hapticFeedbackUtil.release();
                        throw th;
                    }
                }
            }
            Log.d("LinearMotorHelper", "the device is not support LinearMotorVibrate.");
            return false;
        } catch (Exception e) {
            Log.e("LinearMotorHelper", "performHapticFeedback  exception: ", (Object) e);
        }
    }

    public static boolean performHapticFeedback(Context context, String str, int i) {
        boolean z = false;
        try {
            if (SdkHelper.IS_MIUI) {
                HapticFeedbackUtil hapticFeedbackUtil = null;
                if (LINEAR_MOTOR_SUPPORTED.get(null).booleanValue()) {
                    if (context == null) {
                        return false;
                    }
                    try {
                        HapticFeedbackUtil hapticFeedbackUtil2 = new HapticFeedbackUtil(context, false);
                        try {
                            z = hapticFeedbackUtil2.performHapticFeedback(str, false, i);
                            hapticFeedbackUtil2.release();
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            hapticFeedbackUtil = hapticFeedbackUtil2;
                            hapticFeedbackUtil.release();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        hapticFeedbackUtil.release();
                        throw th;
                    }
                }
            }
            Log.d("LinearMotorHelper", "the device is not support LinearMotorVibrate.");
            return false;
        } catch (Exception e) {
            Log.e("LinearMotorHelper", "performHapticFeedback Exception: ", (Object) e);
        }
    }
}
