package com.feragusper.buenosairesantesydespues.core.model

/**
 * A page of historical records, plus the paging metadata returned by the API.
 */
data class HistoricalRecordListPage(
    val records: List<HistoricalRecord>,
    val countTotal: Int,
    val pages: Int,
)
