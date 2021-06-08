package com.example.fastre.core.utils

import com.example.fastre.core.data.source.local.entity.ScheduleEntity
import com.example.fastre.core.data.source.remote.response.schedule.ScheduleResponse
import com.example.fastre.core.domain.model.Schedule

object ScheduleDataMapper {
    fun mapResponsesToEntities(input: List<ScheduleResponse>): List<ScheduleEntity> {
        val list = ArrayList<ScheduleEntity>()
        input.map {
            val schedule = ScheduleEntity(
                doctorId = it.doctorId,
                doctorName = it.doctorName,
                doctorPoly = it.doctorPoly,
                doctorPhoto = it.doctorPhoto,
                doctorSchedule1 = it.doctorSchedule1,
                doctorSchedule2 = it.doctorSchedule2,
                doctorSchedule3 = it.doctorSchedule3,
            )
            list.add(schedule)
        }
        return list
    }

    fun mapEntitiesToDomain(input: List<ScheduleEntity>): List<Schedule> =
        input.map {
            Schedule(
                doctorId = it.doctorId,
                doctorName = it.doctorName,
                doctorPoly = it.doctorPoly,
                doctorPhoto = it.doctorPhoto,
                doctorSchedule1 = it.doctorSchedule1,
                doctorSchedule2 = it.doctorSchedule2,
                doctorSchedule3 = it.doctorSchedule3,
            )
        }
}