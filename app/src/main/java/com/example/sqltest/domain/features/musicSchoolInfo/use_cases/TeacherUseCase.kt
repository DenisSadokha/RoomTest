package com.example.sqltest.domain.features.musicSchoolInfo.use_cases

import com.example.sqltest.base.state.ResultState
import com.example.sqltest.data.features.musicSchoolInfo.repositories.SchoolInfoRepository
import com.example.sqltest.domain.features.musicSchoolInfo.entities.TeacherEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeacherUseCase @Inject constructor( val repository: SchoolInfoRepository) {

    suspend fun getTeachers(onlyLaureate: Boolean): Flow<ResultState<TeacherEntity>> = repository.getTeachers(onlyLaureate)

    suspend fun insertTeacher(laureate: Boolean, name: String) =
        repository.insertTeacher(laureate, name)


    suspend fun editTeacher(id: Int, laureate: Boolean, name: String) =
        repository.editTeacher(id, laureate, name)

    suspend fun deleteTeacherById(id: Int) {
        repository.deleteTeacherByIdInMainTable(id)
        repository.deleteTeacherById(id)
    }


}