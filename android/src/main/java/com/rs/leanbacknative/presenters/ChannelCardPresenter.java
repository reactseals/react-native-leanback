package com.rs.leanbacknative.presenters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.RequiresApi;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;
import com.rs.leanbacknative.cardViews.ChannelCardView;

public class ChannelCardPresenter extends AbstractCardPresenter<ChannelCardView> {
    public ChannelCardPresenter(ReadableMap attributes) {
        initializeAttributes(attributes);
    }

    @Override
    protected ChannelCardView onCreateView(final Context context) {
        ChannelCardView cardView = new ChannelCardView(context) {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void setSelected(boolean selected) {
                View textContainerView = this.findViewById(R.id.channel_text_layout);

                if (selected) {
                    Drawable resImg = context.getResources().getDrawable(R.drawable.border_gradient_channel);
                    this.findViewById(R.id.main_image).setForeground(resImg);

                    this.findViewById(R.id.overlay_title).setVisibility(View.VISIBLE);
                    this.findViewById(R.id.overlay_subtitle).setVisibility(View.VISIBLE);
                    this.findViewById(R.id.channel_container).setTranslationY(6);

                    textContainerView.setScaleX((float) 0.887);
                    textContainerView.setScaleY((float) 0.895);
                    textContainerView.setTranslationY(-17);
                    textContainerView.animate().alpha((float) 0.4).setDuration(100);
                } else {
                    this.findViewById(R.id.main_image).setForeground(null);
                    this.findViewById(R.id.overlay_title).setVisibility(View.INVISIBLE);
                    this.findViewById(R.id.overlay_subtitle).setVisibility(View.INVISIBLE);
                    this.findViewById(R.id.channel_container).setTranslationY(0);

                    textContainerView.setScaleX((float) 1);
                    textContainerView.setScaleY((float) 1);
                    textContainerView.animate().alpha((float) 1).setDuration(100);
                    textContainerView.setTranslationY(0);
                }

                super.setSelected(selected);
            }
        };

        cardView.buildImageCardView();

        return cardView;
    }

    @Override
    public void onBindViewHolder(Card card, ChannelCardView cardView) {
        cardView.updateUI(card, mBorderRadius, mCardWidth, mCardHeight);

        loadMainImage(cardView.getMainImageView(), card, null);
    }
}
