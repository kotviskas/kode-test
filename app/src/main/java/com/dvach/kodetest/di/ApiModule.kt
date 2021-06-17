package com.dvach.kodetest.di

import com.dvach.kodetest.api.RepoApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): RepoApi {
        return retrofit.create(RepoApi::class.java)
    }
    single { provideUseApi(get()) }
}