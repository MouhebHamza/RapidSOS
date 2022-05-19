package com.epi.rapidsosapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.epi.rapidsosapp.Phone
import com.epi.rapidsosapp.UserAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.Arrays.toString

class ViewRegisteredNumbersActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private lateinit var uuid: String
    private lateinit var auth: FirebaseAuth
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<Phone>
    private lateinit var userAdapter:UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_registred_numbers)
        /**set List*/
        userList = ArrayList()
        /**set find Id*/
        recv = findViewById(R.id.mRecycler)
        /**set Adapter*/
        userAdapter = UserAdapter(this,userList)
        /**setRecycler view Adapter*/
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter







        /**after*/
        database = FirebaseDatabase.getInstance("https://rapidsosapp-default-rtdb.europe-west1.firebasedatabase.app").getReference("users")
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                uuid = profile.uid
            }
        }
            Handler().postDelayed({
                fetchNumbers()

                Handler().postDelayed({

                    userAdapter.notifyDataSetChanged()


                }, 500)


            }, 500)



        Log.d("hhh",userList.toString() )

    }


    private fun fetchNumbers() {

        database = FirebaseDatabase.getInstance("https://rapidsosapp-default-rtdb.europe-west1.firebasedatabase.app").getReference("users/$uuid")
        database.child("phones").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (data in dataSnapshot.children) {
                    val user = data.getValue(Phone::class.java)
                    val name =user?.name
                    val phone = user?.number
                    userList.add(Phone("$name","$phone"))


                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })


    }

}

private fun <E> ArrayList<E>.add(element: String) {

}

