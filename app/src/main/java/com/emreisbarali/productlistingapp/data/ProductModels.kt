package com.emreisbarali.productlistingapp.data

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("products")
    val products: ArrayList<ProductListItem>
)

data class ProductListItem(
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("description")
    val description: String
)