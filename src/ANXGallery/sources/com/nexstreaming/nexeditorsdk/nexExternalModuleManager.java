package com.nexstreaming.nexeditorsdk;

import com.nexstreaming.nexeditorsdk.module.UserField;
import com.nexstreaming.nexeditorsdk.module.nexExternalExportProvider;
import com.nexstreaming.nexeditorsdk.module.nexFaceDetectionProvider;
import com.nexstreaming.nexeditorsdk.module.nexModuleProvider;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class nexExternalModuleManager {
    private static final String TAG = "nexModuleManager";
    private static final Class[] s_supportedModuleClass = {nexExternalExportProvider.class, nexFaceDetectionProvider.class};
    private static nexExternalModuleManager single;
    private List<a> moduleInfos = new ArrayList();
    private Map<String, Class<? extends nexModuleProvider>> moduleProviders = new HashMap();

    private final class a implements nexModuleProvider {
        private final String b;
        private final String c;
        private final String d;
        private final String e;
        private final String f;
        private final int g;
        private final UserField[] h;
        /* access modifiers changed from: private */
        public final int i;

        private a(nexModuleProvider nexmoduleprovider, int i2) {
            this.b = nexmoduleprovider.name();
            this.c = nexmoduleprovider.uuid();
            this.d = nexmoduleprovider.description();
            this.e = nexmoduleprovider.auth();
            this.g = nexmoduleprovider.version();
            this.h = nexmoduleprovider.userFields();
            this.f = nexmoduleprovider.format();
            this.i = i2;
        }

        public String auth() {
            return this.e;
        }

        public String description() {
            return this.d;
        }

        public String format() {
            return this.f;
        }

        public String name() {
            return this.b;
        }

        public UserField[] userFields() {
            return this.h;
        }

        public String uuid() {
            return this.c;
        }

        public int version() {
            return this.g;
        }
    }

    private nexExternalModuleManager() {
    }

    static nexExternalModuleManager getInstance() {
        if (single == null) {
            single = new nexExternalModuleManager();
        }
        return single;
    }

    private int getSubClassType(Class<? extends nexModuleProvider> cls) {
        int i = 0;
        while (true) {
            Class[] clsArr = s_supportedModuleClass;
            if (i >= clsArr.length) {
                return 0;
            }
            if (clsArr[i].isAssignableFrom(cls)) {
                return i + 1;
            }
            i++;
        }
    }

    private int getSubClassType(Object obj) {
        int i = 0;
        int i2 = 0;
        while (true) {
            Class[] clsArr = s_supportedModuleClass;
            if (i >= clsArr.length) {
                return i2;
            }
            if (clsArr[i].isInstance(obj)) {
                i2 |= i + 1;
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public void clearModule() {
        this.moduleInfos.clear();
        this.moduleProviders.clear();
    }

    /* access modifiers changed from: package-private */
    public Object getModule(String str) {
        Constructor constructor;
        try {
            constructor = this.moduleProviders.get(str).getConstructor(new Class[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            constructor = null;
        }
        if (constructor != null) {
            try {
                return constructor.newInstance(new Object[0]);
            } catch (InstantiationException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Object getModule(String str, Class<? extends nexModuleProvider> cls) {
        int subClassType = getSubClassType(cls);
        for (a next : this.moduleInfos) {
            if (next.name().compareTo(str) == 0 && (next.i & subClassType) != 0) {
                return getModule(next.uuid());
            }
        }
        return null;
    }

    public List<nexModuleProvider> getModules(Class<? extends nexModuleProvider> cls) {
        ArrayList arrayList = new ArrayList();
        int subClassType = getSubClassType(cls);
        for (a next : this.moduleInfos) {
            if ((next.i & subClassType) != 0) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void registerModule(Class<? extends nexModuleProvider> cls) {
        Constructor<? extends nexModuleProvider> constructor;
        try {
            constructor = cls.getConstructor(new Class[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            constructor = null;
        }
        if (constructor != null) {
            try {
                nexModuleProvider nexmoduleprovider = (nexModuleProvider) constructor.newInstance(new Object[0]);
                for (a uuid : this.moduleInfos) {
                    if (uuid.uuid().compareTo(nexmoduleprovider.uuid()) == 0) {
                        return;
                    }
                }
                int subClassType = getSubClassType((Object) nexmoduleprovider);
                if (subClassType != 0) {
                    this.moduleInfos.add(new a(nexmoduleprovider, subClassType));
                    this.moduleProviders.put(nexmoduleprovider.uuid(), cls);
                    return;
                }
                throw new RuntimeException("not supported Provider interface. uuid=" + nexmoduleprovider.uuid());
            } catch (InstantiationException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
    }

    public void registerModule(String str) {
        try {
            registerModule((Class<? extends nexModuleProvider>) Class.forName(str));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void unregisterModule(nexModuleProvider nexmoduleprovider) {
        unregisterModule(nexmoduleprovider.uuid());
    }

    public void unregisterModule(String str) {
        nexModuleProvider nexmoduleprovider;
        Iterator<a> it = this.moduleInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                nexmoduleprovider = null;
                break;
            }
            nexmoduleprovider = it.next();
            if (nexmoduleprovider.uuid().compareTo(str) == 0) {
                break;
            }
        }
        if (nexmoduleprovider != null) {
            this.moduleInfos.remove(nexmoduleprovider);
            this.moduleProviders.remove(str);
        }
    }
}
