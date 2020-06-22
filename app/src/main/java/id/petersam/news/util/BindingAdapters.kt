package id.petersam.news.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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