package com.example.sqltest.domain.features.musicSchoolInfo.entities

import com.example.sqltest.base.entity.Entity
import com.example.sqltest.base.entity.EntityModel

data class TeacherEntity(
    val teachers: List<Teacher>
) : Entity

data class Teacher(
    override val id: Int,
    val laureateDegree: Boolean,
    val name: String
) : EntityModel {
    override fun toString(): String {
        return this.name
    }
}