package com.hcdisat.usercrud.di

import com.hcdisat.usercrud.network.IUsersRepository
import com.hcdisat.usercrud.network.UsersApi
import com.hcdisat.usercrud.network.UsersRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    @Provides
    fun providesUsersRepository(userApi: UsersApi): IUsersRepository =
        UsersRepository(userApi)

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): UsersApi =
        Retrofit
            .Builder()
            .baseUrl(UsersApi.BASE_PATH)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
                // Converts Retrofit's Response or Call into RxJava Observable (Single, Completable, etc)
//            .addCallAdapterFactory() // used for RXJava CallAdapterFactory
            .build()
            .create(UsersApi::class.java)
}

private const val REQUEST_TIMEOUT = 20L