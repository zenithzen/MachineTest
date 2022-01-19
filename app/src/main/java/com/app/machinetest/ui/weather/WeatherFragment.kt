package com.app.machinetest.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.compose.ui.unit.Constraints
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.app.machinetest.R
import com.app.machinetest.baseresult.BaseResult
import com.app.machinetest.databinding.FragmentAddUserBinding
import com.app.machinetest.databinding.FragmentWeatherBinding
import com.app.machinetest.preferences.PreferenceHandler
import com.app.machinetest.utils.Constants
import com.app.machinetest.utils.showToast
import com.app.machinetest.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val binding: FragmentWeatherBinding by viewBinding()
    private val viewModel: MainViewModel by activityViewModels()
    private var navController: NavController? = null

    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.listener = this
        viewModel.getWeatherData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                BaseResult.Status.SUCCESS -> {
                    binding.mainLayout.visibility = View.VISIBLE
                    binding.tempData.text = it?.data?.current?.temp.toString()
                    binding.humidityData.text = it?.data?.current?.humidity.toString()
                    binding.windSpeedData.text = it?.data?.current?.wind_speed.toString()
                    binding.weatherTypeData.text =
                        it?.data?.current?.weather?.get(0)?.description.toString()

                }

                BaseResult.Status.ERROR -> {
                    showToast(it.message.toString())

                }
            }
        })

        viewModel.getWeatherData(Constants.BASE_URL_WEATHER)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getWeatherData(Constants.BASE_URL_WEATHER)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.logOut -> {

                preferenceHandler.isLogged = false
                //viewModel.clearDb()
                navController?.navigate(R.id.action_weatherFragment_to_home)


            }
            binding.backBtn -> {
                requireActivity().onBackPressed()
            }
        }
    }
}