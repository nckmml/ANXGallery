package com.nexstreaming.app.common.norm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: NormTableInfo */
public class c {
    private static Map<Class<? extends b>, c> g = new HashMap();
    private final String[] a;
    private final String[] b;
    private final NormColumnInfo[] c;
    private final String d;
    private final String[] e;
    private final NormColumnInfo f;

    private c(Class<? extends b> cls) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        this.d = a.camelCaseToLCUnderscore(cls.getSimpleName());
        sb.append("CREATE TABLE ");
        sb.append(this.d);
        sb.append(" (\n    ");
        sb2.append("DROP TABLE IF EXISTS ");
        sb2.append(this.d);
        Field[] declaredFields = cls.getDeclaredFields();
        int i = 0;
        for (Field a2 : declaredFields) {
            if (a(a2)) {
                i++;
            }
        }
        this.c = new NormColumnInfo[(declaredFields.length - i)];
        this.e = new String[(declaredFields.length - i)];
        NormColumnInfo normColumnInfo = null;
        int i2 = -1;
        int i3 = 0;
        String[] strArr = null;
        String[] strArr2 = null;
        for (Field field : declaredFields) {
            if (!a(field)) {
                i2++;
                NormColumnInfo normColumnInfo2 = new NormColumnInfo(field);
                this.c[i2] = normColumnInfo2;
                this.e[i2] = normColumnInfo2.a;
                if (normColumnInfo2.h) {
                    if (strArr == null) {
                        strArr = new String[declaredFields.length];
                        strArr2 = new String[declaredFields.length];
                    }
                    strArr[i3] = "CREATE INDEX idx_" + normColumnInfo2.a + " ON " + this.d + "( " + normColumnInfo2.a + ")";
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("DROP INDEX IF EXISTS idx_");
                    sb3.append(normColumnInfo2.a);
                    strArr2[i3] = sb3.toString();
                    i3++;
                }
                normColumnInfo = normColumnInfo2.g ? normColumnInfo2 : normColumnInfo;
                if (i2 > 0) {
                    sb.append(",\n    ");
                }
                sb.append(normColumnInfo2.a);
                sb.append(' ');
                switch (normColumnInfo2.c) {
                    case INT:
                    case LONG:
                    case BOOL:
                        if (!normColumnInfo2.g) {
                            sb.append("INTEGER");
                            break;
                        } else {
                            sb.append("INTEGER PRIMARY KEY");
                            break;
                        }
                    case DOUBLE:
                    case FLOAT:
                        sb.append("REAL");
                        break;
                    case ENUM:
                    case TEXT:
                    case JSON:
                        sb.append("TEXT");
                        break;
                    case BLOB:
                        sb.append("BLOB");
                        break;
                }
                if (normColumnInfo2.d) {
                    sb.append(" UNIQUE");
                }
                if (normColumnInfo2.e) {
                    sb.append(" NOT NULL");
                }
            }
        }
        this.f = normColumnInfo;
        sb.append(')');
        int i4 = i3 + 1;
        this.a = new String[i4];
        this.a[0] = sb.toString();
        if (i3 > 0) {
            System.arraycopy(strArr, 0, this.a, 1, i3);
        }
        this.b = new String[i4];
        this.b[i3] = sb2.toString();
        if (i3 > 0) {
            System.arraycopy(strArr2, 0, this.b, 0, i3);
        }
    }

    public static c a(Class<? extends b> cls) {
        c cVar = g.get(cls);
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(cls);
        g.put(cls, cVar2);
        return cVar2;
    }

    private boolean a(Field field) {
        if (field.isSynthetic()) {
            return true;
        }
        String name = field.getName();
        return name.startsWith("$") || name.equals("serialVersionUID");
    }

    public NormColumnInfo a(String str) {
        for (NormColumnInfo normColumnInfo : this.c) {
            if (normColumnInfo.a.equals(str)) {
                return normColumnInfo;
            }
        }
        return null;
    }

    public String[] a() {
        return this.a;
    }

    public String[] a(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            NormColumnInfo[] normColumnInfoArr = this.c;
            if (i4 >= normColumnInfoArr.length) {
                break;
            }
            if (normColumnInfoArr[i4].i > i) {
                arrayList.add(Integer.valueOf(i4));
            }
            i4++;
        }
        if (arrayList.size() == 0) {
            return new String[0];
        }
        String[] strArr = new String[arrayList.size()];
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            NormColumnInfo normColumnInfo = this.c[((Integer) it.next()).intValue()];
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE ");
            sb.append(this.d);
            sb.append(" ADD COLUMN ");
            sb.append(normColumnInfo.a);
            sb.append(" ");
            switch (normColumnInfo.c) {
                case INT:
                case LONG:
                case BOOL:
                    if (!normColumnInfo.g) {
                        sb.append("INTEGER");
                        break;
                    } else {
                        sb.append("INTEGER PRIMARY KEY");
                        break;
                    }
                case DOUBLE:
                case FLOAT:
                    sb.append("REAL");
                    break;
                case ENUM:
                case TEXT:
                case JSON:
                    sb.append("TEXT");
                    break;
                case BLOB:
                    sb.append("BLOB");
                    break;
            }
            strArr[i3] = sb.toString();
            sb.reverse();
            i3++;
        }
        return strArr;
    }

    public String[] b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public String[] d() {
        return this.e;
    }

    public NormColumnInfo[] e() {
        return this.c;
    }

    public NormColumnInfo f() {
        return this.f;
    }
}
