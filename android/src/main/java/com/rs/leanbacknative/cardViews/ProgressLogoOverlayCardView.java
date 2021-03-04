package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class ProgressLogoOverlayCardView extends AbstractCardView {
    public ProgressLogoOverlayCardView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_progress_logo_overlay, this);
    }

    public void updateUI(Card rowItem, int borderRadius, int cardWidth, int cardHeight) {
        overlayTextView.setText(rowItem.getOverlayText());
        setGradientCornerRadius(borderRadius);
        setOverlayImagePosition(rowItem.getOverlayPosition());
        setProgressBar(rowItem);
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        gradient.setVisibility(View.INVISIBLE);
        overlayTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        gradient.setVisibility(View.VISIBLE);
        overlayTextView.setVisibility(View.VISIBLE);
    }
}

