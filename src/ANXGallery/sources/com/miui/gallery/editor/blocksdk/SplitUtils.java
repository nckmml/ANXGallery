package com.miui.gallery.editor.blocksdk;

import com.miui.gallery.editor.blocksdk.Block;
import com.miui.gallery.util.GLESUtils;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.List;

public class SplitUtils {
    private static int ceil(int i, int i2) {
        return (i / i2) + (i % i2 == 0 ? 0 : 1);
    }

    private static int floor(int i, int i2) {
        int i3 = i % i2;
        return i3 == 0 ? i2 : i3;
    }

    public static final List<Block> split(int i, int i2) {
        return split(i, i2, 1, true, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        r13 = r3 % r12;
     */
    public static List<Block> split(int i, int i2, int i3, boolean z, boolean z2) {
        int i4;
        int i5;
        int i6;
        int i7 = 6000000;
        if (i * i2 <= 6000000) {
            return null;
        }
        Block.TotalBlockInfo totalBlockInfo = new Block.TotalBlockInfo();
        ArrayList arrayList = new ArrayList();
        if (z) {
            int maxTextureSize = GLESUtils.getMaxTextureSize();
            Log.d("SplitUtils", "GL_MAX_TEXTURE_SIZE %d", (Object) Integer.valueOf(maxTextureSize));
            int i8 = i;
            while (i8 > maxTextureSize) {
                i8 = ceil(i8, 2);
            }
            i4 = (i8 >= i || i6 == 0) ? i8 : (i3 - i6) + i8;
        } else {
            i4 = i;
        }
        int ceil = ceil(i, i4);
        int i9 = i4 * i2;
        if (z2) {
            i7 = 12000000;
        }
        int ceil2 = ceil(i2, ceil(i9, i7));
        if (ceil2 < i2 && (i5 = ceil2 % i3) != 0) {
            ceil2 += i3 - i5;
        }
        int ceil3 = ceil(i2, ceil2);
        totalBlockInfo.mTotalHeight = i2;
        totalBlockInfo.mTotalWidth = i;
        totalBlockInfo.mTotalColumn = ceil;
        totalBlockInfo.mTotalRow = ceil3;
        totalBlockInfo.mBlockWidth = i4;
        totalBlockInfo.mBlockHeight = ceil2;
        for (int i10 = 0; i10 < ceil3; i10++) {
            for (int i11 = 0; i11 < ceil; i11++) {
                Block block = new Block();
                if (i11 == 0) {
                    block.mWidth = floor(i, i4);
                } else {
                    block.mWidth = i4;
                }
                if (i10 == 0) {
                    block.mHeight = floor(i2, ceil2);
                } else {
                    block.mHeight = ceil2;
                }
                block.mRow = i10;
                block.mColumn = i11;
                if (block.mColumn > 0) {
                    block.mOffset += ((Block) arrayList.get(0)).mWidth + ((block.mColumn - 1) * i4);
                }
                if (block.mRow > 0) {
                    block.mOffset += (((Block) arrayList.get(0)).mHeight + ((block.mRow - 1) * ceil2)) * i;
                }
                block.mTotalBlockInfo = totalBlockInfo;
                arrayList.add(block);
            }
        }
        return arrayList;
    }
}
