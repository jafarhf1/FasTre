package com.example.fastre.ui.medicalRecords

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.fastre.R

class MedicalRecordsAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var users = arrayListOf<MedicalRecords>()

    override fun getCount(): Int = users.size

    override fun getItem(position: Int): Any = users[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_medical_records, parent, false)
        }
        val viewHolder = ViewHolder(itemView as View)

        val user = getItem(position) as MedicalRecords
        viewHolder.bind(user)
        return itemView
    }


    private inner class ViewHolder constructor(view: View) {
        //private val tvName: TextView = view.findViewById(R.id.id)
        //private val tvPoli: TextView = view.findViewById(R.id.poli)
        //private val tvDate: TextView = view.findViewById(R.id.date)

        fun bind(user: MedicalRecords) {
            //tvName.inputType = user.medicalRecordId
            //tvPoli.text = user.medicalRecordPoli
            //tvDate.inputType = user.date
        }
    }


}