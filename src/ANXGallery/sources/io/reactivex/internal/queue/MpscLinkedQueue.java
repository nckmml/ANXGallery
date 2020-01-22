package io.reactivex.internal.queue;

import io.reactivex.internal.fuseable.SimplePlainQueue;
import java.util.concurrent.atomic.AtomicReference;

public final class MpscLinkedQueue<T> implements SimplePlainQueue<T> {
    private final AtomicReference<LinkedQueueNode<T>> consumerNode = new AtomicReference<>();
    private final AtomicReference<LinkedQueueNode<T>> producerNode = new AtomicReference<>();

    static final class LinkedQueueNode<E> extends AtomicReference<LinkedQueueNode<E>> {
        private static final long serialVersionUID = 2404266111789071508L;
        private E value;

        LinkedQueueNode() {
        }

        LinkedQueueNode(E e) {
            spValue(e);
        }

        public E getAndNullValue() {
            E lpValue = lpValue();
            spValue((Object) null);
            return lpValue;
        }

        public E lpValue() {
            return this.value;
        }

        public LinkedQueueNode<E> lvNext() {
            return (LinkedQueueNode) get();
        }

        public void soNext(LinkedQueueNode<E> linkedQueueNode) {
            lazySet(linkedQueueNode);
        }

        public void spValue(E e) {
            this.value = e;
        }
    }

    public MpscLinkedQueue() {
        LinkedQueueNode linkedQueueNode = new LinkedQueueNode();
        spConsumerNode(linkedQueueNode);
        xchgProducerNode(linkedQueueNode);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000a, LOOP_START, MTH_ENTER_BLOCK] */
    public void clear() {
        while (poll() != null && !isEmpty()) {
        }
    }

    public boolean isEmpty() {
        return lvConsumerNode() == lvProducerNode();
    }

    /* access modifiers changed from: package-private */
    public LinkedQueueNode<T> lpConsumerNode() {
        return this.consumerNode.get();
    }

    /* access modifiers changed from: package-private */
    public LinkedQueueNode<T> lvConsumerNode() {
        return this.consumerNode.get();
    }

    /* access modifiers changed from: package-private */
    public LinkedQueueNode<T> lvProducerNode() {
        return this.producerNode.get();
    }

    public boolean offer(T t) {
        if (t != null) {
            LinkedQueueNode linkedQueueNode = new LinkedQueueNode(t);
            xchgProducerNode(linkedQueueNode).soNext(linkedQueueNode);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    public T poll() {
        LinkedQueueNode lvNext;
        LinkedQueueNode lpConsumerNode = lpConsumerNode();
        LinkedQueueNode lvNext2 = lpConsumerNode.lvNext();
        if (lvNext2 != null) {
            T andNullValue = lvNext2.getAndNullValue();
            spConsumerNode(lvNext2);
            return andNullValue;
        } else if (lpConsumerNode == lvProducerNode()) {
            return null;
        } else {
            do {
                lvNext = lpConsumerNode.lvNext();
            } while (lvNext == null);
            T andNullValue2 = lvNext.getAndNullValue();
            spConsumerNode(lvNext);
            return andNullValue2;
        }
    }

    /* access modifiers changed from: package-private */
    public void spConsumerNode(LinkedQueueNode<T> linkedQueueNode) {
        this.consumerNode.lazySet(linkedQueueNode);
    }

    /* access modifiers changed from: package-private */
    public LinkedQueueNode<T> xchgProducerNode(LinkedQueueNode<T> linkedQueueNode) {
        return this.producerNode.getAndSet(linkedQueueNode);
    }
}
