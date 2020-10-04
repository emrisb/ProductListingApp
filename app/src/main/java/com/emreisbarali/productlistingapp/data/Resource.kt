package com.emreisbarali.productlistingapp.data

data class Resource<out T>(val status: Status, val data: T?, val error: ProductError?) {

    companion object {
        fun <T> success(data: T) = Resource(Status.SUCCESS, data, null)

        fun <T> error(error: ProductError) : Resource<T> = Resource(Status.ERROR, null, error)
    }
}

sealed class ProductError {
    class GeneralError(val message: String = "") : ProductError()
}

class NoParams