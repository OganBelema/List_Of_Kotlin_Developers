package com.belema.listofkotlindevelopers.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.belema.listofkotlindevelopers.R
import com.belema.listofkotlindevelopers.databinding.UserListItemBinding
import com.belema.listofkotlindevelopers.local.entity.ItemEntity
import com.bumptech.glide.Glide

/**
 * Created by Belema Ogan on 2/2/21.
 */
class UserListItemViewHolder(private val userListItemBinding: UserListItemBinding):
    RecyclerView.ViewHolder(userListItemBinding.root) {

    fun bind(clickListener: (login: String?) -> Unit, item: ItemEntity?){
        userListItemBinding.apply {
            //display github name
            usernameTv.text = item?.login

            //load image into imageView
            Glide.with(root.context)
                .load(item?.avatarUrl)
                .error(R.drawable.ic_avatar)
                .into(profileImageView)

            //set click listener
            root.setOnClickListener {
                clickListener(item?.login)
            }
        }

    }

    companion object {
        fun from(context: Context, parent: ViewGroup): UserListItemViewHolder {
            return UserListItemViewHolder(
                UserListItemBinding.inflate(LayoutInflater.from(context), parent,
                    false)
            )
        }
    }
}