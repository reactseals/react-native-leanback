package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.cardViews.OverlayCardView;
import com.rs.leanbacknative.models.Card;

public class OverlayCardPresenter extends AbstractCardPresenter<OverlayCardView> {
    public OverlayCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected OverlayCardView onCreateView(Context context) {
        OverlayCardView cardView = new OverlayCardView(context) {
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
    public void onBindViewHolder(Card card, OverlayCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);

        if (!card.getOverlayImageUrl().isEmpty()) {
            Glide.with(cardView.getContext())
                    .load(card.getOverlayImageUrl())
                    .apply(RequestOptions.fitCenterTransform())
                    .into(cardView.getOverlayImageView());
        }

        loadMainImage(cardView.getMainImageView(), card, null);
    }
}
