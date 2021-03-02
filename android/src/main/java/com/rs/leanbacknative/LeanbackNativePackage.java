package com.rs.leanbacknative;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.rs.leanbacknative.managers.LeanbackGrid4Col;
import com.rs.leanbacknative.managers.LeanbackGrid5Col;
import com.rs.leanbacknative.managers.LeanbackGrid6Col;
import com.rs.leanbacknative.managers.LeanbackRowManager;

public class LeanbackNativePackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new LeanbackRowManager(),
                new LeanbackGrid4Col(),
                new LeanbackGrid5Col(),
                new LeanbackGrid6Col()
        );
    }
}
