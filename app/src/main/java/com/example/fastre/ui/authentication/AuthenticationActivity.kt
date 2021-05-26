package com.example.fastre.ui.authentication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.fastre.R
import com.example.fastre.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var firstTimeUser = true
    private var fileUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        supportActionBar?.hide()
        val viewPager = findViewById<ViewPager>(R.id.ViewPager)
        val pagerAdapter: AuthenticationPagerAdapter = this.AuthenticationPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragmet(LoginFragment())
        pagerAdapter.addFragmet(RegisterFragment())
        viewPager.adapter = pagerAdapter

    }
    fun buttonClicks(view: View) {
        val mButton = findViewById<Button>(R.id.button_login)

        mButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    internal inner class AuthenticationPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        private val fragmentList = ArrayList<Fragment>()
        override fun getItem(i: Int): Fragment {
            return fragmentList[i]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragmet(fragment: Fragment) {
            fragmentList.add(fragment)
        }
    }

}