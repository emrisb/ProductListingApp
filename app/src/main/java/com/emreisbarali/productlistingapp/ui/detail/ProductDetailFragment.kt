package com.emreisbarali.productlistingapp.ui.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.emreisbarali.productlistingapp.R
import com.emreisbarali.productlistingapp.extension.observeNotNull
import com.emreisbarali.productlistingapp.databinding.FragmentProductDetailBinding
import com.emreisbarali.productlistingapp.ui.main.MainViewModel
import com.emreisbarali.productlistingapp.ui.base.BaseFragment
import com.emreisbarali.productlistingapp.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {

    private val args: ProductDetailFragmentArgs by navArgs()
    private var productId = ""

    private val viewModel: MainViewModel by activityViewModels()

    override fun layoutRes() = R.layout.fragment_product_detail

    override fun getViewModel(): BaseViewModel? = viewModel

    override fun observeData() {
        viewModel.productDetail.observeNotNull(this) {
            binding.product = it
        }
    }

    override fun initFragment() {
        productId = args.productId
        viewModel.getProductDetail(productId)
    }
}