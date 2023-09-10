package com.app.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.home.navigation.NavigationGraph

import com.app.network.models.DataState
import com.app.network.models.errorResponse.ErrorState
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.utils.SharedModel
import java.util.Timer
import java.util.TimerTask


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val lastLogin by viewModel.lastLogin.collectAsState()

//    val signInfo = SharedModel.init().signInfo

//    if (signInfo.value.fromSign) {
//        Scaffold() {
//            NavigationGraphSign(navController = navController)
//        }
//    } else {
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        NavigationGraph(navController = navController)
    }
//    }


    LaunchedEffect(Unit) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                viewModel.lastLogin()
            }
        }, 0, 30000)
    }

    lastLogin?.let {
        when (it) {
            is DataState.Loading -> Unit
            is DataState.Error -> {
                ErrorState(LocalContext.current, it.errorMessage).handleError()
            }

            is DataState.Success -> Unit
        }
    }

}


@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Main,
        BottomNavItem.Transfers,
        BottomNavItem.Adjustments,
    )
    val currentRoute by rememberUpdatedState(navController.currentBackStackEntryAsState().value?.destination?.route)

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(3.dp)) // Add space at the top
                        Text(
                            text = item.title,
                            fontSize = 9.sp
                        )
                    }
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

    // Draw the line indicator on top of the selected item
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp),
        onDraw = {
            val selectedItemIndex = items.indexOfFirst { it.screen_route == currentRoute }
            if (selectedItemIndex != -1) {
                val selectedItemWidth = size.width / items.size
                val indicatorLeft = selectedItemIndex * selectedItemWidth
                val indicatorRight = (selectedItemIndex + 1) * selectedItemWidth
                val indicatorTop = 0f
                val indicatorBottom = size.height
                val indicatorColor = Color(0xFF203657) // Change this to your desired color

                this.drawLine(
                    color = indicatorColor,
                    start = Offset(indicatorLeft, indicatorTop),
                    end = Offset(indicatorRight, indicatorTop),
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    )

}


sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Main : BottomNavItem("Main", R.drawable.ic_menu, "menu")
    object Transfers : BottomNavItem("Transfers", R.drawable.ic_transfers, "transfers")
    object Adjustments : BottomNavItem("Adjustments", R.drawable.ic_adjusments, "adjustment")

}

@Preview(device = Devices.PIXEL_4)
@Composable
fun BottomNavigationBarPreview() {
    MainScreenView(rememberNavController())
}