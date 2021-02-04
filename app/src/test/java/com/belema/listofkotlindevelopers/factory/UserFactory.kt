package com.belema.listofkotlindevelopers.factory

import com.belema.listofkotlindevelopers.local.entity.UserEntity
import com.belema.listofkotlindevelopers.remote.model.User

/**
 * Created by Belema Ogan on 2/4/21.
 */
object UserFactory {

    fun makeUserEntity(): UserEntity {
        return with(DataFactory){
            UserEntity(randomString(), randomString(), randomString(), randomInt(), randomInt(),
            randomString(), randomString(), randomInt())
        }
    }

    fun makeUser(): User {
        return  with(DataFactory){
            User(randomString(), randomString(), randomString(), randomInt(), randomInt(),
            randomString(), randomString(), randomInt())
        }
    }
}