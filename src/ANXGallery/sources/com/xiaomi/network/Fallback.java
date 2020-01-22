package com.xiaomi.network;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fallback {
    public String city;
    public String country;
    private long effectDuration = 86400000;
    private ArrayList<WeightedHost> fallbackHosts = new ArrayList<>();
    public String host;
    public String ip;
    public String isp;
    private String mDomain = "s.mi1.cc";
    private String mISP;
    private double mPercent = 0.1d;
    public String networkLabel = "";
    public String province;
    private long timestamp;
    protected String xforward;

    public Fallback(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.timestamp = System.currentTimeMillis();
            this.fallbackHosts.add(new WeightedHost(str, -1));
            this.networkLabel = HostManager.getActiveNetworkLabel();
            this.host = str;
            return;
        }
        throw new IllegalArgumentException("the host is empty");
    }

    private synchronized void deleteWeightedHost(String str) {
        Iterator<WeightedHost> it = this.fallbackHosts.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().host, str)) {
                it.remove();
            }
        }
    }

    public void accessHost(String str, int i, long j, long j2, Exception exc) {
        accessHost(str, new AccessHistory(i, j, j2, exc));
    }

    public synchronized void accessHost(String str, AccessHistory accessHistory) {
        Iterator<WeightedHost> it = this.fallbackHosts.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WeightedHost next = it.next();
            if (TextUtils.equals(str, next.host)) {
                next.addAccessHistory(accessHistory);
                break;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void addHost(WeightedHost weightedHost) {
        deleteWeightedHost(weightedHost.host);
        this.fallbackHosts.add(weightedHost);
    }

    public synchronized void addHost(String str) {
        addHost(new WeightedHost(str));
    }

    public synchronized void addPreferredHost(String[] strArr) {
        int i;
        int size = this.fallbackHosts.size() - 1;
        while (true) {
            i = 0;
            if (size < 0) {
                break;
            }
            int length = strArr.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (TextUtils.equals(this.fallbackHosts.get(size).host, strArr[i])) {
                    this.fallbackHosts.remove(size);
                    break;
                }
                i++;
            }
            size--;
        }
        Iterator<WeightedHost> it = this.fallbackHosts.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            WeightedHost next = it.next();
            if (next.weight > i2) {
                i2 = next.weight;
            }
        }
        while (i < strArr.length) {
            addHost(new WeightedHost(strArr[i], (strArr.length + i2) - i));
            i++;
        }
    }

    public void failedHost(String str, long j, long j2, Exception exc) {
        accessHost(str, -1, j, j2, exc);
    }

    public void failedUrl(String str, long j, long j2, Exception exc) {
        try {
            failedHost(new URL(str).getHost(), j, j2, exc);
        } catch (MalformedURLException unused) {
        }
    }

    public synchronized Fallback fromJSON(JSONObject jSONObject) throws JSONException {
        this.networkLabel = jSONObject.optString("net");
        this.effectDuration = jSONObject.getLong("ttl");
        this.mPercent = jSONObject.getDouble("pct");
        this.timestamp = jSONObject.getLong("ts");
        this.city = jSONObject.optString("city");
        this.province = jSONObject.optString("prv");
        this.country = jSONObject.optString("cty");
        this.isp = jSONObject.optString("isp");
        this.ip = jSONObject.optString("ip");
        this.host = jSONObject.optString("host");
        this.xforward = jSONObject.optString("xf");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            addHost(new WeightedHost().fromJSON(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized ArrayList<String> getHosts() {
        return getHosts(false);
    }

    public synchronized ArrayList<String> getHosts(boolean z) {
        ArrayList<String> arrayList;
        WeightedHost[] weightedHostArr = new WeightedHost[this.fallbackHosts.size()];
        this.fallbackHosts.toArray(weightedHostArr);
        Arrays.sort(weightedHostArr);
        arrayList = new ArrayList<>();
        for (WeightedHost weightedHost : weightedHostArr) {
            if (z) {
                arrayList.add(weightedHost.host);
            } else {
                int indexOf = weightedHost.host.indexOf(":");
                if (indexOf != -1) {
                    arrayList.add(weightedHost.host.substring(0, indexOf));
                } else {
                    arrayList.add(weightedHost.host);
                }
            }
        }
        return arrayList;
    }

    public synchronized String getISP() {
        if (!TextUtils.isEmpty(this.mISP)) {
            return this.mISP;
        } else if (TextUtils.isEmpty(this.isp)) {
            return "hardcode_isp";
        } else {
            this.mISP = XMStringUtils.join((Object[]) new String[]{this.isp, this.province, this.city, this.country, this.ip}, "_");
            return this.mISP;
        }
    }

    public ArrayList<String> getUrls(String str) throws MalformedURLException {
        if (!TextUtils.isEmpty(str)) {
            URL url = new URL(str);
            if (TextUtils.equals(url.getHost(), this.host)) {
                ArrayList<String> arrayList = new ArrayList<>();
                Iterator<String> it = getHosts(true).iterator();
                while (it.hasNext()) {
                    Host parse = Host.parse(it.next(), url.getPort());
                    arrayList.add(new URL(url.getProtocol(), parse.getHost(), parse.getPort(), url.getFile()).toString());
                }
                return arrayList;
            }
            throw new IllegalArgumentException("the url is not supported by the fallback");
        }
        throw new IllegalArgumentException("the url is empty.");
    }

    public boolean isEffective() {
        return System.currentTimeMillis() - this.timestamp < this.effectDuration;
    }

    /* access modifiers changed from: package-private */
    public boolean isExpired() {
        long j = this.effectDuration;
        if (864000000 >= j) {
            j = 864000000;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.timestamp;
        return currentTimeMillis - j2 > j || (currentTimeMillis - j2 > this.effectDuration && this.networkLabel.startsWith("WIFI-"));
    }

    public boolean match() {
        return TextUtils.equals(this.networkLabel, HostManager.getActiveNetworkLabel());
    }

    public boolean match(Fallback fallback) {
        return TextUtils.equals(this.networkLabel, fallback.networkLabel);
    }

    public void setDomainName(String str) {
        this.mDomain = str;
    }

    public void setEffectiveDuration(long j) {
        if (j > 0) {
            this.effectDuration = j;
            return;
        }
        throw new IllegalArgumentException("the duration is invalid " + j);
    }

    public void setPercent(double d) {
        this.mPercent = d;
    }

    public void succeedHost(String str, long j, long j2) {
        accessHost(str, 0, j, j2, (Exception) null);
    }

    public void succeedUrl(String str, long j, long j2) {
        try {
            succeedHost(new URL(str).getHost(), j, j2);
        } catch (MalformedURLException unused) {
        }
    }

    public synchronized JSONObject toJSON() throws JSONException {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("net", this.networkLabel);
        jSONObject.put("ttl", this.effectDuration);
        jSONObject.put("pct", this.mPercent);
        jSONObject.put("ts", this.timestamp);
        jSONObject.put("city", this.city);
        jSONObject.put("prv", this.province);
        jSONObject.put("cty", this.country);
        jSONObject.put("isp", this.isp);
        jSONObject.put("ip", this.ip);
        jSONObject.put("host", this.host);
        jSONObject.put("xf", this.xforward);
        JSONArray jSONArray = new JSONArray();
        Iterator<WeightedHost> it = this.fallbackHosts.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJSON());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.networkLabel);
        sb.append("\n");
        sb.append(getISP());
        Iterator<WeightedHost> it = this.fallbackHosts.iterator();
        while (it.hasNext()) {
            sb.append("\n");
            sb.append(it.next().toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
