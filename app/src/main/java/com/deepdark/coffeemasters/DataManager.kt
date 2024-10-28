package com.deepdark.coffeemasters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DataManager {
    var menu: List<Category> by mutableStateOf(listOf())
    var cart: List<ItemInCart> by mutableStateOf(listOf())

    fun addToCart(product: Product) {
        var isFound: Boolean = false

        cart.forEach {
            if (product.id == it.product.id) {
                isFound = true
                it.quantity++
            }
        }

        if (isFound) {
            cart = listOf(
                *cart.toTypedArray(),
                ItemInCart(product, 1)
            )
        }
    }

    fun removeFromCart(product: Product) {
        val aux = cart.toMutableList()
        aux.removeAll { product.id == it.product.id }

        cart = listOf(*aux.toTypedArray())
    }

    fun clearCart() {
        cart = listOf()
    }
}