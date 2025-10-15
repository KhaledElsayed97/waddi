package dev.khaled.waddi.domain.usecases

import dev.khaled.waddi.domain.model.Place
import dev.khaled.waddi.domain.repository.MainRepository

class GetPlacesUseCase(
    private val repository: MainRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        radius: Int = 1500,
        type: String = "restaurant",
        nextPageToken: String? = null
    ): Result<List<Place>> {
        return Result.success(emptyList())
    }
}