package com.eztrade.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eztrade.R
import com.eztrade.viewmodel.TransactViewModel

class TransactFragment : Fragment() {

    private lateinit var transactViewModel: TransactViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        transactViewModel =
            ViewModelProviders.of(this).get(TransactViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_transact, container, false)
        val textView: TextView = root.findViewById(R.id.text_transact)
        transactViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

}