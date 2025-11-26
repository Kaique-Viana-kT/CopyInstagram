package com.rose.instagram.login.data

import com.rose.instagram.common.model.UserAuth

interface LoginCallback {
    fun onSucess(userAuth: UserAuth)
    fun onFailure(message: String)
    fun onComplete()
}