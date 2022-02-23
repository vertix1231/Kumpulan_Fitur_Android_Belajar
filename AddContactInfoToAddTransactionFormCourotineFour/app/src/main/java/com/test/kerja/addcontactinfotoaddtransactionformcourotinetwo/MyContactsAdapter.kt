package com.test.kerja.addcontactinfotoaddtransactionformcourotinetwo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class MyContactsAdapter : ListAdapter<MyContact, ViewHolder>(DiffCallback()) {
    private var mListenerContact:ItemContactClickListener? = null
    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.contact_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemlyout.setOnClickListener{
//            mListenerContact?.onItemContactClick(getItem(position))
            try {
                mListenerContact?.onItemContactClick(getItem(position))
            }catch (e:Exception){
//                Toast.makeText(holder.itemlyout.context,"nama : ${getItem(position).name} \n nomor : ${getItem(position).number}",Toast.LENGTH_LONG).show()
                Log.d("tag_error","\"nama : ${getItem(position).name} \\n nomor : ${getItem(position).number} dengan tipe error ${e.message}")
            }
            Toast.makeText(holder.itemlyout.context,"nama : ${getItem(position).name} \n nomor : ${getItem(position).number}",Toast.LENGTH_LONG).show()
            Log.d("tag","data terpilih nama : ${getItem(position).name} nomor : ${getItem(position).number}")
        }
    }
    fun setItemContactClickListener(itemContactClickListener: ItemContactClickListener){
        this.mListenerContact = itemContactClickListener
    }
}


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//    private var mListenerContact:ItemContactClickListener? = null
//    private val tvImage: ImageView = itemView.findViewById(R.id.ivCharacter)
    private val tvName: TextView = itemView.findViewById(R.id.tvName)
    private val tvNumber: TextView = itemView.findViewById(R.id.tvNumber)
    val itemlyout:ConstraintLayout = itemView.findViewById(R.id.rlayout)
    fun bind(item: MyContact) {
        tvName.text = item.name
        tvNumber.text = item.number
//        Glide.with(tvImage)
//            .load(item.image)
//            .into(tvImage)

//        itemlyout.setOnClickListener {
//            try {
////                Toast.makeText(itemView.context,"nama : ${item} \n nomor : ${item}",Toast.LENGTH_LONG).show()
//
//                Toast.makeText(itemView.context,"nama : ${item.name} \n nomor : ${item.number}",Toast.LENGTH_LONG).show()
//                mListenerContact?.onItemContactClick(item)
////                Toast.makeText(itemView.context,"nama : ${item.name} \n nomor : ${item.number}",Toast.LENGTH_LONG).show()
//            }catch (e:Exception){
//                Toast.makeText(itemView.context,e.toString(),Toast.LENGTH_LONG).show()
//            }
////            mListenerContact!!.onItemContactClick(item)
//
//
////            val intent = Intent(itemView.context, DetailContactActivity::class.java).apply {
////                putExtra(DetailContactActivity.IN_USERNAME,item.name)
////                putExtra(DetailContactActivity.IN_NUMBER,item.number)
////            }
////            itemView.context.startActivity(intent)
//
//
////            Toast.makeText(itemView.context,"nama : ${item.name} \n nomor : ${item.number}",Toast.LENGTH_LONG).show()
//        }


    }

}

class DiffCallback : DiffUtil.ItemCallback<MyContact>() {
    override fun areItemsTheSame(oldItem: MyContact, newItem: MyContact): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: MyContact, newItem: MyContact): Boolean {
        return oldItem == newItem
    }
}