package com.feragusper.buenosairesantesydespues.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Fernando.Perez
 * @since 1.3
 * <p>
 * Images Entity used in the data layer.
 */
public class ImagesEntity {

    @SerializedName("thumbnail")
    private ImageEntity thumbnail;

    @SerializedName("full")
    private ImageEntity full;

    public ImagesEntity(ImageEntity full, ImageEntity thumbnail) {
        this.full = full;
        this.thumbnail = thumbnail;
    }

    String getFullURL() {
        return full.getUrl();
    }

    String getThumbnailURL() {
        return thumbnail.getUrl();
    }
}
