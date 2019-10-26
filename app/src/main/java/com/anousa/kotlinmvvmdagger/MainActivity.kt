package com.anousa.kotlinmvvmdagger

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anousa.kotlinmvvmdagger.adapter.ContactAdapter
import com.anousa.kotlinmvvmdagger.data.Contact
import com.anousa.kotlinmvvmdagger.viewmodel.ContactViewModel
import com.anousa.kotlinmvvmdagger.viewmodel.ContactViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var contactRepository: ContactViewModelFactory
    lateinit var contactViewModel: ContactViewModel
    private var adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactViewModel = ViewModelProviders.of(this, contactRepository).get(ContactViewModel::class.java)

        initRecyclerView()

        contactViewModel.isLoading.observe(this, Observer<Boolean> {
            loadingProgressBar.visibility = if (it == true)  View.VISIBLE else View.GONE
        })

        contactViewModel.contactList.observe(this, Observer<List<Contact>>{
            adapter.setContactList(it)
        })

        getAllContacts()
    }

    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        contactRecyclerView.layoutManager = layoutManager
        contactRecyclerView.adapter = adapter
    }

    private fun getAllContacts() {
        contactViewModel.getAllContacts()
    }
}
