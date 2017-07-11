package com.feragusper.buenosairesantesydespues.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * HistoricalRecord Entity used in the data layer.
 */
public class HistoricalRecordEntity {

    @SerializedName("id")
    private Long historicalRecordId;

    @SerializedName("title")
    private String title;

    @SerializedName("custom_fields")
    private CustomFieldsEntity customFields;

    @SerializedName("attachments")
    private List<AttachmentEntity> attachments;

    public HistoricalRecordEntity() {
        //empty
    }

    public String getHistoricalRecordId() {
        return historicalRecordId.toString();
    }

    public String getTitle() {
        return title.toString();
    }

    public String getCreditsNow() {
        return customFields.getCreditsNow().toString();
    }

    public String getDescription() {
        return customFields.getDescription();
    }

    public String getGeo() {
        return customFields.getGeo();
    }

    public String getYearBefore() {
        return customFields.getYearBefore();
    }

    public String getNeighborhood() {
        return customFields.getNeighborhood();
    }

    public String getAddress() {
        return customFields.getAddress();
    }

    public Long getId() {
        return historicalRecordId;
    }

    public String getImageURLBefore() {
        return getAttachmentWithKey(AttachmentEntity.KEY_BEFORE).getFullImageURL();
    }

    public String getImageURLAfter() {
        return getAttachmentWithKey(AttachmentEntity.KEY_AFTER).getFullImageURL();
    }

    public String getImageURLThumbnail() {
        return getAttachmentWithKey(AttachmentEntity.KEY_BEFORE).getThumbnailImageURL();
    }

    private AttachmentEntity getAttachmentWithKey(String key) {
        for (AttachmentEntity attachment : attachments) {
            if (attachment.isImageKey(key)) {
                return attachment;
            }
        }

        return null;
    }

    public String getShareURL() {
        return null;
    }
}