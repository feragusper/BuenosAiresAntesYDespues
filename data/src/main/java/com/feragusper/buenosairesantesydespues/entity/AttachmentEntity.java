package com.feragusper.buenosairesantesydespues.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Fernando.Perez
 * @since 1.3
 * <p>
 * Attachment Entity used in the data layer.
 */
class AttachmentEntity {

    protected static final String KEY_BEFORE = "antes";
    public static final String KEY_AFTER = "ahora";

    @SerializedName("title")
    private String title;

    @SerializedName("images")
    private ImagesEntity images;

    public boolean isImageKey(String key) {
        return title.contains(key);
    }

    public String getFullImageURL() {
        return images.getFullURL();
    }

    public String getThumbnailImageURL() {
        return images.getThumbnailURL();
    }
}
