package com.epi.rapidsosapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout

class InstructionsActvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)


        val ambulanceButton = findViewById<Button>(R.id.instructions)
        ambulanceButton.setOnClickListener {
            startActivity(Intent(this, SetupActivity::class.java))

        }
    }
}