package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class OverlayCardViewView extends AbstractCardView {
    public OverlayCardViewView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_overlay, this);
    }

    public void updateUI(Card rowItem, int borderRadius, int cardWidth, int cardHeight) {
        overlayTextView.setText(rowItem.getOverlayText());
        setGradientCornerRadius(borderRadius);
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }
}

