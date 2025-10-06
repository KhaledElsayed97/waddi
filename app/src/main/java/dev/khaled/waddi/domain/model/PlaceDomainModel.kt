package dev.khaled.waddi.domain.model

data class PlaceDomainModel(
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
