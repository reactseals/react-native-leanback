package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class ProgressLogoOverlayCardView extends AbstractCardView {
    public ProgressLogoOverlayCardView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_progress_logo_overlay, this);
    }

    public void updateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        overlayTextView.setText(card.getOverlayText());
        setGradientCornerRadius(borderRadius);
        setOverlayImagePosition(card.getOverlayPosition());
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

