package com.emreisbarali.productlistingapp.domain.repository

import com.emreisbarali.productlistingapp.data.ProductList
import com.emreisbarali.productlistingapp.data.ProductListItem
import com.emreisbarali.productlistingapp.data.ProductService
import com.emreisbarali.productlistingapp.data.Resource
import com.emreisbarali.productlistingapp.extension.handleServiceCall

interface ProductRepository {

    suspend fun getProductList(): Resource<ProductList>

    suspend fun getProductDetail(productId: String): Resource<ProductListItem>
}

class ProductRepositoryImpl(private val productService: ProductService) :
    ProductRepository {
    override suspend fun getProductList() =
        handleServiceCall { productService.getProductList() }

    override suspend fun getProductDetail(productId: String) =
        handleServiceCall {
            productService.getProductDetail(
                productId
            )
        }
}