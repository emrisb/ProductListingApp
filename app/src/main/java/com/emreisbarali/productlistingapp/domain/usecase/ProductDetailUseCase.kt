package com.emreisbarali.productlistingapp.domain.usecase

import com.emreisbarali.productlistingapp.data.ProductListItem
import com.emreisbarali.productlistingapp.domain.repository.ProductRepository
import com.emreisbarali.productlistingapp.ui.base.BaseUseCase

class ProductDetailUseCase(
    private val repository: ProductRepository
) : BaseUseCase<ProductListItem, ProductDetailUseCase.Params>() {

    data class Params(
        val productId: String
    )

    override suspend fun invoke(params: Params) = repository.getProductDetail(params.productId)
}