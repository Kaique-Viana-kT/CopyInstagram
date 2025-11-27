package com.rose.instagram.register.data

import android.os.Handler
import android.os.Looper
import com.rose.instagram.common.model.Database

class FakeRegisterEmailDataSource : RegisterEmailDataSource {
    override fun create(
        email: String,
        callback: RegisterEmailCallback
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            //firstOrNull -> retorna o primeiero elemento da lista e se não tiver nada
            //retorna null (se a lista estiver vazia)
            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if(userAuth == null){
                callback.onSucess()
            }else{
                callback.onFailure("Usuário já cadastrado")
            }
        },2000)
    }
}