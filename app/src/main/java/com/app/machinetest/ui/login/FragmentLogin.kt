package com.app.machinetest.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.app.machinetest.R
import com.app.machinetest.databinding.FragmentLoginBinding
import com.app.machinetest.databinding.FragmentOnBoardingBinding
import com.app.machinetest.preferences.PreferenceHandler
import com.app.machinetest.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLogin.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class FragmentLogin : Fragment() {
    private var navController: NavController? = null
    @Inject
    lateinit var preferenceHandler: PreferenceHandler

    private val binding: FragmentLoginBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().onBackPressedDispatcher.addCallback(this@FragmentLogin) {
            // handle back event
            requireActivity().finish()
        }
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        binding.loginBtn.setOnClickListener {
            if (binding.email.text.isEmpty())
            {
                showToast(resources.getString(R.string.email_field_empty))
            }else if (!isEmailValid(binding.email.text.toString()))
            {
                showToast(resources.getString(R.string.valid_email))
            }
            else if (binding.password.text.isEmpty())
            {
                showToast(resources.getString(R.string.password_field_empty))
            }else
            {
                if (binding.email.text.toString() == "testapp@google.com" && binding.password.text.toString() == "Test@123456")
                {
                   preferenceHandler.isLogged=true
                    navController?.navigate(R.id.action_fragmentLogin_to_homeFragment)
                    showToast(resources.getString(R.string.success))
                }else
                {
                    showToast(resources.getString(R.string.error))
                }
            }
        }
    }

    fun isEmailValid(email: String): Boolean {
        val pattern = Pattern.compile("[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }


}