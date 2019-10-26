package com.anousa.kotlinmvvmdagger.network

import androidx.lifecycle.LiveData
import com.anousa.kotlinmvvmdagger.data.Contact
import io.reactivex.Observable
import retrofit2.http.GET

interface ContactService {

    @GET("contacts.php")
    fun getAllContact(): Observable<LiveData<List<Contact>>>
}