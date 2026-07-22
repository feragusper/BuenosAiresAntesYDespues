package com.feragusper.buenosairesantesydespues.core.model

/**
 * A historical record: a "before and after" of a Buenos Aires location.
 * Domain model, decoupled from the network/JSON representation.
 */
data class HistoricalRecord(
    val id: String,
    val title: String = "",
    val credits: String = "",
    val description: String = "",
    val year: String = "",
    val neighborhood: String = "",
    val address: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val imageUrlBefore: String? = null,
    val imageUrlAfter: String? = null,
    val thumbnailUrl: String? = null,
    val shareUrl: String? = null,
)
