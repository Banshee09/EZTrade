package com.eztrade.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.eztrade.R
import com.eztrade.databinding.FragmentDetailBinding
import com.eztrade.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding.viewModel = viewModel

        val args = DetailFragmentArgs.fromBundle(arguments!!)
        viewModel.setOrder(args.order);
        //Log.d("Test", args.order.toString())

        return binding.root
    }


}
