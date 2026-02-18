package com.example.XVF_PMDM_T2.data.model

data class ItemDto(

    val _id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val active: Boolean
)
