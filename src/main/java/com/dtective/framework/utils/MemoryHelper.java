package com.dtective.framework.utils;

import com.dtective.framework.test.TestDataCore;

public class MemoryHelper {

    public static void add(String id, Object value) {
        TestDataCore.addToDataStore(id, value);
    }

    public static Object get(String id) {
        return TestDataCore.getDataStore(id);
    }
}
