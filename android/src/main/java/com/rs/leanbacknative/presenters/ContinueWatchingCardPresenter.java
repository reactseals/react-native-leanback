package com.rs.leanbacknative.presenters;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.cardViews.ContinueWatchingCardView;
import com.rs.leanbacknative.models.Card;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class ContinueWatchingCardPresenter extends AbstractCardPresenter<ContinueWatchingCardView> {
    public ContinueWatchingCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected ContinueWatchingCardView onCreateView(Context context) {
        ContinueWatchingCardView cardView = new ContinueWatchingCardView(context) {
            @Override
            public void setSelected(boolean selected) {
                if (selected) {
                    this.findViewById(R.id.content_stroke).setBackgroundResource(R.drawable.card_stroke);
                    this.findViewById(R.id.content_container).setScaleX((float) 0.99);
                    this.findViewById(R.id.content_container).setScaleY((float) 0.925);

                } else {
                    this.findViewById(R.id.content_stroke).setBackgroundResource(0);
                    this.findViewById(R.id.content_container).setScaleX((float) 1);
                    this.findViewById(R.id.content_container).setScaleY((float) 1);
                }
                super.setSelected(selected);
            }
        };
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, ContinueWatchingCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);
        loadMainImage(cardView.getMainImageView(), card, null);
    }
}
