package com.rs.leanbacknative.presenters;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.leanback.widget.Presenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.rs.leanbacknative.models.Card;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.cardViews.OverlayLiveCardView;
import com.rs.leanbacknative.utils.Constants;
import com.rs.leanbacknative.utils.Utils;



public class OverlayLiveCardPresenter extends Presenter {
    private Drawable mDefaultCardImage;

    private Integer mCardWidth = Constants.DEFAULT_CARD_WIDTH;
    private Integer mCardHeight = Constants.DEFAULT_CARD_HEIGHT;
    private ReadableArray mForbiddenFocusDirections;
    private int nextFocusUpId = -1;
    private int nextFocusDownId = -1;
    private int mBorderRadius;

    public OverlayLiveCardPresenter(ReadableMap attributes) {
        mCardWidth = Math.round(PixelUtil.toPixelFromDIP(attributes.getInt("width")));
        mCardHeight = Math.round(PixelUtil.toPixelFromDIP(attributes.getInt("height")));
        mForbiddenFocusDirections = attributes.hasKey("forbiddenFocusDirections") ? attributes.getArray("forbiddenFocusDirections") : null;
        nextFocusUpId = attributes.getInt("nextFocusUpId");
        nextFocusDownId = attributes.getInt("nextFocusDownId");
        mBorderRadius = attributes.getInt("borderRadius");
    }

    public OverlayLiveCardPresenter() { }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        mDefaultCardImage = ContextCompat.getDrawable(parent.getContext(), R.drawable.lb_ic_sad_cloud);
        OverlayLiveCardView cardView = new OverlayLiveCardView(parent.getContext());
        cardView.buildImageCardView();

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Card rowItem = (Card) item;
        OverlayLiveCardView cardView = (OverlayLiveCardView) viewHolder.view;

        if (cardView.getId() == -1)
            cardView.setId(rowItem.getViewId());

        if (mForbiddenFocusDirections != null)
            Utils.setForbiddenFocusDirections(mForbiddenFocusDirections, cardView);

        if (nextFocusUpId != -1)
            cardView.setNextFocusUpId(nextFocusUpId);

        if (nextFocusDownId != -1)
            cardView.setNextFocusDownId(nextFocusDownId);

        cardView.getOverlayTextView().setText(rowItem.getOverlayText());
        cardView.setGradientCornerRadius(mBorderRadius);
        cardView.setOverlayImagePosition(rowItem.getOverlayPosition());
        cardView.setProgressBar(rowItem);
        cardView.setLayoutDimensions(mCardWidth, mCardHeight);
        cardView.setMainImageDimensions(mCardWidth, mCardHeight);

        RequestOptions requestOptions = mBorderRadius != 0 ?
                (new RequestOptions()).transform(new CenterCrop(), new RoundedCorners(mBorderRadius)) :
                RequestOptions.fitCenterTransform();

        Glide.with(viewHolder.view.getContext())
                .load(rowItem.getCardImageUrl())
                .apply(requestOptions)
                .error(mDefaultCardImage)
                .into(cardView.getMainImageView());

        if (!rowItem.getOverlayImageUrl().isEmpty()) {
            Glide.with(viewHolder.view.getContext())
                    .load(rowItem.getOverlayImageUrl())
                    .apply(RequestOptions.fitCenterTransform())
                    .into(cardView.getOverlayImageView());
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        OverlayLiveCardView cardView = (OverlayLiveCardView) viewHolder.view;
//        cardView.setMainImage(null);
    }
}
