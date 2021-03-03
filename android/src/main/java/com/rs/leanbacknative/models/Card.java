package com.rs.leanbacknative.models;

import com.google.gson.Gson;

import java.io.Serializable;

public class Card implements Serializable {
    static final long serialVersionUID = 727566175075960653L;

    private String id;

    private int index;

    private int viewId;

    private String title;

    private String description;

    private String cardImageUrl;

    private String backdropUrl;

    private String overlayImageUrl;

    private String overlayText;

    private String overlayPosition;

    private String liveBadgeColor;

    private String liveProgressBarColor;

    private String type;

    private String backgroundColor;

    private long programStartTimestamp;

    private long programEndTimestamp;

    private byte progress;

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

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public String getOverlayImageUrl() {
        return overlayImageUrl;
    }

    public void setOverlayImageUrl(String overlayImageUrl) {
        this.overlayImageUrl = overlayImageUrl;
    }

    public String getOverlayText() {
        return overlayText;
    }

    public void setOverlayText(String overlayText) {
        this.overlayText = overlayText;
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

    public String getOverlayPosition() {
        return overlayPosition;
    }

    public void setOverlayPosition(String overlayPosition) {
        this.overlayPosition = overlayPosition;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Card.Type getPresenterType() {
        return mType;
    }

    public void setPresenterType(Type type) {
        mType = type;
    }


    public enum Type {
        OVERLAY_IMAGE,
        OVERLAY_TEXT,
        LIVE,
        PROGRESS_LOGO_OVERLAY,
        PROGRESS_LOGO,
        PROGRESS_OVERLAY,
        PROGRESS,
        LOGO_OVERLAY,
        LOGO,
        OVERLAY,
        DEFAULT
    }
}
