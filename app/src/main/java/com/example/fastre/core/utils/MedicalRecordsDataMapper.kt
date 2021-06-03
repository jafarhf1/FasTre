package com.example.fastre.core.utils

import com.example.fastre.core.data.source.local.entity.MedicalRecordsEntity
import com.example.fastre.core.data.source.remote.response.medicalRecords.MedicalRecordsResponse
import com.example.fastre.core.domain.model.MedicalRecords

object MedicalRecordsDataMapper {    fun mapResponsesToEntities(input: List<MedicalRecordsResponse>): List<MedicalRecordsEntity> {
    val medicalRecordList = ArrayList<MedicalRecordsEntity>()
    input.map {
        val medicalRecord = MedicalRecordsEntity(
                medicalRecordId = it.medicalRecordId,
                medicalRecordPoli = it.medicalRecordPoli,
                date = it.date

        )
        medicalRecordList.add(medicalRecord)
    }
    return medicalRecordList
}

    fun mapEntitiesToDomain(input: List<MedicalRecordsEntity>): List<MedicalRecords> =
            input.map {
                MedicalRecords(
                        medicalRecordId = it.medicalRecordId,
                        medicalRecordPoli = it.medicalRecordPoli,
                        date = it.date
                )
            }
}