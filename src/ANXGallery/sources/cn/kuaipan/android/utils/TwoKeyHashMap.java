package cn.kuaipan.android.utils;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class TwoKeyHashMap<E, K, V> extends AbstractMap<String, V> {
    /* access modifiers changed from: private */
    public Entry<E, K, V>[] arr;
    private int arrSize;
    private Set<Map.Entry<String, V>> entrySet;
    private float loadFactor;
    /* access modifiers changed from: private */
    public int modCount;
    /* access modifiers changed from: private */
    public int size;
    int threshold;
    private Collection<V> values;

    public static class Entry<E, K, V> implements Map.Entry<String, V> {
        int hash;
        E key1;
        K key2;
        Entry<E, K, V> next;
        V value;

        public Entry(int i, E e, K k, V v, Entry<E, K, V> entry) {
            this.hash = i;
            this.key1 = e;
            this.key2 = k;
            this.value = v;
            this.next = entry;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key12 = entry.getKey1();
            Object key22 = entry.getKey2();
            Object value2 = entry.getValue();
            return (this.key1 != null || key12 == null) && (this.key2 != null || key22 == null) && ((this.value != null || value2 == null) && this.key1.equals(entry.getKey1()) && this.key2.equals(entry.getKey2()) && this.value.equals(value2));
        }

        public String getKey() {
            return this.key1.toString() + this.key2.toString();
        }

        public E getKey1() {
            return this.key1;
        }

        public K getKey2() {
            return this.key2;
        }

        public V getValue() {
            return this.value;
        }

        public int hashCode() {
            E e = this.key1;
            int i = 0;
            int hashCode = e == null ? 0 : e.hashCode();
            K k = this.key2;
            int hashCode2 = hashCode + (k == null ? 0 : k.hashCode());
            V v = this.value;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode2 ^ i;
        }

        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }
    }

    class EntryIteratorImpl implements Iterator<Map.Entry<String, V>> {
        private int curr = -1;
        private Entry<E, K, V> curr_entry;
        private boolean found;
        private Entry<E, K, V> returned_entry;
        private int returned_index = -1;
        private int startModCount;

        EntryIteratorImpl() {
            this.startModCount = TwoKeyHashMap.this.modCount;
        }

        public boolean hasNext() {
            boolean z = true;
            if (this.found) {
                return true;
            }
            Entry<E, K, V> entry = this.curr_entry;
            if (entry != null) {
                this.curr_entry = entry.next;
            }
            if (this.curr_entry == null) {
                this.curr++;
                while (this.curr < TwoKeyHashMap.this.arr.length) {
                    Entry[] access$400 = TwoKeyHashMap.this.arr;
                    int i = this.curr;
                    if (access$400[i] != null) {
                        break;
                    }
                    this.curr = i + 1;
                }
                if (this.curr < TwoKeyHashMap.this.arr.length) {
                    this.curr_entry = TwoKeyHashMap.this.arr[this.curr];
                }
            }
            if (this.curr_entry == null) {
                z = false;
            }
            this.found = z;
            return z;
        }

        public Map.Entry<String, V> next() {
            if (TwoKeyHashMap.this.modCount != this.startModCount) {
                throw new ConcurrentModificationException();
            } else if (hasNext()) {
                this.found = false;
                this.returned_index = this.curr;
                Entry<E, K, V> entry = this.curr_entry;
                this.returned_entry = entry;
                return entry;
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            Entry<E, K, V> entry;
            Entry<E, K, V> entry2;
            if (this.returned_index == -1) {
                throw new IllegalStateException();
            } else if (TwoKeyHashMap.this.modCount == this.startModCount) {
                Entry<E, K, V> entry3 = null;
                Entry<E, K, V> entry4 = TwoKeyHashMap.this.arr[this.returned_index];
                while (true) {
                    Entry<E, K, V> entry5 = entry4;
                    entry = entry3;
                    entry3 = entry5;
                    entry2 = this.returned_entry;
                    if (entry3 == entry2) {
                        break;
                    }
                    entry4 = entry3.next;
                }
                if (entry != null) {
                    entry.next = entry2.next;
                } else {
                    TwoKeyHashMap.this.arr[this.returned_index] = this.returned_entry.next;
                }
                TwoKeyHashMap.access$010(TwoKeyHashMap.this);
                TwoKeyHashMap.access$308(TwoKeyHashMap.this);
                this.startModCount++;
                this.returned_index = -1;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    class EntrySetImpl extends AbstractSet<Map.Entry<String, V>> {
        EntrySetImpl() {
        }

        public void clear() {
            TwoKeyHashMap.this.clear();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Entry access$100 = TwoKeyHashMap.this.findEntry(entry.getKey1(), entry.getKey2());
            if (access$100 == null) {
                return false;
            }
            Object value = entry.getValue();
            Object value2 = access$100.getValue();
            return value == null ? value2 == null : value.equals(value2);
        }

        public boolean isEmpty() {
            return TwoKeyHashMap.this.size == 0;
        }

        public Iterator<Map.Entry<String, V>> iterator() {
            return TwoKeyHashMap.this.createEntrySetIterator();
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return TwoKeyHashMap.this.removeEntry(entry.getKey1(), entry.getKey2()) != null;
        }

        public int size() {
            return TwoKeyHashMap.this.size;
        }
    }

    class ValueIteratorImpl implements Iterator<V> {
        private TwoKeyHashMap<E, K, V>.EntryIteratorImpl itr;

        ValueIteratorImpl() {
            this.itr = new EntryIteratorImpl();
        }

        public boolean hasNext() {
            return this.itr.hasNext();
        }

        public V next() {
            return this.itr.next().getValue();
        }

        public void remove() {
            this.itr.remove();
        }
    }

    class ValuesCollectionImpl extends AbstractCollection<V> {
        ValuesCollectionImpl() {
        }

        public void clear() {
            TwoKeyHashMap.this.clear();
        }

        public boolean contains(Object obj) {
            return TwoKeyHashMap.this.containsValue(obj);
        }

        public boolean isEmpty() {
            return TwoKeyHashMap.this.size == 0;
        }

        public Iterator<V> iterator() {
            return TwoKeyHashMap.this.createValueCollectionIterator();
        }

        public int size() {
            return TwoKeyHashMap.this.size;
        }
    }

    public TwoKeyHashMap() {
        this(16, 0.75f);
    }

    public TwoKeyHashMap(int i, float f) {
        this.threshold = 0;
        if (i < 0) {
            throw new IllegalArgumentException("initialCapacity should be >= 0");
        } else if (f > 0.0f) {
            this.loadFactor = f;
            i = i == Integer.MAX_VALUE ? i - 1 : i;
            this.arrSize = i <= 0 ? 1 : i;
            int i2 = this.arrSize;
            this.threshold = (int) (((float) i2) * this.loadFactor);
            this.arr = new Entry[(i2 + 1)];
        } else {
            throw new IllegalArgumentException("initialLoadFactor should be > 0");
        }
    }

    static /* synthetic */ int access$010(TwoKeyHashMap twoKeyHashMap) {
        int i = twoKeyHashMap.size;
        twoKeyHashMap.size = i - 1;
        return i;
    }

    static /* synthetic */ int access$308(TwoKeyHashMap twoKeyHashMap) {
        int i = twoKeyHashMap.modCount;
        twoKeyHashMap.modCount = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    public final Entry<E, K, V> findEntry(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return this.arr[this.arrSize];
        }
        int hashCode = obj.hashCode() + obj2.hashCode();
        for (Entry<E, K, V> entry = this.arr[(Integer.MAX_VALUE & hashCode) % this.arrSize]; entry != null; entry = entry.next) {
            if (hashCode == entry.hash && obj.equals(entry.getKey1()) && obj2.equals(entry.getKey2())) {
                return entry;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final Entry<E, K, V> removeEntry(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            int i = this.arrSize;
            Entry<E, K, V>[] entryArr = this.arr;
            if (entryArr[i] == null) {
                return null;
            }
            Entry<E, K, V> entry = entryArr[i];
            entryArr[i] = null;
            this.size--;
            this.modCount++;
            return entry;
        }
        int hashCode = obj.hashCode() + obj2.hashCode();
        int i2 = (Integer.MAX_VALUE & hashCode) % this.arrSize;
        Entry<E, K, V> entry2 = this.arr[i2];
        Entry<E, K, V> entry3 = entry2;
        while (entry2 != null) {
            if (hashCode != entry2.hash || !obj.equals(entry2.getKey1()) || !obj2.equals(entry2.getKey2())) {
                entry3 = entry2;
                entry2 = entry2.next;
            } else {
                if (entry3 == entry2) {
                    this.arr[i2] = entry2.next;
                } else {
                    entry3.next = entry2.next;
                }
                this.size--;
                this.modCount++;
                return entry2;
            }
        }
        return null;
    }

    public void clear() {
        this.modCount++;
        this.size = 0;
        Entry<E, K, V>[] entryArr = this.arr;
        Arrays.fill(entryArr, 0, entryArr.length, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public Entry<E, K, V> createEntry(int i, E e, K k, V v, Entry<E, K, V> entry) {
        return new Entry(i, e, k, v, entry);
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<String, V>> createEntrySetIterator() {
        return new EntryIteratorImpl();
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> createValueCollectionIterator() {
        return new ValueIteratorImpl();
    }

    public Set<Map.Entry<String, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySetImpl();
        }
        return this.entrySet;
    }

    public V get(Object obj, Object obj2) {
        Entry findEntry = findEntry(obj, obj2);
        if (findEntry != null) {
            return findEntry.value;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public V put(E e, K k, V v) {
        if (e == null && k == null) {
            int i = this.arrSize;
            Entry<E, K, V>[] entryArr = this.arr;
            if (entryArr[i] == null) {
                entryArr[i] = createEntry(0, (Object) null, (Object) null, v, (Entry) null);
                this.size++;
                this.modCount++;
                return null;
            }
            V v2 = entryArr[i].value;
            this.arr[i].value = v;
            return v2;
        }
        int hashCode = e.hashCode() + k.hashCode();
        int i2 = (Integer.MAX_VALUE & hashCode) % this.arrSize;
        Entry<E, K, V> entry = this.arr[i2];
        while (entry != null) {
            if (hashCode != entry.hash || !e.equals(entry.getKey1()) || !k.equals(entry.getKey2())) {
                entry = entry.next;
            } else {
                V v3 = entry.value;
                entry.value = v;
                return v3;
            }
        }
        Entry<E, K, V>[] entryArr2 = this.arr;
        entryArr2[i2] = createEntry(hashCode, e, k, v, entryArr2[i2]);
        this.size++;
        this.modCount++;
        if (this.size > this.threshold) {
            rehash();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void rehash() {
        Entry<E, K, V>[] entryArr;
        int i = ((this.arrSize + 1) * 2) + 1;
        if (i < 0) {
            i = 2147483646;
        }
        Entry<E, K, V>[] entryArr2 = new Entry[(i + 1)];
        int i2 = 0;
        while (true) {
            entryArr = this.arr;
            if (i2 >= entryArr.length - 1) {
                break;
            }
            Entry<E, K, V> entry = entryArr[i2];
            while (entry != null) {
                Entry<E, K, V> entry2 = entry.next;
                int i3 = (entry.hash & Reader.READ_DONE) % i;
                entry.next = entryArr2[i3];
                entryArr2[i3] = entry;
                entry = entry2;
            }
            i2++;
        }
        entryArr2[i] = entryArr[this.arrSize];
        this.arrSize = i;
        if (this.arrSize == Integer.MAX_VALUE) {
            this.loadFactor *= 10.0f;
        }
        this.threshold = (int) (((float) this.arrSize) * this.loadFactor);
        this.arr = entryArr2;
    }

    public int size() {
        return this.size;
    }

    public Collection<V> values() {
        if (this.values == null) {
            this.values = new ValuesCollectionImpl();
        }
        return this.values;
    }
}
