package com.anousa.kotlinmvvmdagger.dagger.component

import com.anousa.kotlinmvvmdagger.MyApplication
import com.anousa.kotlinmvvmdagger.dagger.module.NetworkModule
import com.anousa.kotlinmvvmdagger.dagger.module.ViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(application: MyApplication)
}