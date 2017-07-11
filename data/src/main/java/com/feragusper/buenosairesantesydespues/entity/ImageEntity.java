package com.feragusper.buenosairesantesydespues.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Fernando.Perez
 * @since 1.3
 * <p>
 * Image Entity used in the data layer.
 */
class ImageEntity {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }
}
