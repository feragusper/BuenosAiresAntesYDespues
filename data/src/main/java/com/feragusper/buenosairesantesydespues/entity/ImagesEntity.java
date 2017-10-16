package com.feragusper.buenosairesantesydespues.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Fernando.Perez
 * @since 1.3
 * <p>
 * Images Entity used in the data layer.
 */
class ImagesEntity {

    @SerializedName("thumbnail")
    private ImageEntity thumbnail;

    @SerializedName("full")
    private ImageEntity full;

    public String getFullURL() {
        return full.getUrl();
    }

    public String getThumbnailURL() {
        return thumbnail.getUrl();
    }
}
