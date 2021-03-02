package com.rs.leanbacknative.presenters;

import android.view.View;
import android.view.ViewGroup;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.utils.Constants;

import androidx.leanback.widget.BaseGridView;
import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ListRowView;

public class RowPresenter extends ListRowPresenter {
    private String mFocusedCardAlignment = Constants.FOCUSED_CARD_ALIGNMENT_LEFT;

    public RowPresenter() {
        super(FocusHighlight.ZOOM_FACTOR_MEDIUM);
    }

    public RowPresenter(String focusedCardAlignment) {
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
        if (mFocusedCardAlignment.equals(Constants.FOCUSED_CARD_ALIGNMENT_CENTER) || mFocusedCardAlignment.equals(Constants.FOCUSED_CARD_ALIGNMENT_LEFT)) {
            gridView.setWindowAlignment(BaseGridView.WINDOW_ALIGN_BOTH_EDGE);
        }
        if (mFocusedCardAlignment.equals(Constants.FOCUSED_CARD_ALIGNMENT_LEANBACK)) {
            gridView.setWindowAlignment(BaseGridView.WINDOW_ALIGN_LOW_EDGE);
        }
        if (mFocusedCardAlignment.equals(Constants.FOCUSED_CARD_ALIGNMENT_LEANBACK) || mFocusedCardAlignment.equals(Constants.FOCUSED_CARD_ALIGNMENT_LEFT)) {
            gridView.setWindowAlignmentOffsetPercent(0.0F);
            gridView.setWindowAlignmentOffset(parent.getResources().getDimensionPixelSize(R.dimen.lb_browse_padding_start));
            gridView.setItemAlignmentOffsetPercent(0.0F);
        }

    }
}
