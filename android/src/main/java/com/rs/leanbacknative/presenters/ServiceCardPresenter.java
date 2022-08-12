package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.cardViews.ServiceCardView;
import com.rs.leanbacknative.models.Card;

public class ServiceCardPresenter extends AbstractCardPresenter<ServiceCardView> {
    public ServiceCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected ServiceCardView onCreateView(Context context) {
        ServiceCardView cardView = new ServiceCardView(context) {
            @Override
            public void setSelected(boolean selected) {
                if (selected) {
                    this.findViewById(R.id.gradient).setVisibility(View.VISIBLE);
                    this.findViewById(R.id.content_stroke).setBackgroundResource(R.drawable.card_stroke);
                } else {
                    this.findViewById(R.id.gradient).setVisibility(View.INVISIBLE);
                    this.findViewById(R.id.content_stroke).setBackgroundResource(0);
                }

                super.setSelected(selected);
            }
        };
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, ServiceCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);
    }
}
