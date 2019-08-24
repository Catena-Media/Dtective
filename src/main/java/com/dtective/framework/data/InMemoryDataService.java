package com.dtective.framework.data;

import com.dtective.framework.data.interfaces.IDataProviderService;

import java.util.Set;

public abstract class InMemoryDataService implements IDataProviderService {

    protected abstract IDataProviderService getCurrentMap();

    @Override
    public int size() {
        return getCurrentMap().size();
    }

    @Override
    public boolean isEmpty() {
        return getCurrentMap().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return getCurrentMap().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return getCurrentMap().containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return getCurrentMap().get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return getCurrentMap().put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return getCurrentMap().remove(key);
    }

    @Override
    public void clear() {
        getCurrentMap().clear();
    }

    @Override
    public Set keySet() {
        return getCurrentMap().keySet();
    }

    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        return getCurrentMap().getOrDefault(key, defaultValue);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return getCurrentMap().remove(key, value);
    }

    @Override
    public Object replace(Object key, Object value) {
        return getCurrentMap().replace(key, value);
    }


}
