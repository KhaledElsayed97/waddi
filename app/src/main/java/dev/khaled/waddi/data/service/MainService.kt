package dev.khaled.waddi.data.service

import dev.khaled.waddi.data.model.GooglePlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesApiService {
    @GET("maps/api/place/nearbysearch/json")
    suspend fun getNearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") apiKey: String,
        @Query("next_page_token") nextPageToken: String? = null
    ): GooglePlacesResponse
}