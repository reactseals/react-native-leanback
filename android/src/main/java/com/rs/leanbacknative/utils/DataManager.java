package com.rs.leanbacknative.utils;

import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.models.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataManager {
    private static ArrayList<Integer> viewIds = new ArrayList<Integer>();
    private static boolean isOverlayPresenter;

    public static ArrayList getViewIds() {
        return viewIds;
    }

    public static boolean isOverlayPresenter() { return isOverlayPresenter; }

    public static List<Card> setupData(ReadableArray data) {
        viewIds.clear();
        isOverlayPresenter = false;
        List<Card> rows = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < data.size(); i++) {
            random.nextInt();
            int viewId = View.generateViewId() + random.nextInt(); // ensure viewID is not duplicate with React ones
            viewIds.add(viewId);

            ReadableMap dataRowItem = data.getMap(i);

            Card nativeRowItem = new Card();
            nativeRowItem.setIndex(i);
            nativeRowItem.setViewId(viewId);
            nativeRowItem.setId(validateString(dataRowItem, "id"));
            nativeRowItem.setTitle(validateString(dataRowItem, "title"));
            nativeRowItem.setDescription(validateString(dataRowItem, "description"));
            nativeRowItem.setCardImageUrl(validateString(dataRowItem, "cardImageUrl"));
            nativeRowItem.setOverlayImageUrl(validateString(dataRowItem, "overlayImageUrl"));
            nativeRowItem.setOverlayText(validateString(dataRowItem, "overlayText"));
            nativeRowItem.setBackdropUrl(validateString(dataRowItem, "backdropUrl"));
            nativeRowItem.setType(validateString(dataRowItem, "type"));
            nativeRowItem.setBackgroundColor(validateString(dataRowItem, "backgroundColor"));
            nativeRowItem.setOverlayPosition(validateString(dataRowItem, "overlayPosition"));
            nativeRowItem.setLiveBadgeColor(validateString(dataRowItem, "liveBadgeColor"));
            nativeRowItem.setLiveProgressBarColor(validateString(dataRowItem, "liveProgressBarColor"));
            nativeRowItem.setProgramStartTimestamp(validateLong(dataRowItem, "programStartTimestamp"));
            nativeRowItem.setProgress(validateByte(dataRowItem, "progress"));
            nativeRowItem.setProgramEndTimestamp(validateLong(dataRowItem, "programEndTimestamp"));
            nativeRowItem.setPresenterType(Card.Type.DEFAULT);

            rows.add(nativeRowItem);

            if (!nativeRowItem.getOverlayImageUrl().isEmpty() ||
                    !nativeRowItem.getOverlayText().isEmpty() ||
                    nativeRowItem.getProgramStartTimestamp() != 0 ||
                    nativeRowItem.getProgress() != -1
            ) {
                nativeRowItem.setPresenterType(Card.Type.FULL);
            }
        }

        return rows;
    }

    private static Card.Type setType(Card item) {
        boolean hasOverlayImage = !item.getOverlayImageUrl().isEmpty();
        boolean hasOverlayText = !item.getOverlayText().isEmpty();
        boolean isLive = item.getProgramStartTimestamp() != 0;

//        if (hasOverlayImage && hasOverlayText && isLive) {
//            return NativeRowItem.Type.FULL;
//        }
//
//        if (hasOverlayImage && hasOverlayText) {
//
//        }
//
//        if (hasOverlayImage && isLive) {
//
//        }
//
//        if (hasOverlayText && isLive) {

//        }

        return Card.Type.DEFAULT;
    }

    private static String validateString(ReadableMap item, String prop) {
        String res = "";
        if (!item.hasKey(prop) || item.isNull(prop)) return res;
        switch (item.getType(prop)) {
            case String:
                res = item.getString(prop);
                break;
            case Number:
                res = String.valueOf(item.getInt(prop));
                break;
        }
        return res;
    }

    private static long validateLong(ReadableMap item, String prop) {
        long res = 0;
        if (!item.hasKey(prop) || item.isNull(prop)) return res;
        switch (item.getType(prop)) {
            case String:
                res =  Long.parseLong(item.getString(prop));
                break;
        }

        return res;
    }
    private static byte validateByte(ReadableMap item, String prop) {
        byte res = -1;
        if (!item.hasKey(prop) || item.isNull(prop)) return res;
        switch (item.getType(prop)) {
            case Number:
                res = (byte)item.getInt(prop);
                break;
            case String:
                res =(byte) Integer.parseInt(item.getString(prop));
                break;
        }
        return res;
    }
}

