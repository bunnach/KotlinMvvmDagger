package com.anousa.kotlinmvvmdagger.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anousa.kotlinmvvmdagger.dagger.scope.ViewModelKey
import com.anousa.kotlinmvvmdagger.viewmodel.ContactViewModel
import com.thmeythemy.allprovinces.viewmodel.ContactViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ContactViewModel::class)
    abstract fun bindsContactViewModel(mainViewModel: ContactViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ContactViewModelFactory): ViewModelProvider.Factory
}