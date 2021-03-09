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
import com.rs.leanbacknative.utils.Constants;

import java.util.Map;

public class BaseGridManager extends ViewGroupManager<LeanbackGridLayout> {
    private static final String REACT_CLASS = "LeanbackNativeGrid";

    @NonNull
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
                .put(Constants.EVENT_ON_PRESS, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Constants.EVENT_ON_PRESS)))
                .put(Constants.EVENT_ON_FOCUS, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Constants.EVENT_ON_FOCUS)))
                .put(Constants.EVENT_ON_DATA_IDS_READY, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Constants.EVENT_ON_DATA_IDS_READY)))
                .build();
    }

    @ReactProp(name = "dataAndAttributes")
    public void setDataAndAttributes(LeanbackGridLayout view, ReadableMap dataAndAttributes) {
        view.setDataAndAttributes(dataAndAttributes);
    }

    @Override
    public void receiveCommand(@NonNull LeanbackGridLayout view, String commandType, @Nullable ReadableArray args) {
        if (Constants.COMMAND_REQUEST_FOCUS.equals(commandType)) {
            view.requestFocus();
        }
    }
}
