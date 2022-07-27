package com.alisamir.newsapp.ui.home.categorized.health

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alisamir.newsapp.R
import com.alisamir.newsapp.data.CategorizedListAdapter
import com.alisamir.newsapp.databinding.FragmentHealthBinding
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.ui.home.HomeNewsFragmentDirections


class HealthFragment : Fragment() {
    lateinit var binding: FragmentHealthBinding
    val health_Model:healthModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        health_Model.healthArticles.observe(viewLifecycleOwner){
            setHealthAdapter(it)
        }
    }

    private fun setHealthAdapter(list: List<Article>){
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.healthNewsList.adapter = CategorizedListAdapter(list , requireContext()){
            val action = HomeNewsFragmentDirections.actionHomeNewsToDescriptionNews(list[it])
            findNavController().navigate(action)
        }
        binding.healthNewsList.layoutManager = layoutManager
        binding.healthNewsList.isNestedScrollingEnabled = false
    }
}