package id.petersam.news.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.petersam.news.database.getDatabase
import id.petersam.news.domain.News
import id.petersam.news.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(application: Application) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val homeRepository = HomeRepository(getDatabase(application))

    val newsList = homeRepository.newsList

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val _navigateToSelectedNews = MutableLiveData<News>()
    val navigateToSelectedNews: LiveData<News>
        get() = _navigateToSelectedNews

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() = viewModelScope.launch {
        try {
            homeRepository.refreshNewsList()

            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        } catch (networkError: IOException) {
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun navigateToNewsDetail(news: News) {
        _navigateToSelectedNews.value = news
    }

    fun onNewsDetailNavigated() {
        _navigateToSelectedNews.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}