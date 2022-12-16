package uz.digital.contactapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.digital.contactapp.model.Contact
import uz.digital.contactapp.util.Constants.DB_NAME
import uz.digital.contactapp.util.Constants.ID
import uz.digital.contactapp.util.Constants.NAME
import uz.digital.contactapp.util.Constants.NUMBER
import uz.digital.contactapp.util.Constants.TABLE_NAME

class ContactDatabase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1),
    DatabaseService {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME TEXT NOT NULL, $NUMBER TEXT NOT NULL)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun addContact(contact: Contact) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, contact.name)
        contentValues.put(NUMBER, contact.number)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun getAllContactList(): List<Contact> {
        val database = this.readableDatabase
        val contactList = mutableListOf<Contact>()
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val contact = Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
                contactList.add(contact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contactList
    }
}