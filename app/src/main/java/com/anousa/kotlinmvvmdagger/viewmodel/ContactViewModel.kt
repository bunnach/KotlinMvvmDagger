package com.anousa.kotlinmvvmdagger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anousa.kotlinmvvmdagger.data.Contact
import com.anousa.kotlinmvvmdagger.repository.ContactRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactViewModel @Inject constructor(private val repository: ContactRepository) :
    ViewModel() {
    var isLoading = MutableLiveData<Boolean>().apply { postValue(false) }
    private val compositeDisposable = CompositeDisposable()
    val contactList = MutableLiveData<LiveData<List<Contact>>>()
    val errorMesage = MutableLiveData<String>().apply { postValue("") }

    fun getAllContacts() {
        compositeDisposable.add(
            repository.getAllContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.postValue(true) }
                .doFinally { isLoading.postValue(false) }
                .subscribe({
                    contactList.postValue(it)
                }, {
                    errorMesage.postValue(it.message)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}