package com.example.fastre.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fastre.R
<<<<<<< HEAD
import com.example.fastre.core.data.source.remote.network.ApiConfig
import com.example.fastre.core.data.source.remote.response.queue.QueueResponse
import com.example.fastre.core.domain.model.Poly
import com.example.fastre.databinding.ItemPolyBinding
import com.example.fastre.ui.hospital.HospitalFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
=======
import com.example.fastre.core.data.source.remote.RemoteDataSource
import com.example.fastre.core.data.source.remote.response.queue.QueueResponse
import com.example.fastre.core.domain.model.Poly
import com.example.fastre.databinding.ItemPolyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42
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
<<<<<<< HEAD
                val dateView = 1234
=======
                val dateView = dateFormat.format(date)
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42

                val calendar: Calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR)
                val minute = calendar.get(Calendar.MINUTE)

                btnGetQueue.setOnClickListener {
<<<<<<< HEAD
                    ApiConfig.instance.setQueueData(
                        dateView, userID, hour, minute
                    ).enqueue(object: Callback<QueueResponse> {
                        override fun onResponse(call: Call<QueueResponse>, response: Response<QueueResponse>) {
                            val responseText = "response data: ${response.code()}\n " +
                                        "date: ${response.body()?.queueDate}" +
                                        "userId: ${response.body()?.queueId}" +
                                        //"polyId: ${response.body()?.queuePolyId}" +
                                        "hour: ${response.body()?.queueHour}" +
                                        "minute: ${response.body()?.queueMinute}"
                            //val respon = "berhasill"
                            //textUserQueue.text = respon
                            //Log.d("tes", response.code().toString())

                            if(response.isSuccessful) {
                                Log.d("PostActivity", "post submitted to API." + response.body().toString());
                            } else {
                                Log.d("PostActivity", "post submitted to API." + response.body().toString());
                            }
                        }

                        override fun onFailure(call: Call<QueueResponse>, t: Throwable) {
                            tvPolyName.text = t.message
                        }

                    })
=======
                    setDataUser(dateView, hour, minute)
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42
                }
            }
        }

<<<<<<< HEAD
=======
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

>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}