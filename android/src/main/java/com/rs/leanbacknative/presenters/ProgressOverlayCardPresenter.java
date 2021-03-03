package com.rs.leanbacknative.presenters;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.cardViews.ProgressOverlayCardViewView;
import com.rs.leanbacknative.models.Card;


public class ProgressOverlayCardPresenter extends AbstractCardPresenter<ProgressOverlayCardViewView> {
    public ProgressOverlayCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected ProgressOverlayCardViewView onCreateView(Context context) {
        ProgressOverlayCardViewView cardView = new ProgressOverlayCardViewView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, ProgressOverlayCardViewView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);
        loadMainImage(cardView.getMainImageView(), card);
    }
}
