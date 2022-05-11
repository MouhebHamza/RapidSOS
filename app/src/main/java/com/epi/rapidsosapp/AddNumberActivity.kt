package com.epi.rapidsosapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class AddNumberActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private lateinit var uuid: String
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_number)
        val pickBTN = findViewById<CardView>(R.id.pickBTN)
        val submitButton = findViewById<TextView>(R.id.btn_submit)
        val nameCT = findViewById<TextInputLayout>(R.id.nameCT)
        val phoneCT = findViewById<TextInputLayout>(R.id.phoneCT)
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)

        database = FirebaseDatabase.getInstance("https://rapidsosapp-default-rtdb.europe-west1.firebasedatabase.app").getReference("users")
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                uuid = profile.uid
            }
        }
        submitButton.setOnClickListener() {

            val phoneNumber = phoneET.text.toString()
            val PhoneName = nameET.text.toString()
            val newPhone = Phone(PhoneName,phoneNumber)
            addNumber(newPhone)
            submitForm()
        }
        nameFocusListener()
        phoneFocusListener()

        pickBTN.setOnClickListener() {
            var i= Intent(Intent.ACTION_PICK)
            i.type= ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i,111)
        }
        val backIcon = findViewById<CardView>(R.id.backIcon)
        backIcon.setOnClickListener(){
            startActivity(Intent(this, SetupActivity::class.java))


        }


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val nameCT = findViewById<TextInputLayout>(R.id.nameCT)
        val phoneCT = findViewById<TextInputLayout>(R.id.phoneCT)
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)
        if(requestCode==111 && resultCode== Activity.RESULT_OK){
            var contcturi : Uri =data?.data ?:return
            var cols:Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

            var rs: Cursor? =contentResolver.query(contcturi,cols,null,null,null)
            if(rs?.moveToFirst()!!){
                phoneET.setText(rs.getString(0))
                nameET.setText(rs.getString(1))


            }

        }
    }

    private fun addNumber( phone: Phone) {

        database = FirebaseDatabase.getInstance("https://rapidsosapp-default-rtdb.europe-west1.firebasedatabase.app").getReference("users/${uuid}/phones")
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)
        val newPhone = Phone(phone.name ,phone.number )

        val phoneID :String? = phone.number

        if (phoneID != null) {
            database.child(phoneID).setValue(newPhone).addOnSuccessListener {
                Toast.makeText(this,"Successfuly Updated", Toast.LENGTH_SHORT).show()
                nameET.setText(null)
                phoneET.setText(null)

            }.addOnFailureListener{

                Toast.makeText(this,"Failed to Update", Toast.LENGTH_SHORT).show()

            }
        }


    }
    private fun submitForm()
    {
        val nameCT = findViewById<TextInputLayout>(R.id.nameCT)
        val phoneCT = findViewById<TextInputLayout>(R.id.phoneCT)
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)
        nameCT.helperText = validName()
        phoneCT.helperText = validPhone()

        val validName = nameCT.helperText == null
        val validPhone = phoneCT.helperText == null

        if ( validName && validPhone)
            resetForm()
        else
            invalidForm()
    }

    private fun invalidForm()
    {
        val nameCT = findViewById<TextInputLayout>(R.id.nameCT)
        val phoneCT = findViewById<TextInputLayout>(R.id.phoneCT)
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)
        var message = ""
        if(nameCT.helperText != null)
            message += "\n\nPassword: " + nameCT.helperText
        if(phoneCT.helperText != null)
            message += "\n\nPhone: " + phoneCT.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                // do nothing
            }
            .show()
    }

    private fun resetForm()
    {
        val nameCT = findViewById<TextInputLayout>(R.id.nameCT)
        val phoneCT = findViewById<TextInputLayout>(R.id.phoneCT)
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)
        var message = "\nPassword: " + nameET.text
        message += "\nPhone: " + phoneET.text
        AlertDialog.Builder(this)
            .setTitle("Form submitted")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                nameET.text = null
                phoneET.text = null
                nameCT.helperText = getString(R.string.required)
                phoneCT.helperText = getString(R.string.required)
            }
            .show()
    }


    private fun nameFocusListener()
    {
        val nameCT = findViewById<TextInputLayout>(R.id.nameCT)
        val phoneCT = findViewById<TextInputLayout>(R.id.phoneCT)
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)
        nameET.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                nameCT.helperText = validName()
            }
        }
    }

    private fun validName(): String?
    {
        val nameCT = findViewById<TextInputLayout>(R.id.nameCT)
        val phoneCT = findViewById<TextInputLayout>(R.id.phoneCT)
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)
        val passwordText = nameET.text.toString()
        if(passwordText.length < 3)
        {
            return "Minimum 8 Character Password"
        }


        return null
    }

    private fun phoneFocusListener()
    {
        val nameCT = findViewById<TextInputLayout>(R.id.nameCT)
        val phoneCT = findViewById<TextInputLayout>(R.id.phoneCT)
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)
        phoneET.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                phoneCT.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String?
    {
        val nameCT = findViewById<TextInputLayout>(R.id.nameCT)
        val phoneCT = findViewById<TextInputLayout>(R.id.phoneCT)
        val nameET = findViewById<TextInputEditText>(R.id.nameET)
        val phoneET = findViewById<TextInputEditText>(R.id.phoneET)
        val phoneText = phoneET.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 8)
        {
            return "Must be 8 Digits"
        }
        return null
    }

}