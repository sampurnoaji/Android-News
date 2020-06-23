package id.petersam.news.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.petersam.news.domain.News
import id.petersam.news.ui.home.NewsListAdapter

@BindingAdapter("isNetworkError", "newsList")
fun hideIfNetworkError(view: View, isNetworkError: Boolean, newsList: Any?) {
    view.visibility = if (newsList != null) View.GONE else View.VISIBLE

    if (isNetworkError) {
        view.visibility = View.GONE
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("newsListData")
fun bindNewsRecyclerView(recyclerView: RecyclerView, data: List<News>?) {
    val adapter = recyclerView.adapter as NewsListAdapter
    adapter.submitList(data)
}