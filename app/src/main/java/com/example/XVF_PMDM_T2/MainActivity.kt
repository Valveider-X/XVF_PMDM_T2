package com.example.XVF_PMDM_T2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Surface

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.XVF_PMDM_T2.presentation.viewmodel.MainViewModel
import com.example.XVF_PMDM_T2.ui.screens.ProductosScreen
import com.example.XVF_PMDM_T2.ui.theme.RetrofitProductsApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitProductsApiTheme {
                Surface() {
                    val vm: MainViewModel = viewModel()
                    ProductosScreen(vm)
                }
            }
        }
    }
}
