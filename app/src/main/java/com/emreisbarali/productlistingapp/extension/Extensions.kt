package com.emreisbarali.productlistingapp.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.emreisbarali.productlistingapp.data.ProductError
import com.emreisbarali.productlistingapp.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

suspend inline fun <reified T> handleServiceCall(noinline service: suspend () -> T): Resource<T> {

    lateinit var result: Resource<T>

    try {
        val call = withContext(Dispatchers.IO) { service.invoke() }
        result = Resource.success(call)
    } catch (e: Exception) {
        withContext(Dispatchers.Main) {
            result = Resource.error(ProductError.GeneralError())
        }
    }

    return result
}

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    observe(owner, Observer {
        it?.apply { observer(this) }
    })
}