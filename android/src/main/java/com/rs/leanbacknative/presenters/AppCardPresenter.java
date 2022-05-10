package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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

                if (selected) {
                    border.setColor(Color.TRANSPARENT); //white background
                    border.setStroke(6, Color.WHITE);
                    border.setCornerRadius(12);
                    this.findViewById(R.id.gradient).setBackground(border);
                    this.findViewById(R.id.gradient).setPadding(6, 6, 6,6);
                } else {
                    border.setColor(Color.TRANSPARENT); //white background
                    border.setStroke(0, Color.TRANSPARENT);
                    border.setCornerRadius(12);
                    this.findViewById(R.id.gradient).setBackground(border);
                    this.findViewById(R.id.gradient).setPadding(0, 0, 0,0);
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

        loadMainImage(cardView.getMainImageView(), card, null);
    }
}
