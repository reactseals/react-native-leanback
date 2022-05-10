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

    @Expose private String description;

    @Expose private String cardImageUrl;

    @Expose private String videoUrl;

    @Expose private String backdropUrl;

    @Expose private String overlayImageUrl;

    @Expose private String overlayTitle;

    @Expose private String overlaySubtitle;

    @Expose private String overlayRemainingTime;

    private String overlayPosition;

    private Boolean displayLiveBadge;

    private String liveBadgeColor;

    private String liveProgressBarColor;

    private String backgroundColor;

    private String infoBackgroundColor;

    private String infoSelectedBackgroundColor;

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

    public Boolean isLast() {
        return isLast;
    }

    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
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

    public String getOverlayRemainingTime() {
        return overlayRemainingTime;
    }

    public void setOverlayRemainingTime(String overlayRemainingTime) {
        this.overlayRemainingTime = overlayRemainingTime;
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

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getInfoBackgroundColor() {
        return infoBackgroundColor;
    }

    public void setInfoBackgroundColor(String infoBackgroundColor) {
        this.infoBackgroundColor = infoBackgroundColor;
    }

    public String getInfoSelectedBackgroundColor() {
        return infoSelectedBackgroundColor;
    }

    public void setInfoSelectedBackgroundColor(String infoSelectedBackgroundColor) {
        this.infoSelectedBackgroundColor = infoSelectedBackgroundColor;
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
        PROGRESS_LOGO_OVERLAY,
        PROGRESS_LOGO,
        PROGRESS_OVERLAY,
        PROGRESS,
        LOGO_OVERLAY,
        LOGO,
        OVERLAY,
        COLOR_TEXT,
        VIDEO,
        GRID,
        DEFAULT
    }
}