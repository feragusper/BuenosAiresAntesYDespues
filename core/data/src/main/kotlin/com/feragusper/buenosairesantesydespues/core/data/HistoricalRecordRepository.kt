package com.feragusper.buenosairesantesydespues.core.data

import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecord
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecordListPage

/** Provides access to [HistoricalRecord] data. */
interface HistoricalRecordRepository {

    /** Fetch one page of records. */
    suspend fun getRecords(page: Int, count: Int): HistoricalRecordListPage

    /** Fetch a single record by id. */
    suspend fun getRecord(id: String): HistoricalRecord

    companion object {
        const val ITEMS_PER_PAGE = 20
    }
}
