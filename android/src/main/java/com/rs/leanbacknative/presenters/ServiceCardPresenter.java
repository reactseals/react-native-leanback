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
    public void onBindViewHolder(Card card, ServiceCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);
    }
}
