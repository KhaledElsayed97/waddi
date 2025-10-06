package dev.khaled.waddi.data.model

data class Place(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val location: String,
    val operatingTime: String,
    val rating: Float,
    val distance: String,
    val status: String,
    val imageRes: Int
)
