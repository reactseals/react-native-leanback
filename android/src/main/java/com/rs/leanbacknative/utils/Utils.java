package com.rs.leanbacknative.utils;

import android.view.View;

import com.facebook.react.bridge.ReadableArray;

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
        }
    }

}
