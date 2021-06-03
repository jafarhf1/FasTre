package com.example.fastre.core.utils

import com.example.fastre.core.data.source.local.entity.PolyEntity
import com.example.fastre.core.data.source.remote.response.polyclinic.PolyclinicResponse
import com.example.fastre.core.domain.model.Poly

object PolyDataMapper {
    fun mapResponsesToEntities(input: List<PolyclinicResponse>): List<PolyEntity> {
        val list = ArrayList<PolyEntity>()
        input.map {
            val poly = PolyEntity(
                polyId = it.polyId,
                polyName = it.polyName,
              )
            list.add(poly)
        }
        return list
    }

    fun mapEntitiesToDomain(input: List<PolyEntity>): List<Poly> =
        input.map {
            Poly(
                polyId = it.polyId,
                polyName = it.polyName,
            )
        }
}