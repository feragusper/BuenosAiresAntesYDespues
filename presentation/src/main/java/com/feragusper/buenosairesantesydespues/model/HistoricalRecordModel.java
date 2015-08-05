package com.feragusper.buenosairesantesydespues.model;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Class that represents a user in the presentation layer.
 */
public class HistoricalRecordModel {

    private final int historicalRecordId;
    private String imageURLBefore;
    private String imageURLAfter;
    private String address;
    private double lat;
    private double lng;
    private String shareURL;

    public HistoricalRecordModel(int historicalRecordId) {
        this.historicalRecordId = historicalRecordId;
    }

    private String title;

    private String credits;

    private String folder;

    private String description;

    private String year;

    private String neighborhood;

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

    public int getHistoricalRecordId() {
        return historicalRecordId;
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

    public void setImageURLBefore(String imageURLBefore) {
        this.imageURLBefore = imageURLBefore;
    }

    public void setImageURLAfter(String imageURLAfter) {
        this.imageURLAfter = imageURLAfter;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getYear() {
        return year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
