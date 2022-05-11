package com.epi.rapidsosapp

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
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
                    sendSMS("${numberToSend}", "${message} \n Please reach ASAP to the bellow location \n https://maps.google.com/maps?q=loc:${myPosition}  ")

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
            fetchLocation()
            fetchMessage()
            fetchNumbers()
            Handler().postDelayed({
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "HEART ATTACK ALERTT!! \n Please reach ASAP to the bellow location \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }

            }, 5000)
        }
        val Accident= findViewById<LinearLayout>(R.id.Accident)
        Accident.setOnClickListener(){
            fetchLocation()
            fetchMessage()
            fetchNumbers()
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "ACCIDENT ALERTT!! \n Please reach ASAP to the bellow location \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }

        }
        val Hypertension= findViewById<LinearLayout>(R.id.Hypertension)
        Hypertension.setOnClickListener(){
            fetchLocation()
            fetchMessage()
            fetchNumbers()
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "Hypertension ALERTT!! \n Please reach ASAP to the bellow location \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }

        }
        val Convulsion= findViewById<LinearLayout>(R.id.Convulsion)
        Convulsion.setOnClickListener(){
            fetchLocation()
            fetchMessage()
            fetchNumbers()
            Handler().postDelayed({
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "PLEASE HELP MEE \n" +
                            " Convulsion ALERTT!! \n Please reach ASAP to the bellow location \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }

            }, 5000)
        }
        val Kidnap= findViewById<LinearLayout>(R.id.Kidnap)
        Kidnap.setOnClickListener(){
            fetchLocation()
            fetchMessage()
            fetchNumbers()
            Handler().postDelayed({
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "Kidnap ALERTT!! \n Please reach ASAP to the bellow location \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }

            }, 5000)
        }

        val Attack= findViewById<LinearLayout>(R.id.Attack)
        Attack.setOnClickListener(){
            fetchLocation()
            fetchMessage()
            fetchNumbers()
            Handler().postDelayed({
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS("${numberToSend}", "I'm UNDER ATTACK!! \n Please reach ASAP to the bellow location \n https://maps.google.com/maps?q=loc:${myPosition}  ")

                }

            }, 5000)
        }

        val fastSMS= findViewById<FrameLayout>(R.id.frame_sos)
        fastSMS.setOnClickListener() {
            fetchLocation()
            fetchMessage()
            fetchNumbers()
            Handler().postDelayed({
                val iterator = allNumbers.listIterator()
                for (numberToSend in iterator) {
                    sendSMS(
                        "${numberToSend}",
                        "${message} \n Please reach ASAP to the bellow location \n https://maps.google.com/maps?q=loc:${myPosition}  "
                    )

                }

            }, 5000)


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
                    val singlePhone= data.key
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),101)


        }

        task.addOnSuccessListener {
            if(it !=null){
                val geocoder: Geocoder
                var addresses: List<Address?>
                geocoder = Geocoder(this, Locale.getDefault())
                addresses = geocoder.getFromLocation(
                    it.latitude,
                    it.longitude,
                    1
                )

                val lat= it.latitude
                val long = it.longitude
                Toast.makeText(applicationContext,"position safetly sent",
                    Toast.LENGTH_LONG).show()
                myPosition="${lat},${long}"
                Log.d("poss",myPosition)

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


