package com.eztrade.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eztrade.R
import com.eztrade.databinding.FragmentResearchBinding
import com.eztrade.viewmodel.ResearchViewModel


class ResearchFragment : Fragment() {

    private lateinit var researchViewModel: ResearchViewModel
    private lateinit var binding: FragmentResearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_research, container, false)
        binding.setLifecycleOwner(this)

        researchViewModel = ViewModelProviders.of(this).get(ResearchViewModel::class.java)
        binding.researchViewModel = researchViewModel

        val myWebView: WebView = binding.root.findViewById(R.id.webview_research)
        myWebView.setWebViewClient(WebViewClient())
        myWebView.getSettings().setJavaScriptEnabled(true);
        researchViewModel.url.observe(this, Observer { it ->
            myWebView.loadUrl(it)
        })

        val shareButton: Button = binding.root.findViewById(R.id.shareButton)
        shareButton.setOnClickListener(View.OnClickListener {
            val shareText: TextView = binding.root.findViewById(R.id.shareText)
            researchViewModel.setShareURL(shareText.text.toString())
        })

        return binding.root
    }

}



