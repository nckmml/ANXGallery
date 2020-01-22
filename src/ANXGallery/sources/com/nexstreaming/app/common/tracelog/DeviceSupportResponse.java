package com.nexstreaming.app.common.tracelog;

import com.nexstreaming.app.common.tracelog.TLP;
import java.util.List;
import java.util.Map;

public class DeviceSupportResponse implements TLP.BaseResponse {
    public DeviceInfo device_info;
    public MatchInfo match_info;
    public int next;
    public int result;

    public static class DeviceInfo {
        public int audio_codec_count;
        public List<ExportResInfo> export_res_extra;
        public List<ExportResInfo> export_res_hw;
        public List<ExportResInfo> export_res_sw;
        public int max_codec_mem_size;
        public int max_dec_count;
        public int max_dec_res_hw_b;
        public int max_dec_res_hw_h;
        public int max_dec_res_hw_m;
        public int max_dec_res_nexsw_b;
        public int max_dec_res_nexsw_h;
        public int max_dec_res_nexsw_m;
        public int max_dec_res_sw_b;
        public int max_dec_res_sw_h;
        public int max_dec_res_sw_m;
        public int max_enc_count;
        public int max_fhd_trans_time;
        public int max_fps;
        public int max_hw_import_res;
        public int max_sw_import_res;
        public Map<String, String> properties;
        public int rec_image_mode;
        public int rec_video_mode;
        public int support_avc;
        public int support_mpeg4v;
        public int supported;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("DeviceSupportResponse:\n");
            sb.append("  support_avc:");
            sb.append(this.support_avc);
            sb.append(10);
            sb.append("  support_mpeg4v:");
            sb.append(this.support_mpeg4v);
            sb.append(10);
            sb.append("  max_fps:");
            sb.append(this.max_fps);
            sb.append(10);
            sb.append("  max_codec_mem_size:");
            sb.append(this.max_codec_mem_size);
            sb.append(10);
            sb.append("  max_dec_count:");
            sb.append(this.max_dec_count);
            sb.append(10);
            sb.append("  max_enc_count:");
            sb.append(this.max_enc_count);
            sb.append(10);
            sb.append("  max_fhd_trans_time:");
            sb.append(this.max_fhd_trans_time);
            sb.append(10);
            sb.append("  rec_image_mode:");
            sb.append(this.rec_image_mode);
            sb.append(10);
            sb.append("  rec_video_mode:");
            sb.append(this.rec_video_mode);
            sb.append(10);
            sb.append("  audio_codec_count:");
            sb.append(this.audio_codec_count);
            sb.append(10);
            sb.append("  max_sw_import_res:");
            sb.append(this.max_sw_import_res);
            sb.append(10);
            sb.append("  max_hw_import_res:");
            sb.append(this.max_hw_import_res);
            sb.append(10);
            sb.append("  max_dec_res_nexsw_b:");
            sb.append(this.max_dec_res_nexsw_b);
            sb.append(10);
            sb.append("  max_dec_res_nexsw_b:");
            sb.append(this.max_dec_res_nexsw_b);
            sb.append(10);
            sb.append("  max_dec_res_nexsw_h:");
            sb.append(this.max_dec_res_nexsw_h);
            sb.append(10);
            sb.append("  max_dec_res_sw_b:");
            sb.append(this.max_dec_res_sw_b);
            sb.append(10);
            sb.append("  max_dec_res_sw_m:");
            sb.append(this.max_dec_res_sw_m);
            sb.append(10);
            sb.append("  max_dec_res_sw_h:");
            sb.append(this.max_dec_res_sw_h);
            sb.append(10);
            sb.append("  max_dec_res_hw_b:");
            sb.append(this.max_dec_res_hw_b);
            sb.append(10);
            sb.append("  max_dec_res_hw_m:");
            sb.append(this.max_dec_res_hw_m);
            sb.append(10);
            sb.append("  max_dec_res_hw_h:");
            sb.append(this.max_dec_res_hw_h);
            sb.append(10);
            int i = 0;
            if (this.export_res_sw == null) {
                sb.append("  export_res_sw: null\n");
            } else {
                sb.append("  export_res_sw:\n");
                int i2 = 0;
                for (ExportResInfo exportResInfo : this.export_res_sw) {
                    sb.append("    [" + i2 + "] " + exportResInfo.toString() + "\n");
                    i2++;
                }
            }
            if (this.export_res_hw == null) {
                sb.append("  export_res_hw: null\n");
            } else {
                sb.append("  export_res_hw:\n");
                int i3 = 0;
                for (ExportResInfo exportResInfo2 : this.export_res_hw) {
                    sb.append("    [" + i3 + "] " + exportResInfo2.toString() + "\n");
                    i3++;
                }
            }
            if (this.export_res_extra == null) {
                sb.append("  export_res_extra: null\n");
            } else {
                sb.append("  export_res_extra:\n");
                for (ExportResInfo exportResInfo3 : this.export_res_extra) {
                    sb.append("    [" + i + "] " + exportResInfo3.toString() + "\n");
                    i++;
                }
            }
            if (this.properties == null) {
                sb.append("  properties: null\n");
            } else {
                sb.append("  properties:\n");
                for (Map.Entry next : this.properties.entrySet()) {
                    sb.append("    " + ((String) next.getKey()) + "=" + ((String) next.getValue()));
                }
            }
            return sb.toString();
        }
    }

    public static class ExportResInfo {
        public int bitrate;
        public int display_height;
        public int height;
        public int width;

        public String toString() {
            return "<ExportResInfo " + this.width + "x" + this.height + " disp=" + this.display_height + " bitrate=" + this.bitrate + ">";
        }
    }

    public static class MatchInfo {
        public String board_platform;
        public String build_device;
        public String build_model;
        public String manufacturer;
        public int os_api_level_max;
        public int os_api_level_min;
        public Integer record_idx;

        public String toString() {
            return "MatchInfo:\n" + "  record_idx:" + this.record_idx + 10 + "  build_device:" + this.build_device + 10 + "  build_model:" + this.build_model + 10 + "  board_platform:" + this.board_platform + 10 + "  manufacturer:" + this.manufacturer + 10 + "  os_api_level_min:" + this.os_api_level_min + 10 + "  os_api_level_max:" + this.os_api_level_max + 10;
        }
    }

    public int getResult() {
        return this.result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceSupportResponse:\n");
        sb.append("  result:");
        sb.append(this.result);
        sb.append(10);
        sb.append("  next:");
        sb.append(this.next);
        sb.append(10);
        if (this.match_info == null) {
            sb.append("  match_info: null\n");
        } else {
            sb.append("  match_info:\n");
            sb.append(this.match_info.toString().replaceAll("(?m)^", "    "));
        }
        if (this.device_info == null) {
            sb.append("  device_info: null\n");
        } else {
            sb.append("  device_info:\n");
            sb.append(this.device_info.toString().replaceAll("(?m)^", "    "));
        }
        return sb.toString();
    }
}
