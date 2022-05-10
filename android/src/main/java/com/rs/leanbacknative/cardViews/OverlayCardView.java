package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

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
        overlayTitleView.setText(card.getOverlayTitle());
        overlayTitleView.setVisibility(View.INVISIBLE);


        overlaySubtitleView.setText(card.getOverlaySubtitle());
        overlaySubtitleView.setVisibility(View.INVISIBLE);


        gradient.setVisibility(View.INVISIBLE);
        setGradientCornerRadius(borderRadius);
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }

    public void colorTextCardUpdateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        getOverlayTitleView().setText(card.getOverlayTitle());
        getOverlaySubtitleView().setText(card.getOverlaySubtitle());
        getOverlayRemainingTimeView().setText(card.getOverlayRemainingTime());
        getGradientView().setBackgroundColor(Color.parseColor(card.getBackgroundColor()));
        setLayoutDimensions(cardWidth, cardHeight);
    }
}

