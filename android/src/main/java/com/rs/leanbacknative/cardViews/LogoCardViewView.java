package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class LogoCardViewView extends AbstractCardView {
    public LogoCardViewView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_logo, this);
    }

    public void updateUI(Card rowItem, int borderRadius, int cardWidth, int cardHeight) {
        setOverlayImagePosition(rowItem.getOverlayPosition());
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }
}

