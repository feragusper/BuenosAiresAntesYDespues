package com.feragusper.buenosairesantesydespues.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Fernando.Perez
 * @since 1.3
 * <p>
 * Image Entity used in the data layer.
 */
public class ImageEntity {

    @SerializedName("url")
    private String url;

    public ImageEntity(String url) {
        this.url = url;
    }

    String getUrl() {
        return url;
    }
}
