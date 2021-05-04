package com.rs.leanbacknative.utils;

import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

public class Utils {
    /**
     *
     * @param start
     * @param end
     * @return
     */
    public static int livePercentageLeft(long start, long end) {
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
     * @param forbiddenFocusDirections
     * @param cardView
     */
    public static void setForbiddenFocusDirections(ReadableArray forbiddenFocusDirections, View cardView) {
        for (int i = 0; i < forbiddenFocusDirections.size(); i++) {
            if (Objects.equals(forbiddenFocusDirections.getString(i), Constants.FOCUS_DIRECTION_UP)) {
                cardView.setNextFocusUpId(cardView.getId());
            }
            if (Objects.equals(forbiddenFocusDirections.getString(i), Constants.FOCUS_DIRECTION_DOWN)) {
                cardView.setNextFocusDownId(cardView.getId());
            }
            if (Objects.equals(forbiddenFocusDirections.getString(i), Constants.FOCUS_DIRECTION_LEFT)) {
                cardView.setNextFocusLeftId(cardView.getId());
            }
            if (Objects.equals(forbiddenFocusDirections.getString(i), Constants.FOCUS_DIRECTION_RIGHT)) {
                cardView.setNextFocusRightId(cardView.getId());
            }
        }
    }

    /**
     *
     * @param transformationMode
     * @return
     */
    public static RequestOptions getRequestOptions(String transformationMode) {
        switch (transformationMode) {
            case Constants.IMAGE_TRANSFORMATION_NO_TRANSFORMATION:
                return RequestOptions.noTransformation();
            case Constants.IMAGE_TRANSFORMATION_CENTER_CROP:
                return RequestOptions.centerCropTransform();
            default:
                return RequestOptions.fitCenterTransform();
        }
    }
}
