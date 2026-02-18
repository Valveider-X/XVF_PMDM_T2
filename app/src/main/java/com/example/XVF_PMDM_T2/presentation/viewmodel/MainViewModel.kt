package com.example.XVF_PMDM_T2.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.XVF_PMDM_T2.data.model.ItemDto
import com.example.XVF_PMDM_T2.data.remote.RetrofitClient
import com.example.XVF_PMDM_T2.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MainRepository(RetrofitClient.api)

    var state by mutableStateOf<UiState<List<ItemDto>>>(UiState.Loading)
        private set

    init {
        loadProducts()
    }

    fun loadProducts(page: Int = 1) {
        viewModelScope.launch {
            state = UiState.Loading
            state = try {
                UiState.Success(repository.getProducts(page))
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error cargando productos")
            }
        }
    }

}