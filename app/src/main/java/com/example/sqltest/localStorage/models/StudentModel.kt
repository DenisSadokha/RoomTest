package com.example.sqltest.localStorage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.sqltest.app.utils.StudentTable
import com.example.sqltest.app.utils.TableNames

@Entity(tableName = TableNames.STUDENT_TABLE,
    indices =
    [Index(value = ["id"])])
data class StudentModel(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = StudentTable.id)
    val id: Int,
    @ColumnInfo(name = StudentTable.startEducation)
    val startEducation: String,
    @ColumnInfo(name = StudentTable.name)
    val name: String
)