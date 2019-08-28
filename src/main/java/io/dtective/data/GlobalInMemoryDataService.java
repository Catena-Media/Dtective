package io.dtective.data;

import io.dtective.data.extensions.ConcurrentHashMapStore;
import io.dtective.data.interfaces.IDataProviderService;

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
