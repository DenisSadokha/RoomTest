package com.example.sqltest.domain.features.musicSchoolInfo.entities

import com.example.sqltest.base.entity.Entity
import com.example.sqltest.base.entity.EntityModel

data class StudentEntity(
    val students: List<Student>,
    val studentAndTeacherNames: List<String>
) : Entity

data class Student(
    override val id: Int,
    val startEducation: String,
    val name: String
) : EntityModel {
    override fun toString(): String {
        return this.name
    }
}