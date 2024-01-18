package com.example.mvicoroutinesretroold.ui.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvicoroutinesretroold.R
import com.example.mvicoroutinesretroold.ui.data.model.User

class RecyclerAdapter(val userList: ArrayList<User>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun addData(newUserList: List<User>){
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }
}