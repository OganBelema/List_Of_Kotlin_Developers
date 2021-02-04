package com.belema.listofkotlindevelopers.factory

import com.belema.listofkotlindevelopers.local.entity.ItemEntity
import com.belema.listofkotlindevelopers.remote.model.Item

/**
 * Created by Belema Ogan on 2/4/21.
 */
object ItemFactory {
    fun makeItemEntity(): ItemEntity {
        return with(DataFactory){
            ItemEntity(randomString(), randomInt(), randomString())
        }
    }

    fun makeItem(): Item {
        return  with(DataFactory){
            Item(randomString(), randomInt(), randomString())
        }
    }
}