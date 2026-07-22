package com.feragusper.buenosairesantesydespues.feature.records.list

import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecord

/** Immutable state rendered by [RecordsListScreen]. */
data class RecordsListUiState(
    val records: List<HistoricalRecord> = emptyList(),
    val isLoading: Boolean = false,
    val isPaginating: Boolean = false,
    val endReached: Boolean = false,
    val errorMessage: String? = null,
) {
    val isEmpty: Boolean get() = records.isEmpty() && !isLoading
}
