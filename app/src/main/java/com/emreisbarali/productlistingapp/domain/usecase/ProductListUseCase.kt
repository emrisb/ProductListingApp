package com.emreisbarali.productlistingapp.domain.usecase

import com.emreisbarali.productlistingapp.data.NoParams
import com.emreisbarali.productlistingapp.data.ProductList
import com.emreisbarali.productlistingapp.domain.repository.ProductRepository
import com.emreisbarali.productlistingapp.ui.base.BaseUseCase

class ProductListUseCase(
    private val repository: ProductRepository
) : BaseUseCase<ProductList, NoParams>() {

    override suspend fun invoke(params: NoParams) = repository.getProductList()
}