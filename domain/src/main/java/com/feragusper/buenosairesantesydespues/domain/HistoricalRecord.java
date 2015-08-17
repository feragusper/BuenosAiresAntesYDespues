package com.feragusper.buenosairesantesydespues.domain;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Class that represents a HistoricalRecord in the domain layer.
 */
public class HistoricalRecord {

    private final String historicalRecordId;
    private String neighborhood;
    private String imageURLBefore;
    private String imageURLAfter;
    private String address;
    private double lat;
    private double lng;
    private String shareURL;

    public HistoricalRecord(String historicalRecordId) {
        this.historicalRecordId = historicalRecordId;
    }

    private String title;

    private String credits;

    private String folder;

    private String description;

    private String year;

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

    public String getHistoricalRecordId() {
        return historicalRecordId;
    }

    public String getYear() {
        return year;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageURLBefore() {
        return imageURLBefore;
    }

    public String getImageURLAfter() {
        return imageURLAfter;
    }

    public void setImageURLAfter(String imageURLAfter) {
        this.imageURLAfter = imageURLAfter;
    }

    public void setImageURLBefore(String imageURLBefore) {
        this.imageURLBefore = imageURLBefore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
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
