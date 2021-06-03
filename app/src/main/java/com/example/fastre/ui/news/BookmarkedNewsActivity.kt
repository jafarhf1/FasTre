package com.example.fastre.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fastre.R
import com.example.fastre.core.ui.NewsAdapter
import com.example.fastre.core.ui.ViewModelFactory
import com.example.fastre.databinding.ActivityBookmarkedNewsBinding

class BookmarkedNewsActivity : AppCompatActivity() {
    private lateinit var viewModel: BookmarkedNewsViewModel

    private var _binding: ActivityBookmarkedNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBookmarkedNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsAdapter = NewsAdapter()
        binding.progressBar.visibility = View.VISIBLE

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[BookmarkedNewsViewModel::class.java]

        viewModel.favoriteNews.observe(this, { dataNews ->
            binding.progressBar.visibility = View.GONE
            newsAdapter.setData(dataNews)
            if(dataNews.isEmpty()) {
                val text = getString(R.string.you_don_t_have_any_bookmarked_news)
                binding.emptyBookmarked.text = text
                binding.emptyBookmarked.visibility = View.VISIBLE
            }  else {
                binding.emptyBookmarked.visibility = View.GONE
            }
        })

        with(binding.rvNewsFav) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}