package com.rs.leanbacknative.managers;

import androidx.annotation.NonNull;
import androidx.leanback.app.VerticalGridFragment;

import com.facebook.react.uimanager.ThemedReactContext;
import com.rs.leanbacknative.layouts.LeanbackGridLayout;

public class LeanbackGrid4ColManager extends BaseGridManager {
    private static final String REACT_CLASS = "LeanbackGrid4Col";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    public LeanbackGridLayout createViewInstance(ThemedReactContext context) {
        VerticalGridFragment gridFragment = new VerticalGridFragment();
        LeanbackGridLayout leanbackGridLayout = new LeanbackGridLayout(context, gridFragment, 4);

        addView(leanbackGridLayout, gridFragment.getView(), 0);

        return leanbackGridLayout;
    }
}
