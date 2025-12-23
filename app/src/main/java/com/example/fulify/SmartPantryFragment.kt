package com.example.fulify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentSmartPantryBinding

class SmartPantryFragment : Fragment(R.layout.fragment_smart_pantry) {

    private var _binding: FragmentSmartPantryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSmartPantryBinding.bind(view)

        setupBack()
        setupAddButton()
        setupVideoButtons()
        setupBottomNavigation()
    }

    // 🔙 رجوع
    private fun setupBack() {
        binding.r4br4lvt61oh.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    // ➕ Add
    private fun setupAddButton() {
        binding.r3jcd8asnbjc.setOnClickListener {
            showAddDialog()
        }
    }

    // 🪟 Dialog الإضافة
    private fun showAddDialog() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.diaglog_add_item, null)

        val nameInput = dialogView.findViewById<EditText>(R.id.etName)
        val amountInput = dialogView.findViewById<EditText>(R.id.etAmount)

        AlertDialog.Builder(requireContext())
            .setTitle("Add Pantry Item")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameInput.text.toString()
                val amount = amountInput.text.toString()

                if (name.isNotBlank() && amount.isNotBlank()) {
                    addItemToPantry(
                        name = name,
                        amount = amount,
                        category = "General",
                        expiry = "7 days"
                    )
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // ➕ إضافة item جديد
    private fun addItemToPantry(
        name: String,
        amount: String,
        category: String,
        expiry: String
    ) {

        val itemLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = android.view.Gravity.CENTER_VERTICAL
            setBackgroundResource(R.drawable.pink_full_rectnagle)
            setPadding(dp(16), dp(16), dp(16), dp(16))

            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = dp(12)
            }
        }

        // ⬅️ الجزء الشمال (الاسم + التفاصيل)
        val leftContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        val nameText = TextView(requireContext()).apply {
            text = name
            textSize = 13f
            setTypeface(typeface, android.graphics.Typeface.BOLD)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            setPadding(0, 0, 0, dp(10))
        }

        val detailsRow = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = android.view.Gravity.CENTER_VERTICAL
        }

        val amountText = TextView(requireContext()).apply {
            text = amount
            textSize = 11f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        }

        val dot = TextView(requireContext()).apply {
            text = "  •  "
            textSize = 11f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        }

        val categoryText = TextView(requireContext()).apply {
            text = category
            textSize = 11f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        }

        detailsRow.addView(amountText)
        detailsRow.addView(dot)
        detailsRow.addView(categoryText)

        leftContainer.addView(nameText)
        leftContainer.addView(detailsRow)

        // ➡️ الجزء اليمين (Expiry)
        val rightContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.END
        }

        val expiresLabel = TextView(requireContext()).apply {
            text = "Expires in"
            textSize = 11f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
            setPadding(0, 0, 0, dp(6))
        }

        val expiresValue = TextView(requireContext()).apply {
            text = expiry
            textSize = 11f
            setTypeface(typeface, android.graphics.Typeface.BOLD)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
        }

        rightContainer.addView(expiresLabel)
        rightContainer.addView(expiresValue)

        itemLayout.addView(leftContainer)
        itemLayout.addView(rightContainer)

        binding.rey3c9zf1p2.addView(itemLayout)
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun openYoutubeVideo(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


    private fun setupVideoButtons() {

        // Recipe 1
        binding.rhcwfng47yj6.setOnClickListener {
            openYoutubeVideo("https://youtu.be/bUvmOvhQag8?si=T1A5Axm-Qk5_8Y2m")
        }

        // Recipe 2
        binding.riy0r5bbpyr.setOnClickListener {
            openYoutubeVideo("https://youtu.be/qH__o17xHls?si=mN8OrlUaYptLXHC9")
        }

        // Recipe 3
        binding.r8inpls2hnkv.setOnClickListener {
            openYoutubeVideo("https://youtu.be/ZDFbJSLXWhg?si=gBlfpwo_94592uKs")
        }
    }

    private fun setupBottomNavigation() {

        //switch to home fragment
        binding.rcpxyvmq9d2q.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeDietFragment())
                .commit()
        }

        binding.rwdjxk6o2zpe.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MealDeliveryFragment())
                .commit()
        }

        binding.rs54njblc63i.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipesFragment())
                .commit()
        }

        binding.r7zydsrg3nir.setOnClickListener {
        }

        binding.r4rs03s1n61q.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EcoSustainability())
                .addToBackStack(null)
                .commit()
        }
    }

}
