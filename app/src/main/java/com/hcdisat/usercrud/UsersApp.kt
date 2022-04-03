package com.hcdisat.usercrud

import android.app.Application
import com.hcdisat.usercrud.di.ApplicationModule
import com.hcdisat.usercrud.di.DaggerUsersComponent
import com.hcdisat.usercrud.di.UsersComponent

class UsersApp: Application() {
    override fun onCreate() {
        super.onCreate()
        userComponent = DaggerUsersComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var userComponent: UsersComponent
    }
}