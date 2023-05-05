package com.example.kyivweatherapp.ui.fragments.rootelement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.kyivweatherapp.R
import com.example.kyivweatherapp.databinding.FragmentRootBinding
import com.google.android.material.tabs.TabLayoutMediator


class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    private var cont: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cont = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)

        binding.tabLayout.tabIconTint = null
        binding.viewPager.adapter = VpAdapter(cont as FragmentActivity)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.setIcon(R.drawable.baseline_electric_bolt)

                }
                1 -> {
                    tab.setIcon(R.drawable.baseline_access_time)
                }
            }
        }.attach()
    }

}