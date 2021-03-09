package com.rs.leanbacknative.managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.leanback.app.RowsFragment;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.rs.leanbacknative.layouts.LeanbackRowLayout;
import com.rs.leanbacknative.utils.Constants;

import java.util.Map;

public class LeanbackRowManager extends ViewGroupManager<LeanbackRowLayout> {
    private static final String REACT_CLASS = "LeanbackRow";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    public LeanbackRowLayout createViewInstance(ThemedReactContext context) {
        RowsFragment rowsFragment = new RowsFragment();
        LeanbackRowLayout leanbackRowsLayout = new LeanbackRowLayout(context, rowsFragment);

        addView(leanbackRowsLayout, rowsFragment.getView(), 0);

        return leanbackRowsLayout;
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
            .put(Constants.EVENT_ON_PRESS, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Constants.EVENT_ON_PRESS)))
            .put(Constants.EVENT_ON_FOCUS, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Constants.EVENT_ON_FOCUS)))
            .put(Constants.EVENT_ON_DATA_IDS_READY, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Constants.EVENT_ON_DATA_IDS_READY)))
            .build();
    }

    @ReactProp(name = "dataAndAttributes")
    public void setDataAndAttributes(LeanbackRowLayout view, ReadableMap dataAndAttributes) {
        view.setDataAndAttributes(dataAndAttributes);
    }

    @ReactProp(name = "title")
    public void setTitle(LeanbackRowLayout view, String title) {
        view.setRowTitle(title);
    }

    @Override
    public void receiveCommand(@NonNull LeanbackRowLayout view, String commandType, @Nullable ReadableArray args) {
        if (Constants.COMMAND_REQUEST_FOCUS.equals(commandType)) {
            view.requestFocus();
        }
    }
}
