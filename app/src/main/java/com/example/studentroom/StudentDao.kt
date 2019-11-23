package com.example.studentroom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(studen:Student)

    @Query("delete from Student_table where name = :name")
    suspend fun delete(name: String)

    @Query("Select * from Student_table")
    fun selectAll(): LiveData<List<Student>>

    @Query("delete from Student_table")
    suspend fun deleteAll()

    @Query("update Student_table set name = :name, age = :age, avt = :avt where name = :name_before")
    suspend fun update(name: String, age: String,avt: String, name_before: String)
}