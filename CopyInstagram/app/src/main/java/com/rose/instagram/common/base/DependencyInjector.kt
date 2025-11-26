package com.rose.instagram.common.base

import com.rose.instagram.login.data.FakeDataSource
import com.rose.instagram.login.data.LoginRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository{
        return LoginRepository(FakeDataSource())
    }

}