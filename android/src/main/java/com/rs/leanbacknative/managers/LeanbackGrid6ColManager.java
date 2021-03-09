package com.rs.leanbacknative.managers;

import androidx.leanback.app.VerticalGridFragment;

import com.facebook.react.uimanager.ThemedReactContext;
import com.rs.leanbacknative.layouts.LeanbackGridLayout;

public class LeanbackGrid6ColManager extends BaseGridManager {
    private static final String REACT_CLASS = "LeanbackGrid6Col";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public LeanbackGridLayout createViewInstance(ThemedReactContext context) {
        VerticalGridFragment gridFragment = new VerticalGridFragment();
        LeanbackGridLayout leanbackGridLayout = new LeanbackGridLayout(context, gridFragment, 6);

        addView(leanbackGridLayout, gridFragment.getView(), 0);

        return leanbackGridLayout;
    }
}
