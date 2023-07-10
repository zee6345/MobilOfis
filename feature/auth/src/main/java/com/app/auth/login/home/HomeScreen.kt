import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import com.app.auth.login.home.adjustments.TextFieldWithEndDrawable

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Menu : NavigationItem("menu", R.drawable.tariffs_icon, "Menu")
    object Transfer : NavigationItem("transfer", R.drawable.whatsapp_icon, "Transfer")
    object Adjustments : NavigationItem("adjustments", R.drawable.whatsapp_icon, "Adjustments")
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Menu, NavigationItem.Transfer, NavigationItem.Adjustments
    )
    BottomNavigation(
        backgroundColor = Color.White, contentColor = Color.Black
    ) {
        items.forEach { item ->
            BottomNavigationItem(icon = {
                Icon(
                    painterResource(id = item.icon), contentDescription = item.title
                )
            },
                label = { Text(text = item.title) },
                selectedContentColor = Color(0x223142),
                unselectedContentColor = Color(0x859DB5).copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    /* Add code later */
                })
        }
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController) }, content = { padding ->
        Box(modifier = Modifier.padding(padding)) {

//            Navigation(navController = navController)
//                Navigation(navController)
        }
    }, backgroundColor = Color.White
    )
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun BottomNavigationBarPreview() {
//    BottomNavigationBar()
//    MainScreen()
    TextFieldWithEndDrawable("fcgvhbj")
}