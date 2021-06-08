package com.example.fastre.ui.authentication

import com.google.firebase.auth.FirebaseAuth

class User {

    companion object{
        val user = FirebaseAuth.getInstance().currentUser!!
        val userID = user.uid
    }

    var fullName: String? = null
    var age: String? = null
    var bloodType: String? = null
    var email: String? = null

    constructor(
        fullName: String?,
        age: String?,
        bloodType: String?,
        email: String?
    ){
        this.fullName = fullName
        this.age = age
        this.bloodType = bloodType
        this.email = email
    }

    constructor()
}