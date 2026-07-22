package com.feragusper.buenosairesantesydespues.core.network.api

import com.feragusper.buenosairesantesydespues.core.network.dto.HistoricalRecordListPageDto
import retrofit2.http.GET

/**
 * Data source for Buenos Aires Antes y Después.
 *
 * The original WordPress JSON API is gone — the website is now a static SPA with its data
 * baked into its JS bundle. That same dataset (245 records, identical shape) is extracted and
 * hosted as a static JSON on the project's GitHub Pages, which this app consumes.
 */
interface HistoricalRecordApi {

    @GET("records.json")
    suspend fun getRecords(): HistoricalRecordListPageDto

    companion object {
        const val BASE_URL = "https://feragusper.github.io/BuenosAiresAntesYDespues/"
    }
}
