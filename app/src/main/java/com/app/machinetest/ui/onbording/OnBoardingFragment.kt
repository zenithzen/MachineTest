package com.app.machinetest.ui.onbording

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.app.machinetest.R
import com.app.machinetest.databinding.FragmentOnBoardingBinding
import com.app.machinetest.preferences.PreferenceHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    private var navController: NavController? = null
    private val binding:FragmentOnBoardingBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(this@OnBoardingFragment) {
            // handle back event
            requireActivity().finish()
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)

        preferenceHandler.isOnBoardingCompleted=true
        binding.login.setOnClickListener {
            navController?.navigate(R.id.action_onBoardingFragment_to_fragmentLogin)

        }

    }
}