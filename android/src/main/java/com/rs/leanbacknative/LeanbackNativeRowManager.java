package com.rs.leanbacknative;

import androidx.annotation.Nullable;
import androidx.leanback.app.RowsFragment;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.rs.leanbacknative.Layout.LeanbackRowLayout;

import java.util.Map;

public class LeanbackNativeRowManager extends ViewGroupManager<LeanbackRowLayout> {

    public static final String REACT_CLASS = "LeanbackNativeRow";
    private final int COMMAND_REQUEST_FOCUS = 1;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public LeanbackRowLayout createViewInstance(ThemedReactContext context) {
        RowsFragment rowsFragment = new RowsFragment();
        LeanbackRowLayout leanbackRowsLayout = new LeanbackRowLayout(context, rowsFragment);

        addView(leanbackRowsLayout, rowsFragment.getView(), 0);

        return leanbackRowsLayout;
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put("onPress", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPress")))
                .put("onFocus", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onFocus")))
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
    public void receiveCommand(LeanbackRowLayout view, int commandType, @Nullable ReadableArray args) {
        switch (commandType) {
            case COMMAND_REQUEST_FOCUS:
                view.requestFocus();
                break;
            default:
        }
    }
}
