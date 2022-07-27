package com.alisamir.newsapp.ui.home.categorized.sports

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
import com.alisamir.newsapp.databinding.FragmentSportsBinding
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.ui.home.HomeNewsFragmentDirections


class SportsFragment : Fragment() {

    lateinit var binding: FragmentSportsBinding
    val sports_model:sportsModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSportsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sports_model.sportsArticles.observe(viewLifecycleOwner){
            setSportsAdapter(it)
        }
    }

    private fun setSportsAdapter(list: List<Article>){
        binding.sportsNewsList.adapter = CategorizedListAdapter(list , requireContext()){
            val action = HomeNewsFragmentDirections.actionHomeNewsToDescriptionNews(list[it])
            findNavController().navigate(action)
        }
        binding.sportsNewsList.layoutManager = LinearLayoutManager(requireContext())
        binding.sportsNewsList.isNestedScrollingEnabled = false
    }

}