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
    private SpreadsheetString historicalRecordId;

    @SerializedName("gsx$id")
    private SpreadsheetString id;

    @SerializedName("gsx$titulo")
    private SpreadsheetString title;

    @SerializedName("gsx$creditos")
    private SpreadsheetString credits;

    @SerializedName("gsx$carpeta")
    private SpreadsheetString folder;

    @SerializedName("gsx$descripcion")
    private SpreadsheetString description;

    @SerializedName("gsx$geo")
    private SpreadsheetString geo;

    @SerializedName("gsx$año")
    private SpreadsheetString year;

    @SerializedName("gsx$direccion")
    private SpreadsheetString address;

    @SerializedName("gsx$barrio")
    private SpreadsheetString neighborhood;

    public HistoricalRecordEntity() {
        //empty
    }

    public String getHistoricalRecordId() {
        return historicalRecordId.toString();
    }

    public String getTitle() {
        return title.toString();
    }

    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public String getCredits() {
        return credits.toString();
    }

    public String getFolder() {
        return folder.toString();
    }

    public String getDescription() {
        return description.toString();
    }

    public String getGeo() {
        return geo.toString();
    }

    public String getYear() {
        return year.toString();
    }

    public String getNeighborhood() {
        return neighborhood.toString();
    }

    public String getAddress() {
        return address.toString();
    }

    public String getId() {
        return id.toString();
    }
}