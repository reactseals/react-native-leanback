package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class ProgressOverlayCardView extends AbstractCardView {
    public ProgressOverlayCardView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_progress_overlay, this);
    }

    public void updateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        overlayTitleView.setText(card.getOverlayTitle());
        overlaySubtitleView.setText(card.getOverlaySubtitle());
        overlayRemainingTimeView.setText(card.getOverlayRemainingTime());
        setGradientCornerRadius(borderRadius);
        setProgressBar(card);
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}

