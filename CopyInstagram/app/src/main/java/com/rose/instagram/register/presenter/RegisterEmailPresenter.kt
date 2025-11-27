package com.rose.instagram.register.presenter

import android.util.Patterns
import com.rose.instagram.R
import com.rose.instagram.common.model.UserAuth
import com.rose.instagram.login.Login
import com.rose.instagram.login.data.LoginCallback
import com.rose.instagram.login.data.LoginRepository
import com.rose.instagram.register.RegisterEmail
import com.rose.instagram.register.data.RegisterEmailCallback
import com.rose.instagram.register.data.RegisterEmailRepository

class RegisterEmailPresenter(
    private var view: RegisterEmail.View?,
    private val repository: RegisterEmailRepository
) : RegisterEmail.Presenter {
    override fun create(email: String) {

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (isEmailValid) {
            view?.showProgress(true)

            repository.create(email, object : RegisterEmailCallback {
                override fun onSucess() {
                    view?.goToNameAndPasswordScreen(email)
                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)
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