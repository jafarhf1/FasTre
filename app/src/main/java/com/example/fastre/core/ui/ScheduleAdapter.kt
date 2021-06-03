package com.example.fastre.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fastre.R
import com.example.fastre.core.domain.model.Schedule
import com.example.fastre.databinding.ItemScheduleBinding
import java.util.ArrayList

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ListViewHolder>() {

    private var listData = ArrayList<Schedule>()
    var onItemClick: ((Schedule) -> Unit)? = null

    fun setData(newListData: List<Schedule>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemScheduleBinding.bind(itemView)
        fun bind(data: Schedule) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.doctorPhoto)
                    .into(ivDoctorPhoto)

                tvDoctorName.text = data.doctorName

                val poliType = when (data.doctorPoly) {
                    1 -> { "Poli Ortopedi" }
                    2 -> { "Poli Penyakit Dalam" }
                    3 -> { "Poli THT" }
                    else -> { "Data tidak ditemukan" }
                }

                tvDoctorPoly.text = poliType
                tvSchedule1.text = data.doctorSchedule1
                tvSchedule2.text = data.doctorSchedule2
                tvSchedule3.text = data.doctorSchedule3
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}