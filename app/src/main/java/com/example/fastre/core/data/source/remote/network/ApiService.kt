package com.example.fastre.core.data.source.remote.network

import com.example.fastre.core.data.source.remote.response.hospital.ListHospitalResponse
import com.example.fastre.core.data.source.remote.response.medicalRecords.ListMedicalRecordsResponse
import com.example.fastre.core.data.source.remote.response.news.ListNewsResponse
import com.example.fastre.core.data.source.remote.response.polyclinic.ListPolyResponse
import com.example.fastre.core.data.source.remote.response.queue.QueueResponse
import com.example.fastre.core.data.source.remote.response.schedule.ListScheduleResponse
import retrofit2.Call
<<<<<<< HEAD
import retrofit2.http.*
=======
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42

interface ApiService {
    @GET("posts")
    fun getList(): Call<ListNewsResponse>

    @GET("hospitals")
    fun getHospitalList(): Call<ListHospitalResponse>

    @GET("medical_records")
    fun getMedicalRecordList(): Call<ListMedicalRecordsResponse>

    @GET("hospitals/1/schedules")
    fun getSchedule(): Call<ListScheduleResponse>

    @GET("hospitals/1/polyclinics")
    fun getPoly(): Call<ListPolyResponse>

<<<<<<< HEAD

    @POST("hospitals/1/polyclinics/1/queues/")
    @FormUrlEncoded
    fun setQueueData(
        @Field("date") queueDate: Int,
        @Field("userId") queueId: String,
        //@Field("userPolyId") queuePolyId: Int,
        @Field("scheduleHour") queueHour: Int,
        @Field("scheduleMinute") queueMinute: Int
    ): Call<QueueResponse>
=======
    @POST("hospitals/1/polyclinics/:polyId/queues/")
    fun setQueueData(@Body data: QueueResponse): Call<QueueResponse>
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42
}