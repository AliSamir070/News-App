package com.alisamir.newsapp.data

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alisamir.newsapp.R
import com.alisamir.newsapp.databinding.CategorizedItemBinding
import com.alisamir.newsapp.pojo.Article
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

class CategorizedListAdapter(val articles:List<Article> , val context: Context ,val onItemClick:(position:Int)->Unit):RecyclerView.Adapter<CategorizedListAdapter.myViewHolder>() {
    class myViewHolder(val binding: CategorizedItemBinding,val context: Context ,val onItemClick:(position:Int)->Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article , position: Int){
            if(article.urlToImage!=null){
                binding.articlIv.load(article.urlToImage){
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.broken_image)
                }
            }else{
                binding.articlIv.load(R.drawable.back_no_image){
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.broken_image)
                }
            }
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'" , Locale.getDefault())
            val time = format.parse(article.publishedAt?:"")?.time
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time!!
            val prettytime = PrettyTime(Locale.getDefault())
            Log.d("TAG", "prettyTime: $prettytime")
            Log.d("TAG", "Time: ${calendar.get(Calendar.HOUR)}")
            if(article.author == null){
                binding.authorTv.text = ""
            }else{
                if(article.author.contains("<a href")){
                    binding.authorTv.text = Html.fromHtml(article.author)
                }else{
                    binding.authorTv.text = article.author
                }
            }
            binding.timeCategoryTv.text = prettytime.format(Date(time!!))
            binding.titleCategoryTv.text = article.title
            binding.root.setOnClickListener {
                onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(CategorizedItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),context , onItemClick)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(articles[position] , position)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}