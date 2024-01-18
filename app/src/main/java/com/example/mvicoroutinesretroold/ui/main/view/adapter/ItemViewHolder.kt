package com.example.mvicoroutinesretroold.ui.main.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.mvicoroutinesretroold.R
import com.example.mvicoroutinesretroold.ui.data.model.User

class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
    private var textViewUserName : TextView? = null
    private var textViewUserEmail : TextView? = null
    private var imageViewAvatar : ImageView
    init {
        textViewUserName = itemView.findViewById(R.id.textViewUserName)
        textViewUserEmail = itemView.findViewById(R.id.textViewUserEmail)
        imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar)
    }

    fun bind(user: User){
        textViewUserName?.text = user.name
        textViewUserEmail?.text = user.email
        imageViewAvatar.context?.let {
            Glide.with(it)
                .load(user.avatar)
                .into(imageViewAvatar)
        }
    }
}