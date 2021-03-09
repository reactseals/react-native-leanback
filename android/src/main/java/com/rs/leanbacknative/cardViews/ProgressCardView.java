package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class ProgressCardView extends AbstractCardView {
    public ProgressCardView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_progress, this);
    }

    public void updateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        setProgressBar(card);
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }
}

