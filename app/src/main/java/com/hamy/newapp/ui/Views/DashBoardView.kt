package com.hamy.newapp.ui.Views

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.hamy.newapp.ui.Views.ui.theme.NewAppTheme
import androidx.navigation.compose.*
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.hamy.newapp.R
import com.hamy.newapp.ui.Views.utils.ExtraItems
import com.hamy.newapp.ui.Views.Modal.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState =
        androidx.compose.material3.rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                navController = navController,
                drawerState = drawerState,
                scope = scope
            )
        }) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "H Movie's", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "menu")
                        }
                    })
            },
            bottomBar = { BottomNavigationBar(navController) }
        ) {
            NavigationHost(navController)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = com.hamy.newapp.ui.Views.ui.theme.Purple40,
        contentColor = Color.White,

        ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        ExtraItems.BottomTabsItem.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.route) },
                label = { Text(screen.route, color = Color.White) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    var selectedItem: MutableState<ExtraItems.Screen> =
        remember { mutableStateOf(ExtraItems.Screen.Home) }

    Column(
        Modifier
            .background(Color.White)
            .fillMaxHeight()
            .width(300.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = "H Movie's",
                style = MaterialTheme.typography.body1
            )
        }

        NavigationDrawerItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            label = { Text("home") },
            selected = true,
            onClick = {
                selectedItem.value = ExtraItems.Screen.Home
                navController.navigate(ExtraItems.Screen.Home.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch { drawerState.close() }
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        NavigationDrawerItem(
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            label = { Text("Profile") },
            selected = false,
            onClick = {
                selectedItem.value = ExtraItems.Screen.Profile
                navController.navigate(ExtraItems.Screen.Profile.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch { drawerState.close() }
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = ExtraItems.Screen.Home.route) {
        composable(ExtraItems.Screen.Home.route) { HomeScreen() }
        composable(ExtraItems.Screen.Search.route) { SearchScreen() }
        composable(ExtraItems.Screen.Profile.route) { ProfileScreen() }
    }
}

@Composable
fun HomeScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        PreviewMovieList()
    }
}

@Composable
fun SearchScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Search Screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Profile Screen")
    }
}


@Composable
fun MovieList(movies: List<Movie>) {
    LazyColumn (Modifier.padding(top = 56.dp,bottom = 56.dp)){
        items(movies) { movie ->
            MovieItem(movie = movie)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(movie.imageUrl),
                contentDescription = movie.title,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(text = movie.displayTitle)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Genre: ${movie.genre}")
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Year: ${movie.year}")
            }
        }
    }
}

@Preview
@Composable
fun PreviewMovieList() {
    val movies = listOf(
        Movie("Davil's Eye", 2020, "Sci-Fi", "https://example.com/inception.jpg"),
        Movie("Inception", 2010, "Sci-Fi", "https://example.com/inception.jpg"),
        Movie("The Shawshank Redemption", 1994, "Drama", "https://example.com/shawshank.jpg"),
        Movie("SpiderMan 3", 2023, "Crime", "https://example.com/godfather.jpg"),
        Movie("Last War", 2000, "Crime", "https://example.com/godfather.jpg"),
        Movie("The Godfather", 1972, "Crime", "https://example.com/godfather.jpg"),
        Movie("Zoombie Land 2", 2004, "Crime", "https://example.com/godfather.jpg"),
    )
    MovieList(movies = movies)
}




