package com.sanddev.doggodex.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.sanddev.doggodex.MainActivity
import com.sanddev.doggodex.R
import com.sanddev.doggodex.api.ApiResponseStatus
import com.sanddev.doggodex.databinding.ActivityLoginBinding
import com.sanddev.doggodex.models.User

class LoginActivity : AppCompatActivity(), LoginFragment.LoginFragmentActions,
    SignUpFragment.SignUpFragmentActions {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val progressBar = binding.progressBar
        viewModel.status.observe(this, { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    progressBar.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }

                is ApiResponseStatus.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is ApiResponseStatus.Success -> {
                    progressBar.visibility = View.GONE
                }
            }
        })

        viewModel.user.observe(this) { user ->
            if (user != null) {
                User.setLoggedInUser(this, user)
                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showErrorDialog(messageId: Int) {
        AlertDialog.Builder(this)
            .setTitle(R.string.there_was_an_error)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .create()
            .show()
    }

    override fun onRegisterButtonClick() {
        findNavController(R.id.nav_host_fragment)
            .navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
    }

    override fun onSignUpFieldsValidated(
        email: String,
        password: String,
        confirmationPassword: String
    ) {
        viewModel.signUp(email, password, confirmationPassword)
    }

    override fun onLogInFieldsValidated(email: String, password: String) {
        viewModel.logIn(email, password)
    }
}