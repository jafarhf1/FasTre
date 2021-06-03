package com.example.fastre.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fastre.R
import com.example.fastre.core.data.source.remote.RemoteDataSource
import com.example.fastre.core.data.source.remote.response.queue.QueueResponse
import com.example.fastre.core.domain.model.Poly
import com.example.fastre.databinding.ItemPolyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PolyAdapter: RecyclerView.Adapter<PolyAdapter.ListViewHolder>() {
    private lateinit var user: FirebaseUser
    private lateinit var userID: String

    private var listData = ArrayList<Poly>()
    var onItemClick: ((Poly) -> Unit)? = null

    fun setData(newListData: List<Poly>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_poly, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPolyBinding.bind(itemView)
        fun bind(data: Poly) {
            with(binding) {
                tvPolyName.text = data.polyName

                user = FirebaseAuth.getInstance().currentUser!!
                userID = user.uid

                val dateFormat: DateFormat = SimpleDateFormat("MM/dd/YY")
                val date = Date()
                val dateView = dateFormat.format(date)

                val calendar: Calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR)
                val minute = calendar.get(Calendar.MINUTE)

                btnGetQueue.setOnClickListener {
                    setDataUser(dateView, hour, minute)
                }
            }
        }

        private fun setDataUser(dateView: String, hour: Int, minute: Int) {
            val apiService: RemoteDataSource? = null
            //val apiService = RemoteDataSource(apiService)
            val userData = QueueResponse(
                queueDate = dateView,
                queueId = userID,
                queueHour = hour,
                queueMinute = minute
            )

            apiService?.setQueueData(userData){
                if (it?.queueId != null) {
                    Log.d("cek poly adapter", "berhasil")
                } else {
                    Log.d("cek poly adapter","Error registering new user")
                }
            }

        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}