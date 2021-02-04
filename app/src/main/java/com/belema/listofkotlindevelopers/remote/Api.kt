package com.belema.listofkotlindevelopers.remote

import com.belema.listofkotlindevelopers.remote.model.User
import com.belema.listofkotlindevelopers.remote.model.UserListApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Belema Ogan on 2/1/21.
 */
interface Api {

    @GET("/search/users?q=language:kotlin+location:lagos")
    suspend fun getGithubUser(): UserListApiResponse

    @GET("/users/{user}")
    suspend fun getUserData(@Path("user") user: String): User
}