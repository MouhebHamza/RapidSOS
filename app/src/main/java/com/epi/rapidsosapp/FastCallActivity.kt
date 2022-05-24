package com.epi.rapidsosapp

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class FastCallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fast_call)



    val ambulanceButton = findViewById<RelativeLayout>(R.id.ambulanceCALL)
    ambulanceButton.setOnClickListener {
            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.call_dialog)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:190")
                startActivity(callIntent)
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }







        val policeButton = findViewById<RelativeLayout>(R.id.policeCALL)
        policeButton.setOnClickListener {
            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.call_dialog)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:197")
                startActivity(callIntent)

            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }





        val fireButton = findViewById<RelativeLayout>(R.id.fbCALL)
        fireButton.setOnClickListener {
            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.call_dialog)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:198")
                startActivity(callIntent)
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        val coastGuard = findViewById<RelativeLayout>(R.id.CGCALL)
        coastGuard.setOnClickListener {
            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.call_dialog)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:190")
                startActivity(callIntent)
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        val nationalGuard = findViewById<RelativeLayout>(R.id.NGCALL)
        nationalGuard.setOnClickListener {
            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.call_dialog)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:977")
                startActivity(callIntent)
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        val cecovery = findViewById<RelativeLayout>(R.id.RVCALL)
        cecovery.setOnClickListener {
            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.call_dialog)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:8070")
                startActivity(callIntent)
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}