package com.deepdark.coffeemasters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deepdark.coffeemasters.pages.InfoPage
import com.deepdark.coffeemasters.pages.MenuPage
import com.deepdark.coffeemasters.pages.OffersPage
import com.deepdark.coffeemasters.pages.OrderPage
import com.deepdark.coffeemasters.ui.theme.CoffeeMastersTheme
import com.deepdark.coffeemasters.ui.theme.Primary

@Preview
@Composable
fun AppPreview() {
    CoffeeMastersTheme {
        App()
    }
}

@Composable
fun App() {
    val selectedRoute = remember {
        mutableStateOf(Routes.MenuPage.route)
    }

    Scaffold(
        topBar = {
            AppTitle()
        },
        bottomBar = {
            NavBar(
                selectedRoute = selectedRoute.value,
                onChange = { newRoute ->
                    selectedRoute.value = newRoute
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            when (selectedRoute.value) {
                Routes.MenuPage.route -> MenuPage()
                Routes.OffersPage.route -> OffersPage()
                Routes.OrderPage.route -> OrderPage()
                Routes.InfoPage.route -> InfoPage()
            }
        }
    }
}

@Composable
fun AppTitle() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary)
            .height(54.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Coffee Masters logo"
        )
    }
}