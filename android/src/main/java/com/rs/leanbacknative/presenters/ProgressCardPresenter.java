package com.rs.leanbacknative.presenters;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.cardViews.ProgressCardViewView;
import com.rs.leanbacknative.models.Card;

public class ProgressCardPresenter extends AbstractCardPresenter<ProgressCardViewView> {
    public ProgressCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected ProgressCardViewView onCreateView(Context context) {
        ProgressCardViewView cardView = new ProgressCardViewView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, ProgressCardViewView cardView) {
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
