package com.rs.leanbacknative;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.rs.leanbacknative.managers.LeanbackGrid4ColManager;
import com.rs.leanbacknative.managers.LeanbackGrid5ColManager;
import com.rs.leanbacknative.managers.LeanbackGrid6ColManager;
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
                new LeanbackGrid4ColManager(),
                new LeanbackGrid5ColManager(),
                new LeanbackGrid6ColManager()
        );
    }
}
