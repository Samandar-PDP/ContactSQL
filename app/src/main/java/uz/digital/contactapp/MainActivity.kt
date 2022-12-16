package uz.digital.contactapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.digital.contactapp.activity.AddContactActivity
import uz.digital.contactapp.activity.ContactListActivity
import uz.digital.contactapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            startActivity(Intent(this, ContactListActivity::class.java))
        }
        binding.btn2.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }
}