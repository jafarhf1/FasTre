package com.example.fastre.core.utils

import com.example.fastre.core.data.source.local.entity.MedicalRecordsEntity
import com.example.fastre.core.data.source.remote.response.medicalRecords.MedicalRecordsResponse
import com.example.fastre.core.domain.model.MedicalRecords
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

object MedicalRecordsDataMapper {
    fun mapResponsesToEntities(input: List<MedicalRecordsResponse>): List<MedicalRecordsEntity> {
        val list = ArrayList<MedicalRecordsEntity>()
        input.map {
            val dateInMillis = it.medicalDate._seconds * 1000
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            val dateInString = simpleDateFormat.format(dateInMillis)

            val poliType = when (it.medicalPolyId) {
                1 -> { "Poli Ortopedi" }
                2 -> { "Poli Penyakit Dalam" }
                3 -> { "Poli THT" }
                else -> { "Data tidak ditemukan" }
            }

            val medicalRecord = MedicalRecordsEntity(
                medicalDate = dateInString,
                medicalPolyId = poliType,
                medicalUserNumber = it.medicalUserNumber,
                medicalQueueId = it.medicalQueueId
            )
            list.add(medicalRecord)
        }
        return list
    }

    fun mapEntitiesToDomain(input: List<MedicalRecordsEntity>): List<MedicalRecords> =
        input.map {
            MedicalRecords(
                medicalDate = it.medicalDate,
                medicalPolyId = it.medicalPolyId,
                medicalUserNumber = it.medicalUserNumber,
                medicalQueueId = it.medicalQueueId
            )
        }
}