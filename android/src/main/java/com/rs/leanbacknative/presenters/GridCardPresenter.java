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
import com.rs.leanbacknative.utils.Constants;

public class GridCardPresenter extends AbstractCardPresenter<DefaultImageCardView> {
    private Card mCard;

    public GridCardPresenter(ReadableMap attributes, Card card) {
        initializeAttributes(attributes);
        mCard = card;
    }

    public GridCardPresenter() {
    }

    @Override
    protected DefaultImageCardView onCreateView(Context context) {
        boolean hasTitle = !mCard.getTitle().isEmpty();
        boolean hasContent = !mCard.getDescription().isEmpty();

        final int defaultBackgroundColor = mCard.getInfoBackgroundColor().isEmpty() ?
                Color.TRANSPARENT : Color.parseColor(mCard.getInfoBackgroundColor());
        final int selectedBackgroundColor = mCard.getInfoSelectedBackgroundColor().isEmpty() ?
                ContextCompat.getColor(context, R.color.selected_background) : Color.parseColor(mCard.getInfoSelectedBackgroundColor());;

        DefaultImageCardView cardView =
                new DefaultImageCardView(context) {
                    @Override
                    public void setSelected(boolean selected) {
                        if (!mCardShape.equals(Constants.CARD_SHARE_ROUND)) {
                            int color = selected ? selectedBackgroundColor : defaultBackgroundColor;
                            this.setBackgroundColor(color);
                            this.findViewById(R.id.info_field).setBackgroundColor(color);
                        }
                        if (mGridShowOnlyFocusedInfo) {
                            if (selected) {
                                setTitleVisibility(View.VISIBLE);
                                setContentVisibility(View.VISIBLE);
                            } else {
                                setTitleVisibility(View.INVISIBLE);
                                setContentVisibility(View.INVISIBLE);
                            }
                        }
                    }
                };

        cardView.buildImageCardView(hasTitle, hasContent, mHasImageOnly);
        cardView.setBackgroundColor(defaultBackgroundColor);
        cardView.findViewById(R.id.info_field).setBackgroundColor(defaultBackgroundColor);

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, DefaultImageCardView cardView) {
        cardView.setTitleText(card.getTitle());
        cardView.setContentText(card.getDescription());
        cardView.setMainImageDimensions(mCardWidth, mCardHeight);

        RequestOptions requestOptions = mCardShape.equals(Constants.CARD_SHARE_ROUND) ? RequestOptions.circleCropTransform() : RequestOptions.fitCenterTransform();

        loadMainImage(cardView.getMainImageView(), card, requestOptions);

        if (mGridShowOnlyFocusedInfo) {
            cardView.setTitleVisibility(View.INVISIBLE);
            cardView.setContentVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onUnbindViewHolder(DefaultImageCardView cardView) {
        cardView.setBadgeImage(null);
        cardView.setMainImage(null);
    }
}
