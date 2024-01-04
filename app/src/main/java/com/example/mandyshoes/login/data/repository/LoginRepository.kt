package com.example.mandyshoes.login.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class LoginRepository {
    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth
    private var errorMessage = " "

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    fun getErrorMessage(): String = errorMessage

    fun login(email: String, pass: String): Flow<Boolean> = callbackFlow {
        try {
            auth.signInWithEmailAndPassword(email, pass).await()
            trySend(true).isSuccess// Login bem-sucedido
        } catch (e: Exception) {
            errorMessage = e.message.toString()
            trySend(false).isSuccess // Login falhou
        }

        awaitClose()
    }

    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }

    fun deleteUser() {
        val user = auth.currentUser
        user?.delete()
    }
}

