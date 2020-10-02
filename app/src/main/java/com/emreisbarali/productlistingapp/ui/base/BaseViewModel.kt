package com.emreisbarali.productlistingapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreisbarali.productlistingapp.SingleLiveEvent
import com.emreisbarali.productlistingapp.data.Resource
import com.emreisbarali.productlistingapp.data.Status
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val progressDialog: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val error: SingleLiveEvent<String> = SingleLiveEvent()

    fun showProgressDialog() {
        progressDialog.postValue(true)
    }

    fun dismissProgressDialog() {
        progressDialog.postValue(false)
    }

    fun handleError() {
        error.postValue("")
    }


    fun <T> callService(
        service: suspend () -> Resource<T>,
        success: (data: T?) -> Unit,
        fail: (err: String?) -> Unit = { error.postValue(it) }
    ) {

        showProgressDialog()

        viewModelScope.launch {
            val result = service()

            when (result.status) {
                Status.SUCCESS -> success(result.data)
                Status.ERROR -> fail(result.message)
            }

            dismissProgressDialog()
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}