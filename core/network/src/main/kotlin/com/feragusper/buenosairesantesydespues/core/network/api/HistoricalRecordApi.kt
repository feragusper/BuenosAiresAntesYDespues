package com.feragusper.buenosairesantesydespues.core.network.api

import com.feragusper.buenosairesantesydespues.core.network.dto.HistoricalRecordListPageDto
import com.feragusper.buenosairesantesydespues.core.network.dto.PostResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/** Retrofit definition of the Buenos Aires Antes y Después backend (WordPress JSON API). */
interface HistoricalRecordApi {

    @GET("get_posts/")
    suspend fun getPosts(
        @Query("count") count: Int,
        @Query("page") page: Int,
    ): HistoricalRecordListPageDto

    @GET("get_post/")
    suspend fun getPost(
        @Query("post_id") postId: String,
    ): PostResponseDto

    companion object {
        const val BASE_URL = "http://bsasantesydespues.com.ar/admin/api/"
    }
}
