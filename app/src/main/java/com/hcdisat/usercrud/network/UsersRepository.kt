package com.hcdisat.usercrud.network

import com.hcdisat.usercrud.ui.states.UserState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

interface IUsersRepository {
    suspend fun getUsers(): Flow<UserState>
}

class UsersRepository(
    private val userApi: UsersApi
) : IUsersRepository {
    override suspend fun getUsers(): Flow<UserState> = flow {
        try {
            val response = userApi.getUsers()
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    emit(UserState.SUCCESS(body))
                } ?: throw Exception("Body is empty")
            } else throw Exception("No SUCCESS")
        } catch (e: Exception) {
            emit(UserState.ERROR(e))
        }
    }
}