package kashyap.`in`.androidbestpractices.data

import androidx.room.Database
import androidx.room.RoomDatabase
import kashyap.`in`.androidbestpractices.data.dao.SomeDaoDao

@Database(entities = [], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun someDao(): SomeDaoDao

}