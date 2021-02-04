package com.belema.listofkotlindevelopers.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.belema.listofkotlindevelopers.local.entity.ItemEntity

/**
 * Created by Belema Ogan on 2/2/21.
 */
class ItemListAdapter(private val clickListener: (login: String?) -> Unit):
    RecyclerView.Adapter<UserListItemViewHolder>() {

    private var items: List<ItemEntity>? = arrayListOf()

    fun setData(items: List<ItemEntity>?){
        val diffResult = DiffUtil.calculateDiff(ItemListDiffUtil(this.items, items))
        this.items = items
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder.from(parent.context, parent)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(clickListener, items?.get(position))
    }
}