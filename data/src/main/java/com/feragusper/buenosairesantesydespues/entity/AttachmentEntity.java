package com.feragusper.buenosairesantesydespues.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Fernando.Perez
 * @since 1.3
 * <p>
 * Attachment Entity used in the data layer.
 */
class AttachmentEntity {

    static final String KEY_AFTER = "ahora";
    static final String KEY_BEFORE = "antes";
    @SerializedName("title")
    private String title;

    @SerializedName("images")
    private ImagesEntity images;

    boolean isImageKey(String key) {
        return title.contains(key);
    }

    String getFullImageURL() {
        return images.getFullURL();
    }

    String getThumbnailImageURL() {
        return images.getThumbnailURL();
    }
}
