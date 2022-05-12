package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class ChannelCardView extends AbstractCardView {
    public ChannelCardView(Context context) {
        super(context);
    }

    protected LinearLayout channelContainerLayout;
    protected TextView channelTitleView;
    protected TextView channelSubtitleView;

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_channel, this);

        channelContainerLayout = findViewById(R.id.channel_container);
        channelTitleView = findViewById(R.id.channel_title);
        channelSubtitleView = findViewById(R.id.channel_subtitle);
//        channelContainerLayout.
    }

    public void updateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        overlayTitleView.setText(card.getOverlayTitle());
        overlayTitleView.setVisibility(View.INVISIBLE);

        overlaySubtitleView.setText(card.getOverlaySubtitle());
        overlaySubtitleView.setVisibility(View.INVISIBLE);

        channelTitleView.setText(card.getTitle());
        channelSubtitleView.setText(card.getSubtitle());

        gradient.setVisibility(View.INVISIBLE);

        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
        setChannelLayoutDimensions(cardWidth, cardHeight + 100);
    }

    public void setChannelLayoutDimensions(int width, int height) {
        ViewGroup.LayoutParams lp = channelContainerLayout.getLayoutParams();
        lp.width = width;
        lp.height = height;
        channelContainerLayout.setLayoutParams(lp);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}

