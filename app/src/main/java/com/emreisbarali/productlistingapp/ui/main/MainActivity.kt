package com.emreisbarali.productlistingapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.emreisbarali.productlistingapp.R
import com.emreisbarali.productlistingapp.ui.base.BaseActivity
import com.emreisbarali.productlistingapp.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.activity_main_toolbar

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun layoutRes() = R.layout.activity_main

    override fun getViewModel(): BaseViewModel? = viewModel

    override fun observeData() {
    }

    override fun initView(savedInstanceState: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        activity_main_toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}