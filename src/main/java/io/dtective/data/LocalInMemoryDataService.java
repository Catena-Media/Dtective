package io.dtective.data;

import io.dtective.data.extensions.HashMapStore;
import io.dtective.data.interfaces.IDataProviderService;

import java.util.concurrent.ConcurrentHashMap;

public class LocalInMemoryDataService extends InMemoryDataService {

    private static final Object MUTEX = new Object();

    private static ConcurrentHashMap<String, HashMapStore<String, Object>> context;

    static {
        if (context == null) {
            synchronized (MUTEX) {
                if (context == null)
                    context = new ConcurrentHashMap<>();
            }
        }
    }

    protected IDataProviderService getCurrentMap() {
        if (!context.containsKey(Thread.currentThread().getName())) {
            context.put(Thread.currentThread().getName(), new HashMapStore<>());
        }
        return context.get(Thread.currentThread().getName());
    }
}
