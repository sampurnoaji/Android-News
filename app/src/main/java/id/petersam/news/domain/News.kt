package id.petersam.news.domain

import id.petersam.news.util.smartTruncate

data class News(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) {
    val shortDescription: String
        get() = description.smartTruncate(200)
}