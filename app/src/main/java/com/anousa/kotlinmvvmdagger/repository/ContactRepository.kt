package com.anousa.kotlinmvvmdagger.repository

import com.anousa.kotlinmvvmdagger.data.Contact
import com.anousa.kotlinmvvmdagger.network.ContactService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ContactRepository @Inject constructor(private val service: ContactService) {

    fun getAllContacts(): Observable<List<Contact>> {
        return service.getAllContact()
    }

}