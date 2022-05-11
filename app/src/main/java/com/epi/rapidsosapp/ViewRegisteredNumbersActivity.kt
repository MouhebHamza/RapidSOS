//package com.epi.rapidsosapp
//
//import android.content.Context
//import android.os.Bundle
//import android.widget.ArrayAdapter
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.ListView
//import androidx.appcompat.app.AppCompatActivity
//import java.io.File
//import java.io.FileInputStream
//import java.io.FileOutputStream
//import java.util.*
//import kotlin.collections.ArrayList
//
//
//class ViewRegisteredNumbersActivity : AppCompatActivity() {
//    var listView: ListView? = null
//    var input: EditText? = null
//    var enter: ImageView? = null
//    var adapter: ListViewAdapter? = null
//    var  items: ArrayList<String>? = null
//    var context: Context? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_view_registred_numbers)
//        val listView = findViewById<ListView>(R.id.listView)
//        val list = arrayOf<String>("Ajay","Prakesh","Michel","John","Sumit")
//        val items = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
//        listView.adapter=items
//    }
//    private fun loadContent() {
//        val path: File = applicationContext.filesDir
//        val readFrom = File(path, "list.txt")
//        val content = ByteArray(readFrom.length() as Int)
//        var stream: FileInputStream? = null
//        try {
//            stream = FileInputStream(readFrom)
//            stream.read(content)
//            var s = String(content)
//            // [Apple, Banana, Kiwi, Strawberry]
//            s = s.substring(1, s.length - 1)
//            val split = s.split(", ").toTypedArray()
//
//            adapter = ListViewAdapter(this, items)
//            listView?.setAdapter(adapter)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun onDestroy() {
//        val path: File = applicationContext.filesDir
//        try {
//            val writer = FileOutputStream(File(path, "list.txt"))
//            writer.write(items.toString().toByteArray())
//            writer.close()
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
//        super.onDestroy()
//    }
//
//        public fun addItem(item: String?) {
//            if (item != null) {
//                items?.add(item)
//            }
//            listView?.setAdapter(adapter)
//        }
//
//
//}