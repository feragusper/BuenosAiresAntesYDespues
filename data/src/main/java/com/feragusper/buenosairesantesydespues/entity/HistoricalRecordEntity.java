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

    public HistoricalRecordEntity(Long historicalRecordId, String title, CustomFieldsEntity customFields, List<AttachmentEntity> attachments) {
        this.historicalRecordId = historicalRecordId;
        this.title = title;
        this.customFields = customFields;
        this.attachments = attachments;
    }

    public String getHistoricalRecordId() {
        return historicalRecordId.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getCreditsNow() {
        return customFields.getCreditsNow();
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
        final AttachmentEntity attachmentWithKey = getAttachmentWithKey(AttachmentEntity.KEY_BEFORE);
        if (attachmentWithKey != null) {
            return attachmentWithKey.getFullImageURL();
        }

        return null;
    }

    private AttachmentEntity getAttachmentWithKey(String key) {
        for (AttachmentEntity attachment : attachments) {
            if (attachment.isImageKey(key)) {
                return attachment;
            }
        }

        return null;
    }

    public String getImageURLAfter() {
        final AttachmentEntity attachmentWithKey = getAttachmentWithKey(AttachmentEntity.KEY_AFTER);
        if (attachmentWithKey != null) {
            return attachmentWithKey.getFullImageURL();
        }
        return null;
    }

    public String getImageURLThumbnail() {
        final AttachmentEntity attachmentWithKey = getAttachmentWithKey(AttachmentEntity.KEY_BEFORE);
        if (attachmentWithKey != null) {
            return attachmentWithKey.getThumbnailImageURL();
        }
        return null;
    }

    public String getShareURL() {
        return null;
    }
}