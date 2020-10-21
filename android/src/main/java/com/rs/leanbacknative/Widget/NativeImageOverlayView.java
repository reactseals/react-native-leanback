package com.rs.leanbacknative.Widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rs.leanbacknative.R;

public class NativeImageOverlayView extends NativeImageCardView {
    private FrameLayout layout;
    private RelativeLayout overlayImageWrapper;
    private ImageView overlayImage;
    private ProgressBar progressBar;
    private TextView overlayTextView;
    private TextView liveBadge;
    private View gradient;

    public NativeImageOverlayView(Context context, int themeResId) {
        super(context, themeResId);
    }

    public NativeImageOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NativeImageOverlayView(Context context) {
        super(context);
    }

    public NativeImageOverlayView(Context context, AttributeSet attrs) {
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

        getLayoutView().removeView(getProgressBarView());
        getLayoutView().removeView(getLiveBadgeView());
        getLayoutView().removeView(getGradientView());
        getLayoutView().removeView(getOverlayTextView());

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
}

