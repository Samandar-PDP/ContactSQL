package uz.digital.contactapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import uz.digital.contactapp.R
import uz.digital.contactapp.adapter.ContactAdapter
import uz.digital.contactapp.database.ContactDatabase
import uz.digital.contactapp.databinding.ActivityContactListBinding

class ContactListActivity : AppCompatActivity() {
    private val binding by lazy { ActivityContactListBinding.inflate(layoutInflater) }
    private lateinit var contactDatabase: ContactDatabase
    private val contactAdapter by lazy { ContactAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        contactDatabase = ContactDatabase(this)

        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@ContactListActivity)
            adapter = contactAdapter
        }
        contactAdapter.submitList(contactDatabase.getAllContactList())

        contactAdapter.onClick = {
            val bundle = bundleOf("contact" to it)
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}