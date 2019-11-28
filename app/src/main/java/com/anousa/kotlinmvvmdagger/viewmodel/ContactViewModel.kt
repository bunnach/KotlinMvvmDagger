package com.anousa.kotlinmvvmdagger.viewmodel

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
    var isLoading = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()
    val contactList = MutableLiveData<List<Contact>>()
    /**
     * Handle errors
     */
    var exception = MutableLiveData<Throwable>()

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
                    exception.postValue(it)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}