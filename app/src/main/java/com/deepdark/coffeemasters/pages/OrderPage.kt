package com.deepdark.coffeemasters.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.deepdark.coffeemasters.DataManager
import com.deepdark.coffeemasters.ItemInCart
import com.deepdark.coffeemasters.Product
import com.deepdark.coffeemasters.ui.theme.Primary

@Composable
fun OrderPage(dataManager: DataManager) {
    LazyColumn {
        if (dataManager.cart.isEmpty()) {
            item {
                Text(
                    text = "Your cart is empty! Order something",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }

        items (dataManager.cart) { itemInCart ->
            CartItem(
                itemInCart,
                onDelete = { product ->
                    dataManager.removeFromCart(product)
                }
            )
        }
    }
}

@Composable
private fun CartItem(cartItem: ItemInCart, onDelete: (Product) -> Unit ) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("${cartItem.quantity}x")
        Text(
            cartItem.product.name,
            modifier = Modifier.width(150.dp)
        )
        Text(
            "$${(cartItem.quantity * cartItem.product.price).format(2)}",
            modifier = Modifier.width(50.dp)
        )
        Image(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete",
            colorFilter = ColorFilter.tint(Primary),
            modifier = Modifier.clickable {
                onDelete(cartItem.product)
            }
        )
    }
}