package com.feragusper.buenosairesantesydespues.feature.records.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feragusper.buenosairesantesydespues.core.data.HistoricalRecordRepository
import com.feragusper.buenosairesantesydespues.feature.records.list.toUserMessage
import com.feragusper.buenosairesantesydespues.feature.records.navigation.RecordDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: HistoricalRecordRepository,
) : ViewModel() {

    private val recordId: String =
        requireNotNull(savedStateHandle[RecordDetailRoute.ARG_ID]) {
            "RecordDetail requires a '${RecordDetailRoute.ARG_ID}' argument"
        }

    private val _uiState = MutableStateFlow(RecordDetailUiState())
    val uiState: StateFlow<RecordDetailUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            try {
                val record = repository.getRecord(recordId)
                _uiState.update { it.copy(record = record, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.toUserMessage())
                }
            }
        }
    }
}
