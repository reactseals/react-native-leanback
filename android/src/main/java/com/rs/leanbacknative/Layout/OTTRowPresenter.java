package com.rs.leanbacknative.Layout;

import android.view.View;
import android.view.ViewGroup;

import com.rs.leanbacknative.R;

import androidx.leanback.widget.BaseGridView;
import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ListRowView;

class OTTRowPresenter extends ListRowPresenter {
    private String mFocusedCardAlignment = "left";

    public OTTRowPresenter() {
        super(FocusHighlight.ZOOM_FACTOR_MEDIUM);
    }

    public OTTRowPresenter(String focusedCardAlignment) {
        super(FocusHighlight.ZOOM_FACTOR_MEDIUM);
        mFocusedCardAlignment = focusedCardAlignment;
    }

    @Override
    protected androidx.leanback.widget.RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        androidx.leanback.widget.RowPresenter.ViewHolder viewHolder = super.createRowViewHolder(parent);

        View view = viewHolder.view;

        HorizontalGridView gridView = ((ListRowView) view).getGridView();

        setFocusedCardAlignment(gridView, parent);

        return viewHolder;

    }


    private void setFocusedCardAlignment(HorizontalGridView gridView, ViewGroup parent) {
        if (mFocusedCardAlignment.equals("center") || mFocusedCardAlignment.equals("left")) {
            gridView.setWindowAlignment(BaseGridView.WINDOW_ALIGN_BOTH_EDGE);
        }
        if (mFocusedCardAlignment.equals("leanback")) {
            gridView.setWindowAlignment(BaseGridView.WINDOW_ALIGN_LOW_EDGE);
        }
        if (mFocusedCardAlignment.equals("leanback") || mFocusedCardAlignment.equals("left")) {
            gridView.setWindowAlignmentOffsetPercent(0.0F);
            gridView.setWindowAlignmentOffset(parent.getResources().getDimensionPixelSize(R.dimen.lb_browse_padding_start));
            gridView.setItemAlignmentOffsetPercent(0.0F);
        }

    }
}
