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
import com.eztrade.databinding.FragmentResearchBinding
import com.eztrade.databinding.FragmentTransactBinding
import com.eztrade.viewmodel.ResearchViewModel
import com.eztrade.viewmodel.TransactViewModel

class TransactFragment : Fragment() {

    private lateinit var transactViewModel: TransactViewModel
    private lateinit var binding: FragmentTransactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transact, container, false)
        binding.setLifecycleOwner(this)

        transactViewModel = ViewModelProviders.of(this).get(TransactViewModel::class.java)
        binding.transactViewModel = transactViewModel

        return binding.root
    }

}