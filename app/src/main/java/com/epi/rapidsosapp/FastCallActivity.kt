package com.epi.rapidsosapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView

class FastCallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fast_call)

        val ambulanceButton = findViewById<RelativeLayout>(R.id.ambulanceCALL)
        ambulanceButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:190")
            startActivity(callIntent)
        }
        val policeButton = findViewById<RelativeLayout>(R.id.policeCALL)
        policeButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:197")
            startActivity(callIntent)
        }
        val fireButton = findViewById<RelativeLayout>(R.id.fbCALL)
        fireButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:198")
            startActivity(callIntent)
        }
//        val homeButton = findViewById<CardView>(R.id.fr)
//        homeButton.setOnClickListener {
//            val callIntent = Intent(Intent.ACTION_CALL)
//            callIntent.data = Uri.parse("tel:73 505050")
//            startActivity(callIntent)
//        }
//        val backIcon = findViewById<CardView>(R.id.backIcon)
//        backIcon.setOnClickListener(){
//            startActivity(Intent(this, MainActivity::class.java))
//
//
//        }
    }
}