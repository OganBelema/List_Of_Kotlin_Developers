package com.belema.listofkotlindevelopers.mapper

import com.belema.listofkotlindevelopers.factory.ItemFactory
import com.belema.listofkotlindevelopers.local.entity.ItemEntity
import com.belema.listofkotlindevelopers.remote.model.Item
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Belema Ogan on 2/4/21.
 */
@RunWith(JUnit4::class)
class ItemMapperTest {

    private val itemMapper = ItemMapper()

    @Test
    fun testFromEntity_returnsItemObjectWithSameProperties() {
        val itemEntity = ItemFactory.makeItemEntity()
        val item = itemMapper.fromEntity(itemEntity)

        //check that mapper returned Item object
        assertThat(item, instanceOf(Item::class.java))

        //check that mapper mapped the properties correctly
        assertEqualData(itemEntity, item)
    }

    @Test
    fun testToEntity_returnsItemEntityObjectWithSameProperties() {
        val item = ItemFactory.makeItem()
        val itemEntity = itemMapper.toEntity(item)

        //check that mapper returned ItemEntity object
        assertThat(itemEntity, instanceOf(ItemEntity::class.java))

        //check that mapper mapped the properties correctly
        assertEqualData(itemEntity, item)
    }

    @Test
    fun testFromEntityList_returnsListOfItemObjectsWithSameProperties() {
        val itemEntities = listOf(ItemFactory.makeItemEntity())
        val items = itemMapper.fromEntityList(itemEntities)

        //check that mapper returned list of Topic object
        assertThat(items?.get(0), instanceOf(Item::class.java))

        //check that mapper mapped the properties correctly
        assertEqualData(itemEntities[0], items?.get(0))
    }

    @Test
    fun testToEntityList_returnsListOfItemEntityObjectsWithSameProperties() {
        val items = listOf(ItemFactory.makeItem())
        val itemEntities = itemMapper.toEntityList(items)

        //check that mapper returned list of TopicEntity object
        assertThat(itemEntities?.get(0), instanceOf(ItemEntity::class.java))

        //check that mapper mapped the properties correctly
        assertEqualData(itemEntities?.get(0), items[0])
    }

    private fun assertEqualData(entity: ItemEntity?, model: Item?) {
        assertEquals(entity?.id, model?.id)
        assertEquals(entity?.login, model?.login)
        assertEquals(entity?.avatarUrl, model?.avatarUrl)
    }
}