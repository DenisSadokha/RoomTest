package com.example.sqltest.domain.features.musicSchoolInfo.entities

import com.example.sqltest.base.entity.Entity
import com.example.sqltest.base.entity.EntityModel

data class CourseEntity(
    val courses: List<Course>
) : Entity

data class Course(
    override val id: Int,
    val courseDuration: String,
    val name: String
) : EntityModel {
    override fun toString(): String {
        return this.name
    }
}