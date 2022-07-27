package com.alisamir.newsapp.ui.home.categorized.entertainment

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
import com.alisamir.newsapp.databinding.FragmentEntertainmentBinding
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.ui.home.HomeNewsFragmentDirections


class EntertainmentFragment : Fragment() {
    lateinit var binding: FragmentEntertainmentBinding
    val entertainment_Model:entertainmentModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntertainmentBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        entertainment_Model.entertainmentArticles.observe(viewLifecycleOwner){
            setEntertainmentAdapter(it)
        }
    }

    private fun setEntertainmentAdapter(list: List<Article>){
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.entertainmentNewsList.adapter = CategorizedListAdapter(list , requireContext()){
            val action = HomeNewsFragmentDirections.actionHomeNewsToDescriptionNews(list[it])
            findNavController().navigate(action)
        }
        binding.entertainmentNewsList.layoutManager = layoutManager
        binding.entertainmentNewsList.isNestedScrollingEnabled = false
    }
}