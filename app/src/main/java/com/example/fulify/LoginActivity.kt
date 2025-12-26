package com.example.fulify

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fulify.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Back arrow
        binding.rca589uz20jt.setOnClickListener {
            finish()
        }

        // Password visibility toggle
        binding.rlhctwdh473.setOnClickListener {
            togglePasswordVisibility()
        }

        // Forgot Password
        binding.r1hht59hz75e.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Login button
        binding.loginBtn.setOnClickListener {
            validateAndLogin()
        }

        // Sign up
        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        if (isPasswordVisible) {
            binding.password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.rlhctwdh473.setImageResource(R.drawable.eye_off)
        } else {
            binding.password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.rlhctwdh473.setImageResource(R.drawable.eye)
        }
        binding.password.setSelection(binding.password.text?.length ?: 0)
    }

    private fun validateAndLogin() {
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString()

        // Validation
        when {
            email.isEmpty() -> {
                binding.email.error = "Email is required"
                binding.email.requestFocus()
                return
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.email.error = "Enter a valid email"
                binding.email.requestFocus()
                return
            }
            password.isEmpty() -> {
                binding.password.error = "Password is required"
                binding.password.requestFocus()
                return
            }
        }

        // Check credentials (in real app, verify with backend)
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val savedEmail = prefs.getString("email", "")
        val savedPassword = prefs.getString("password", "")

        if (email == savedEmail && password == savedPassword) {
            // Login successful
            prefs.edit().putBoolean("isLoggedIn", true).apply()

            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

            // Navigate to main app (create MainActivity later)
            // For now, just finish


            finish()
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }
}