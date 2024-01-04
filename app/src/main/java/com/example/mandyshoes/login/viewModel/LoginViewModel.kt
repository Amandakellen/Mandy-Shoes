package com.example.mandyshoes.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mandyshoes.login.data.repository.LoginRepository
import com.example.mandyshoes.repository.UserFirebaseRegistrationRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class LoginViewModel() : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private var _userData: MutableLiveData<FirebaseUser>? = null
    var userData: LiveData<FirebaseUser>? = _userData

    private var _loggedStatus: MutableLiveData<Boolean>? = null
    var loggedStatus: LiveData<Boolean>? = _loggedStatus

    private var repository: LoginRepository = LoginRepository()

    private val registrationRepository: UserFirebaseRegistrationRepository =
        UserFirebaseRegistrationRepository()

    init {
        _userData = repository.firebaseUserMutableLiveData
        _loggedStatus = repository.userLoggedMutableLiveData

    }

    fun emailValue(email: String) {
        _email.value = email
    }

    fun passwordValue(senha: String) {
        _pass.value = senha
    }

    fun getErrorMessage() : String =  repository.getErrorMessage()

    fun verifyLogin() : Deferred<Flow<Boolean>> {
        val result = viewModelScope.async {
            repository.login(_email.value.toString(), _pass.value.toString())
        }

        return result

    }

    fun checkPassLength(): Boolean {
        return _pass.value!!.length == 6
    }
    private fun hashMapData(): HashMap<String, String> {
        return hashMapOf(
            "pu_email" to _email.value.toString()
        )

    }

    fun crateUser(): Deferred<String> {
        val result = viewModelScope.async {
            registrationRepository.createUser(
                _email.value.toString(),
                _pass.value.toString(),
                hashMapData(),
                "users"
            )
        }
        return result


    }

}