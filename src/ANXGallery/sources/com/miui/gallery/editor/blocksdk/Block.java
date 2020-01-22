package com.miui.gallery.editor.blocksdk;

public class Block {
    public int mColumn;
    public int mHeight;
    public int mOffset;
    public int mRow;
    public TotalBlockInfo mTotalBlockInfo;
    public int mWidth;

    public static class TotalBlockInfo {
        public int mBlockHeight;
        public int mBlockWidth;
        public int mTotalColumn;
        public int mTotalHeight;
        public int mTotalRow;
        public int mTotalWidth;
    }
}
