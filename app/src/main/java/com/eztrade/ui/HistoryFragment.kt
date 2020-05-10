package com.eztrade.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eztrade.R
import com.eztrade.database.getDatabase
import com.eztrade.databinding.FragmentHistoryBinding
import com.eztrade.viewmodel.HistoryViewModel
import com.eztrade.viewmodel.ResearchViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        binding.setLifecycleOwner(this)

        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        binding.historyViewModel = historyViewModel

//        val textView: TextView = root.findViewById(R.id.text_history)
//
//        historyViewModel.orders.observe(this, Observer {
//            textView.text = it.toString()
//        })

        val adapter = OrderAdapter(OrderListener { orderId ->
            Toast.makeText(context, "${orderId}", Toast.LENGTH_LONG).show()
        })
        binding.orderList.adapter = adapter
        historyViewModel.orders.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        return binding.root
    }
}