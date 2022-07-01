package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.cardViews.AppCardView;
import com.rs.leanbacknative.models.Card;

public class AppCardPresenter extends AbstractCardPresenter<AppCardView> {
    public AppCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected AppCardView onCreateView(Context context) {
        AppCardView cardView = new AppCardView(context) {
            @Override
            public void setSelected(boolean selected) {
                GradientDrawable border = new GradientDrawable();
                border.setCornerRadius(12);
                border.setColor(Color.TRANSPARENT);
                if (selected) {
                    this.findViewById(R.id.content_stroke).setBackgroundResource(R.drawable.card_stroke);
                    this.findViewById(R.id.gradient).setBackground(border);
                } else {
                    this.findViewById(R.id.content_stroke).setBackgroundResource(0);
                    this.findViewById(R.id.gradient).setBackground(border);
                }

                super.setSelected(selected);
            }
        };
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, AppCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);
        if (card.getId().equals("all-apps")) {
            loadLocalImage(cardView.getMainImageView(), card, R.drawable.icon_apps);
            cardView.setIsServiceCard(true);
        } else if (card.getId().equals("edit")) {
            loadLocalImage(cardView.getMainImageView(), card, R.drawable.icon_edit);
            cardView.setIsServiceCard(true);
        } else {
            loadMainImage(cardView.getMainImageView(), card, null);
        }
    }
}
