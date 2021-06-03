package id.petersam.news.domain

import android.os.Parcelable
import id.petersam.news.util.smartTruncate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val sourceId: String,
    val sourceName: String
) : Parcelable {
    val shortDescription: String
        get() = description.smartTruncate(200)
}