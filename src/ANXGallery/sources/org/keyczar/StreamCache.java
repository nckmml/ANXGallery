package org.keyczar;

import java.util.concurrent.ConcurrentHashMap;
import org.keyczar.interfaces.Stream;

class StreamCache<T extends Stream> {
    private final ConcurrentHashMap<KeyczarKey, StreamQueue<T>> cacheMap = new ConcurrentHashMap<>();

    StreamCache() {
    }

    /* access modifiers changed from: package-private */
    public T get(KeyczarKey keyczarKey) {
        return (Stream) getQueue(keyczarKey).poll();
    }

    /* access modifiers changed from: package-private */
    public StreamQueue<T> getQueue(KeyczarKey keyczarKey) {
        StreamQueue<T> streamQueue = this.cacheMap.get(keyczarKey);
        if (streamQueue != null) {
            return streamQueue;
        }
        StreamQueue<T> streamQueue2 = new StreamQueue<>();
        StreamQueue<T> putIfAbsent = this.cacheMap.putIfAbsent(keyczarKey, streamQueue2);
        return putIfAbsent != null ? putIfAbsent : streamQueue2;
    }

    /* access modifiers changed from: package-private */
    public void put(KeyczarKey keyczarKey, T t) {
        getQueue(keyczarKey).add(t);
    }
}
