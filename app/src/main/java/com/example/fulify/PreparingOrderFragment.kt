package com.example.fulify

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentPreparingOrderBinding

class PreparingOrderFragment : Fragment(R.layout.fragment_preparing_order) {

    private lateinit var binding: FragmentPreparingOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPreparingOrderBinding.bind(view)

        startLoading()
    }

    private fun startLoading() {
        // تأكدي إن الـ ProgressBar شغال
        binding.progress.visibility = View.VISIBLE

        // انتظار 10 ثواني
        Handler(Looper.getMainLooper()).postDelayed({

            // الانتقال للصفحة اللي بعدها
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DeliveryStatusFragment())
                .addToBackStack(null)
                .commit()

        }, 10_000) // 10 seconds
    }
}
