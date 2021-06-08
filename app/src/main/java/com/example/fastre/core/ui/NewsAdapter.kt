package com.example.fastre.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fastre.R
import com.example.fastre.core.domain.model.News
import com.example.fastre.databinding.ItemNewsBinding
import com.example.fastre.ui.news.NewsDetailActivity
import java.util.ArrayList

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {
    private var listData = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    fun setData(newListData: List<News>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNewsBinding.bind(itemView)
        fun bind(data: News) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.photo)
                    .into(ivNewsPhoto)

                tvNewsTitle.text = data.title
                tvNewsDate.text = data.date

                btnReadMore.setOnClickListener {
                    val intent = Intent(itemView.context, NewsDetailActivity::class.java)
                    intent.putExtra(NewsDetailActivity.EXTRA_NEWS_ID, data)
                    itemView.context.startActivity(intent)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}