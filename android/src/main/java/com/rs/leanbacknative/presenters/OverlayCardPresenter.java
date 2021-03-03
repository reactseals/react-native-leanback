package com.rs.leanbacknative.presenters;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.cardViews.OverlayCardViewView;
import com.rs.leanbacknative.models.Card;

public class OverlayCardPresenter extends AbstractCardPresenter<OverlayCardViewView> {
    public OverlayCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected OverlayCardViewView onCreateView(Context context) {
        OverlayCardViewView cardView = new OverlayCardViewView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, OverlayCardViewView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);

        if (!card.getOverlayImageUrl().isEmpty()) {
            Glide.with(cardView.getContext())
                    .load(card.getOverlayImageUrl())
                    .apply(RequestOptions.fitCenterTransform())
                    .into(cardView.getOverlayImageView());
        }

        loadMainImage(cardView.getMainImageView(), card);
    }
}
