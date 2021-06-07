package com.example.fastre.ui.hospital

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fastre.core.data.source.Resource
import com.example.fastre.core.domain.model.Hospital
import com.example.fastre.core.domain.model.HospitalPhoto
import com.example.fastre.core.ui.HospitalPhotoAdapter
import com.example.fastre.core.ui.PolyAdapter
import com.example.fastre.core.ui.ScheduleAdapter
import com.example.fastre.core.ui.ViewModelFactory
import com.example.fastre.databinding.FragmentHospitalBinding
import kotlin.collections.ArrayList

class HospitalFragment : Fragment() {
    private lateinit var viewModel: HospitalViewModel

    private val listPhoto: ArrayList<HospitalPhoto> = arrayListOf()

    private var _binding: FragmentHospitalBinding? = null
    private val binding get() = _binding!!

    private var name: String ?= null
    private var phoneNumber: String ?= null
    private var whatsappNumber: String ?= null
    private var longitude: Double ?= null
    private var latitude: Double ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHospitalBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[HospitalViewModel::class.java]

            val hospitalPhotoAdapter = HospitalPhotoAdapter(listPhoto)
            viewModel.hospital.observe(viewLifecycleOwner, { hospital ->
                if (hospital != null) {
                    when (hospital) {
                        is Resource.Loading -> {
                            Log.d("resource", "observe hospital: loading")
                            //binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            Log.d("resource", "observe hospital: succes")
                            //binding.progressBar.visibility = View.GONE
                            showInformation(hospital.data)
                        }
                        is Resource.Error -> {
                            Log.d("resource", "observe poly: error")
                            //binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            })
            with(binding.rvHospitalPhoto) {
                listPhoto.addAll(HospitalPhotoData.listHospitalPhoto)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = hospitalPhotoAdapter
            }

            showPoli()
            showSchedule()
        }
    }

    private fun showInformation(data: List<Hospital>?){
        if (data != null) {
            val firstHospitalData = data[0]

            name = firstHospitalData.hospitalName
            phoneNumber = firstHospitalData.hospitalPhone
            whatsappNumber = firstHospitalData.hospitalWhatsapp
            latitude = firstHospitalData.hospitalLatitude
            longitude = firstHospitalData.hospitalLongitude

            binding.tvHospitalName.text = name
        }

        binding.btnCallHospital.setOnClickListener {
            val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0$phoneNumber"))
            startActivity(dialPhoneIntent)
        }

        binding.btnLocation.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:$latitude, $longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        binding.btnWhatsapp.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=0$whatsappNumber"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    private fun showPoli(){
        val polyAdapter = PolyAdapter()
        viewModel.poly.observe(viewLifecycleOwner, { poly ->
            if (poly != null) {
                when (poly) {
                    is Resource.Loading -> {
                        Log.d("resource", "observe poly: loading")
                        //binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        Log.d("resource", "observe poly: succes")
                        //binding.progressBar.visibility = View.GONE
                        polyAdapter.setData(poly.data)
                    }
                    is Resource.Error -> {
                        Log.d("resource", "observe poly: error")
                        //binding.progressBar.visibility = View.GONE
                    }
                }
            }
        })
        with(binding.rvPoly) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = polyAdapter
        }
    }

    private fun showSchedule(){
        val scheduleAdapter = ScheduleAdapter()
        viewModel.schedule.observe(viewLifecycleOwner, { schedule ->
            if (schedule != null) {
                when (schedule) {
                    is Resource.Loading -> {
                        Log.d("resource", "observe schedule: loading")
                        //binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        Log.d("resource", "observe schedule: succes")
                        //binding.progressBar.visibility = View.GONE
                        scheduleAdapter.setData(schedule.data)
                    }
                    is Resource.Error -> {
                        Log.d("resource", "observe schedule: error")
                        //binding.progressBar.visibility = View.GONE
                    }
                }
            }
        })
        with(binding.rvSchedule) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = scheduleAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}