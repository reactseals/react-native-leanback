package com.rs.leanbacknative.Model;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class NativeRowItem implements Serializable {
    static final long serialVersionUID = 727566175075960653L;

    private long id;

    private int index;

    private String title;

    private String description;

    private String cardImageUrl;

    private String backdropUrl;

    public NativeRowItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public void setBackdropUrl(String backdropUrl) {
        this.backdropUrl = backdropUrl;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
