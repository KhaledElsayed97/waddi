package dev.khaled.waddi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.khaled.waddi.navigation.BottomNavigationBar
import dev.khaled.waddi.navigation.Screen
import dev.khaled.waddi.ui.screens.FavouritesScreen
import dev.khaled.waddi.ui.screens.HomeScreen
import dev.khaled.waddi.ui.screens.PlaceScreen
import dev.khaled.waddi.ui.theme.WaddiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaddiTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Hide bottom navigation bar on place screen
    val showBottomBar = currentRoute?.startsWith("place/") != true

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onPlaceClick = { placeId ->
                        navController.navigate(Screen.Place.createRoute(placeId))
                    }
                )
            }
            composable(Screen.Favourites.route) {
                FavouritesScreen()
            }
            composable(Screen.Place.route) { backStackEntry ->
                val placeId = backStackEntry.arguments?.getString("placeId") ?: "1"
                PlaceScreen(
                    placeId = placeId,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}