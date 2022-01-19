package com.app.machinetest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.machinetest.R
import com.app.machinetest.databinding.ItemUserlistBinding
import com.app.machinetest.localdatabaseservice.entities.UserEntity

class UserListAdapter(val context: Context, val listener: View.OnClickListener) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemUserlistBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    val differCallback = object : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
    fun submitList(list: List<UserEntity>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemUserListBinding: ItemUserlistBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_userlist, parent, false)
        return ViewHolder(itemUserListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        val itemBinding = holder.itemBinding
        itemBinding.item = data
        itemBinding.listener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}