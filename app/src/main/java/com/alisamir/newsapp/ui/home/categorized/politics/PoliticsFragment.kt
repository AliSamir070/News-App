package com.alisamir.newsapp.ui.home.categorized.politics

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
import com.alisamir.newsapp.databinding.FragmentPoliticsBinding
import com.alisamir.newsapp.databinding.FragmentSportsBinding
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.ui.home.HomeNewsFragmentDirections

class PoliticsFragment : Fragment() {
    lateinit var binding:FragmentPoliticsBinding
    val politics_model:politicsModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPoliticsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        politics_model.politicsArticles.observe(viewLifecycleOwner){
            setPoliticsAdapter(it)
        }
    }

    private fun setPoliticsAdapter(list: List<Article>){
        binding.politicsNewsList.adapter = CategorizedListAdapter(list , requireContext()){
            val action = HomeNewsFragmentDirections.actionHomeNewsToDescriptionNews(list[it])
            findNavController().navigate(action)
        }
        binding.politicsNewsList.layoutManager = LinearLayoutManager(requireContext())
        binding.politicsNewsList.isNestedScrollingEnabled = false
    }
}