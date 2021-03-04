package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.models.Card;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.cardViews.DefaultImageCardView;

public class GridCardPresenter extends AbstractCardPresenter<DefaultImageCardView> {
    public GridCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    public GridCardPresenter() {
    }

    private static void updateCardBackgroundColor(DefaultImageCardView view, boolean selected) {
        int color = selected ? sSelectedBackgroundColor : sDefaultBackgroundColor;
        // Both background colors should be set because the view's background is temporarily visible
        // during animations.
        view.setBackgroundColor(color);
        view.findViewById(R.id.info_field).setBackgroundColor(color);

    }

    @Override
    protected DefaultImageCardView onCreateView(Context context) {
        sSelectedBackgroundColor =
                ContextCompat.getColor(context, R.color.selected_background);
        sDefaultBackgroundColor = !mCardShape.equals("round") && mHasImageOnly ? ContextCompat.getColor(context, R.color.default_background) : Color.TRANSPARENT;
        mDefaultCardImage = ContextCompat.getDrawable(context, R.drawable.lb_ic_sad_cloud);

        DefaultImageCardView cardView =
                new DefaultImageCardView(context) {
                    @Override
                    public void setSelected(boolean selected) {
                        if (!mHasImageOnly && !mCardShape.equals("round")) {
                            updateCardBackgroundColor(this, selected);
                        }
                        if (selected) {
                            getTitleView().setVisibility(View.VISIBLE);
                            getContentView().setVisibility(View.VISIBLE);
                        } else {
                            getTitleView().setVisibility(View.INVISIBLE);
                            getContentView().setVisibility(View.INVISIBLE);
                        }
                    }
                };

        cardView.buildImageCardView(mHasImageOnly, mHasTitle, mHasContent, mHasIconRight, mHasIconLeft);
        cardView.setBackgroundColor(sDefaultBackgroundColor);
        if (!mHasImageOnly)  cardView.findViewById(R.id.info_field).setBackgroundColor(sDefaultBackgroundColor);

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, DefaultImageCardView cardView) {
        cardView.setTitleText(card.getTitle());
        cardView.setContentText(card.getDescription());
        cardView.setMainImageDimensions(mCardWidth, mCardHeight);

        RequestOptions requestOptions = mCardShape.equals("round") ? RequestOptions.circleCropTransform() : RequestOptions.fitCenterTransform();

        loadMainImage(cardView.getMainImageView(), card, requestOptions);

        cardView.getTitleView().setVisibility(View.INVISIBLE);
        cardView.getContentView().setVisibility(View.INVISIBLE);
    }

    @Override
    public void onUnbindViewHolder(DefaultImageCardView cardView) {
        cardView.setBadgeImage(null);
        cardView.setMainImage(null);
    }
}
