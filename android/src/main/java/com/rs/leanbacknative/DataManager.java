package com.rs.leanbacknative;

import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.Model.NativeRowItem;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static ArrayList<Integer> viewIds = new ArrayList<Integer>();

    public static ArrayList getViewIds() {
        return viewIds;
    }

    public static List<NativeRowItem> setupData(ReadableArray data) {
        viewIds.clear();
        List<NativeRowItem> rows = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            int viewId = View.generateViewId();
            viewIds.add(viewId);
            
            ReadableMap dataRowItem = data.getMap(i);

            NativeRowItem nativeRowItem = new NativeRowItem();
            nativeRowItem.setIndex(i);
            nativeRowItem.setViewId(viewId);
            nativeRowItem.setId(validateString(dataRowItem, "id"));
            nativeRowItem.setTitle(validateString(dataRowItem, "title"));
            nativeRowItem.setDescription(validateString(dataRowItem, "description"));
            nativeRowItem.setCardImageUrl(validateString(dataRowItem, "cardImageUrl"));
            nativeRowItem.setBackdropUrl(validateString(dataRowItem, "backdropUrl"));

            rows.add(nativeRowItem);
        }

        return rows;
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
}
