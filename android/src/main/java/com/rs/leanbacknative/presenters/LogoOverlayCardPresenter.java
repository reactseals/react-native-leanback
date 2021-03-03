package com.rs.leanbacknative.presenters;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.cardViews.LogoOverlayCardViewView;
import com.rs.leanbacknative.models.Card;

public class LogoOverlayCardPresenter extends AbstractCardPresenter<LogoOverlayCardViewView> {
    public LogoOverlayCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected LogoOverlayCardViewView onCreateView(Context context) {
        LogoOverlayCardViewView cardView = new LogoOverlayCardViewView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, LogoOverlayCardViewView cardView) {
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
