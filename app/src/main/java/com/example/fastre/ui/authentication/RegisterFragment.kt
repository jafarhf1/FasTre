package com.example.fastre.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fastre.R
import com.example.fastre.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment(), View.OnClickListener {
    private var editTextFullName: EditText? = null
    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null
    private var editTextAge: EditText? = null
    private var editTextBloodType: EditText? = null
    private var mRegister: Button? = null
    private var progressBar: ProgressBar? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        mAuth = FirebaseAuth.getInstance()
        mRegister = view.findViewById<View>(R.id.btn_register) as Button
        mRegister!!.setOnClickListener(this)
        editTextFullName = view.findViewById<View>(R.id.et_name) as EditText
        editTextEmail = view.findViewById<View>(R.id.et_email) as EditText
        editTextPassword = view.findViewById<View>(R.id.et_password) as EditText
        editTextAge = view.findViewById<View>(R.id.et_age) as EditText
        editTextBloodType = view.findViewById<View>(R.id.et_bloodType) as EditText
        progressBar = view.findViewById<View>(R.id.progressBar) as ProgressBar
        progressBar!!.visibility = View.INVISIBLE
        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.text_register_title -> startActivity(Intent(context, MainActivity::class.java))
            R.id.btn_register -> registerUser()
        }
    }

    private fun registerUser() {
        val email = editTextEmail!!.text.toString().trim { it <= ' ' }
        val password = editTextPassword!!.text.toString().trim { it <= ' ' }
        val fullName = editTextFullName!!.text.toString().trim { it <= ' ' }
        val bloodType = editTextBloodType!!.text.toString().trim { it <= ' ' }
        val age = editTextAge!!.text.toString().trim { it <= ' ' }
        if (fullName.isEmpty()) {
            editTextFullName!!.error = getString(R.string.register_name)
            editTextFullName!!.requestFocus()
            return
        }
        if (age.isEmpty()) {
            editTextAge!!.error = getString(R.string.register_age)
            editTextAge!!.requestFocus()
            return
        }
        if (bloodType.isEmpty()) {
            editTextBloodType!!.error = getString(R.string.blood_type)
            editTextBloodType!!.requestFocus()
            return
        }
        if (email.isEmpty()) {
            editTextEmail!!.error = getString(R.string.register_email)
            editTextEmail!!.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail!!.error = getString(R.string.register_wrong_email)
            editTextEmail!!.requestFocus()
            return
        }
        if (password.isEmpty()) {
            editTextPassword!!.error =getString(R.string.register_password)
            editTextPassword!!.requestFocus()
            return
        }
        if (password.length < 6) {
            editTextPassword!!.error = getString(R.string.register_wrong_password)
            editTextPassword!!.requestFocus()
            return
        }
        progressBar!!.visibility = View.VISIBLE
        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = User(fullName, age, bloodType, email)
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                                .setValue(user).addOnCompleteListener { task ->
                                    val user = FirebaseAuth.getInstance().currentUser
                                    if (task.isSuccessful) {
                                        user!!.sendEmailVerification()
                                        Toast.makeText(context, getString(R.string.register_success), Toast.LENGTH_LONG).show()
                                        progressBar!!.visibility = View.GONE
                                    } else {
                                        Toast.makeText(context, getString(R.string.register_failed), Toast.LENGTH_LONG).show()
                                        progressBar!!.visibility = View.GONE
                                    }
                                }
                    } else {
                        Toast.makeText(context, getString(R.string.register_failed), Toast.LENGTH_LONG).show()
                        progressBar!!.visibility = View.GONE
                    }
                }
    }
}