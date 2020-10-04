package com.emreisbarali.productlistingapp.ui.main

import com.emreisbarali.productlistingapp.domain.usecase.ProductDetailUseCase
import com.emreisbarali.productlistingapp.domain.usecase.ProductListUseCase
import io.mockk.MockKAnnotations
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emreisbarali.productlistingapp.data.NoParams
import com.emreisbarali.productlistingapp.data.ProductList
import com.emreisbarali.productlistingapp.data.ProductListItem
import com.emreisbarali.productlistingapp.data.Resource
import com.emreisbarali.productlistingapp.utils.CoroutineTestRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule =
        CoroutineTestRule()


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var productListUseCase: ProductListUseCase

    @MockK
    lateinit var productDetailUseCase: ProductDetailUseCase

    private lateinit var viewModel: MainViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = spyk(
            MainViewModel(
                productListUseCase,
                productDetailUseCase,
                coroutineTestRule.testDispatcher
            )
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get product list gives success`() {
        coroutineTestRule.runBlockingTest {
            // given
            val list = ProductList(arrayListOf(ProductListItem("1", "Elma", 140, "", "Elma")))
            coEvery { productListUseCase.invoke(NoParams()) } returns Resource.success(list)

            // when
            viewModel.getProductList()

            //then
            viewModel.productList.observeForever {
                assertThat(it.products.size).isEqualTo(list.products.size)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get product detail gives success`() {
        coroutineTestRule.runBlockingTest {
            // given
            val item = ProductListItem("1", "Elma", 140, "", "Elma")
            coEvery { productDetailUseCase.invoke(ProductDetailUseCase.Params("1")) } returns Resource.success(
                item
            )

            // when
            viewModel.getProductDetail("1")

            //then
            viewModel.productDetail.observeForever {
                assertThat(it.description).isEqualTo(item.description)
            }
        }
    }
}