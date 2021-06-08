package com.example.fastre.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.fastre.R
import com.example.fastre.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(), View.OnClickListener {
    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null
    private var forgotPassword: TextView? = null
    private var mLogin: Button? = null
    private var progressBar: ProgressBar? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mAuth = FirebaseAuth.getInstance()
        mLogin = view.findViewById<View>(R.id.btn_login) as Button
        mLogin!!.setOnClickListener(this)
        editTextEmail = view.findViewById<View>(R.id.et_email) as EditText
        editTextPassword = view.findViewById<View>(R.id.et_password) as EditText
        forgotPassword = view.findViewById<View>(R.id.et_forgotPassword) as TextView
        forgotPassword!!.setOnClickListener(this)
        progressBar = view.findViewById<View>(R.id.progressBar) as ProgressBar
        progressBar!!.visibility = View.INVISIBLE
        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.text_login_title -> startActivity(Intent(context, MainActivity::class.java))
            R.id.btn_login -> userLogin()
            R.id.et_forgotPassword -> startActivity(Intent(context, ForgotPasswordActivity::class.java))
        }
    }

    private fun userLogin() {
        val email = editTextEmail!!.text.toString().trim { it <= ' ' }
        val password = editTextPassword!!.text.toString().trim { it <= ' ' }
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
            editTextPassword!!.error = getString(R.string.register_password)
            editTextPassword!!.requestFocus()
            return
        }
        if (password.length < 6) {
            editTextPassword!!.error = getString(R.string.register_wrong_password)
            editTextPassword!!.requestFocus()
            return
        }
        progressBar!!.visibility = View.VISIBLE
        mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //redirect to user profile
                        val user = FirebaseAuth.getInstance().currentUser
                        if (user!!.isEmailVerified) {
                            Toast.makeText(context, getString(R.string.login_success), Toast.LENGTH_LONG).show()
                            startActivity(Intent(context, MainActivity::class.java))
                            requireActivity().finish()
                        } else {
                            Toast.makeText(context, getString(R.string.login_not_verified), Toast.LENGTH_LONG).show()
                            progressBar!!.visibility = View.GONE
                        }
                    } else {
                        Toast.makeText(context, getString(R.string.login_failed), Toast.LENGTH_LONG).show()
                        progressBar!!.visibility = View.GONE
                    }
                }
    }
}