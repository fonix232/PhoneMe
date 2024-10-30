package net.fonix232.phoneme.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.stefanoq21.material3.navigation.ModalBottomSheetLayout
import com.stefanoq21.material3.navigation.bottomSheet
import com.stefanoq21.material3.navigation.rememberBottomSheetNavigator
import net.fonix232.phoneme.ui.screens.phone.PhoneDetailScreen
import net.fonix232.phoneme.ui.screens.phone.PhoneListScreen
import net.fonix232.phoneme.ui.screens.todo.TodoDetailScreen
import net.fonix232.phoneme.ui.screens.todo.TodoListScreen

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun NavigationStack() {
    val bottomSheetNavigator = rememberBottomSheetNavigator(skipPartiallyExpanded = true)
    val navController = rememberNavController(bottomSheetNavigator)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { AppNavBar(navController) }
    ) {  innerPadding ->
        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxSize(),
            bottomSheetNavigator = bottomSheetNavigator
        ) {
            NavHost(
                navController = navController,
                startDestination = PhoneList,
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                composable<PhoneList> {
                    PhoneListScreen(navController)
                }
                bottomSheet<PhoneDetail> {
                    val phoneId = it.toRoute<PhoneDetail>().phoneId
                    PhoneDetailScreen(navController, phoneId)
                }

                composable<TodoList> {
                    TodoListScreen(navController)
                }
                bottomSheet<TodoDetail> {
                    val todoId = it.toRoute<TodoDetail>().todoId
                    TodoDetailScreen(navController, todoId)
                }
            }
        }

    }
}

@Composable
fun AppNavBar(navController: NavController) {

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        AppDestinations.entries.forEach { destination ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.hasRoute(destination.route::class) } == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = { Icon(
                    imageVector = if(isSelected) destination.selectedIcon else destination.icon,
                    contentDescription = destination.label
                ) },
                label = { Text(destination.label) }
            )
        }
    }
}
