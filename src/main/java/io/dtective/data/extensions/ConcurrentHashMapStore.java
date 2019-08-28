package io.dtective.data.extensions;

import io.dtective.data.interfaces.IDataProviderService;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapStore<K, V> extends ConcurrentHashMap implements IDataProviderService {
}
