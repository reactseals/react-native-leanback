package com.rs.leanbacknative;

import androidx.annotation.Nullable;
import androidx.leanback.app.VerticalGridFragment;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.rs.leanbacknative.Layout.LeanbackGridLayout;

import java.util.Map;

public class LeanbackNativeGridManager extends ViewGroupManager<LeanbackGridLayout> {

    public static final String REACT_CLASS = "LeanbackNativeGrid";
    private final String COMMAND_REQUEST_FOCUS = "'requestFocus'";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public LeanbackGridLayout createViewInstance(ThemedReactContext context) {
        VerticalGridFragment gridFragment = new VerticalGridFragment();
        LeanbackGridLayout leanbackGridLayout = new LeanbackGridLayout(context, gridFragment);

        addView(leanbackGridLayout, gridFragment.getView(), 0);

        return leanbackGridLayout;
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put("onClick", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onClick")))
                .put("onFocus", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onFocus")))
                .build();
    }

    @ReactProp(name = "data")
    public void setData(LeanbackGridLayout view, ReadableArray data) {
        view.setData(data);
    }

    @Override
    public void receiveCommand(LeanbackGridLayout view, String commandType, @Nullable ReadableArray args) {
        switch (commandType) {
            case COMMAND_REQUEST_FOCUS:
                view.requestFocus();
                break;
            default:
        }
    }
}
