package com.belema.listofkotlindevelopers.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.belema.listofkotlindevelopers.local.entity.ItemEntity

/**
 * Created by Belema Ogan on 2/2/21.
 */
class ItemListDiffUtil (private val oldList: List<ItemEntity>?,
                        private val newList: List<ItemEntity>?): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList?.size ?: 0

    override fun getNewListSize(): Int = newList?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)?.id == newList?.get(newItemPosition)?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition) == newList?.get(newItemPosition)
    }

}