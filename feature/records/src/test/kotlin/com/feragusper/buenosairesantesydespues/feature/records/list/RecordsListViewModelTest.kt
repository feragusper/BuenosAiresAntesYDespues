package com.feragusper.buenosairesantesydespues.feature.records.list

import app.cash.turbine.test
import com.feragusper.buenosairesantesydespues.core.common.error.NetworkConnectionException
import com.feragusper.buenosairesantesydespues.core.data.HistoricalRecordRepository
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecord
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecordListPage
import com.feragusper.buenosairesantesydespues.feature.records.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RecordsListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun record(id: String) = HistoricalRecord(id = id, title = "R$id")

    @Test
    fun `loads first page on init and marks end reached when all records fetched`() = runTest {
        val repository = object : HistoricalRecordRepository {
            override suspend fun getRecords(page: Int, count: Int) =
                HistoricalRecordListPage(records = listOf(record("1"), record("2")), countTotal = 2, pages = 1)

            override suspend fun getRecord(id: String) = record(id)
        }

        val viewModel = RecordsListViewModel(repository)

        viewModel.uiState.test {
            val state = expectMostRecentItem()
            assertEquals(2, state.records.size)
            assertFalse(state.isLoading)
            assertTrue(state.endReached)
        }
    }

    @Test
    fun `exposes a user message when the first page fails`() = runTest {
        val repository = object : HistoricalRecordRepository {
            override suspend fun getRecords(page: Int, count: Int): HistoricalRecordListPage =
                throw NetworkConnectionException()

            override suspend fun getRecord(id: String) = record(id)
        }

        val viewModel = RecordsListViewModel(repository)

        viewModel.uiState.test {
            val state = expectMostRecentItem()
            assertEquals("No hay conexión a Internet", state.errorMessage)
            assertTrue(state.records.isEmpty())
        }
    }
}
