package com.example.sqltest.localStorage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.sqltest.app.utils.CourseTable
import com.example.sqltest.app.utils.TableNames

@Entity(
    tableName = TableNames.COURSE_TABLE,
    indices =
    [Index(value = ["id"])]
)

data class CourseModel(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = CourseTable.id)
    val id: Int,
    @ColumnInfo(name = CourseTable.educationDuration)
    val educationDuration: String,
    @ColumnInfo(name = CourseTable.name)
    val name: String

)