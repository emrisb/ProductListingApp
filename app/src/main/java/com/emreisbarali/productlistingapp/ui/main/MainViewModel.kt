package com.emreisbarali.productlistingapp.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emreisbarali.productlistingapp.data.*
import com.emreisbarali.productlistingapp.di.DispatcherDefault
import com.emreisbarali.productlistingapp.domain.usecase.ProductDetailUseCase
import com.emreisbarali.productlistingapp.domain.usecase.ProductListUseCase
import com.emreisbarali.productlistingapp.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class MainViewModel @ViewModelInject constructor(
    private val productListUseCase: ProductListUseCase,
    private val productDetailUseCase: ProductDetailUseCase,
    @DispatcherDefault private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _productList = MutableLiveData<ProductList>()
    val productList: LiveData<ProductList> = _productList

    private val _productDetail = MutableLiveData<ProductListItem>()
    val productDetail: LiveData<ProductListItem> = _productDetail


    fun getProductList() {
        callService(
            service = {
                productListUseCase.invoke(NoParams())
            },
            success = {
                _productList.postValue(it)
            },
            dispatcher = dispatcher
        )
    }

    fun getProductDetail(productId: String) {
        callService(
            service = {
                productDetailUseCase.invoke(ProductDetailUseCase.Params(productId))
            },
            success = {
                _productDetail.postValue(it)
            },
            dispatcher = dispatcher
        )
    }
}