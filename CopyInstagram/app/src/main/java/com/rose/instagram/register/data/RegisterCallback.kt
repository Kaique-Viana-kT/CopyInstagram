package com.rose.instagram.register.data


interface RegisterCallback {
    fun onSucess()
    fun onFailure(message: String)
    fun onComplete()
}