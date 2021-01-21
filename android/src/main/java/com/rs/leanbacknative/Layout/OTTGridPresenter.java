package com.rs.leanbacknative.Layout;

import android.view.ViewGroup;

import com.rs.leanbacknative.R;

import androidx.leanback.widget.BaseGridView;
import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.VerticalGridPresenter;
import androidx.leanback.widget.VerticalGridView;

class OTTGridPresenter extends VerticalGridPresenter {

    public OTTGridPresenter() {
        super(FocusHighlight.ZOOM_FACTOR_MEDIUM);
    }

    OTTGridPresenter(boolean useFocusDimmer) {
        super(FocusHighlight.ZOOM_FACTOR_MEDIUM, useFocusDimmer);
    }

    @Override
    protected ViewHolder createGridViewHolder(ViewGroup parent) {
        ViewHolder viewHolder = super.createGridViewHolder(parent);
        VerticalGridView gridView = viewHolder.getGridView();

        gridView.setWindowAlignment(BaseGridView.WINDOW_ALIGN_BOTH_EDGE);
        gridView.setWindowAlignmentOffsetPercent(0.0F);
        gridView.setWindowAlignmentOffset(parent.getResources().getDimensionPixelSize(R.dimen.lb_browse_padding_start));
        gridView.setItemAlignmentOffsetPercent(0.0F);

        return viewHolder;
    }

}
