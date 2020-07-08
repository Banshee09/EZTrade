package com.eztrade.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eztrade.R
import com.eztrade.databinding.FragmentHistoryBinding
import com.eztrade.databinding.FragmentHomeBinding
import com.eztrade.viewmodel.HistoryViewModel
import com.eztrade.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.setLifecycleOwner(this)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel

        val adapter = HoldingAdapter()
        binding.holdingList.adapter = adapter
        homeViewModel.holdings.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}