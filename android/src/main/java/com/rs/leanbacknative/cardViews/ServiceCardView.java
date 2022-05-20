package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class ServiceCardView extends AbstractCardView {
    public ServiceCardView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_service, this);
    }

    public void updateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        serviceCardTitleView.setText(card.getServiceCardTitle());
        serviceCardImageView.setBackgroundResource(R.drawable.service_icon_delete);
        setGradientCornerRadius(borderRadius);
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}

