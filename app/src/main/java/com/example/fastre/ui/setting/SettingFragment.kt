package com.example.fastre.ui.setting

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fastre.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnChangeLanguage.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        binding.btnAboutUs.setOnClickListener {
            //val moveIntent = Intent(context, AboutUsActivity::class.java)
            //startActivity(moveIntent)
        }
        binding.btnLogout.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val dialogTitle: String  = "Batal"
        val dialogMessage: String = "Apakah anda ingin keluar dari akun ini?"

        val alertDialogBuilder = context?.let { AlertDialog.Builder(it) }
        alertDialogBuilder?.setTitle(dialogTitle)
        alertDialogBuilder
            ?.setMessage(dialogMessage)
            ?.setCancelable(false)
            ?.setPositiveButton("Ya") { _, _ ->
                //koding untuk logout
            }
            ?.setNegativeButton("Tidak") { dialog, _ -> dialog.cancel() }
        val alertDialog = alertDialogBuilder?.create()
        alertDialog?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}