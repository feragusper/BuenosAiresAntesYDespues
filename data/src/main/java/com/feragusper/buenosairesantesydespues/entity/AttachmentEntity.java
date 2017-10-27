package com.feragusper.buenosairesantesydespues.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Fernando.Perez
 * @since 1.3
 * <p>
 * Attachment Entity used in the data layer.
 */
public class AttachmentEntity {

    public static final String KEY_AFTER = "ahora";
    public static final String KEY_BEFORE = "antes";

    @SerializedName("title")
    private String title;

    @SerializedName("images")
    private ImagesEntity images;

    public AttachmentEntity(String title, ImagesEntity imagesEntities) {
        this.title = title;
        this.images = imagesEntities;
    }

    boolean isImageKey(String key) {
        return title.contains(key);
    }

    String getFullImageURL() {
        return images.getFullURL();
    }

    String getThumbnailImageURL() {
        return images.getThumbnailURL();
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
