package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Main composable with Drawer and NavigationGraph integration
@Composable
fun MyAppWithDrawer(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerContent(navController = navController, scaffoldState = scaffoldState, coroutineScope = coroutineScope)
        },
        content = {
            NavigationGraph(navController = navController, onBottomBarVisibilityChanged = {})
        }
    )
}

// Drawer Content with Navigation Links and Icons
@Composable
fun DrawerContent(navController: NavHostController, scaffoldState: ScaffoldState, coroutineScope: CoroutineScope) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                    navController.navigate(BottomNavigationItems.PizzaScreen.route)
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Pizza Order")
            Spacer(modifier = Modifier.width(16.dp))
            Text("Pizza Order")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                    navController.navigate(BottomNavigationItems.GpaAppScreen.route)
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.School, contentDescription = "GPA App")
            Spacer(modifier = Modifier.width(16.dp))
            Text("GPA App")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                    navController.navigate(BottomNavigationItems.Screen3.route)
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Person, contentDescription = "Screen3")
            Spacer(modifier = Modifier.width(16.dp))
            Text("Screen3")
        }
    }
}

// Navigation Graph for the app
@Composable
fun NavigationGraph(navController: NavHostController, onBottomBarVisibilityChanged: (Boolean) -> Unit) {
    NavHost(navController, startDestination = BottomNavigationItems.Welcome.route) {
        composable(BottomNavigationItems.Welcome.route) {
            onBottomBarVisibilityChanged(false)
            SplashScreen(navController = navController)
        }
        composable(BottomNavigationItems.PizzaScreen.route) {
            onBottomBarVisibilityChanged(true)
            PizzaPartyScreen()
        }
        composable(BottomNavigationItems.GpaAppScreen.route) {
            onBottomBarVisibilityChanged(true)
            GpaAppScreen()
        }
        composable(BottomNavigationItems.Screen3.route) {
            onBottomBarVisibilityChanged(true)
            Screen3()
        }
    }
}

// Placeholder for PizzaPartyScreen
@Composable
fun PizzaPartyScreen() {
    // Implement your PizzaParty screen UI here
    Text("Pizza Party Screen")
}

// Placeholder for GpaAppScreen
@Composable
fun GpaAppScreen() {
    // Implement your GPA App screen UI here
    Text("GPA App Screen")
}

// Placeholder for Screen3
@Composable
fun Screen3() {
    // Implement your Screen3 UI here
    Text("Screen 3")
}

// Placeholder for SplashScreen
@Composable
fun SplashScreen(navController: NavHostController) {
    // Implement your Splash Screen UI here
    Text("Welcome Screen")
}

// Enum for Navigation Routes
enum class BottomNavigationItems(val route: String) {
    Welcome("welcome"),
    PizzaScreen("pizza_screen"),
    GpaAppScreen("gpa_app_screen"),
    Screen3("screen_3")
}


// ToDo 8: This is the homework:
// add a drawer navigation as described in drawable drawermenu.png
// Improve the design and integration of the app for 5 extra credit points.