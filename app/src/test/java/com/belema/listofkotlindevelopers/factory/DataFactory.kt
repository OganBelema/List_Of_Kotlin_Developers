package com.belema.listofkotlindevelopers.factory

import java.util.*
import kotlin.random.Random

/**
 * Created by Belema Ogan on 1/18/21.
 */
internal object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return Random.nextInt()
    }

}