package com.epi.rapidsosapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class PermissionActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var uuid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        val locationBTN = findViewById<Button>(R.id.button1)
        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                uuid = profile.uid
                 initialUser()

            }
        }
        locationBTN.setOnClickListener(){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),101)


            }


        }

        val smsBTN = findViewById<Button>(R.id.button3)
        smsBTN.setOnClickListener(){
             if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CALL_PHONE),200)

            }

            val callBTN = findViewById<Button>(R.id.button2)
            callBTN.setOnClickListener(){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.SEND_SMS),200)

            }}
            val phoneBTN = findViewById<Button>(R.id.button3)
            smsBTN.setOnClickListener(){
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CALL_PHONE),200)

                }}

        }
        val submitBTN = findViewById<Button>(R.id.submit)
        submitBTN.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))


        }
    }

    private fun initialUser(){
        database = FirebaseDatabase.getInstance("https://rapidsosapp-default-rtdb.europe-west1.firebasedatabase.app").getReference("users")
        database.child(uuid).get().addOnSuccessListener {

            if (!it.exists()){
                val initialuser = User( uuid, "HELP ME!! IT'S AN EMERGENCY ")
                database.child(uuid).setValue(initialuser).addOnSuccessListener {
                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {

                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                }


            }else{
                Toast.makeText(this, "User exists", Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()


        }
    }
}