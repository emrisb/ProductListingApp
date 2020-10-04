package com.emreisbarali.productlistingapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

//    https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/list
//    https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/1/detail

    @GET("cart/list")
    suspend fun getProductList(): ProductList

    @GET("cart/{product_id}/detail")
    suspend fun getProductDetail(@Path("product_id") productId: String): ProductListItem

}