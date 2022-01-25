package com.example.sqltest.domain.features.musicSchoolInfo.entities

import com.example.sqltest.base.entity.Entity

data class AllMembersMuSchool(
    val student: StudentEntity,
    val teacher: TeacherEntity,
    val course: CourseEntity
) : Entity