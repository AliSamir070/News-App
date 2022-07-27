package com.alisamir.newsapp.ui.home.categorized.technology

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
import com.alisamir.newsapp.databinding.FragmentTechnologyBinding
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.ui.home.HomeNewsFragmentDirections


class TechnologyFragment : Fragment() {
    lateinit var binding:FragmentTechnologyBinding
    val technology_model:technologyModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTechnologyBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        technology_model.technologyArticles.observe(viewLifecycleOwner){
            setTechnologyAdapter(it)
        }
    }

    private fun setTechnologyAdapter(list:List<Article>){
        binding.technologyNewsList.adapter = CategorizedListAdapter(list , requireContext()){
            val action = HomeNewsFragmentDirections.actionHomeNewsToDescriptionNews(list[it])
            findNavController().navigate(action)
        }
        binding.technologyNewsList.layoutManager = LinearLayoutManager(requireContext())
        binding.technologyNewsList.isNestedScrollingEnabled = false
    }
}