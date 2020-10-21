package com.rs.leanbacknative.Presenter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.ReadableArray;
import com.rs.leanbacknative.Model.NativeRowItem;
import com.rs.leanbacknative.R;
import com.rs.leanbacknative.Widget.NativeImageOverlayView;

import java.util.Objects;

class CardUtils {

    private static final String FOCUS_DIRECTION_UP = "up";
    private static final String FOCUS_DIRECTION_DOWN = "down";

    /**
     *
     * @param forbiddenFocusDirections
     * @param cardView
     */
    static void setForbiddenFocusDirections(ReadableArray forbiddenFocusDirections, View cardView) {
        for (int i = 0; i < forbiddenFocusDirections.size(); i++) {
            if (Objects.equals(forbiddenFocusDirections.getString(i), FOCUS_DIRECTION_UP)) {
                cardView.setNextFocusUpId(cardView.getId());
            }
            if (Objects.equals(forbiddenFocusDirections.getString(i), FOCUS_DIRECTION_DOWN)) {
                cardView.setNextFocusDownId(cardView.getId());
            }
        }
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    static int livePercentageLeft(long start, long end) {
        long now = System.currentTimeMillis();

        if (start >= end || now >= end) {
            return 0;
        }

        if (now <= start) {
            return 100;
        }

        return (int) ((end - now) * 100 / (end - start));
    }

    /**
     *
     * @param cardView
     * @param rowItem
     */
    static void setupLiveAssetElements(NativeImageOverlayView cardView, NativeRowItem rowItem) {
        View view = cardView.findViewById(R.id.progress_bar);
        if (rowItem.getProgramStartTimestamp() != 0 && rowItem.getProgramEndTimestamp() != 0) {
            if (view == null) {
                cardView.getLayoutView().addView(cardView.getProgressBarView());
                cardView.getLayoutView().addView(cardView.getLiveBadgeView());
            }
            GradientDrawable drawable = (GradientDrawable) cardView.getLiveBadgeView().getBackground();
            if (!rowItem.getLiveBadgeColor().isEmpty())
                drawable.setColor(Color.parseColor(rowItem.getLiveBadgeColor()));
            if (!rowItem.getLiveProgressBarColor().isEmpty())
                cardView.getProgressBarView().setProgressTintList(ColorStateList.valueOf(Color.parseColor(rowItem.getLiveProgressBarColor())));
            cardView.getProgressBarView().setProgress(CardUtils.livePercentageLeft(rowItem.getProgramStartTimestamp(), rowItem.getProgramEndTimestamp()));
        }
    }

    /**
     *
     * @param cardView
     * @param rowItem
     */
    static void setupOverlayImage(NativeImageOverlayView cardView, NativeRowItem rowItem) {
        switch (rowItem.getOverlayPosition()) {
            case "right":
                ((FrameLayout.LayoutParams) cardView.getOverlayImageWrapperView().getLayoutParams()).gravity = Gravity.END;
                ((FrameLayout.LayoutParams) cardView.getOverlayImageWrapperView().getLayoutParams()).topMargin = 10;
                ((FrameLayout.LayoutParams) cardView.getOverlayImageWrapperView().getLayoutParams()).rightMargin = 10;
                cardView.getOverlayImageView().setScaleType(ImageView.ScaleType.FIT_END);
                break;
            default:
                ((FrameLayout.LayoutParams) cardView.getOverlayImageWrapperView().getLayoutParams()).gravity = Gravity.CENTER;
                ((FrameLayout.LayoutParams) cardView.getOverlayImageWrapperView().getLayoutParams()).topMargin = 0;
                ((FrameLayout.LayoutParams) cardView.getOverlayImageWrapperView().getLayoutParams()).rightMargin = 0;
                cardView.getOverlayImageView().setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
        }
    }

    /**
     *
     * @param cardView
     * @param rowItem
     */
    static void setupTextOverlay(NativeImageOverlayView cardView, NativeRowItem rowItem) {
        View view = cardView.findViewById(R.id.gradient);
        if (!rowItem.getOverlayText().isEmpty()) {
            if (view == null) {
                cardView.getLayoutView().addView(cardView.getGradientView());
                cardView.getLayoutView().addView(cardView.getOverlayTextView());
            }
            cardView.getOverlayTextView().setText(rowItem.getOverlayText());
        }
    }
}
