package kashyap.`in`.androidbestpractices.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface SomeDaoDao :
    BaseDao<String> {
    @Query("")
    fun getDao(): LiveData<List<String>>
}