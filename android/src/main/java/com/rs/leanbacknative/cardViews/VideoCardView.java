package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;
import com.rs.leanbacknative.widgets.VideoCardWidget;

public class VideoCardView extends AbstractCardView {

    VideoCardWidget mVideoView;

    private boolean mSelected = false;

    public VideoCardView(Context context) {
        super(context);
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_video, this);
    }

    public void updateUI(Card rowItem, int borderRadius, int cardWidth, int cardHeight) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.card_view_video, this);

        setLayoutDimensions(cardWidth, cardHeight);

        mVideoView = view.findViewById(R.id.main_video);
        mVideoView.setLayoutParams(new LayoutParams(cardWidth, cardHeight));

        setLoading(rowItem.getVideoUrl());
    }

    @Override
    public void setSelected(boolean selected) {
        try {
            mSelected = selected;
            if (selected && !mVideoView.isPlaying()) {
                mVideoView.starPlayback();
            }
            if (!selected && mVideoView.isPlaying()) {
                mVideoView.stopPlayback();
            }
        } catch (Exception e) {}
    }

    public void setLoading(String videoUrl) {
        mVideoView.setupMediaPlayer(videoUrl, new VideoCardWidget.OnVideoReadyListener() {
            @Override
            public void onVideoReady() {
                if (mSelected) {
                    mVideoView.starPlayback();
                }
            }

            @Override
            public void onVideoError() {
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        mVideoView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mVideoView.setVisibility(View.VISIBLE);
    }
}

