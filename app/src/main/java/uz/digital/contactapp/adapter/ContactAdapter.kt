package uz.digital.contactapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.digital.contactapp.databinding.ContactLayoutBinding
import uz.digital.contactapp.model.Contact

class ContactAdapter: ListAdapter<Contact, ContactAdapter.ContactViewHolder>(DiffCallBack()) {
    lateinit var onClick: (Contact) -> Unit
    private class DiffCallBack: DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ContactLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ContactViewHolder(private val binding: ContactLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.number.text = contact.number
            binding.name.text = contact.name

            itemView.setOnClickListener {
                onClick(contact)
            }
        }
    }
}