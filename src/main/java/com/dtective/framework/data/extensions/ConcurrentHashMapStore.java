package com.dtective.framework.data.extensions;

import com.dtective.framework.data.interfaces.IDataProviderService;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapStore<K, V> extends ConcurrentHashMap implements IDataProviderService {
}
