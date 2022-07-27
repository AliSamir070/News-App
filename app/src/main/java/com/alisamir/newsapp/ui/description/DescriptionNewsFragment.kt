package com.alisamir.newsapp.ui.description

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.alisamir.newsapp.R
import com.alisamir.newsapp.databinding.FragmentDescriptionNewsBinding
import com.alisamir.newsapp.pojo.Article
import com.google.android.material.appbar.CollapsingToolbarLayout


class DescriptionNewsFragment : Fragment() {
    lateinit var binding: FragmentDescriptionNewsBinding
    lateinit var article: Article
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            article = getParcelable<Article>("article")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val controller = findNavController()
        val appBarConfiguration = AppBarConfiguration(controller.graph)
        val layout = view.findViewById<CollapsingToolbarLayout>(R.id.toolbarcollabsed)
        binding.toolbarcollabsed.setupWithNavController(binding.toolbar,controller,appBarConfiguration)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)
        (activity as AppCompatActivity).supportActionBar?.title = ""
        if (article.urlToImage!=null){
            binding.background.load(Uri.parse(article.urlToImage)){
                placeholder(R.drawable.loading_animation)
                error(R.drawable.broken_image)
            }
        }else{
            binding.background.load(R.drawable.back_no_image){
                placeholder(R.drawable.loading_animation)
                error(R.drawable.broken_image)
            }
        }
        binding.description.text = article.description?:"No description"
        binding.link.text = getString(R.string.link,Html.fromHtml(article.url,FROM_HTML_MODE_LEGACY))
        binding.title.text = article.title

    }



}