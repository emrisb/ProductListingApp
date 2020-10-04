package com.emreisbarali.productlistingapp.di

import com.emreisbarali.productlistingapp.BuildConfig
import com.emreisbarali.productlistingapp.data.*
import com.emreisbarali.productlistingapp.domain.repository.ProductRepository
import com.emreisbarali.productlistingapp.domain.repository.ProductRepositoryImpl
import com.emreisbarali.productlistingapp.domain.usecase.ProductDetailUseCase
import com.emreisbarali.productlistingapp.domain.usecase.ProductListUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor(object : Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)

    @Singleton
    @Provides
    fun provideProductRepository(productService: ProductService): ProductRepository =
        ProductRepositoryImpl(
            productService
        )

    @Provides
    fun provideProductListUseCase(repository: ProductRepository) =
        ProductListUseCase(
            repository
        )

    @Provides
    fun provideProductDetailUseCase(repository: ProductRepository) =
        ProductDetailUseCase(
            repository
        )
}