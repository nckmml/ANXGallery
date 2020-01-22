package org.jcodec.containers.mp4.boxes;

import androidx.annotation.Keep;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Keep
public class MetaBox extends NodeBox {
    private static final String FOURCC = "meta";

    public MetaBox(Header header) {
        super(header);
    }

    public static MetaBox createMetaBox() {
        return new MetaBox(Header.createHeader(fourcc(), 0));
    }

    public static String fourcc() {
        return FOURCC;
    }

    private DataBox getDataBox(List<Box> list) {
        for (Box next : list) {
            if (next instanceof DataBox) {
                return (DataBox) next;
            }
        }
        return null;
    }

    public Map<String, MetaValue> getKeyedMeta() {
        DataBox dataBox;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        IListBox iListBox = (IListBox) findFirst(this, IListBox.class, IListBox.fourcc());
        MdtaBox[] mdtaBoxArr = (MdtaBox[]) findAllPath(this, MdtaBox.class, new String[]{KeysBox.fourcc(), MdtaBox.fourcc()});
        if (!(iListBox == null || mdtaBoxArr.length == 0)) {
            for (Map.Entry next : iListBox.getValues().entrySet()) {
                Integer num = (Integer) next.getKey();
                if (!(num == null || (dataBox = getDataBox((List) next.getValue())) == null)) {
                    MetaValue createOtherWithLocale = MetaValue.createOtherWithLocale(dataBox.getType(), dataBox.getLocale(), dataBox.getData());
                    if (num.intValue() > 0 && num.intValue() <= mdtaBoxArr.length) {
                        linkedHashMap.put(mdtaBoxArr[num.intValue() - 1].getKey(), createOtherWithLocale);
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public void setKeyedMeta(Map<String, MetaValue> map) {
        if (!map.isEmpty()) {
            KeysBox createKeysBox = KeysBox.createKeysBox();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 1;
            for (Map.Entry next : map.entrySet()) {
                createKeysBox.add(MdtaBox.createMdtaBox((String) next.getKey()));
                MetaValue metaValue = (MetaValue) next.getValue();
                ArrayList arrayList = new ArrayList();
                arrayList.add(DataBox.createDataBox(metaValue.getType(), metaValue.getLocale(), metaValue.getData()));
                linkedHashMap.put(Integer.valueOf(i), arrayList);
                i++;
            }
            IListBox createIListBox = IListBox.createIListBox(linkedHashMap);
            replaceBox(createKeysBox);
            replaceBox(createIListBox);
        }
    }
}
