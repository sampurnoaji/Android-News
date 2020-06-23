package id.petersam.news.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.petersam.news.domain.News

class DetailViewModelFactory(private val news: News) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(news) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}