package com.epi.rapidsosapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
            startActivity(Intent(this, ViewRegisteredNumbersActivity::class.java))


        }
        val Instructions = findViewById<RelativeLayout>(R.id.Instructions)
        Instructions.setOnClickListener(){
            startActivity(Intent(this, InstructionsActvity::class.java))


        }

        val RateUS = findViewById<RelativeLayout>(R.id.rateUS)
        RateUS.setOnClickListener {
            val uri: Uri = Uri.parse("apps/details?id=com.google.android.apps.meetings")
            val goToMarket = Intent(Intent.ACTION_VIEW,uri)
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK)

            try {
                startActivity(goToMarket)
            }catch (e: ActivityNotFoundException){
                startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps")
                ))
            }
        }



        val shareIT = findViewById<RelativeLayout>(R.id.shareIT)
        shareIT.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                val Body:String = "Download this app"
                val Sub:String = "Try this app http://play.google.com"
                intent.putExtra(Intent.EXTRA_TEXT,Body)
                intent.putExtra(Intent.EXTRA_TEXT, Sub)
                startActivity(Intent.createChooser(intent,"share using"))


        }





    }
}