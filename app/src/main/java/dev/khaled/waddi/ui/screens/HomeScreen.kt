package dev.khaled.waddi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.khaled.waddi.R
import dev.khaled.waddi.ui.components.StatusChip

// Data model for places
data class Place(
    val id: String,
    val name: String,
    val description: String,
    val status: String,
    val imageRes: Int,
    val category: String,
    val rating: Float,
    val distance: String,
    val location: String,
    val operatingTime: String
)

// Data model for featured places
data class FeaturedPlace(
    val id: Int,
    val title: String,
    val status: String,
    val imageRes: Int,
    val category: String
)

// Filter categories
val filterCategories = listOf(
    "All", "Food", "Desserts", "Games", "Shopping", 
    "Entertainment", "Sports", "Health", "Education", "Travel"
)

// Sample featured places data
val featuredPlaces = listOf(
    FeaturedPlace(1, "Pizza Palace", "Open", getCategoryDrawable("Food"), "Food"),
    FeaturedPlace(2, "Game Zone", "Close Soon", getCategoryDrawable("Games"), "Games"),
    FeaturedPlace(3, "Sweet Dreams", "Open", getCategoryDrawable("Desserts"), "Desserts"),
    FeaturedPlace(4, "Fashion Store", "Open", getCategoryDrawable("Shopping"), "Shopping"),
    FeaturedPlace(5, "Cinema Max", "Close Soon", getCategoryDrawable("Entertainment"), "Entertainment"),
    FeaturedPlace(6, "Gym Fit", "Open", getCategoryDrawable("Sports"), "Sports")
)

// Function to get drawable resource based on category
fun getCategoryDrawable(category: String): Int {
    return when (category.lowercase()) {
        "food" -> R.drawable.ic_food_placeholder
        "desserts" -> R.drawable.ic_dessert_placeholder
        "games" -> R.drawable.ic_games_placeholder
        "shopping" -> R.drawable.ic_shopping_placeholder
        "entertainment" -> R.drawable.ic_entertainment_placeholder
        "sports" -> R.drawable.ic_sports_placeholder
        "health" -> R.drawable.ic_sports_placeholder // Using sports icon for health
        "education" -> R.drawable.ic_entertainment_placeholder // Using entertainment icon for education
        "travel" -> R.drawable.ic_entertainment_placeholder // Using entertainment icon for travel
        else -> R.drawable.ic_food_placeholder // Default to food icon
    }
}

// Sample all places data
val allPlaces = listOf(
    Place("1", "Pizza Palace", "Best pizza in town with authentic Italian flavors", "Open", getCategoryDrawable("Food"), "Food", 4.5f, "0.5 km", "123 Main Street, Downtown District", "Mon-Sun: 11:00 AM - 10:00 PM"),
    Place("2", "Game Zone", "Arcade games and entertainment center", "Close Soon", getCategoryDrawable("Games"), "Games", 4.2f, "1.2 km", "456 Gaming Avenue, Entertainment District", "Mon-Sun: 10:00 AM - 11:00 PM"),
    Place("3", "Sweet Dreams", "Artisanal desserts and pastries", "Open", getCategoryDrawable("Desserts"), "Desserts", 4.8f, "0.8 km", "789 Sweet Street, Food Court", "Mon-Sun: 8:00 AM - 9:00 PM"),
    Place("4", "Fashion Store", "Trendy clothing and accessories", "Open", getCategoryDrawable("Shopping"), "Shopping", 4.1f, "2.1 km", "321 Fashion Boulevard, Shopping Mall", "Mon-Sat: 10:00 AM - 8:00 PM, Sun: 12:00 PM - 6:00 PM"),
    Place("5", "Cinema Max", "Latest movies in comfortable theaters", "Close Soon", getCategoryDrawable("Entertainment"), "Entertainment", 4.3f, "1.5 km", "654 Cinema Plaza, Entertainment Complex", "Mon-Sun: 12:00 PM - 11:00 PM"),
    Place("6", "Gym Fit", "24/7 fitness center with modern equipment", "Open", getCategoryDrawable("Sports"), "Sports", 4.6f, "0.3 km", "987 Fitness Lane, Sports Center", "24/7 Open"),
    Place("7", "Coffee Corner", "Premium coffee and light snacks", "Open", getCategoryDrawable("Food"), "Food", 4.4f, "1.0 km", "147 Coffee Street, Business District", "Mon-Fri: 6:00 AM - 6:00 PM, Sat-Sun: 8:00 AM - 4:00 PM"),
    Place("8", "Book Store", "Wide selection of books and magazines", "Close Soon", getCategoryDrawable("Education"), "Education", 4.0f, "1.8 km", "258 Library Road, Academic Area", "Mon-Sat: 9:00 AM - 7:00 PM, Sun: 12:00 PM - 5:00 PM"),
    Place("9", "Spa Relax", "Wellness and relaxation services", "Open", getCategoryDrawable("Health"), "Health", 4.7f, "2.5 km", "369 Wellness Way, Health District", "Mon-Sun: 9:00 AM - 8:00 PM"),
    Place("10", "Travel Agency", "Travel planning and booking services", "Close Soon", getCategoryDrawable("Travel"), "Travel", 4.2f, "3.2 km", "741 Travel Terrace, Business Plaza", "Mon-Fri: 9:00 AM - 5:00 PM, Sat: 10:00 AM - 2:00 PM")
)

@Composable
fun FeaturedPlaceCard(place: FeaturedPlace) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Accent background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        RoundedCornerShape(12.dp)
                    )
            )
            
            // Content layout
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Status badge
                StatusChip(status = place.status)
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Centered icon
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = place.imageRes),
                        contentDescription = place.title,
                        modifier = Modifier
                            .sizeIn(minWidth = 32.dp, minHeight = 32.dp, maxWidth = 80.dp, maxHeight = 80.dp)
                            .aspectRatio(1f),
                        contentScale = ContentScale.Fit
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Title
                Text(
                    text = place.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun PlaceListItem(
    place: Place,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick(place.id) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Place image
            Image(
                painter = painterResource(id = place.imageRes),
                contentDescription = place.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Place details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 4.dp)
            ) {
                // Title and status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = place.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    
                    StatusChip(status = place.status)
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Description
                Text(
                    text = place.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Rating and distance
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "★ ${place.rating}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        Text(
                            text = place.distance,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    Text(
                        text = place.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    onPlaceClick: (String) -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("All") }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        // Top bar with title and search icon
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bold title in top left
                Column {
                    Text(
                        text = "Waddi",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "All currently open places around you",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }

                // Search icon in top right
                IconButton(
                    onClick = { /* TODO: Handle search action */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        
        // Filter chips row
        item {
            LazyRow(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filterCategories) { category ->
                    FilterChip(
                        onClick = { selectedFilter = category },
                        label = {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                        selected = selectedFilter == category,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
        
        // Featured places section
        item {
            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Featured Places",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(featuredPlaces) { place ->
                        FeaturedPlaceCard(place = place)
                    }
                }
            }
        }
        
        // All places section header
        item {
            Text(
                text = "All Places",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
            )
        }
        
        // All places list
        items(allPlaces) { place ->
            PlaceListItem(place = place, onClick = onPlaceClick)
        }
    }
}


