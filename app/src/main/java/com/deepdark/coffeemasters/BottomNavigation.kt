package com.deepdark.coffeemasters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepdark.coffeemasters.ui.theme.Alternative
import com.deepdark.coffeemasters.ui.theme.CardBackground
import com.deepdark.coffeemasters.ui.theme.Primary

data class NavigationPage(
    var name: String,
    var icon: ImageVector,
    var route: String
)

object Routes {
    val MenuPage = NavigationPage("Menu", Icons.Outlined.Menu, "menu")
    val OffersPage = NavigationPage("Offers", Icons.Outlined.Star, "offers")
    val OrderPage = NavigationPage("My Order", Icons.Outlined.ShoppingCart, "my-order")
    val InfoPage = NavigationPage("Info", Icons.Outlined.Info, "info")

    val pages = listOf(MenuPage, OffersPage, OrderPage, InfoPage)
}

@Preview
@Composable
fun NavBarPreview() {
    NavBar(onChange = { "menu" })
}

@Composable
fun NavBar(
    selectedRoute: String = Routes.MenuPage.route,
    onChange: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary)
            .padding(16.dp)
    ) {
        for (page in Routes.pages) {
            NavBarItem(
                page,
                modifier = Modifier.clickable {
                    onChange(page.route)
                },
                selected = selectedRoute == page.route
            )
        }
    }
}

@Composable
fun NavBarItemPreview() {
    NavBarItem(
        page = Routes.MenuPage,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun NavBarItem(page: NavigationPage, modifier: Modifier = Modifier, selected: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 12.dp)) {
        Image(
            imageVector = page.icon,
            contentDescription = page.name,
            colorFilter = ColorFilter.tint(
                if (selected) Alternative else CardBackground
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(24.dp)
        )
        Text(page.name,
            fontSize = 12.sp,
            color = if (selected) Alternative else CardBackground
        )
    }
}