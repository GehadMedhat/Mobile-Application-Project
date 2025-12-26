package com.example.fulify

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fulify.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
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

        // Confirm password visibility toggle
        binding.reyeConfirm.setOnClickListener {
            toggleConfirmPasswordVisibility()
        }

        // Sign Up button
        binding.signUpBtn.setOnClickListener {
            validateAndSignUp()
        }

        // Already have account - Login
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
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

    private fun toggleConfirmPasswordVisibility() {
        isConfirmPasswordVisible = !isConfirmPasswordVisible
        if (isConfirmPasswordVisible) {
            binding.confPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.reyeConfirm.setImageResource(R.drawable.eye_off)
        } else {
            binding.confPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.reyeConfirm.setImageResource(R.drawable.eye)
        }
        binding.confPassword.setSelection(binding.confPassword.text?.length ?: 0)
    }

    private fun validateAndSignUp() {
        val firstName = binding.fName.text.toString().trim()
        val secondName = binding.secName.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString()
        val confirmPassword = binding.confPassword.text.toString()

        // Validation
        when {
            firstName.isEmpty() -> {
                binding.fName.error = "First name is required"
                binding.fName.requestFocus()
                return
            }
            secondName.isEmpty() -> {
                binding.secName.error = "Second name is required"
                binding.secName.requestFocus()
                return
            }
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
            password.length < 6 -> {
                binding.password.error = "Password must be at least 6 characters"
                binding.password.requestFocus()
                return
            }
            confirmPassword != password -> {
                binding.confPassword.error = "Passwords do not match"
                binding.confPassword.requestFocus()
                return
            }
        }

        // Save user data (in real app, send to backend)
        saveUserData(firstName, secondName, email, password)

        // Navigate to verification
        val intent = Intent(this, VerificationActivity::class.java)
        intent.putExtra("EMAIL", email)
        startActivity(intent)
        finish()
    }

    private fun saveUserData(firstName: String, secondName: String, email: String, password: String) {
        // Save to SharedPreferences (in real app, use backend API)
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        prefs.edit().apply {
            putString("firstName", firstName)
            putString("secondName", secondName)
            putString("email", email)
            putString("password", password) // Never store plain passwords in production!
            putBoolean("isLoggedIn", false)
            apply()
        }

        Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
    }
}