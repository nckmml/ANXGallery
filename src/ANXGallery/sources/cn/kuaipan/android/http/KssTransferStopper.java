package cn.kuaipan.android.http;

import com.xiaomi.opensdk.file.model.MiCloudTransferStopper;

public interface KssTransferStopper {

    public static class KssTransferStopperFromMiCloudTransferStopper implements KssTransferStopper {
        private final MiCloudTransferStopper mMiCloudTransferStopper;

        private KssTransferStopperFromMiCloudTransferStopper(MiCloudTransferStopper miCloudTransferStopper) {
            this.mMiCloudTransferStopper = miCloudTransferStopper;
        }

        public static KssTransferStopper get(MiCloudTransferStopper miCloudTransferStopper) {
            if (miCloudTransferStopper == null) {
                return null;
            }
            return new KssTransferStopperFromMiCloudTransferStopper(miCloudTransferStopper);
        }

        public boolean checkStop() {
            return this.mMiCloudTransferStopper.checkStop();
        }
    }

    boolean checkStop();
}
