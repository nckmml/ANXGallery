package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;

public final class Iterables {
    public static <T> boolean addAll(Collection<T> collection, Iterable<? extends T> iterable) {
        return iterable instanceof Collection ? collection.addAll(Collections2.cast(iterable)) : Iterators.addAll(collection, ((Iterable) Preconditions.checkNotNull(iterable)).iterator());
    }
}
