package com.rose.instagram.register.view

interface FragmentAttachListiner {
    fun goToNameAndPasswordScreen(email: String)
    fun goToWelcomeScreen(name: String)
    fun goToPhotoScreen()
}