package com.feragusper.buenosairesantesydespues.core.network.mapper

import android.util.Log
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecord
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecordListPage
import com.feragusper.buenosairesantesydespues.core.network.dto.AttachmentDto
import com.feragusper.buenosairesantesydespues.core.network.dto.HistoricalRecordDto
import com.feragusper.buenosairesantesydespues.core.network.dto.HistoricalRecordListPageDto

private const val TAG = "HistoricalRecordMapper"
private const val KEY_BEFORE = "antes"
private const val KEY_AFTER = "ahora"

private fun List<AttachmentDto>.withKey(key: String): AttachmentDto? =
    firstOrNull { it.title.contains(key) }

/**
 * Maps a network record to the domain model, mirroring the original data-layer logic:
 * credits join with newlines, geo split into lat/lng, and before/after images resolved
 * from the attachment whose title contains "antes" / "ahora".
 */
fun HistoricalRecordDto.toDomain(): HistoricalRecord {
    val latLng = customFields.geo.firstOrNull()
        ?.split(",")
        ?.mapNotNull { it.trim().toDoubleOrNull() }
        ?: emptyList()

    return HistoricalRecord(
        id = id.toString(),
        title = title,
        credits = customFields.creditsNow.firstOrNull().orEmpty().replace(" - ", "\n"),
        description = customFields.description.firstOrNull().orEmpty(),
        year = customFields.yearBefore.firstOrNull().orEmpty(),
        neighborhood = customFields.neighborhood.firstOrNull().orEmpty(),
        address = customFields.address.firstOrNull().orEmpty(),
        lat = latLng.getOrElse(0) { 0.0 },
        lng = latLng.getOrElse(1) { 0.0 },
        imageUrlBefore = attachments.withKey(KEY_BEFORE)?.images?.full?.url,
        imageUrlAfter = attachments.withKey(KEY_AFTER)?.images?.full?.url,
        thumbnailUrl = attachments.withKey(KEY_BEFORE)?.images?.thumbnail?.url,
        shareUrl = null,
    )
}

/**
 * Maps a page of records, skipping any individual record that fails to transform
 * (matching the original per-item try/catch behavior).
 */
fun HistoricalRecordListPageDto.toDomain(): HistoricalRecordListPage =
    HistoricalRecordListPage(
        records = posts.mapNotNull { dto ->
            runCatching { dto.toDomain() }
                .onFailure { Log.e(TAG, "Failed to transform record id=${dto.id}", it) }
                .getOrNull()
        },
        countTotal = countTotal,
        pages = pages,
    )
