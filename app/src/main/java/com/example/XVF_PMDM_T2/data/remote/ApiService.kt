package com.example.XVF_PMDM_T2.data.remote

import com.example.XVF_PMDM_T2.data.model.PagedResponse
import com.example.XVF_PMDM_T2.data.model.ItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int = 1
    ): PagedResponse<ItemDto>
}