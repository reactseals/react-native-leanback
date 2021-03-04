package com.rs.leanbacknative.presenters;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.cardViews.ProgressOverlayCardView;
import com.rs.leanbacknative.models.Card;


public class ProgressOverlayCardPresenter extends AbstractCardPresenter<ProgressOverlayCardView> {
    public ProgressOverlayCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected ProgressOverlayCardView onCreateView(Context context) {
        ProgressOverlayCardView cardView = new ProgressOverlayCardView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, ProgressOverlayCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);
        loadMainImage(cardView.getMainImageView(), card);
    }
}
