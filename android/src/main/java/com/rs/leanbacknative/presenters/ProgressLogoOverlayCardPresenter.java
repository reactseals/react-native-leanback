package com.rs.leanbacknative.presenters;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.models.Card;
import com.rs.leanbacknative.cardViews.ProgressLogoOverlayCardViewView;

public class ProgressLogoOverlayCardPresenter extends AbstractCardPresenter<ProgressLogoOverlayCardViewView> {
    public ProgressLogoOverlayCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected ProgressLogoOverlayCardViewView onCreateView(Context context) {
        ProgressLogoOverlayCardViewView cardView = new ProgressLogoOverlayCardViewView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, ProgressLogoOverlayCardViewView cardView) {
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
