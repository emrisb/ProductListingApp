package com.emreisbarali.productlistingapp.domain.usecase

import com.emreisbarali.productlistingapp.data.ProductError
import com.emreisbarali.productlistingapp.data.ProductListItem
import com.emreisbarali.productlistingapp.data.Resource
import com.emreisbarali.productlistingapp.data.Status
import com.emreisbarali.productlistingapp.domain.repository.ProductRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductDetailUseCaseTest {

    private lateinit var useCase: ProductDetailUseCase

    @MockK
    private lateinit var productRepository: ProductRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = spyk(ProductDetailUseCase(productRepository))
    }

    @Test
    fun `get product detail gives success`() {
        // given
        val item = ProductListItem("1", "Elma", 140, "", "Elma")
        coEvery { productRepository.getProductDetail(any()) } returns Resource.success(item)

        // when
        val result = runBlocking { useCase.invoke(ProductDetailUseCase.Params("1")) }

        //then
        assertThat(result.status).isEqualTo(Status.SUCCESS)
        assertThat(result.data).isNotNull()
        assertThat(result.data).isInstanceOf(ProductListItem::class.java)
    }

    @Test
    fun `get product detail gives error`() {
        // given
        val error = ProductError.GeneralError()
        coEvery { productRepository.getProductDetail(any()) } returns Resource.error(error)

        // when
        val result = runBlocking { useCase.invoke(ProductDetailUseCase.Params("1")) }

        //then
        assertThat(result.status).isEqualTo(Status.ERROR)
        assertThat(result.data).isNull()
        assertThat(result.error).isInstanceOf(ProductError.GeneralError::class.java)
    }
}