package com.rs.leanbacknative.presenters;

import android.content.Context;

import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.cardViews.VideoCardView;
import com.rs.leanbacknative.models.Card;

public class VideoCardPresenter extends AbstractCardPresenter<VideoCardView> {
    public VideoCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected VideoCardView onCreateView(Context context) {
        VideoCardView cardView = new VideoCardView(context);
        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, VideoCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);
    }
}
