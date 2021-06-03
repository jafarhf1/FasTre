package com.example.fastre.core.utils

import com.example.fastre.core.data.source.local.entity.HospitalEntity
import com.example.fastre.core.data.source.remote.response.hospital.HospitalResponse
import com.example.fastre.core.domain.model.Hospital

object HospitalDataMapper {
    fun mapResponsesToEntities(input: List<HospitalResponse>): List<HospitalEntity> {
        val hospitalList = ArrayList<HospitalEntity>()
        input.map {
            val hospital = HospitalEntity(
                hospitalId = it.hospitalId,
                hospitalName = it.hospitalName,
                hospitalPhone = it.hospitalPhone,
                hospitalWhatsapp = it.hospitalWhatsapp,
                hospitalLongitude = it.longitude,
                hospitalLatitude = it.latitude,
                hospitalPhoto1 = it.hospitalPhoto1,
                hospitalPhoto2 = it.hospitalPhoto2,
                hospitalPhoto3 = it.hospitalPhoto3
            )
            hospitalList.add(hospital)
        }
        return hospitalList
    }

    fun mapEntitiesToDomain(input: List<HospitalEntity>): List<Hospital> =
        input.map {
            Hospital(
                hospitalId = it.hospitalId,
                hospitalName = it.hospitalName,
                hospitalPhone = it.hospitalPhone,
                hospitalWhatsapp = it.hospitalWhatsapp,
                hospitalLongitude = it.hospitalLongitude,
                hospitalLatitude = it.hospitalLatitude,
                hospitalPhoto1 = it.hospitalPhoto1,
                hospitalPhoto2 = it.hospitalPhoto2,
                hospitalPhoto3 = it.hospitalPhoto3,
            )
        }
}