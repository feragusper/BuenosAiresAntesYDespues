package com.feragusper.buenosairesantesydespues.feature.records.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feragusper.buenosairesantesydespues.core.common.error.NetworkConnectionException
import com.feragusper.buenosairesantesydespues.core.data.HistoricalRecordRepository
import com.feragusper.buenosairesantesydespues.core.data.HistoricalRecordRepository.Companion.ITEMS_PER_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsListViewModel @Inject constructor(
    private val repository: HistoricalRecordRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecordsListUiState())
    val uiState: StateFlow<RecordsListUiState> = _uiState.asStateFlow()

    private var page = 0
    private var countTotal = 0

    init {
        loadNextPage()
    }

    /** Loads the first page again after an error, or the next page while paginating. */
    fun retry() {
        if (_uiState.value.records.isEmpty()) {
            page = 0
            loadNextPage()
        } else {
            loadNextPage()
        }
    }

    fun loadNextPage() {
        val state = _uiState.value
        if (state.isLoading || state.isPaginating || state.endReached) return

        val isFirstPage = state.records.isEmpty()
        _uiState.update {
            it.copy(
                isLoading = isFirstPage,
                isPaginating = !isFirstPage,
                errorMessage = null,
            )
        }

        viewModelScope.launch {
            try {
                val nextPage = page + 1
                val result = repository.getRecords(page = nextPage, count = ITEMS_PER_PAGE)
                page = nextPage
                countTotal = result.countTotal
                val merged = _uiState.value.records + result.records
                _uiState.update {
                    it.copy(
                        records = merged,
                        isLoading = false,
                        isPaginating = false,
                        endReached = merged.size >= countTotal,
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isPaginating = false,
                        errorMessage = e.toUserMessage(),
                    )
                }
            }
        }
    }
}

internal fun Throwable.toUserMessage(): String = when (this) {
    is NetworkConnectionException -> "No hay conexión a Internet"
    else -> "Ocurrió un error"
}
