@file:Suppress("DEPRECATION")

package com.example.fastre.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fastre.databinding.FragmentProfileBinding
import com.example.fastre.ui.authentication.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var user: FirebaseUser
    private lateinit var reference: DatabaseReference
    private lateinit var userID: String
    private val defaultImageUrl = "https://picsum.photos/200"
    private lateinit var imageUri : Uri

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 100
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Users")
        userID = user.uid

        val progressBar : ProgressBar = binding.progressBar
        val fullNameTextView: TextView = binding.tvName
        val emailTextView: TextView = binding.hintEmail
        val ageTextView: TextView = binding.hintAge
        val bloodTypeTextView: TextView = binding.hintBloodType

        reference.child(userID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(
                    User::class.java
                )
                progressBar.visibility = View.VISIBLE
                if (userProfile != null) {
                    val fullName = userProfile.fullName
                    val email = userProfile.email
                    val age = userProfile.age
                    val bloodType = userProfile.bloodType
                    fullNameTextView.text = fullName
                    emailTextView.text = email
                    ageTextView.text = age
                    bloodTypeTextView.text = bloodType
                    progressBar.visibility = View.GONE
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Something wrong Happened!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user.let { user->
            Glide.with(this)
                .load(user.photoUrl)
                .into(binding.imgProfile)
        }

        binding.imgProfile.setOnClickListener{
            takePictureIntent()
        }

        binding.btnUpdate.setOnClickListener{
            val photo = when{
                ::imageUri.isInitialized -> imageUri
                user.photoUrl == null -> Uri.parse(defaultImageUrl)
                else -> user.photoUrl
            }

            val updates = UserProfileChangeRequest.Builder()
                .setPhotoUri(photo)
                .build()

            user.updateProfile(updates)
                .addOnCompleteListener { task->
                    if(task.isSuccessful){
                        Toast.makeText(context, "Profile Picture Saved", Toast.LENGTH_SHORT)
                            .show()
                    }else{
                        Toast.makeText(context, "Something wrong Happened!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }

    private fun takePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            activity?.packageManager?.let {
                intent.resolveActivity(it).also {
                    this.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_CAPTURE ){
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }
    }


    private fun uploadImage(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("img/${FirebaseAuth.getInstance().currentUser?.uid}")

        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()

        ref.putBytes(image)
            .addOnCompleteListener{ it ->
                if (it.isSuccessful){
                    ref.downloadUrl.addOnCompleteListener{ it1 ->
                        it1.result.let{
                            if (it != null) {
                                imageUri = it
                            }
                            val imageView: ImageView = binding.imgProfile
                            imageView.setImageBitmap(imgBitmap)
                            Toast.makeText(context, "Profile Upload is Succesful", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }else{
                    Toast.makeText(context, "Something wrong Happened!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

    }
}