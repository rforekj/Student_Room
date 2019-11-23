package com.example.studentroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {
    private val repository: StudentRepository
    val allStudent: LiveData<List<Student>>

    init {
        repository = StudentRepository(StudentRoomDataBase.getDatabase(application)!!.studentDao())
        allStudent = repository.allStudent
    }

    fun insert(student: Student) = viewModelScope.launch{
        repository.insert(student)
    }

    fun delete(name: String) = viewModelScope.launch {
        repository.delete(name)
    }

    fun update(name: String, age: String, avt: String, name_before: String) = viewModelScope.launch{
        repository.update(name, age, avt, name_before)
    }
}