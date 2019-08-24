package com.dtective.framework.data.interfaces;

import java.util.Set;

public interface IDataProviderService {

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    Object get(Object key);

    Object put(Object key, Object value);

    Object remove(Object key);

    boolean remove(Object key, Object value);

    Object replace(Object key, Object value);

    void clear();

    Set keySet();

    Object getOrDefault(Object key, Object defaultValue);
}
