package com.example.sqltest.domain.features.musicSchoolInfo.entities

import com.example.sqltest.base.entity.Entity
import com.example.sqltest.base.entity.EntityModel

data class MusicSchoolEntity(
    val list: List<MusicsSchoolStudent>,
    val count: Int,
    val joins: List<String>
) : Entity

data class MusicsSchoolStudent(
    override val id: Int,
    val student: Student,
    val teacher: Teacher,
    val course: Course
) : EntityModel