package cn.kuaipan.android.http;

import android.net.Uri;
import android.util.Log;
import cn.kuaipan.android.http.multipart.ByteArrayValuePair;
import cn.kuaipan.android.http.multipart.FilePart;
import cn.kuaipan.android.http.multipart.FileValuePair;
import cn.kuaipan.android.http.multipart.MultipartEntity;
import cn.kuaipan.android.http.multipart.Part;
import cn.kuaipan.android.http.multipart.StringPart;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.AbstractHttpEntity;
import org.keyczar.Keyczar;

public class KscHttpRequest {
    private final IKscDecoder mDecoder;
    private final IKscTransferListener mListener;
    private final HttpMethod mMethod;
    private AbstractHttpEntity mPostEntity;
    private final ArrayList<NameValuePair> mPostForm;
    private HttpUriRequest mRequest;
    private boolean mTryGzip;
    private Uri mUri;

    /* renamed from: cn.kuaipan.android.http.KscHttpRequest$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cn$kuaipan$android$http$KscHttpRequest$HttpMethod = new int[HttpMethod.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$cn$kuaipan$android$http$KscHttpRequest$HttpMethod[HttpMethod.GET.ordinal()] = 1;
            $SwitchMap$cn$kuaipan$android$http$KscHttpRequest$HttpMethod[HttpMethod.POST.ordinal()] = 2;
        }
    }

    public enum HttpMethod {
        GET,
        POST
    }

    public KscHttpRequest() {
        this((Uri) null);
    }

    public KscHttpRequest(Uri uri) {
        this((HttpMethod) null, uri, (IKscDecoder) null, (IKscTransferListener) null);
    }

    public KscHttpRequest(HttpMethod httpMethod, Uri uri, IKscDecoder iKscDecoder, IKscTransferListener iKscTransferListener) {
        this(httpMethod, uri, (AbstractHttpEntity) null, iKscDecoder, iKscTransferListener);
    }

    public KscHttpRequest(HttpMethod httpMethod, Uri uri, AbstractHttpEntity abstractHttpEntity, IKscDecoder iKscDecoder, IKscTransferListener iKscTransferListener) {
        this.mPostForm = new ArrayList<>();
        this.mTryGzip = false;
        this.mMethod = httpMethod;
        this.mUri = uri;
        this.mPostEntity = abstractHttpEntity;
        this.mDecoder = iKscDecoder;
        this.mListener = iKscTransferListener;
    }

    public KscHttpRequest(HttpMethod httpMethod, String str, IKscDecoder iKscDecoder, IKscTransferListener iKscTransferListener) {
        this(httpMethod, Uri.parse(str), (AbstractHttpEntity) null, iKscDecoder, iKscTransferListener);
    }

    private void checkRequest() {
        if (this.mRequest != null) {
            throw new RuntimeException("HttpRequest has been created. All input can't be changed.");
        }
    }

    private HttpUriRequest createHttpRequest() {
        if (isValidUri(this.mUri)) {
            String uri = this.mUri.toString();
            HttpMethod httpMethod = this.mMethod;
            if (httpMethod == null) {
                httpMethod = (this.mPostEntity != null || !this.mPostForm.isEmpty()) ? HttpMethod.POST : HttpMethod.GET;
            }
            HttpPost httpPost = null;
            int i = AnonymousClass1.$SwitchMap$cn$kuaipan$android$http$KscHttpRequest$HttpMethod[httpMethod.ordinal()];
            if (i == 1) {
                httpPost = new HttpGet(uri);
                if (this.mPostEntity != null || !this.mPostForm.isEmpty()) {
                    Log.w("KscHttpRequest", "Post data is not empty, but method is GET. All post data is lost.");
                }
            } else if (i == 2) {
                httpPost = new HttpPost(uri);
                if (!this.mPostForm.isEmpty()) {
                    this.mPostEntity = makeFormEntity();
                }
                httpPost.setEntity(this.mPostEntity);
            }
            if (this.mTryGzip) {
                httpPost.setHeader("Accept-Encoding", "gzip");
            }
            this.mRequest = httpPost;
            return httpPost;
        }
        throw new IllegalArgumentException("Request uri is not valid. uri=" + this.mUri);
    }

    private static ArrayList<NameValuePair> getMergedPostValue(AbstractHttpEntity abstractHttpEntity, List<NameValuePair> list) {
        ArrayList<NameValuePair> arrayList = new ArrayList<>();
        if (abstractHttpEntity != null) {
            try {
                arrayList.addAll(URLEncodedUtils.parse(abstractHttpEntity));
            } catch (IOException e) {
                Log.e("KscHttpRequest", "Failed parse an user entity.", e);
                throw new RuntimeException("Failed parse an user entity. The user entity should be parseable by URLEncodedUtils.parse(HttpEntity)", e);
            }
        }
        arrayList.addAll(list);
        return arrayList;
    }

    private static boolean isFormEntity(AbstractHttpEntity abstractHttpEntity) {
        return abstractHttpEntity == null || (abstractHttpEntity instanceof MultipartEntity) || URLEncodedUtils.isEncoded(abstractHttpEntity);
    }

    private boolean isValidUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0054 A[SYNTHETIC, Splitter:B:25:0x0054] */
    private AbstractHttpEntity makeFormEntity() {
        MultipartEntity multipartEntity = this.mPostEntity;
        ArrayList<NameValuePair> arrayList = this.mPostForm;
        if (arrayList.isEmpty()) {
            return this.mPostEntity;
        }
        boolean z = true;
        boolean z2 = multipartEntity != null && (multipartEntity instanceof MultipartEntity);
        if (!z2) {
            Iterator<NameValuePair> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NameValuePair next = it.next();
                if (!(next instanceof FileValuePair)) {
                    if (next instanceof ByteArrayValuePair) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                try {
                    return new UrlEncodedFormEntity(this.mPostForm, Keyczar.DEFAULT_ENCODING);
                } catch (UnsupportedEncodingException e) {
                    Log.e("KscHttpRequest", "JVM not support UTF_8?", e);
                    throw new RuntimeException("JVM not support UTF_8?", e);
                }
            } else if (multipartEntity == null || !(multipartEntity instanceof MultipartEntity)) {
                return new MultipartEntity(toPartArray(getMergedPostValue(multipartEntity, arrayList)));
            } else {
                MultipartEntity multipartEntity2 = multipartEntity;
                multipartEntity2.appendPart(toPartArray(arrayList));
                return multipartEntity2;
            }
        }
        z = z2;
        if (z) {
        }
    }

    private static Part[] toPartArray(List<NameValuePair> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int size = list.size();
        Part[] partArr = new Part[size];
        for (int i = 0; i < size; i++) {
            FileValuePair fileValuePair = (NameValuePair) list.get(i);
            if (fileValuePair instanceof FileValuePair) {
                try {
                    partArr[i] = new FilePart(fileValuePair.getName(), fileValuePair.getFile());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("The file to be sent should be exist. file=" + fileValuePair.getFile(), e);
                }
            } else if (fileValuePair instanceof ByteArrayValuePair) {
                partArr[i] = new FilePart(fileValuePair.getName(), fileValuePair.getValue(), ((ByteArrayValuePair) fileValuePair).getData());
            } else {
                partArr[i] = new StringPart(fileValuePair.getName(), fileValuePair.getValue(), Keyczar.DEFAULT_ENCODING);
            }
        }
        return partArr;
    }

    public IKscDecoder getDecoder() {
        return this.mDecoder;
    }

    public IKscTransferListener getListener() {
        return this.mListener;
    }

    public HttpUriRequest getRequest() {
        if (this.mRequest == null) {
            this.mRequest = createHttpRequest();
        }
        return this.mRequest;
    }

    public void setPostEntity(AbstractHttpEntity abstractHttpEntity) {
        checkRequest();
        this.mPostEntity = abstractHttpEntity;
        if (!isFormEntity(abstractHttpEntity)) {
            this.mPostForm.clear();
        }
    }
}
