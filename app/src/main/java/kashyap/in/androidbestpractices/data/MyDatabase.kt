package kashyap.`in`.androidbestpractices.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kashyap.`in`.androidbestpractices.data.dao.UserDetailsDao
import kashyap.`in`.androidbestpractices.data.entities.UserDetailsEntity

@TypeConverters(CustomConverters::class)
@Database(entities = [UserDetailsEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun userDetailsDao(): UserDetailsDao

//    DB Migrations
//    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//        override fun migrate(@NonNull database: SupportSQLiteDatabase) {
//            database.execSQL("ALTER TABLE Name ADD COLUMN Name INTEGER")
//        }
//    }
}