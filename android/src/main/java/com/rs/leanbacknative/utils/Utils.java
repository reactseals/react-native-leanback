package com.rs.leanbacknative.utils;

import android.util.Log;
import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.bumptech.glide.request.RequestOptions;
import com.rs.leanbacknative.models.Card;

import java.util.ArrayList;
import java.util.List;
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
    public static void setForbiddenFocusDirections(ReadableArray forbiddenFocusDirections, Card card, View cardView) {
        // Find the right array of blocked directions for the card
        List<String> forbiddenFocus = toStringArrayList(forbiddenFocusDirections);
        if(card.getIndex() != 0){
            forbiddenFocus = filterStringArrayList(forbiddenFocus, Constants.FOCUS_DIRECTION_LEFT);
        }
        if(!card.isLast()){
            forbiddenFocus = filterStringArrayList(forbiddenFocus, Constants.FOCUS_DIRECTION_RIGHT);
        }

        for (int i = 0; i < forbiddenFocus.size(); i++) {
            if (Objects.equals(forbiddenFocus.get(i), Constants.FOCUS_DIRECTION_UP)
            ) {
                cardView.setNextFocusUpId(cardView.getId());
            }
            if (Objects.equals(forbiddenFocus.get(i), Constants.FOCUS_DIRECTION_DOWN)) {
                cardView.setNextFocusDownId(cardView.getId());
            }
            if (Objects.equals(forbiddenFocus.get(i), Constants.FOCUS_DIRECTION_LEFT)) {
                cardView.setNextFocusLeftId(cardView.getId());
            }
            if (Objects.equals(forbiddenFocus.get(i), Constants.FOCUS_DIRECTION_RIGHT)) {
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

    /**
     * Takes a readable array and converts it to ArrayList<String>
     * @param array
     * @return ArrayList<String>
     */
    public static ArrayList<String> toStringArrayList(ReadableArray array) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            arrayList.add(array.getString(i));
        }
        return arrayList;
    }

    /**
     * Takes a List containing string values and removes all values that match given
     * @param list - a list that needs to be filtered
     * @param value - string value which needs to be filtered out
     * @return List<String>
     */
    public static List<String> filterStringArrayList(List<String> list, String value){
        // create an empty list
        List<String> filteredList = new ArrayList<>();

        // iterate through the list
        for (String entry: list) {
            // filter values that match entered value to filter out
            if (!entry.matches(value)) {
                filteredList.add(entry);
            }
        }
        return filteredList;
    }
}
