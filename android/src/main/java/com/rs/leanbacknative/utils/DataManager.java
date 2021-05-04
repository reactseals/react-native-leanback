package com.rs.leanbacknative.utils;

import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.models.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataManager {
    private static ArrayList<Integer> viewIds = new ArrayList<Integer>();

    public static ArrayList getViewIds() {
        return viewIds;
    }

    public static List<Card> setupData(@Nullable ReadableArray data, ReadableMap attributes) {
        viewIds.clear();
        List<Card> rows = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < data.size(); i++) {
            random.nextInt();
            int viewId = View.generateViewId() + random.nextInt(); // ensure viewID is not duplicate with React ones
            viewIds.add(viewId);

            ReadableMap dataRowItem = data.getMap(i);

            Card card = new Card();
            card.setIndex(i);
            card.setViewId(viewId);
            card.setId(validateString(dataRowItem, "id"));
            card.setTitle(validateString(dataRowItem, "title"));
            card.setDescription(validateString(dataRowItem, "description"));
            card.setCardImageUrl(validateString(dataRowItem, "cardImageUrl"));
            card.setOverlayImageUrl(validateString(dataRowItem, "overlayImageUrl"));
            card.setVideoUrl(validateString(dataRowItem, "videoUrl"));
            card.setOverlayText(validateString(dataRowItem, "overlayText"));
            card.setBackdropUrl(validateString(dataRowItem, "backdropUrl"));
            card.setBackgroundColor(validateString(dataRowItem, "backgroundColor"));
            card.setOverlayPosition(validateString(dataRowItem, "overlayPosition"));
            card.setLiveBadgeColor(validateString(dataRowItem, "liveBadgeColor"));
            card.setLiveProgressBarColor(validateString(dataRowItem, "liveProgressBarColor"));

            card.setInfoBackgroundColor(validateString(dataRowItem, "infoBackgroundColor"));
            card.setInfoSelectedBackgroundColor(validateString(dataRowItem, "infoSelectedBackgroundColor"));

            card.setProgramStartTimestamp(validateLong(dataRowItem, "programStartTimestamp"));
            card.setProgress(validateByte(dataRowItem, "progress"));
            card.setProgramEndTimestamp(validateLong(dataRowItem, "programEndTimestamp"));
            card.setPresenterType(getType(card, attributes));
            rows.add(card);
        }

        return rows;
    }

    private static Card.Type getType(Card item, ReadableMap attributes) {
        boolean hasLogo = !item.getOverlayImageUrl().isEmpty();
        boolean hasOverlayText = !item.getOverlayText().isEmpty();
        boolean isLive = item.getProgramStartTimestamp() != 0 || item.getProgress() != -1;
        boolean isColorText = !item.getBackgroundColor().isEmpty();
        boolean isVideo = !item.getVideoUrl().isEmpty();
        boolean hasImageOnly = attributes.getBoolean("hasImageOnly");

        if (isLive && hasOverlayText && hasLogo) return Card.Type.PROGRESS_LOGO_OVERLAY;
        if (isLive && hasLogo) return Card.Type.PROGRESS_LOGO;
        if (isLive && hasOverlayText) return Card.Type.PROGRESS_OVERLAY;
        if (hasLogo && hasOverlayText) return Card.Type.LOGO_OVERLAY;
        if (hasLogo) return Card.Type.LOGO;
        if (isColorText) return Card.Type.COLOR_TEXT;
        if (hasOverlayText) return Card.Type.OVERLAY;
        if (isLive) return Card.Type.PROGRESS;
        if (isVideo) return Card.Type.VIDEO;
        if (hasImageOnly) return Card.Type.LOGO;

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

