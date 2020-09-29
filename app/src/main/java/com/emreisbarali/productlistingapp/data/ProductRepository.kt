package com.emreisbarali.productlistingapp.data

interface ProductRepository {

    suspend fun getProductList(): ProductList

    suspend fun getProductDetail(productId: Int): ProductListItem
}

class ProductRepositoryImpl(private val productService: ProductService) : ProductRepository {
    override suspend fun getProductList() = productService.getProductList()

    override suspend fun getProductDetail(productId: Int) =
        productService.getProductDetail(productId)
}