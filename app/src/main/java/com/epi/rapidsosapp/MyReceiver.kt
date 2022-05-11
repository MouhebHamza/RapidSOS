package com.epi.rapidsosapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_SCREEN_OFF) {
            Log.e("In on receive", "In Method:  ACTION_SCREEN_OFF")
            countPowerOff++
        } else if (intent.action == Intent.ACTION_SCREEN_ON) {
            Log.e("In on receive", "In Method:  ACTION_SCREEN_ON")
        } else if (intent.action == Intent.ACTION_USER_PRESENT) {
            Log.e("In on receive", "In Method:  ACTION_USER_PRESENT")
            if (countPowerOff > 2) {
                countPowerOff = 0

                Toast.makeText(context, "SMS safely sent", Toast.LENGTH_LONG).show()
                Intent(context, MainActivity::class.java).also {
                    it.putExtra("SEND_SMS_NOW",true)
                    context.startActivity(it)

                }
            }
        }
    }

    companion object {
        private var countPowerOff = 0
    }
    private fun sendSMS(phoneNumber: String, message: String) {
        var obj= SmsManager.getDefault();
        obj.sendTextMessage(phoneNumber, null, message,null, null)
    }

}