package id.petersam.news.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.petersam.news.domain.News

class DetailViewModel(news: News) : ViewModel() {

    private val _selectedNews = MutableLiveData<News>()
    val selectedNews: LiveData<News>
        get() = _selectedNews

    init {
        _selectedNews.value = news
    }
}