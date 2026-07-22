package com.feragusper.buenosairesantesydespues.core.data

import com.feragusper.buenosairesantesydespues.core.common.dispatchers.BaadDispatcher
import com.feragusper.buenosairesantesydespues.core.common.dispatchers.Dispatcher
import com.feragusper.buenosairesantesydespues.core.common.error.NetworkConnectionException
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecord
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecordListPage
import com.feragusper.buenosairesantesydespues.core.network.api.HistoricalRecordApi
import com.feragusper.buenosairesantesydespues.core.network.mapper.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class DefaultHistoricalRecordRepository @Inject constructor(
    private val api: HistoricalRecordApi,
    @Dispatcher(BaadDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
) : HistoricalRecordRepository {

    override suspend fun getRecords(page: Int, count: Int): HistoricalRecordListPage =
        withContext(ioDispatcher) {
            try {
                api.getPosts(count = count, page = page).toDomain()
            } catch (e: IOException) {
                throw NetworkConnectionException(e)
            }
        }

    override suspend fun getRecord(id: String): HistoricalRecord =
        withContext(ioDispatcher) {
            try {
                api.getPost(postId = id).post.toDomain()
            } catch (e: IOException) {
                throw NetworkConnectionException(e)
            }
        }
}
