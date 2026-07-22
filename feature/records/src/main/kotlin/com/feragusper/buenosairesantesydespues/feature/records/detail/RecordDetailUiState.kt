package com.feragusper.buenosairesantesydespues.feature.records.detail

import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecord

/** Immutable state rendered by [RecordDetailScreen]. */
data class RecordDetailUiState(
    val record: HistoricalRecord? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)
