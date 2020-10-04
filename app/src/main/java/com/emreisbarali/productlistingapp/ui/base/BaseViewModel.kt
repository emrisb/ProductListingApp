package com.emreisbarali.productlistingapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreisbarali.productlistingapp.SingleLiveEvent
import com.emreisbarali.productlistingapp.data.ProductError
import com.emreisbarali.productlistingapp.data.Resource
import com.emreisbarali.productlistingapp.data.Status
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val progressDialog: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val error: SingleLiveEvent<ProductError> = SingleLiveEvent()

    private fun showProgressDialog() {
        progressDialog.postValue(true)
    }

    private fun dismissProgressDialog() {
        progressDialog.postValue(false)
    }

    fun handleError(err: ProductError) {
        error.postValue(err)
    }


    fun <T> callService(
        service: suspend () -> Resource<T>,
        success: (data: T?) -> Unit,
        fail: (err: ProductError?) -> Unit = { error.postValue(it) }
    ) {

        showProgressDialog()

        viewModelScope.launch {
            val result = service()

            when (result.status) {
                Status.SUCCESS -> success(result.data)
                Status.ERROR -> fail(result.error)
            }

            dismissProgressDialog()
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}