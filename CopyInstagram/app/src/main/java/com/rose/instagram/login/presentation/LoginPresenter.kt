package com.rose.instagram.login.presentation

import android.util.Patterns
import com.rose.instagram.R
import com.rose.instagram.common.model.UserAuth
import com.rose.instagram.login.Login
import com.rose.instagram.login.data.LoginCallback
import com.rose.instagram.login.data.LoginRepository

class LoginPresenter(
    private var view: Login.View?,
    private val repository: LoginRepository
) : Login.Presenter {
    override fun login(email: String, password: String) {

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length < 8

        if (isEmailValid && isPasswordValid) {
            view?.showProgress(true)

            repository.login(email, password, object : LoginCallback {
                override fun onSucess(userAuth: UserAuth) {
                    view?.onUserAuthenticated()
                }

                override fun onFailure(message: String) {
                    view?.onUserUnauthorized(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }

            }
            )
        }

    }

    override fun onDestroy() {
        view = null
    }


}