package com.hamy.newapp.ui.Views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamy.newapp.ui.theme.NewAppTheme
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hamy.newapp.R
import com.hamy.newapp.ui.Views.utils.ExtraItems
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashLoginView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = ExtraItems.Screen.Splash.route) {
                        composable(ExtraItems.Screen.Splash.route) { LaunchingState(navController) }
                        composable(ExtraItems.Screen.Login.route) { LoginPage(navController) }
                        composable(ExtraItems.Screen.DashBoard.route) { MainScreen() }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LaunchingState(navController: NavController) {
        LaunchedEffect(Unit) {
            delay(2000) // Delay for 2 seconds
            navController.navigate(ExtraItems.Screen.Login.route)
        }
        SplashView()
    }

    @Composable
    fun SplashView() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = ExtraItems.Screen.Splash.label,
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "Learning Compose",
                style = MaterialTheme.typography.headlineMedium // Add a style if needed
            )
            Text(
                text = "News Projection",
                style = MaterialTheme.typography.labelLarge // Add a style if needed
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginPage(navController: NavController) {
        var snackBar = remember { SnackbarHostState() }
        var coroutineScope = rememberCoroutineScope()
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isUserNameEmpty by remember { mutableStateOf(false) }
        var isUserNameFocus by remember { mutableStateOf(false) }
        var isPasswordEmpty by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = ExtraItems.Screen.Splash.label,
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "Login",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = userName, onValueChange = { userName = it },
                label = { Text(text = "User Name") },
                isError = isUserNameEmpty,
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Username") },
                singleLine = true,
              //  error( if(isUserNameEmpty){ "UserName can not be emoty"} else null),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                isError = isPasswordEmpty,
                singleLine = true,
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "Username") },
                value = password, onValueChange = { password = it },
                label = { Text(text = "Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Forget Password",
                style = TextStyle(color = Color.Red),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (userName.isNullOrEmpty()) {
                        coroutineScope.launch {
                            isUserNameEmpty = true
                        }
                    } else {
                        isUserNameEmpty = false
                    }
                    if (password.isNullOrEmpty()) {
                        coroutineScope.launch {
                            isPasswordEmpty = true
                        }
                    } else {
                        isPasswordEmpty = false
                        navController.navigate(ExtraItems.Screen.DashBoard.route) {
                            popUpTo(0) { inclusive = true }
                        }

                    }
                }) {
                Text(text = "Login")
            }
            Text(
                text = "Registered New Account!",
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
        }
    }
}





