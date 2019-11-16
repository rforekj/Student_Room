package com.example.studentroom

import androidx.lifecycle.LiveData

class StudentRepository(private val studentDao: StudentDao){
    val allStudent = studentDao.selectAll()
    suspend fun insert(student: Student){
        studentDao.insert(student)
    }

    suspend fun delete(name: String) {
        studentDao.delete(name)
    }

    suspend fun update(name: String, age: String,name_before: String){
        studentDao.update(name, age,name_before)
    }
}