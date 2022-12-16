package uz.digital.contactapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import uz.digital.contactapp.R
import uz.digital.contactapp.database.ContactDatabase
import uz.digital.contactapp.databinding.ActivityAddContactBinding
import uz.digital.contactapp.model.Contact

class AddContactActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddContactBinding.inflate(layoutInflater) }
    private lateinit var contactDatabase: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        contactDatabase = ContactDatabase(this)

        binding.btnSave.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val number = binding.number.text.toString().trim()
            if (name.isNotBlank() || number.isNotBlank()) {
                contactDatabase.addContact(Contact(name = name, number = number))
                Snackbar.make(binding.root, "Saved", Snackbar.LENGTH_SHORT).show()
                binding.name.text?.clear()
                binding.number.text?.clear()
            }
        }
    }
}