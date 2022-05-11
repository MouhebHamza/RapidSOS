package com.epi.rapidsosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class CustomMessageActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private lateinit var uuid: String
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_message)
        val submitBTN = findViewById<Button>(R.id.textButton)
        val tvMessage = findViewById<EditText>(R.id.messageF)

        database = FirebaseDatabase.getInstance("https://rapidsosapp-default-rtdb.europe-west1.firebasedatabase.app").getReference("users")
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                uuid = profile.uid
            }
        }
        submitBTN.setOnClickListener() {
            val newMessage = tvMessage.text.toString()
            changeMessage(newMessage)
        }
        val backIcon = findViewById<CardView>(R.id.backIcon)
        backIcon.setOnClickListener(){
            startActivity(Intent(this, SetupActivity::class.java))


        }
    }
    private fun changeMessage( message: String) {

        database = FirebaseDatabase.getInstance("https://rapidsosapp-default-rtdb.europe-west1.firebasedatabase.app").getReference("users")
        val user = mapOf<String,String>(
            "message" to message,
        )
        val tvMessage = findViewById<EditText>(R.id.messageF)

        database.child(uuid).updateChildren(user).addOnSuccessListener {

            tvMessage.text?.clear()

            Toast.makeText(this,"Successfuly Updated", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update", Toast.LENGTH_SHORT).show()

        }}
}