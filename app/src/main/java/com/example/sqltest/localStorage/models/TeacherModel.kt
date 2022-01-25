package com.example.sqltest.localStorage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.sqltest.app.utils.TableNames
import com.example.sqltest.app.utils.TeacherTable

@Entity(tableName = TableNames.TEACHER_TABLE,
    indices =
    [Index(value = ["id"])])
data class TeacherModel(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = TeacherTable.id)
    val id: Int,
    @ColumnInfo(name = TeacherTable.laureateDegree)
    val laureateDegree: Boolean,
    @ColumnInfo(name = TeacherTable.name)
    val name: String
)