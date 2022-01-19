package com.app.machinetest.ui.home

import android.os.Bundle
import android.os.UserManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.machinetest.R
import com.app.machinetest.databinding.FragmentHomeBinding
import com.app.machinetest.databinding.FragmentLoginBinding
import com.app.machinetest.localdatabaseservice.entities.UserEntity
import com.app.machinetest.preferences.PreferenceHandler
import com.app.machinetest.ui.adapter.UserListAdapter
import com.app.machinetest.utils.SwipeGesture

import com.app.machinetest.utils.showToast
import com.app.machinetest.viewmodels.HomeViewMode
import com.app.machinetest.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {
    private var navController: NavController? = null
    private val mainList: MutableList<UserEntity> = mutableListOf()

    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    private val viewModel: HomeViewMode by viewModels()
    var adapter: UserListAdapter? = null

    private val binding: FragmentHomeBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllUsersData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(this@HomeFragment) {
            // handle back event
            requireActivity().finish()
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("@Iee" + "1")
        initView()


        val swipeGesture = object : SwipeGesture(requireActivity()) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return super.onMove(recyclerView, viewHolder, target)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        println("@SHOW" + viewHolder.absoluteAdapterPosition)
                        viewModel.deleteItem(mainList[viewHolder.absoluteAdapterPosition])
                        if (mainList.size == 1) {

                            adapter?.differ?.submitList(null)
                            adapter?.notifyDataSetChanged()
                        } else {
                            mainList.remove(mainList[viewHolder.absoluteAdapterPosition])
                            adapter?.differ?.submitList(mainList)
                            adapter?.notifyDataSetChanged()
                        }


                    }


                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.userList)




        navController = Navigation.findNavController(view)

        binding.addBtn.setOnClickListener {

            navController?.navigate(R.id.action_homeFragment_to_fragmentAddUser)
        }
    }


    override fun onClick(p0: View?) {
        navController?.navigate(R.id.action_homeFragment_to_weatherFragment2)
    }

    private fun initView() {


        userListObserver()
        deleteUserObserver()
        println("@ENTE" + "initView")


    }

    private fun userListObserver() {
        viewModel.getAllUserData.observe(viewLifecycleOwner, Observer {


            adapter = UserListAdapter(requireActivity(), this)
            binding.userList.setHasFixedSize(true)
            binding.userList.adapter = adapter
            mainList.addAll(it)
            adapter?.differ?.submitList(it)
            adapter?.notifyDataSetChanged()


        })
    }

    fun deleteUserObserver() {
        viewModel.deleteLiveData.observe(viewLifecycleOwner, {
            println("@SHOWKK$it")
            viewModel.getAllUsersData()
        })

    }
}