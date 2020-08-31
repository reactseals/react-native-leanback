package com.rs.leanbacknative.Layout;


import android.view.View;
import android.view.ViewGroup;

import com.rs.leanbacknative.R;

import androidx.leanback.widget.BaseGridView;
import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ListRowView;
import androidx.leanback.widget.RowPresenter;

class FocusedItemAtStartListRowPresenter extends ListRowPresenter {
    public FocusedItemAtStartListRowPresenter(int focusZoomFactor) {
        super(focusZoomFactor);
    }

    public FocusedItemAtStartListRowPresenter() {
        super(FocusHighlight.ZOOM_FACTOR_MEDIUM);
    }

    @Override
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        RowPresenter.ViewHolder viewHolder = super.createRowViewHolder(parent);

        View view = viewHolder.view;

        HorizontalGridView gridView = ((ListRowView) view).getGridView();
        gridView.setWindowAlignment(BaseGridView.WINDOW_ALIGN_BOTH_EDGE);
        gridView.setWindowAlignmentOffsetPercent(0.0F);
        gridView.setWindowAlignmentOffset(parent.getResources().getDimensionPixelSize(R.dimen.lb_browse_padding_start));
        gridView.setItemAlignmentOffsetPercent(0.0F);
        return viewHolder;

    }
}
