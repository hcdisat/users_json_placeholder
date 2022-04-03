package com.hcdisat.usercrud.ui.states

import com.hcdisat.usercrud.models.User

sealed class UserState {
    object LOADING: UserState()
    class SUCCESS(val usersList: List<User>): UserState()
    class ERROR(val throwable: Throwable): UserState()
}