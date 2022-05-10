package com.rs.leanbacknative.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Card implements Serializable {
    static final long serialVersionUID = 727566175075960653L;

    @Expose private String id;

    @Expose private int index;

    @Expose private Boolean isLast;

    @Expose private int viewId;

    @Expose private String title;

    @Expose private String subtitle;

    @Expose private String cardImageUrl;

    @Expose private String overlayTitle;

    @Expose private String overlaySubtitle;

    private Boolean displayLiveBadge;

    private String liveBadgeColor;

    private String liveProgressBarColor;

    @Expose private long programStartTimestamp;

    @Expose private long programEndTimestamp;

    @Expose private byte progress;

    private Card.Type mType;

    public Card() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public Boolean isLast() {
        return isLast;
    }

    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public String getOverlayTitle() {
        return overlayTitle;
    }

    public void setOverlayTitle(String overlayTitle) {
        this.overlayTitle = overlayTitle;
    }

    public String getOverlaySubtitle() {
        return overlaySubtitle;
    }

    public void setOverlaySubtitle(String overlaySubtitle) {
        this.overlaySubtitle = overlaySubtitle;
    }

    public long getProgramStartTimestamp() {
        return programStartTimestamp;
    }

    public void setProgramStartTimestamp(long programStartTimestamp) {
        this.programStartTimestamp = programStartTimestamp;
    }

    public long getProgramEndTimestamp() {
        return programEndTimestamp;
    }

    public void setProgramEndTimestamp(long programEndTimestamp) {
        this.programEndTimestamp = programEndTimestamp;
    }

    public Boolean getDisplayLiveBadge() { return displayLiveBadge; }

    public void setDisplayLiveBadge(Boolean displayLiveBadge) {
        this.displayLiveBadge = displayLiveBadge;
    }

    public String getLiveBadgeColor() {
        return liveBadgeColor;
    }

    public void setLiveBadgeColor(String liveBadgeColor) {
        this.liveBadgeColor = liveBadgeColor;
    }

    public String getLiveProgressBarColor() {
        return liveProgressBarColor;
    }

    public void setLiveProgressBarColor(String liveProgressBarColor) {
        this.liveProgressBarColor = liveProgressBarColor;
    }
    public byte getProgress() {
        return progress;
    }

    public void setProgress(byte progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public String toJSON(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.toJson(this);
    }

    public Card.Type getPresenterType() {
        return mType;
    }

    public void setPresenterType(Type type) {
        mType = type;
    }


    public enum Type {
        CHANNEL_TILE,
        CONTINUE_WATCHING_TILE,
        APP_TILE,
        REGULAR_TILE,
        GRID_TILE,
    }
}