package com.epi.rapidsosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)


        val addPhone = findViewById<RelativeLayout>(R.id.RegisterN)
        addPhone.setOnClickListener(){
            startActivity(Intent(this, AddNumberActivity::class.java))


        }
        val changeMessage = findViewById<RelativeLayout>(R.id.EditSOSMessage)
        changeMessage.setOnClickListener(){
            startActivity(Intent(this, CustomMessageActivity::class.java))


        }
        val viewPhone = findViewById<RelativeLayout>(R.id.ViewRegistred)
        viewPhone.setOnClickListener(){
            //startActivity(Intent(this, ViewRegisteredNumbersActivity::class.java))


        }
        val Instructions = findViewById<RelativeLayout>(R.id.Instructions)
        Instructions.setOnClickListener(){
            startActivity(Intent(this, InstructionsActvity::class.java))


        }
//        val backIcon = findViewById<CardView>(R.id.backIcon)
//        backIcon.setOnClickListener(){
//            startActivity(Intent(this, MainActivity::class.java))
//
//
//        }


    }
}