package com.example.XVF_PMDM_T2.data.repository

import com.example.XVF_PMDM_T2.data.model.ItemDto
import com.example.XVF_PMDM_T2.data.remote.ApiService

class MainRepository(private val api: ApiService) {
    suspend fun getProducts(page: Int = 1): List<ItemDto> =
        api.getProducts(page).results
}