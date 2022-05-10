package com.rs.leanbacknative.presenters;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.cardViews.ContinueWatchingCardView;
import com.rs.leanbacknative.models.Card;


public class ContinueWatchingCardPresenter extends AbstractCardPresenter<ContinueWatchingCardView> {
    public ContinueWatchingCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected ContinueWatchingCardView onCreateView(Context context) {
        ContinueWatchingCardView cardView = new ContinueWatchingCardView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, ContinueWatchingCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);
        loadMainImage(cardView.getMainImageView(), card, null);
    }
}
