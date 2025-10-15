package dev.khaled.waddi.data.model

import com.google.gson.annotations.SerializedName

data class GooglePlacesResponse(
    @SerializedName("results")
    val results: List<PlaceDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("next_page_token")
    val nextPageToken: String? = null
)

data class PlaceDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("geometry")
    val geometry: Geometry,
    @SerializedName("vicinity")
    val vicinity: String,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("user_ratings_total")
    val userRatingsTotal: Int? = null,
    @SerializedName("price_level")
    val priceLevel: Int? = null,
    @SerializedName("types")
    val types: List<String> = emptyList(),
    @SerializedName("photos")
    val photos: List<PlacePhoto>? = null,
    @SerializedName("opening_hours")
    val openingHours: OpeningHours? = null,
    @SerializedName("business_status")
    val businessStatus: String? = null,
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("icon_background_color")
    val iconBackgroundColor: String? = null,
    @SerializedName("icon_mask_base_uri")
    val iconMaskBaseUri: String? = null
)


data class Geometry(
    @SerializedName("location")
    val location: Location
)


data class Location(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)


data class PlacePhoto(
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int,
    @SerializedName("photo_reference")
    val photoReference: String
)

data class OpeningHours(
    @SerializedName("open_now")
    val openNow: Boolean,
    @SerializedName("periods")
    val periods: List<Period>? = null,
    @SerializedName("weekday_text")
    val weekdayText: List<String>? = null
)

data class Period(
    @SerializedName("close")
    val close: Time? = null,
    @SerializedName("open")
    val open: Time
)

data class Time(
    @SerializedName("day")
    val day: Int,
    @SerializedName("time")
    val time: String
)
