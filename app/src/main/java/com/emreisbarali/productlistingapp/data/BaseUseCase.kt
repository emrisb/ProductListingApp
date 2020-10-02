package com.emreisbarali.productlistingapp.data

abstract class BaseUseCase<Type, in Params> where Type : Any {

    abstract suspend operator fun invoke(params: Params) : Resource<Type>
}