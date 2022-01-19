package com.app.machinetest.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.app.machinetest.R
import com.app.machinetest.databinding.FragmentAddUserBinding
import com.app.machinetest.localdatabaseservice.entities.UserEntity
import com.app.machinetest.preferences.PreferenceHandler
import com.app.machinetest.utils.showToast
import com.app.machinetest.viewmodels.AddUserViewModel
import com.app.machinetest.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern
import javax.inject.Inject


@AndroidEntryPoint
class FragmentAddUser : Fragment() {
    private var navController: NavController? = null
    private val binding: FragmentAddUserBinding by viewBinding()
    private val viewModel: AddUserViewModel by viewModels()


    @Inject
    lateinit var preferenceHandler: PreferenceHandler

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
        return inflater.inflate(R.layout.fragment_add_user, container, false)
        // Inflate the layout for this fragment



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {



            viewModel.insertUserData.observe(viewLifecycleOwner, Observer {

               showToast("Add User successfully")
                requireActivity().onBackPressed()
            })

            binding.subBtn.setOnClickListener {

                if (validateData()) {

                    viewModel.insertUserData(UserEntity(0,
                        binding.firstName.text.toString(),
                        binding.lastName.text.toString(),
                        binding.email.text.toString()))

                }else
                {
                    println("@XP" +"NO")
                }

            }
        } catch (e: Exception) {
            println("@XP" + e.message)
        }
    }

    override fun onDestroy() {
        println("@XP" +"YES")
        super.onDestroy()
    }

    fun validateData(): Boolean {
        if (binding.firstName.text.toString().isEmpty()) {
            showToast("FirstName cannot be empty")
            return false
        } else if (binding.lastName.text.toString().isEmpty()) {
            showToast("LastName cannot be empty")
            return false
        } else if (binding.email.text.toString().isEmpty()) {
            showToast("Email cannot be empty")
            return false
        } else if (!isEmailValid(binding.email.text.toString())) {
            showToast(resources.getString(R.string.valid_email))
            return false
        } else {
            return true
        }
    }

    fun isEmailValid(email: String): Boolean {
        val pattern =
            Pattern.compile("[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
                Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }


}