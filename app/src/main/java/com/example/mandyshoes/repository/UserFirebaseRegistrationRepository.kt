package com.example.mandyshoes.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserFirebaseRegistrationRepository {
    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth
    private val db = Firebase.firestore

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    suspend fun createUser(
        email: String,
        pass: String,
        userData: HashMap<String, String>,
        colectionName:String
    ): String {
        try {
            auth
                .createUserWithEmailAndPassword(email, pass)
                .await()
            userData.put("uId", auth.currentUser!!.uid)
            db.collection(colectionName).add(userData)
            return "Sucesso"
        } catch (e: Exception) {
            val message = e.message.toString()
            return message
        }
    }

}