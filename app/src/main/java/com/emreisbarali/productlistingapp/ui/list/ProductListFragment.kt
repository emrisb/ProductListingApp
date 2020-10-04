package com.emreisbarali.productlistingapp.ui.list

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.emreisbarali.productlistingapp.R
import com.emreisbarali.productlistingapp.data.ProductListItem
import com.emreisbarali.productlistingapp.extension.observeNotNull
import com.emreisbarali.productlistingapp.databinding.FragmentProductListBinding
import com.emreisbarali.productlistingapp.ui.main.MainViewModel
import com.emreisbarali.productlistingapp.ui.base.BaseFragment
import com.emreisbarali.productlistingapp.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : BaseFragment<FragmentProductListBinding>() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var productListAdapter: ProductListAdapter
    private var productList = arrayListOf<ProductListItem>()

    override fun layoutRes() = R.layout.fragment_product_list

    override fun getViewModel(): BaseViewModel? = viewModel

    override fun observeData() {
        viewModel.productList.observeNotNull(this) {
            productListAdapter.apply {
                productList = it.products
                items = productList
                notifyDataSetChanged()
            }
        }
    }

    override fun initFragment() {
        productListAdapter = ProductListAdapter().apply {
            itemClickListener = {
                val action =
                    ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                        it.productId
                    )
                findNavController().navigate(action)
            }
        }

        binding.fragmentProductListRvProducts.apply {
            adapter = productListAdapter
        }

        viewModel.getProductList()
    }
}