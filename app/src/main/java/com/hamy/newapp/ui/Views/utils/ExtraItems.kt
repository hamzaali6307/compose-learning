package com.hamy.newapp.ui.Views.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

object ExtraItems {

    val BottomTabsItem = listOf( // items for bottom tabs
        Screen.Home,
        Screen.Search,
        Screen.Profile
    )
    val NavDrawerItem = listOf( // items for bottom tabs
        Screen.Home,
        Screen.Profile
    )


    sealed class Screen(val route: String, val icon: ImageVector, val label: String?) {
        object Home : Screen("home", Icons.Default.Home, "Home")
        object Splash : Screen("splash", Icons.Default.Home, "Splash")
        object Login : Screen("login", Icons.Default.Home, "Login")
        object DashBoard : Screen("dashboard", Icons.Default.Home, "DashBoard")
        object Profile : Screen("profile", Icons.Default.Person, "Profile")
        object Search : Screen("search", Icons.Default.Search, "Search")
    }
}