package com.alisamir.newsapp.ui.home.categorized.business

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alisamir.newsapp.data.CategorizedListAdapter
import com.alisamir.newsapp.databinding.FragmentBusinessBinding
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.ui.home.HomeNewsFragmentDirections


class BusinessFragment : Fragment() {
    lateinit var binding: FragmentBusinessBinding
    val business_model:businessModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBusinessBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        business_model.businessArticles.observe(viewLifecycleOwner){
            setBusinessAdapter(it)
        }
    }

    private fun setBusinessAdapter(list: List<Article>){
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.businessNewsList.adapter = CategorizedListAdapter(list , requireContext()){
            val action = HomeNewsFragmentDirections.actionHomeNewsToDescriptionNews(list[it])
            findNavController().navigate(action)
        }
        binding.businessNewsList.layoutManager = layoutManager
        binding.businessNewsList.isNestedScrollingEnabled = false
    }
}