package com.example.fastre.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fastre.core.data.source.remote.network.ApiResponse
import com.example.fastre.core.data.source.remote.network.ApiService
import com.example.fastre.core.data.source.remote.response.hospital.HospitalResponse
import com.example.fastre.core.data.source.remote.response.hospital.ListHospitalResponse
import com.example.fastre.core.data.source.remote.response.medicalRecords.ListMedicalRecordsResponse
import com.example.fastre.core.data.source.remote.response.medicalRecords.MedicalRecordsResponse
import com.example.fastre.core.data.source.remote.response.news.ListNewsResponse
import com.example.fastre.core.data.source.remote.response.news.NewsResponse
import com.example.fastre.core.data.source.remote.response.polyclinic.ListPolyResponse
import com.example.fastre.core.data.source.remote.response.polyclinic.PolyclinicResponse
import com.example.fastre.core.data.source.remote.response.queue.QueueResponse
import com.example.fastre.core.data.source.remote.response.schedule.ListScheduleResponse
import com.example.fastre.core.data.source.remote.response.schedule.ScheduleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllNews(): LiveData<ApiResponse<List<NewsResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<NewsResponse>>>()

        val client = apiService.getList()
        client.enqueue(object : Callback<ListNewsResponse> {
            override fun onResponse(call: Call<ListNewsResponse>, response: Response<ListNewsResponse>) {
                val dataArray = response.body()?.news
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }
            override fun onFailure(call: Call<ListNewsResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })
        return resultData
    }

    fun getAllHospital(): LiveData<ApiResponse<List<HospitalResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<HospitalResponse>>>()

        val client = apiService.getHospitalList()
        client.enqueue(object : Callback<ListHospitalResponse> {
            override fun onResponse(
                call: Call<ListHospitalResponse>,
                response: Response<ListHospitalResponse>
            ) {
                val dataArray = response.body()?.hospital
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }
            override fun onFailure(call: Call<ListHospitalResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

    fun getAllMedicalRecords(): LiveData<ApiResponse<List<MedicalRecordsResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<MedicalRecordsResponse>>>()

        val client = apiService.getMedicalRecordList()
        client.enqueue(object : Callback<ListMedicalRecordsResponse> {
            override fun onResponse(
                    call: Call<ListMedicalRecordsResponse>,
                    response: Response<ListMedicalRecordsResponse>
            ) {
                val dataArray = response.body()?.medicalRecords
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }
            override fun onFailure(call: Call<ListMedicalRecordsResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

    fun getAllSchedule(): LiveData<ApiResponse<List<ScheduleResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<ScheduleResponse>>>()

        val client = apiService.getSchedule()
        client.enqueue(object : Callback<ListScheduleResponse> {
            override fun onResponse(
                call: Call<ListScheduleResponse>,
                response: Response<ListScheduleResponse>
            ) {
                val dataArray = response.body()?.schedule
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }
            override fun onFailure(call: Call<ListScheduleResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

    fun getAllPoly(): LiveData<ApiResponse<List<PolyclinicResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<PolyclinicResponse>>>()

        val client = apiService.getPoly()
        client.enqueue(object : Callback<ListPolyResponse> {
            override fun onResponse(
                call: Call<ListPolyResponse>,
                response: Response<ListPolyResponse>
            ) {
                val dataArray = response.body()?.poly
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }
            override fun onFailure(call: Call<ListPolyResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

}