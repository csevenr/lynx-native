// Copyright 2017 The Lynx Authors. All rights reserved.
package com.lynx.core.base;

import android.support.annotation.Keep;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The properties map of a JavaScript object
 */
public class LynxMap {
    // jni use
    private LynxArray mProperties;
    // for java use
    private Map<Object, Object> mPropertiesMap;

    public LynxMap() {
        mPropertiesMap = new HashMap<>();
    }

    // for jni use
    @Keep
    private void setProperties(LynxArray properties) {
        mProperties = properties;
        for (int i = 0; i < mProperties.length(); i += 2) {
            mPropertiesMap.put(mProperties.get(i), mProperties.get(i + 1));
        }
    }

    // for jni use
    @Keep
    private LynxArray getProperties() {
        return mProperties;
    }

    public Set<Object> getKeySet() {
        return mPropertiesMap.keySet();
    }

    public Object get(Object key) {
        return mPropertiesMap.get(key);
    }

    @Keep
    public void set(Object key, Object value) {
        mPropertiesMap.put(key, value);
        if (mProperties == null) {
            mProperties = new LynxArray();
        }
        mProperties.add(key);
        mProperties.add(value);
    }

}
