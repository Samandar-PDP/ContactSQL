package uz.digital.contactapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import uz.digital.contactapp.R
import uz.digital.contactapp.database.ContactDatabase
import uz.digital.contactapp.databinding.ActivityDetailBinding
import uz.digital.contactapp.model.Contact

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var contactDatabase: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        contactDatabase = ContactDatabase(this)

        val contact = intent.getSerializableExtra("contact") as Contact

        binding.apply {
            name.setText(contact.name)
            number.setText(contact.number)
        }
        binding.btnDelete.setOnClickListener {
            contactDatabase.deleteContact(contact.id!!)
            Snackbar.make(it, "Deleted", Snackbar.LENGTH_SHORT).show()
        }
        binding.btnUpdate.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val number = binding.number.text.toString().trim()
            contactDatabase.updateContact(Contact(contact.id, name, number))
            Snackbar.make(it, "Updated", Snackbar.LENGTH_SHORT).show()
        }
    }
}