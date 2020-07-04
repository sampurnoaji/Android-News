package id.petersam.news.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import id.petersam.news.database.NewsEntity
import id.petersam.news.domain.News

@JsonClass(generateAdapter = true)
data class NewsDataTransferObjects(@Json(name = "articles") val newsList: List<NetworkNews>)

@JsonClass(generateAdapter = true)
data class NetworkNews(
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
    @Json(name = "source")
    val source: Source? = null
) {
    @JsonClass(generateAdapter = true)
    data class Source(
        @Json(name = "id")
        val sourceId: String? = null,
        @Json(name = "name")
        val sourceName: String? = null
    )
}

fun NewsDataTransferObjects.asDomainModel(): List<News> {
    return newsList.map {
        News(
            author = it.author ?: "",
            title = it.title ?: "",
            description = it.description ?: "",
            url = it.url ?: "",
            urlToImage = it.urlToImage ?: "",
            publishedAt = it.publishedAt ?: "",
            content = it.content ?: "",
            sourceId = it.source?.sourceId ?: "",
            sourceName = it.source?.sourceName ?: ""
        )
    }
}

fun NewsDataTransferObjects.asDatabaseModel(): List<NewsEntity> {
    return newsList.map {
        NewsEntity(
            author = it.author ?: "",
            title = it.title ?: "",
            description = it.description ?: "",
            url = it.url ?: "",
            urlToImage = it.urlToImage ?: "",
            publishedAt = it.publishedAt ?: "",
            content = it.content ?: "",
            sourceId = it.source?.sourceId ?: "",
            sourceName = it.source?.sourceName ?: ""
        )
    }
}