package dev.khaled.waddi.domain.repository

import dev.khaled.waddi.data.model.PlaceDto

interface MainRepository {
    suspend fun getNearbyPlaces(
        latitude: Double,
        longitude: Double,
        radius: Int = 1500,
        type: String = "restaurant",
        nextPageToken: String? = null
    ): Result<List<PlaceDto>>
}