package com.emreisbarali.productlistingapp.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.emreisbarali.productlistingapp.R
import com.emreisbarali.productlistingapp.extension.observeNotNull
import com.emreisbarali.productlistingapp.ui.widgets.ProgressDialog

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog

    @LayoutRes
    abstract fun layoutRes(): Int

    abstract fun getViewModel(): BaseViewModel?

    abstract fun observeData()

    abstract fun initView(savedInstanceState: Bundle? = null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        progressDialog =
            ProgressDialog(this)

        bindViewModel(getViewModel())
        observeData()
        initView(savedInstanceState)

    }

    private fun bindViewModel(viewModel: BaseViewModel?) {
        viewModel?.let { vm ->
            vm.progressDialog.observeNotNull(this) {
                if (it) showProgressDialog() else dismissProgressDialog()
            }

            vm.error.observeNotNull(this) {
                Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun showProgressDialog() {
        if (!isFinishing && !progressDialog.isShowing) progressDialog.show()
    }

    private fun dismissProgressDialog() {
        if (!isFinishing && progressDialog.isShowing) progressDialog.dismiss()
    }
}