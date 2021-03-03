package com.rs.leanbacknative.managers;

import androidx.leanback.app.VerticalGridFragment;

import com.facebook.react.uimanager.ThemedReactContext;
import com.rs.leanbacknative.layouts.LeanbackGridLayout;

public class LeanbackGrid5ColManager extends BaseGridManager {
    private static final String REACT_CLASS = "LeanbackGrid5Col";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public LeanbackGridLayout createViewInstance(ThemedReactContext context) {
        VerticalGridFragment gridFragment = new VerticalGridFragment();
        LeanbackGridLayout leanbackGridLayout = new LeanbackGridLayout(context, gridFragment, 5);

        addView(leanbackGridLayout, gridFragment.getView(), 0);

        return leanbackGridLayout;
    }
}
