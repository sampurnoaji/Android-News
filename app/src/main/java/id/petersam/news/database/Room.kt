package id.petersam.news.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM NewsEntity")
    fun getNewsList(): LiveData<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(newsList: List<NewsEntity>)
}

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}

private lateinit var INSTANCE: NewsDatabase

fun getDatabase(context: Context): NewsDatabase {
    synchronized(NewsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                NewsDatabase::class.java,
                "news").build()
        }
    }
    return INSTANCE
}