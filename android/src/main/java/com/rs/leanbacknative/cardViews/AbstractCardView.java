package com.rs.leanbacknative.cardViews;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;
import com.rs.leanbacknative.utils.Utils;

public abstract class AbstractCardView extends DefaultImageCardView {
    protected FrameLayout layout;
    protected RelativeLayout overlayImageWrapper;
    protected ImageView overlayImage;
    protected ProgressBar progressBar;
    protected TextView overlayTextView;
    protected TextView liveBadge;
    protected View gradient;

    public AbstractCardView(Context context) {
        super(context);
    }

    public void buildImageCardView() {
        setBackgroundColor(Color.TRANSPARENT);
        setFocusable(true);
        setFocusableInTouchMode(true);

        buildCardView();

        layout = findViewById(R.id.overlay_container);
        overlayImageWrapper = findViewById(R.id.overlay_image_wrapper);
        mImageView = findViewById(com.rs.leanbacknative.R.id.main_image);
        overlayImage = findViewById(R.id.overlay_image);
        progressBar = findViewById(R.id.progress_bar);
        overlayTextView = findViewById(R.id.overlay_text);
        liveBadge = findViewById(R.id.live_badge);
        gradient = findViewById(R.id.gradient);

        mFadeInAnimator = ObjectAnimator.ofFloat(mImageView, ALPHA, 1f);
        mFadeInAnimator.setDuration(
                mImageView.getResources().getInteger(android.R.integer.config_shortAnimTime));
    }

    public abstract void buildCardView();

    public void setLayoutDimensions(int width, int height) {
        ViewGroup.LayoutParams lp = layout.getLayoutParams();
        lp.width = width;
        lp.height = height;
        layout.setLayoutParams(lp);
    }

    public ImageView getOverlayImageView() {
        return overlayImage;
    }
    public View getGradientView() {  return gradient; }
    public TextView getOverlayTextView() {  return overlayTextView; }

    protected void setOverlayImagePosition(String position) {
        FrameLayout.LayoutParams layoutParams = ((FrameLayout.LayoutParams) overlayImageWrapper.getLayoutParams());

        if (position.equals("right")) {
            layoutParams.gravity = Gravity.BOTTOM|Gravity.END;
            layoutParams.bottomMargin = 10;
            layoutParams.rightMargin = 10;
            overlayImage.setScaleType(ImageView.ScaleType.FIT_END);
        } else {
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.topMargin = 0;
            layoutParams.rightMargin = 0;
            overlayImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    protected void setGradientCornerRadius(int borderRadius) {
        GradientDrawable drawable = (GradientDrawable) gradient.getBackground();
        drawable.setCornerRadius(borderRadius);
    }

    protected void setProgressBar(Card card) {
        long startTimestamp = card.getProgramStartTimestamp();
        long endTimestamp = card.getProgramEndTimestamp();
        int progress = card.getProgress();
        String badgeColor = card.getLiveBadgeColor();
        String progressBarColor = card.getLiveProgressBarColor();

        if ((startTimestamp!= 0 && endTimestamp != 0) || progress != -1) {
            GradientDrawable drawable = (GradientDrawable) liveBadge.getBackground();

            if (!badgeColor.isEmpty()) {
                drawable.setColor(Color.parseColor(badgeColor));
            }

            if (!progressBarColor.isEmpty()) {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor(progressBarColor)));
            }

            if (progress > 0 && progress < 100) {
                progressBar.setProgress(progress);
            } else if (startTimestamp != 0 && endTimestamp != 0) {
                progressBar.setProgress(Utils.livePercentageLeft(card.getProgramStartTimestamp(), card.getProgramEndTimestamp()));
            }
        }
    }
}
