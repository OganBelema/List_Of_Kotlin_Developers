package com.belema.listofkotlindevelopers.mapper

import com.belema.listofkotlindevelopers.local.entity.UserEntity
import com.belema.listofkotlindevelopers.remote.model.User
import javax.inject.Inject

/**
 * Created by Belema Ogan on 2/2/21.
 */
class UserMapper @Inject constructor(): EntityMapper<UserEntity, User> {

    override fun fromEntity(entity: UserEntity): User {
        return User(entity.avatarUrl, entity.email, entity.company,
            entity.followers, entity.id, entity.login, entity.name, entity.publicRepos)
    }

    override fun toEntity(model: User): UserEntity {
        return UserEntity(model.avatarUrl, model.company, model.email, model.followers,
        model.id, model.login, model.name, model.publicRepos)
    }

    override fun fromEntityList(entities: List<UserEntity>?): List<User>? {
        return if (entities != null){
            val users = ArrayList<User>()
            for (entity in entities){
                users.add(fromEntity(entity))
            }
            users
        } else {
            null
        }
    }

    override fun toEntityList(models: List<User>?): List<UserEntity>? {
        return if (models != null){
            val users = ArrayList<UserEntity>()
            for (model in models){
                users.add(toEntity(model))
            }
            users
        } else {
            null
        }
    }
}