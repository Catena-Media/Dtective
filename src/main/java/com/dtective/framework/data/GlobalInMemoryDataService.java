package com.dtective.framework.data;

import com.dtective.framework.data.extensions.ConcurrentHashMapStore;
import com.dtective.framework.data.interfaces.IDataProviderService;

public class GlobalInMemoryDataService extends InMemoryDataService implements IDataProviderService {

    private static final Object MUTEX = new Object();

    private static ConcurrentHashMapStore<String, Object> context;

    static {
        if (context == null) {
            synchronized (MUTEX) {
                if (context == null)
                    context = new ConcurrentHashMapStore<>();
            }
        }
    }

    protected ConcurrentHashMapStore getCurrentMap() {
        return context;
    }


}
