package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.leanback.widget.BaseCardView;
import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;
import com.rs.leanbacknative.utils.Constants;
import com.rs.leanbacknative.utils.Utils;

public abstract class AbstractCardPresenter<T extends BaseCardView> extends Presenter {
    protected Drawable mDefaultCardImage;

    protected Integer mCardWidth = Constants.DEFAULT_CARD_WIDTH;
    protected Integer mCardHeight = Constants.DEFAULT_CARD_HEIGHT;
    protected ReadableArray mForbiddenFocusDirections;
    protected int nextFocusUpId = -1;
    protected int nextFocusDownId = -1;
    protected int mBorderRadius;

    public AbstractCardPresenter() { }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent) {
        mDefaultCardImage = ContextCompat.getDrawable(parent.getContext(), R.drawable.lb_ic_sad_cloud);
        T cardView = onCreateView(parent.getContext());
        return new ViewHolder(cardView);
    }

    @Override
    public final void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Card card = (Card) item;
        setFocusRules((T) viewHolder.view, card);
        onBindViewHolder(card, (T) viewHolder.view);
    }

    @Override
    public final void onUnbindViewHolder(ViewHolder viewHolder) {
        onUnbindViewHolder((T) viewHolder.view);
    }

    protected abstract T onCreateView(Context context);

    public abstract void onBindViewHolder(Card card, T cardView);

    public void onUnbindViewHolder(T cardView) {
        // Nothing to clean up. Override if necessary.
    }

    void initializeAttributes(ReadableMap attributes) {
        mCardWidth = Math.round(PixelUtil.toPixelFromDIP(attributes.getInt("width")));
        mCardHeight = Math.round(PixelUtil.toPixelFromDIP(attributes.getInt("height")));
        mForbiddenFocusDirections = attributes.hasKey("forbiddenFocusDirections") ? attributes.getArray("forbiddenFocusDirections") : null;
        nextFocusUpId = attributes.getInt("nextFocusUpId");
        nextFocusDownId = attributes.getInt("nextFocusDownId");
        mBorderRadius = attributes.getInt("borderRadius");
    }

    void setFocusRules(View cardView, Card rowItem) {
        if (cardView.getId() == -1)
            cardView.setId(rowItem.getViewId());

        if (mForbiddenFocusDirections != null)
            Utils.setForbiddenFocusDirections(mForbiddenFocusDirections, cardView);

        if (nextFocusUpId != -1)
            cardView.setNextFocusUpId(nextFocusUpId);

        if (nextFocusDownId != -1)
            cardView.setNextFocusDownId(nextFocusDownId);
    }

    void loadMainImage(ImageView imageView, Card rowItem) {
        RequestOptions requestOptions = mBorderRadius != 0 ?
                (new RequestOptions()).transform(new CenterCrop(), new RoundedCorners(mBorderRadius)) :
                RequestOptions.fitCenterTransform();

        Glide.with(imageView.getContext())
                .load(rowItem.getCardImageUrl())
                .apply(requestOptions)
                .error(mDefaultCardImage)
                .into(imageView);
    }
}
