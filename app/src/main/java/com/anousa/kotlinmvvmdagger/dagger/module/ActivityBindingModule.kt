package com.anousa.kotlinmvvmdagger.dagger.module

import com.anousa.kotlinmvvmdagger.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}