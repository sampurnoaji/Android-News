package id.petersam.news.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Query("SELECT * FROM NewsEntity")
    fun getNewsList(): LiveData<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(newsList: List<NewsEntity>)

    @Query("SELECT * FROM NewsEntity WHERE :country LIKE country")
    fun getNewsListByCountry(country: String): LiveData<List<NewsEntity>>
}