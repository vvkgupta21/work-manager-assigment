package personal.project.workmanagerassignment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import personal.project.workmanagerassignment.Convertors

@Database(entities = [PostModel::class], version = 1)
@TypeConverters(Convertors::class)
abstract class PostDatabase : RoomDatabase() {
    abstract fun contactDao(): PostDao

    companion object {

//        val migration_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE post ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
//            }
//        }

        @Volatile
        private var INSTANCE: PostDatabase? = null
        fun getDataBase(context: Context): PostDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            PostDatabase::class.java,
                            "postDb"
                        ).build()
                }
            }
            return INSTANCE!!
        }
    }

}