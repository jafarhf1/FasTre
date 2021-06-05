package com.example.fastre.ui.authentication

class User {
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