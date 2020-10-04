package com.emreisbarali.productlistingapp.domain.usecase

import com.emreisbarali.productlistingapp.data.*
import com.emreisbarali.productlistingapp.domain.repository.ProductRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductListUseCaseTest {

    private lateinit var useCase: ProductListUseCase

    @MockK
    private lateinit var productRepository: ProductRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = spyk(ProductListUseCase(productRepository))
    }

    @Test
    fun `get product list gives success`() {
        // given
        val list = ProductList(arrayListOf(ProductListItem("1", "Elma", 140, "", "Elma")))
        coEvery { productRepository.getProductList() } returns Resource.success(list)

        // when
        val result = runBlocking { useCase.invoke(NoParams()) }

        //then
        assertThat(result.status).isEqualTo(Status.SUCCESS)
        assertThat(result.data).isInstanceOf(ProductList::class.java)
        assertThat(result.data).isNotNull()
        assertThat(result.data?.products?.size).isEqualTo(list.products.size)
    }

    @Test
    fun `get product detail gives error`() {
        // given
        val error = ProductError.GeneralError()
        coEvery { productRepository.getProductList() } returns Resource.error(error)

        // when
        val result = runBlocking { useCase.invoke(NoParams()) }

        //then
        assertThat(result.status).isEqualTo(Status.ERROR)
        assertThat(result.error).isInstanceOf(ProductError.GeneralError::class.java)
        assertThat(result.data).isNull()
    }

}