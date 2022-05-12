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
                GradientDrawable border = new GradientDrawable();
                if (selected) {
                    border.setColor(Color.TRANSPARENT);
                    border.setStroke(6, Color.WHITE);
                    border.setCornerRadius(12);
                    this.findViewById(R.id.gradient).setVisibility(View.VISIBLE);
                    this.findViewById(R.id.gradient).setPadding(6, 6, 6,6);
                } else {
                    border.setColor(Color.TRANSPARENT);
                    border.setStroke(0, Color.TRANSPARENT);
                    border.setCornerRadius(12);
                    this.findViewById(R.id.gradient).setVisibility(View.GONE);
                    this.findViewById(R.id.gradient).setPadding(0, 0, 0,0);
                }
                this.findViewById(R.id.gradient).setBackground(border);

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
