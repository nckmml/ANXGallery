package com.miui.gallery.model.repository.config;

import java.util.HashMap;

public class ModelManager {
    private final HashMap<String, Object> mRepositorys;

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static final ModelManager INSTANCE = new ModelManager();
    }

    private ModelManager() {
        this.mRepositorys = new HashMap<>(4);
    }

    public static ModelManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <M> M getModel(Class<M> cls) {
        if (cls != null) {
            String name = cls.getName();
            M m = this.mRepositorys.get(name);
            if (m == null) {
                synchronized (this) {
                    if (m == null) {
                        try {
                            m = ModelConfig.getModelConfigs().get(name).newInstance();
                            this.mRepositorys.put(name, m);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            return m;
        }
        throw new IllegalArgumentException("classzz can't null");
    }
}
