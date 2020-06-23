package id.petersam.news.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.petersam.news.databinding.ItemListNewsBinding
import id.petersam.news.domain.News

class NewsListAdapter(
    private val listener: NewsListListener
) : ListAdapter<News, NewsListAdapter.NewsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.itemView.setOnClickListener {
            listener.onClick(news)
        }
        holder.bind(news)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.publishedAt == newItem.publishedAt
        }

    }

    class NewsViewHolder(private val binding: ItemListNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.news = news
            binding.executePendingBindings()
        }
    }

    class NewsListListener(val listener: (news: News) -> Unit) {
        fun onClick(news: News) = listener(news)
    }
}