package com.example.studentroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student_table")
class Student(@PrimaryKey @ColumnInfo(name = "name") var name: String, @ColumnInfo(name = "age") var age: String)