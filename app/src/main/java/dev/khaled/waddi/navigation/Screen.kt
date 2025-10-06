package dev.khaled.waddi.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import dev.khaled.waddi.R

sealed class Screen(val route: String, val title: String, val icon: Int? = null) {
    object Home : Screen("home", "Home", R.drawable.ic_home1)
    object Favourites : Screen("favourites", "Favourites", R.drawable.ic_favourites1)
    object Place : Screen("place/{placeId}", "Place") {
        fun createRoute(placeId: String) = "place/$placeId"
    }

    object Magic : Screen("magic", "Magic", R.drawable.ic_magic_wand)
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Favourites,
    Screen.Magic
)


