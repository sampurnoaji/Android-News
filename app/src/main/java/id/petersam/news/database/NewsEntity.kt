package id.petersam.news.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.petersam.news.domain.News

@Entity
data class NewsEntity constructor(
    val country: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    @PrimaryKey
    val publishedAt: String,
    val content: String,
    val sourceId: String,
    val sourceName: String
)

fun List<NewsEntity>.asDomainModel(): List<News> {
    return map {
        News(
            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content,
            sourceId = it.sourceId,
            sourceName = it.sourceName
        )
    }
}