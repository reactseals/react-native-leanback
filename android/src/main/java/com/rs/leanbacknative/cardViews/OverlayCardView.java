package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class OverlayCardView extends AbstractCardView {
    public OverlayCardView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_overlay, this);
    }

    public void updateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        overlayTextView.setText(card.getOverlayText());
        setGradientCornerRadius(borderRadius);
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }

    public void colorTextCardUpdateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        getOverlayTextView().setText(card.getOverlayText());
        getGradientView().setBackgroundColor(Color.parseColor(card.getBackgroundColor()));
        setLayoutDimensions(cardWidth, cardHeight);
    }
}

