package com.example.sqltest.localStorage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sqltest.app.utils.SchoolTable
import com.example.sqltest.app.utils.TableNames

@Entity(tableName = TableNames.MAIN_TABLE)
data class MusicalSchoolModel(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = SchoolTable.id)
    val id: Int,
    @ColumnInfo(name = SchoolTable.studentId)
    val studentId: Int,
    @ColumnInfo(name = SchoolTable.teacherId)
    val teacherId: Int,
    @ColumnInfo(name = SchoolTable.courseId)
    val courseId: Int

)