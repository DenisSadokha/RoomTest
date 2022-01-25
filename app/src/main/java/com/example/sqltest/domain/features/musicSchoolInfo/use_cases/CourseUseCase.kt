package com.example.sqltest.domain.features.musicSchoolInfo.use_cases

import com.example.sqltest.base.state.ResultState
import com.example.sqltest.data.features.musicSchoolInfo.repositories.SchoolInfoRepository
import com.example.sqltest.domain.features.musicSchoolInfo.entities.CourseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CourseUseCase @Inject constructor( val repository: SchoolInfoRepository) {


    suspend fun getCourses(isSorted: Boolean): Flow<ResultState<CourseEntity>> = repository.getCourses(isSorted)


    suspend fun insertCourse(duration: String, name: String) =
        repository.insertCourse(duration, name)


    suspend fun deleteCourseById(id: Int) {
        repository.deleteCourseIdInMainTable(id)
        repository.deleteCourseById(id)
    }

    suspend fun editCourse(id: Int, newDuration: String, name: String) =
        repository.editCourse(id, newDuration, name)


}