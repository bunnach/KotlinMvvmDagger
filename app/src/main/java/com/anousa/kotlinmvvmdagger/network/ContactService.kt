package com.anousa.kotlinmvvmdagger.network

import com.anousa.kotlinmvvmdagger.data.Contact
import io.reactivex.Observable
import retrofit2.http.GET

interface ContactService {

    @GET("contacts.php")
    fun getAllContact(): Observable<List<Contact>>
}