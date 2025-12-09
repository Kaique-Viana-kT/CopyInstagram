package com.rose.instagram.register.presenter

import com.rose.instagram.R
import com.rose.instagram.register.RegisterNameAndPassword
import com.rose.instagram.register.data.RegisterCallback
import com.rose.instagram.register.data.RegisterRepository

class RegisterNameAndPasswordPresenter(private var view: RegisterNameAndPassword.View?,
    private var repository: RegisterRepository) : RegisterNameAndPassword.Presenter {

    override fun create(email: String, name: String, password: String,  confirm: String) {

        val isNameValid = name.length > 3
        val isPasswordValid = password.length >= 8
        val isConfirmValid = password == confirm

        if(!isNameValid){
            view?.displayNameFailure(R.string.invalid_name)
        }else{
            view?.displayNameFailure(null)
        }

        if(!isConfirmValid){
            view?.displaPasswordFailure(R.string.password_not_equal)
        }else{
            if(!isPasswordValid){
                view?.displaPasswordFailure(R.string.invalid_password)
            }else{
                view?.displaPasswordFailure(null)
            }
        }


        if (isNameValid && isPasswordValid && isConfirmValid) {
            view?.showProgress(true)

            repository.create(email, name, password,object : RegisterCallback {
                override fun onSucess() {
                    view?.onCreateSucess(name)
                }

                override fun onFailure(message: String) {
                    view?.onCreateFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }

            })
        }

    }

    override fun onDestroy() {
        view = null
    }


}