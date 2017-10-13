package com.feragusper.buenosairesantesydespues.domain.model;

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
    private String thumbnail;
    private String title;
    private String credits;
    private String folder;
    private String description;
    private String year;

    public HistoricalRecord(String historicalRecordId) {
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

    public String getHistoricalRecordId() {
        return historicalRecordId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getImageURLBefore() {
        return imageURLBefore;
    }

    public void setImageURLBefore(String imageURLBefore) {
        this.imageURLBefore = imageURLBefore;
    }

    public String getImageURLAfter() {
        return imageURLAfter;
    }

    public void setImageURLAfter(String imageURLAfter) {
        this.imageURLAfter = imageURLAfter;
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

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getShareURL() {
        return shareURL;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
