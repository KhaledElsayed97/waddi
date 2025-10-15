package dev.khaled.waddi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.khaled.waddi.domain.model.Category
import dev.khaled.waddi.domain.model.Place
import dev.khaled.waddi.domain.usecases.GetPlacesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase
) : ViewModel() {
    
    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> = _places.asStateFlow()
    
    private val _selectedCategory = MutableStateFlow(Category.All)
    val selectedCategory: StateFlow<Category> = _selectedCategory.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    val filteredPlaces: StateFlow<List<Place>> = kotlinx.coroutines.flow.combine(
        _places,
        _selectedCategory
    ) { places, category ->
        if (category == Category.All) {
            places
        } else {
            places.filter { it.category.equals(category.value, ignoreCase = true) }
        }
    }.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        _places.value = listOf(
            Place(
                id = "1",
                name = "Caizo",
                description = "Best pizza in town with authentic Italian flavors",
                category = Category.Food.value,
                location = "123 Main Street, Downtown District",
                operatingTime = "Mon-Sun: 11:00 AM - 10:00 PM",
                rating = 4.5f,
                distance = "0.5 km",
                status = "Open",
                imageUrl = "https://i.postimg.cc/KvF6GqDb/img-food.jpg",
                isFeatured = true
            ),
            Place(
                id = "2",
                name = "Brown Nose",
                description = "Arcade games and entertainment center",
                category = Category.Drinks.value,
                location = "456 Gaming Avenue, Entertainment District",
                operatingTime = "Mon-Sun: 9:00 AM - 11:00 PM",
                rating = 4.2f,
                distance = "1.2 km",
                status = "Open",
                imageUrl = "https://i.postimg.cc/287fnRSZ/img-coffee1.webp",
                isFeatured = true
            ),
            Place(
                id = "3",
                name = "Brass Monkeys",
                description = "Artisanal desserts and pastries",
                category = Category.Entertainment.value,
                location = "789 Sweet Street, Food Court",
                operatingTime = "Mon-Sun: 8:00 AM - 9:00 PM",
                rating = 4.8f,
                distance = "0.8 km",
                status = "Open",
                imageUrl = "https://i.postimg.cc/JzzSJsgW/img-entertainment.jpg",
                isFeatured = true
            ),
            Place(
                id = "4",
                name = "City Centre Almaza",
                description = "Trendy clothing and accessories",
                category = Category.Shopping.value,
                location = "321 Fashion Boulevard, Shopping Mall",
                operatingTime = "Mon-Sun: 10:00 AM - 10:00 PM",
                rating = 4.1f,
                distance = "2.1 km",
                status = "Open",
                imageUrl = "https://i.postimg.cc/9MZkVZ4f/img-shopping.webp",
                isFeatured = true
            ),
            Place(
                id = "5",
                name = "Pizza Palace",
                description = "Authentic Italian pizza with wood-fired oven",
                category = Category.Food.value,
                location = "555 Pizza Street, Food District",
                operatingTime = "Mon-Sun: 12:00 PM - 11:00 PM",
                rating = 4.7f,
                distance = "0.3 km",
                status = "Open",
                imageUrl = "https://i.postimg.cc/KvF6GqDb/img-food.jpg",
                isFeatured = true
            ),
            Place(
                id = "6",
                name = "Coffee Corner",
                description = "Artisanal coffee and pastries",
                category = Category.Drinks.value,
                location = "777 Coffee Lane, Downtown",
                operatingTime = "Mon-Sun: 6:00 AM - 8:00 PM",
                rating = 4.4f,
                distance = "1.5 km",
                status = "Open",
                imageUrl = "https://i.postimg.cc/287fnRSZ/img-coffee1.webp",
                isFeatured = true
            )
        )
    }
    
    fun selectCategory(category: Category) {
        _selectedCategory.value = category
    }
    
    fun getPlaceById(placeId: String): Place? {
        return _places.value.find { it.id == placeId }
    }
    
    fun loadNearbyPlaces(
        latitude: Double = 30.0444,
        longitude: Double = 31.2357,
        radius: Int = 1500,
        type: String = "restaurant"
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            getPlacesUseCase(
                latitude = latitude,
                longitude = longitude,
                radius = radius,
                type = type
            ).fold(
                onSuccess = { places ->
                    _places.value = places
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _error.value = exception.message
                    _isLoading.value = false
                }
            )
        }
    }
}