package com.example.fulify

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentCloudKitchen1Binding

class CloudKitchenFragment : Fragment(R.layout.fragment_cloud_kitchen1) {

    private lateinit var binding: FragmentCloudKitchen1Binding

    private var selectedPlan: String? = null
    private var selectedSize: String? = null
    private var selectedSpice: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCloudKitchen1Binding.bind(view)

        setupPlanClicks()
        setupSizeClicks()
        setupSpiceClicks()
        clickOrderNow()
    }

    // ---------- PLAN ----------
    private fun setupPlanClicks() {
        binding.rbppzybkhgzm.setOnClickListener {
            selectedPlan = "Daily"
            selectPlan(true)
        }

        binding.rl6m51m6hmh.setOnClickListener {
            selectedPlan = "Weekly"
            selectPlan(false)
        }
    }

    private fun selectPlan(isDaily: Boolean) {
        binding.rbppzybkhgzm.setBackgroundResource(
            if (isDaily) R.drawable.dark_pink_rectangle else R.drawable.pink_full_rectnagle
        )
        binding.rl6m51m6hmh.setBackgroundResource(
            if (!isDaily) R.drawable.dark_pink_rectangle else R.drawable.pink_full_rectnagle
        )
    }

    // ---------- SIZE ----------
    private fun setupSizeClicks() {
        binding.rlcjdmf2gxa9.setOnClickListener {
            selectedSize = "Small"
            selectSize(0)
        }
        binding.rqvunv1gap5s.setOnClickListener {
            selectedSize = "Regular"
            selectSize(1)
        }
        binding.rhuc0ukwuj4j.setOnClickListener {
            selectedSize = "Large"
            selectSize(2)
        }
    }

    private fun selectSize(index: Int) {
        val views = listOf(
            binding.rlcjdmf2gxa9,
            binding.rqvunv1gap5s,
            binding.rhuc0ukwuj4j
        )

        views.forEachIndexed { i, v ->
            v.setBackgroundResource(
                if (i == index) R.drawable.dark_pink_rectangle
                else R.drawable.pink_full_rectnagle
            )
        }
    }

    // ---------- SPICE ----------
    private fun setupSpiceClicks() {
        binding.rohx4rozf0x.setOnClickListener {
            selectedSpice = "Mild"
            selectSpice(0)
        }
        binding.r5vac6iw36b4.setOnClickListener {
            selectedSpice = "Medium"
            selectSpice(1)
        }
        binding.r3tlpx5ub9ys.setOnClickListener {
            selectedSpice = "Hot"
            selectSpice(2)
        }
    }

    private fun selectSpice(index: Int) {
        val views = listOf(
            binding.rohx4rozf0x,
            binding.r5vac6iw36b4,
            binding.r3tlpx5ub9ys
        )

        views.forEachIndexed { i, v ->
            v.setBackgroundResource(
                if (i == index) R.drawable.dark_pink_rectangle
                else R.drawable.pink_full_rectnagle
            )
        }
    }

    private fun clickOrderNow() {
        binding.rsj5n8h2bdo.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PreparingOrderFragment())
                .commit()
        }
    }
}