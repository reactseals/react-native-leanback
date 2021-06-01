package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCardPresenter<T extends BaseCardView> extends Presenter {
    protected Drawable mDefaultCardImage;

    protected Integer mCardWidth = Constants.DEFAULT_CARD_WIDTH;
    protected Integer mCardHeight = Constants.DEFAULT_CARD_HEIGHT;
    protected ReadableArray mForbiddenFocusDirections;
    protected int nextFocusUpId = View.NO_ID;
    protected int nextFocusDownId = View.NO_ID;
    protected int nextFocusLeftId = View.NO_ID;
    protected int nextFocusRightId = View.NO_ID;
    protected int mBorderRadius;
    protected boolean mHasImageOnly;
    protected String mImageTransformationMode;

    protected boolean mGridShowOnlyFocusedInfo = false;

    protected String mCardShape = Constants.CARD_SHARE_SQUARE;

    public AbstractCardPresenter() { }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent) {
        mDefaultCardImage = ContextCompat.getDrawable(parent.getContext(), R.drawable.lb_fallback_bg);
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
        cardView.setNextFocusLeftId(View.NO_ID);
        cardView.setNextFocusRightId(View.NO_ID);
        cardView.setNextFocusUpId(View.NO_ID);
        cardView.setNextFocusDownId(View.NO_ID);
    }

    void initializeAttributes(ReadableMap attributes) {
        mCardWidth = Math.round(PixelUtil.toPixelFromDIP(attributes.getInt("width")));
        mCardHeight = Math.round(PixelUtil.toPixelFromDIP(attributes.getInt("height")));
        mForbiddenFocusDirections = attributes.hasKey("forbiddenFocusDirections") ? attributes.getArray("forbiddenFocusDirections") : null;
        nextFocusUpId = attributes.getInt("nextFocusUpId");
        nextFocusDownId = attributes.getInt("nextFocusDownId");
        nextFocusLeftId = attributes.getInt("nextFocusLeftId");
        nextFocusRightId = attributes.getInt("nextFocusLeftId");
        mBorderRadius = attributes.getInt("borderRadius");
        mCardShape = attributes.getString("cardShape");
        mGridShowOnlyFocusedInfo = attributes.getBoolean("showOnlyFocusedInfo");
        mHasImageOnly = attributes.getBoolean("hasImageOnly");
        mImageTransformationMode = attributes.getString("imageTransformationMode");
    }

    void setFocusRules(View cardView, Card card) {
        if (cardView.getId() == View.NO_ID)
            cardView.setId(card.getViewId());

        if (mForbiddenFocusDirections != null) {
            Utils.setForbiddenFocusDirections(mForbiddenFocusDirections, card, cardView);
        }

        if (nextFocusUpId != View.NO_ID)
            cardView.setNextFocusUpId(nextFocusUpId);

        if (nextFocusDownId != View.NO_ID)
            cardView.setNextFocusDownId(nextFocusDownId);

        if (nextFocusLeftId != View.NO_ID && card.getIndex() == 0)
            cardView.setNextFocusLeftId(nextFocusLeftId);

        if (nextFocusRightId != View.NO_ID && card.isLast())
            cardView.setNextFocusRightId(nextFocusRightId);
    }

    void loadMainImage(ImageView imageView, Card card, @Nullable RequestOptions reqOptions) {

        RequestOptions requestOptions = reqOptions != null ? reqOptions : mBorderRadius != 0 ?
                (new RequestOptions()).transform(new CenterCrop(), new RoundedCorners(mBorderRadius)) :
                Utils.getRequestOptions(mImageTransformationMode);

        Glide.with(imageView.getContext())
                .load(card.getCardImageUrl())
                .apply(requestOptions)
                .error(mDefaultCardImage)
                .into(imageView);
    }
}