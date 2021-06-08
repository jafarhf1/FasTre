package com.example.fastre.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fastre.R
import com.example.fastre.core.data.source.remote.network.ApiConfig
import com.example.fastre.core.data.source.remote.request.QueueRequest
import com.example.fastre.core.data.source.remote.response.queue.QueueResponse
import com.example.fastre.core.domain.model.Poly
import com.example.fastre.databinding.ItemPolyBinding
import com.example.fastre.ui.authentication.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PolyAdapter: RecyclerView.Adapter<PolyAdapter.ListViewHolder>() {

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

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPolyBinding.bind(itemView)
        fun bind(data: Poly) {
            with(binding) {
                tvPolyName.text = data.polyName
                tvQueueNumber.text = data.currentNumber.toString()

                val calendar = Calendar.getInstance()
                val scheduledDay = calendar.get(Calendar.DAY_OF_WEEK) //ini nanti diganti jadi jadwal praktek
                val scheduledHour = calendar.get(Calendar.HOUR) //ini nanti diganti jadi jadwal praktek
                val scheduledMinute = calendar.get(Calendar.MINUTE) //ini nanti diganti jadi jadwal praktek

                btnGetUserNumber.setOnClickListener {
                    val queueRequest = QueueRequest(
                        queueUserId = User.userID,
                        queueDay = 2,
                        queueHour = scheduledHour,
                        queueMinute = scheduledMinute
                    )

                    ApiConfig.instance.setQueue(data.polyId, queueRequest).enqueue(object: Callback<QueueResponse>{
                        override fun onResponse(call: Call<QueueResponse>, response: Response<QueueResponse>) {
                            val responseQueueData = response.body()
                            Log.d("Post data API success", "QueueId: "+ responseQueueData?.queueId.toString() )

                            if (responseQueueData?.queueId != null){
                                val text = "Your queue number"
                                textUserNumber.text = text
                                tvUserNumber.text = responseQueueData.queueUserNumber.toString()

                                val estimatedTimeInInteger = responseQueueData.estimatedTime.toInt()
                                tvEstimatedTime.text = estimatedTimeInInteger.toString()
                            } else {
                                val text = "This poly doesn't have a scheduled today"
                                val number = "-"
                                val time = "0"
                                textUserNumber.text = text
                                tvUserNumber.text = number
                                tvEstimatedTime.text = time
                            }
                        }

                        override fun onFailure(call: Call<QueueResponse>, t: Throwable) {
                            Log.e("Post data API failed", t.message.toString() )
                        }
                    })

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