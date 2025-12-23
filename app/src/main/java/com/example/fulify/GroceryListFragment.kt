package com.example.fulify

import android.os.Bundle
import android.widget.ImageView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentGroceryListBinding
import com.google.android.material.imageview.ShapeableImageView

class GroceryListFragment : Fragment(R.layout.fragment_grocery_list) {

    private var _binding: FragmentGroceryListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGroceryListBinding.bind(view)

        setupBack()
        setupAddButton()
        setupExistingItems()
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

    // 🪟 Dialog
    private fun showAddDialog() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.diaglog_add_item, null)

        val nameInput = dialogView.findViewById<EditText>(R.id.etName)
        val amountInput = dialogView.findViewById<EditText>(R.id.etAmount)

        AlertDialog.Builder(requireContext())
            .setTitle("Add Item")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameInput.text.toString()
                val amount = amountInput.text.toString()

                if (name.isNotBlank() && amount.isNotBlank()) {
                    addItemToList(name, amount)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // ➕ إضافة بنفس XML بالظبط
    private fun addItemToList(name: String, amount: String) {

        val itemLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = android.view.Gravity.CENTER_VERTICAL
            setBackgroundResource(R.drawable.gray_rectanglewith_shape)
            setPadding(dp(13), dp(13), dp(13), dp(13))

            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = dp(8)
            }
        }

        val checkIcon = ShapeableImageView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(dp(20), dp(20)).apply {
                marginEnd = dp(12)
            }
            setImageResource(R.drawable.gray_rectangle1)
            scaleType = ImageView.ScaleType.FIT_XY
        }


        val textContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            setPadding(0, dp(5), 0, dp(5))
        }

        val nameText = TextView(requireContext()).apply {
            text = name
            textSize = 13f
            setTypeface(typeface, android.graphics.Typeface.BOLD)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            setPadding(0, 0, 0, dp(13))
        }

        val amountText = TextView(requireContext()).apply {
            text = amount
            textSize = 11f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        }

        textContainer.addView(nameText)
        textContainer.addView(amountText)

        itemLayout.addView(checkIcon)
        itemLayout.addView(textContainer)

        setupToggle(itemLayout, checkIcon, nameText, amountText)

        binding.r44msilj92fj.addView(itemLayout)
    }

    // ✅ نفس behavior القديم
    private fun setupToggle(
        container: LinearLayout,
        checkIcon: ShapeableImageView,
        nameText: TextView,
        amountText: TextView
    ) {
        var checked = false

        container.setOnClickListener {
            checked = !checked

            if (checked) {
                container.setBackgroundResource(R.drawable.dark_pink_rectangle)
                checkIcon.setImageResource(R.drawable.orange_checkmark)
                nameText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.gray)
                )
                amountText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.gray)
                )
            } else {
                container.setBackgroundResource(R.drawable.gray_rectanglewith_shape)
                checkIcon.setImageResource(R.drawable.gray_rectangle1)
                nameText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.black)
                )
                amountText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.gray)
                )
            }
        }
    }

    // 🔥 تفعيل items اللي جاية من XML
    private fun setupExistingItems() {
        for (i in 0 until binding.r44msilj92fj.childCount) {

            val item = binding.r44msilj92fj.getChildAt(i) as? LinearLayout ?: continue

            val firstChild = item.getChildAt(0)
            val textContainer = item.getChildAt(1) as? LinearLayout ?: continue

            val nameText = textContainer.getChildAt(0) as? TextView ?: continue
            val amountText = textContainer.getChildAt(1) as? TextView ?: continue

            when (firstChild) {
                is ShapeableImageView -> {
                    setupToggle(item, firstChild, nameText, amountText)
                }

                is LinearLayout -> {
                    // نحوله لشيك وهمي بس نغير الخلفية
                    setupToggleForLegacy(item, firstChild, nameText, amountText)
                }
            }
        }
    }

    private fun setupToggleForLegacy(
        container: LinearLayout,
        fakeCheck: View,
        nameText: TextView,
        amountText: TextView
    ) {
        var checked = false

        container.setOnClickListener {
            checked = !checked

            if (checked) {
                container.setBackgroundResource(R.drawable.dark_pink_rectangle)
                fakeCheck.setBackgroundResource(R.drawable.orange_checkmark)
                nameText.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                amountText.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
            } else {
                container.setBackgroundResource(R.drawable.gray_rectanglewith_shape)
                fakeCheck.setBackgroundResource(R.drawable.gray_rectangle1)
                nameText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                amountText.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
            }
        }
    }


    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
