package com.example.sqltest.data.features.musicSchoolInfo.mapper

import com.example.sqltest.domain.features.musicSchoolInfo.entities.*
import com.example.sqltest.localStorage.models.CourseModel
import com.example.sqltest.localStorage.models.StudentModel
import com.example.sqltest.localStorage.models.TeacherModel

fun List<CourseModel>.toEntity(): CourseEntity {
    val list: MutableList<Course> = mutableListOf()
    this.forEach {
        list.add(it.toEntity())
    }
    return CourseEntity(
        courses = list
    )
}

fun CourseModel.toEntity(): Course {
    return Course(
        id = this.id,
        courseDuration = this.educationDuration,
        name = this.name
    )
}

fun List<StudentModel>.toEntity(listNames: List<String> = mutableListOf()): StudentEntity {
    val list: MutableList<Student> = mutableListOf()
    this.forEach {
        list.add(it.toEntity())
    }
    return StudentEntity(
        students = list,
        studentAndTeacherNames = listNames
    )
}

fun StudentModel.toEntity(): Student {
    return Student(
        id = this.id,
        startEducation = this.startEducation,
        name = this.name
    )
}

fun List<TeacherModel>.toEntity(): TeacherEntity {
    val list: MutableList<Teacher> = mutableListOf()
    this.forEach {
        list.add(it.toEntity())
    }
    return TeacherEntity(
        teachers = list
    )
}

fun TeacherModel.toEntity(): Teacher {
    return Teacher(
        id = this.id,
        laureateDegree = this.laureateDegree,
        name = this.name
    )
}


