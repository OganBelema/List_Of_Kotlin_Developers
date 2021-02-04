package com.belema.listofkotlindevelopers.mapper

/**
 * Created by Belema Ogan on 2/1/21.
 */
interface EntityMapper<E, D> {
    fun fromEntity(entity: E): D
    fun toEntity(model: D): E
    fun fromEntityList(entities: List<E>?): List<D>?
    fun toEntityList(models: List<D>?): List<E>?
}