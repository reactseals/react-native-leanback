package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.cardViews.RegularCardView;
import com.rs.leanbacknative.models.Card;

public class RegularCardPresenter extends AbstractCardPresenter<RegularCardView> {
    private Card mCard;

    public RegularCardPresenter(ReadableMap attributes, Card card) {
        initializeAttributes(attributes);
        mCard = card;
    }

    @Override
    protected RegularCardView onCreateView(Context context) {
        RegularCardView cardView = new RegularCardView(context) {
            @Override
            public void setSelected(boolean selected) {
                GradientDrawable border = new GradientDrawable();
                border.setColor(Color.TRANSPARENT); //white background
                border.setCornerRadius(12);
                if (selected) {
                    this.findViewById(R.id.overlay_title).setVisibility(View.VISIBLE);
                    this.findViewById(R.id.overlay_subtitle).setVisibility(View.VISIBLE);
                    this.findViewById(R.id.gradient).setVisibility(View.VISIBLE);
                    this.findViewById(R.id.content_stroke).setBackgroundResource(R.drawable.card_stroke);
                    if (mCard.getDeleteMode()) {
                        this.findViewById(R.id.overlay_size_title).setVisibility(View.VISIBLE);
                    } else {
                        this.findViewById(R.id.overlay_size_title).setVisibility(View.GONE);
                    }
                } else {
                    this.findViewById(R.id.overlay_title).setVisibility(View.INVISIBLE);
                    this.findViewById(R.id.overlay_subtitle).setVisibility(View.INVISIBLE);
                    this.findViewById(R.id.gradient).setVisibility(View.INVISIBLE);
                    this.findViewById(R.id.content_stroke).setBackgroundResource(0);
                }
                this.findViewById(R.id.gradient).setBackground(border);

                super.setSelected(selected);
            }
        };
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, RegularCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);

        loadMainImage(cardView.getMainImageView(), card, null);
    }
}
