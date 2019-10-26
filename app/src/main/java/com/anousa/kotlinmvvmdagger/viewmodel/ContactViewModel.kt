package com.anousa.kotlinmvvmdagger.viewmodel

import androidx.lifecycle.ViewModel
import com.anousa.kotlinmvvmdagger.repository.ContactRepository

class ContactViewModel(private val repository: ContactRepository): ViewModel() {
}