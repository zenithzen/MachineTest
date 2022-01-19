package com.app.machinetest.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.app.machinetest.R
import com.app.machinetest.preferences.PreferenceHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Splash : Fragment() {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    private var navController: NavController? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.navController = Navigation.findNavController(view)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (!preferenceHandler.isOnBoardingCompleted) {
                    navController?.navigate(R.id.action_splash_to_onBoardingFragment)
                } else if (!preferenceHandler.isLogged) {
                    navController?.navigate(R.id.action_splash_to_fragmentLogin)
                } else {
                    navController?.navigate(R.id.action_splash_to_homeFragment)
                }

            }, 2000L)
    }
}