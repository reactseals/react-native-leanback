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
    public DataManager() {}

    private ArrayList<Integer> viewIds = new ArrayList<Integer>();

    public ArrayList getViewIds() {
        return viewIds;
    }

    public List<Card> setupData(@Nullable ReadableArray data, ReadableMap attributes) {
        List<Card> rows = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        Random random = new Random();

        for (int i = 0; i < data.size(); i++) {
            random.nextInt();
            int viewId = View.generateViewId() + random.nextInt(); // ensure viewID is not duplicate with React ones
            ids.add(viewId);
            ReadableMap dataRowItem = data.getMap(i);

            Card card = new Card();
            card.setIndex(i);
            card.setIsLast(i == data.size());
            card.setViewId(viewId);
            card.setId(validateString(dataRowItem, "id"));
            card.setTitle(validateString(dataRowItem, "title"));
            card.setSubtitle(validateString(dataRowItem, "description"));
            card.setCardImageUrl(validateString(dataRowItem, "cardImageUrl"));
            card.setOverlayTitle(validateString(dataRowItem, "overlayTitle"));
            card.setOverlaySubtitle(validateString(dataRowItem, "overlaySubtitle"));
            card.setOverlayRemainingTime(validateString(dataRowItem, "overlayRemainingTime"));
            card.setDisplayLiveBadge(validateBoolean(dataRowItem, "displayLiveBadge"));
            card.setLiveBadgeColor(validateString(dataRowItem, "liveBadgeColor"));
            card.setLiveProgressBarColor(validateString(dataRowItem, "liveProgressBarColor"));
            card.setProgramStartTimestamp(validateLong(dataRowItem, "programStartTimestamp"));
            card.setProgress(validateByte(dataRowItem, "progress"));
            card.setProgramEndTimestamp(validateLong(dataRowItem, "programEndTimestamp"));
            card.setPresenterType(getType(card, attributes));
            rows.add(card);
        }

        if (viewIds.size() == 0) {
            viewIds = ids;
        }

        return rows;
    }

    private static Card.Type getType(Card item, ReadableMap attributes) {
        boolean hasOverlayTitle = !item.getOverlayTitle().isEmpty();
        boolean hasTitle = !item.getTitle().isEmpty();
        boolean hasProgress = item.getProgress() > 0;
        boolean hasImageOnly = attributes.getBoolean("hasImageOnly");

        if (hasTitle && hasOverlayTitle) return Card.Type.CHANNEL_TILE;
        if (hasProgress && hasOverlayTitle) return Card.Type.CONTINUE_WATCHING_TILE;
        if (hasOverlayTitle) return Card.Type.REGULAR_TILE;
        if (hasImageOnly) return Card.Type.APP_TILE;

        return Card.Type.REGULAR_TILE;
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
    private static Boolean validateBoolean(ReadableMap item, String prop) {
        Boolean res = false;
        if (!item.hasKey(prop) || item.isNull(prop)) return false;
        switch (item.getType(prop)) {
            case Boolean:
                res =  item.getBoolean(prop);
                break;
        }
        return res;
    }
}