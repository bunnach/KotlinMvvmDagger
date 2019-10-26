package com.anousa.kotlinmvvmdagger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anousa.kotlinmvvmdagger.R
import com.anousa.kotlinmvvmdagger.data.Contact
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.contact_list_item.view.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    private var contactList = ArrayList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.contact_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactList[position]
        holder.name.text = contact.name
        holder.phone.text = contact.phone
        Picasso.get().load(contact.image).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class MyViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        var thumbnail: ImageView = rootView.thumbnail
        val name: TextView = rootView.name
        val phone: TextView = rootView.phone
    }

}