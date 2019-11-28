package com.anousa.kotlinmvvmdagger.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.anousa.kotlinmvvmdagger.SchedulerTestRule
import com.anousa.kotlinmvvmdagger.data.Contact
import com.anousa.kotlinmvvmdagger.network.ContactService
import com.anousa.kotlinmvvmdagger.repository.ContactRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ContactViewModelTest {

    @Rule
    @JvmField
    var mTestRule = SchedulerTestRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var contactService: ContactService
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var contactRepository: ContactRepository

    @Mock
    lateinit var listObserver: Observer<List<Contact>>
    @Mock
    lateinit var loadingObserver: Observer<Boolean>
    @Mock
    lateinit var exceptionObserver: Observer<Throwable>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        contactRepository = ContactRepository(contactService)
        contactViewModel = ContactViewModel(contactRepository)
        contactViewModel.isLoading.observeForever(loadingObserver)
        contactViewModel.contactList.observeForever(listObserver)
        contactViewModel.exception.observeForever(exceptionObserver)
    }

    @Test
    fun testApiFetchDataSuccess() {
        val responseList = arrayListOf<Contact>()

        // Mock API response
        `when`(contactRepository.getAllContacts()).thenReturn(Observable.just(responseList))
        contactViewModel.getAllContacts()
        verify(loadingObserver, times(1)).onChanged(true)
        verify(listObserver).onChanged(responseList)
        verify(loadingObserver, times(1)).onChanged(false)
        verify(exceptionObserver, never()).onChanged(Throwable("error"))
    }

    @Test
    fun testApiFetchDataFail() {
        val responseList = arrayListOf<Contact>()
        val myError = Throwable("my error")
        // Mock API response
        `when`(contactRepository.getAllContacts()).thenReturn(Observable.error(myError))
        contactViewModel.getAllContacts()
        verify(loadingObserver, times(1)).onChanged(true)
        verify(listObserver, never()).onChanged(responseList)
        verify(loadingObserver, times(1)).onChanged(false)
        verify(exceptionObserver).onChanged(myError)
    }

}