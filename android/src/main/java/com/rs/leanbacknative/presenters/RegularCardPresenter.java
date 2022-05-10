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
    public RegularCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected RegularCardView onCreateView(Context context) {
        RegularCardView cardView = new RegularCardView(context) {
            @Override
            public void setSelected(boolean selected) {
                GradientDrawable border = new GradientDrawable();

                if (selected) {
                    border.setColor(Color.TRANSPARENT); //white background
                    border.setStroke(6, Color.WHITE);
                    this.findViewById(R.id.overlay_title).setVisibility(View.VISIBLE);
                    this.findViewById(R.id.overlay_subtitle).setVisibility(View.VISIBLE);
                    this.findViewById(R.id.gradient).setVisibility(View.VISIBLE);

                    this.findViewById(R.id.gradient).setPadding(6, 6, 6,6);

                } else {
                    border.setColor(Color.TRANSPARENT); //white background
                    border.setStroke(0, Color.TRANSPARENT);
                    this.findViewById(R.id.overlay_title).setVisibility(View.GONE);
                    this.findViewById(R.id.overlay_subtitle).setVisibility(View.GONE);
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
    public void onBindViewHolder(Card card, RegularCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);

        loadMainImage(cardView.getMainImageView(), card, null);
    }
}
