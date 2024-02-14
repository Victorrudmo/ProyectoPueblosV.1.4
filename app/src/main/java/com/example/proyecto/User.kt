package com.example.proyecto

class User {
    private lateinit var user: String
    private lateinit var password: String

    fun getUser(): String {
        return user
    }

    fun setUser(user: String) {
        this.user = user
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }
}
