package com.rs.leanbacknative.managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.rs.leanbacknative.layouts.LeanbackGridLayout;

import java.util.Map;

public class BaseGridManager extends ViewGroupManager<LeanbackGridLayout> {
    private final int COMMAND_REQUEST_FOCUS = 1;
    public static final String REACT_CLASS = "LeanbackNativeGrid";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected LeanbackGridLayout createViewInstance(@NonNull ThemedReactContext reactContext) {
        return null;
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put("onPress", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPress")))
                .put("onFocus", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onFocus")))
                .put("onDataIdsReady", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onDataIdsReady")))
                .build();
    }

    @ReactProp(name = "dataAndAttributes")
    public void setDataAndAttributes(LeanbackGridLayout view, ReadableMap dataAndAttributes) {
        view.setDataAndAttributes(dataAndAttributes);
    }

    @Override
    public void receiveCommand(LeanbackGridLayout view, int commandType, @Nullable ReadableArray args) {
        switch (commandType) {
            case COMMAND_REQUEST_FOCUS:
                view.requestFocus();
                break;
            default:
        }
    }
}
