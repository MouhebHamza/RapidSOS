//package com.epi.rapidsosapp
////
////import android.app.Activity
////import android.content.Context
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import android.widget.*
////
////
////class ListViewAdapter(private val context: Activity, private val list: ArrayList<String>?,)
////    : ArrayAdapter<String>(context, R.layout.list_row) {
////
////
////
//////    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
//////        val inflater = context.layoutInflater
//////        val rowView = inflater.inflate(R.layout.list_row, null, true)
//////
//////        val titleText = rowView.findViewById(R.id.title) as TextView
//////        val imageView = rowView.findViewById(R.id.icon) as ImageView
//////        val subtitleText = rowView.findViewById(R.id.description) as TextView
//////
//////        titleText.text = title[position]
//////        imageView.setImageResource(imgid[position])
//////        subtitleText.text = description[position]
//////
//////        return rowView
//////    }
////
////    override fun getView(position: Int,  convertView: View?, parent: ViewGroup): View {
////        var convertView = convertView
////        if (convertView == null) {
////            val mInflater =
////                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
////            convertView = mInflater.inflate(R.layout.list_row, null)
////            val name = convertView.findViewById<TextView>(R.id.name)
////            val remove: ImageView = convertView.findViewById(R.id.remove)
////            val edit: ImageView = convertView.findViewById(R.id.edit)
////            val number = convertView.findViewById<TextView>(R.id.number)
////            number.setText(position + 1)
////            name.setText(list?.get(position))
////
////            // Listeners for duplicating and removing an item.
////            // They use the static removeItem and addItem methods created in MainActivity.
////            //remove.setOnClickListener { ViewRegisteredNumbersActivity.addItem(position) }
////            //edit.setOnClickListener { ViewRegisteredNumbersActivity.addItem(list?.get(position)) }
////
////        }
////        return convertView
////
////    }
////
////
////}
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.row.view.*
//
//class ListViewAdapter (val arrayList: ArrayList<Model>, val context: Context):
//    RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {
//
//    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
//
//        fun  bindItems(model: Model){
//            itemView.titleTv.text = model.title
//            itemView.descriptionTv.text = model.des
//            itemView.imageIv.setImageResource(model.image)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_row,parent,false)
//        return ViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindItems(arrayList[position])
//    }
//
//    override fun getItemCount(): Int {
//        return arrayList.size
//    }
//}
//
//
//
//
//
//
//
//
//
