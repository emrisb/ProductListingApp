package com.emreisbarali.productlistingapp.ui.list

import com.emreisbarali.productlistingapp.BR
import com.emreisbarali.productlistingapp.R
import com.emreisbarali.productlistingapp.data.ProductListItem
import com.emreisbarali.productlistingapp.databinding.ListItemProductBinding
import com.emreisbarali.productlistingapp.ui.base.BaseBindingAdapter

class ProductListAdapter : BaseBindingAdapter<ProductListItem, ListItemProductBinding>() {

    override fun bindingVariableId() = BR.product

    override fun layoutRes() = R.layout.list_item_product

}