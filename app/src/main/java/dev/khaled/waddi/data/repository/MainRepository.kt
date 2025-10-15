package dev.khaled.waddi.data.repository

import dev.khaled.waddi.data.model.PlaceDto
import dev.khaled.waddi.data.service.GooglePlacesApiService
import dev.khaled.waddi.domain.repository.MainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val apiService: GooglePlacesApiService,
    private val apiKey: String
) : MainRepository {
    
    override suspend fun getNearbyPlaces(
        latitude: Double,
        longitude: Double,
        radius: Int,
        type: String,
        nextPageToken: String?
    ): Result<List<PlaceDto>> {
        return try {
            val location = "$latitude,$longitude"
            
            val response = apiService.getNearbyPlaces(
                location = location,
                radius = radius,
                type = type,
                apiKey = apiKey,
                nextPageToken = nextPageToken
            )
            
            if (response.status == "OK") {
                Result.success(response.results)
            } else {
                Result.failure(Exception("API Error: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}