package com.deepdark.coffeemasters.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.deepdark.coffeemasters.DataManager
import com.deepdark.coffeemasters.Product
import com.deepdark.coffeemasters.ui.theme.Alternative
import com.deepdark.coffeemasters.ui.theme.CardBackground
import com.deepdark.coffeemasters.ui.theme.Primary

@Composable
fun MenuPage(dataManager: DataManager) {
    LazyColumn {
        items(dataManager.menu) {
            Text(
                it.name,
                color = Primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp, 20.dp, 10.dp, 10.dp)
            )
            it.products.forEach { product ->
                Card(
                    elevation = cardElevation(2.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .background(CardBackground)
                        .padding(12.dp)
                ) {
                    ProductItem(
                        product = product,
                        onAdd = {
                            dataManager.addToCart(product)
                        }
                    )
                }
            }
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)

@Composable
fun ProductItemPreview() {
    ProductItem(
        Product(1, "Coca Cola", 12.5, "1234"),
        onAdd = { it }
    )
}

@Composable
fun ProductItem(product: Product, onAdd: (Product) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = "Image for ${product.name}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text("$${product.price.format(2)}")
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    Alternative,
                    Color.White
                ),
                onClick = {
                    onAdd(product)
                },
            ) {
                Text("Add")
            }
        }
    }
}