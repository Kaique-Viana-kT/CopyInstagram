package com.rose.instagram.register.data

import android.os.Handler
import android.os.Looper
import com.rose.instagram.common.model.Database
import com.rose.instagram.common.model.UserAuth
import java.util.UUID

class FakeRegisterEmailDataSource : RegisterDataSource {
    override fun create(
        email: String,
        callback: RegisterCallback
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

    override fun create(
        email: String,
        name: String,
        password: String,
        callback: RegisterCallback
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if(userAuth != null){
                callback.onFailure("Usuário já cadastrado")
            }else{
                val created = Database.usersAuth.add(
                    UserAuth(UUID.randomUUID().toString(), name, email, password)
                )

                if(created){
                    callback.onSucess()
                }else{
                    callback.onFailure("Erro Interno do servidor.")
                }
            }
            callback.onComplete()
        },2000)
    }
}