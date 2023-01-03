package com.example.dttproject.model

data class HouseItem(
    val id: Int,
    val image: String,
    val price: Long,
    val bedrooms: Int,
    val bathrooms: Int,
    val description: String,
    val zip: String,
    val size: Int,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val createdDate: String,
)
