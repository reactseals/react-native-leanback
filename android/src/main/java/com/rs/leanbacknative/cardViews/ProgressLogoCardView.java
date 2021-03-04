package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class ProgressLogoCardView extends AbstractCardView {
    public ProgressLogoCardView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_progress_logo, this);
    }

    public void updateUI(Card rowItem, int borderRadius, int cardWidth, int cardHeight) {
        setOverlayImagePosition(rowItem.getOverlayPosition());
        setProgressBar(rowItem);
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }
}

