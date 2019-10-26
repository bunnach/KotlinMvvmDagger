package com.anousa.kotlinmvvmdagger.repository

import androidx.lifecycle.LiveData
import com.anousa.kotlinmvvmdagger.data.Contact
import com.anousa.kotlinmvvmdagger.network.ContactService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(private val service: ContactService) {

    fun getAllContacts(): Observable<LiveData<List<Contact>>> {
        return service.getAllContact()
    }

}