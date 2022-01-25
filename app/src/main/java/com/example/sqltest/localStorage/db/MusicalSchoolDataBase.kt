package com.example.sqltest.localStorage.db

import android.content.Context
import android.util.Log
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sqltest.app.utils.CourseTable
import com.example.sqltest.app.utils.TableNames
import com.example.sqltest.localStorage.dao.MainDao
import com.example.sqltest.localStorage.models.CourseModel
import com.example.sqltest.localStorage.models.MusicalSchoolModel
import com.example.sqltest.localStorage.models.StudentModel
import com.example.sqltest.localStorage.models.TeacherModel
import kotlin.reflect.KClass

@Database(
    entities = [
        StudentModel::class,
        CourseModel::class,
        TeacherModel::class,
        MusicalSchoolModel::class
    ],
    version = 1,
//    autoMigrations = [AutoMigration(from = 3, to = 4)],
//    exportSchema = true
)
abstract class MusicalSchoolDataBase : RoomDatabase() {




    abstract fun musicalSchoolDao(): MainDao


    companion object {

        private var INSTANCE: MusicalSchoolDataBase? = null


        fun getDatabase(context: Context): MusicalSchoolDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MusicalSchoolDataBase::class.java,
                    "school_database"
                )
                    .addCallback(DB_CALLBACK)
                    .build()
                INSTANCE = instance
                instance
            }
        }

         val DB_CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                Log.d("SOMETAG", "create")
                super.onCreate(db)
                db.execSQL("CREATE TRIGGER name_test AFTER INSERT ON ${TableNames.COURSE_TABLE} BEGIN INSERT INTO ${TableNames.COURSE_TABLE} (educationDuration, name) VALUES (:qw, :vasya)")
            }
        }


    }
}