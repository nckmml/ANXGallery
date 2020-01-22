package com.nexstreaming.kminternal.kinemaster.codeccolorformat;

/* compiled from: WrapMediaCodec */
class a {
    a() {
    }

    public static String a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.format("UNKNOWN", new Object[0]) : "BUFFER_FLAG_CODEC_CONFIG" : "BUFFER_FLAG_END_OF_STREAM" : "INFO_TRY_AGAIN_LATER" : "INFO_OUTPUT_FORMAT_CHANGED" : "INFO_OUTPUT_BUFFERS_CHANGED";
    }
}
