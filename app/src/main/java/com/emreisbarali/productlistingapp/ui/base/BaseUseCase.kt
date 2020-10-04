package com.emreisbarali.productlistingapp.ui.base

import com.emreisbarali.productlistingapp.data.Resource

abstract class BaseUseCase<Type, in Params> where Type : Any {

    abstract suspend operator fun invoke(params: Params) : Resource<Type>
}