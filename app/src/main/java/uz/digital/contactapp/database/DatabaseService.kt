package uz.digital.contactapp.database

import uz.digital.contactapp.model.Contact

interface DatabaseService {
    fun addContact(contact: Contact)
    fun getAllContactList(): List<Contact>
    fun updateContact(contact: Contact)
    fun deleteContact(id: Int)
    fun deleteAllContacts()
}