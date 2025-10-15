package dev.khaled.waddi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.khaled.waddi.MainViewModel
import dev.khaled.waddi.R
import dev.khaled.waddi.domain.model.Category
import dev.khaled.waddi.ui.components.FeaturedPlaceCard
import dev.khaled.waddi.ui.components.PlaceCard

@Composable
fun HomeScreen(
    onPlaceClick: (String) -> Unit = {},
    viewModel: MainViewModel
) {
    val places by viewModel.filteredPlaces.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        ////////////////////// Top bar with title and search icon///////////////////////////////////
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "WADDY",
                    fontFamily = FontFamily(Font(R.font.fascinate_regular)),
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

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

        /////////////////////////////// Categories /////////////////////////////////////////////////
        item {
            LazyRow(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(Category.entries) { category ->
                    FilterChip(
                        onClick = { viewModel.selectCategory(category) },
                        label = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                // Category icon above text
                                Image(
                                    painter = painterResource(id = category.icon),
                                    contentDescription = category.value,
                                    modifier = Modifier.size(36.dp),
                                    contentScale = ContentScale.Fit
                                )
                                // Category text below icon
                                Text(
                                    text = category.value,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        },
                        selected = selectedCategory == category,
                        modifier = Modifier.padding(vertical = 4.dp),
                        border = null,
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = if (selectedCategory == category)
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            else
                                Color.Transparent,
                            selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                        )
                    )
                }
            }
        }

        //////////////// Featured places section///////////////////////////////////////////
        item {
            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Featured Places",
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(places.filter { it.isFeatured == true }) { place ->
                        FeaturedPlaceCard(
                            place = place,
                            onClick = onPlaceClick
                        )
                    }
                }
            }
        }

        //////////////////////// All places section//////////////////////////////////
        item {
            Text(
                text = "All Places",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
            )
        }

        // All places list
        items(places) { place ->
            PlaceCard(place = place, onClick = onPlaceClick)
        }
    }
}


