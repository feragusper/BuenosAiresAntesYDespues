package com.feragusper.buenosairesantesydespues.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of a historical record, matching the WordPress JSON API shape.
 * Custom fields arrive as single-element arrays of strings.
 */
@Serializable
data class HistoricalRecordDto(
    @SerialName("id") val id: Long = 0,
    @SerialName("title") val title: String = "",
    @SerialName("custom_fields") val customFields: CustomFieldsDto = CustomFieldsDto(),
    @SerialName("attachments") val attachments: List<AttachmentDto> = emptyList(),
)

@Serializable
data class CustomFieldsDto(
    @SerialName("creditos_ahora") val creditsNow: List<String> = emptyList(),
    @SerialName("creditos_antigua") val creditsBefore: List<String> = emptyList(),
    @SerialName("descripcion") val description: List<String> = emptyList(),
    @SerialName("anio_antes") val yearBefore: List<String> = emptyList(),
    @SerialName("direccion") val address: List<String> = emptyList(),
    @SerialName("barrio") val neighborhood: List<String> = emptyList(),
    @SerialName("geo") val geo: List<String> = emptyList(),
)

@Serializable
data class AttachmentDto(
    @SerialName("title") val title: String = "",
    @SerialName("images") val images: ImagesDto = ImagesDto(),
)

@Serializable
data class ImagesDto(
    @SerialName("thumbnail") val thumbnail: ImageDto = ImageDto(),
    @SerialName("full") val full: ImageDto = ImageDto(),
)

@Serializable
data class ImageDto(
    @SerialName("url") val url: String = "",
)

@Serializable
data class HistoricalRecordListPageDto(
    @SerialName("posts") val posts: List<HistoricalRecordDto> = emptyList(),
    @SerialName("count_total") val countTotal: Int = 0,
    @SerialName("pages") val pages: Int = 0,
)

@Serializable
data class PostResponseDto(
    @SerialName("post") val post: HistoricalRecordDto = HistoricalRecordDto(),
)
