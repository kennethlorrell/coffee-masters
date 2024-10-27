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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Scaffold(
        topBar = {
            AppTitle()
        },
        bottomBar = {
            Text("I am the BottomBar")
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OffersPage()
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