package com.example.suitmediatest.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.databinding.ItemListBinding

class UserAdapter(
    private val onClick: (DataItem) -> Unit
): PagingDataAdapter<DataItem, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {

    class UserViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(users : DataItem) {
            binding.apply {
                tvName.text = "${users.firstName} ${users.lastName}"
                tvEmail.text = users.email
                Glide.with(itemView.context)
                    .load(users.avatar)
                    .into(ivAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users = getItem(position)
        if (users != null) {
            holder.bind(users)
            holder.itemView.setOnClickListener {
                onClick(users)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}