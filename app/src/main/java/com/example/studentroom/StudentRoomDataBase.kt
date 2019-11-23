package com.example.studentroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Student::class), version = 2, exportSchema = false)
abstract class StudentRoomDataBase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
    companion object
    {
        @Volatile
        private var instance: StudentRoomDataBase? = null
        fun getDatabase(context: Context): StudentRoomDataBase? {
            if(instance == null) {
                synchronized(this){
                    instance = Room.databaseBuilder(context.applicationContext, StudentRoomDataBase::class.java,
                            "student_database" ).fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }
    }
}