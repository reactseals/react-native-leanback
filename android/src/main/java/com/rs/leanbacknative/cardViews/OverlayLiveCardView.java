package com.rs.leanbacknative.cardViews;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rs.leanbacknative.models.Card;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.utils.Utils;

public class OverlayLiveCardView extends DefaultImageCardView {
    private FrameLayout layout;
    private RelativeLayout overlayImageWrapper;
    private ImageView overlayImage;
    private ProgressBar progressBar;
    private TextView overlayTextView;
    private TextView liveBadge;
    private View gradient;

    public OverlayLiveCardView(Context context, int themeResId) {
        super(context, themeResId);
    }

    public OverlayLiveCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OverlayLiveCardView(Context context) {
        super(context);
    }

    public OverlayLiveCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void buildImageCardView() {
        setBackgroundColor(Color.TRANSPARENT);
        setFocusable(true);
        setFocusableInTouchMode(true);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(com.rs.leanbacknative.R.layout.lb_image_overlay_view, this);

        layout = (FrameLayout) findViewById(com.rs.leanbacknative.R.id.overlay_container);
        overlayImageWrapper = (RelativeLayout) findViewById(com.rs.leanbacknative.R.id.overlay_image_wrapper);
        mImageView = findViewById(com.rs.leanbacknative.R.id.main_image);
        overlayImage = findViewById(R.id.overlay_image);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        overlayTextView = (TextView) findViewById(R.id.overlay_text);
        liveBadge = (TextView) findViewById(R.id.live_badge);
        gradient = (View) findViewById(R.id.gradient);

//        getLayoutView().removeView(getProgressBarView());
//        getLayoutView().removeView(getLiveBadgeView());
//        getLayoutView().removeView(getGradientView());
//        getLayoutView().removeView(getOverlayTextView());

        mFadeInAnimator = ObjectAnimator.ofFloat(mImageView, ALPHA, 1f);
        mFadeInAnimator.setDuration(
                mImageView.getResources().getInteger(android.R.integer.config_shortAnimTime));
    }

    /**
     *
     * @param width
     * @param height
     */
    public void setLayoutDimensions(int width, int height) {
        ViewGroup.LayoutParams lp = layout.getLayoutParams();
        lp.width = width;
        lp.height = height;
        layout.setLayoutParams(lp);
    }

    /**
     *
     * @return
     */
    public ImageView getOverlayImageView() {
        return overlayImage;
    }

    /**
     *
     * @return
     */
    public RelativeLayout getOverlayImageWrapperView() {
        return overlayImageWrapper;
    }

    /**
     *
     * @return
     */
    public TextView getOverlayTextView() {
        return overlayTextView;
    }

    /**
     *
     * @return
     */
    public ProgressBar getProgressBarView() {
        return progressBar;
    }

    /**
     *
     * @return
     */
    public FrameLayout getLayoutView() { return layout; }

    /**
     *
     * @return
     */
    public TextView getLiveBadgeView() { return liveBadge; }

    /**
     *
     * @return
     */
    public View getGradientView() { return gradient; }

    public void setOverlayImagePosition(String position) {
        FrameLayout.LayoutParams layoutParams = ((FrameLayout.LayoutParams) getOverlayImageWrapperView().getLayoutParams());

        if (position.equals("right")) {
            layoutParams.gravity = Gravity.BOTTOM|Gravity.END;
            layoutParams.bottomMargin = 10;
            layoutParams.rightMargin = 10;
            getOverlayImageView().setScaleType(ImageView.ScaleType.FIT_END);
        } else {
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.topMargin = 0;
            layoutParams.rightMargin = 0;
            getOverlayImageView().setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    public void setGradientCornerRadius(int borderRadius) {
        GradientDrawable drawable = (GradientDrawable) getGradientView().getBackground();
        drawable.setCornerRadius(borderRadius);
    }

    public void setProgressBar(Card rowItem) {
        long startTimestamp = rowItem.getProgramStartTimestamp();
        long endTimestamp = rowItem.getProgramEndTimestamp();
        int progress = rowItem.getProgress();
        String badgeColor = rowItem.getLiveBadgeColor();
        String progressBarColor = rowItem.getLiveProgressBarColor();

        if ((startTimestamp!= 0 && endTimestamp != 0) || progress != -1) {
            GradientDrawable drawable = (GradientDrawable) getLiveBadgeView().getBackground();

            if (!badgeColor.isEmpty()) {
                drawable.setColor(Color.parseColor(badgeColor));
            }

            if (!progressBarColor.isEmpty()) {
                getProgressBarView().setProgressTintList(ColorStateList.valueOf(Color.parseColor(progressBarColor)));
            }

            if (progress > 0 && progress < 100) {
                getProgressBarView().setProgress(progress);
            } else if (startTimestamp != 0 && endTimestamp != 0) {
                getProgressBarView().setProgress(Utils.livePercentageLeft(rowItem.getProgramStartTimestamp(), rowItem.getProgramEndTimestamp()));
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        getGradientView().setVisibility(View.INVISIBLE);
        getOverlayTextView().setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        getGradientView().setVisibility(View.VISIBLE);
        getOverlayTextView().setVisibility(View.VISIBLE);
    }
}

