import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import com.app.auth.home.TransferScreen
import com.app.auth.login.MenuScreen
import com.app.auth.login.home.AdjustmentsScreen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(navController: NavController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        NavigationGraph(navController = navController)
    }
}


@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Menu,
        BottomNavItem.Transfers,
        BottomNavItem.Adjustments,

        )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
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
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Menu.screen_route) {
        composable(BottomNavItem.Menu.screen_route) {
            MenuScreen()
        }
        composable(BottomNavItem.Transfers.screen_route) {
            TransferScreen()
        }
        composable(BottomNavItem.Adjustments.screen_route) {
            AdjustmentsScreen(navController)
        }

    }
}




sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Menu : BottomNavItem("Menu", R.drawable.menu, "menu")
    object Transfers : BottomNavItem("Transfers", R.drawable.transfer, "transfers")
    object Adjustments : BottomNavItem("Adjustments", R.drawable.adjustments, "adjustment")

}

@Preview(device = Devices.PIXEL_4)
@Composable
fun BottomNavigationBarPreview() {
//    MainScreenView(navController)
}