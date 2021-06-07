package com.example.fastre.core.data.source.remote.network

import com.example.fastre.core.data.source.remote.request.QueueRequest
import com.example.fastre.core.data.source.remote.response.hospital.ListHospitalResponse
import com.example.fastre.core.data.source.remote.response.medicalRecords.ListMedicalRecordsResponse
import com.example.fastre.core.data.source.remote.response.news.ListNewsResponse
import com.example.fastre.core.data.source.remote.response.polyclinic.ListPolyResponse
import com.example.fastre.core.data.source.remote.response.queue.QueueResponse
import com.example.fastre.core.data.source.remote.response.schedule.ListScheduleResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("posts")
    fun getList(): Call<ListNewsResponse>

    @GET("hospitals")
    fun getHospitalList(): Call<ListHospitalResponse>

    @GET("hospitals/1/schedules")
    fun getSchedule(): Call<ListScheduleResponse>

    @GET("hospitals/1/polyclinics")
    fun getPoly(): Call<ListPolyResponse>

    @POST("hospitals/1/polyclinics/{polyId}/queues")
    fun setQueue(
        @Path("polyId") polyIdData: Int?,
        @Body queueRequest: QueueRequest
    ): Call<QueueResponse>

    @GET("user/{userId}/queues")
    fun getMedicalRecordList(
        @Path("userId") userIdData: String?
    ): Call<ListMedicalRecordsResponse>

}