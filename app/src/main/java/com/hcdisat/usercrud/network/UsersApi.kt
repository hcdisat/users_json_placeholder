package com.hcdisat.usercrud.network

import com.hcdisat.usercrud.models.User
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    @GET(USER_PATH)
    suspend fun getUsers(): Response<List<User>>

    companion object {
        const val BASE_PATH = "https://jsonplaceholder.typicode.com/"
        private const val USER_PATH = "users"
    }
}