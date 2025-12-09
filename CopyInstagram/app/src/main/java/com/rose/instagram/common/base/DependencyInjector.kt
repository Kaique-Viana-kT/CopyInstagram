package com.rose.instagram.common.base

import com.rose.instagram.login.data.FakeDataSource
import com.rose.instagram.login.data.LoginRepository
import com.rose.instagram.register.data.FakeRegisterEmailDataSource
import com.rose.instagram.register.data.RegisterRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository{
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository() : RegisterRepository{
        return RegisterRepository(FakeRegisterEmailDataSource())
    }

}