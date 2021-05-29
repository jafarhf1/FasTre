package com.example.fastre.ui.hospital

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fastre.databinding.FragmentHospitalBinding

class HospitalFragment : Fragment() {
    private var _binding: FragmentHospitalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHospitalBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showPoli()
        actionInformation()
    }

    private fun showPoli(){}

    private fun actionInformation(){

        binding.btnCallHospital.setOnClickListener {
            val phoneNumber = "0217410808"
            val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(dialPhoneIntent)
        }

        binding.btnLocation.setOnClickListener {
            val latitude = "-6.325218826876304"
            val longitude = "106.74444504486024"
            val gmmIntentUri = Uri.parse("geo:$latitude, $longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        binding.btnWhatsapp.setOnClickListener {
            val whatsappNumber = "628161913838"
            val url = "https://api.whatsapp.com/send?phone=$whatsappNumber"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

}