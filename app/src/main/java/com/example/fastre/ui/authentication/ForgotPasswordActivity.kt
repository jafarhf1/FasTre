package com.example.fastre.ui.authentication

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fastre.R
import com.example.fastre.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private var _binding: ActivityForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private var emailEditText: EditText? = null
    private var resetPassword: Button? = null
    private var progessBar: ProgressBar? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailEditText = binding.etEmail
        resetPassword = binding.btnForgotPassword
        progessBar = binding.progressBar
        mAuth = FirebaseAuth.getInstance()
        progessBar!!.visibility = View.INVISIBLE
        resetPassword!!.setOnClickListener { resetPassword() }
    }

    private fun resetPassword() {
        val email = emailEditText!!.text.toString().trim { it <= ' ' }
        if (email.isEmpty()) {
            emailEditText!!.error = getString(R.string.register_email)
            emailEditText!!.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText!!.error = getString(R.string.register_wrong_email)
            emailEditText!!.requestFocus()
            return
        }
        progessBar!!.visibility = View.VISIBLE
        mAuth!!.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this@ForgotPasswordActivity,
                    getString(R.string.forgot_pw_succes),
                    Toast.LENGTH_LONG
                ).show()
                progessBar!!.visibility = View.INVISIBLE
            } else {
                Toast.makeText(
                    this@ForgotPasswordActivity,
                    getString(R.string.forgot_pw_failed),
                    Toast.LENGTH_LONG
                ).show()
                progessBar!!.visibility = View.INVISIBLE
            }
        }
    }

}