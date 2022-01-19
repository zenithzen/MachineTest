package com.app.machinetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.app.machinetest.R
import com.app.machinetest.databinding.ActivityLaunchBinding
import com.app.machinetest.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val bindings: ActivityLaunchBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

    }

    override fun onBackPressed() {

        super.onBackPressed()
    }
}