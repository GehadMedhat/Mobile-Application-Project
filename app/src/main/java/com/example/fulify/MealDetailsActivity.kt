package com.example.fulify

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentMealDetailsBinding

class MealDetailsFragment : Fragment(R.layout.fragment_meal_details) {

    private var _binding: FragmentMealDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMealDetailsBinding.bind(view)

        val title = arguments?.getString("TITLE")
        val image = arguments?.getInt("IMAGE")
        val time = arguments?.getString("TIME")
        val level = arguments?.getString("LEVEL")
        val videoUrl = arguments?.getString("VIDEO_URL")

        binding.r8bkxb2stlgv.text = title
        binding.rnm39gz4ih6f.text = time
        binding.r6wa3139mxfj.text = level

        image?.let {
            binding.rifjohphgywi.setImageResource(it)
        }

        binding.rm95a9kwudsg.setOnClickListener {
            if (!videoUrl.isNullOrEmpty()) {
                try {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(videoUrl)
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(
                        requireContext(),
                        "Cannot open video",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
