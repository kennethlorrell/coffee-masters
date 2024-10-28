package com.deepdark.coffeemasters

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataManager(app: Application): AndroidViewModel(application = app) {
    var menu: List<Category> by mutableStateOf(listOf())
    var cart: List<ItemInCart> by mutableStateOf(listOf())

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            menu = API.menuService.fetchMenu()
        }
    }

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