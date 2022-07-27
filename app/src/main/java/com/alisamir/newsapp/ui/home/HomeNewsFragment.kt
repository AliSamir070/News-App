package com.alisamir.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.alisamir.newsapp.R
import com.alisamir.newsapp.data.BreakingNewsAdapter
import com.alisamir.newsapp.data.CategorizedContainerAdapter
import com.alisamir.newsapp.databinding.FragmentHomeNewsBinding
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.ui.home.categorized.health.HealthFragment
import com.alisamir.newsapp.ui.home.categorized.politics.PoliticsFragment
import com.alisamir.newsapp.ui.home.categorized.sports.SportsFragment
import com.alisamir.newsapp.ui.home.categorized.business.BusinessFragment
import com.alisamir.newsapp.ui.home.categorized.entertainment.EntertainmentFragment
import com.alisamir.newsapp.ui.home.categorized.science.ScienceFragment
import com.alisamir.newsapp.ui.home.categorized.technology.TechnologyFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeNewsFragment : Fragment() {
    lateinit var binding:FragmentHomeNewsBinding
    val homeNewsModel: HomeNewsModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
      /*  binding.button.setOnClickListener {
            findNavController().navigate(HomeNewsFragmentDirections.actionHomeNewsToDescriptionNews())
            (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)
        }*/
        makeCategorizedContainer()
        val controller = findNavController()
        val appBarConfiguration = AppBarConfiguration(controller.graph)
        NavigationUI.setupActionBarWithNavController(activity as AppCompatActivity,controller,appBarConfiguration)
        homeNewsModel.breakingNewsArticles.observe(viewLifecycleOwner){
            setBreakingNewsAdapter(it)
        }

    }

    private fun makeCategorizedContainer(){
        val titleTabsList = listOf(
            "Business",
            "Health",
            "Politics",
            "Sports",
            "Technology",
            "Science",
            "Entertainment"
        )
        val iconsTabsList = listOf(
            R.drawable.ic_business,
            R.drawable.ic_health,
            R.drawable.ic_politics,
            R.drawable.ic_baseline_sports_soccer_24,
            R.drawable.ic_technology,
            R.drawable.ic_science,
            R.drawable.ic_entertainment
        )
        val fragments = listOf(
            BusinessFragment(),
            HealthFragment(),
            PoliticsFragment(),
            SportsFragment(),
            TechnologyFragment(),
            ScienceFragment(),
            EntertainmentFragment()
        )
        binding.categorizedFragmentsContainer.adapter = CategorizedContainerAdapter(requireActivity(),fragments)
        val mediator = TabLayoutMediator(binding.newsCategoryTabL,binding.categorizedFragmentsContainer){tab,position ->
            tab.text = titleTabsList[position]
            tab.icon = ContextCompat.getDrawable(requireContext(),iconsTabsList[position])
        }
        mediator.attach()
    }

    private fun setBreakingNewsAdapter(articles:List<Article>){

        val adapter = BreakingNewsAdapter(articles , requireContext()){
            val action = HomeNewsFragmentDirections.actionHomeNewsToDescriptionNews(articles[it])
            findNavController().navigate(action)
        }
        binding.breakingnewslist.adapter = adapter
        binding.breakingnewslist.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

    }

}