package com.example.mandyshoes.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mandyshoes.R
import com.example.mandyshoes.databinding.FragmentLoginBinding
import com.example.mandyshoes.login.viewModel.LoginViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var toastLoginIsNull: Toast

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = LoginViewModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setUpEditText()
    }

    private fun setUpEditText() {
        binding.editTextTextEmailAddress.doOnTextChanged { text, start, before, count ->
            viewModel.emailValue(text.toString())
        }

        binding.editTextTextpasswordAddress.doOnTextChanged { text, start, before, count ->
            viewModel.passwordValue(text.toString())
        }
    }

    private fun checkLoginIsEmpty(): Boolean =
        (viewModel.email.value?.isNullOrEmpty() == null ||
                viewModel.pass.value?.isNullOrEmpty() == null ||
                viewModel.email.value?.isNullOrEmpty() == true ||
                viewModel.pass.value?.isNullOrEmpty() == true)


    fun setUpToast(toastMessage: String) {
        toastLoginIsNull = Toast.makeText(
            context, toastMessage, Toast.LENGTH_LONG
        )
    }

    private fun setupViews() {
        binding.loginButton.setOnClickListener {
            when (checkLoginIsEmpty()) {
                true -> {
                    setUpToast("Enter your login and password ")
                    toastLoginIsNull.show()
                }

                else -> {
                    lifecycleScope.launch {
                        viewModel.verifyLogin().await().collect {
                            when (it) {
                                true -> {
                                    findNavController()
                                        .navigate(R.id.action_loginFragment_to_shopListFragment)
                                }

                                else -> {
                                    setUpToast("We couldn't find your user; we are creating a new one")
                                    val message = viewModel.crateUser()
                                    message.invokeOnCompletion {
                                        if (message.getCompleted() != "Sucesso") {
                                            setUpToast(message.getCompleted())
                                        } else {
                                            findNavController()
                                                .navigate(R.id.action_loginFragment_to_onboardingFragment)
                                        }

                                    }


                                }
                            }
                        }
                    }
                }
            }
        }
    }

}