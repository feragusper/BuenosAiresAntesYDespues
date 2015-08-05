package com.feragusper.buenosairesantesydespues;

import com.google.gson.annotations.SerializedName;

/**
 * @author Fernando.Perez
 * @since 0.1
 *
 * HistoricalRecord Entity used in the data layer.
 */
public class HistoricalRecordEntity {

    @SerializedName("id")
    private int historicalRecordId;

    private String title;

    private String credits;

    private String folder;

    private String description;

    private String geo;

    private String year;

    private String neighborhood;
    private String imageURLBefore;
    private String imageURLAfter;
    private String address;
    private double lat;
    private double lng;
    private String shareURL;

    public HistoricalRecordEntity() {
        //empty
    }

    public int getHistoricalRecordId() {
        return historicalRecordId;
    }

    public void setHistoricalRecordId(int historicalRecordId) {
        this.historicalRecordId = historicalRecordId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getYear() {
        return year;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setImageURLBefore(String imageURLBefore) {
        this.imageURLBefore = imageURLBefore;
    }

    public void setImageURLAfter(String imageURLAfter) {
        this.imageURLAfter = imageURLAfter;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getImageURLBefore() {
        return imageURLBefore;
    }

    public String getImageURLAfter() {
        return imageURLAfter;
    }

    public String getAddress() {
        return address;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }

    public String getShareURL() {
        return shareURL;
    }
}