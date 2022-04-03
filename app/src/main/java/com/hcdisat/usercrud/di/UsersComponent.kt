package com.hcdisat.usercrud.di

import com.hcdisat.usercrud.MainActivity
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        ApplicationModule::class
    ]
)
abstract class UsersComponent {
    abstract fun inject(mainActivity: MainActivity)
}