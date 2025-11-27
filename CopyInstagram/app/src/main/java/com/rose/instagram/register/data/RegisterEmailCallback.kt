package com.rose.instagram.register.data


interface RegisterEmailCallback {
    fun onSucess()
    fun onFailure(message: String)
    fun onComplete()
}