package id.petersam.news.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import id.petersam.news.database.NewsDatabase
import id.petersam.news.database.asDomainModel
import id.petersam.news.domain.News
import id.petersam.news.network.NewsNetwork
import id.petersam.news.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class HomeRepository(private val database: NewsDatabase) {
    suspend fun refreshNewsList() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh news list")
            val dto = NewsNetwork.newsService.getTopHeadlinesAsync("us").await()
            database.newsDao.insertAll(dto.asDatabaseModel())
        }
    }

    val newsList: LiveData<List<News>> = Transformations.map(database.newsDao.getNewsList()) {
        it.asDomainModel()
    }
}