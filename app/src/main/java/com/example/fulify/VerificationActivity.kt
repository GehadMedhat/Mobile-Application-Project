package com.example.fulify

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fulify.databinding.ActivityVerificationBinding

class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding
    private var email: String = ""
    private var fromForgotPassword: Boolean = false
    private lateinit var codeBoxes: List<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = intent.getStringExtra("EMAIL") ?: ""
        fromForgotPassword = intent.getBooleanExtra("FROM_FORGOT_PASSWORD", false)

        // Update email text
        binding.rozgsyo8bbg9.text = "We sent a code to\n$email"

        setupCodeInput()
        setupClickListeners()
    }

    private fun setupCodeInput() {
        // For this demo, we'll simulate code entry
        // In your actual implementation, you'd need to add EditTexts to the layout
        // or handle the empty LinearLayouts as code input fields
    }

    private fun setupClickListeners() {
        // Back arrow
        binding.r0rvttdbbaec.setOnClickListener {
            finish()
        }

        // Continue button
        binding.rngayjooszt.setOnClickListener {
            verifyCode()
        }

        // Send Again (resend code)
        binding.rx23q80yl3em.setOnClickListener {
            resendCode()
        }

        // Back to login (bottom)
        binding.rgo75io1sqvj.setOnClickListener {
            finish()
        }

        binding.rfkhgkclngl.setOnClickListener {
            finish()
        }
    }

    private fun verifyCode() {
        // In real app, verify the entered code
        // For demo, we'll accept any input after a delay

        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val savedCode = prefs.getString("verificationCode", "1234")

        // For demo purposes, let's just accept and continue
        Toast.makeText(this, "Verification successful!", Toast.LENGTH_SHORT).show()

        if (fromForgotPassword) {
            // Navigate to Set New Password
            val intent = Intent(this, SetNewPasswordActivity::class.java)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
            finish()
        } else {
            // Navigate to main app after signup verification
            prefs.edit().putBoolean("isLoggedIn", true).apply()
            Toast.makeText(this, "Account verified! Welcome!", Toast.LENGTH_SHORT).show()
            // Navigate to main screen (to be implemented)
            finish()
        }
    }

    private fun resendCode() {
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val newCode = (1000..9999).random().toString()
        prefs.edit().putString("verificationCode", newCode).apply()

        Toast.makeText(this, "New code sent to $email", Toast.LENGTH_SHORT).show()
    }
}