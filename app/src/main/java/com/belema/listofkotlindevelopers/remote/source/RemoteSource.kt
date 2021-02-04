package com.belema.listofkotlindevelopers.remote.source

import com.belema.listofkotlindevelopers.remote.Api
import javax.inject.Inject

/**
 * Created by Belema Ogan on 2/2/21.
 */
class RemoteSource @Inject constructor(private val api: Api) {

    suspend fun fetchUserList() = api.getGithubUser()

    suspend fun fetchUser(user: String) = api.getUserData(user)
}