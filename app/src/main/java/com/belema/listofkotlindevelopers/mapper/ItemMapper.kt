package com.belema.listofkotlindevelopers.mapper

import com.belema.listofkotlindevelopers.local.entity.ItemEntity
import com.belema.listofkotlindevelopers.remote.model.Item
import javax.inject.Inject

/**
 * Created by Belema Ogan on 2/2/21.
 */
class ItemMapper @Inject constructor(): EntityMapper<ItemEntity, Item> {

    override fun fromEntity(entity: ItemEntity): Item {
        return Item(entity.avatarUrl, entity.id, entity.login)
    }

    override fun toEntity(model: Item): ItemEntity {
        return ItemEntity(model.avatarUrl, model.id, model.login)
    }

    override fun fromEntityList(entities: List<ItemEntity>?): List<Item>? {
        return if (entities != null) {
            val items = ArrayList<Item>()
            for (entity in entities){
                items.add(fromEntity(entity))
            }
            items
        } else {
            null
        }
    }

    override fun toEntityList(models: List<Item>?): List<ItemEntity>? {
        return if (models != null) {
            val users = ArrayList<ItemEntity>()
            for (model in models){
                users.add(toEntity(model))
            }
            users
        } else {
            null
        }
    }

}