package com.example.fulify

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.fulify.databinding.FragmentDeliveryStatusBinding

class DeliveryStatusFragment : Fragment(R.layout.fragment_delivery_status) {

    private lateinit var binding: FragmentDeliveryStatusBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeliveryStatusBinding.bind(view)

        setupBackButton()
        setupFooterNavigation()
    }

    private fun setupBackButton() {
        binding.rm6nnd7u9n9h.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupFooterNavigation() {

        binding.rcpxyvmq9d2q.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeDietFragment())
                .commit()
        }

        binding.rwdjxk6o2zpe.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MealDeliveryFragment())
                .addToBackStack(null)
                .commit()
        }

        // switch to recipes fragment
        binding.rs54njblc63i.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipesFragment())
                .addToBackStack(null)
                .commit()
        }


        binding.r7zydsrg3nir.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GroceryFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.r4rs03s1n61q.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EcoSustainability())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
