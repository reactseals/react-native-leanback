package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class RegularCardView extends AbstractCardView {
    public RegularCardView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_regular, this);
    }

    public void updateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        overlayTitleView.setText(card.getOverlayTitle());
        overlayTitleView.setVisibility(View.INVISIBLE);

        overlaySubtitleView.setText(card.getOverlaySubtitle());
        overlaySubtitleView.setVisibility(View.INVISIBLE);

        if (card.getDeleteMode()) {
            deleteLayout.setVisibility(View.VISIBLE);
            layout.setAlpha(.5f);
        } else {
            deleteLayout.setVisibility(View.INVISIBLE);
            layout.setAlpha(1f);
        }

        gradient.setVisibility(View.INVISIBLE);
        setGradientCornerRadius(borderRadius);
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }

    public void colorTextCardUpdateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        getOverlayTitleView().setText(card.getOverlayTitle());
        getOverlaySubtitleView().setText(card.getOverlaySubtitle());
        setLayoutDimensions(cardWidth, cardHeight);
    }
}

