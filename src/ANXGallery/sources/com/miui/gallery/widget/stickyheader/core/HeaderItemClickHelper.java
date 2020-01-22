package com.miui.gallery.widget.stickyheader.core;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;

class HeaderItemClickHelper extends RecyclerView.SimpleOnItemTouchListener {
    private ClickGestureDetector mGestureDetector;

    private static class ClickGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private GestureDetectorCompat mGestureDetector;
        private HeaderClickListener mHeaderClickListener;
        private StickyHeaderDecoration mHeaderDecoration;
        private boolean mIsTapUpConsumed;
        private WeakReference<HeaderViewHolder> mPressedHolderRef;

        public ClickGestureDetector(Context context, StickyHeaderDecoration stickyHeaderDecoration) {
            this.mGestureDetector = new GestureDetectorCompat(context, this);
            this.mHeaderDecoration = stickyHeaderDecoration;
        }

        private void dispatchResetPressState(MotionEvent motionEvent) {
            resetPressState();
        }

        private void dispatchSingleTapUpIfNeeded(MotionEvent motionEvent) {
            if (!this.mIsTapUpConsumed) {
                onSingleTapUp(motionEvent);
            }
        }

        private boolean performItemClick(HeaderViewHolder headerViewHolder) {
            HeaderClickListener headerClickListener = this.mHeaderClickListener;
            if (headerClickListener == null) {
                return false;
            }
            boolean onHeaderClick = headerClickListener.onHeaderClick(headerViewHolder);
            if (onHeaderClick) {
                headerViewHolder.itemView.playSoundEffect(0);
            }
            return onHeaderClick;
        }

        private boolean performItemLongClick(HeaderViewHolder headerViewHolder) {
            HeaderClickListener headerClickListener = this.mHeaderClickListener;
            if (headerClickListener == null) {
                return false;
            }
            boolean onHeaderLongClick = headerClickListener.onHeaderLongClick(headerViewHolder);
            if (onHeaderLongClick) {
                headerViewHolder.itemView.performHapticFeedback(0);
            }
            return onHeaderLongClick;
        }

        private void resetPressState() {
            WeakReference<HeaderViewHolder> weakReference = this.mPressedHolderRef;
            if (weakReference != null) {
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) weakReference.get();
                if (!(headerViewHolder == null || headerViewHolder.itemView == null)) {
                    headerViewHolder.itemView.setPressed(false);
                    this.mPressedHolderRef.clear();
                }
                this.mPressedHolderRef = null;
            }
        }

        public boolean onDown(MotionEvent motionEvent) {
            this.mIsTapUpConsumed = false;
            return this.mHeaderDecoration.findHeaderUnder(motionEvent.getX(), motionEvent.getY()) != null;
        }

        public void onLongPress(MotionEvent motionEvent) {
            HeaderViewHolder findHeaderUnder = this.mHeaderDecoration.findHeaderUnder(motionEvent.getX(), motionEvent.getY());
            if (findHeaderUnder != null && findHeaderUnder.itemView != null && performItemLongClick(findHeaderUnder)) {
                this.mIsTapUpConsumed = true;
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!this.mIsTapUpConsumed) {
                this.mIsTapUpConsumed = true;
            }
            resetPressState();
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
            resetPressState();
            HeaderViewHolder findHeaderUnder = this.mHeaderDecoration.findHeaderUnder(motionEvent.getX(), motionEvent.getY());
            if (findHeaderUnder != null && findHeaderUnder.itemView != null) {
                findHeaderUnder.itemView.setPressed(true);
                this.mPressedHolderRef = new WeakReference<>(findHeaderUnder);
            }
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            HeaderViewHolder findHeaderUnder = this.mHeaderDecoration.findHeaderUnder(motionEvent.getX(), motionEvent.getY());
            if (findHeaderUnder == null || findHeaderUnder.itemView == null) {
                return false;
            }
            boolean performItemClick = performItemClick(findHeaderUnder);
            if (performItemClick) {
                this.mIsTapUpConsumed = true;
            }
            return performItemClick;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
            if (r1 != 3) goto L_0x001d;
         */
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (this.mHeaderClickListener == null) {
                return false;
            }
            boolean onTouchEvent = this.mGestureDetector.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1) {
                dispatchSingleTapUpIfNeeded(motionEvent);
            }
            dispatchResetPressState(motionEvent);
            return onTouchEvent;
        }

        public void setOnHeaderClickListener(HeaderClickListener headerClickListener) {
            this.mHeaderClickListener = headerClickListener;
        }
    }

    interface HeaderClickListener {
        boolean onHeaderClick(HeaderViewHolder headerViewHolder);

        boolean onHeaderLongClick(HeaderViewHolder headerViewHolder);
    }

    public HeaderItemClickHelper(Context context, StickyHeaderDecoration stickyHeaderDecoration) {
        this.mGestureDetector = new ClickGestureDetector(context, stickyHeaderDecoration);
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }

    public void setHeaderClickListener(HeaderClickListener headerClickListener) {
        this.mGestureDetector.setOnHeaderClickListener(headerClickListener);
    }
}
