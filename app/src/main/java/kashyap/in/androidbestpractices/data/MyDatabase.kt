package kashyap.`in`.androidbestpractices.data

import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kashyap.`in`.androidbestpractices.data.dao.GithubRepoDetailsDao
import kashyap.`in`.androidbestpractices.data.models.GithubRepoDetails


@Database(entities = [GithubRepoDetails::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun githubRepoDetailsDao(): GithubRepoDetailsDao

//    DB Migrations
//    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//        override fun migrate(@NonNull database: SupportSQLiteDatabase) {
//            database.execSQL("ALTER TABLE Name ADD COLUMN Name INTEGER")
//        }
//    }

    open fun clearDatabase() {
        githubRepoDetailsDao().clear()
    }

}