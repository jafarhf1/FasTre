package com.example.fastre.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fastre.R
import com.example.fastre.core.domain.model.News
import com.example.fastre.core.ui.ViewModelFactory
import com.example.fastre.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    private var _binding: ActivityNewsDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsDetailViewModel: NewsDetailViewModel

    companion object {
        const val EXTRA_NEWS_ID = "extra_news_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.VISIBLE
        val factory = ViewModelFactory.getInstance(this)
        newsDetailViewModel = ViewModelProvider(this, factory)[NewsDetailViewModel::class.java]

        val detailNews = intent.getParcelableExtra<News>(EXTRA_NEWS_ID)
        showDetailNews(detailNews)
    }

    private fun showDetailNews(detailNews: News?) {
        binding.progressBar.visibility = View.GONE
        detailNews.let {

            if (detailNews != null) {
                binding.tvNewsTitle.text = detailNews.title
                binding.tvNewsDate.text = detailNews.date
                binding.tvNewsDetail.text = detailNews.detail

                Glide.with(this@NewsDetailActivity)
                    .load(detailNews.photo)
                    .into(binding.ivNewsPhoto)


                var statusFavorite = detailNews.isBookmarked
                setStatusFavorite(statusFavorite)

                binding.btnBookmarked.setOnClickListener {
                    statusFavorite = !statusFavorite
                    newsDetailViewModel.setFavoriteNews(detailNews, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }

                binding.btnBack.setOnClickListener {
                    super.onBackPressed()
                }

            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnBookmarked.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_white_bookmarked))
        } else {
            binding.btnBookmarked.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_white_bookmark))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}