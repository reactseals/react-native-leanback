package com.rs.leanbacknative.presenters;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.cardViews.LogoCardView;
import com.rs.leanbacknative.models.Card;

public class LogoCardPresenter extends AbstractCardPresenter<LogoCardView> {
    public LogoCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected LogoCardView onCreateView(Context context) {
        LogoCardView cardView = new LogoCardView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, LogoCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);

        if (!card.getOverlayImageUrl().isEmpty()) {
            Glide.with(cardView.getContext())
                    .load(card.getOverlayImageUrl())
                    .apply(RequestOptions.fitCenterTransform())
                    .into(cardView.getOverlayImageView());
        }

        loadMainImage(cardView.getMainImageView(), card, null);
    }
}
