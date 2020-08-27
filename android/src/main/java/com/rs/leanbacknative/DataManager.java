package com.rs.leanbacknative;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.Model.NativeRowItem;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static List<NativeRowItem> setupData(ReadableArray data) {
        List<NativeRowItem> rows = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            ReadableMap dataRowItem = data.getMap(i);

            NativeRowItem nativeRowItem = new NativeRowItem();
            nativeRowItem.setIndex(i);
            nativeRowItem.setId(dataRowItem.getInt("id"));
            nativeRowItem.setTitle(dataRowItem.getString("title"));
            nativeRowItem.setDescription(dataRowItem.getString("description"));
            nativeRowItem.setCardImageUrl(dataRowItem.getString("cardImage"));
            nativeRowItem.setBackdropUrl(dataRowItem.getString("backdropUrl"));

            rows.add(nativeRowItem);
        }

        return rows;
    }
}
