package com.feragusper.buenosairesantesydespues.core.data

import com.feragusper.buenosairesantesydespues.core.common.dispatchers.BaadDispatcher
import com.feragusper.buenosairesantesydespues.core.common.dispatchers.Dispatcher
import com.feragusper.buenosairesantesydespues.core.common.error.NetworkConnectionException
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecord
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecordListPage
import com.feragusper.buenosairesantesydespues.core.network.api.HistoricalRecordApi
import com.feragusper.buenosairesantesydespues.core.network.mapper.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The backend is a single static JSON with the whole dataset, so the list is fetched once and
 * cached; paging is satisfied from the cache and a record lookup reads it directly.
 */
@Singleton
class DefaultHistoricalRecordRepository @Inject constructor(
    private val api: HistoricalRecordApi,
    @Dispatcher(BaadDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
) : HistoricalRecordRepository {

    private val mutex = Mutex()
    @Volatile
    private var cache: List<HistoricalRecord>? = null

    private suspend fun loadAll(): List<HistoricalRecord> {
        cache?.let { return it }
        return mutex.withLock {
            cache ?: fetchAll().also { cache = it }
        }
    }

    private suspend fun fetchAll(): List<HistoricalRecord> = withContext(ioDispatcher) {
        try {
            api.getRecords().toDomain().records
        } catch (e: IOException) {
            throw NetworkConnectionException(e)
        }
    }

    override suspend fun getRecords(page: Int, count: Int): HistoricalRecordListPage {
        val all = loadAll()
        return HistoricalRecordListPage(records = all, countTotal = all.size, pages = 1)
    }

    override suspend fun getRecord(id: String): HistoricalRecord =
        loadAll().firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Historical record $id not found")
}
