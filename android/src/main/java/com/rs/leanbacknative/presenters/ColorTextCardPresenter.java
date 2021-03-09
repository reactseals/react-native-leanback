package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.Color;

import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.cardViews.OverlayCardView;
import com.rs.leanbacknative.models.Card;

public class ColorTextCardPresenter extends AbstractCardPresenter<OverlayCardView> {
    public ColorTextCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected OverlayCardView onCreateView(Context context) {
        OverlayCardView cardView = new OverlayCardView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, OverlayCardView cardView) {
        cardView.colorTextCardUpdateUI(card, mBorderRadius, mCardWidth, mCardHeight);
    }
}
