package com.alisamir.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.alisamir.newsapp.R
import com.alisamir.newsapp.databinding.FragmentSiteViewBinding


class SiteViewFragment : Fragment() {
    lateinit var link:String
    lateinit var binding:FragmentSiteViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            link = it.getString("link").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSiteViewBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val controller = findNavController()
        val appBarConfiguration = AppBarConfiguration(controller.graph)
        binding.toolbar.setupWithNavController(controller,appBarConfiguration)

        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)
        (activity as AppCompatActivity).supportActionBar?.title = "News"
        binding.siteViewer.loadUrl(link)
        binding.siteViewer.settings.javaScriptEnabled = true
        binding.siteViewer.settings.builtInZoomControls = true
    }
}