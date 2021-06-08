package com.example.fastre.ui.hospital

import androidx.lifecycle.ViewModel
import com.example.fastre.core.domain.usecase.MyUseCase

class HospitalViewModel(useCase: MyUseCase): ViewModel() {
    val hospital = useCase.getAllHospital()
    val schedule = useCase.getAllSchedule()
    val poly = useCase.getAllPoly()
}