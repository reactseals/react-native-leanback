package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.Color;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.models.Card;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.cardViews.DefaultImageCardView;
import com.rs.leanbacknative.utils.Constants;
import com.rs.leanbacknative.utils.Utils;

public class DefaultCardPresenter extends AbstractCardPresenter<DefaultImageCardView> {
    private Card mCard;

    public DefaultCardPresenter(ReadableMap attributes, Card card) {
        initializeAttributes(attributes);
        mCard = card;
    }

    public DefaultCardPresenter() { }

    @Override
    protected DefaultImageCardView onCreateView(Context context) {
        boolean hasTitle = !mCard.getTitle().isEmpty();
        boolean hasContent = !mCard.getDescription().isEmpty();

        int infoBackgroundColor = mCard.getInfoBackgroundColor().isEmpty() ?
                ContextCompat.getColor(context, R.color.default_background) : Color.parseColor(mCard.getInfoBackgroundColor());
        final int selectedBackgroundColor = mCard.getInfoSelectedBackgroundColor().isEmpty() ?
                ContextCompat.getColor(context, R.color.selected_background) : Color.parseColor(mCard.getInfoSelectedBackgroundColor());;
        final int defaultBackgroundColor = mCardShape.equals(Constants.CARD_SHARE_ROUND) ? Color.TRANSPARENT : infoBackgroundColor;

        DefaultImageCardView cardView =
            new DefaultImageCardView(context) {
                @Override
                public void setSelected(boolean selected) {
                    if (!mCardShape.equals(Constants.CARD_SHARE_ROUND)) {
                        int color = selected ? selectedBackgroundColor : defaultBackgroundColor;
                        this.setBackgroundColor(color);
                        this.findViewById(R.id.info_field).setBackgroundColor(color);
                    }
                    super.setSelected(selected);
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

        if (card.getCardImageUrl() != null) {
            cardView.setMainImageDimensions(mCardWidth, mCardHeight);

            RequestOptions requestOptions = mCardShape.equals(Constants.CARD_SHARE_ROUND) ?
                    RequestOptions.circleCropTransform() : Utils.getRequestOptions(mImageTransformationMode);

            loadMainImage(cardView.getMainImageView(), card, requestOptions);
        }
    }

    @Override
    public void onUnbindViewHolder(DefaultImageCardView cardView) {
        cardView.setBadgeImage(null);
        cardView.setMainImage(null);
    }
}
