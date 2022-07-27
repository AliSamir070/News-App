package com.alisamir.newsapp.data

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alisamir.newsapp.R
import com.alisamir.newsapp.databinding.BreakingNewsItemLayoutBinding
import com.alisamir.newsapp.pojo.Article
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

class BreakingNewsAdapter(val breakngNewsList: List<Article> , val context: Context,val onItemClick:(position:Int)->Unit ) : RecyclerView.Adapter<BreakingNewsAdapter.myViewHolder>() {

    class myViewHolder(val binding: BreakingNewsItemLayoutBinding , val onItemClick: (position: Int) -> Unit , val context: Context) :RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article , position: Int){
            /*Glide.with(context).load(Uri.parse(article.urlToImage?:("https://mcdn.wallpapersafari.com/medium/84/1/RDlyFi.png")))
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.broken_image)
                .into(binding.newsImageBackIV)*/
            if (article.urlToImage!=null) {
                binding.newsImageBackIV.load(article.urlToImage) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.broken_image)
                }
            }else{
                binding.newsImageBackIV.load(R.drawable.back_no_image) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.broken_image)
                }
            }
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            Log.d("TAG", article.publishedAt?:"")
            val time = format.parse(article.publishedAt?:"")?.time
            val prettyTime = PrettyTime(Locale.getDefault())
            binding.titleTv.text = article.title
            binding.timeTv.text = prettyTime.format(Date(time!!))
            binding.root.setOnClickListener {
                onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(BreakingNewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false) , onItemClick , context)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(breakngNewsList[position],position)
    }

    override fun getItemCount(): Int {
        return  breakngNewsList.size
    }
}