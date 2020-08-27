package com.rs.leanbacknative;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.rs.leanbacknative.Model.NativeRowItem;
import com.rs.leanbacknative.Widget.NativeImageCardView;


public class CardPresenter extends Presenter {
    private static final String TAG = "CardPresenter";

    private static final int DEFAULT_CARD_WIDTH = 313;
    private static final int DEFAULT_CARD_HEIGHT = 176;

    private Drawable mDefaultCardImage;

    private Integer mCardWidth = DEFAULT_CARD_WIDTH;
    private Integer mCardHeight = DEFAULT_CARD_HEIGHT;
    private boolean mHasImageOnly = false;
    private boolean mHasTitle = true;
    private boolean mHasContent = true;
    private boolean mHasIconRight = false;
    private boolean mHasIconLeft = false;
    private static int sSelectedBackgroundColor;
    private static int sDefaultBackgroundColor;


    public CardPresenter(ReadableMap attributes) {
        mCardWidth = Math.round(PixelUtil.toPixelFromDIP(attributes.getInt("width")));
        mCardHeight= Math.round(PixelUtil.toPixelFromDIP(attributes.getInt("height")));
        mHasImageOnly = attributes.getBoolean("hasImageOnly");
        mHasTitle = attributes.getBoolean("hasTitle");
        mHasContent = attributes.getBoolean("hasContent");
        mHasIconRight = attributes.getBoolean("hasIconRight");
        mHasIconLeft = attributes.getBoolean("hasIconLeft");
    }

    public CardPresenter() {
    }

    private static void updateCardBackgroundColor(NativeImageCardView view, boolean selected) {
        int color = selected ? sSelectedBackgroundColor : sDefaultBackgroundColor;
        // Both background colors should be set because the view's background is temporarily visible
        // during animations.
        view.setBackgroundColor(color);
        view.findViewById(R.id.info_field).setBackgroundColor(color);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        sDefaultBackgroundColor =
            ContextCompat.getColor(parent.getContext(), R.color.default_background);
        sSelectedBackgroundColor =
            ContextCompat.getColor(parent.getContext(), R.color.selected_background);

        mDefaultCardImage = ContextCompat.getDrawable(parent.getContext(), R.drawable.lb_ic_sad_cloud);

        NativeImageCardView cardView =
            new NativeImageCardView(parent.getContext()) {
                @Override
                public void setSelected(boolean selected) {
                    if (!mHasImageOnly) {
                        updateCardBackgroundColor(this, selected);
                    }
                    super.setSelected(selected);
                }
            };

        cardView.buildImageCardView(mHasImageOnly, mHasTitle, mHasContent, mHasIconRight, mHasIconLeft);

        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        NativeRowItem rowItem = (NativeRowItem) item;
        NativeImageCardView cardView = (NativeImageCardView) viewHolder.view;

        if (rowItem.getCardImageUrl() != null) {
            cardView.setTitleText(rowItem.getTitle());
            cardView.setContentText(rowItem.getDescription());
            cardView.setMainImageDimensions(mCardWidth, mCardHeight);
            Glide.with(viewHolder.view.getContext())
                .load(rowItem.getCardImageUrl())
                .centerCrop()
                .error(mDefaultCardImage)
                .into(cardView.getMainImageView());
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        NativeImageCardView cardView = (NativeImageCardView) viewHolder.view;
        cardView.setBadgeImage(null);
        cardView.setMainImage(null);
    }
}
