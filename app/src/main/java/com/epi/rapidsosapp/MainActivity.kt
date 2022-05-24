package com.epi.rapidsosapp

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var uuid: String
     lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    private var myReceiver: MyReceiver? = null
    private var myPosition  = ""
   private var message  = ""
    private var allNumbers = mutableListOf<String>()
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        var sendNow = intent.getExtras()?.getBoolean("SEND_SMS_NOW")
        Log.d("tess", sendNow.toString())
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_USER_PRESENT)
        myReceiver = MyReceiver()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        registerReceiver(myReceiver, filter)
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                uuid = profile.uid
               // initialUser()

            }
        }
        Handler().postDelayed({
            Log.d("taggg", allNumbers.toString())
            val iterator = allNumbers.listIterator()
            for (item in iterator) {
                Log.d("taggg", item)

            }

        }, 5000)
        if (sendNow == true){
            fetchLocation()
            fetchMessage()
            fetchNumbers()
            Handler().postDelayed({
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "${message} \n \n Please reach ASAP to the bellow location \n \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }

            }, 5000)


        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            startActivity(Intent(this, PermissionActivity::class.java))


        }



        val setupBTN = findViewById<ImageView>(R.id.settings)
        setupBTN.setOnClickListener(){
            startActivity(Intent(this, SetupActivity::class.java))

        }

        val sosBTN = findViewById<RelativeLayout>(R.id.rapidCall)
        sosBTN.setOnClickListener(){
            startActivity(Intent(this, FastCallActivity::class.java))

        }





        val heartAttack= findViewById<LinearLayout>(R.id.HeartAttack)
        heartAttack.setOnClickListener(){


            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.dialog_confirmation)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                fetchLocation()
                fetchMessage()
                fetchNumbers()
                Handler().postDelayed({

                    val iterator = allNumbers.listIterator()
                    for (numberToSend in iterator) {
                        sendSMS("${numberToSend}", "HEART ATTACK ALERTT!! \n \n Please reach ASAP to the bellow location \n \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                    }
                }, 5000)
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()




        }
        val Accident= findViewById<LinearLayout>(R.id.Accident)
        Accident.setOnClickListener(){



            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.dialog_confirmation)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()


                fetchLocation()
                fetchMessage()
                fetchNumbers()
                Handler().postDelayed({

                    val iterator = allNumbers.listIterator()
                    for (numberToSend in iterator) {
                        sendSMS("${numberToSend}", "ACCIDENT ALERTT!! \n \n Please reach ASAP to the bellow location \n \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                    }
                }, 5000)
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()




        }
        val Hypertension= findViewById<LinearLayout>(R.id.Hypertension)
        Hypertension.setOnClickListener(){

            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.dialog_confirmation)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                fetchLocation()
                fetchMessage()
                fetchNumbers()

                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "Hypertension ALERTT!! \n \n Please reach ASAP to the bellow location \n \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()







        }
        val Convulsion= findViewById<LinearLayout>(R.id.Convulsion)
        Convulsion.setOnClickListener(){


            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.dialog_confirmation)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                fetchLocation()
                fetchMessage()
                fetchNumbers()
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "PLEASE HELP MEE \n \n " +
                            " Convulsion ALERTT!! \n \n Please reach ASAP to the bellow location \n \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()



        }
        val Kidnap= findViewById<LinearLayout>(R.id.Kidnap)
        Kidnap.setOnClickListener(){


            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.dialog_confirmation)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                fetchLocation()
                fetchMessage()
                fetchNumbers()
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "Kidnap ALERTT!! \n \n Please reach ASAP to the bellow location \n \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }

            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()


        }

        val Attack= findViewById<LinearLayout>(R.id.Attack)

        Attack.setOnClickListener(){

            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.dialog_confirmation)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                fetchLocation()
                fetchMessage()
                fetchNumbers()
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "I'm UNDER ATTACK!! \n \n Please reach ASAP to the bellow location \n \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()




        }

        val fastSMS= findViewById<FrameLayout>(R.id.frame_sos)
        fastSMS.setOnClickListener() {

            val dialog = Dialog(this, R.style.Dialog)
            dialog.setContentView(R.layout.dialog_confirmation)
            dialog.findViewById<View>(R.id.yesBTN).setOnClickListener {
                dialog.dismiss()

                fetchLocation()
                fetchMessage()
                fetchNumbers()
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS(
                        "${numberToSend}",
                        "${message} \n \n Please reach ASAP to the bellow location \n \n https://maps.google.com/maps?q=loc:${myPosition}  "
                    )

                }
            }
            dialog.findViewById<View>(R.id.noBTN).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()




        }

    }


    private fun fetchMessage() {
        database = FirebaseDatabase.getInstance("https://rapidsosapp-default-rtdb.europe-west1.firebasedatabase.app").getReference("users")
        database.child(uuid).get().addOnSuccessListener {

            if (it.exists()){

                 message = it.child("message").value.toString()

            }else{
                Toast.makeText(this,"Message Doesn't Exist",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


        }

}
    private fun fetchNumbers() {
        database = FirebaseDatabase.getInstance("https://rapidsosapp-default-rtdb.europe-west1.firebasedatabase.app").getReference("users/$uuid")
        database.child("phones").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (data in dataSnapshot.children) {
                    val userNumbers = data.getValue(Phone::class.java)
                    val singlePhone =userNumbers?.number
                    allNumbers.add(singlePhone.toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("taggg", "loadPost:onCancelled", databaseError.toException())
            }
        })

    }
    private fun fetchLocation()  {
        val task = fusedLocationProviderClient.lastLocation
        Log.d("taggg", "1")

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),101)
            Log.d("taggg", "333")


        }

        task.addOnSuccessListener {
            Log.d("taggg", "2")

            if(it !=null){

                val lat= it.latitude
                val long = it.longitude
                Toast.makeText(applicationContext,"position safetly sent",
                    Toast.LENGTH_LONG).show()
                myPosition="${lat},${long}"
                Log.d("poss",myPosition)
                Log.d("taggg", "2")

            }

        }

    }
    private fun sendSMS(phoneNumber: String, message: String) {
        var obj= SmsManager.getDefault();
        obj.sendTextMessage(phoneNumber, null, message,null, null)
    }

    override fun onDestroy() {
        if (myReceiver != null) {
            unregisterReceiver(myReceiver)
            myReceiver = null
        }
        super.onDestroy()
    }

}


